/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Unidad3;

/**
 *
 * @author lucas
 */
//package Ejercicio2;


/**
 * Ejercicio 2  
 * Tarea 3 Unidad 3 Grupo G13
 * Integrantes                 Ci  Seccion     
 * Lucas Vallejos       5439993     TR
 * Iván Figueredo       6743661     TR
 */

 /*
 * El rendimiento de el algoritmo implementado KesimoMasPequenho es parecido al de un algoritmo de busqueda, 
 * pues al pasar por un nodo, se elige seguir por el hijo derecho o izquierdo. Sabiendo esto, el peor caso ocurriria 
 * cuando el elemento se encuentre en la hoja mas profunda del arbol, en cuyo caso el tiempo seria de O(h), siendo 
 * h la altura del arbol.
 */

import java.util.Iterator;
import java.util.Stack;

public class BST_Ej2<T extends Comparable<? super T>> implements Iterable<T> {

    private class NodoBST {
        T dato = null;
        NodoBST izq = null;
        NodoBST der = null;
        int peso = 0;

        public NodoBST(T dato) {
            this.dato = dato;
            peso = 1;
        }

        public int getPesoHijo(NodoBST n) {
            if (n == null){
                return 0;
            }
            peso = 1+ getPesoHijo(n.izq) + getPesoHijo(n.der);
            return peso;
        }
    }

    
    private NodoBST raiz = null;

    /* Agregar un dato al arbol */
    public void agregar(T dato) {
        raiz = priv_agregar(raiz, dato);
    }


    public T getRaiz(){
        return raiz.dato;
    }
    /*
     * Retorna el "nodo" donde se encuentra el primer dato que dice ser igual a
     * parametro dado. La comparacion se realiza via "compareTo()" de la interfaz T.
     */
    public T buscar(T dato) {
        NodoBST nodo = priv_buscar(raiz, dato);
        if (nodo != null)
            return nodo.dato;
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

    private NodoBST priv_agregar(NodoBST n_actual, T dato) {
        if (n_actual == null)
            return (new NodoBST(dato));

        int comparacion = dato.compareTo(n_actual.dato);

        if (comparacion < 0){
            n_actual.izq = priv_agregar(n_actual.izq, dato);
            n_actual.peso = 1+ n_actual.getPesoHijo(n_actual.izq) + n_actual.getPesoHijo(n_actual.der);
        }else{
            n_actual.der = priv_agregar(n_actual.der, dato);
            n_actual.peso = 1+ n_actual.getPesoHijo(n_actual.izq) + n_actual.getPesoHijo(n_actual.der);
        }
        return n_actual;

    }

    /* Imprime en in-orden */
    private void priv_imprimir(NodoBST n_actual) {
        if (n_actual != null) {
            priv_imprimir(n_actual.izq);
            System.out.print(n_actual.dato + " ");
            priv_imprimir(n_actual.der);
        }
    }

    private NodoBST priv_buscar(NodoBST n_actual, T dato) {
        if (n_actual == null) // dato no se encuentra en el arbol
            return null;

        int comparacion = dato.compareTo(n_actual.dato);

        if (comparacion == 0) // dato == n_actual.dato
            return n_actual;
        else if (comparacion < 0) // dato < n_actual.dato, puede estar a la izquierda
            return priv_buscar(n_actual.izq, dato);
        else // dato > n_actual.dato, puede estar a la derecha
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
                    int comparacion = min.compareTo(current.dato);
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
                T result = nodo.dato;
                current = nodo.der;
                buscarSiguiente(min);
                return result;
            }
        }
    }

    public void KesimoMasPequenho(BST_Ej2 bst, int k){
        try {
            Comparable kesimo = buscarKesimo(bst.raiz, k);
            if (kesimo.equals(-1)){
               System.out.print("El elemento "+ k + " no existe");
            }else {
                System.out.print("El elemento "+ k + " del arbol es "+ kesimo + " \n");
            }
        } catch (NullPointerException e){
            
        }
    }

    private Comparable buscarKesimo(NodoBST n_actual, int k){
        Comparable resultado = -1;

        while (n_actual != null){
            int pos = calcularPosicion(n_actual);
            if (pos == k){
                resultado = n_actual.dato;
               
                break;
            }else if(k > pos){
                k = k - pos;
                n_actual = n_actual.der;
            }else {
                n_actual = n_actual.izq;
            }
        }
        return resultado;
    }

    private int calcularPosicion(NodoBST n_actual){
        if(n_actual.izq != null){
            return n_actual.izq.peso + 1; 
        }else if (n_actual.der != null){
            return (n_actual.peso - n_actual.der.peso);
        }else {
            return n_actual.peso;
        }
    }

    
    public static void main(String[] args) {
        
        BST_Ej2<Integer> bi = new BST_Ej2<>();
        Integer[] nums = {4,5,6,1,3,9,2,8,7,10 };
        for (Integer num : nums)
            bi.agregar(num);

        bi.imprimir();
        bi.KesimoMasPequenho(bi, 4);

    }
}
        

