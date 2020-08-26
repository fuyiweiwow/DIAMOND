/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.model.cyborg.geom;

import java.awt.geom.Rectangle2D;

import diamond.model.cyborg.geom.d0.Vertex;

/**
 * @author Kei Morisue
 *
 */
public interface Cyborg {

    public double distP(Vertex v);

    public double distQ(Vertex v);

    public Rectangle2D.Double clipP();

    public Rectangle2D.Double clipQ();

}
