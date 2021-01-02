package pacman.controllers.examples;
import java.util.ArrayList;
import java.util.List;

import dataRecording.DataSaverLoader;
import dataRecording.DataTuple;
import pacman.controllers.Controller;
import pacman.game.Game;
import pacman.game.Node;
import pacman.game.Constants.MOVE;

import static pacman.game.Constants.*;



public class DT_Controller extends Controller<MOVE> 
{
	DataTuple[] data;
	List<Node.Attribute> attributeList = new ArrayList<Node.Attribute>();
	List<Node> tree = new ArrayList<Node>();
	public DT_Controller()
	{
		data = DataSaverLoader.LoadPacManData();
		//attributeList = LoadAttributes();
		BuildTree(data, attributeList);
		System.out.println("DT_CONTROLLER CONSTRUCTOR!!!!");
	}
	
	
	
	public void BuildTree(DataTuple[] tuples, List<Node.Attribute> attributes)
	{
		//Steg 1
		Node node = new Node();
		tree.add(node);
		
		//Steg 2
		boolean sameClass = true;
		for(int i = 0; i < tuples.length; i++) 
		{
			if(tuples[i].DirectionChosen != tuples[0].DirectionChosen)
			{
				sameClass = false;
				break;
			}
		}
		if(sameClass)
		{
			node.SetMove(tuples[0].DirectionChosen);
			node.SetLeaf(true);
			System.out.println("EQUAL TUPLES");
			return;
		}
		
		//Steg 3
		if(attributes.size() <= 0)
		{
			int upCount = 0;
			int downCount = 0;
			int leftCount = 0;
			int rightCount = 0;
			for(int i = 0; i < tuples.length; i++) 
			{
				if(tuples[i].DirectionChosen == MOVE.UP)
				{
					upCount++;
				}
				else if(tuples[i].DirectionChosen == MOVE.DOWN)
				{
					downCount++;
				}
				else if(tuples[i].DirectionChosen == MOVE.LEFT)
				{
					leftCount++;
				}
				else if(tuples[i].DirectionChosen == MOVE.RIGHT)
				{
					rightCount++;
				}
			}
			if(upCount >= downCount && upCount >= leftCount && upCount >= rightCount)
			{
				node.SetMove(MOVE.UP);
			}
			else if(downCount >= upCount && downCount >= leftCount && downCount >= rightCount)
			{
				node.SetMove(MOVE.DOWN);
			}
			else if(leftCount >= upCount && leftCount >= downCount && leftCount >= rightCount)
			{
				node.SetMove(MOVE.LEFT);
			}
			else if(rightCount >= upCount && rightCount >= downCount && rightCount >= leftCount)
			{
				node.SetMove(MOVE.RIGHT);
			}
			node.SetLeaf(true);
			System.out.println("NO ATTRIBUTES, RETURNING MAX");
			return;
		}
		
	}
	
	private void TestTree()
	{
		
	}
	
	private MOVE ParseTree(Game game, long timeDue)
	{
		return tree.get(0).GetMove();
	}
	
	public MOVE getMove(Game game, long timeDue)
	{
		return ParseTree(game, timeDue);
	}
	
	private List<Node.Attribute> LoadAttributes()
	{
		List<Node.Attribute> result = new ArrayList<Node.Attribute>();
		result.add(Node.Attribute.DirectionChosen);
		result.add(Node.Attribute.pacmanPosition);
		result.add(Node.Attribute.blinkyDist);
		result.add(Node.Attribute.inkyDist);
		result.add(Node.Attribute.pinkyDist);
		result.add(Node.Attribute.sueDist);
		result.add(Node.Attribute.blinkyDir);
		result.add(Node.Attribute.inkyDir);
		result.add(Node.Attribute.pinkyDir);
		result.add(Node.Attribute.sueDir);
		return result;
	}
}


