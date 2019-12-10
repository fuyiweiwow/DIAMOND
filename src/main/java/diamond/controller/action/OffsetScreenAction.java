/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.controller.action;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;

import diamond.controller.Context;
import diamond.controller.MouseUtility;
import diamond.view.ui.screen.OffsetScreen;

/**
 * @author Kei Morisue
 *
 */
public class OffsetScreenAction extends AbstractScreenAction {
    private Context context;

    public OffsetScreenAction(Context context, OffsetScreen screen) {
        super(screen);
        this.context = context;
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        Point point = e.getPoint();
        context.setMousePoint(new Point2D.Double(point.x, point.x));
        context.getPaintAction().onMove(context);
        e.getComponent().repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (MouseUtility.isLeftClick(e)) {
            context.getPaintAction().onLeftClick(context);
        }
        if (MouseUtility.isRightClick(e)) {
            context.getPaintAction().onRightClick(context);
        }
        e.getComponent().repaint();
        return;
    }
}
