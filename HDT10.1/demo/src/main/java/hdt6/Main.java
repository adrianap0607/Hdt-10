package hdt6;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Grafo grafo = leerGrafoDesdeArchivo("guategrafo.txt");

        if (grafo == null) {
            System.out.println("Error al leer el archivo del grafo. Asegúrese de que el archivo exista y tenga el formato correcto.");
            return;
        }

        FloydWarshall floydWarshall = new FloydWarshall();
        CentroGrafo centroGrafo = new CentroGrafo();

        while (true) {
            System.out.println("Opciones:");
            System.out.println("1. Ruta más corta entre dos ciudades");
            System.out.println("2. Centro del grafo");
            System.out.println("3. Modificar el grafo");
            System.out.println("4. Salir");

            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (opcion) {
                case 1:
                    buscarRutaMasCorta(grafo, floydWarshall, scanner);
                    break;
                case 2:
                    mostrarCentroDelGrafo(grafo, floydWarshall, centroGrafo);
                    break;
                case 3:
                    modificarGrafo(grafo, scanner);
                    break;
                case 4:
                    System.out.println("Saliendo del programa.");
                    scanner.close();
                    return;
                default:
                    System.out.println("Opción inválida.");
            }
        }
    }

    private static Grafo leerGrafoDesdeArchivo(String nombreArchivo) {
        try {
            List<String[]> lineas = new ArrayList<>();
            BufferedReader br = new BufferedReader(new FileReader(nombreArchivo));
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(" ");
                lineas.add(partes);
            }
            br.close();

            Grafo grafo = new Grafo();
            for (String[] partes : lineas) {
                grafo.agregarArco(partes[0], partes[1], Integer.parseInt(partes[2]));
            }

            return grafo;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static void buscarRutaMasCorta(Grafo grafo, FloydWarshall floydWarshall, Scanner scanner) {
        System.out.print("Ciudad origen: ");
        String origen = scanner.nextLine();
        System.out.print("Ciudad destino: ");
        String destino = scanner.nextLine();
        mostrarRutaMasCorta(grafo, floydWarshall, origen, destino);
    }

    private static void mostrarRutaMasCorta(Grafo grafo, FloydWarshall floydWarshall, String origen, String destino) {
    int[][] dist = floydWarshall.floydWarshall(grafo.getAdjMatrix());
    Map<String, Integer> ciudadIndexMap = grafo.getCiudadIndexMap();
    List<String> ciudades = grafo.getCiudades();

    if (!ciudadIndexMap.containsKey(origen) || !ciudadIndexMap.containsKey(destino)) {
        System.out.println("Una o ambas ciudades no existen en el grafo.");
        return;
    }

    int u = ciudadIndexMap.get(origen);
    int v = ciudadIndexMap.get(destino);
    if (dist[u][v] == Integer.MAX_VALUE) {
        System.out.println("No existe un camino entre " + origen + " y " + destino);
    } else {
        System.out.println("La distancia más corta entre " + origen + " y " + destino + " es " + dist[u][v] + " km.");
        System.out.println();
    }
}


    private static void mostrarCentroDelGrafo(Grafo grafo, FloydWarshall floydWarshall, CentroGrafo centroGrafo) {
        int[][] dist = floydWarshall.floydWarshall(grafo.getAdjMatrix());
        int centro = centroGrafo.encontrarCentro(dist);
        List<String> ciudades = grafo.getCiudades();
        System.out.println("El centro del grafo es: " + ciudades.get(centro));
    }

    private static void modificarGrafo(Grafo grafo, Scanner scanner) {
        System.out.println("Opciones de modificación:");
        System.out.println("1. Agregar arco");
        System.out.println("2. Eliminar arco");

        int opcion = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        switch (opcion) {
            case 1:
                agregarArco(grafo, scanner);
                break;
            case 2:
                eliminarArco(grafo, scanner);
                break;
            default:
                System.out.println("Opción inválida.");
        }
    }

    private static void agregarArco(Grafo grafo, Scanner scanner) {
        System.out.print("Ciudad origen: ");
        String ciudad1 = scanner.nextLine();
        System.out.print("Ciudad destino: ");
        String ciudad2 = scanner.nextLine();
        System.out.print("Distancia: ");
        int distancia = scanner.nextInt();
        grafo.agregarArco(ciudad1, ciudad2, distancia);
    }

    private static void eliminarArco(Grafo grafo, Scanner scanner) {
        System.out.print("Ciudad origen: ");
        String ciudad1 = scanner.nextLine();
        System.out.print("Ciudad destino: ");
        String ciudad2 = scanner.nextLine();
        grafo.eliminarArco(ciudad1, ciudad2);
    }
}