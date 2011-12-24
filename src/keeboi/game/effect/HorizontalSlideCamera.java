package keeboi.game.effect;

import it.randomtower.engine.Camera;
import it.randomtower.engine.entity.Entity;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;

public class HorizontalSlideCamera extends Camera {
	
	float camWidth;
	float camHeight;

	public HorizontalSlideCamera(Entity toFollow, int width, int height) {
		super(toFollow, width, height);
		camWidth = width;
		camHeight = height;
	}
	
	@Override
	public void update(GameContainer gc, int delta) throws SlickException{
		super.update(gc, delta);
		
		if(x < 0)
			x = 0;
		if(x + camWidth > gc.getWidth())
			x = x - camWidth;
		y = 0;

	}
	
	
}
