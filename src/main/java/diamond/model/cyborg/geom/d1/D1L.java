/**
 * DEFOX - Diagram Editor for Origami Creators
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.model.cyborg.geom.d1;

import diamond.model.cyborg.geom.d0.Direction;
import diamond.model.cyborg.geom.d0.Vertex;

/**
 * @author Kei Morisue
 *
 */
public final class D1L {
    private double l;
    private Direction d;

    public D1L(double t, double l) {
        setT(t);
        this.l = l;
    }

    @Deprecated
    public void setT(double t) {
        t = t % Math.PI;
        this.d = new Direction(
                Math.cos(t),
                Math.sin(t));
    }

    @Deprecated
    public double getL() {
        return l;
    }

    @Deprecated
    public void setL(double l) {
        this.l = l;
    }

    public double dist(Vertex v) {
        return Math.abs(l + v.dir().prod(d)) / d.norm();
    }

    @Deprecated
    public Direction getD() {
        return d;
    }

    @Deprecated
    public void setD(Direction d) {
        this.d = d;
    }

}
