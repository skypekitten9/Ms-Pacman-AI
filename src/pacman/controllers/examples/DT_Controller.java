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
		attributeList = LoadAttributes();
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
		
		//Step 4
		System.out.println(AttributeSelection(tuples, attributes));
	}
	
	private Node.Attribute AttributeSelection(DataTuple[] tuples, List<Node.Attribute> attributes)
	{
		Node.Attribute result = attributes.get(0);
		float highestGain = InformationGain(tuples, attributes.get(0));
		for(int i = 1; i<attributes.size(); i++)
		{
			if(InformationGain(tuples, attributes.get(i)) > highestGain)
			{
				highestGain = InformationGain(tuples, attributes.get(i));
				result = attributes.get(i);
			}
		}
		return result;
	}
	
	private float InformationGain(DataTuple[] tuples, Node.Attribute attribute)
	{
		float gain = 0;
		float entropyBefore = 0;
		float entropyAfter = 0;
		entropyBefore = Entropy(tuples);
		
		List<DataTuple[]> splitTuples = SplitTuples(tuples, attribute);
		for(int i = 0; i < splitTuples.size(); i++)
		{
			//entropyAfter += Entropy(splitTuples.get(i));
		}
		
		//System.out.println("entropy after: " + entropyAfter);
		return gain;
	}
	
	private float Entropy(DataTuple[] tuples)
	{
		float right = 0, left = 0, up = 0, down = 0, neutral = 0;
		
		for(int i = 0; i < tuples.length; i++) 
		{
			switch(tuples[i].DirectionChosen) 
			{
			case LEFT:
				left++;
				break;
				
			case RIGHT:
				right++;
				break;
				
			case UP:
				up++;
				break;
				
			case DOWN:
				down++;
				break;
				
			case NEUTRAL:
				neutral++;
				break;
			}			
		}
		
		float entropy = -1 *((left / tuples.length)*Log2(left / tuples.length)) + 
						 -1 *((right / tuples.length)*Log2(right / tuples.length)) + 
						 -1 *((up / tuples.length)*Log2(up / tuples.length)) + 
						 -1 *((down / tuples.length)*Log2(down / tuples.length)) +
						 -1 *((neutral / tuples.length)*Log2(neutral / tuples.length));
		
		System.out.println("entropy: " + entropy);

		return entropy;
	}
	
	private float Log2(float x) 
	{
		if(x <= 0) return 0f;
		return (float)(Math.log10(x) / Math.log10(2));
	}
	
	private List<DataTuple[]> SplitTuples(DataTuple[] tuples, Node.Attribute attribute)
	{
		List<DataTuple[]> result = new ArrayList<DataTuple[]>();
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
		return result;
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


