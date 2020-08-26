/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.model.cyborg.axiom;

import diamond.model.cyborg.geom.d1.AbstractSegment;
import diamond.model.cyborg.geom.d1.Crease;

/**
 * @author Kei Morisue
 *
 */
public abstract class AxiomGenerator<T1, T2> {
    protected AbstractSegment segment = null;

    abstract Crease allign(T1 t1, T2 t2);

}
