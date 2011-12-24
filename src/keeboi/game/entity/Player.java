package keeboi.game.entity;

import it.randomtower.engine.ME;
import it.randomtower.engine.entity.Entity;
import it.randomtower.engine.tween.Ease;
import it.randomtower.engine.tween.LinearMotion;
import it.randomtower.engine.tween.Tween;
import keeboi.game.effect.Laser;
import keeboi.game.world.Global;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.MouseListener;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

public class Player extends Entity implements MouseListener {

	public static float scale = 0.5f;
	public static int health = 200;

	public static String PLAYER = "player";
	
	Image scaledImage = null;
	
	int fireRate = 200;
	int tick = 0;
	
	Sound gunfire = null;
	Sound hit = null;
	
	boolean showHud = false;
	
	private Tween tween;
	private LinearMotion motion;
	Input input;
	
	public Player(float x, float y, Image image) throws SlickException {
		super(x, y, image);
		
		this.x = x;
		this.y = y;
		this.name = PLAYER;
		setDefinitions();
		scaledImage = image.getScaledCopy(scale);
		gunfire = new Sound("res/sound/pyow.wav");
		hit = new Sound("res/sound/hit.wav");
		setGraphic(scaledImage);
		setCentered(true);
		setHitBox(0, 0, scaledImage.getWidth(), scaledImage.getHeight(), true);
		addType(EntityType.SHIP);

//		motion = new LinearMotion(x,y,x,y,1, Ease.BACK_OUT);
//		tween = new Tween(motion, true);
	}
	
	@Override
	public void update(GameContainer gc, int delta) throws SlickException {
		super.update(gc, delta);

		input = gc.getInput();
		
		gc.setPaused(Global.PAUSED);
//		if(input.isKeyPressed(Input.KEY_ESCAPE) && !Global.isPaused) {
//			Global.isPaused = true;
//			gc.pause();
//		} 
//		if(input.isKeyPressed(Input.KEY_ESCAPE) && Global.isPaused) {
//			Global.isPaused = false;
//		}		
		
		input.addMouseListener(this);
		
		tick += delta;
			
		if(!gc.isPaused()) {
			if(Global.CONTROL_SCHEME == 1) {
				if(check("right") && x + scaledImage.getWidth() < Global.GAME_WIDTH) {
					x += delta * scale;
				}
				if(check("left") && x > 0) {
					x -= delta * scale;
				}
				if(check("forward") && y > 0) {
					y -= delta * scale;
				}		
				if(check("backward") && y + scaledImage.getHeight() < gc.getHeight()) {
					y += delta * scale;
				}
			}
			
			if(Global.CONTROL_SCHEME == 0) {
				motion = new LinearMotion(x,y,input.getMouseX(),input.getMouseY(),(int) (delta * (scale*2)), Ease.BACK_OUT);
				tween = new Tween(motion, true);
//				motion.update();
//				motion.fromX = x;
//				motion.fromY = x;
//				motion.toX = input.getMouseX();
//				motion.toY = input.getMouseY();
//				tween.
//				tweener.add(tween);
//				tweener.start();
//				x = input.getMouseX();
//				y = input.getMouseY();
//				this.setPosition(tweener.apply(this));
				setPosition(tween.apply(this));
			}
		}
		
//		if(check("change scheme") && Global.CONTROL_SCHEME == 1) {
//			Global.CONTROL_SCHEME = 0;
//		}
//		if(check("change scheme") && Global.CONTROL_SCHEME == 0) {
//			Global.CONTROL_SCHEME = 1;
//		}	
		
		if(input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON) || input.isKeyDown(Input.KEY_J))
			fire();
		
		if(input.isKeyPressed(Input.KEY_TAB)) {
			if(showHud)
				showHud = false;
			else
				showHud = true;
		}
		
		if(input.isKeyPressed(Input.KEY_ESCAPE)) {
			if(Global.PAUSED)
				Global.PAUSED = false;
			else
				Global.PAUSED = true;
		}
		
		if(health <= 0) {
			gc.pause();
		}
		
		Global.PLAYER_X_COORD = x;
		Global.PLAYER_Y_COORD = y;
	}
	
	@Override
	public void render(GameContainer gc, Graphics gfx) throws SlickException {
		super.render(gc, gfx);
		if(showHud || health <= 0 || Global.PAUSED) {
			gfx.setColor(org.newdawn.slick.Color.green);
			gfx.drawRoundRect(x - 175, y + 10, 150, 60, 5);
			if(health <= 100 && health > 60) { 
				gfx.setColor(org.newdawn.slick.Color.yellow);
				gfx.drawString("Health:" + health , x- 170, y+ 10);
				gfx.setColor(org.newdawn.slick.Color.green);
			} else if (health <= 60){
				gfx.setColor(org.newdawn.slick.Color.red);
				gfx.drawString("Health:" + health , x- 170, y+ 10);
				gfx.setColor(org.newdawn.slick.Color.green);
			} else
				gfx.drawString("Health:" + health , x- 170, y+ 10);
			gfx.drawString("Score:" + Global.PLAYER_SCORE , x-170, y+30);
			gfx.drawString("Kills:" + Global.PLAYER_KILLS , x-170, y+50);
		}
	}
	
	private void fire() throws SlickException {
		if(tick >= fireRate) {
//			if(Global.PLAYER_KILLS <= 20) {
//				Laser fire = new Laser(x, y);
//				ME.world.add(fire);
//				gunfire.play();
//				tick = 0;
//			}
//			if(Global.PLAYER_KILLS > 20 && Global.PLAYER_KILLS <= 40 ) {
			Laser left = new Laser(x-15, y);
			Laser right = new Laser(x+15, y);
			ME.world.add(left);
			ME.world.add(right);
			gunfire.play();
			tick = 0;
//			}
//			if(Global.PLAYER_KILLS > 40) {
//				Laser left = new Laser(x-15, y);
//				Laser mid = new Laser(x,y);
//				Laser right = new Laser(x+15, y);
//				ME.world.add(left);
//				ME.world.add(mid);
//				ME.world.add(right);
//				gunfire.play();
//				tick = 0;
//			}		
		}
	}
	
	private void setDefinitions() {
		define("right", Input.KEY_D, Input.KEY_RIGHT);
		define("forward", Input.KEY_W, Input.KEY_UP);
		define("left", Input.KEY_A, Input.KEY_LEFT);
		define("backward", Input.KEY_S, Input.KEY_DOWN);
		define("pause", Input.KEY_ESCAPE);
		define("change scheme", Input.KEY_1);
	}

	@Override
	public void collisionResponse(Entity e) {
		hit.play();
		health -= 20;
	}

	@Override
	public void inputEnded() {
	}

	@Override
	public void inputStarted() {
	}

	@Override
	public boolean isAcceptingInput() {
		return true;
	}

	@Override
	public void setInput(Input arg0) {
	}

	@Override
	public void mouseClicked(int arg0, int arg1, int arg2, int arg3) {
	}

	@Override
	public void mouseDragged(int arg0, int arg1, int arg2, int arg3) {
	}

	@Override
	public void mouseMoved(int arg0, int arg1, int arg2, int arg3) {
	}

	@Override
	public void mousePressed(int arg0, int arg1, int arg2) {
	}

	@Override
	public void mouseReleased(int arg0, int arg1, int arg2) {
	}

	@Override
	public void mouseWheelMoved(int arg0) {
	}
	
}
