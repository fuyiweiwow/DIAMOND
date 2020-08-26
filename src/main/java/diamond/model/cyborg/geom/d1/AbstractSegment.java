/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.model.cyborg.geom.d1;

import java.awt.geom.Rectangle2D.Double;

import diamond.model.cyborg.geom.Cyborg;
import diamond.model.cyborg.geom.d0.Vertex;
import diamond.model.cyborg.geom.d0.Wex;

/**
 * @author Kei Morisue
 *
 */
public abstract class AbstractSegment implements Cyborg {
    protected D1 p;
    protected D1 q;
    protected SegmentType type = SegmentType.CREASE;

    @Deprecated
    protected AbstractSegment() {
    }

    public AbstractSegment(Wex w0, Wex w1) {
        this.p = new D1(w0.getP(), w1.getP());
        this.q = new D1(w0.getQ(), w1.getQ());
    }

    public AbstractSegment(AbstractSegment segment) {
        this.p = segment.p;
        this.q = segment.q;
        this.type = segment.type;
    }

    public Wex w0() {
        return new Wex(p.getV0(), q.getV0());
    }

    public Wex w1() {
        return new Wex(p.getV1(), q.getV1());
    }

    @Override
    public double distP(Vertex v) {
        return dist(v, p);
    }

    @Override
    public double distQ(Vertex v) {
        return dist(v, q);
    }

    private double dist(Vertex v, D1 s) {
        return v.dist(s.c());
    }

    @Override
    public Double clipP() {
        return clip(p);
    }

    @Override
    public Double clipQ() {
        return clip(q);
    }

    private Double clip(D1 s) {
        Vertex c = s.c();
        double w = Math.abs(s.v0.getX() - s.v1.getX());
        double h = Math.abs(s.v0.getY() - s.v1.getY());
        return new Double(c.getX() - w * 0.5, c.getY() - h * 0.5, w, h);
    }

    abstract public void split(double t);

    abstract public void setType(SegmentType type);

    public D1 getP() {
        return p;
    }

    @Deprecated
    public void setP(D1 p) {
        this.p = p;
    }

    public D1 getQ() {
        return q;
    }

    public void setQ(D1 q) {
        this.q = q;
    }

    public SegmentType getType() {
        return type;
    }

}
