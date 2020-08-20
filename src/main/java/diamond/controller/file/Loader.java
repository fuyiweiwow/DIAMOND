/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.controller.file;

import javax.swing.JFileChooser;

import diamond.model.cyborg.diagram.Diagram;

/**
 * @author Kei Morisue
 *
 */
public interface Loader {
    public Diagram load(JFileChooser chooser);

    public void set(JFileChooser chooser);
}
