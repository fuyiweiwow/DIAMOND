/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.controller.mouse;

import java.util.Observable;
import java.util.Observer;

import diamond.model.cyborg.geom.Cyborg;
import diamond.model.cyborg.geom.PointerCyborg;
import diamond.model.cyborg.geom.d0.Wex;
import diamond.model.cyborg.geom.d1.AbstractSegment;
import diamond.model.cyborg.geom.d2.Face;

/**
 * @author Kei Morisue
 *
 */
public class Pointer extends Observable implements Observer {
    private PointerCyborg<Face> face = new PointerCyborg<>(Face.class);
    private PointerCyborg<AbstractSegment> segment = new PointerCyborg<>(
            AbstractSegment.class);
    private PointerCyborg<Wex> vertex = new PointerCyborg<>(Wex.class);

    public Pointer() {
        face.addObserver(this);
        segment.addObserver(this);
        vertex.addObserver(this);
    }

    @SuppressWarnings("unchecked")
    public <T extends Cyborg> PointerCyborg<T> get(Class<T> type) {
        if (type == Wex.class) {
            return (PointerCyborg<T>) vertex;
        }
        if (type == AbstractSegment.class) {
            return (PointerCyborg<T>) segment;
        }
        if (type == Face.class) {
            return (PointerCyborg<T>) face;
        }
        return null;//TODO
    }

    public void initialize() {
        face.initialize();
        segment.initialize();
        vertex.initialize();
    }

    @Override
    public void update(Observable o, Object arg) {
        setChanged();
        notifyObservers();
    }
}
