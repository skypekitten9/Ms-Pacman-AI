package pacman.controllers.examples;
import java.util.ArrayList;
import pacman.controllers.Controller;
import pacman.game.Game;
import pacman.game.Constants.MOVE;

import static pacman.game.Constants.*;

public class DT_Controller extends Controller<MOVE> 
{
	public DT_Controller()
	{
		BuildTree();
		System.out.println("DT_CONTROLLER CONSTRUCTOR!!!!");
	}
	
	private void BuildTree()
	{
		
	}
	
	private MOVE ParseTree(Game game, long timeDue)
	{
		return MOVE.LEFT;
	}
	
	public MOVE getMove(Game game, long timeDue)
	{
		return ParseTree(game, timeDue);
	}
}


