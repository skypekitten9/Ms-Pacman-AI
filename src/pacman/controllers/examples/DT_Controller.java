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
			entropyAfter += Entropy(splitTuples.get(i));
		}
		gain = entropyBefore - entropyAfter;
		System.out.println("entropy before: " + entropyBefore);
		System.out.println("entropy after: " + entropyAfter);
		System.out.println("Gain: " + gain);
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
		
		if(Float.isNaN(entropy)) return 0f;
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
			List<DataTuple> close = new ArrayList<DataTuple>();
			List<DataTuple> distant = new ArrayList<DataTuple>();
			for(int i = 0; i < tuples.length; i++)
			{
				if(tuples[i].blinkyDist <= 20)
				{
					close.add(tuples[i]);
				}
				else
				{
					distant.add(tuples[i]);
				}
			}
			result.add(ListToArray(close));
			result.add(ListToArray(distant));
			break;
			
		case inkyDist:
			List<DataTuple> inkyClose = new ArrayList<DataTuple>();
			List<DataTuple> inkyDistant = new ArrayList<DataTuple>();
			for(int i = 0; i < tuples.length; i++)
			{
				if(tuples[i].inkyDist <= 20)
				{
					inkyClose.add(tuples[i]);
				}
				else
				{
					inkyDistant.add(tuples[i]);
				}
			}
			result.add(ListToArray(inkyClose));
			result.add(ListToArray(inkyDistant));
			break;
			
		case pinkyDist:
			List<DataTuple> pinkyClose = new ArrayList<DataTuple>();
			List<DataTuple> pinkyDistant = new ArrayList<DataTuple>();
			for(int i = 0; i < tuples.length; i++)
			{
				if(tuples[i].pinkyDist <= 20)
				{
					pinkyClose.add(tuples[i]);
				}
				else
				{
					pinkyDistant.add(tuples[i]);
				}
			}
			result.add(ListToArray(pinkyClose));
			result.add(ListToArray(pinkyDistant));
			break;
			
		case sueDist:
			List<DataTuple> sueClose = new ArrayList<DataTuple>();
			List<DataTuple> sueDistant = new ArrayList<DataTuple>();
			for(int i = 0; i < tuples.length; i++)
			{
				if(tuples[i].sueDist <= 20)
				{
					sueClose.add(tuples[i]);
				}
				else
				{
					sueDistant.add(tuples[i]);
				}
			}
			result.add(ListToArray(sueClose));
			result.add(ListToArray(sueDistant));
			break;
			
		case blinkyDir:
			List<DataTuple> left = new ArrayList<DataTuple>();
			List<DataTuple> right = new ArrayList<DataTuple>();
			List<DataTuple> up = new ArrayList<DataTuple>();
			List<DataTuple> down = new ArrayList<DataTuple>();
			List<DataTuple> neutral = new ArrayList<DataTuple>();
			for(int i = 0; i < tuples.length; i++)
			{
				switch(tuples[i].blinkyDir) 
				{
				case LEFT:
					left.add(tuples[i]);
					break;
				case RIGHT:
					right.add(tuples[i]);
					break;
					
				case UP:
					up.add(tuples[i]);
					break;
					
				case DOWN:
					down.add(tuples[i]);
					break;
										
				case NEUTRAL:
					neutral.add(tuples[i]);
					break;
				}
			}
			
			result.add(ListToArray(left));
			result.add(ListToArray(right));
			result.add(ListToArray(up));
			result.add(ListToArray(down));
			result.add(ListToArray(neutral));
			break;
			
		case inkyDir:
			List<DataTuple> inkyLeft = new ArrayList<DataTuple>();
			List<DataTuple> inkyRight = new ArrayList<DataTuple>();
			List<DataTuple> inkyUp = new ArrayList<DataTuple>();
			List<DataTuple> inkyDown = new ArrayList<DataTuple>();
			List<DataTuple> inkyNeutral = new ArrayList<DataTuple>();
			for(int i = 0; i < tuples.length; i++)
			{
				switch(tuples[i].inkyDir) 
				{
				case LEFT:
					inkyLeft.add(tuples[i]);
					break;
				case RIGHT:
					inkyRight.add(tuples[i]);
					break;
					
				case UP:
					inkyUp.add(tuples[i]);
					break;
					
				case DOWN:
					inkyDown.add(tuples[i]);
					break;
										
				case NEUTRAL:
					inkyNeutral.add(tuples[i]);
					break;
				}
			}
			
			result.add(ListToArray(inkyLeft));
			result.add(ListToArray(inkyRight));
			result.add(ListToArray(inkyUp));
			result.add(ListToArray(inkyDown));
			result.add(ListToArray(inkyNeutral));
			break;
			
		case pinkyDir:
			List<DataTuple> pinkyLeft = new ArrayList<DataTuple>();
			List<DataTuple> pinkyRight = new ArrayList<DataTuple>();
			List<DataTuple> pinkyUp = new ArrayList<DataTuple>();
			List<DataTuple> pinkyDown = new ArrayList<DataTuple>();
			List<DataTuple> pinkyNeutral = new ArrayList<DataTuple>();
			for(int i = 0; i < tuples.length; i++)
			{
				switch(tuples[i].pinkyDir) 
				{
				case LEFT:
					pinkyLeft.add(tuples[i]);
					break;
				case RIGHT:
					pinkyRight.add(tuples[i]);
					break;
					
				case UP:
					pinkyUp.add(tuples[i]);
					break;
					
				case DOWN:
					pinkyDown.add(tuples[i]);
					break;
										
				case NEUTRAL:
					pinkyNeutral.add(tuples[i]);
					break;
				}
			}
			
			result.add(ListToArray(pinkyLeft));
			result.add(ListToArray(pinkyRight));
			result.add(ListToArray(pinkyUp));
			result.add(ListToArray(pinkyDown));
			result.add(ListToArray(pinkyNeutral));
			break;
			
		case sueDir:
			List<DataTuple> sueLeft = new ArrayList<DataTuple>();
			List<DataTuple> sueRight = new ArrayList<DataTuple>();
			List<DataTuple> sueUp = new ArrayList<DataTuple>();
			List<DataTuple> sueDown = new ArrayList<DataTuple>();
			List<DataTuple> sueNeutral = new ArrayList<DataTuple>();
			for(int i = 0; i < tuples.length; i++)
			{
				switch(tuples[i].sueDir) 
				{
				case LEFT:
					sueLeft.add(tuples[i]);
					break;
				case RIGHT:
					sueRight.add(tuples[i]);
					break;
					
				case UP:
					sueUp.add(tuples[i]);
					break;
					
				case DOWN:
					sueDown.add(tuples[i]);
					break;
										
				case NEUTRAL:
					sueNeutral.add(tuples[i]);
					break;
				}
			}
			
			result.add(ListToArray(sueLeft));
			result.add(ListToArray(sueRight));
			result.add(ListToArray(sueUp));
			result.add(ListToArray(sueDown));
			result.add(ListToArray(sueNeutral));
			break;
			
		default:
			System.out.println("ERROR: ATTRIBUTE NOT IN ENUM");
			break;
			
		}
		return result;
	}
	
	private DataTuple[] ListToArray(List<DataTuple> list)
	{
		DataTuple[] array = new DataTuple[list.size()];
		for(int i = 0; i < list.size(); i++)
		{
			array[i] = list.get(i);
		}
		return array;
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


