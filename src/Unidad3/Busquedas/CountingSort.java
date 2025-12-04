/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Unidad3.Busquedas;

/**
 *
 * @author lucas
 */
import java.util.Arrays;

/**
 * Tarea 5 Grupo 13 Ejercicio 2 Item d) 
 * Integrantes: Lucas Vallejos CI: 5439993
 *              Ivan Figueredo CI: 6743661
 */

/* Esta clase utiliza el algoritmo ConuntingSort para implementar una version de RadixSort */
public class CountingSort {

    /* Metodo para realizar el CountingSort en el arreglo, utilizando el digito */
    public static void countingSort(int[] arreglo, int digito) {
        final int BASE = 10; // Base decimal 
        int n = arreglo.length;
        int[] salida = new int[n];
        int[] cont = new int[BASE]; // Contador para digitos del 0 al 9

        // Inicializar el arreglo contador
        for(int i = 0; i < BASE; i++){
            cont[i] = 0;
        }

        // Contar la frecuencia de cada digito
        for (int i = 0; i < n; i++) {
            int indice = (arreglo[i] / digito) % BASE;
            cont[indice]++;
        }

        // Calcular la posición actual de cada digito en el arreglo de salida
        for (int i = 1; i < BASE; i++) {
            cont[i] += cont[i - 1];
        }

        // Construir el arreglo de salida
        for (int i = n - 1; i >= 0; i--) {
            int indice = (arreglo[i] / digito) % BASE;
            salida[cont[indice] - 1] = arreglo[i];
            cont[indice]--;
        }

        // Copiar el arreglo de salida al arreglo original
        System.arraycopy(salida, 0, arreglo, 0, n);
    }

    /*  Metodo principal de RadixSort */
    public static void radixSort(int[] arreglo) {
        int longMax = Arrays.stream(arreglo).max().orElse(0); // Encontrar el valor maximo en el arreglo
        int digito = 1;

        // Realizar el RadixSort desde el digito menos significativo al mas significativo
        while (longMax / digito > 0) {
            countingSort(arreglo, digito);
            digito *= 10; // Mover al siguiente dígito
        }
    }

    public static void main(String[] args) {
        int[] arreglo = {170, 45, 75, 90, 802, 24, 2, 66};

        // Llamar a RadixSort para ordenar el arreglo
        radixSort(arreglo);

        // Imprimir el arreglo ordenado
        System.out.println("Arreglo ordenado:");
        for (int num : arreglo) {
            System.out.print(num + " ");
        }
        System.out.println("\n");
    }
}

/*¿Qué ventaja poseería sobre la versión de RadixSort presentado en el ítem a) ?
 La ventaja es que esta implementacion es mas general y puede manejar numeros de longitud variable, 
ya que no asume longitud maxima fija. Ademas, se puede adaptar facilmente para trabajar con diferentes bases numericas */ 

