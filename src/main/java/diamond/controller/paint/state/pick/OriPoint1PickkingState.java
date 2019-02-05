/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.controller.paint.state.pick;

import java.util.Stack;

import diamond.controller.paint.state.OripointPickkingState;
import diamond.model.geom.element.OriLine;
import diamond.model.geom.element.OriPoint;
import diamond.model.palette.CreasePatternHolder;
import diamond.view.paint.PaintContext;

/**
 * @author long_
 *
 */
public class OriPoint1PickkingState extends OripointPickkingState {

    public OriPoint1PickkingState() {
        super();
        initialize();
    }

    @Override
    protected void initialize() {
        setNextClass(OriPoint0PickkingState.class);
        setPrevClass(OriPoint0PickkingState.class);

    }

    @Override
    protected void onResult(PaintContext context) {
        Stack<OriPoint> pickedPoint = context.getPickedPoints();
        if (pickedPoint.size() != 2) {
            throw new RuntimeException();
        }
        OriLine line = new OriLine(
                pickedPoint.get(0),
                pickedPoint.get(1),
                context.inputLineType);
        CreasePatternHolder.getCP().addLine(line);
        pickedPoint.clear();
    }

}
