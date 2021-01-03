package pacman.game;

import java.util.ArrayList;
import java.util.List;
import dataRecording.DataTuple;
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
	
	boolean leaf = false;
	Attribute attribute;
	MOVE move;
	
	public Node()
	{
		
	}
	
	public MOVE GetMove()
	{
		return move;
	}
	
	public void SetMove(MOVE moveToSet)
	{
		move = moveToSet;
	}
	
	public void SetLeaf(boolean toSet)
	{
		leaf = toSet;
	}
	
	public void SetAttribute()
	{
		
	}
	
	public static boolean AttributeCondition(Attribute attribute)
	{
		switch(attribute)
		{
		case blinkyDist:
			break;
			
		case inkyDist:
			break;
			
		case pinkyDist:
			break;
			
		case sueDist:
			break;
			
		case blinkyDir:
			break;
			
		case inkyDir:
			break;
			
		case pinkyDir:
			break;
			
		case sueDir:
			break;
			
		default:
			System.out.println("ERROR: ATTRIBUTE NOT IN ENUM");
			break;
			
		}
		return true;
	}
}
