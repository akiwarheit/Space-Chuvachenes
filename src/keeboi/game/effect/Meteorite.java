package keeboi.game.effect;

import org.newdawn.slick.geom.Polygon;

public class Meteorite extends Polygon {
	
	private static final long serialVersionUID = 1L;

	public Meteorite(int size) {
		super();
		this.addPoint(0, 0);
		this.addPoint(0, size);
	}
	
}
