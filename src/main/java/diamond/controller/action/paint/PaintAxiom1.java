/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.controller.action.paint;

import java.awt.Graphics2D;

import diamond.controller.Context;
import diamond.controller.action.state.AddSegment;
import diamond.controller.action.state.PickCyborg;
import diamond.model.cyborg.geom.d0.Wex;

/**
 * @author Kei Morisue
 *
 */
public class PaintAxiom1 extends AbstractPaintActionMouse {
    private Context context;

    public PaintAxiom1(Context context) {
        this.context = context;
        initialize(
                new PickCyborg<Wex>(context, Wex.class),
                new PickCyborg<Wex>(context, Wex.class),
                new AddSegment(context));
    }

    @Override
    public void onDraw(Graphics2D g2d) {
    }

    @Override
    protected void onLeftCtrl() {
    }

    @Override
    protected void onRightCtrl() {
    }
}
