/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.model.cyborg.geom.d2;

import java.util.HashSet;

import diamond.model.cyborg.geom.Cyborg;
import diamond.model.cyborg.geom.d0.Wex;
import diamond.model.cyborg.geom.d1.Crease;
import diamond.model.cyborg.geom.m.AbstractMirror;

/**
 * @author Kei Morisue
 *
 */
public abstract class FaceBase implements Cyborg {
    protected D2 p = new D2();
    protected D2 q = new D2();
    protected HashSet<Crease> creases = new HashSet<Crease>();
    protected AbstractMirror mirror = null;

    public FaceBase(Wex... wexes) {
        for (Wex w : wexes) {
            p.add(w.getP());
            q.add(w.getQ());
        }
    }

    public void remove(Crease crease) {
        creases.remove(crease);
    }

    public HashSet<Crease> getCreases() {
        return creases;
    }

    @Deprecated
    public void setCreases(HashSet<Crease> creases) {
        this.creases = creases;
    }

    public boolean isFlip() {
        return mirror.isFlip();
    }

    public AbstractMirror getMirror() {
        return mirror;
    }

    public void setMirror(AbstractMirror mirror) {
        this.mirror = mirror;
    }

    public void add(Wex... vs) {

    }

    public D2 getP() {
        return p;
    }

    @Deprecated
    public void setP(D2 p) {
        this.p = p;
    }

    public D2 getQ() {
        return q;
    }

    @Deprecated
    public void setQ(D2 q) {
        this.q = q;
    }

}
