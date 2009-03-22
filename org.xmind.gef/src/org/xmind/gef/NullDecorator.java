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
package org.xmind.gef;

import org.eclipse.draw2d.IFigure;
import org.xmind.gef.part.IGraphicalPart;


/**
 * @author Frank Shaka
 */
public class NullDecorator implements IDecorator {
    
    private static IDecorator instance = new NullDecorator();
    
    private NullDecorator() {
    }

    public void decorate( IGraphicalPart part, IFigure figure ) {
    }
    
    public void decorateChildren(IGraphicalPart part, IFigure figure) {
    }
    
    public void activate( IGraphicalPart part, IFigure figure ) {
    }
    
    public void deactivate( IGraphicalPart part, IFigure figure ) {
    }
    
    public static final IDecorator getInstance() {
        return instance;
    }

}