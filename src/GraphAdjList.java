import java.util.Iterator;
import java.util.LinkedList;


// IMPORTS 
/**
 * Graph implementation that uses Adjacency Lists to store edges. It
 * contains one linked-list for every vertex i of the graph. The list for i
 * contains one instance of VertexAdjList for every vertex that is adjacent to i.
 * For directed graphs, if there is an edge from vertex i to vertex j then there is
 * a corresponding element in the adjacency list of node i (only). For
 * undirected graphs, if there is an edge between vertex i and vertex j, then there is a
  corresponding element in the adjacency lists of both* vertex i and vertex j. The
  edges are not sorted; they contain the adjacent nodes in order* of
 * edge insertion. In other words, for a graph, the node at the head of
 * the list of some vertex i corresponds to the edge involving i that was
 * added to the graph least recently (and has not been removed, yet). 
 */

public class GraphAdjList  implements Graph {

	// ATTRIBUTES: 
	private LinkedList<VertexAdjList>[] edges; // The array of the linked lists with the adjacency edges.
	private int V; // number of vertices
	private int E; // number of edges
	private boolean directed;  // is the graph directed or not ?
	private int EdgeCount=0;


	/*
	 * CONSTRUCTOR: Creates a directed/undirected graph with V vertices and no edges.
	 * It initializes the array of adjacency edges so that each list is empty.
	 */
	public GraphAdjList(int v,boolean b) {
		this.V = v;
		this.directed = b;

		edges = new LinkedList[V];       					// Creates space for the array
		{
			for(int i=0; i<V; i++) {       					// Go through the array of v
				edges[i] = new LinkedList<VertexAdjList>(); 	// Creates space for the LinkedList in the array if less then 5
			}
		}
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
		boolean exists = false;						// Create a boolean variable and set it as false
		if(v1 < V && v2 < V){						// Check if v1 and v2 are valid and if there is an edge between the two
			Iterator<VertexAdjList> itr = edges[v1].iterator();	// Pointer to collection/looping through the collection linkedList corresponding to location v1
			VertexAdjList vertex=null;				// Set the variable of type VertexAdjList to null used to store the object of VAL that has edge v1
			while(itr.hasNext()){					// If there is next element in the linkedList
				vertex = (VertexAdjList) itr.next();	// Extract it and save it to vertex variable
				if(vertex.v == v2){					// Check if the current iterated VAL from LinkedList is equal to the input v2
					exists = true;					// When we find it, we update our boolean flag to true indicating that there is an edge  between v1 and v2
					break;							// Break from the loop
				}
			}
			if(!exists){								// If they don't exist
				if(directed){						// For directed graph
					edges[v1].add(new VertexAdjList(v2, w));      	// Add the new edge
					EdgeCount++;
				}else{
					edges[v1].add(new VertexAdjList(v2, w));	// Undirected added twice
					edges[v2].add(new VertexAdjList(v1, w));      
					EdgeCount++;
				}
			}else{									// If edge exists
				if(directed){						// For directed
					edges[v1].remove(vertex);				// Remove the v from the linkedList
					edges[v1].add(new VertexAdjList(v2, w)); // Add a new vertex with new weight
				}else{
					edges[v1].remove(vertex);				// Remove what's there 
					itr = edges[v2].iterator();				// Iterate through linkedList that represent v2
					while(itr.hasNext()){					// If there is next element
						vertex = (VertexAdjList) itr.next();	// Extract it and save it to vertex
						if(vertex.v == v1)					
							break;
					}
					edges[v2].remove(vertex);				// Remove the vertexes
					edges[v1].add(new VertexAdjList(v2, w));	
					edges[v2].add(new VertexAdjList(v1, w));
				}
			}
		}
	}

	// 4. IMPLEMENTATION METHOD removeEdge: 
	@Override
	public void removeEdge(int v1, int v2) {
		try {
			//checks if edge exists
			boolean exists = false;						// Create a boolean variable and set it as false to tell that an edge does not exist
			if(v1 < V && v2 < V){						// Check if v1 and v2 are valid and if there is an edge between the two
				Iterator<VertexAdjList> itr = edges[v1].iterator();	// Pointer to collection/looping through the collection linkedList. Extract location v1
				VertexAdjList vertex=null;				
				while(itr.hasNext()){					// If there is next element in the linkedList
					vertex = (VertexAdjList) itr.next();	// Extract it and save it to vertex
					if(vertex.v == v2){					// If they are both the same
						exists = true;					// Exist is set to true
						break;
					}
				}
				if(exists){							// If it exists 
					if(directed){					// For directed graphs
						edges[v1].remove(vertex);	// Remove that vertex
						EdgeCount--;
					}else{
						edges[v1].remove(vertex);		// For undirected remove what is there
						itr = edges[v2].iterator();		// Iterate through linkedList that represent v2
						while(itr.hasNext()){			// If there is next element
							vertex = (VertexAdjList) itr.next();	// Extract it and save it to vertex
							if(vertex.v == v1)
								break;
						}
						edges[v2].remove(vertex);		// Remove that vertex
						EdgeCount--;
					}
				} else{
					System.out.println("The edge "+v1+"-->"+v2+" does not exist!");
				}
			}else{
				System.out.println("The edge "+v1+"-->"+v2+" does not exist!");
			}
		}
		catch(Exception e) {
			System.out.println("Size of graph is "+ V + ". Invalid vertex to add edge between!" );
		}

	}

	// 5. IMPLEMENTATION METHOD hasEdge:
	@Override
	public boolean hasEdge(int v1, int v2) {
		Iterator<VertexAdjList> it = edges[v1].iterator();	// Goes through LunkedList that is pointed by v1
		while (it.hasNext())
			if (it.next().v == v2)							// If it is found return true
				return true;
		return false;  
	}


	// 6. IMPLEMENTATION METHOD getWeightEdge:
	@Override
	public int getWeightEdge(int v1, int v2) {
		if(v1 < V && v2 < V){							// Check if edge is valid from v1 to v2
			Iterator<VertexAdjList> itr = edges[v1].iterator();		// Iterate through edges of v1
			VertexAdjList vertex=null;					// Create vertex variable name to represent VertexAdjList object
			while(itr.hasNext()){
				vertex = (VertexAdjList) itr.next(); 	// Check if the vertex is equal to v2
				if(vertex.v == v2){					
					return vertex.w;
				}
			}
		}
		return 0;									// don't return anything
	}

	// 7. IMPLEMENTATION METHOD getNeighbors:
	@Override
	public LinkedList getNeighbors(int v) {
		if(v < V)									// Check if vertex is a valid vertex - if the LinkedList is corresponding to vertex v 
			if(!edges[v].isEmpty())					//  return number of neighbors given the edges is not empty
				return edges[v];
		return null;
	}

	// 8. IMPLEMENTATION METHOD getDegree:
	@Override
	public int getDegree(int v) {					
		if(v < V)
			if(!edges[v].isEmpty())
				return edges[v].size();			// return number of edges given the edges is not empty
		return 0;								//else return 0;
	}


	// 9. IMPLEMENTATION METHOD toString:

	public String toString() {
		String s = "";
		for (int i = 0; i < V; i++) {
			s +=i + ": ";
			for (Iterator<VertexAdjList> iter = edges[i].iterator(); iter.hasNext();)  // Iterator loops through elements of the list
				s += " " + iter.next();
			s += "\n";
		}
		return s;
	}

}