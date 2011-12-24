package keeboi.game;

import keeboi.game.menu.Menu;
import keeboi.game.world.Global;
import keeboi.game.world.Space;
import keeboi.game.world.StateID;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class SpaceChuvachenes extends StateBasedGame {

	Space gameWorld = null;
	Menu menuWorld = null;
	
	public SpaceChuvachenes(String name) {
		super(name);
	}

	@Override
	public void initStatesList(GameContainer gc) throws SlickException {
		menuWorld = new Menu(StateID.MENU_STATE,gc);
		gameWorld = new Space(StateID.GAMEPLAY_STATE, gc);
		addState(menuWorld);
		addState(gameWorld);
	}
	
	public static void main(String[] args) throws SlickException {
		SpaceChuvachenes space = new SpaceChuvachenes("SpaceChuvachenes");
		AppGameContainer app = new AppGameContainer(space);
		app.setMouseGrabbed(true);
		app.setTargetFrameRate(60);
//		app.setMinimumLogicUpdateInterval(10);
//		app.setShowFPS(true);
		app.setDisplayMode(Global.GAME_WIDTH, Global.GAME_HEIGHT, false);
		app.setSoundVolume(0.3f);
		app.start();
	}
}
