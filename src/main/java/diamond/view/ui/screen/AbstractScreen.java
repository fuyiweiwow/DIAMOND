/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.view.ui.screen;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.JPanel;

/**
 * @author Kei Morisue
 *
 */
public abstract class AbstractScreen extends JPanel
        implements ComponentListener {
    protected TransformScreen transform = new TransformScreen();

    public AbstractScreen() {
        addComponentListener(this);
    }

    public TransformScreen getTransform() {
        return transform;
    };

    public void setTransform(TransformScreen screenTransform) {
        this.transform = screenTransform;
    };

    protected abstract Color getBGColor();

    protected abstract void draw(Graphics2D g2d);

    protected void drawBackGround(Graphics2D g2d) {
        g2d.setColor(getBGColor());
        int width = getWidth();
        int height = getHeight();
        g2d.fillRect(0, 0, width, height);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        drawBackGround(g2d);
        transform.resize(getWidth(), getHeight());
        g2d.setTransform(transform.getTransform());
        draw(g2d);
    }

    @Override
    public void componentResized(ComponentEvent e) {
        repaint();
    }

    @Override
    public void componentMoved(ComponentEvent e) {
    }

    @Override
    public void componentShown(ComponentEvent e) {
        repaint();
    }

    @Override
    public void componentHidden(ComponentEvent e) {
    }
}
