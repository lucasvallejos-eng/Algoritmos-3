/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Unidad3_Arboles.BST2;

/**
 *
 * @author lucas
 */
import java.util.Arrays;
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
class NodoAVL {
    int dato, altura;
    NodoAVL izquierdo, derecho;

    public NodoAVL(int dato) {
        this.dato = dato;
        this.altura = 1;
    }
}

class AVL {
    NodoAVL raiz;

    int altura(NodoAVL nodo) {
        if (nodo == null) return 0;
        return nodo.altura;
    }

    int obtenerBalance(NodoAVL nodo) {
        if (nodo == null) return 0;
        return altura(nodo.izquierdo) - altura(nodo.derecho);
    }

    NodoAVL rotacionDerecha(NodoAVL y) {
        NodoAVL x = y.izquierdo;
        NodoAVL t2 = x.derecho;

        x.derecho = y;
        y.izquierdo = t2;

        y.altura = Math.max(altura(y.izquierdo), altura(y.derecho)) + 1;
        x.altura = Math.max(altura(x.izquierdo), altura(x.derecho)) + 1;

        return x;
    }

    NodoAVL rotacionIzquierda(NodoAVL x) {
        NodoAVL y = x.derecho;
        NodoAVL t2 = y.izquierdo;

        y.izquierdo = x;
        x.derecho = t2;

        x.altura = Math.max(altura(x.izquierdo), altura(x.derecho)) + 1;
        y.altura = Math.max(altura(y.izquierdo), altura(y.derecho)) + 1;

        return y;
    }

    NodoAVL insertar(NodoAVL nodo, int dato) {
        if (nodo == null) return new NodoAVL(dato);

        if (dato < nodo.dato)
            nodo.izquierdo = insertar(nodo.izquierdo, dato);
        else if (dato > nodo.dato)
            nodo.derecho = insertar(nodo.derecho, dato);
        else
            return nodo; // Duplicados no permitidos

        nodo.altura = 1 + Math.max(altura(nodo.izquierdo), altura(nodo.derecho));

        int balance = obtenerBalance(nodo);

        // Casos de rotación
        if (balance > 1 && dato < nodo.izquierdo.dato)
            return rotacionDerecha(nodo);
        if (balance < -1 && dato > nodo.derecho.dato)
            return rotacionIzquierda(nodo);
        if (balance > 1 && dato > nodo.izquierdo.dato) {
            nodo.izquierdo = rotacionIzquierda(nodo.izquierdo);
            return rotacionDerecha(nodo);
        }
        if (balance < -1 && dato < nodo.derecho.dato) {
            nodo.derecho = rotacionDerecha(nodo.derecho);
            return rotacionIzquierda(nodo);
        }

        return nodo;
    }

    void imprimirInorder(NodoAVL nodo) {
        if (nodo != null) {
            imprimirInorder(nodo.izquierdo);
            System.out.print(nodo.dato + " ");
            imprimirInorder(nodo.derecho);
        }
    }
}

public class Main {
    /*public static void main(String[] args) {
        Integer[] nums = { 10, 15, 7, 8, 6, 2, 11, 12 };

        AVL arbol = new AVL();

        // Insertar datos del array en el árbol AVL
        for (int num : nums) {
            arbol.raiz = arbol.insertar(arbol.raiz, num);
        }

        System.out.println("Recorrido Inorder del arbol AVL:");
        arbol.imprimirInorder(arbol.raiz);
    }*/
}

