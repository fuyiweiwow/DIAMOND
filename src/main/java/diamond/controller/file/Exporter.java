/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.controller.file;

import java.io.File;

import javax.swing.JFileChooser;

import diamond.model.cyborg.diagram.Diagram;

/**
 * @author Kei Morisue
 *
 */
public interface Exporter {
    public File export(Diagram diagram, String path);

    public void set(JFileChooser chooser);
}
