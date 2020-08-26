/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.view.ui.screen;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;
import java.util.Observable;

import diamond.controller.Context;
import diamond.controller.action.ScreenActionPaint;
import diamond.controller.mouse.Picker;
import diamond.controller.mouse.Pointer;
import diamond.model.cyborg.diagram.Diagram;
import diamond.model.cyborg.diagram.step.Step;
import diamond.model.cyborg.geom.d0.Vertex;
import diamond.model.cyborg.geom.d0.Wex;
import diamond.model.cyborg.geom.d1.Crease;
import diamond.model.cyborg.geom.d1.Edge;
import diamond.model.cyborg.geom.d1.SegmentType;
import diamond.model.cyborg.geom.d2.Face;
import diamond.model.cyborg.graphics.ShapeBuilder;
import diamond.model.cyborg.style.StyleFace;
import diamond.model.cyborg.style.StyleSegment;
import diamond.model.cyborg.style.StyleStep;
import diamond.view.ui.screen.draw.G2DUtil;
import diamond.view.ui.screen.style.Skin;

/**
 * @author Kei Morisue
 *
 */
public final class ScreenStep extends AbstractScreen {

    public ScreenStep(Context context) {
        super(context);
        ScreenActionPaint screenAction = new ScreenActionPaint(context, this);
        addMouseListener(screenAction);
        addMouseMotionListener(screenAction);
        addMouseWheelListener(screenAction);
    }

    @Override
    protected Color getBGColor() {
        return Skin.BG_STEP_SCREEN;
    }

    @Override
    protected void draw(Graphics2D g2d) {
        Step step = diagram().getStep();
        step.draw(g2d, this);
    }

    @Override
    public void update(Observable o, Object arg) {
        repaint();
    }

    @Override
    public void draw(Graphics2D g2d, Step step) {
        g2d.setColor(StyleStep.COLOR);
        Diagram diagram = screen.diagram();
        int i = diagram.getSteps().indexOf(this);
        AffineTransform tmpTransform = g2d.getTransform();
        g2d.setTransform(new AffineTransform());
        g2d.setFont(StyleStep.FONT_STEP);
        g2d.drawString(String.valueOf(i + 1), 10,
                g2d.getFont().getSize());
        g2d.setTransform(tmpTransform);
        setG2d(g2d, screen);
        for (Face face : faces) {
            face.draw(g2d, screen);
        }
    }

    @Override
    public void draw(Graphics2D g2d, Wex w) {
        // TODO 自動生成されたメソッド・スタブ

    }

    @Override
    public void draw(Graphics2D g2d, Crease crease) {
        StyleSegment styleSegment = diagram().getStyleSegment();
        SegmentType type = crease.getType();
        g2d.setColor(styleSegment.getStepColor(type));
        float scale = (float) G2DUtil.getScale(g2d);
        g2d.setStroke(styleSegment.strokeCrease(scale, type));
    }

    @Override
    public void draw(Graphics2D g2d, Edge edge) {
        // TODO 自動生成されたメソッド・スタブ

    }

    @Override
    public void draw(Graphics2D g2d, Face face) {
        Diagram diagram = screen.diagram();
        StyleFace styleFace = diagram.getStyleFace();
        g2d.setColor(styleFace.getColor(isFlip()));
        setG2d(g2d, screen);
        GeneralPath polygon = ShapeBuilder.build(this, screen);
        g2d.fill(polygon);
        for (Crease c : creases) {
            c.setG2d(g2d, screen);
            c.draw(g2d, screen);
        }
        g2d.setColor(StyleSegment.COLOR_CUT);
        StyleSegment styleSegment = screen.diagram().getStyleSegment();
        g2d.setStroke(styleSegment.strokeEdge((float) G2DUtil.getScale(g2d)));
        g2d.draw(polygon);
    }

    @Override
    public void draw(Graphics2D g2d, Picker picker) {
    }

    @Override
    public void draw(Graphics2D g2d, Pointer pointer) {
    }

    @Override
    public void setMouseLocation(Vertex v) {
        // TODO 自動生成されたメソッド・スタブ

    }

}
