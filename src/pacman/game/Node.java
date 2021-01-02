package pacman.game;

import java.util.ArrayList;
import java.util.List;
import dataRecording.DataTuple;
import pacman.game.Constants.MOVE;
public class Node {
	public enum Attribute{
		DirectionChosen,
		pacmanPosition,
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
}
