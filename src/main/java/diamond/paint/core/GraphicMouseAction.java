package diamond.paint.core;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import javax.vecmath.Vector2d;

import diamond.paint.EditMode;
import diamond.paint.GraphicMouseActionInterface;
import diamond.paint.ScreenUpdaterInterface;
import diamond.paint.geometry.GeometricOperation;
import diamond.paint.util.ElementSelector;
import diamond.value.OriLine;
import diamond.viewsetting.paint.ScreenUpdater;

public abstract class GraphicMouseAction implements GraphicMouseActionInterface {

	private EditMode editMode = EditMode.INPUT;
	private boolean needSelect = false;
	private ActionState state;

	private void drawPickedLines(Graphics2D g2d, PaintContext context){
		for(int i = 0; i < context.getLineCount(); i++){
			g2d.setColor(LineSetting.LINE_COLOR_PICKED);
			g2d.setStroke(LineSetting.STROKE_PICKED);

			OriLine line = context.getLine(i);

			drawLine(g2d, line);
		}

	}

	private void drawPickedVertices(Graphics2D g2d, PaintContext context){
		ElementSelector selector = new ElementSelector();

		for(int i = 0; i < context.getVertexCount(); i++){
			g2d.setColor(selector.selectColorByLineType(PaintConfig.inputLineType));

			Vector2d vertex = context.getVertex(i);
			drawVertex(g2d, context, vertex.x, vertex.y);
		}		
	}	

	/* (非 Javadoc)
	 * @see oripa.paint.core.GraphicMouseActionInterface#destroy(oripa.paint.core.PaintContext)
	 */
	@Override
	public void destroy(PaintContext context){
		context.clear(false);
	}

	/* (非 Javadoc)
	 * @see oripa.paint.core.GraphicMouseActionInterface#doAction(oripa.paint.core.PaintContext, java.awt.geom.Point2D.Double, boolean)
	 */
	@Override
	public void doAction(PaintContext context, Point2D.Double point, boolean differntAction){

		state = state.doAction(context, 
				point, differntAction);

		// TODO move this variable to parameter
		ScreenUpdaterInterface screenUpdater = ScreenUpdater.getInstance();
		screenUpdater.updateScreen();
	}
	
	/* (非 Javadoc)
	 * @see oripa.paint.core.GraphicMouseActionInterface#getEditMode()
	 */
	@Override
	public final EditMode getEditMode(){
		return editMode;
	}


	/* (非 Javadoc)
	 * @see oripa.paint.core.GraphicMouseActionInterface#needSelect()
	 */
	@Override
	public final boolean needSelect() {
		return needSelect;
	}

	/* (非 Javadoc)
	 * @see oripa.paint.core.GraphicMouseActionInterface#onDrag(oripa.paint.core.PaintContext, java.awt.geom.AffineTransform, boolean)
	 */
	@Override
	public abstract void onDrag(PaintContext context, AffineTransform affine, boolean differentAction);

	/* (非 Javadoc)
	 * @see oripa.paint.core.GraphicMouseActionInterface#onDraw(java.awt.Graphics2D, oripa.paint.core.PaintContext)
	 */
	@Override
	public void onDraw(Graphics2D g2d, PaintContext context){
		drawPickedLines(g2d, context);
		drawPickedVertices(g2d, context);

	}
	/* (非 Javadoc)
	 * @see oripa.paint.core.GraphicMouseActionInterface#onLeftClick(oripa.paint.core.PaintContext, java.awt.geom.AffineTransform, boolean)
	 */
	@Override
	public GraphicMouseActionInterface onLeftClick(PaintContext context, 
			AffineTransform affine, boolean differentAction){
		Point2D.Double clickPoint = context.getLogicalMousePoint();

		doAction(context, clickPoint, differentAction);
		return this;
	}





	/* (非 Javadoc)
	 * @see oripa.paint.core.GraphicMouseActionInterface#onMove(oripa.paint.core.PaintContext, java.awt.geom.AffineTransform, boolean)
	 */
	@Override
	public Vector2d onMove(
			PaintContext context, AffineTransform affine, boolean differentAction) {


		setCandidateVertexOnMove(context, differentAction);
		setCandidateLineOnMove(context);

		return context.pickCandidateV;
	}


	/* (非 Javadoc)
	 * @see oripa.paint.core.GraphicMouseActionInterface#onPress(oripa.paint.core.PaintContext, java.awt.geom.AffineTransform, boolean)
	 */
	@Override
	public abstract void onPress(PaintContext context, AffineTransform affine, boolean differentAction);
	
