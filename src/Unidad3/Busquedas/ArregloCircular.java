/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Unidad3.Busquedas;

/**
 *
 * @author lucas
 */

/**
 * Tarea 5 Grupo 13 Ejercicio 3
 * @author Lucas Vallejos CI: 5439993
 *         Iván Figueredo CI: 6743661 
 */

/*Clase que realiza la busqueda de un valor dentro de un arreglo circular*/
public class ArregloCircular<T extends Comparable<? super T>>{
    private final T[] arr;
    private final int n; /*longitud del arreglo*/

    /*Constructor del arreglo*/
    public ArregloCircular(T[] arr, int n){
        this.arr = arr;
        this.n = n; 
    }
 
    /* Metodo para buscar el indice del mayor valor del arreglo del arreglo y lueho hallar el valor desseado*/
    public boolean buscar(T t){
        
        
        /*Recorre el arreglo para hallar el indice donde se encuentra el amyor valor del arreglo*/
        int indiceMayor = hallarIndiceMayor(arr[0], 0, n-1);
        int indiceMenor = indiceMayor+1;
        if (hallarElementoArregloCircular(0, indiceMayor, t) == true || hallarElementoArregloCircular(indiceMenor, n-1, t)== true){
            return true;
        }else {
            return false;

        }
        
    }

    private int hallarIndiceMayor(T inicioArreglo, int inicio, int fin){
        int medio = (inicio+fin)/2;
        if ((inicio-fin) == 1){
            if (arr[inicio].compareTo(arr[fin])>0){
                return inicio;
            }else{
                return fin;
            }
        }else if(arr[medio].compareTo(inicioArreglo) < 0){
            return hallarIndiceMayor(inicioArreglo, inicio, medio-1);
        }else{
            return hallarIndiceMayor(inicioArreglo, medio+1, fin);
        }   
    }

    /*Metodo que usa la busqueda binaria para encontrar el valor, su complejidad es de O(log n) 
       pues el algoritmo divide el arreglo en 2 por cada llamada consecutiva*/
    private boolean hallarElementoArregloCircular(int inicio, int fin, T t){
       
        int medio = (inicio+fin)/2;
        if (arr[medio].equals(t)){ 
            return true;
        }else if ( fin<= inicio){
            return false;

        /*Compara el valor buscado al medio del arreglo dado*/
        }else if (t.compareTo(arr[medio]) > 0){ 
            return hallarElementoArregloCircular(medio+1, fin, t);
        }else{
            return hallarElementoArregloCircular(inicio, medio-1, t);
        }
    }
    
}

   