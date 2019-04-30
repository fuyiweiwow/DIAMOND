/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.model.geom.element.diagram.arrow.head;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D.Double;

import diamond.model.geom.element.diagram.arrow.body.AbstractArrowBody;
import diamond.view.screen.draw.style.color.OriArrow;

/**
 * @author long_
 *
 */
public class ValleyFoldArrowHead extends AbstractArrowHead {
    public static final double kurtosis = Math.PI / 7;
    private final static double size = 15.0;

    public ValleyFoldArrowHead(AbstractArrowBody body, boolean isTail) {
        super(body, isTail);
    }

    private GeneralPath getShape(Double p0, Double p1) {
        GeneralPath path = new GeneralPath();
        AffineTransform affineTransform = new AffineTransform();
        Double position = getPosition(p0, p1);
        Double o = new Double(size, 0);
        Double p = new Double(-size, size * Math.sin(kurtosis));
        Double q = new Double(-size, -size * Math.sin(kurtosis));
        affineTransform.translate(position.x, position.y);
        affineTransform.rotate(getAngle(p0, p1));

        affineTransform.transform(o, o);
        affineTransform.transform(p, p);
        affineTransform.transform(q, q);
        path.moveTo(o.x, o.y);
        path.lineTo(p.x, p.y);
        path.lineTo(q.x, q.y);
        path.closePath();
        return path;
    }

    @Override
    public void draw(Graphics2D g2d, Double p0, Double p1) {
        g2d.setColor(OriArrow.ARROW_VALLEY);
        g2d.fill(getShape(p0, p1));
    }

}