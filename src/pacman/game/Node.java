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
	
	//Constructor
	public Node()
	{
		children = new ArrayList<Node>();
	}
	
	//Parse the node with gamedata
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
	
	//Parse the node with recorded data
	public MOVE Parse(DataTuple data)
	{
		if(children.size() <= 0)
		{
			return move;
		}
		
		switch(attribute)
		{
		case blinkyDist:
			if(data.blinkyDist < 20)
			{
				return children.get(0).Parse(data);
			}
			else
			{
				return children.get(1).Parse(data);
			}
			
		case inkyDist:
			if(data.blinkyDist < 20)
			{
				return children.get(0).Parse(data);
			}
			else
			{
				return children.get(1).Parse(data);
			}
			
		case pinkyDist:
			if(data.pinkyDist < 20)
			{
				return children.get(0).Parse(data);
			}
			else
			{
				return children.get(1).Parse(data);
			}
			
		case sueDist:
			if(data.sueDist < 20)
			{
				return children.get(0).Parse(data);
			}
			else
			{
				return children.get(1).Parse(data);
			}
			
		case blinkyDir:
			switch(data.blinkyDir)
			{
			case LEFT:
				return children.get(0).Parse(data);
			
			case RIGHT:
				return children.get(1).Parse(data);
				
			case UP:
				return children.get(2).Parse(data);
				
			case DOWN:
				return children.get(3).Parse(data);
				
			case NEUTRAL:
				return children.get(4).Parse(data);
			}
			break;
			
		case inkyDir:
			switch(data.inkyDir)
			{
			case LEFT:
				return children.get(0).Parse(data);
			
			case RIGHT:
				return children.get(1).Parse(data);
				
			case UP:
				return children.get(2).Parse(data);
				
			case DOWN:
				return children.get(3).Parse(data);
				
			case NEUTRAL:
				return children.get(4).Parse(data);
			}
			break;
			
		case pinkyDir:
			switch(data.pinkyDir)
			{
			case LEFT:
				return children.get(0).Parse(data);
			
			case RIGHT:
				return children.get(1).Parse(data);
				
			case UP:
				return children.get(2).Parse(data);
				
			case DOWN:
				return children.get(3).Parse(data);
				
			case NEUTRAL:
				return children.get(4).Parse(data);
			}
			break;
			
		case sueDir:
			switch(data.sueDir)
			{
			case LEFT:
				return children.get(0).Parse(data);
			
			case RIGHT:
				return children.get(1).Parse(data);
				
			case UP:
				return children.get(2).Parse(data);
				
			case DOWN:
				return children.get(3).Parse(data);
				
			case NEUTRAL:
				return children.get(4).Parse(data);
			}
			break;
		default:
			System.out.println("ERROR: ATTRIBUTE NOT IN ENUM");
			break;
			
		}
		return move;
	}
	
	//Print node and children
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
