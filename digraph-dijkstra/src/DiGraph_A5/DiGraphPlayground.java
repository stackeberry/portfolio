package DiGraph_A5;

public class DiGraphPlayground {

  public static void main (String[] args) {
	  DiGraph d = new DiGraph();
	  
	  
	  d.addNode(0,"a");
	  d.addNode(1,"b");
	  d.addNode(2,"c");


	  d.addEdge(0, "a", "b", 3, null);
	  d.addEdge(1, "a", "c", 5, null);
	  d.addEdge(2, "b", "c", 4, null);
	  
	  d.shortestPath("a");
      // thorough testing is your responsibility
      //
      // you may wish to create methods like 
      //    -- print
      //    -- sort
      //    -- random fill
      //    -- etc.
      // in order to convince yourself your code is producing
      // the correct behavior
    }
  
    public static void exTest(){
      DiGraph d = new DiGraph();
      d.addNode(1, "f");
      d.addNode(3, "s");
      d.addNode(7, "t");
      d.addNode(0, "fo");
      d.addNode(4, "fi");
      d.addNode(6, "si");
      d.addEdge(0, "f", "s", 0, null);
      d.addEdge(1, "f", "si", 0, null);
      d.addEdge(2, "s", "t", 0, null);
      d.addEdge(3, "fo", "fi", 0, null);
      d.addEdge(4, "fi", "si", 0, null);
      System.out.println("numEdges: "+d.numEdges());
      System.out.println("numNodes: "+d.numNodes());
    }
}