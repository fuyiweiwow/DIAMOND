/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.controller.action.state;

import java.util.Stack;

import diamond.controller.Context;
import diamond.controller.mouse.PickerCyborg;
import diamond.model.cyborg.geom.d0.Vertex;
import diamond.model.cyborg.geom.d1.SegmentCrease;
import diamond.model.cyborg.step.Step;

/**
 * @author Kei Morisue
 *
 */
public class AddSegment extends AbstractPaintState {
    private Context context;
    private Vertex v0;
    private Vertex v1;

    public AddSegment(Context context) {
        this.context = context;
    }

    @Override
    protected void undo() {
    }

    @Override
    protected void executeAction() {
        Step step = context.getDiagram().getStep();
        step.getFaces().get(0).getCreases().add(new SegmentCrease(v0,
                v1, context.getType()));
        step.update();
        context.initialize();
    }

    @Override
    protected boolean tryAction() {
        PickerCyborg<Vertex> picker = context.getPicker(Vertex.class);
        Stack<Vertex> picked = picker.get();
        this.v1 = picked.get(1);
        this.v0 = picked.get(0);
        return v1 != v0;
    }

    @Override
    void initialize() {
    }

    @Override
    public AbstractState onMove() {
        return this;
    }

    @Override
    public AbstractState onRelease() {
        return doAction();
    }

}