	/* (非 Javadoc)
	 * @see oripa.paint.core.GraphicMouseActionInterface#onRelease(oripa.paint.core.PaintContext, java.awt.geom.AffineTransform, boolean)
	 */
	@Override
	public abstract void onRelease(PaintContext context, AffineTransform affine, boolean differentAction);

	/* (非 Javadoc)
	 * @see oripa.paint.core.GraphicMouseActionInterface#onRightClick(oripa.paint.core.PaintContext, java.awt.geom.AffineTransform, boolean)
	 */
	@Override
	public void onRightClick(PaintContext context, AffineTransform affine,
			boolean differentAction) {

		undo(context);
	}

	/* (非 Javadoc)
	 * @see oripa.paint.core.GraphicMouseActionInterface#recover(oripa.paint.core.PaintContext)
	 */
	@Override
	public void recover(PaintContext context){
	}

	/* (非 Javadoc)
	 * @see oripa.paint.core.GraphicMouseActionInterface#undo(oripa.paint.core.PaintContext)
	 */
	@Override
	public void undo(PaintContext context){
		state = BasicUndo.undo(state, context);
	}

	protected final boolean currentStateIs(Class<? extends ActionState> s){
		return state.equals(s);
	}	

	protected void drawLine(Graphics2D g2d, OriLine line){
		g2d.draw(new Line2D.Double(line.p0.x, line.p0.y, 
				line.p1.x, line.p1.y));

	}

	
	protected void drawLine(Graphics2D g2d, Vector2d p0, Vector2d p1){
		g2d.draw(new Line2D.Double(p0.x, p0.y, 
				p1.x, p1.y));

	}
	
	protected void drawPickCandidateLine(Graphics2D g2d, PaintContext context){
		if (context.pickCandidateL!= null) {
			g2d.setColor(LineSetting.LINE_COLOR_CANDIDATE);
			OriLine candidate = context.pickCandidateL;
			drawLine(g2d, candidate);
		}
	}
	protected void drawPickCandidateVertex(Graphics2D g2d, PaintContext context){
		if (context.pickCandidateV != null) {
			g2d.setColor(LineSetting.LINE_COLOR_CANDIDATE);
			Vector2d candidate = context.pickCandidateV;
			drawVertex(g2d, context, candidate.x, candidate.y);
		}
	}

	/**
	 * draws the line between the most recently selected vertex and 
	 * the closest vertex sufficiently to the mouse cursor.
	 * if every vertex is far from cursor, this method uses the cursor point
	 * instead of close vertex.
	 * @param g2d
	 * @param context
	 */
	protected void drawTemporaryLine(Graphics2D g2d, PaintContext context){
		ElementSelector selector = new ElementSelector();

		if(context.getVertexCount() > 0){
			Vector2d picked = context.peekVertex();

			Color color = selector.selectColorByLineType(PaintConfig.inputLineType);
			g2d.setColor(color);
			drawLine(g2d, picked, GeometricOperation.getCandidateVertex(context, true));
		}

	}


	/**
	 * draw a picked vertex as an small rectangle at (x, y)
	 * @param g2d
	 * @param context
	 * @param x
	 * @param y
	 */
	protected void drawVertex(Graphics2D g2d, PaintContext context, 
			double x, double y){
		double scale = context.scale;
		g2d.fill(new Rectangle2D.Double(x - 5.0 / scale,
				y - 5.0 / scale, 10.0 / scale, 10.0 / scale));

	}

	protected final ActionState getActionState(){
		return state;
	}


	protected void log(double x, double y){
		System.out.println(x + "," + y);

	}


	protected void log(Vector2d p){
		if(p == null){
			return;
		}
		log(p.x, p.y);
	}

	protected final void setActionState(ActionState state){
		this.state = state;
	}

	protected final void setCandidateLineOnMove(PaintContext context) {
		context.pickCandidateL = GeometricOperation.pickLine(
				context);		
	}

	protected final void setCandidateVertexOnMove(
			PaintContext context, boolean differentAction) {
		context.pickCandidateV = GeometricOperation.pickVertex(
				context, differentAction);		
		
	}

	protected final void setEditMode(EditMode mode){
		editMode = mode;
	}


	protected final void setNeedSelect(boolean selectable) {
		this.needSelect = selectable;
	}




}
