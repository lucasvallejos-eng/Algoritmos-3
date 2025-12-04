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
 * es un T o derivado. 
 *
 * Esta implementacion no utiliza Generic !!
 * ---------------------------------------
 */

 /**
 * Ejercicio 5
 * Tarea 3 Unidad 3 Grupo G13
 * Integrantes                 Ci  Seccion     
 * Lucas Vallejos       5439993     TR
 * Iván Figueredo       6743661     TR
 */

 import java.util.NoSuchElementException;

 public class BST_Ej5 <T extends Comparable<? super T>>
  {
  
     private class NodoBST { 
        T dato = null;
        NodoBST izq = null;
        NodoBST der = null;
  
        public NodoBST (T dato)
       {
          this.dato = dato;
       }
     }
     
     private NodoBST raiz = null;
  
  
     /* Agregar un dato al arbol */ 
     public void agregar (T dato)
     {
        raiz = priv_agregar (raiz, dato);
     }
  
     /*
      * Retorna el "nodo" donde se encuentra el primer dato
      * que dice ser igual a parametro dado.
      * La comparacion se realiza via "compareTo()" de la interfaz T.
      */
     public T buscar (T dato) 
     {
        NodoBST nodo = priv_buscar(raiz,dato);
        if ( nodo != null ) 
           return nodo.dato;
        else { /* Reemplazar por manejo de excepcion!! */
           System.out.println("No existe en el arbol!!! " + dato);
           return null;	  
        }	 
     }
  
     /*
      * Imprime el arbol en recorrido infijo o simetrico.
      */
     public void imprimir()
     {
        System.out.println();
        priv_imprimir (raiz);
        System.out.println();
     }
  
  
     private NodoBST priv_agregar (NodoBST n_actual, T dato)
     {
        if ( n_actual == null )
           return ( new NodoBST(dato) );
  
       int comparacion = dato.compareTo (n_actual.dato);
       
       if ( comparacion < 0 ) 
           n_actual.izq = priv_agregar(n_actual.izq,dato);
       else
          n_actual.der = priv_agregar(n_actual.der,dato);
           
       return n_actual;
       
     }
  
  
     /* Imprime en in-orden */
     private void priv_imprimir (NodoBST n_actual)
     {
        if ( n_actual != null )
        {
           priv_imprimir (n_actual.izq);
           System.out.print (n_actual.dato + " ");
           priv_imprimir (n_actual.der);
        }
     }
  
  
     private NodoBST priv_buscar (NodoBST n_actual, T dato)
     {
        if ( n_actual == null )      // dato no se encuentra en el arbol
           return null;
  
       int comparacion = dato.compareTo (n_actual.dato);
  
       if ( comparacion == 0 )      // dato == n_actual.dato 
         return n_actual;
       else if ( comparacion < 0 )  // dato < n_actual.dato, puede estar a la izquierda
         return priv_buscar(n_actual.izq,dato);
       else	                      // dato > n_actual.dato, puede estar a la derecha
         return priv_buscar(n_actual.der,dato);
            
     }
     
     public int lci ()  {
          return lci(raiz,0);
     }
     
     private int lci ( NodoBST nodo, int nivel) {
          if ( nodo == null ) 
              return 0;
          else 
              return nivel + lci(nodo.izq, nivel+1) + lci (nodo.der, nivel+1);
     }
 
 
     /*
     * Método que devuelve verdadero si el arbol es lleno, falso en caso contrario.
     */
    
    public boolean esLleno() {
       int altura = altura(raiz);
       int nodos = contarNodos(raiz);
       return nodos == (1 << altura) - 1;
   }
   /*
    * Método que devuelve la altura de un arbol.
    */
   private int altura(NodoBST nodo) {
       if (nodo == null) {
           return 0;
       }
       int alturaIzq = altura(nodo.izq);
       int alturaDer = altura(nodo.der);
       return Math.max(alturaIzq, alturaDer) + 1;
   }
   /*
    * Método que devuelve el número de nodos de un arbol.
    */
   private int contarNodos(NodoBST nodo) {
       if (nodo == null) {
           return 0;
       }
       return 1 + contarNodos(nodo.izq) + contarNodos(nodo.der);
   }
    
    /*
     * Método que devuelve verdadero si el arbol es completo, falso en caso contrario.
     */
    public boolean esCompleto() {
       int altura = altura(raiz);
       return esCompleto(raiz, altura, 1);
    }
 
    private boolean esCompleto(NodoBST nodo, int altura, int nivel) {
       if (nodo == null) {
          return true;
       }
 
       if (nivel == altura) {
          return nodo.izq == null && nodo.der == null;
       }
 
       if (nodo.izq == null || nodo.der == null) {
          return false;
       }
 
       return esCompleto(nodo.izq, altura, nivel + 1) && esCompleto(nodo.der, altura, nivel + 1);
    }
 
 /*
  *  Método que elimina un nodo del arbol de búsqueda binario si lo contiene 
  * y devuelve su valor.
  */
     public void eliminar(T dato) {
       NodoBST padre = null;
       NodoBST actual = raiz;
 
       while (actual != null && !actual.dato.equals(dato)) {
          padre = actual;
          if (dato.compareTo(actual.dato) < 0) {
                actual = actual.izq;
          } else {
                actual = actual.der;
          }
       }
 
       if (actual == null) {
          throw new NoSuchElementException("El dato no existe en el arbol");
       }
 
       if (actual.izq == null && actual.der == null) {
          if (padre == null) {
                raiz = null;
          } else if (actual == padre.izq) {
                padre.izq = null;
          } else {
                padre.der = null;
          }
       } else if (actual.izq == null) {
          if (padre == null) {
                raiz = actual.der;
          } else if (actual == padre.izq) {
                padre.izq = actual.der;
          } else {
                padre.der = actual.der;
          }
       } else if (actual.der == null) {
          if (padre == null) {
                raiz = actual.izq;
          } else if (actual == padre.izq) {
                padre.izq = actual.izq;
          } else {
                padre.der = actual.izq;
          }
       } else {
          NodoBST sucesor = obtenerSucesor(actual);
          T temp = sucesor.dato;
          eliminar(sucesor.dato);
          actual.dato = temp;
       }
    }
 
    
    public T sucesor(T dato) {
       NodoBST nodo = priv_buscar(raiz,dato);
       if (nodo == null) {
          throw new NoSuchElementException("El dato no existe en el arbol");
       }
       if (nodo.der != null) {
          NodoBST sucesor = obtenerSucesor(nodo.der);
          return sucesor.dato;
       } else {
          throw new NoSuchElementException("No hay sucesor para el nodo");
       }
    }
 
    public T predecesor(T dato) {
       NodoBST nodo = priv_buscar(raiz,dato);
      
       if (nodo == null) {
          throw new NoSuchElementException("El dato no existe en el arbol");
       }
       if (nodo.izq != null) {
          NodoBST predecesor = obtenerPredecesorN(nodo.izq);
          return predecesor.dato;
       } else {
          throw new NoSuchElementException("No hay predecesor para el nodo");
       }
    }
 
   
 
    private NodoBST obtenerSucesor(NodoBST nodo) {
       NodoBST sucesor = nodo;
       while (sucesor.izq != null) {
          sucesor = sucesor.izq;
       }
       return sucesor;
    }
 
    private NodoBST obtenerPredecesorN(NodoBST nodo) {
       NodoBST predecesor = nodo;
       while (predecesor.der != null) {
          predecesor = predecesor.der;
       }
       return predecesor;
    }
 
  
 
 
     /*
       Un ejemplo de uso sencillo de uso de la clase BST
     */   
     public static void main(String[] args) {
       BST_Ej5<Integer> bst = new BST_Ej5<>();
       Integer[] array = {10, 5, 15, 3, 7, 12, 17, 2};
       for (Integer num : array) {
           bst.agregar(num);
       }
   
       // Imprimir el arbol
       System.out.println("arbol original:");
       bst.imprimir();
   
       // Verificar si el arbol es lleno
       System.out.println("¿El arbol es lleno? " + bst.esLleno());
   
       // Verificar si el arbol es completo
       System.out.println("¿El arbol es completo? " + bst.esCompleto());
   
       // Eliminar un nodo
       Integer datoAEliminar = 15;
       System.out.println("Eliminando el nodo con dato " + datoAEliminar);
       bst.eliminar(datoAEliminar);
       bst.imprimir(); // Imprimir el arbol después de eliminar el nodo
   
       // Obtener el sucesor de un nodo
       int datoSucesor = 12;
       System.out.println("Sucesor de " + datoSucesor + ": " + bst.sucesor(datoSucesor));
       
       // Obtener el predecesor de un nodo
       int datoPredecesor = 10;
       System.out.println("Predecesor de " + datoPredecesor + ": " + bst.predecesor(datoPredecesor));
   }
   
 }