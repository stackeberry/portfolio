package DiGraph_A5;

import java.util.ArrayList;

public class Vertex implements Comparable<Vertex>{

	private long id;
	private ArrayList<Edge> outEdges = new ArrayList<Edge>();
	private ArrayList<Edge> inEdges = new ArrayList<Edge>();
	private String name;
	private long distance;
	private boolean visited;
	
	public Vertex(String name, long id) {
		this.name = name;
		this.id = id;
		visited = false;
	}
	
	public void addOutEdge(Edge e) {
		outEdges.add(e);
	}
	
	public void addInEdge(Edge e) {
		inEdges.add(e);
	}
	
	public long getId() {
		return id;
	}
	
	public ArrayList<Edge> getOutEdges() {
		return outEdges;
	}
	
	public ArrayList<Edge> getInEdges() {
		return inEdges;
	}
	
	public String getName() {
		return name;
	}
	
	
	public long getDistance() {
		return distance;
	}
	
	public void setDistance(long x) {
		distance = x;
	}
	

	public int compareTo(Vertex o) {
		if (distance == o.getDistance()) {
			return 1;
		}
		return 0;
	}

	public boolean getVisited() {
		return visited;
	}
	
	public void setVisited(boolean t) {
		visited = t;
	}
}
