package diamond.paint.vertical;

import diamond.paint.core.PaintContext;
import diamond.paint.core.PickingVertex;

public class SelectingVertexForVertical extends PickingVertex{
	
	public SelectingVertexForVertical(){
		super();
	}
	
	@Override
	public void undoAction(PaintContext context) {
		context.clear(false);
	}

	@Override
	public void onResult(PaintContext context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void initialize() {
		setPreviousClass(this.getClass());
		setNextClass(SelectingLineForVertical.class);
		
//		System.out.println("SelectingFirstVertex.initialize() is called");
	}
	
}
