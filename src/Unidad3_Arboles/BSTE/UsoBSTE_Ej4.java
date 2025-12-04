/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
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

public class UsoBSTE_Ej4 {
    
    public static void main (String[] args){
        BSTE_Ej4<Integer, String> bst = new BSTE_Ej4<>();


        /* En este arbol, la clave seria el numero de cedula de la persona, y el dato seria su nombre*/
        bst.agregar(859243,"Raquel Torres");
        bst.agregar(6743660, "Yilda Kaimen");
        bst.agregar(6743661, "Ivan Figeuredo");
        bst.agregar(758298, "Martin Cano");
        bst.agregar(5439993 , "Lucas Vallejos");

        bst.imprimir();

        }
    }

    

