package keeboi.game.menu;

import keeboi.game.world.StateID;
import it.randomtower.engine.World;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Menu extends World {
	
	Input input;

	public Menu(int id, GameContainer container) {
		super(id, container);
	}
	
	@Override
	public void init(GameContainer gc, StateBasedGame game)
			throws SlickException {

	}
	
	@Override
	public void render(GameContainer gc, StateBasedGame game, Graphics gfx)
			throws SlickException {
		super.render(gc, game, gfx);
		gfx.drawString("Space Chuva Eklavush", gc.getWidth()/2 - 80, gc.getHeight()/2 - 50);
		gfx.drawString("Press Enter to Chuva", gc.getWidth()/2 - 80, gc.getHeight()/2 - 50 + 20);
	}
	
	@Override
	public void update(GameContainer gc, StateBasedGame game, int delta)
			throws SlickException {
		super.update(gc, game, delta);
		input = gc.getInput();

		if(input.isKeyPressed(Input.KEY_ENTER))
			game.enterState(StateID.GAMEPLAY_STATE);
		
	}
	

}
