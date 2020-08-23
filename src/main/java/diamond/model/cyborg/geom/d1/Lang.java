/**
 * DEFOX - Diagram Editor for Origami Creators
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.model.cyborg.geom.d1;

import java.awt.geom.Rectangle2D.Double;

import diamond.model.cyborg.geom.Cyborg;
import diamond.model.cyborg.geom.d0.Direction;
import diamond.model.cyborg.geom.d0.Vertex;
import diamond.view.ui.screen.AbstractScreen;

/**
 * @author Kei Morisue
 *
 */
public class Lang implements Cyborg {
    private double t;
    private double l;
    private Direction d;

    public Lang(double t, double d) {
        setT(t);
        this.l = d;
    }

    public double getT() {
        return t;
    }

    @Deprecated
    public void setT(double t) {
        this.t = t % Math.PI;
        this.d = new Direction(
                Math.cos(t),
                Math.sin(t));
    }

    public double getL() {
        return l;
    }

    @Deprecated
    public void setL(double l) {
        this.l = l;
    }

    @Override
    public double dist(Vertex v, AbstractScreen screen) {
        return Math.abs(l + v.dir().prod(d)) / d.norm();
    }

    @Override
    public Double clip(AbstractScreen screen) {
        int w = screen.getWidth();
        int h = screen.getHeight();
        return new Double(-(w >> 1), -(h >> 1), w, h);
    }
}
