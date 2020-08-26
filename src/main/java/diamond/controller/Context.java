/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.controller;

import diamond.controller.action.paint.PaintAxiom1;
import diamond.model.cyborg.diagram.Diagram;
import diamond.model.cyborg.diagram.step.Step;
import diamond.model.cyborg.geom.Cyborg;
import diamond.model.cyborg.geom.PickerCyborg;
import diamond.model.cyborg.geom.PointerCyborg;
import diamond.model.cyborg.geom.d0.Vertex;
import diamond.model.cyborg.geom.m.AbstractMirror;

/**
 * @author Kei Morisue
 *
 */
public class Context extends ContextBase {

    public Context(Diagram diagram) {
        this.diagram = diagram;
        this.diagram.addObserver(this);
        this.pointer.addObserver(this);
        this.paintAction = new PaintAxiom1(this);
        this.paintAction.addObserver(this);
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

    public void setP(Vertex p, AbstractMirror mirror) {
        this.mouseLocation.setP(p);
        this.mouseLocation.setP(mirror.apply(p));
        setChanged();
        notifyObservers();
    }

    public void setQ(Vertex q, AbstractMirror mirror) {
        this.mouseLocation.setQ(mirror.apply(q));
        this.mouseLocation.setQ(q);
        setChanged();
        notifyObservers();
    }

    public <T extends Cyborg> PickerCyborg<T> getPicker(
            Class<T> type) {
        return picker.get(type);
    }

    public <T extends Cyborg> PointerCyborg<T> getPointer(
            Class<T> type) {
        return pointer.get(type);
    }
}
