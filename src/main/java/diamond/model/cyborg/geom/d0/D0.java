/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.model.cyborg.geom.d0;

/**
 * @author Kei Morisue
 *
 */
public abstract class D0 {
    protected double x = .0;
    protected double y = .0;

    protected D0() {
    }

    public D0(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    @Deprecated

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    @Deprecated

    public void setY(double y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "(" + String.valueOf(x) + ", " + String.valueOf(y) + ")";
    }

}
