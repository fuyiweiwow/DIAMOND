/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.model.cyborg.geom.d2;

import java.util.LinkedList;

import diamond.model.cyborg.geom.d0.Direction;
import diamond.model.cyborg.geom.d0.Vertex;
import diamond.model.cyborg.geom.d0.Wex;
import diamond.model.math.Fuzzy;
import diamond.model.math.Util;

/**
 * @author Kei Morisue
 *
 */
public class D2 {
    protected LinkedList<Wex> wexes = new LinkedList<Wex>();

    protected D2() {
    }

    public boolean isBoundary(Wex w) {
        Wex v0 = wexes.get(0);
        int size = wexes.size();
        for (int i = 1; i < size; ++i) {
            Vertex p = v0.getP();
            Direction d0 = w.getP().dir(p);
            Wex v1 = wexes.get(i % size);
            Direction d1 = v1.getP().dir(p);
            if (Fuzzy.isSmall(d0.outer(d1)) && Util.in(d0.proj(d1), .0, 1.0)) {
                return true;
            }
            v0 = v1;
        }
        return false;
    }

    public LinkedList<Wex> getWexes() {
        return wexes;
    }

    protected void add(Wex v) {
        wexes.add(v);
    }

    public void add(Wex v, Wex v0, Wex v1) {
        int i0 = wexes.indexOf(v0);
        int i1 = wexes.indexOf(v1);
        if (i0 == -1 || i1 == -1) {
            return;
        }
        wexes.add(Math.max(i0, i1), v);
    }

    @Deprecated
    public void setWexes(LinkedList<Wex> wexes) {
        this.wexes = wexes;
    }

}
