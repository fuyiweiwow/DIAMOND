/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.model.cyborg.geom.d1;

import diamond.model.cyborg.diagram.step.Step;
import diamond.model.cyborg.geom.d0.Wex;
import diamond.model.cyborg.geom.d2.Face;

/**
 * @author Kei Morisue
 *
 */
public final class Edge extends AbstractSegment {
    private Face f0;
    private Face f1;

    @Deprecated
    public Edge() {
        super();
    }

    public Edge(Face f0, Face f1, Wex v0, Wex v1) {
        super(v0, v1);
        this.type = SegmentType.CUT;
        this.f0 = f0;
        this.f1 = f1;
    }

    @Deprecated
    @Override
    public void split(double t) {
    }

    public void split(double t, Step step) {
        Wex w1 = w1();
        Wex w0 = w0();
        Wex w = new Wex(p.scale(t), q.scale(t));
        step.remove(this);
        step.remove(this);
        step.link(f0, f1, w, w0);
        step.link(f0, f1, w, w1);
    }

    public void setType(boolean isM) {
        this.type = (isM) ? SegmentType.MOUNTAIN : SegmentType.VALLEY;
    }

    @Deprecated
    public void setType(SegmentType type) {
        this.type = type;
    }

    public Face getPair(Face face) {
        if (face == f1) {
            return f0;
        }
        if (face == f0) {
            return f1;
        }
        return null;
    }

    @Deprecated
    public Face getF0() {
        return f0;
    }

    @Deprecated
    public void setF0(Face f0) {
        this.f0 = f0;
    }

    @Deprecated
    public Face getF1() {
        return f1;
    }

    @Deprecated
    public void setF1(Face f1) {
        this.f1 = f1;
    }

}
