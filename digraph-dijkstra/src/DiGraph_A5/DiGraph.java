package DiGraph_A5;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class DiGraph implements DiGraphInterface {

  // in here go all your data and methods for the graph

	private HashMap<String, Vertex> map;
	private HashMap<Long, Vertex> VertexId;
	private HashMap<Long, Edge> EdgeId;
	
  public DiGraph () { // default constructor
    // explicitly include this
    // we need to have the default constructor
    // if you then write others, this one will still be there
	  map = new HashMap<>();
	  VertexId = new HashMap<Long, Vertex>();
	  EdgeId = new HashMap<Long, Edge>();
	  
  }


public boolean addNode(long idNum, String label) {
	if (idNum < 0 || label == null || map.containsKey(label) || VertexId.containsKey(idNum)) {
		return false;
	}
	
	Vertex node = new Vertex(label, idNum);
	VertexId.put(idNum, node);
	map.put(label, node);
	return true;
}


public boolean addEdge(long idNum, String sLabel, String dLabel, long weight, String eLabel) {
	if (idNum < 0 || !map.containsKey(sLabel) || !map.containsKey(dLabel) || EdgeId.containsKey(idNum)) {
		return false;
	}
	
	// Check the starting vertex's edges to see if there is an edge that already exists
	for (int i = 0; i < map.get(sLabel).getOutEdges().size(); i++) {
		if (map.get(sLabel).getOutEdges().get(i).getEnd().getName() == dLabel) {
			return false;
		}
	}
	
	Edge e = new Edge(map.get(sLabel), map.get(dLabel), idNum, weight);

	// Add EdgeId to the arraylist, create new edge, add new edge to the starting vertex in the hashmap
	
	EdgeId.put(idNum, e);
	map.get(sLabel).addOutEdge(e);
	map.get(dLabel).addInEdge(e);
	
	return true;
}


public boolean delNode(String label) {
	if (!map.containsKey(label)) {
		return false;
	}
	
	for (int i = 0; i < map.get(label).getOutEdges().size(); i++) {
		delEdge(label, map.get(label).getOutEdges().get(i).getEnd().getName());
	}
	VertexId.remove(map.get(label).getId());
	map.remove(label);
	return true;
}


public boolean delEdge(String sLabel, String dLabel) {
	if (!map.containsKey(sLabel) || !map.containsKey(dLabel)) {
		return false;
	}
	
	for (int i = 0; i < map.get(sLabel).getOutEdges().size(); i++) {
		if (map.get(sLabel).getOutEdges().get(i).getEnd() == map.get(dLabel) ) {
			EdgeId.remove(map.get(sLabel).getOutEdges().get(i).getId());
			map.get(sLabel).getOutEdges().remove(i);
			map.get(dLabel).getInEdges().remove(i);
			return true;
			}
	}
	return false;
}


public long numNodes() {
	return VertexId.size();
}


public long numEdges() {
	return EdgeId.size();
}


public ShortestPathInfo[] shortestPath(String label) {
	
	ShortestPathInfo[] info = new ShortestPathInfo[VertexId.size()];
	
	Vertex s = map.get(label);
	
	PriorityQueue<Vertex> q = new PriorityQueue<Vertex>(VertexId.size());
	
	for (Vertex v: map.values()) {
		v.setDistance(Integer.MAX_VALUE);
	}
	
	s.setDistance(0);
	q.add(s);
	
	while (!q.isEmpty()) {
		
		Vertex v = q.remove();
		
		for (Edge adj: v.getOutEdges()) {
			
				if (v.getDistance() + adj.getWeight() < adj.getEnd().getDistance()) {
					adj.getEnd().setDistance(v.getDistance() + adj.getWeight());
					if (!q.contains(adj.getEnd())) {
						q.add(adj.getEnd());
					}
				}
		}
		v.setVisited(true);
	}
	
	// store name of each vertex and its weight in array and return
	int i = 0;
	for (Vertex v: map.values()) {
		if (v.getVisited()) {
			info[i] = new ShortestPathInfo(v.getName(), v.getDistance());
			i++;
		} else {
			info[i] = new ShortestPathInfo(v.getName(), -1);
			i++;
		}
	}
	
	return info;
	
}
  
}