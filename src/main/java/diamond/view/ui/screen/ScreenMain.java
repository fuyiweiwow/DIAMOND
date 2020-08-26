/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.view.ui.screen;

import java.awt.Color;
import java.awt.Graphics2D;
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
import diamond.model.cyborg.geom.d1.AbstractSegment;
import diamond.model.cyborg.geom.d1.Crease;
import diamond.model.cyborg.geom.d1.Edge;
import diamond.model.cyborg.geom.d1.SegmentType;
import diamond.model.cyborg.geom.d2.Face;
import diamond.model.cyborg.graphics.ShapeBuilder;
import diamond.model.cyborg.style.StyleFace;
import diamond.model.cyborg.style.StyleSegment;
import diamond.model.cyborg.style.StyleVertex;
import diamond.view.ui.screen.draw.G2DUtil;
import diamond.view.ui.screen.style.Skin;

/**
 * @author Kei Morisue
 *
 */
public final class ScreenMain extends AbstractScreen {

    public ScreenMain(Context context) {
        super(context);
        ScreenActionPaint screenAction = new ScreenActionPaint(context, this);
        addMouseListener(screenAction);
        addMouseMotionListener(screenAction);
        addMouseWheelListener(screenAction);
    }

    @Override
    protected Color getBGColor() {
        return Skin.BG_MAIN_SCREEN;
    }

    @Override
    protected void draw(Graphics2D g2d) {
        Step step = diagram().getStep();
        step.draw(g2d, this);
        context.getPaintAction().onDraw(g2d);
    }

    @Override
    public void update(Observable o, Object arg) {
        repaint();
    }

    @Override
    public void draw(Graphics2D g2d, Wex w) {
        // TODO 自動生成されたメソッド・スタブ

    }

    @Override
    public void draw(Graphics2D g2d, Crease crease) {
        Diagram diagram = diagram();
        StyleSegment styleSegment = diagram.getStyleSegment();
        SegmentType type = crease.getType();
        g2d.setColor(styleSegment.getColor(type));
        float scale = (float) G2DUtil.getScale(g2d);
        g2d.setStroke(styleSegment.strokeCrease(scale, type));

    }

    @Override
    public void draw(Graphics2D g2d, Edge edge) {
        g2d.setColor(StyleSegment.COLOR_CUT);
        Diagram diagram = screen.diagram();
        StyleSegment styleSegment = diagram.getStyleSegment();
        g2d.setStroke(styleSegment.strokeEdge((float) G2DUtil.getScale(g2d)));
        Diagram diagram = screen.diagram();
        StyleSegment styleSegment = diagram.getStyleSegment();
        g2d.setColor(styleSegment.getColor(type));
        g2d.setStroke(styleSegment.strokeCp((float) G2DUtil.getScale(g2d)));

    }

    @Override
    public void draw(Graphics2D g2d, Face face) {
        Diagram diagram = screen.diagram();
        StyleFace styleFace = diagram.getStyleFace();
        g2d.setColor(styleFace.getColor(false));
        GeneralPath polygon = ShapeBuilder.build(this, screen);
        g2d.fill(polygon);
        for (Crease crease : creases) {
            crease.setG2d(g2d, screen);
            crease.draw(g2d, screen);
            Vertex v0 = screen.v(crease.getW0());
            Vertex v1 = screen.v(crease.getW1());
            v0.setG2d(g2d, screen);
            v0.draw(g2d, screen);
            v1.draw(g2d, screen);
        }
        for (Wex v : vs) {
            v.setG2d(g2d, screen);
            v.draw(g2d, screen);
        }

    }

    @Override
    public void draw(Graphics2D g2d, Picker picker) {
        Diagram diagram = screen.diagram();
        g2d.setColor(Color.GREEN);
        StyleSegment styleSegment = diagram.getStyleSegment();
        g2d.setStroke(styleSegment
                .strokePointed((float) G2DUtil.getScale(g2d)));
        for (T t : picked) {
            t.draw(g2d, screen);
        }
    }

    @Override
    public void draw(Graphics2D g2d, Pointer pointer) {
        if (type == Face.class) {
            g2d.setColor(StyleFace.POINTED);
            return;
        }
        if (type == Wex.class) {
            g2d.setColor(StyleVertex.POINTED);
            return;
        }
        Diagram diagram = screen.diagram();
        if (type == AbstractSegment.class) {
            g2d.setStroke(diagram.getStyleSegment()
                    .strokePointed((float) G2DUtil.getScale(g2d)));
            g2d.setColor(StyleSegment.POINTED);
            return;
        }
        if (pointed == null) {
            return;
        }
        pointed.draw(g2d, screen);
    }

    @Override
    public void draw(Graphics2D g2d, Step step) {
        // TODO 自動生成されたメソッド・スタブ

    }

    @Override
    public void setMouseLocation(Vertex v) {
        // TODO 自動生成されたメソッド・スタブ

    }

}
