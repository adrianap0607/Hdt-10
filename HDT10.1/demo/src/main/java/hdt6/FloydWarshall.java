package hdt6;
import java.util.*;

public class FloydWarshall {
    private final int INF = Integer.MAX_VALUE;

    /**
     * Aplica el algoritmo de Floyd-Warshall para encontrar las rutas más cortas.
     * 
     * @param graph La matriz de adyacencia del grafo.
     * @return La matriz de distancias más cortas.
     */
    public int[][] floydWarshall(int[][] graph) {
        int V = graph.length;
        int[][] dist = new int[V][V];
        int[][] next = new int[V][V];

        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                dist[i][j] = graph[i][j];
                if (graph[i][j] != INF && i != j) {
                    next[i][j] = j;
                } else {
                    next[i][j] = -1;
                }
            }
        }

        for (int k = 0; k < V; k++) {
            for (int i = 0; i < V; i++) {
                for (int j = 0; j < V; j++) {
                    if (dist[i][k] != INF && dist[k][j] != INF && dist[i][k] + dist[k][j] < dist[i][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                        next[i][j] = next[i][k];
                    }
                }
            }
        }
        return dist;
    }

}
