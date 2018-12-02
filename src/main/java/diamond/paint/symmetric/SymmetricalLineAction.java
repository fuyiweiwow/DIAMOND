package diamond.paint.symmetric;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import javax.vecmath.Vector2d;

import diamond.paint.core.GraphicMouseAction;
import diamond.paint.core.PaintContext;

public class SymmetricalLineAction extends GraphicMouseAction {


	public SymmetricalLineAction(){
		setActionState(new SelectingVertexForSymmetric());
	}



//	private OriLine closeLine = null;
//
//	@Override
//	public Vector2d onMove(MouseContext context, AffineTransform affine,
//			MouseEvent event) {
//		Vector2d result = super.onMove(context, affine, event);
//
//		if(context.getVertexCount() == 3){
//			if(closeLine != null){
//				closeLine.selected = false;
//			}
//			
//			closeLine = context.pickCandidateL;
//	
//			if(closeLine != null){
//				closeLine.selected = true;
//			}
//		}		
//		return result;
//	}



	@Override
	public Vector2d onMove(
			PaintContext context, AffineTransform affine, boolean differentAction) {

		if (context.getVertexCount() < 2) {
			return super.onMove(context, affine, differentAction);
		}
		
		// enable auto-walk selection only
		return super.onMove(context, affine, false);
	}

	@Override
	public void onDrag(PaintContext context, AffineTransform affine, boolean differentAction) {

	}

	@Override
	public void onRelease(PaintContext context, AffineTransform affine,
			boolean differentAction) {

	}


	@Override
	public void onDraw(Graphics2D g2d, PaintContext context) {

		super.onDraw(g2d, context);


		drawPickCandidateVertex(g2d, context);
	}



	@Override
	public void onPress(PaintContext context, AffineTransform affine,
			boolean differentAction) {
		
	}

}
