/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.view.ui.menu;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import diamond.controller.Context;
import diamond.controller.file.Export;
import diamond.controller.file.ExporterXml;
import diamond.controller.file.Load;
import diamond.controller.file.LoaderXML;
import diamond.view.resource.string.Labels;

/**
 * @author Kei Morisue
 *
 */
public class MainFile extends JMenu {
    public MainFile(Context context) {
        super(Labels.get("main_menu_file"));
        add(buildNew(context));
        add(buildOpen(context));
        add(buildSave(context));
    }

    private JMenuItem buildNew(Context context) {
        JMenuItem item = new JMenuItem(Labels.get("main_menu_file_new"));
        item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,
                ActionEvent.CTRL_MASK));
        return item;
    }

    private JMenuItem buildOpen(Context context) {
        JMenuItem item = new JMenuItem(Labels.get("main_menu_file_open"));
        item.addActionListener(new Load(context, this, new LoaderXML()));
        item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,
                ActionEvent.CTRL_MASK));
        return item;
    }

    private JMenuItem buildSave(Context context) {
        JMenuItem item = new JMenuItem(Labels.get("main_menu_file_save"));
        item.addActionListener(new Export(context, this, new ExporterXml()));
        item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
                ActionEvent.CTRL_MASK));
        return item;
    }
}
