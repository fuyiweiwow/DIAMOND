/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.model.cyborg.geom.d1;

import diamond.model.cyborg.geom.d0.Wex;
import diamond.model.cyborg.geom.d2.D2;
import diamond.model.cyborg.geom.d2.Face;

/**
 * @author Kei Morisue
 *
 */
public final class Crease extends AbstractSegment {
    private Face face;

    @Deprecated
    public Crease() {
    }

    public Crease(AbstractSegment segment) {
        super(segment);
        this.setType(segment.type);
    }

    public Crease(Wex v0, Wex v1, SegmentType type) {
        super(v0, v1);
        this.setType(type);
    }

    public Crease scale(double clip) {
        Wex c = new Wex(p.c(), q.c());
        Wex w0 = new Wex(p.v0, q.v0);
        Wex w1 = new Wex(p.v1, q.v1);
        D2 d2 = face.getP();
        boolean isBoundary0 = d2.isBoundary(p.v0);
        boolean isBoundary1 = d2.isBoundary(p.v1);
        return new Crease(
                isBoundary0 ? w0.scale(clip, c) : w0,
                isBoundary1 ? w1.scale(clip, c) : w1,
                type);
    }

    @Override
    public void split(double t) {
        Wex w = new Wex(p.scale(t), q.scale(t));
        face.remove(this);
        face.add(new Crease(w0(), w, type));
        face.add(new Crease(w, w1(), type));
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
