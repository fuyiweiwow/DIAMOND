/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.model.cyborg.geom.d0;

import java.awt.geom.Rectangle2D.Double;

import diamond.model.cyborg.geom.Cyborg;
import diamond.model.cyborg.geom.m.AbstractMirror;
import diamond.model.math.Fuzzy;

/**
 * @author Kei Morisue
 *
 */
public class Wex implements Cyborg {
    private Vertex p;
    private Vertex q;

    public Wex(double x, double y) {
        p = new Vertex(x, y);
        q = new Vertex(x, y);
    }

    public Wex(Vertex p, Vertex q) {
        this.p = p;
        this.q = q;
    }

    public Wex apply(AbstractMirror mirror) {
        if (Fuzzy.isSmall(p.dist(q))) {
            q = mirror.apply(p);
        }
        return this;
    }

    public Wex scale(double scale, Wex w0) {
        return new Wex(
                p.scale(scale, w0.p),
                q.scale(scale, w0.q));
    }

    public Vertex getP() {
        return p;
    }

    public void setP(Vertex p) {
        this.p = p;
    }

    public Vertex getQ() {
        return q;
    }

    public void setQ(Vertex q) {
        this.q = q;
    }

    @Override
    public double distP(Vertex v) {
        return v.dist(p);
    }

    @Override
    public double distQ(Vertex v) {
        return v.dist(q);
    }

    @Override
    public Double clipP() {
        return new Double(p.x, p.y, .0, .0);
    }

    @Override
    public Double clipQ() {
        return new Double(q.x, q.y, .0, .0);
    }

    @Override
    public boolean equals(Object obj) {
        Wex w0 = (Wex) obj;
        return w0.p.equals(p);
    }
}
