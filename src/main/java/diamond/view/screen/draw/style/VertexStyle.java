/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.view.screen.draw.style;

import java.awt.BasicStroke;
import java.awt.Color;

/**
 * @author long_
 *
 */
public class VertexStyle {
    public static final double SIZE = 5.0;
    public static final double SIZE_PICKED = 10.0;
    public static final double SIZE_POINTED = 10.0;

    public static final int SIZE_LANDMARK_BODY = 10;
    public static final int SIZE_LANDMARK_EDGE = 10;

    public static final Color COLOR_SELECTED = Color.green;
    public static final Color COLOR_LANDMARK_BODY = Color.black;
    public static final Color COLOR_LANDMARK_EDGE = Color.white;

    final public static BasicStroke STROKE_LANDMARK_BODY = new BasicStroke(
            2.0f,
            BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER);
    final public static BasicStroke STROKE_LANDMARK_EDGE = new BasicStroke(
            6.0f,
            BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER);

}
