package hdt6;

import java.util.*;

public class CentroGrafo {
    /**
     * Encuentra el centro del grafo.
     * 
     * @param dist La matriz de distancias más cortas.
     * @return El índice del nodo que es el centro del grafo.
     */
    public int encontrarCentro(int[][] dist) {
        int V = dist.length;
        int[] excentricidades = new int[V];
        Arrays.fill(excentricidades, Integer.MIN_VALUE);

        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                if (dist[i][j] != Integer.MAX_VALUE && dist[i][j] > excentricidades[i]) {
                    excentricidades[i] = dist[i][j];
                }
            }
        }

        int centro = 0;
        int minExcentricidad = Integer.MAX_VALUE;
        for (int i = 0; i < V; i++) {
            if (excentricidades[i] < minExcentricidad) {
                minExcentricidad = excentricidades[i];
                centro = i;
            }
        }
        return centro;
    }
}
