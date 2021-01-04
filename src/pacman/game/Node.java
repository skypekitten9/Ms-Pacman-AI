package pacman.game;

import java.util.ArrayList;
import java.util.List;
import dataRecording.DataTuple;
import pacman.game.Constants.DM;
import pacman.game.Constants.GHOST;
import pacman.game.Constants.MOVE;
public class Node {
	public enum Attribute{
		blinkyDist,
		inkyDist,
		pinkyDist,
		sueDist,
		blinkyDir,
		inkyDir,
		pinkyDir,
		sueDir
	}
	
	List<Node> children;
	Attribute attribute;
	MOVE move;
	
	public Node()
	{
		children = new ArrayList<Node>();
	}
	
	public MOVE Parse(Game game, long timeDue)
	{
		if(children.size() <= 0)
		{
			return move;
		}
		
		switch(attribute)
		{
		case blinkyDist:
			if(game.getShortestPathDistance(game.getPacmanCurrentNodeIndex(), game.getGhostCurrentNodeIndex(GHOST.BLINKY)) < 20)
			{
				return children.get(0).Parse(game, timeDue);
			}
			else
			{
				return children.get(1).Parse(game, timeDue);
			}
			
		case inkyDist:
			if(game.getShortestPathDistance(game.getPacmanCurrentNodeIndex(), game.getGhostCurrentNodeIndex(GHOST.INKY)) < 20)
			{
				return children.get(0).Parse(game, timeDue);
			}
			else
			{
				return children.get(1).Parse(game, timeDue);
			}
			
		case pinkyDist:
			if(game.getShortestPathDistance(game.getPacmanCurrentNodeIndex(), game.getGhostCurrentNodeIndex(GHOST.PINKY)) < 20)
			{
				return children.get(0).Parse(game, timeDue);
			}
			else
			{
				return children.get(1).Parse(game, timeDue);
			}
			
		case sueDist:
			if(game.getShortestPathDistance(game.getPacmanCurrentNodeIndex(), game.getGhostCurrentNodeIndex(GHOST.SUE)) < 20)
			{
				return children.get(0).Parse(game, timeDue);
			}
			else
			{
				return children.get(1).Parse(game, timeDue);
			}
			
		case blinkyDir:
			switch(game.getNextMoveTowardsTarget(game.getPacmanCurrentNodeIndex(), game.getGhostCurrentNodeIndex(GHOST.BLINKY), DM.PATH))
			{
			case LEFT:
				return children.get(0).Parse(game, timeDue);
			
			case RIGHT:
				return children.get(1).Parse(game, timeDue);
				
			case UP:
				return children.get(2).Parse(game, timeDue);
				
			case DOWN:
				return children.get(3).Parse(game, timeDue);
				
			case NEUTRAL:
				return children.get(4).Parse(game, timeDue);
			}
			break;
			
		case inkyDir:
			switch(game.getNextMoveTowardsTarget(game.getPacmanCurrentNodeIndex(), game.getGhostCurrentNodeIndex(GHOST.INKY), DM.PATH))
			{
			case LEFT:
				return children.get(0).Parse(game, timeDue);
			
			case RIGHT:
				return children.get(1).Parse(game, timeDue);
				
			case UP:
				return children.get(2).Parse(game, timeDue);
				
			case DOWN:
				return children.get(3).Parse(game, timeDue);
				
			case NEUTRAL:
				return children.get(4).Parse(game, timeDue);
			}
			break;
			
		case pinkyDir:
			switch(game.getNextMoveTowardsTarget(game.getPacmanCurrentNodeIndex(), game.getGhostCurrentNodeIndex(GHOST.PINKY), DM.PATH))
			{
			case LEFT:
				return children.get(0).Parse(game, timeDue);
			
			case RIGHT:
				return children.get(1).Parse(game, timeDue);
				
			case UP:
				return children.get(2).Parse(game, timeDue);
				
			case DOWN:
				return children.get(3).Parse(game, timeDue);
				
			case NEUTRAL:
				return children.get(4).Parse(game, timeDue);
			}
			break;
			
		case sueDir:
			switch(game.getNextMoveTowardsTarget(game.getPacmanCurrentNodeIndex(), game.getGhostCurrentNodeIndex(GHOST.SUE), DM.PATH))
			{
			case LEFT:
				return children.get(0).Parse(game, timeDue);
			
			case RIGHT:
				return children.get(1).Parse(game, timeDue);
				
			case UP:
				return children.get(2).Parse(game, timeDue);
				
			case DOWN:
				return children.get(3).Parse(game, timeDue);
				
			case NEUTRAL:
				return children.get(4).Parse(game, timeDue);
			}
			break;
		default:
			System.out.println("ERROR: ATTRIBUTE NOT IN ENUM");
			break;
			
		}
		return move;
	}
	
	public void Print(String indent, boolean last)
	{
		System.out.print(indent);
		if(last)
		{
			System.out.print("\\-");
	        indent += "  ";
		}
		else
		{
			System.out.print("|-");
			indent += "| ";
		}
		
		if(children.size() <= 0)
		{
			System.out.println(move);
		}
		else
		{
			System.out.println(attribute);
		}
		
		for(int i = 0; i<children.size(); i++)
		{
			children.get(i).Print(indent, i == children.size() - 1);
		}
		
	}
	
	public MOVE GetMove()
	{
		return move;
	}
	
	public void SetMove(MOVE moveToSet)
	{
		move = moveToSet;
	}
	
	public void SetAttribute(Attribute attributeToSet)
	{
		attribute = attributeToSet;
	}
	
	public void AddChild(Node child)
	{
		children.add(child);
	}
}
