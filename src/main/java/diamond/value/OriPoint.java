package diamond.value;

import javax.vecmath.Vector2d;

/**
 * Comparable vertex
 * 
 * @author Koji
 *
 */
public class OriPoint extends Vector2d implements Comparable<Vector2d> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6753157628675186598L;

	public OriPoint() {
		super();
	}
	
	public OriPoint(double x, double y) {
		super(x, y);
	}
	
	@Override
	public int compareTo(Vector2d o) {
		if (this.x == o.x) {
			return (int)Math.signum(this.y - o.y);
		}
		return (int)Math.signum(this.x - o.x);
	}
}
