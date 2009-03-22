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
package org.xmind.gef.event;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.swt.widgets.Event;
import org.xmind.gef.part.IPart;

/**
 * @author Brian Sun
 */
public class MouseWheelEvent extends MouseEvent {

    public boolean upOrDown;

    public boolean doIt = true;

    /**
     * @param host
     * @param location
     */
    public MouseWheelEvent(IPart host, Point location, boolean upOrDown) {
        super(null, host, true, location);
        this.upOrDown = upOrDown;
    }

    public static MouseWheelEvent createEvent(IPart host, Event e) {
        return new MouseWheelEvent(host, new Point(e.x, e.y), e.count > 0);
    }
}