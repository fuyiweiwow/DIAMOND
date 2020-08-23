/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.model.cyborg.geom.d1;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D.Double;

import diamond.model.cyborg.geom.Cyborg;
import diamond.model.cyborg.geom.d0.Vertex;
import diamond.model.cyborg.geom.d0.Wex;
import diamond.model.cyborg.graphics.ShapeBuilder;
import diamond.view.ui.screen.AbstractScreen;
import diamond.view.ui.screen.ScreenMain;
import diamond.view.ui.screen.ScreenStep;

/**
 * @author Kei Morisue
 *
 */
public abstract class SegmentBase implements Cyborg {
    protected Wex w0;
    protected Wex w1;
    protected SegmentType type = SegmentType.CREASE;

    @Deprecated
    protected SegmentBase() {
    }

    public SegmentBase(Wex w0, Wex w1) {
        this.w0 = w0;
        this.w1 = w1;
    }

    @Override
    public double dist(Vertex v, AbstractScreen screen) {
        return v.dist(c(screen));
    }

    @Override
    public Double clip(AbstractScreen screen) {
        Vertex c = c(screen);
        Vertex v0 = screen.v(w0);
        Vertex v1 = screen.v(w1);
        double w = Math.abs(v0.getX() - v1.getX());
        double h = Math.abs(v0.getY() - v1.getY());
        return new Double(
                c.getX() - w * 0.5,
                c.getY() - h * 0.5,
                w,
                h);
    }

    private Vertex c(AbstractScreen screen) {
        return screen.v(w0).c(screen.v(w1));
    }

    @Override
    public void draw(Graphics2D g2d, ScreenMain screen) {
        g2d.draw(ShapeBuilder.build(w0.getP(), w1.getP()));
    }

    @Override
    public void draw(Graphics2D g2d, ScreenStep screen) {
        g2d.draw(ShapeBuilder.build(w0.getQ(), w1.getQ()));
    }

    abstract public void split(Wex v);

    public SegmentBase(SegmentBase segment) {
        this(segment.w0, segment.w1);
        this.type = segment.type;
    }

    @Deprecated
    public SegmentType getType() {
        return type;
    }

    public Wex getW0() {
        return w0;
    }

    @Deprecated
    public void setW0(Wex w0) {
        this.w0 = w0;
    }

    public Wex getW1() {
        return w1;
    }

    public void setW1(Wex w1) {
        this.w1 = w1;
    }

}
