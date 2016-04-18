
package ska.coms535.WikiCrawler;

/**
 * @author Shubham (tech.shubham@gmail.com)
 *
 */
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;



public class PageRank 
{
	private String filename;
	private double epsilon;
	private static int totalVertices;
	private int edgeCount;
	private Map<String, Integer> mapVertexIndex;
	int[][] graph;

	public PageRank(String filename, double d) throws NumberFormatException, IOException
	{
		this.filename = filename;
		this.epsilon = d;
		this.mapVertexIndex = new HashMap<String, Integer>();
		generateGraph();
	}

	/**
	 * Generates the adjacency matrix of the graph and counts its edges.
	 * @throws NumberFormatException
	 * @throws IOException
	 */
	public void generateGraph() throws NumberFormatException, IOException
	{
		int currIndex = 0;
		System.out.println(filename);
		BufferedReader br = new BufferedReader(new FileReader(filename));
		boolean firstLine = true;
		this.totalVertices = Integer.parseInt(br.readLine());
		graph = new int[totalVertices][totalVertices];
		for(String line; (line = br.readLine()) != null; )
		{
			System.out.println(line);
			edgeCount++;
			String[] links = line.split("\\s");
			int fromLinkIndex = -1;
			int toLinkIndex = -1;
			if(mapVertexIndex.containsKey(links[0])){
				fromLinkIndex = mapVertexIndex.get(links[0]);
			}else{
				fromLinkIndex = currIndex;
				mapVertexIndex.put(links[0], currIndex);
				currIndex++;
			}

			if(mapVertexIndex.containsKey(links[1])){
				toLinkIndex = mapVertexIndex.get(links[1]);
			}else{
				toLinkIndex = currIndex;
				mapVertexIndex.put(links[1], currIndex);
				currIndex++;
			}
			/*
			 * M[i][j] = 1, if there is a link from j to i 
			 */
			graph[toLinkIndex][fromLinkIndex] = 1;
		}   
	}

	/**
	 * Gets name of vertex of the graph as parameter and returns its page rank.
	 * @param vertex
	 * @return pageRank
	 */
	public double pageRankOf(String vertex)
	{
		double[] P0 = new double[totalVertices];
		for(int i=0; i<totalVertices; i++){
			P0[i] = 1.0/totalVertices;
		}
		double[] Pn = P0;
		boolean converged = false;
		int count= 0;
		while(!converged)
		{
			Pn = randomWalk(P0);
			count++;
			if(norm(Pn, P0) <= epsilon){
				System.out.println("Count = "+count);
				converged = true;
			}
			else{
				P0 = Pn;
			}
		}		
		return Pn[mapVertexIndex.get(vertex)];
	}

	/**
	 * Simulates one step of a the random walk in page rank
	 * @param p0
	 * @return
	 */
	private double[] randomWalk(double[] p0)
	{
		double Pn[] =  new double[totalVertices];

		double beta = 0.85; 
		for(int i=0; i<totalVertices; i++)
			Pn[i] = (1.0-beta)/totalVertices;
		for(String vertex: mapVertexIndex.keySet())
		{
			int index = mapVertexIndex.get(vertex);
			int modQ = outDegreeOf(vertex);
			if(modQ!=0)
			{
				for(int i=0; i<totalVertices; i++)
					if(graph[i][index]!=0)
						Pn[i] +=(beta*(p0[index]/modQ));
			}
			else
			{
				for(int i=0; i<totalVertices; i++)
					Pn[i] +=(beta*(p0[index]/totalVertices));
			}
		}

		return Pn;
	}

	/**
	 * Calculates the norm of two vectors
	 * @param pNext
	 * @param P
	 * @return
	 */
	public static double norm(double[] pNext, double[] P){

		double norm = 0.0;
		for (int i=0; i<totalVertices; i++)       
			norm += Math.abs(P[i] - pNext[i]);
		return norm;
	}


	/**
	 * Gets name of vertex of the graph as parameter and returns its out degree.
	 * @param vertex
	 * @return outDegree;
	 * @throws FileNotFoundException 
	 */
	public int outDegreeOf(String vertex) 
	{
		/*
		 * Since, graph[i][j] = 1, if there is a link from j to i
		 * To calculate outdegree of node at vertex 'j' we can sum up
		 * all values in column 'j'  
		 */
		int verIndex = mapVertexIndex.get(vertex);
		int outDegree = 0;
		for(int i=0; i < totalVertices; i++){
			outDegree += graph[i][verIndex];
		}
		return outDegree;
	}

