/**
 * This class implements an element of an adjacency linked-list in the adjacency
 * lists graph representation.
 */

public class VertexAdjList {

	//ATTRIBUTES

	// The v vertex.
	public int v; 

	/**
	 * The weight of the corresponding edge (i.e., the edge from the node whose
	 * adjacency list this VertexAdjList belongs to, to the v node).
	 */
	public int w;

	// CONSTRUCTOR
	public VertexAdjList (int v, int w) {
		this.v = v;
		this.w = w;

	}



	// IMPLEMENTATION METHOD toString: The method is used to get a String object representing the VertexAdjList.
	// You have freedom for representing the string that describes the object, but it should contain the two attributes. 
	public String toString() {
		return "(" + v + "; w:" +w + ")";
	}


}
