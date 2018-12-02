package diamond.paint.util;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import diamond.paint.core.PaintConfig;

public class LineTypeSetter implements ActionListener {

	protected int lineType;
	
	public LineTypeSetter(int type) {
		lineType = type;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		PaintConfig.inputLineType = lineType;

	}

}
