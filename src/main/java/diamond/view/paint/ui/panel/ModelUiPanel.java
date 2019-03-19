/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.view.paint.ui.panel;

import java.awt.GridLayout;

import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import diamond.controller.paint.PaintContext;
import diamond.view.resource.ResourceHolder;
import diamond.view.resource.string.StringKey.LABEL;

/**
 * @author long_
 *
 */
public class ModelUiPanel extends JPanel {
    private ButtonGroup paintActionButtons;

    public ModelUiPanel(PaintContext context, ButtonGroup paintActionButtons) {
        this.paintActionButtons = paintActionButtons;
        addModelEditPanel(context);
    }

    private void addModelEditPanel(PaintContext context) {
        setLayout(new GridLayout(2, 2));
        JRadioButton b1 = new JRadioButton("Select Origin Face");
        JRadioButton b4 = new JRadioButton("Modify Face Order");
        JRadioButton b2 = new JRadioButton("Modify Vertex Position");
        JRadioButton b3 = new JRadioButton("Modify Line Position");
        paintActionButtons.add(b1);
        paintActionButtons.add(b2);
        paintActionButtons.add(b3);
        paintActionButtons.add(b4);
        add(b1);//TODO implement Actions!
        add(b2);//TODO implement Actions!
        add(b3);//TODO implement Actions!
        add(b4);//TODO implement Actions!
        UiPanelUtil.setBorder(
                this,
                ResourceHolder.getLabelString(LABEL.MODEL_EDIT));
    }

}