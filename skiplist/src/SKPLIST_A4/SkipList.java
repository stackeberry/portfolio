package SKPLIST_A4;

import java.util.Random;

public class SkipList implements SkipList_Interface {
  private SkipList_Node root;
  private final Random rand;
  private double probability;
  private int MAXHEIGHT = 30; // the most links that a data cell may contain
  private int size;

  public SkipList(int maxHeight) {
	if (maxHeight > 30) {
		throw new IllegalArgumentException();
	}
    root = new SkipList_Node(Double.NaN, maxHeight);
    rand = new Random();
    probability = 0.5;
    size = 0;
    MAXHEIGHT = maxHeight;
  }

  public void setSeed(long seed) { rand.setSeed(seed); }
  
   public void setProbability(double probability) { 
	 if (probability < 0 || probability > 1) {
		 throw new IllegalArgumentException();
	 }
     this.probability = probability; 
  }
  
  private boolean flip() {
    // use this where you "roll the dice"
    // call it repeatedly until you determine the level
    // for a new node
    return rand.nextDouble() < probability;
  }
  
  public SkipList_Node getRoot() { return root; }

  public String toString() {
    StringBuilder sb = new StringBuilder();
    
    int levels;
    for(levels = 0; levels < root.getNext().length && root.getNext(levels) != null; levels++);
    
    StringBuilder[] sbs = new StringBuilder[levels];
    
    for(int i = 0; i < sbs.length; i ++) {
      sbs[i] = new StringBuilder();
      sbs[i].append("level ").append(i).append(":");
    }
    
    SkipList_Node cur = root;
    
    while (cur.getNext(0) != null) {
      cur = cur.getNext(0);
      for(int i = levels - 1; i >= cur.getNext().length; i --) {
        sbs[i].append("\t");
      }
      for(int i = cur.getNext().length - 1; i >= 0; i --) {
        if (cur.getNext(i) == null) {
          levels --;
        }
        sbs[i].append("\t").append(cur.getValue());
      }
    }
    
    for(int i = sbs.length - 1; i >= 0; i --) {
      sb.append(sbs[i]).append("\n");
    }
    
    return sb.toString();
  }

	public boolean insert(double value) {
		if (contains(value)) {
			return false;
		}
		
		// create node (use flip to get a height)
		int level = 0;
		while (flip() && level < MAXHEIGHT - 1) {
			level++;
		}
		SkipList_Node node = new SkipList_Node(value, level + 1);
		
		// Find nodes that should connect to inserted node and store in array

		SkipList_Node[] nodes = new SkipList_Node[level + 1];
		
		for (int i = level; i > -1; i--) {
			SkipList_Node compareNode = root;
			// loop through and find the right node
			while (compareNode.getNext(i) != null) {
				if (value < compareNode.getNext(i).getValue()) {
					break;
				} else {
					compareNode = compareNode.getNext(i);
				}
			}
			nodes[i] = compareNode;
		}	
	
		// Attach new node to each node in the Array
		for (int i = 0; i < node.getLevel(); i++) {
			node.setNext(i, nodes[i].getNext(i));
			nodes[i].setNext(i, node);
		}
		size++;
		return true;
	}
	
	
	public boolean remove(double value) {
		if (!contains(value)) {
			return false;
		} else {
			// Find nodes that are connected to node we want to remove
			
			for (int i = level(); i > -1; i--) {
				SkipList_Node compareNode = root;
				// loop through and find the right node
				while (compareNode.getNext(i) != null) {
					if (value == compareNode.getNext(i).getValue()) {
						compareNode.setNext(i, compareNode.getNext(i).getNext(i));
						break;
					} else {
						compareNode = compareNode.getNext(i);
					}
				}
			}
			size--;
			return true;
		}
		
	}
	
	public boolean contains(double value) {
		if (size == 0) {
			return false;
		}
		
		SkipList_Node compareNode = root;
		for (int i = level(); i > -1; i--) {
			do {
				if (compareNode.getNext(i) == null || value < compareNode.getNext(i).getValue()) {
					break;
				} else if (compareNode.getNext(i).getValue() == value) {
					return true;
				}
				compareNode = compareNode.getNext(i);	
			} while (compareNode != null);
		}
		
		return false;
	}	
	
	public double findMin() {
		if (size == 0) {
			return Double.NaN;
		} else {
			return root.getNext(0).getValue();
		}
	}
	
	public double findMax() {
		if (size == 0) {
			return Double.NaN;
		} else {
			SkipList_Node currentNode = root.getNext(0);
			for (; currentNode.getNext(0) != null;) {
				currentNode = currentNode.getNext(0);
			}
			
			return currentNode.getValue();
		}
	}
	
	public boolean empty() {
		if (size == 0) {
			return true;
		} else {
			return false;
		}
	}
	
	public void clear() {
		for (int i = 0; i < MAXHEIGHT; i++) {
			root.setNext(i, null);
		}
		
		size = 0;
	}
	
	public int size() {
		return size;
	}
	
	public int level() {
		for (int i = MAXHEIGHT - 1; i > -1; i--) {
			if (root.getNext(i) != null) {
				return i;
			}
		}
		return -1;
	}
	
	public int max() {
		return MAXHEIGHT;
	}
	  
  
}