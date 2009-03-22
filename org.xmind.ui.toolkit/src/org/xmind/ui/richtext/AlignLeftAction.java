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
package org.xmind.ui.richtext;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.SWT;
import org.xmind.ui.internal.ToolkitImages;

public class AlignLeftAction extends Action implements IRichTextAction {

    private IRichTextEditViewer viewer;

    public AlignLeftAction(IRichTextEditViewer viewer) {
        this(viewer, RichTextMessages.AlignLeftAction_text, ToolkitImages
                .get(ToolkitImages.ALIGN_LEFT),
                RichTextMessages.AlignLeftAction_toolTip);
    }

    public AlignLeftAction(IRichTextEditViewer viewer, String text,
            ImageDescriptor image, String tooltip) {
        super(text, AS_CHECK_BOX);
        this.viewer = viewer;
        setImageDescriptor(image);
        setToolTipText(tooltip);
    }

    public void dispose() {
        viewer = null;
    }

    public void selctionChanged(IRichTextEditViewer viewer, ISelection selection) {
        setChecked(viewer.getRenderer().getSelectionParagraphAlignment() == SWT.LEFT);
    }

    public void run() {
        if (viewer == null || viewer.getControl().isDisposed())
            return;

        viewer.getRenderer().setSelectionParagraphAlignment(SWT.LEFT);
    }

}