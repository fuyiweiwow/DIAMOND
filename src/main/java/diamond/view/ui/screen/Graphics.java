/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.view.ui.screen;

import java.awt.Graphics2D;

import diamond.controller.mouse.Picker;
import diamond.controller.mouse.Pointer;
import diamond.model.cyborg.diagram.step.Step;
import diamond.model.cyborg.geom.d0.Wex;
import diamond.model.cyborg.geom.d1.Crease;
import diamond.model.cyborg.geom.d1.Edge;
import diamond.model.cyborg.geom.d2.Face;

/**
 * @author Kei Morisue
 *
 */
public interface Graphics {
    abstract public void draw(Graphics2D g2d, Step step);

    abstract public void draw(Graphics2D g2d, Wex w);

    abstract public void draw(Graphics2D g2d, Crease crease);

    abstract public void draw(Graphics2D g2d, Edge edge);

    abstract public void draw(Graphics2D g2d, Face face);

    abstract public void draw(Graphics2D g2d, Picker picker);

    abstract public void draw(Graphics2D g2d, Pointer pointer);

}
