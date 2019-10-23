/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.controller.paint.action;

import java.awt.Graphics2D;

import diamond.controller.paint.context.Context;
import diamond.controller.paint.context.PaintScreenContext;
import diamond.controller.paint.state.scalearrow.Arrow0PickkingState;
import diamond.controller.paint.state.scalearrow.ArrowScalingState;
import diamond.view.screen.draw.RadarDrawer;

/**
 * @author long_
 *
 */
public class ArrowScalingAction extends AbstractPaintAction {

    public ArrowScalingAction() {
        setActionState(new Arrow0PickkingState());
    }

    @Override
    public void onDraw(Graphics2D g2d, Context context) {
        PaintScreenContext paintScreenContext = context.getPaintScreenContext();
        if (getActionState().getClass() == ArrowScalingState.class) {
            RadarDrawer.draw(g2d, paintScreenContext);
        } else {
            paintScreenContext.getPointedElements().draw(g2d);
        }
    }

}
