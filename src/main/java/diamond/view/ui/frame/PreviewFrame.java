/**
 * DEFOX - Diagram Editor for Origami Creators
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.view.ui.frame;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JFrame;

import diamond.controller.Context;
import diamond.view.resource.string.Labels;

/**
 * @author Kei Morisue
 *
 */
public class PreviewFrame extends JFrame {
    public PreviewFrame(Context context, Component parent) {
        setTitle(Labels.get("frame_preview"));
        setLocationRelativeTo(parent);
        setSize(new Dimension(800, 800));
        setVisible(true);
    }
}
