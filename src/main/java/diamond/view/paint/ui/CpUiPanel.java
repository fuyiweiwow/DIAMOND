/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.view.paint.ui;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import diamond.controller.paint.PaintContext;
import diamond.model.geom.element.LineType;
import diamond.view.resource.ResourceHolder;
import diamond.view.resource.string.StringKey.LABEL;

/**
 * @author long_
 *
 */
public class CpUiPanel extends JPanel {
    ButtonGroup paintActionButtons = new ButtonGroup();
    ButtonGroup lineTypeButtons = new ButtonGroup();

    private JPanel lineTypePanel = new JPanel();
    private JPanel inputLinePanel = new JPanel();
    private JPanel modifyLineTypePanel = new JPanel();

    public CpUiPanel(PaintContext context) {
        setLayout(new GridLayout(2, 1));
        addLineTypePanel(context);
        addInputLinePanel(context);
        addEditLineTypePanel(context);
    }

    private void setBorder(JPanel panel, String title) {
        TitledBorder border = new TitledBorder(
                new LineBorder(Color.GRAY, 2),
                title,
                TitledBorder.LEFT,
                TitledBorder.TOP);
        panel.setBorder(border);
    }

    private void addLineTypePanel(PaintContext context) {
        lineTypePanel.setLayout(new GridLayout(3, 2));
        addLineTypeButton(LABEL.MOUNTAIN, LineType.MOUNTAIN, context);
        addLineTypeButton(LABEL.AUX_MOUNTAIN, LineType.AUX_MOUNTAIN, context);
        addLineTypeButton(LABEL.VALLEY, LineType.VALLEY, context);
        addLineTypeButton(LABEL.AUX_VALLEY, LineType.AUX_VALLEY, context);
        addLineTypeButton(LABEL.AUX, LineType.AUX, context);
        add(lineTypePanel);
        setBorder(
                lineTypePanel,
                ResourceHolder.getLabelString(LABEL.INPUT_LINE));
    }

    private void addInputLinePanel(PaintContext context) {
        inputLinePanel.setLayout(new GridLayout(2, 3));
        addPaintActionButton(inputLinePanel, LABEL.AXIOM1, context);
        addPaintActionButton(inputLinePanel, LABEL.AXIOM2, context);
        addPaintActionButton(inputLinePanel, LABEL.AXIOM3, context);
        addPaintActionButton(inputLinePanel, LABEL.AXIOM4, context);
        addPaintActionButton(inputLinePanel, LABEL.SYMMETRIC, context);
        lineTypePanel.add(inputLinePanel);
        setBorder(
                inputLinePanel,
                ResourceHolder.getLabelString(LABEL.LINE_PATTERN));
    }

    private void addEditLineTypePanel(PaintContext context) {
        modifyLineTypePanel.setLayout(new GridLayout(2, 2));
        addPaintActionButton(modifyLineTypePanel, LABEL.FLIP_LINE_TYPE,
                context);
        addPaintActionButton(modifyLineTypePanel, LABEL.SETTLE_UNSETTLE,
                context);
        addPaintActionButton(modifyLineTypePanel, LABEL.FOLD_UNFOLD, context);
        addPaintActionButton(modifyLineTypePanel, LABEL.DELETE_LINE, context);
        add(modifyLineTypePanel);
        setBorder(
                modifyLineTypePanel,
                ResourceHolder.getLabelString(LABEL.MODIFY_LINE_TYPE));
    }

    private class LinetypeButtonAction implements ActionListener {
        PaintContext context;
        LineType type;

        public LinetypeButtonAction(LineType type, PaintContext context) {
            this.context = context;
            this.type = type;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (((JRadioButton) e.getSource()).isSelected()) {
                context.inputLineType = this.type;
            }
        }
    }

    private void addLineTypeButton(LABEL label, LineType type,
            PaintContext context) {
        JRadioButton button = new JRadioButton(
                ResourceHolder.getLabelString(label));
        button.addActionListener(new LinetypeButtonAction(type, context));
        lineTypeButtons.add(button);
        lineTypePanel.add(button);
        if (label == LABEL.MOUNTAIN) {
            button.setSelected(true);
        }
    }

    private void addPaintActionButton(JPanel parentPanel, LABEL label,
            PaintContext context) {
        JRadioButton button = new PaintActionButton(label, context);
        parentPanel.add(button);
        paintActionButtons.add(button);
        if (label == LABEL.AXIOM1) {
            button.setSelected(true);
        }
    }
}