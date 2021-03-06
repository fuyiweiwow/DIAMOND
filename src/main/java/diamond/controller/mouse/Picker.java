/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.controller.mouse;

import diamond.model.cyborg.geom.Cyborg;
import diamond.model.cyborg.geom.PickerCyborg;
import diamond.model.cyborg.geom.d0.Wex;
import diamond.model.cyborg.geom.d1.AbstractSegment;
import diamond.model.cyborg.geom.d2.Face;

/**
 * @author Kei Morisue
 *
 */
public class Picker {
    private PickerCyborg<Face> faces = new PickerCyborg<>();
    private PickerCyborg<AbstractSegment> segments = new PickerCyborg<>();
    private PickerCyborg<Wex> vs = new PickerCyborg<>();

    public void initialize() {
        faces.initialize();
        segments.initialize();
        vs.initialize();
    }

    @SuppressWarnings("unchecked")
    public <T extends Cyborg> PickerCyborg<T> get(Class<T> type) {
        if (type.equals(Wex.class)) {
            return (PickerCyborg<T>) vs;
        }
        if (type.equals(AbstractSegment.class)) {
            return (PickerCyborg<T>) segments;
        }
        if (type.equals(Face.class)) {
            return (PickerCyborg<T>) faces;
        }
        return null;
    }
}
