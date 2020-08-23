/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.model.cyborg.geom.d1;

import java.awt.Graphics2D;

import diamond.model.cyborg.diagram.Diagram;
import diamond.model.cyborg.geom.d0.Vertex;
import diamond.model.cyborg.geom.d0.Wex;
import diamond.model.cyborg.geom.d2.Face;
import diamond.model.cyborg.graphics.ShapeBuilder;
import diamond.model.cyborg.style.StyleSegment;
import diamond.view.ui.screen.ScreenMain;
import diamond.view.ui.screen.ScreenStep;
import diamond.view.ui.screen.draw.G2DUtil;

/**
 * @author Kei Morisue
 *
 */
public class SegmentCrease extends SegmentBase {
    private Face face;

    @Deprecated
    public SegmentCrease() {
    }

    public SegmentCrease(SegmentBase segment) {
        super(segment.w0, segment.w1);
        this.setType(segment.type);
    }

    public SegmentCrease(Wex v0, Wex v1, SegmentType type) {
        super(v0, v1);
        this.setType(type);
    }

    @Override
    public void draw(Graphics2D g2d, ScreenStep screen) {
        if (type == SegmentType.CREASE) {
            double clip = screen.diagram().getStyleSegment().getClip();
            Wex[] w = clip(clip);
            g2d.draw(ShapeBuilder.build(
                    w[0].getQ(),
                    w[1].getQ()));
        } else {
            g2d.draw(ShapeBuilder.build(
                    w0.getQ(),
                    w1.getQ()));
        }
    }

    private Wex[] clip(double clip) {
        Vertex c = w0.getP().c(w1.getP());
        Vertex d = w0.getQ().c(w1.getQ());
        Wex u0 = (face.isBoundary(w0)) ? w0.scale(clip, c, d) : w0;
        Wex u1 = (face.isBoundary(w1)) ? w1.scale(clip, c, d) : w1;
        Wex[] v0v1 = { u0, u1 };
        return v0v1;
    }

    @Override
    public void setG2d(Graphics2D g2d, ScreenMain screen) {
        Diagram diagram = screen.diagram();
        StyleSegment styleSegment = diagram.getStyleSegment();
        g2d.setColor(styleSegment.getColor(type));
        float scale = (float) G2DUtil.getScale(g2d);
        g2d.setStroke(styleSegment.strokeCrease(scale, type));
    }

    @Override
    public void setG2d(Graphics2D g2d, ScreenStep screen) {
        Diagram diagram = screen.diagram();
        StyleSegment styleSegment = diagram.getStyleSegment();
        g2d.setColor(styleSegment.getStepColor(type));
        float scale = (float) G2DUtil.getScale(g2d);
        g2d.setStroke(styleSegment.strokeCrease(scale, type));
    }

    @Override
    public void split(Wex v) {
        face.remove(this);
        face.add(new SegmentCrease(w0, v, type));
        face.add(new SegmentCrease(v, w1, type));
    }

    public void setFace(Face face) {
        this.face = face;
    }

    @Deprecated
    public Face getFace() {
        return face;
    }

    public void setType(SegmentType type) {
        if (!SegmentType.isCrease(type)) {
            this.type = SegmentType.foldUnfold(type);
        }
        this.type = type;
    }
}
