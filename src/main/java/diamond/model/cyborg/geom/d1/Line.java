/**
 * DEFOX - Diagram Editor for Origami Creators
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.model.cyborg.geom.d1;

import java.awt.geom.Rectangle2D.Double;

import diamond.model.cyborg.geom.Cyborg;
import diamond.model.cyborg.geom.d0.Vertex;

/**
 * @author Kei Morisue
 *
 */
public final class Line implements Cyborg {
    private D1L p;
    private D1L q;

    public Line(double t, double l) {
        this.p = new D1L(t, l);
        this.q = new D1L(t, l);
    }

    @Override
    public double distP(Vertex v) {
        return p.dist(v);
    }

    @Override
    public double distQ(Vertex v) {
        return q.dist(v);
    }

    @Override
    public Double clipP() {
        return new Double();
    }

    @Override
    public Double clipQ() {
        return new Double();
    }

}
