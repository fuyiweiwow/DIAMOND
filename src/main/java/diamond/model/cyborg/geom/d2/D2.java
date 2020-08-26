/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.model.cyborg.geom.d2;

import java.util.LinkedList;

import diamond.model.cyborg.geom.d0.Direction;
import diamond.model.cyborg.geom.d0.Vertex;
import diamond.model.math.Fuzzy;
import diamond.model.math.Util;

/**
 * @author Kei Morisue
 *
 */
public final class D2 {
    protected LinkedList<Vertex> vs = new LinkedList<>();

    protected D2(Vertex... vs) {
        for (Vertex v : vs) {
            this.vs.add(v);
        }
    }

    public Vertex c() {
        double x = .0;
        double y = .0;
        for (Vertex v : vs) {
            x += v.getX();
            y += v.getY();
        }
        int n = vs.size();
        return new Vertex(x / n, y / n);
    }

    public boolean isBoundary(Vertex v) {
        Vertex v0 = vs.get(0);
        int size = vs.size();
        for (int i = 1; i < size; ++i) {
            Vertex p = v0;
            Direction d0 = v.dir(p);
            Vertex v1 = vs.get(i % size);
            Direction d1 = v1.dir(p);
            if (Fuzzy.isSmall(d0.outer(d1)) && Util.in(d0.proj(d1), .0, 1.0)) {
                return true;
            }
            v0 = v1;
        }
        return false;
    }

    protected void add(Vertex v) {
        vs.add(v);
    }

    protected void remove(Vertex v) {
        vs.remove(v);
    }

    public void add(Vertex v, Vertex v0, Vertex v1) {
        int i0 = vs.indexOf(v0);
        int i1 = vs.indexOf(v1);
        if (i0 == -1 || i1 == -1) {
            return;
        }
        vs.add(Math.max(i0, i1), v);
    }

    public LinkedList<Vertex> getVs() {
        return vs;
    }

    @Deprecated
    public void setVs(LinkedList<Vertex> vs) {
        this.vs = vs;
    }

}
