/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.view.ui.screen.draw;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

/**
 * @author Kei Morisue
 *
 */
public class G2DUtil {
    public static double getScale(Graphics2D g2d) {
        AffineTransform transform = g2d.getTransform();
        return Math.hypot(transform.getScaleX(), transform.getShearY());
    }

}
