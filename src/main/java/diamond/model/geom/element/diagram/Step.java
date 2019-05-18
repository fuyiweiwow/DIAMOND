/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.model.geom.element.diagram;

import java.awt.Graphics2D;

import javax.swing.border.BevelBorder;

import diamond.model.geom.element.origami.OriModel;
import diamond.view.screen.draw.OriModelDrawer;
import diamond.view.screen.draw.StringDrawer;
import diamond.view.screen.draw.style.FontStyle;
import diamond.view.screen.draw.style.PageStyle;

/**
 * @author long_
 *
 */
public class Step extends AbstractStep {
    private int stepNo;
    public Step(Diagram diagram, int stepNo) {
        super(diagram);
        this.stepNo = stepNo;
        setBorder(new BevelBorder(BevelBorder.LOWERED));
        setBackground(PageStyle.bg);
    }

    @Override
    protected void draw(Graphics2D g2d) {
        OriModel model = diagram.getCp().getOriModel();
        OriModelDrawer.drawModel(g2d, model);
        StringDrawer.drawStepNo(
                g2d,
                stepNo + 1,
                FontStyle.DIAGRAM_STEP_NO, x, y);
        g2d.dispose();
    }
}
