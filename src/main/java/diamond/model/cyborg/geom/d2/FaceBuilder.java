/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.model.cyborg.geom.d2;

import diamond.model.cyborg.geom.d0.Wex;

/**
 * @author Kei Morisue
 *
 */
public class FaceBuilder {
    public static Face square(double width) {
        return polygon(new Wex(width, width),
                new Wex(-width, width),
                new Wex(-width, -width),
                new Wex(width, -width));
    }

    public static Face polygon(Wex... vs) {
        Face face = new Face();
        for (Wex v : vs) {
            face.add(v);
        }
        return face;
    }
}
