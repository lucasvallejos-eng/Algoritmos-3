/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Unidad3_Arboles.BST2;

/**
 *
 * @author lucas
 */
/* 
 * ALGORITMOS Y ESTRUCTURAS DE DATOS III - Seccion TQ - Prof. Cristian Cappo / Prof. Luis More
 * Ing. Informatica
 * 2021-1er Periodo
 *
 * Implementa la clase BST (Arbol Binario de Busqueda)
 * El dato cuya referencia se almacena en cada nodo
 * es un T o derivado. 
 *
 * ---------------------------------------
 */

/**
 * Ejercicio 1  
 * Tarea 3 Unidad 3 Grupo G13
 * Integrantes                 Ci  Seccion     
 * Lucas Vallejos       5439993     TR
 * Iván Figueredo       6743661     TR
 */

/*
 * ¿Cuál es el costo asintótico en el mejor y peor caso de este algoritmo de ordenación? Fundamente su respuesta.
 * Peor caso: O(n) - Similar al caso de búsqueda en un árbol desbalanceado, donde se tiene que recorrer todo el árbol 
  para encontrar el nodo a eliminar.
 *Mejor caso: O(log n) - En un árbol balanceado, la eliminación sigue el mismo principio 
  que la búsqueda en un árbol balanceado.
 */

 /*
  * Para evitar el peor caso en un  (BST), es fundamental mantener el árbol 
  balanceado. Un árbol balanceado garantiza que la altura del árbol sea lo más cercana posible a log n
  */


  /* 
   * La implementacion de un arbol balanceado como un AVL demuestra su eficiencia a partir de vectores de gran tamaño 
   en el siguiente ejemplo se demuestra
   java Tabla 100000 2000000 50 8000000
   N         BST(ms)   BST/T(log(N))  AVL(ms)        AVL/T(log(N))       
    100000    69        5,993264       92             7,991018
    2000000   2604      179,479043     2327           160,386994
    50        0         0,000000       0              0,000000
    8000000   14418     907,080431     13274          835,107896
   */

import java.util.NoSuchElementException;
import java.util.Random;

public class BST_EJER6<T extends Comparable<? super T>> {
    private class NodoBST {
        T dato = null;
        NodoBST izq = null;
        NodoBST der = null;

        public NodoBST(T dato) {
            this.dato = dato;
        }
    }

    private NodoBST raiz = null;

    public void agregar(T dato) {
        raiz = priv_agregar(raiz, dato);
    }

    public T buscar(T dato) throws NoSuchElementException {
        NodoBST nodo = priv_buscar(raiz, dato);
        if (nodo != null) {
            return nodo.dato;
        } else {
            throw new NoSuchElementException("El dato no existe en el árbol: " + dato);
        }
    }

    public void imprimir() {
        System.out.println();
        priv_imprimir(raiz);
        System.out.println();
        System.out.println();
    }

    private NodoBST priv_agregar(NodoBST n_actual, T dato) {
        if (n_actual == null)
            return (new NodoBST(dato));

        int comparacion = dato.compareTo(n_actual.dato);

        if (comparacion < 0)
            n_actual.izq = priv_agregar(n_actual.izq, dato);
        else
            n_actual.der = priv_agregar(n_actual.der, dato);

        return n_actual;
    }

    private void priv_imprimir(NodoBST n_actual) {
        if (n_actual != null) {
            priv_imprimir(n_actual.izq);
            System.out.print(n_actual.dato + " ");
            priv_imprimir(n_actual.der);
        }
    }

    private NodoBST priv_buscar(NodoBST n_actual, T dato) {
        if (n_actual == null)      // dato no se encuentra en el arbol
            return null;

        int comparacion = dato.compareTo(n_actual.dato);

        if (comparacion == 0)      // dato == n_actual.dato
            return n_actual;
        else if (comparacion < 0)  // dato < n_actual.dato, puede estar a la izquierda
            return priv_buscar(n_actual.izq, dato);
        else                        // dato > n_actual.dato, puede estar a la derecha
            return priv_buscar(n_actual.der, dato);
    }

    public int lci() {
        return lci(raiz, 0);
    }

    private int lci(NodoBST nodo, int nivel) {
        if (nodo == null)
            return 0;
        else
            return nivel + lci(nodo.izq, nivel + 1) + lci(nodo.der, nivel + 1);
    }

    /*public static void main(String[] args) {
        Random random = new Random();

        for (String arg : args) {
            int size = Integer.parseInt(arg);
            BST_EJER6<Integer> tree = new BST_EJER6<>();
            for (int i = 0; i < size; i++) {
                tree.agregar(random.nextInt(1000));
            }
            tree.imprimir();
        }
    }*/
}

