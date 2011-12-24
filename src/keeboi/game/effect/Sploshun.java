package keeboi.game.effect;

import it.randomtower.engine.entity.Entity;

import java.io.IOException;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.particles.ParticleIO;
import org.newdawn.slick.particles.ParticleSystem;

public class Sploshun extends Entity {
	
	ParticleSystem explosionSystem = null;
	
	boolean isDestroyed = false;
	
	int tick = 0;
	int sploshunTimeout = 300;
	
	public Sploshun(float x, float y) throws SlickException, IOException {
		super(x, y);
		explosionSystem = ParticleIO.loadConfiguredSystem("res/particle/explosion/explosion.xml");
	}
	
	@Override
	public void update(GameContainer gc, int delta) throws SlickException {
		super.update(gc, delta);
		
		y += delta * 0.5f;	
		explosionSystem.update(delta);
		
		tick+= delta;
		if(tick >= sploshunTimeout) {
			this.destroy();
		}
	}
	
	@Override
	public void render(GameContainer gc, Graphics gfx) throws SlickException {
		super.render(gc, gfx);
		explosionSystem.render(x,y);
	}
}
