package diamond.paint.byvalue;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import diamond.paint.GraphicMouseActionInterface;
import diamond.paint.core.GraphicMouseAction;
import diamond.paint.core.PaintContext;

public class LengthMeasuringAction extends GraphicMouseAction {

	public LengthMeasuringAction(){
		setActionState(new SelectingVertexForLength());
	}
	
	
	
	@Override
	public GraphicMouseActionInterface onLeftClick(PaintContext context,
			AffineTransform affine, boolean differentAction) {

		GraphicMouseActionInterface action;
		action = super.onLeftClick(context, affine, differentAction);
		
		if(context.isMissionCompleted()){
			action = new LineByValueAction();
		}
		
		return action;
	}



	@Override
	public void onDrag(PaintContext context, AffineTransform affine,
			boolean differentAction) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onRelease(PaintContext context, AffineTransform affine,
			boolean differentAction) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onDraw(Graphics2D g2d, PaintContext context) {

		super.onDraw(g2d, context);
		
		drawPickCandidateVertex(g2d, context);


	}



	@Override
	public void onPress(PaintContext context, AffineTransform affine,
			boolean differentAction) {
		// TODO Auto-generated method stub
		
	}
}
