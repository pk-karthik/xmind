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
package org.xmind.ui.internal.fishbone.decorations;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Pattern;
import org.eclipse.swt.widgets.Display;
import org.xmind.gef.draw2d.IRotatableReferencedFigure;
import org.xmind.gef.draw2d.geometry.PrecisionLine;
import org.xmind.gef.draw2d.geometry.PrecisionPoint;
import org.xmind.gef.draw2d.geometry.PrecisionPointList;
import org.xmind.gef.draw2d.geometry.PrecisionPolygon;
import org.xmind.gef.draw2d.geometry.PrecisionRectangle;
import org.xmind.gef.draw2d.geometry.PrecisionRotator;
import org.xmind.gef.draw2d.geometry.PrecisionLine.LineType;
import org.xmind.gef.draw2d.graphics.GradientPattern;
import org.xmind.gef.draw2d.graphics.Path;
import org.xmind.ui.decorations.AbstractTopicDecoration;
import org.xmind.ui.resources.ColorUtils;

public class FishboneTopicDecoration extends AbstractTopicDecoration {

    private static final Point CENTER = new Point(0, 0);

    private PrecisionRotator rotator = null;

    private PrecisionPolygon points = null;

    protected PrecisionRotator r() {
        if (rotator == null) {
            rotator = new PrecisionRotator();
        }
        return rotator;
    }

    public void invalidate() {
        points = null;
        super.invalidate();
    }

    public void validate(IFigure figure) {
        if (points == null)
            points = calcPoints(figure);
        super.validate(figure);
    }

    private PrecisionPolygon calcPoints(IFigure figure) {
        PrecisionPolygon polygon = new PrecisionPolygon(4);
        IRotatableReferencedFigure rf = (IRotatableReferencedFigure) figure;
        r().setAngle(rf.getRotationDegrees());
        r().setOrigin(CENTER.x, CENTER.y);
        PrecisionRectangle bounds = rf.getNormalPreferredBounds(CENTER)
                .getExpanded(-getLineWidth(), -getLineWidth());
        polygon.setPoint(r().tp(bounds.getTopLeft()), 0);
        polygon.setPoint(r().tp(bounds.getTopRight()), 1);
        polygon.setPoint(r().tp(bounds.getBottomRight()), 2);
        polygon.setPoint(r().tp(bounds.getBottomLeft()), 3);
        return polygon;
    }

    protected void sketch(IFigure figure, Path shape, Rectangle box, int purpose) {
        double cx = box.x + box.width * 0.5d;
        double cy = box.y + box.height * 0.5d;
        PrecisionPoint p = points.getPoint(0);
        shape.moveTo((float) (cx + p.x), (float) (cy + p.y));
        for (int i = 1; i < 4; i++) {
            lineTo(shape, cx, cy, points.getPoint(i));
        }
        shape.close();
    }

    private void lineTo(Path shape, double cx, double cy, PrecisionPoint p) {
        shape.lineTo((float) (cx + p.x), (float) (cy + p.y));
    }

    protected Pattern createGradientPattern(IFigure figure, int alpha,
            Color bgColor) {
        PrecisionPoint p1 = points.getPoint(0);
        PrecisionPoint p2 = points.getPoint(3);
        Pattern p = new GradientPattern(Display.getCurrent(), //
                (float) p1.x, (float) p1.y, //
                (float) p2.x, (float) p2.y,//
                ColorUtils.gradientLighter(bgColor), alpha, //
                bgColor, alpha);
        return p;
    }

    protected void paintOutline(IFigure figure, Graphics graphics) {
        // don't paint outline
    }

    protected PrecisionPoint getEast(IFigure figure, double expansion) {
        PrecisionPoint center = new PrecisionPoint(figure.getBounds()
                .getCenter());
        return center.translate(points.getPoint(2));
    }

    protected PrecisionPoint getWest(IFigure figure, double expansion) {
        PrecisionPoint center = new PrecisionPoint(figure.getBounds()
                .getCenter());
        return center.translate(points.getPoint(3));
    }

    protected PrecisionPoint getNorth(IFigure figure, double expansion) {
        PrecisionPoint center = new PrecisionPoint(figure.getBounds()
                .getCenter());
        return center.translate(points.getPoint(0)
                .getCenter(points.getPoint(1)));
    }

    protected PrecisionPoint getSouth(IFigure figure, double expansion) {
        PrecisionPoint center = new PrecisionPoint(figure.getBounds()
                .getCenter());
        return center.translate(points.getPoint(3)
                .getCenter(points.getPoint(2)));
    }

    public PrecisionPoint getAnchorLocation(IFigure figure, double refX,
            double refY, double expansion) {
        checkValidation(figure);
        Rectangle r = figure.getBounds();
        double cx = r.x + r.width * 0.5d;
        double cy = r.y + r.height * 0.5d;
        PrecisionLine ray = new PrecisionLine(cx, cy, refX, refY, LineType.Ray);
        PrecisionPolygon polygon = points.getCopy();
        polygon.translate(cx, cy);
        PrecisionPointList result = polygon.intersect(ray, 0.000001);
        if (result.isEmpty())
            return polygon.getPoint(0);
        return result.getPoint(0);
    }

}