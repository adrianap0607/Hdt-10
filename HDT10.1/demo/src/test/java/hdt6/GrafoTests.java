package hdt6;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import java.util.List;

public class GrafoTests {

    @Test
    public void testEncontrarCentro_GrafoConUnaCiudad() {
        CentroGrafo centroGrafo = new CentroGrafo();
        int[][] distancias = {{0}};
        assertEquals(0, centroGrafo.encontrarCentro(distancias));
    }

    @Test
    public void testEncontrarCentro_GrafoConTresCiudades() {
        CentroGrafo centroGrafo = new CentroGrafo();
        int[][] distancias = {
            {0, 2, 3},
            {2, 0, 4},
            {3, 4, 0}
        };
        assertEquals(0, centroGrafo.encontrarCentro(distancias));
    }

    @Test
    public void testFloydWarshall_GrafoConTresCiudades() {
        FloydWarshall floydWarshall = new FloydWarshall();
        int[][] graph = {
            {0, 2, 6},
            {Integer.MAX_VALUE, 0, 7},
            {Integer.MAX_VALUE, Integer.MAX_VALUE, 0}
        };
        int[][] result = floydWarshall.floydWarshall(graph);
        
        assertEquals(0, result[0][0]);
        assertEquals(2, result[0][1]);
        assertEquals(6, result[0][2]);
        assertEquals(Integer.MAX_VALUE, result[1][0]);
        assertEquals(0, result[1][1]);
        assertEquals(7, result[1][2]);
        assertEquals(Integer.MAX_VALUE, result[2][0]);
        assertEquals(Integer.MAX_VALUE, result[2][1]);
        assertEquals(0, result[2][2]);
    }

   

    // Puedes agregar más casos de prueba según sea necesario

    @Test
    public void shouldAnswerWithTrue() {
        assertTrue(true);
    }
}
