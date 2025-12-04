/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Unidad3.Busquedas;

/**
 *
 * @author lucas
 */
/*
 Ejercicio 2 Item A)
  a) Dado el código de RadixSort en SL presentado aquí:
    a.1) Mencione que restricciones impone esta implementación (en SL).
        -Tamaño máximo de los elementos: La implementación utiliza un vector de tamaño fijo de 1500
        para almacenar los elementos de cada dígito. Esto significa que el algoritmo 
        no puede manejar elementos con más de 1500 dígitos.
        -Tipo de datos: La implementación solo funciona con tipos de datos numéricos. 
        Si se intenta usar con tipos de datos no numéricos, el algoritmo podría generar 
        resultados incorrectos o incluso fallar.
    a.2) Indique la T(n) y la O de este algoritmo en el peor caso.
        La complejidad temporal en el peor caso de RadixSort es O(n log d), donde:

        n es el número de elementos en la lista de entrada.
        d es el número máximo de dígitos en cualquier elemento.
Ejercicio 2 Item C)
c) ¿En qué caso el algoritmo RadixSort puede tardar más que un tiempo lineal?
    El algoritmo RadixSort tiene un tiempo de ejecución promedio de O(n log m),
    donde n es el número de elementos y m es la longitud máxima de los elementos. 
    Sin embargo, puede tardar más que un tiempo lineal en casos específicos:

        Claves con la misma longitud: O(n)
        Claves con distribución uniforme: O(n log n)
        Implementación ineficiente: Variable
  */

  /**
 * Tarea 5 Grupo 13 Ejercicio 2 Item B) 
 * Integrantes: Lucas Vallejos CI: 5439993
 *              Ivan Figueredo CI: 6743661
 */
import java.util.Arrays;

public class RadixSort {

    public static void radixSort(String[] palabras) {
        int maxCharacterCode = Character.MAX_VALUE; // Valor máximo de Unicode
        int maxLongitud = getMaxLongitud(palabras);
        
        // Ordenar desde el último dígito hasta el primero
        for (int i = maxLongitud - 1; i >= 0; i--) {
            ordenarPorDigito(palabras, i, maxCharacterCode);
        }
    }

    private static int getMaxLongitud(String[] palabras) {
        int maxLongitud = 0;
        for (String palabra : palabras) {
            maxLongitud = Math.max(maxLongitud, palabra.length());
        }
        return maxLongitud;
    }

    private static void ordenarPorDigito(String[] palabras, int digito, int maxCharacterCode) {
        int[] count = new int[maxCharacterCode + 1]; // Arreglo para todos los caracteres posibles
        String[] output = new String[palabras.length]; // Arreglo temporal para los resultados ordenados
        
        // Contar ocurrencias de cada carácter
        for (String palabra : palabras) {
            int charIndex = digito < palabra.length() ? palabra.charAt(digito) : 0;
            count[charIndex]++;
        }

        // Transformar count en posiciones reales
        for (int i = 1; i < count.length; i++) {
            count[i] += count[i - 1];
        }

        // Construir el arreglo ordenado
        for (int i = palabras.length - 1; i >= 0; i--) {
            int charIndex = digito < palabras[i].length() ? palabras[i].charAt(digito) : 0;
            output[count[charIndex] - 1] = palabras[i];
            count[charIndex]--;
        }

        // Copiar los resultados ordenados de vuelta al arreglo original
        System.arraycopy(output, 0, palabras, 0, palabras.length);
    }

    public static void main(String[] args) {
        String[] palabras = {"hola", "mundo", "adios", "programacion", "computadora"};

        System.out.println("Lista sin ordenar:");
        for (String palabra : palabras) {
            System.out.print(palabra + " ");
        }

        radixSort(palabras);

        System.out.println("\nLista ordenada:");
        for (String palabra : palabras) {
            System.out.print(palabra + " ");
        }
    }
}

