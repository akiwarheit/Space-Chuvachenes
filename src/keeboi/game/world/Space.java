package keeboi.game.world;

import it.randomtower.engine.ME;
import it.randomtower.engine.World;

import java.io.IOException;

import keeboi.game.entity.Enemy;
import keeboi.game.entity.Player;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.state.StateBasedGame;

public class Space extends World {
	
//	Meteorite[] meteorShower;
//	Meteorite[] slowMeteors;
	
	int enemyRespawnRate = 750;
	int tick = 0;
	
	public static int currentBoss = 0;
	
	public static boolean isBossBattle = false;
	
	public Player player = null;
	
	Sound enemySpawn = null;
//	Music background = null;
//	HorizontalSlideCamera camera;
	
	public Space(int id, GameContainer gc) throws SlickException {
		super(id, gc);
		
	}
	
	@Override
	public void init(GameContainer gc, StateBasedGame game)
			throws SlickException {
//		initShower(5);
//		initSlowShower(5);
		
//		background = new Sound("res/sound/background.ogg");
//		background = new Music("res/sound/background.wav");
		enemySpawn = new Sound("res/sound/kwaah.wav");
		
		player = new Player(Global.GAME_HEIGHT/2, Global.GAME_HEIGHT/2 + Global.GAME_HEIGHT/4, new Image("res/model/plane.png"));
		player.depth += 10;
		add(player);
//		camera = new HorizontalSlideCamera(player, 500, 768);
//		this.setCamera(camera);
//		gc.setMouseCursor(Cursor.CURSOR_ONE_BIT_TRANSPARENCY, (int)player.x, (int)player.y);
		
		
	}
	
	@Override
	public void render(GameContainer gc, StateBasedGame game, Graphics gfx)
			throws SlickException {
		super.render(gc, game, gfx);
		
//		gfx.setColor(new Color(188.0f,227.0f,229.0f,0.3f));
//		for(int i=0;i<meteorShower.length-1;i++){
//			gfx.draw(meteorShower[i]);
//		}
//		
//		gfx.setColor(new Color(200.0f,150.0f,229.0f,0.3f));
//		for(int i=0;i<slowMeteors.length-1;i++){
//			gfx.draw(slowMeteors[i]);
//		}
	}
	
	@Override
	public void update(GameContainer gc, StateBasedGame game, int delta)
			throws SlickException {
		super.update(gc, game, delta);
		
//		camera.update(gc, delta);
		
//		if(!background.playing())
//			background.play(1.0f, 2.0f);
		
//		this.setCameraOn(player);
		
//		if(!background.playing())
//			background.play();
//		this.setCamera(new HorizontalSlideCamera(player, 1024, 768));
		
		tick += delta;
		
		if(tick > enemyRespawnRate && !isBossBattle) {
			try {
					ME.world.add(new Enemy((float) Math.random() * gc.getWidth() + 1,0));
					enemySpawn.play();
			} catch (IOException e) {
				e.printStackTrace();
			}
			randomizeRespawn();
			tick = 0;
		}
		
//		if(Global.PLAYER_SCORE % 100 == 0 && Global.PLAYER_SCORE != 0 && currentBoss == 0) {
//			isBossBattle = true;
//			Boss bhans = new Boss((float) Math.random() * gc.getWidth() + 1,0, new Image("res/model/bossbhans.png"));
//			ME.world.add(bhans);
//			++currentBoss;
//		}
		
//		for(int i=0;i<meteorShower.length-1;i++){
//      meteorShower[i].setY(meteorShower[i].getY()+ delta); 
//	    if(meteorShower[i].getY()>gc.getHeight()){
//	      meteorShower[i].setX((float) ((Math.random() * 2000)+30));
//	      meteorShower[i].setY((float) ((Math.random() * 5)));
//			}
//		}
//		
//		for(int i=0;i<slowMeteors.length-1;i++){
//      slowMeteors[i].setY(slowMeteors[i].getY()+ delta * 0.4f); 
//	    if(slowMeteors[i].getY()>gc.getHeight()){
//	      slowMeteors[i].setX((float) ((Math.random() * 2000)+30));
//	      slowMeteors[i].setY((float) ((Math.random() * 5)));
//			}
//		}	
	}
	
//	void initShower(int size) {
//		meteorShower = new Meteorite[size];
//		for(int i=0;i< meteorShower.length-1;i++){
//	    meteorShower[i]=new Meteorite((int) (Math.random() * 60 + 5));
//	    meteorShower[i].setX((float) ((Math.random() * 2000)+30));
//	    meteorShower[i].setY((float) ((Math.random() * 750)));
//		}
//	}
//	
//	void initSlowShower(int size) {
//		slowMeteors = new Meteorite[size];
//		for(int i=0;i< slowMeteors.length-1;i++){
//	    slowMeteors[i]=new Meteorite((int) (Math.random() * 40 + 30));
//	    slowMeteors[i].setX((float) ((Math.random() * 2000)+30));
//	    slowMeteors[i].setY((float) ((Math.random() * 750)));
//		}
//	}
	
	void randomizeRespawn() {
		enemyRespawnRate = (int) (Math.random() * 1500 + 200);
	}
	
}
