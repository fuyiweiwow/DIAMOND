/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.controller.file;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;

import diamond.controller.Context;

/**
 * @author Kei Morisue
 *
 */
public class Export implements ActionListener {
    private Context context;
    private Component parentComponent;
    private Exporter exporter;
    private JFileChooser chooser = new JFileChooser();
    protected File latestFile;

    public Export(Context context, Component parent, Exporter exporter) {
        this.context = context;
        this.parentComponent = parent;
        this.exporter = exporter;
        exporter.set(chooser);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (latestFile != null) {
            exporter.export(context.getDiagram(), latestFile.getAbsolutePath());
            return;
        }
        if (JFileChooser.APPROVE_OPTION == chooser
                .showSaveDialog(parentComponent)) {
        } else {
            return;
        }
        latestFile = exporter.export(
                context.getDiagram(),
                chooser.getSelectedFile().getPath());
    }
}