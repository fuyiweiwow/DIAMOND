/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.controller.paint.state.axiom4;

import java.util.Stack;

import diamond.controller.paint.context.PaintContext;
import diamond.controller.paint.state.OriLinePickkingState;
import diamond.model.geom.element.cp.OriLine;
import diamond.model.geom.element.cp.OriPoint;
import diamond.model.geom.util.OriLineUtil;

/**
 * @author long_
 *
 */
public class OriLine0PickkingState extends OriLinePickkingState {

    @Override
    protected void initialize() {
        setPrevClass(OriPoint0PickkingState.class);
        setNextClass(OriPoint0PickkingState.class);
    }

    @Override
    protected void onResult(PaintContext context) {
        Stack<OriLine> pickedLines = context.getPickedLines();
        Stack<OriPoint> pickedPoints = context.getPickedPoints();
        if (pickedLines.size() != 1 || pickedPoints.size() != 1) {
            throw new RuntimeException();
        }
        OriLine line = OriLineUtil.getVerticalLine(
                pickedPoints.get(0), pickedLines.get(0),
                context.getInputLineType());

        context.palette.getCP().addLine(line);
        pickedPoints.clear();
        pickedLines.clear();
    }

}
