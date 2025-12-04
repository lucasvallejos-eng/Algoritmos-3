/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Unidad3.Busquedas;

/**
 *
 * @author lucas
 */
/**
 * @author Lucas Vallejos CI: 5439993
 *         Iván Figueredo CI: 6743661
 */

/*Clase que implementa la busqueda de un valor en un arreglo circular*/
public class MainBusquedaArregloCircular {

    /*Metodo main que contiene el caso de prueba*/
    public static void main(String[] args) {
      
        /*Crea un arreglo circular*/
        Integer[] arreglo = {34, 55, 67,89,190, 0,1, 2, 3, 5, 6, 8, 9};
        /*instancia la clase para hacer la busqueda*/ 
        ArregloCircular<Integer> arr = new ArregloCircular<>(arreglo, arreglo.length);

        /*Valor buscado*/
        Integer t = 9; 

        /*Impresion del resultado de la busqueda*/
        if (arr.buscar(t)){
            System.out.print("El elemento "+ t+ " se encuentra en el arreglo\n");
        }else{
            System.out.print("El elemento "+ t +" no se encuentra en el arreglo\n");
        }
    }
    
}

