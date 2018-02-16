import java.util.LinkedList;

// IMPORTS 

/*
 *  Implementation of the interface Graph with adjacency matrix.
 */


public class GraphAdjMatrix implements Graph{

	// ATTRIBUTES: 
	private int[][] adj; // 2-dimensional array representing the edges. 
	private int V; // number of vertices
	//private int E; // number of edges
	private boolean directed;  // is the graph directed or not ?
	private int EdgeCount=0;


	// CONSTRUCTOR: Creates a directed/undirected graph with V vertices and no edges
	public GraphAdjMatrix(int v, boolean directed) {
		this.V = v;
		this.adj = new int[V][V];
		this.directed = directed;
	}

	// 1. IMPLEMENTATION METHOD numVerts: 
	@Override
	public int numVerts() {
		return V;
	}

	// 2. IMPLEMENTATION METHOD numEdges:
	@Override
	public int numEdges() {
		return EdgeCount;

	}

	//  3. IMPLEMENTATION METHOD addEdge:
	@Override
	public void addEdge(int v1, int v2, int w) {
		//directed
		try{
			if(directed == true) {			// If directed
				if(v1 >= V || v2 >= V) {		// If it is bigger than V/5
					System.out.println("The edge "+v1+"-->"+v2+" does not exist!");
				}
				else {
					if(adj[v1][v2] == 0)		// If empty
						EdgeCount++;			// Increment EdgeCount and add he weight
					adj[v1][v2] = w;
				}
			}
			else {
				if(v1 >= V || v2 >= V) {		// Undirected
					System.out.println("The edge "+v1+"-->"+v2+" does not exist!");
				}
				else {
					if(adj[v1][v2] == 0) {
						EdgeCount++;
						adj[v1][v2] = w;
						adj[v2][v1] = w;
					}
				}
			}

		}
		catch(Exception e) {
			System.out.println("\nError");
		}
	}

	// 4. IMPLEMENTATION METHOD removeEdge: 
	@Override
	public void removeEdge(int v1, int v2) {

		try{
			if(directed == true) {
				if(adj[v1][v2] != 0) {		// If arrays are not empty
					adj[v1][v2] = 0;			// Set them to 0 and decrement
					EdgeCount--;
				}
			}
			else {
				//Undirected
				if(adj[v1][v2] != 0) {
					adj[v1][v2] = 0;
					adj[v2][v1] = 0;
					EdgeCount--;

				}
			}

		}
		catch(Exception e) {
			System.out.println("Error");
		}
	}


	// 5. IMPLEMENTATION METHOD hasEdge:
	@Override
	public boolean hasEdge(int v1, int v2) {
		if(adj[v1][v2] > 0) {				// if there is an edge between v1 and v2 return true
			return true;
		}
		else {
			return false;
		}
	}

	// 6. IMPLEMENTATION METHOD getWeightEdge:
	@Override
	public int getWeightEdge(int v1, int v2) {
		int a = 0;
		if(adj[v1][v2] > 0)			// If there is an edge
			a = adj[v1][v2];			// add it to the variable a
		else 
			System.out.print("Edge is not there");
		return a;
	}

	// 7. IMPLEMENTATION METHOD getNeighbors:
	@Override
	public LinkedList<Integer> getNeighbors(int v) {
		LinkedList<Integer> neighbors = new LinkedList<Integer>();		// Create a LinkedList of type Integer called neighbors
		for(int v2=0; v2<V; v2++){										// loop through all the columns and check if there is an edge
			if(v != v2){													// Check v2 is not the same as the value we give it
				if(adj[v][v2] != 0){
					neighbors.add(v2);									// Add that vertices to the neighbors LinkedList
				}
			}
		}
		return neighbors;
	}

	// 8. IMPLEMENTATION METHOD getDegree:
	public int getDegree(int v) {
		int degree= 0;				// set the degree to 0		
		for(int v2=0; v2<V; v2++){	// iterate through all the columns and check if there is and edge
			if(adj[v][v2]!=0)		// if there is an edge 
			{
				degree= degree+ 1;	//!!!!!!!!!!!!!!!!Update the degree 
			}
		}
		return degree;
	}

	// 9. IMPLEMENTATION METHOD toString:
	public String toString() {
		String ans = "";
		System.out.println("");
		for(int i=0; i<V; i++) {
			for(int j=0; j<V; j++) {
				ans += "" + Integer.toString(adj[i][j]) + " ";
			}
			ans+="\n";
		}
		return ans;
	}

}