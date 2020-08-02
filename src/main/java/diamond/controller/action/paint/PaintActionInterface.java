/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.controller.action.paint;

import java.awt.Graphics2D;

import diamond.controller.Context;

/**
 * @author Kei Morisue
 *
 */
public interface PaintActionInterface {
    public abstract void doAction(Context context);

    public abstract void undo(Context context);

    public abstract void destroy(Context context);

    public abstract void recover(Context context);

    public abstract PaintActionInterface onLeftClick(Context context);

    public abstract PaintActionInterface onRightClick(Context context);

    public abstract void onMove(Context context);

    public abstract void onPress(Context context);

    public abstract void onDrag(Context context);

    public abstract void onRelease(Context context);

    public abstract void onDraw(Graphics2D g2d, Context context);
}