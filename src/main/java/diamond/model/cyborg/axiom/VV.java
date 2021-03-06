/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.model.cyborg.axiom;

import diamond.model.cyborg.geom.d0.Wex;
import diamond.model.cyborg.geom.d1.Crease;

/**
 * @author Kei Morisue
 *
 */
public class VV extends AxiomGenerator<Wex, Wex> {

    @Override
    Crease allign(Wex t1, Wex t2) {
        if (t1 == t2) {
            return null;
        }
        // Perpendicular bisector
        return null;
    }

}
