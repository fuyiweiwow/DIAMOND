/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.model.cyborg.geom.d2;

import java.awt.geom.Rectangle2D.Double;

import diamond.model.cyborg.geom.d0.Vertex;
import diamond.model.cyborg.geom.d0.Wex;
import diamond.model.cyborg.geom.d1.Crease;

/**
 * @author Kei Morisue
 *
 */
public final class Face extends FaceBase {
    public Face(Wex... wexes) {
        super(wexes);
    }

    private Double clip(D2 d2) {
        double x0 = .0;
        double x1 = .0;
        double y0 = .0;
        double y1 = .0;
        for (Vertex v : d2.vs) {
            double x = v.getX();
            double y = v.getY();
            x0 = Math.min(x0, x);
            y0 = Math.min(y0, y);
            x1 = Math.max(x1, x);
            y1 = Math.max(y1, y);
        }
        double w = x1 - x0;
        double h = y1 - y0;
        double cx = (x1 + x0) * .5;
        double cy = (y1 + y0) * .5;
        return new Double(cx - 0.5 * w, cy - 0.5 * h, w, h);
    }

    public void add(Crease crease) {
        creases.add(crease);
        crease.setFace(this);
    }

    @Override
    public double distP(Vertex v) {
        return dist(v, p);
    }

    @Override
    public double distQ(Vertex v) {
        return dist(v, q);
    }

    private double dist(Vertex v, D2 d2) {
        return v.dist(d2.c());
    }

    @Override
    public Double clipP() {
        return clip(p);
    }

    @Override
    public Double clipQ() {
        return clip(q);
    }

}
