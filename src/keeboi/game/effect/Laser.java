package keeboi.game.effect;

import it.randomtower.engine.entity.Entity;

import java.io.IOException;

import keeboi.game.entity.EntityType;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.particles.ParticleIO;
import org.newdawn.slick.particles.ParticleSystem;

public class Laser extends Entity {

	ParticleSystem laserEffect;
	float speed = 0.8f;
	
	public Laser(float x, float y) {
		super(x, y);
		try {
			laserEffect = ParticleIO.loadConfiguredSystem("res/particle/projectile/bullet.xml");
		} catch (IOException e) {
			e.printStackTrace();
		}
		setHitBox(0,0,10,20);
		addType(EntityType.PROJECTILE);
	}
	
	@Override
	public void update(GameContainer gc, int delta) throws SlickException {
		super.update(gc, delta);
		if(collide(EntityType.ENEMY, x, y) != null) {
			this.destroy();
		}
		if(collide(EntityType.ENEMY_PROJECTILE, x, y) != null) {
			this.destroy();
		}	
		
		laserEffect.update(delta);
		y -= delta * speed;
			
		if(y <= 0)
			this.destroy();
	
	}
	
	@Override
	public void render(GameContainer gc, Graphics gfx) throws SlickException {
		super.render(gc, gfx);
		laserEffect.render(x,y);
	}
	
	@Override
	public void collisionResponse(Entity e) {
	}
	
}
