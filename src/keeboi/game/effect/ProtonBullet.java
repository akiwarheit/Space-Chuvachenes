package keeboi.game.effect;

import it.randomtower.engine.ME;
import it.randomtower.engine.entity.Entity;

import java.io.IOException;

import keeboi.game.entity.EntityType;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.particles.ParticleIO;
import org.newdawn.slick.particles.ParticleSystem;

public class ProtonBullet extends Entity {
	
	ParticleSystem protonBullet;
	float speed = 0.5f;

	public ProtonBullet(float x, float y) {
		super(x, y);
		try {
			protonBullet = ParticleIO.loadConfiguredSystem("res/particle/projectile/proton.xml");
		} catch (IOException e) {
			e.printStackTrace();
		}
		setHitBox(0,0,30,20);
		addType(EntityType.ENEMY_PROJECTILE);
	}
	
	@Override
	public void update(GameContainer gc, int delta) throws SlickException {
		super.update(gc, delta);
		if(collide(EntityType.SHIP, x, y) != null) {
			this.destroy();
		}
		if(collide(EntityType.PROJECTILE, x, y) != null) {
			ME.world.add(new SmallSploshun(x,y));
			this.destroy();
		}	
		
		protonBullet.update(delta);
		y += delta * speed;
			
		if(y >= gc.getHeight())
			this.destroy();
	}
	
	@Override
	public void render(GameContainer gc, Graphics gfx) throws SlickException {
		super.render(gc, gfx);
		protonBullet.render(x,y);
	}
	
}
