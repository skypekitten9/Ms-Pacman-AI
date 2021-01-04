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
	
	List<Node> children;
	Attribute attribute;
	MOVE move;
	
	public Node()
	{
		children = new ArrayList<Node>();
	}
	
	public MOVE Parse()
	{
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
		if(children.size() <= 0)
		{
			return move;
		}
		else return Parse();
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