	/**
	 * Gets name of vertex of the graph as parameter and returns its in degree.
	 * @param vertex
	 * @return inDegree
	 * @throws FileNotFoundException 
	 */
	public int inDegreeOf(String vertex)
	{
		/*
		 * Since, graph[i][j] = 1, if there is a link from j to i
		 * To calculate indegree of node at vertex 'i' we can sum up
		 * all values in row 'i'  
		 */
		int verIndex = mapVertexIndex.get(vertex);
		int inDegree = 0;
		for(int i=0; i < totalVertices; i++){
			inDegree += graph[verIndex][i];
		}
		return inDegree;	
	}

	/**
	 * Returns the number of edges of a graph
	 * @return numEdges
	 */
	public int numEdges()
	{
		return edgeCount;
	}

	/**
	 * Gets an integer k as a parameter and returns an array (of strings) of pages with top k page ranks.
	 * @param k
	 * @return topKPageRank[]
	 */
	public String[] topKPageRank(int k)
	{
		String[] retArr = new String[k];
		Node[] arrNodes = new Node[totalVertices];
		int iter =0 ;
		for(String toLink : mapVertexIndex.keySet())
			arrNodes[iter++] = new Node(toLink, pageRankOf(toLink));
		Arrays.sort(arrNodes, new NodeComparator());
		int index = arrNodes.length - 1;
		for(int i = 0; i < k  ; i++)
			retArr[i] = arrNodes[index--].getName();
		return retArr;
	}

	/**
	 * Gets an integer k as a parameter and returns an array (of strings) of pages with top k in degree.
	 * @param k
	 * @return topKInDegree[]
	 * @throws FileNotFoundException 
	 */
	public String[] topKInDegree(int k) 
	{
		String[] retArr = new String[k];
		Node[] arrNodes = new Node[totalVertices];
		int iter =0 ;
		for(String toLink : mapVertexIndex.keySet())
			arrNodes[iter++] = new Node(toLink, inDegreeOf(toLink));
		Arrays.sort(arrNodes, new NodeComparator());
		int index = arrNodes.length - 1;
		for(int i = 0; i < k  ; i++)
			retArr[i] = arrNodes[index--].getName();
		return retArr;
	}

	/**
	 * Gets an integer k as a parameter and returns an array (of strings) of pages with top k out degree.
	 * @param k
	 * @return topKOutDegree[]
	 * @throws FileNotFoundException 
	 */
	public String[] topKOutDegree(int k) 
	{
		String[] retArr = new String[k];
		Node[] arrNodes = new Node[totalVertices];
		int iter =0 ;
		for(String fromLink : mapVertexIndex.keySet())
			arrNodes[iter++] = new Node(fromLink, outDegreeOf(fromLink));
		Arrays.sort(arrNodes, new NodeComparator());
		int index = arrNodes.length - 1;
		for(int i = 0; i < k  ; i++)
			retArr[i] = arrNodes[index--].getName();
		return retArr;
	}

	private class Node 
	{
		private String name;
		private int degree;
		private double pageRank;
		private boolean forDegree;

		Node(String name, int degree)
		{
			this.name = name;
			this.degree = degree;
			this.forDegree = true;
		}

		Node(String name, double pageRank){
			this.name = name;
			this.pageRank = pageRank;
			this.forDegree = false;
		}

		String getName()
		{
			return name;
		}

		int getDegree()
		{
			return degree;
		}

		boolean isDegree(){
			return forDegree;
		}

		double getPageRank(){
			return pageRank;
		}
	}


	private class NodeComparator implements Comparator<Node>
	{
		public int compare(Node node1, Node node2) 
		{
			if(node1.isDegree() == node2.isDegree()){
				if(node1.isDegree()){
					if(node1.getDegree() > node2.getDegree())
						return 1;
					else if ( node1.getDegree() == node2.getDegree())
						return 0;
					else
						return -1;
				}else{
					if(node1.getPageRank() > node2.getPageRank())
						return 1;
					else if ( node1.getPageRank() == node2.getPageRank())
						return 0;
					else
						return -1;
				}
			}else{
				return -1;
			}
		}

	}
}