/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.view.ui.panel;

import java.awt.GridLayout;

import javax.swing.ButtonGroup;
import javax.swing.JPanel;

import diamond.controller.Context;
import diamond.model.cyborg.EdgeType;
import diamond.view.resource.string.Labels;
import diamond.view.ui.button.LineTypeButton;

/**
 * @author Kei Morisue
 *
 */
public class PaintColorPanel extends JPanel {
    private ButtonGroup buttonGroup = new ButtonGroup();

    public PaintColorPanel(Context context) {
        setLayout(new GridLayout(3, 1));
        LineTypeButton button = new LineTypeButton(EdgeType.UNSETTLED_VALLEY,
                context,
                buttonGroup);
        button.doClick();
        add(button);
        add(new LineTypeButton(EdgeType.UNSETTLED_MOUNTAIN, context,
                buttonGroup));
        add(new LineTypeButton(EdgeType.CREASE, context, buttonGroup));
        UiUtil.setBorder(this, Labels.get("input_color"));

    }
}