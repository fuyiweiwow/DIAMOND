/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.model.geom.element.diagram.arrow;

import java.awt.Graphics2D;
import java.awt.geom.Point2D.Double;

import diamond.model.geom.element.cp.OriLine;
import diamond.model.geom.element.cp.OriPoint;
import diamond.model.geom.element.diagram.arrow.body.AbstractArrowBody;
import diamond.model.geom.element.diagram.arrow.head.AbstractArrowHead;
import diamond.model.geom.element.origami.OriHalfEdge;
import diamond.model.geom.util.Point2DUtil;

/**
 * @author long_
 *
 */
public abstract class AbstractArrow {
    AbstractArrowBody body;
    AbstractArrowHead head0;
    AbstractArrowHead head1;

    protected AbstractArrow() {
        buildBody();
        buildHead0();
        buildHead1();
    }

    public void flip() {
        head0.flip();
        head1.flip();
        body.flip();
    }

    protected abstract void buildHead1();

    protected abstract void buildHead0();

    protected abstract void buildBody();

    public void draw(
            Graphics2D g2d,
            Double p0,
            Double p1) {
        body.draw(g2d, p0, p1);
        head0.draw(g2d, p0, p1);
        head1.draw(g2d, p0, p1);
    };

    public void draw(Graphics2D g2d, OriLine oriLine) {
        OriPoint p0 = oriLine.p0;
        OriPoint p1 = oriLine.p1;

        Double center = Point2DUtil.center(p0, p1);
        double scale = 0.05;
        Double scaledP0 = Point2DUtil.plus(center,
                Point2DUtil.scale(Point2DUtil.sub(p0, p1), scale));
        Double scaledP1 = Point2DUtil.plus(center,
                Point2DUtil.scale(Point2DUtil.sub(p1, p0), scale));
        draw(g2d, scaledP0, scaledP1);
    };

    public void draw(Graphics2D g2d, OriHalfEdge he) {
        draw(g2d, he.getSv().getFoldedPosition(),
                he.getEv().getFoldedPosition());
    };
}