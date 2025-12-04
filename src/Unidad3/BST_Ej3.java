/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Unidad3;

/**
 *
 * @author lucas
 */
/**
 * Ejercicio 3
 * Tarea 3 Unidad 3 Grupo G13
 * Integrantes                 Ci  Seccion     
 * Lucas Vallejos       5439993     TR
 * Iván Figueredo       6743661     TR
 */

import java.util.Iterator;
import java.util.Stack;

public class BST_Ej3<T extends Comparable<? super T>> implements Iterable<T> {

    private class NodoBST {
        T dato = null;
        NodoBST izq = null;
        NodoBST der = null;

        public NodoBST(T dato) {
            this.dato = dato;
        }
    }

    private NodoBST raiz = null;

    /* Agregar un dato al arbol */
    public void agregar(T dato) {
        raiz = priv_agregar(raiz, dato);
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

        if (comparacion < 0)
            n_actual.izq = priv_agregar(n_actual.izq, dato);
        else
            n_actual.der = priv_agregar(n_actual.der, dato);

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

    public BST_Ej3 SumatoriaNodos (BST_Ej3 arbol){
        BST_Ej3<T> arbolPrima = new BST_Ej3<>();
        crearArbolPrima(arbol.raiz, arbolPrima, arbol);
        return arbolPrima;
    }

    private void crearArbolPrima(NodoBST nodo, BST_Ej3 arbolPrima, BST_Ej3 arbol){
        if (nodo != null){
            arbolPrima.agregar(arbol.ACC_GREATER(nodo));
            crearArbolPrima(nodo.izq, arbolPrima, arbol);
            crearArbolPrima(nodo.der, arbolPrima, arbol);
        }
    }

    public Comparable ACC_GREATER(NodoBST nodo){
        Comparable resultado;
        if (nodo.dato instanceof Integer){
            Comparable cero = 0;
            return resultado = recorridoGreater(raiz, nodo, cero);
        }else if (nodo.dato instanceof Double){
            Comparable cero = 0.0;
            return resultado = recorridoGreater(raiz, nodo, cero);
        }else if (nodo.dato instanceof Float){
            Comparable cero = 0.0;
            return resultado = recorridoGreater(raiz, nodo, cero);
        }else if (nodo.dato instanceof Long){
            Comparable cero = 0;
            return resultado = recorridoGreater(raiz, nodo, cero);
        }else if (nodo.dato instanceof Short){
            Comparable cero = 0;
            return resultado = recorridoGreater(raiz, nodo, cero);
        }else if (nodo.dato instanceof Number){
            Comparable cero = 0;
            return resultado = recorridoGreater(raiz, nodo, cero);
        }else if (nodo.dato instanceof String){
            Comparable cero = "";
             return resultado = recorridoGreater(raiz, nodo, cero);
        } 
        
        return null;
    }

    private Comparable recorridoGreater(NodoBST nodo_actual, NodoBST nodo_buscado, Comparable suma){
        if (nodo_actual != null){       

            suma = recorridoGreater(nodo_actual.der, nodo_buscado, suma);
            if (nodo_actual.dato.compareTo(nodo_buscado.dato) >=0 ){
                suma = sumar(suma, nodo_actual.dato);
            }
            suma = recorridoGreater(nodo_actual.izq, nodo_buscado, suma);
        }
        return suma;
    }

    public Comparable sumar(Object o1,Object o2){
    if(o1 instanceof Integer && o2 instanceof Integer)
        return  ((int)o1) + ((int) o2) ;

    if(o1 instanceof Double && o2 instanceof Double)
        return  ((double)o1) + ((double) o2) ;

    if(o1 instanceof Float && o2 instanceof Float)
        return  ((float)o1) + ((float) o2) ;

    if(o1 instanceof Long && o2 instanceof Long)
        return  ((long)o1) + ((long) o2) ;

    if(o1 instanceof Short && o2 instanceof Short)
        return  ((short)o1) + ((short) o2) ;

    if(o1 instanceof Number && o2 instanceof Number)
        return (new Double(String.valueOf(o1))  +   (new Double(String.valueOf(o2)))   );

    if(o1 instanceof String && o2 instanceof String){
        return ((String)o1 + (String)o2);
    }

    return null;
}
    public static void main(String[] args) {

        // Ejemplo de uso del método claves
        BST_Ej3<Integer> bi = new BST_Ej3<>();
        Integer[] nums = { 10, 15, 7, 8, 6, 2, 11, 12 };
        for (Integer num : nums)
            bi.agregar(num);
        
        Comparable resultado = bi.ACC_GREATER(bi.raiz.der.izq);
        System.out.print("el resultado del recorrido de " +bi.raiz.der.izq.dato+ " es: "+ resultado + "\n");

        BST_Ej3<Integer> prima =  bi.SumatoriaNodos(bi);
        prima.imprimir();
    }
}
        

