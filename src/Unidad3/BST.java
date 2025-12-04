/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Unidad3;
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
 * es un Comparable o derivado. 
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

import java.util.Iterator;
import java.util.Stack;



public class BST<T extends Comparable<? super T>> implements Iterable<T> {
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

    public T buscar(T dato) {
        NodoBST nodo = priv_buscar(raiz, dato);
        if (nodo != null)
            return nodo.dato;
        else {
            System.out.println("No existe en el arbol!!! " + dato);
            return null;
        }
    }

    public void imprimir() {
        System.out.println();
        priv_imprimir(raiz);
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
        if (n_actual == null)
            return null;

        int comparacion = dato.compareTo(n_actual.dato);

        if (comparacion == 0)
            return n_actual;
        else if (comparacion < 0)
            return priv_buscar(n_actual.izq, dato);
        else
            return priv_buscar(n_actual.der, dato);
    }

    public Iterator<T> iterator() {
        return new BSTIterator();
    }

    private class BSTIterator implements Iterator<T> {
        private Stack<NodoBST> stack = new Stack<>();
        private NodoBST current = raiz;

        public BSTIterator() {
            while (current != null) {
                stack.push(current);
                current = current.izq;
            }
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public T next() {
            NodoBST nodo = stack.pop();
            T result = nodo.dato;
            if (nodo.der != null) {
                nodo = nodo.der;
                while (nodo != null) {
                    stack.push(nodo);
                    nodo = nodo.izq;
                }
            }
            return result;
        }
    }

    public Iterable<T> claves(T min, T max) {
        if (min == null || max == null || min.compareTo(max) > 0) {
            throw new IllegalArgumentException("Claves no válidas");
        }

        NodoBST nodoMin = priv_buscar(raiz, min);
        NodoBST nodoMax = priv_buscar(raiz, max);
        if (nodoMin == null || nodoMax == null) {
            throw new IllegalArgumentException("Una o ambas claves no existen en el arbol");
        }

        return new ClavesIterable(nodoMin, nodoMax);
    }

    private class ClavesIterable implements Iterable<T> {
        private NodoBST nodoMin;
        private NodoBST nodoMax;
        private Stack<NodoBST> stack;

        public ClavesIterable(NodoBST nodoMin, NodoBST nodoMax) {
            this.nodoMin = nodoMin;
            this.nodoMax = nodoMax;
            stack = new Stack<>();
            buscarSiguiente(raiz);
        }

        private void buscarSiguiente(NodoBST nodo) {
            Stack<NodoBST> tempStack = new Stack<>();
            while (nodo != null || !tempStack.isEmpty()) {
                while (nodo != null) {
                    tempStack.push(nodo);
                    nodo = nodo.izq;
                }
                nodo = tempStack.pop();
                if (nodo.dato.compareTo(nodoMin.dato) >= 0 && nodo.dato.compareTo(nodoMax.dato) <= 0) {
                    stack.push(nodo);
                }
                nodo = nodo.der;
            }
        }

        @Override
        public Iterator<T> iterator() {
            return new ClavesIterator();
        }

        private class ClavesIterator implements Iterator<T> {
            private Stack<NodoBST> stack = new Stack<>();

            public ClavesIterator() {
                buscarSiguiente(raiz);
            }

            private void buscarSiguiente(NodoBST nodo) {
                Stack<NodoBST> tempStack = new Stack<>();
                while (nodo != null || !tempStack.isEmpty()) {
                    while (nodo != null) {
                        tempStack.push(nodo);
                        nodo = nodo.izq;
                    }
                    nodo = tempStack.pop();
                    if (nodo.dato.compareTo(nodoMin.dato) >= 0 && nodo.dato.compareTo(nodoMax.dato) <= 0) {
                        stack.push(nodo);
                    }
                    nodo = nodo.der;
                }
            }

            @Override
            public boolean hasNext() {
                return !stack.isEmpty();
            }

            @Override
            public T next() {
                return stack.pop().dato;
            }
        }
    }

    public static void main(String[] args) {

        BST<String> t = new BST<>();
        String[] A = { "hola", "amor", "roma", "barco", "mesa", "musica", "zapato", "amigo" };
        for (int k = 0; k < A.length; k++)
            t.agregar(A[k]);
        t.imprimir();

        String k = t.buscar("mesa");
        if (k != null)
            System.out.println("Si existe!! " + k);

        System.out.println("Recorrido con iterador for-each:");
        for (String s : t)
            System.out.print(s + " ");
        System.out.println();

    
        // Ejemplo de uso del método claves

        BST<Integer> bi = new BST<>();
        Integer[] nums = { 10, 15, 7, 8, 6, 2, 11, 12 };
        for (Integer num : nums)
            bi.agregar(num);
        bi.imprimir();
        int min = 2;
        int max = 12;
        Iterable<Integer> it = bi.claves(min, max);
        System.out.printf("Recorrido de nodos entre %d y %d: ", min, max);
        for (Integer i : it) {
            System.out.printf("%s ", i);
        }
        System.out.println();
    }
}
        

