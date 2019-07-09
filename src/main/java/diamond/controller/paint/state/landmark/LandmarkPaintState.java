/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.controller.paint.state.landmark;

import java.awt.geom.Point2D.Double;

import diamond.controller.paint.PaintContext;
import diamond.controller.paint.state.AbstractPaintState;
import diamond.model.geom.Constants;
import diamond.model.geom.element.cp.OriLine;
import diamond.model.geom.element.cp.OriPoint;
import diamond.model.geom.element.origami.OriVertex;

/**
 * @author long_
 *
 */
public class LandmarkPaintState extends AbstractPaintState {

    public LandmarkPaintState() {
    }

    @Override
    protected void undoAction(PaintContext context) {
        for (OriLine l : context.palette.getCP().getLines()) {
            l.p0.disableLandmark();
            l.p1.disableLandmark();
        }
        rebuild(context);
    }

    @Override
    protected void onResult(PaintContext context) {
    }

    @Override
    protected void rebuild(PaintContext context) {
    }

    @Override
    protected boolean onAction(PaintContext context, Double currentPoint) {
        OriPoint p = context.pointedOriPoint;
        if (p != null) {
            for (OriLine l : context.palette.getCP().getLines()) {
                OriPoint p0 = l.p0;
                if (p.distance(p0) < Constants.EPS) {
                    p0.flipLandmark();
                    OriVertex v = p0.getV();
                    v.flipLandmark();
                }
                OriPoint p1 = l.p1;
                if (p.distance(p1) < Constants.EPS) {
                    OriVertex v = p1.getV();
                    p1.flipLandmark();
                    v.flipLandmark();
                }
            }
            return true;
        }

        return false;
    }

    @Override
    protected void initialize() {
        setPrevClass(LandmarkPaintState.class);
        setNextClass(LandmarkPaintState.class);
    }

}
