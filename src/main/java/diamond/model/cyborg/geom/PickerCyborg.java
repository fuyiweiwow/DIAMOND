/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.model.cyborg.geom;

import java.util.Stack;

/**
 * @author Kei Morisue
 *
 */
public class PickerCyborg<T extends Cyborg> {
    private Stack<T> picked = new Stack<T>();

    public void initialize() {
        picked.clear();
    }

    public void add(T t) {
        if (picked.contains(t)) {
            picked.remove(t);
            return;
        }
        picked.add(t);
    }

    public void pop() {
        if (picked.isEmpty()) {
            return;
        }
        picked.pop();
    }

    public Stack<T> get() {
        return picked;
    }

}
