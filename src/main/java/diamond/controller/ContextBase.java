/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.controller;

import java.util.Observable;
import java.util.Observer;

import diamond.controller.action.paint.AbstractPaintAction;
import diamond.controller.action.paint.PaintLazy;
import diamond.controller.mouse.Picker;
import diamond.controller.mouse.Pointer;
import diamond.model.cyborg.diagram.Diagram;
import diamond.model.cyborg.diagram.step.Step;
import diamond.model.cyborg.geom.d0.Vertex;
import diamond.model.cyborg.geom.d0.Wex;
import diamond.model.cyborg.geom.d1.SegmentType;

/**
 * @author Kei Morisue
 *
 */
public class ContextBase extends Observable implements Observer {
    protected Diagram diagram;
    protected AbstractPaintAction paintAction = new PaintLazy();
    protected Wex mouseLocation = new Wex(new Vertex(), new Vertex());
    protected SegmentType type = SegmentType.CREASE_MOUNTAIN;
    protected Picker picker = new Picker();
    protected Pointer pointer = new Pointer();

    protected ContextBase() {
    }

    public Diagram getDiagram() {
        return diagram;
    }

    public void setDiagram(Diagram diagram) {
        this.diagram = diagram;
        this.diagram.addObserver(this);
        for (Step step : diagram.getSteps()) {
            step.update();
        }
        initialize();
    }

    public void initialize() {
        picker.initialize();
        pointer.initialize();
        setChanged();
        notifyObservers();
    }

    @Override
    public void update(Observable o, Object arg) {
        setChanged();
        notifyObservers();
    }

    public SegmentType getType() {
        return type;
    }

    public void setType(SegmentType type) {
        this.type = type;
    }

    public AbstractPaintAction getPaintAction() {
        return paintAction;
    }

    public void setPaintAction(AbstractPaintAction paintAction) {
        paintAction.addObserver(this);
        this.paintAction = paintAction;
    }

    public Wex getMouseLocation() {
        return mouseLocation;
    }

    public Pointer getPointer() {
        return pointer;
    }

    public Picker getPicker() {
        return picker;
    }

}
