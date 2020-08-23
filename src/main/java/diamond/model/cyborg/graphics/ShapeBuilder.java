/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.model.cyborg.graphics;

import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.Line2D;
import java.util.LinkedList;

import diamond.model.cyborg.geom.d0.Vertex;
import diamond.model.cyborg.geom.d0.Wex;
import diamond.model.cyborg.geom.d2.Face;
import diamond.view.ui.screen.AbstractScreen;

/**
 * @author Kei Morisue
 *
 */
public class ShapeBuilder {
    public static Ellipse2D.Double build(Vertex v, double size) {
        double sizeHalf = size * 0.5;
        return new Ellipse2D.Double(
                v.getX() - sizeHalf,
                v.getY() - sizeHalf,
                size,
                size);
    }

    public static GeneralPath build(Face face, AbstractScreen screen) {
        GeneralPath outline = new GeneralPath();
        LinkedList<Wex> vertices = face.getWexes();
        Wex w0 = vertices.get(0);
        Vertex v0 = screen.v(w0);
        outline.moveTo(v0.getX(), v0.getY());
        for (Wex w : vertices) {
            Vertex v = screen.v(w);
            outline.lineTo(v.getX(), v.getY());
        }
        outline.closePath();
        return outline;
    }

    public static Line2D.Double build(Vertex v0, Vertex v1) {
        Line2D.Double line = new Line2D.Double(v0.getX(), v0.getY(), v1.getX(),
                v1.getY());
        return line;
    }

}
