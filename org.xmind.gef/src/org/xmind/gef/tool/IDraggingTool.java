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
package org.xmind.gef.tool;

import org.eclipse.draw2d.geometry.Point;

public interface IDraggingTool {

    /**
     * A hint value for detecting scrolling when dragging (value=<code>20</code>
     * ).
     * <p>
     * A viewer should scroll itself when user is dragging something over the
     * edge of the canvas and within this range.
     * </p>
     */
    int SCROLLING_DETECTION = 20;

    Point getStartingPosition();

    void setStartingPosition(Point pos);

}