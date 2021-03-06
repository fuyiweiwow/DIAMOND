/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.controller.action;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.geom.Point2D;

import diamond.view.ui.screen.AbstractScreen;

/**
 * @author Kei Morisue
 *
 */
public abstract class AbstractScreenAction
        implements MouseListener, MouseMotionListener, MouseWheelListener {
    protected AbstractScreen screen;
    protected Point2D latestClickedPoint;

    public AbstractScreenAction(AbstractScreen screen) {
        this.screen = screen;
    }

    protected void zoom(MouseWheelEvent e) {
        double zoom = zoomAmount(e);
        screen.getTransform().zoom(zoom);
        screen.repaint();
    }

    protected double zoomAmount(MouseWheelEvent e) {
        return Math.pow(1.5, -e.getWheelRotation());
    }

    protected void translate(MouseEvent e) {
        double scale = screen.getTransform().getZoom();
        Point2D p0 = latestClickedPoint;
        double x = (e.getX() - p0.getX()) / scale;
        double y = (e.getY() - p0.getY()) / scale;
        screen.getTransform().shift(x, y);
        latestClickedPoint = e.getPoint();
        screen.repaint();
    }

    protected void rotate(MouseWheelEvent e) {
        double theta = thetaAmount(e);
        screen.getTransform().rotate(theta);
        screen.repaint();
    }

    protected double thetaAmount(MouseWheelEvent e) {
        double moved = e.getWheelRotation();
        return Math.PI / 8 * ((moved) % 8);
    }

    @Override
    public final void mouseClicked(MouseEvent e) {
    }

    @Override
    public final void mouseEntered(MouseEvent e) {
    }

    public abstract void onAction(MouseEvent e);

    @Override
    public final void mousePressed(MouseEvent e) {
        latestClickedPoint = e.getPoint();
        onAction(e);
    }

}
