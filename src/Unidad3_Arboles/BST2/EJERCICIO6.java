/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Unidad3_Arboles.BST2;

/**
 *
 * @author lucas
 */
import java.util.Random;
/**
 * Ejercicio 6
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
public class EJERCICIO6 {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Por favor, proporcione los tamaños de los arrays como argumentos.");
            return;
        }

        Random random = new Random();

        /*System.out.println("N\tBST(ms)\tBST/T(log(N))\tAVL(ms)\tAVL/T(log(N))");*/
        System.out.printf("%-10s%-10s%-15s%-15s%-20s%n",
                    "N",
                    "BST(ms)",
                    "BST/T(log(N))",
                    "AVL(ms)",
                    "AVL/T(log(N))");


        for (String arg : args) {
            int size = Integer.parseInt(arg);

            // Crear y llenar array aleatorio
            int[] array = new int[size];
            for (int i = 0; i < size; i++) {
                array[i] = random.nextInt();
            }

            // Medir tiempo BST
            long startTimeBST = System.currentTimeMillis();
            BST_EJER6<Integer> bst = new BST_EJER6<>();
            for (int num : array) {
                bst.agregar(num);
            }
            long endTimeBST = System.currentTimeMillis();
            long elapsedTimeBST = endTimeBST - startTimeBST;
            double logN_BST = Math.log(size);

            // Medir tiempo AVL
            long startTimeAVL = System.currentTimeMillis();
            AVL avl = new AVL();
            for (int num : array) {
                avl.raiz = avl.insertar(avl.raiz, num);
            }
            long endTimeAVL = System.currentTimeMillis();
            long elapsedTimeAVL = endTimeAVL - startTimeAVL;
            double logN_AVL = Math.log(size);

            // Imprimir resultados
            /*System.out.println(size + "\t" + elapsedTimeBST + "\t" + elapsedTimeBST / logN_BST + "\t\t" +
                               elapsedTimeAVL + "\t" + elapsedTimeAVL / logN_AVL);*/
            
            System.out.printf("%-10d%-10d%-15f%-15d%-20f%n",
                        size,
                        elapsedTimeBST,
                        elapsedTimeBST / logN_BST,
                        elapsedTimeAVL,
                        elapsedTimeAVL / logN_AVL);
        }
    }
}
