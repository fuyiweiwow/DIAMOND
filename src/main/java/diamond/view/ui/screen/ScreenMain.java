/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.view.ui.screen;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Observable;

import diamond.controller.Context;
import diamond.controller.action.ScreenActionPaint;
import diamond.model.cyborg.diagram.step.Step;
import diamond.model.cyborg.geom.Cyborg;
import diamond.model.cyborg.geom.PickerCyborg;
import diamond.model.cyborg.geom.PointerCyborg;
import diamond.model.cyborg.geom.d0.Wex;
import diamond.model.cyborg.geom.d0.Vertex;
import diamond.model.cyborg.geom.d1.SegmentBase;
import diamond.model.cyborg.geom.d2.Face;
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
    protected void drawPointed(Graphics2D g2d) {
        pointed(g2d, Wex.class);
        pointed(g2d, SegmentBase.class);
        pointed(g2d, Face.class);
    }

    @Override
    public Vertex v(Wex w) {
        return w.getP();
    }

    private <T extends Cyborg> void pointed(Graphics2D g2d, Class<T> type) {
        PointerCyborg<T> pointer = context
                .getPointer(type);
        pointer.setG2d(g2d, this);
        pointer.draw(g2d, this);
        PickerCyborg<T> picker = context
                .getPicker(type);
        picker.setG2d(g2d, this);
        picker.draw(g2d, this);
    }

}
