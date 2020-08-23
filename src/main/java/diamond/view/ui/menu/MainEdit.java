/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.view.ui.menu;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import diamond.controller.Context;
import diamond.view.resource.string.Labels;
import diamond.view.ui.frame.PreviewFrame;
import diamond.view.ui.frame.TreeFrame;

/**
 * @author Kei Morisue
 *
 */
public class MainEdit extends JMenu {
    private Context context;

    public MainEdit(Context context) {
        super(Labels.get("main_menu_edit"));
        this.context = context;
        add(buildTree(context));
        add(buildRun(context));
    }

    private JMenuItem buildRun(Context context2) {
        JMenuItem item = new JMenuItem(Labels.get("main_menu_edit_preview"));
        item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R,
                ActionEvent.CTRL_MASK));
        item.addActionListener(new Preview(this));
        return item;
    }

    private JMenuItem buildTree(Context context) {
        JMenuItem item = new JMenuItem(Labels.get("main_menu_edit_tree"));
        item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M,
                ActionEvent.CTRL_MASK));
        item.addActionListener(new Tree(this));
        return item;
    }

    private class Tree implements ActionListener {
        private Component parent;

        public Tree(Component parent) {
            this.parent = parent;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            new TreeFrame(context, parent);
        }
    }

    private class Preview implements ActionListener {
        private Component parent;

        public Preview(Component parent) {
            this.parent = parent;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            new PreviewFrame(context, parent);
        }
    }
}
