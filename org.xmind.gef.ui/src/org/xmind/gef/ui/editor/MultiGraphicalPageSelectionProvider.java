/* ******************************************************************************
 * Copyright (c) 2006-2008 XMind Ltd. and others.
 * 
 * This file is a part of XMind 3. XMind releases 3 and
 * above are dual-licensed under the Eclipse Public License (EPL),
 * which is available at http://www.eclipse.org/legal/epl-v10.html
 * and the GNU Lesser General Public License (LGPL), 
 * which is available at http://www.gnu.org/licenses/lgpl.html
 * See http://www.xmind.net/license.html for details.
 * 
 * Contributors:
 *     XMind Ltd. - initial API and implementation
 *******************************************************************************/
package org.xmind.gef.ui.editor;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.IPostSelectionProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;

public class MultiGraphicalPageSelectionProvider implements
        IPostSelectionProvider, ISelectionChangedListener {

    private List<ISelectionChangedListener> listeners = new ArrayList<ISelectionChangedListener>();

    private List<ISelectionChangedListener> postListeners = new ArrayList<ISelectionChangedListener>();

    private IGraphicalEditorPage activePage = null;

    public void addPostSelectionChangedListener(
            ISelectionChangedListener listener) {
        postListeners.add(listener);
    }

    public void addSelectionChangedListener(ISelectionChangedListener listener) {
        listeners.add(listener);
    }

    public void removePostSelectionChangedListener(
            ISelectionChangedListener listener) {
        postListeners.remove(listener);
    }

    public void removeSelectionChangedListener(
            ISelectionChangedListener listener) {
        listeners.remove(listener);
    }

    public void setActivePage(IGraphicalEditorPage page) {
        if (page == this.activePage)
            return;
        if (this.activePage != null) {
            ISelectionProvider selectionProvider = this.activePage
                    .getSelectionProvider();
            if (selectionProvider != null)
                selectionProvider.removeSelectionChangedListener(this);
        }
        this.activePage = page;
        if (page != null) {
            ISelectionProvider selectionProvider = page.getSelectionProvider();
            if (selectionProvider != null)
                selectionProvider.addSelectionChangedListener(this);
        }
        fireSelectionChangedEvent(new SelectionChangedEvent(this,
                getSelection()));
    }

    protected void fireSelectionChangedEvent(SelectionChangedEvent event) {
        fireSelectionChangedEvent(new SelectionChangedEvent(this, event
                .getSelection()), listeners);
    }

    protected void firePostSelectionChangedEvent(SelectionChangedEvent event) {
        fireSelectionChangedEvent(new SelectionChangedEvent(this, event
                .getSelection()), postListeners);
    }

    private void fireSelectionChangedEvent(SelectionChangedEvent event,
            List<ISelectionChangedListener> listeners) {
        for (Object listener : listeners.toArray()) {
            ((ISelectionChangedListener) listener).selectionChanged(event);
        }
    }

    public ISelection getSelection() {
        if (activePage != null) {
            ISelectionProvider selectionProvider = activePage
                    .getSelectionProvider();
            if (selectionProvider != null)
                return selectionProvider.getSelection();
        }
        return StructuredSelection.EMPTY;
    }

    public void setSelection(ISelection selection) {
        if (activePage != null) {
            ISelectionProvider selectionProvider = activePage
                    .getSelectionProvider();
            if (selectionProvider != null) {
                selectionProvider.setSelection(selection);
            }
        }
    }

    /**
     * @see org.eclipse.jface.viewers.ISelectionChangedListener#selectionChanged(org.eclipse.jface.viewers.SelectionChangedEvent)
     */
    public void selectionChanged(SelectionChangedEvent event) {
        event = new SelectionChangedEvent(this, event.getSelection());
        fireSelectionChangedEvent(event);
    }

}