/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.controller.paint.state.arrow;

import java.awt.geom.Point2D.Double;

import diamond.controller.paint.PaintContext;
import diamond.controller.paint.state.AbstractPaintState;
import diamond.model.geom.element.cp.OriLine;
import diamond.model.geom.element.diagram.arrow.AbstractArrow;

/**
 * @author long_
 *
 */
public abstract class ArrowAddingState extends AbstractPaintState {

    @Override
    protected void undoAction(PaintContext context) {
        OriLine pointedOriLine = context.pointedOriLine;
        if (pointedOriLine != null) {
            AbstractArrow arrow = pointedOriLine.getArrow();
            if (arrow != null) {
                pointedOriLine.setArrow(null);
            }
        }
        rebuild(context);
    }

    @Override
    protected void onResult(PaintContext context) {

    }

    @Override
    protected void rebuild(PaintContext context) {
        context.palette.getCP().rebuildModel();

    }

    protected abstract AbstractArrow buildArrow();

    @Override
    protected boolean onAction(PaintContext context, Double currentPoint) {
        OriLine pointedOriLine = context.pointedOriLine;
        if (pointedOriLine != null) {
            AbstractArrow arrow = pointedOriLine.getArrow();
            if (arrow != null) {
                arrow.flip();
            } else {
                pointedOriLine.setArrow(buildArrow());
            }
            return true;
        }

        return false;
    }

}