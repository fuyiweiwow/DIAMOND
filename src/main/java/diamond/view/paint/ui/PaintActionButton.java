/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.view.paint.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JRadioButton;

import diamond.controller.paint.PaintContext;
import diamond.controller.paint.action.LazyPaintAction;
import diamond.controller.paint.action.PaintActionInterface;
import diamond.controller.paint.action.axiom1.Axiom1Action;
import diamond.view.resource.ImageIconLoader;
import diamond.view.resource.string.StringKey.LABEL;

/**
 * @author long_
 *
 */
public class PaintActionButton extends JRadioButton implements ActionListener {
    private PaintContext paintContext;
    private PaintActionInterface paintAction;

    public PaintActionButton(LABEL l, PaintContext context) {
        this.paintContext = context;
        switch (l) {
        case AXIOM1:
            setIcons("axiom1");
            this.paintAction = new Axiom1Action();
            break;
        case AXIOM2:
            setIcons("axiom2");
            this.paintAction = new LazyPaintAction();
            break;
        case AXIOM3:
            setIcons("axiom3");
            this.paintAction = new LazyPaintAction();
            break;
        case AXIOM4:
            setIcons("axiom4");
            this.paintAction = new LazyPaintAction();
            break;
        default:
            break;
        }
        addActionListener(this);
    }

    private void setIcons(String iconBaseName) {
        ImageIconLoader imgLoader = new ImageIconLoader();
        setIcon(
                imgLoader
                        .loadAsIcon("icon/" + iconBaseName + ".gif"));
        setSelectedIcon(
                imgLoader
                        .loadAsIcon("icon/" + iconBaseName + "_p.gif"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (isSelected()) {
            paintContext.paintAction = paintAction;
        }
    }
}
