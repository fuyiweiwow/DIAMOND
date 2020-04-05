/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.view.ui.screen;

import java.awt.Color;
import java.awt.Graphics2D;

import diamond.controller.Context;
import diamond.controller.action.ScreenActionPaint;
import diamond.view.ui.screen.draw.Drawer;
import diamond.view.ui.screen.style.Skin;

/**
 * @author Kei Morisue
 *
 */
public class MainScreen extends AbstractScreen {
    private Context context;

    protected MainScreen() {
    }

    public MainScreen(Context context) {
        super();
        this.context = context;
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
        Drawer drawer = new Drawer(context.getStyleFace(),
                context.getStyleSegment());
        drawer.Draw(g2d, context.getCurrentStep());
    }

    public void reset() {
        transform.resize(getWidth(), getHeight());
        transform.zoom(1.0);
        transform.translate(.0, .0);
    }

}
