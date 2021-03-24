import java.util.*;

public class Main {
  static final int V = 6;

 
    static boolean bfs(int Graph[][], int s, int t, int p[]) {
    boolean visited[] = new boolean[V];
    for (int i = 0; i < V; ++i)
      visited[i] = false;

    LinkedList<Integer> queue = new LinkedList<Integer>();
    queue.add(s);
    visited[s] = true;
    p[s] = -1;

    while (queue.size() != 0) {
      int u = queue.poll();

      for (int v = 0; v < V; v++) {
        if (visited[v] == false && Graph[u][v] > 0) {
          queue.add(v);
          p[v] = u;
          visited[v] = true;
        }
      }
    }

    return (visited[t] == true);
  }

  int maxflow(int graph[][], int s, int t) {
    int u, v;
    int Graph[][] = new int[V][V];

    for (u = 0; u < V; u++)
      for (v = 0; v < V; v++)
        Graph[u][v] = graph[u][v];

    int p[] = new int[V];

    int max_flow = 0;

  
    while (bfs(Graph, s, t, p)) {
      int path_flow = Integer.MAX_VALUE;
      for (v = t; v != s; v = p[v]) {
        u = p[v];
        path_flow = Math.min(path_flow, Graph[u][v]);
      }

      for (v = t; v != s; v = p[v]) {
        u = p[v];
        Graph[u][v] -= path_flow;
        Graph[v][u] += path_flow;
      }

 
      max_flow += path_flow;
    }

    return max_flow;
  }
  private static String minCut(int[][] graph, int s, int t) {
        int u,v;
          
        
        int[][] rGraph = new int[graph.length][graph.length]; 
        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph.length; j++) {
                rGraph[i][j] = graph[i][j];
            }
        }
  
        int[] parent = new int[graph.length]; 
        
        while (bfs(rGraph, s, t, parent)) {
              
           
            int pathFlow = Integer.MAX_VALUE;         
            for (v = t; v != s; v = parent[v]) {
                u = parent[v];
                pathFlow = Math.min(pathFlow, rGraph[u][v]);
            }
            for (v = t; v != s; v = parent[v]) {
                u = parent[v];
                rGraph[u][v] = rGraph[u][v] - pathFlow;
                rGraph[v][u] = rGraph[v][u] + pathFlow;
            }
        }
        boolean[] isVisited = new boolean[graph.length];     
        dfs(rGraph, s, isVisited);
        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph.length; j++) {
                if (graph[i][j] > 0 && isVisited[i] && !isVisited[j]) {
                    return (i + " - " + j);
                }
            }
        }
        return null; 
    }
    private static void dfs(int[][] rGraph, int s,
                                boolean[] visited) {
        visited[s] = true;
        for (int i = 0; i < rGraph.length; i++) {
                if (rGraph[s][i] > 0 && !visited[i]) {
                    dfs(rGraph, i, visited);
                }
        }
    }
  public static void main(String[] args) throws java.lang.Exception {
    int graph[][] = new int[][] { { 0, 8, 0, 0, 3, 0 }, { 0, 0, 9, 0, 0, 0 }, { 0, 0, 0, 0, 7, 2 },
        { 0, 0, 0, 0, 0, 5 }, { 0, 0, 7, 4, 0, 0 }, { 0, 0, 0, 0, 0, 0 } };
    Main m = new Main();

    System.out.println("Max Flow: " + m.maxflow(graph, 0, 5));
    System.out.println("Min Cut:  " + m.minCut(graph, 0, 5));

  }
}
