/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Unidad2_busquedas;

/**
 *
 * @author lucas
 */
/**
 * Tarea 1 Unidad 1 Grupo G13
 * Integrantes                 Ci  Seccion     
 * Lucas Vallejos       5439993     TQ
 * Iván Figueredo       6743661     TQ
 */

import java.util.Random;

public class Util {
    private static final Random random = new Random();

    // Método de ordenación rápida
    public static <T extends Comparable<T>> void quicksort(T[] array) {
        quicksort(array, 0, array.length - 1);
    }

    // Implementación recursiva del método de ordenación rápida
    private static <T extends Comparable<T>> void quicksort(T[] array, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(array, low, high);
            quicksort(array, low, pivotIndex - 1);
            quicksort(array, pivotIndex + 1, high);
        }
    }

    // Método auxiliar para realizar la partición en el método de ordenación rápida
    private static <T extends Comparable<T>> int partition(T[] array, int low, int high) {
        int pivotIndex = getRandomPivotIndex(low, high);
        swap(array, pivotIndex, high);

        T pivot = array[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (array[j].compareTo(pivot) <= 0) {
                i++;
                swap(array, i, j);
            }
        }

        swap(array, i + 1, high);
        return i + 1;
    }

    // Método auxiliar para realizar el intercambio de elementos en el método de ordenación rápida
    private static <T> void swap(T[] array, int i, int j) {
        T temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    // Método auxiliar para obtener un índice de pivote aleatorio en el método de ordenación rápida
    private static int getRandomPivotIndex(int low, int high) {
        return random.nextInt(high - low + 1) + low;
    }
}


