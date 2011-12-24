package keeboi.game.entity;


import it.randomtower.engine.ME;
import it.randomtower.engine.entity.Entity;
import it.randomtower.engine.tween.LinearMotion;
import it.randomtower.engine.tween.Tween;

import java.io.IOException;

import keeboi.game.effect.ProtonBullet;
import keeboi.game.effect.Sploshun;
import keeboi.game.world.Global;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.particles.ParticleSystem;

public class Enemy extends Entity {
	

	Image scaledImage = null;
	int health = 140;
	
	int fireRate = 30;
	int tick = 0;
	
//	Image bulletImage = null;
	
	int quality = 0;
	
	Sound destruction = null;
	
	float speed = 0.1f;
	LinearMotion motion;
	Tween tween;
	
	ParticleSystem explosionSystem = null;
	
	public Enemy(float x, float y) throws SlickException, IOException {
		super(x, y);
		
		Image image1 = new Image("res/model/enemy.png");
		scaledImage = image1.getScaledCopy(0.4f);
//		bulletImage = new Image("res/model/bullet_yellow.png");
		setGraphic(scaledImage);
		destruction = new Sound("res/sound/bogsh.wav");
		setHitBox(0,0,scaledImage.getWidth(), scaledImage.getHeight(), true);
		setCentered(true);
		addType(EntityType.ENEMY);
		fireRate = (int) (fireRate * Math.random() * 100 + 30);
		quality = 20;
	}

	
	@Override
	public void update(GameContainer gc, int delta) throws SlickException {
		super.update(gc, delta);
		
		if(collide(EntityType.SHIP, x , y) != null) {
			health -= 100;
		}
		
	
		move(delta);
		tick += delta;	
		
		if(tick >= fireRate) 
			fire();
		
		if(y > gc.getHeight()) {
			this.destroy();
		}
	
		if(health <= 0)
			die();
		
	}
	
	@Override
	public void render(GameContainer gc, Graphics gfx) throws SlickException {
		super.render(gc, gfx);
	}
	
	@Override
	public void collisionResponse(Entity e) {
		health -= 33;
	}
	
	private void fire() throws SlickException {
		if(tick >= fireRate) {
			ProtonBullet proton = new ProtonBullet(x,y);
			ME.world.add(proton);
			tick = 0;
			randomizeFireRate();
		}
	}
	
	private void randomizeFireRate() {
		fireRate = (int) (Math.random() * 1000 + 150);
	}
	
	private void die() throws SlickException {
		destruction.play(1.0f, 0.8f);
		try {
			ME.world.add(new Sploshun(x,y));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Global.PLAYER_SCORE += quality;
		Global.PLAYER_KILLS++;
		this.destroy();	
	}
	
	public void move(int delta) {
		if(y+scaledImage.getHeight()/2 < Global.GAME_HEIGHT - Global.GAME_HEIGHT * 0.5f) {
			if(x+  scaledImage.getWidth()/2 < Global.PLAYER_X_COORD - 20) {
				x += delta * speed;
			}
			if(x > Global.PLAYER_X_COORD + 40) {
				x -= delta * speed;		
			}
			y += delta * speed;
		} else {
			y += delta * speed * 9;
		}
	}
	
}
