/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Unidad3_Arboles.BSTE;

/**
 *
 * @author lucas
 */



/**
 * Ejercicio 4 
 * Tarea 3 Unidad 3 Grupo G13
 * Integrantes                 Ci  Seccion     
 * Lucas Vallejos       5439993     TR
 * Iván Figueredo       6743661     TR
 */

import java.util.Iterator;
import java.util.Stack;

public class BSTE_Ej4<T extends Comparable<? super T>, E> implements Iterable<T> {

    private class NodoBST {
        T clave = null;
        E dato = null;
        NodoBST izq = null;
        NodoBST der = null;

        public NodoBST(T clave, E dato) {
            this.clave = clave;
            this.dato = dato;
        }
    }

    private NodoBST raiz = null;

    /* Agregar un dato al arbol */
    public void agregar(T clave, E dato) {
        raiz = priv_agregar(raiz, clave, dato);
    }

    /*
     * Retorna el "nodo" donde se encuentra el primer dato que dice ser igual a
     * parametro dado. La comparacion se realiza via "compareTo()" de la interfaz T.
     */
    public T buscar(T dato) {
        NodoBST nodo = priv_buscar(raiz, dato);
        if (nodo != null)
            return nodo.clave;
        else {
            System.out.println("No existe en el arbol!!! " + dato);
            return null;
        }
    }

    /*
     * Imprime el arbol en recorrido infijo o simetrico.
     */
    public void imprimir() {
        System.out.println();
        priv_imprimir(raiz);
        System.out.println();
    }

    private NodoBST priv_agregar(NodoBST n_actual, T clave, E dato) {
        if (n_actual == null)
            return (new NodoBST(clave, dato));

        int comparacion = clave.compareTo(n_actual.clave);

        if (comparacion < 0)
            n_actual.izq = priv_agregar(n_actual.izq, clave, dato);
        else
            n_actual.der = priv_agregar(n_actual.der, clave, dato);

        return n_actual;

    }

    /* Imprime en in-orden */
    private void priv_imprimir(NodoBST n_actual) {
        if (n_actual != null) {
            priv_imprimir(n_actual.izq);
            System.out.print(n_actual.clave + " : "+ n_actual.dato + " \n");
            priv_imprimir(n_actual.der);
        }
    }

    private NodoBST priv_buscar(NodoBST n_actual, T clave) {
        if (n_actual == null) // dato no se encuentra en el arbol
            return null;

        int comparacion = clave.compareTo(n_actual.clave);

        if (comparacion == 0) //  == n_actual.dato
            return n_actual;
        else if (comparacion < 0) // dato < n_actual.dato, puede estar a la izquierda
            return priv_buscar(n_actual.izq, clave);
        else // dato > n_actual.dato, puede estar a la derecha
            return priv_buscar(n_actual.der, clave);

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
            T result = nodo.clave;
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
        return new ClavesIterable(min, max);
    }

    private class ClavesIterable implements Iterable<T> {
        private T min;
        private T max;

        public ClavesIterable(T min, T max) {
            this.min = min;
            this.max = max;
        }

        @Override
        public Iterator<T> iterator() {
            return new ClavesIterator();
        }

        private class ClavesIterator implements Iterator<T> {
            private Stack<NodoBST> stack = new Stack<>();
            private NodoBST current = raiz;

            public ClavesIterator() {
                buscarSiguiente(min);
            }

            private void buscarSiguiente(T min) {
                while (current != null) {
                    int comparacion = min.compareTo(current.clave);
                    if (comparacion <= 0) {
                        stack.push(current);
                        current = current.izq;
                    } else {
                        current = current.der;
                    }
                }
            }

            @Override
            public boolean hasNext() {
                return !stack.isEmpty();
            }

            @Override
            public T next() {
                NodoBST nodo = stack.pop();
                T result = nodo.clave;
                current = nodo.der;
                buscarSiguiente(min);
                return result;
            }
        }
    }

    
}
        
