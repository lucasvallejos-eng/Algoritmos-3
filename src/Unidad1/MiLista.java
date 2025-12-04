/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Unidad1;

/**
 *
 * @author lucas
 */



import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Tarea 1 Unidad 1
 * Integrantes                 Ci  Seccion     
 * Lucas Vallejos       5439993     TQ
 * Iván Figueredo       6743661     TQ
 */


/**
  * Interfaz para el Tipo Abstracto de Dato Lista.
  *
  * @param <T> Tipo de elementos contenidos en la lista.
  */
interface Lista<T> extends Iterable<T> {
    /**
      * Agrega un elemento a la lista.
      *
      * @param elemento Elemento a agregar.
      */
    void agregar(T elemento);
    /**
      * Borra todos los elementos de la lista.
      */
    void borrarTodos();

    /**
      * Crea y devuelve una copia de la lista.
      *
      * @return Una copia de la lista.
      */
    Lista<T> copiar();

    /**
      * Devuelve la cantidad de elementos en la lista.
      *
      * @return La cantidad de elementos en la lista.
      */
    int contar();

    /**
      * Extiende la lista agregando los elementos de otra lista.
      *
      * @param lista Lista a extender.
      */
    void extender(Lista<T> lista);

    /**
      * Devuelve el indice de la primera aparicion del elemento en la lista.
      *
      * @param elemento Elemento a buscar.
      * @return indice del elemento en la lista.
      * @throws NoSuchElementException Si el elemento no esta presente en la lista.
      */
    int indice(T elemento);

    /**
      * Devuelve el elemento en la posicion especificada.
      *
      * @param i indice del elemento a devolver.
      * @return Elemento en la posicion especificada.
      * @throws IndexOutOfBoundsException Si el indice esta fuera de rango.
      */
    T indice(int i);

    /**
      * Elimina y devuelve el ultimo elemento de la lista.
      *
      * @return ultimo elemento de la lista.
      * @throws NoSuchElementException Si la lista esta vacia.
      */
    T pop();

    /**
      * Elimina la primera aparicion del elemento en la lista.
      *
      * @param elemento Elemento a remover.
      * @throws NoSuchElementException Si el elemento no esta presente en la lista.
      */
    void remover(T elemento);

    /**
      * Devuelve una representacion en cadena de la lista.
      *
      * @return Representacion en cadena de la lista.
      */
    @Override
    String toString();
}

/**
  * Implementacion del Tipo Abstracto de Dato Lista.
  *
  * @param <T> Tipo de elementos contenidos en la lista.
  */
public class MiLista<T> implements Lista<T> {
    private Nodo<T> cabeza;

    private static class Nodo<T> {
        T valor;
        Nodo<T> siguiente;

        Nodo(T valor) {
            this.valor = valor;
            this.siguiente = null;
        }
    }

    @Override
    public void agregar(T elemento) {
        Nodo<T> nuevoNodo = new Nodo<>(elemento);
        if (cabeza == null) {
            cabeza = nuevoNodo;
        } else {
            Nodo<T> ultimo = obtenerUltimoNodo();
            ultimo.siguiente = nuevoNodo;
        }
    }

    @Override
    public void borrarTodos() {
        cabeza = null;
    }

    @Override
    public Lista<T> copiar() {
        Lista<T> copia = new MiLista<>();
        for (T elemento : this) {
            copia.agregar(elemento);
        }
        return copia;
    }

    @Override
    public int contar() {
        int contador = 0;
        Nodo<T> actual = cabeza;
        while (actual != null) {
            contador++;
            actual = actual.siguiente;
        }
        return contador;
    }

    @Override
    public void extender(Lista<T> lista) {
        for (T elemento : lista) {
            agregar(elemento);
        }
    }

    @Override
    public int indice(T elemento) {
        int indice = 0;
        Nodo<T> actual = cabeza;
        while (actual != null) {
            if (actual.valor.equals(elemento)) {
                return indice;
            }
            actual = actual.siguiente;
            indice++;
        }
        throw new NoSuchElementException("Elemento no encontrado en la lista");
    }

    @Override
    public T indice(int i) {
        if (i < 0) {
            throw new IndexOutOfBoundsException("indice fuera de rango");
        }

        Nodo<T> actual = cabeza;
        for (int j = 0; j < i; j++) {
            if (actual == null) {
                throw new IndexOutOfBoundsException("indice fuera de rango");
            }
            actual = actual.siguiente;
        }

        if (actual == null) {
            throw new IndexOutOfBoundsException("indice fuera de rango");
        }

        return actual.valor;
    }

    @Override
    public T pop() {
        if (cabeza == null) {
            throw new NoSuchElementException("La lista esta vacia");
        }

        if (cabeza.siguiente == null) {
            T valor = cabeza.valor;
            cabeza = null;
            return valor;
        }

        Nodo<T> penultimo = obtenerPenultimoNodo();
        T valor = penultimo.siguiente.valor;
        penultimo.siguiente = null;
        return valor;
    }

    @Override
    public void remover(T elemento) {
        if (cabeza == null) {
            throw new NoSuchElementException("La lista esta vacia");
        }
 
         if (cabeza.valor.equals(elemento)) {
             cabeza = cabeza.siguiente;
             return;
         }
 
         Nodo<T> actual = cabeza;
         while (actual.siguiente != null && !actual.siguiente.valor.equals(elemento)) {
             actual = actual.siguiente;
         }
 
         if (actual.siguiente == null) {
             throw new NoSuchElementException("Elemento no encontrado en la lista");
         }
 
         actual.siguiente = actual.siguiente.siguiente;
     }
 
     @Override
     public String toString() {
         StringBuilder resultado = new StringBuilder("[");
         Nodo<T> actual = cabeza;
         while (actual != null) {
             resultado.append(actual.valor);
             if (actual.siguiente != null) {
                 resultado.append(", ");
             }
             actual = actual.siguiente;
         }
         resultado.append("]");
         return resultado.toString();
     }
 
     private Nodo<T> obtenerUltimoNodo() {
         Nodo<T> actual = cabeza;
         while (actual.siguiente != null) {
             actual = actual.siguiente;
         }
         return actual;
     }
 
     private Nodo<T> obtenerPenultimoNodo() {
         Nodo<T> actual = cabeza;
         while (actual.siguiente != null && actual.siguiente.siguiente != null) {
             actual = actual.siguiente;
         }
         return actual;
     }
 
     @Override
     public Iterator<T> iterator() {
         return new Iterator<T>() {
             private Nodo<T> actual = cabeza;
 
             @Override
             public boolean hasNext() {
                 return actual != null;
             }
 
             @Override
             public T next() {
                 if (!hasNext()) {
                     throw new NoSuchElementException();
                 }
                 T valor = actual.valor;
                 actual = actual.siguiente;
                 return valor;
             }
         };
     }
 }

