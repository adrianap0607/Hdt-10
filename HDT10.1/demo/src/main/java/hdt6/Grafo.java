package hdt6;

import java.util.*;

public class Grafo {
    private final int INF = Integer.MAX_VALUE;
    private int[][] adjMatrix;
    private int[][] next;
    private List<String> ciudades;
    private Map<String, Integer> ciudadIndexMap;

    public Grafo() {
        adjMatrix = new int[0][0];
        next = new int[0][0];
        ciudades = new ArrayList<>();
        ciudadIndexMap = new HashMap<>();
    }

    public void agregarArco(String ciudad1, String ciudad2, int distancia) {
        if (!ciudadIndexMap.containsKey(ciudad1)) {
            ciudadIndexMap.put(ciudad1, ciudades.size());
            ciudades.add(ciudad1);
            actualizarMatrices();
        }
        if (!ciudadIndexMap.containsKey(ciudad2)) {
            ciudadIndexMap.put(ciudad2, ciudades.size());
            ciudades.add(ciudad2);
            actualizarMatrices();
        }
        int i = ciudadIndexMap.get(ciudad1);
        int j = ciudadIndexMap.get(ciudad2);
        adjMatrix[i][j] = distancia;
        next[i][j] = j;
    }

    public void eliminarArco(String ciudad1, String ciudad2) {
        if (ciudadIndexMap.containsKey(ciudad1) && ciudadIndexMap.containsKey(ciudad2)) {
            int i = ciudadIndexMap.get(ciudad1);
            int j = ciudadIndexMap.get(ciudad2);
            adjMatrix[i][j] = INF;
            next[i][j] = -1;
        }
    }

    public int[][] getAdjMatrix() {
        return adjMatrix;
    }

    public List<String> getCiudades() {
        return ciudades;
    }

    public Map<String, Integer> getCiudadIndexMap() {
        return ciudadIndexMap;
    }

    private void actualizarMatrices() {
        int size = ciudades.size();
        int[][] newAdjMatrix = new int[size][size];
        int[][] newNext = new int[size][size];
        for (int i = 0; i < size; i++) {
            Arrays.fill(newAdjMatrix[i], INF);
            Arrays.fill(newNext[i], -1);
            newAdjMatrix[i][i] = 0;
        }
        for (int i = 0; i < adjMatrix.length; i++) {
            System.arraycopy(adjMatrix[i], 0, newAdjMatrix[i], 0, adjMatrix[i].length);
            System.arraycopy(next[i], 0, newNext[i], 0, next[i].length);
        }
        adjMatrix = newAdjMatrix;
        next = newNext;
    }
}
