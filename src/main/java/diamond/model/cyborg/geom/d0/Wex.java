/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.model.cyborg.geom.d0;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D.Double;

import diamond.model.cyborg.geom.Cyborg;
import diamond.model.cyborg.geom.m.AbstractMirror;
import diamond.model.math.Fuzzy;
import diamond.view.ui.screen.AbstractScreen;
import diamond.view.ui.screen.ScreenMain;
import diamond.view.ui.screen.ScreenStep;

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

    private Wex(Vertex p, Vertex q) {
        this.p = p;
        this.q = q;
    }

    public Wex apply(AbstractMirror mirror) {
        if (Fuzzy.isSmall(p.dist(q))) {
            q = mirror.apply(p);
        }
        return this;
    }

    public Wex scale(double scale, Vertex vp, Vertex vq) {
        return new Wex(
                p.scale(scale, vp),
                q.scale(scale, vq));
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

    //    public void setQ(Vertex q) {
    //        this.q = q;
    //    }

    @Override
    public void draw(Graphics2D g2d, ScreenMain screen) {
        p.draw(g2d, screen);
    }

    @Override
    public void setG2d(Graphics2D g2d, ScreenMain screen) {
        p.setG2d(g2d, screen);
    }

    @Override
    public void draw(Graphics2D g2d, ScreenStep screen) {
        q.draw(g2d, screen);
    }

    @Override
    public void setG2d(Graphics2D g2d, ScreenStep screen) {
        q.setG2d(g2d, screen);
    }

    @Override
    public double dist(Vertex v, AbstractScreen screen) {
        return screen.v(this).dist(v);
    }

    @Override
    public Double clip(AbstractScreen screen) {
        return screen.v(this).clip();
    }

}
