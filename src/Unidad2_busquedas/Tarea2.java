/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Unidad2_busquedas;
import java.util.Random;

/**
 *
 * @author lucas
 */


    /**
     * @param args the command line arguments
     */
   /**
 * Tarea 2 Unidad 2 Grupo G13
 * Integrantes                 Ci  Seccion     
 * Lucas Vallejos       5439993     TR
 * Iván Figueredo       6743661     TR
 */

/*
    *Procesador                     
    *intel core i3-1125G4 2.00GHz
*
    *Ram
    *8 GB 
*
    *Sistema operativo
    *Windows 11
*
    *Version de Java 
*   java 17
*/

/*
 T/N:
    -Para la búsqueda binaria, el tiempo promedio por elemento es muy bajo 
    y casi constante a medida que aumenta el tamaño de la entrada. Esto indica una eficiencia alta y 
    una búsqueda rápida incluso con entradas más grandes.
    -Para la búsqueda lineal, el tiempo promedio por elemento aumenta gradualmente a medida que aumenta 
    el tamaño de la entrada. Esto muestra una tendencia lineal en el tiempo de ejecución, lo que indica una 
    búsqueda menos eficiente en comparación con la búsqueda binaria.
T/(N*LOG2(N)):

    -Este factor tiene en cuenta el crecimiento logarítmico y muestra que la búsqueda binaria mantiene 
    una eficiencia alta y relativamente constante incluso con entradas más grandes. La línea de 
    tiempo de ejecución sigue una tendencia logarítmica.
    -Para la búsqueda lineal, este factor muestra un aumento significativo en el tiempo de ejecución a 
    medida que aumenta el tamaño de la entrada. La línea de tiempo de ejecución sigue una tendencia lineal, lo que 
    indica una búsqueda menos eficiente en comparación con la búsqueda binaria.
T/N^2:
    -Este factor muestra cómo el tiempo de ejecución varía con el cuadrado del tamaño de la entrada. Para la 
    búsqueda binaria, el tiempo de ejecución apenas muestra un aumento, lo que indica una eficiencia cuadrática 
    muy baja.
    -En el caso de la búsqueda lineal, el tiempo de ejecución aumenta significativamente con el cuadrado del tamaño 
    de la entrada, lo que indica una eficiencia cuadrática en la búsqueda lineal.

En resumen, los resultados confirman que la búsqueda binaria es considerablemente más eficiente que la búsqueda lineal
para grandes conjuntos de datos, ya que muestra un tiempo de ejecución constante y un crecimiento logarítmico en
comparación con el crecimiento lineal y cuadrático de la búsqueda lineal.

*/
public class Tarea2{
    private static final int MIN_N = 50000;
    private static final int MAX_N = 1000000;
    private static final int INCREMENT = 50000;
    
    // Método para generar un array de tamaño N con números enteros aleatorios
    private static Integer[] generateRandomArray(int N) {
        Integer[] array = new Integer[N];
        Random random = new Random();

        for (int i = 0; i < N; i++) {
            array[i] = random.nextInt(MAX_N - MIN_N + 1) + MIN_N;
        }

        Util.quicksort(array); // Ordenar el array para la búsqueda binaria
        return array;
    }

    // Método para obtener un elemento aleatorio entre 1 y N
    private static int getRandomElement(int N) {
        return new Random().nextInt(N) + 1;
    }

    // Método para medir el tiempo de ejecución de una tarea
    private static long measureTime(Runnable task) {
        long startTime = System.currentTimeMillis();
        task.run();
        long endTime = System.currentTimeMillis();
        return endTime - startTime;
    }

    // Implementación de la búsqueda binaria
    private static <T extends Comparable<T>> Integer binarySearch(T[] array, T target) {
        int low = 0;
        int high = array.length - 1;

        while (low <= high) {
            int mid = (low + high) / 2;
            int cmp = array[mid].compareTo(target);

            if (cmp == 0) {
                return mid; // Elemento encontrado
            } else if (cmp < 0) {
                low = mid + 1; // Buscar en la mitad derecha
            } else {
                high = mid - 1; // Buscar en la mitad izquierda
            }
        }

        return null; // Elemento no encontrado
    }

    // Implementación de la búsqueda lineal
    private static <T> Integer linearSearch(T[] array, T target) {
        for (T array1 : array) {
            if (array1.equals(target)) {
               
            }
        }
        return null; // Elemento no encontrado
    }
    
    
   
    

     public static void main(String[] args) {
        System.out.printf("%-20s%-20s%-20s%-20s%-20s%-20s%-20s%-20s%-20s%n",
                "N", "T(n) en ms", "T/N", "T/(N*LOG2(N))", "T/N^2",
                "T(n) en ms", "T/N", "T/(N*LOG2(N))", "T/N^2");

        for (int xN = MIN_N; xN <= MAX_N; xN += INCREMENT) {
            final int currentN = xN;  // Hacer final o efectivamente final

            Integer[] array = generateRandomArray(currentN);

            long binarySearchTotalTime = 0;
            long linearSearchTotalTime = 0;

            for (int i = 0; i < currentN; i++) {
                int targetElement = getRandomElement(currentN);

                // Búsqueda binaria
                binarySearchTotalTime += measureTime(() -> {
                    binarySearch(array, targetElement);
                });

                // Búsqueda lineal
                linearSearchTotalTime += measureTime(() -> {
                    linearSearch(array, targetElement);
                });
            }

            double binarySearchAverageTime = (double) binarySearchTotalTime / currentN;
            double logaritmicabinary=(double) binarySearchTotalTime/ (currentN * Math.log(currentN) / Math.log(2));
            double exponencialbinary=(double )binarySearchTotalTime/ (currentN * currentN);
            
            double linearSearchAverageTime = (double) linearSearchTotalTime / currentN;
            double logaritmicalineal= (double) linearSearchTotalTime / (currentN * Math.log(currentN) / Math.log(2));
            double exponenciallineal=(double) linearSearchTotalTime / (currentN * currentN);

            System.out.printf("%-20d%-20d%-20f%-20f%-20.8f%-20d%-20f%-20f%-10f%n",
                    currentN,
                    binarySearchTotalTime,
                    binarySearchAverageTime,
                    logaritmicabinary,
                    Math.abs(exponencialbinary),
                    linearSearchTotalTime,
                    linearSearchAverageTime,
                    logaritmicalineal,
                    Math.abs(exponenciallineal) 
            );

            System.out.println();
        }
    }
    
}

