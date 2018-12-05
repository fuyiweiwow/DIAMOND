/**
 * DIAMOND - Origami Editor
 * Copyright (C) 2018 Kei Morisue
 */
package diamond.view.main.menubar.help;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import diamond.resource.ResourceHolder;
import diamond.resource.ResourceKey;
import diamond.resource.StringID;
import diamond.resource.StringID.Main;

/**
 * @author long_
 *
 */
public class About extends JMenuItem implements ActionListener {
    private static About instance = null;

    public static About getInstance() {
        if (instance == null) {
            instance = new About();
        }
        return instance;
    }

    private static final String content = "DIAMOND: (c) 2018 Kei Morisue\n"
            + "Being Developed based on:\n"
            + "ORIPA S: (c) 2012 OUCHI Koji\n" +
            "http://github.com/Ooouch1\n" +
            "ORIPA: (c) 2005-2009 Jun Mitani\nhttp://mitani.cs.tsukuba.ac.jp/\n\n"
            + "This program comes with ABSOLUTELY NO WARRANTY.";

    private About() {
        super(ResourceHolder.getString(ResourceKey.LABEL,
                Main.ABOUT_ID));
        addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(this, content,
                ResourceHolder.getString(ResourceKey.LABEL,
                        StringID.Main.TITLE_ID),
                JOptionPane.INFORMATION_MESSAGE);

    }
}
