/**
 * DIAMOND - Origami Editor
 * Copyright (C) 2018 Kei Morisue
 */
package diamond.view.paint;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;

import diamond.resource.ResourceHolder;
import diamond.resource.string.StringID;
import diamond.viewsetting.paint.MainFrameSettingDB;

/**
 * @author long_
 *
 */
public class HintLabel extends JLabel implements Observer {

    public HintLabel() {
        super(ResourceHolder.getHintString(StringID.ON_V_ID));
        MainFrameSettingDB.getInstance().addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o.toString() == MainFrameSettingDB
                .getInstance().getName()) {
            setText("    " + MainFrameSettingDB
                    .getInstance().getHint());
            repaint();
        }
    }
}
