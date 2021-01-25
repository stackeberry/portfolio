package DiGraph_A5;

public class Edge {

	private long id;
	private Vertex start;
	private Vertex end;
	private long weight;
	
	public Edge(Vertex s, Vertex d, long id, long weight) {
		start = s;
		end = d;
		this.id = id;
		this.weight = weight;
	}
	
	public void setWeight(long w) {
		weight = w;
	}
	
	public Vertex getStart() {
		return start;
	}
	
	public Vertex getEnd() {
		return end;
	}
	
	public long getId() {
		return id;
	}
	
	public long getWeight() {
		return weight;
	}
}
