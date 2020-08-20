/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.controller.file;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFileChooser;

import diamond.controller.Context;
import diamond.model.cyborg.diagram.Diagram;

/**
 * @author Kei Morisue
 *
 */
public class Load implements ActionListener {
    Context context;
    Component parentComponent;
    Loader loader;

    public Load(Context context, Component parent, Loader loader) {
        this.context = context;
        this.parentComponent = parent;
        this.loader = loader;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser chooser = new JFileChooser();
        if (JFileChooser.APPROVE_OPTION == chooser
                .showSaveDialog(parentComponent)) {
        }
        Diagram diagram = loader.load(chooser);
        if (diagram == null) {
            return;
        }
        context.setDiagram(diagram);
    }
}