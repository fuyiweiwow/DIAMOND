/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.model.cyborg.geom.d0;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import diamond.model.cyborg.graphics.ShapeBuilder;
import diamond.model.cyborg.style.StyleVertex;
import diamond.model.math.Fuzzy;
import diamond.view.ui.screen.ScreenMain;
import diamond.view.ui.screen.ScreenStep;
import diamond.view.ui.screen.draw.G2DUtil;

/**
 * @author Kei Morisue
 *
 */
public class Vertex extends D0 {
    public Vertex() {
    }

    public Vertex(double x, double y) {
        super(x, y);
    }

    public Vertex scale(double scale, Vertex v0) {
        Direction d = dir(v0).scale(scale);
        return d.ver(v0);
    }

    public double angle(Vertex v0) {
        return dir(v0).angle();
    }

    public Vertex div(Vertex v0, double p) {
        return dir(v0).scale(p).ver(v0);
    }

    public Vertex c(Vertex v0) {
        return div(v0, .5);
    }

    public Direction dir() {
        return new Direction(x, y);
    }

    public Direction dir(Vertex v0) {
        return new Direction(x - v0.x, y - v0.y);
    }

    public double dist(Vertex v0) {
        return dir(v0).norm();
    }

    public void draw(Graphics2D g2d, ScreenMain screen) {
        double scale = G2DUtil.getScale(g2d);
        g2d.fill(ShapeBuilder.build(this, StyleVertex.SIZE / scale));
    }

    public void setG2d(Graphics2D g2d, ScreenMain Screen) {
        g2d.setColor(StyleVertex.DEFAULT);
    }

    public void draw(Graphics2D g2d, ScreenStep screen) {
    }

    public void setG2d(Graphics2D g2d, ScreenStep Screen) {
    }

    public Rectangle2D.Double clip() {
        double size = 5;//TODO
        return new Rectangle2D.Double(
                x - size,
                y - size,
                size * 2,
                size * 2);
    }

    @Override
    public boolean equals(Object obj) {
        Vertex v0 = (Vertex) obj;
        return Fuzzy.isSmall(dir(v0).norm());
    }
}
