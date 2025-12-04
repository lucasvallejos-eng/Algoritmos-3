package Unidad1;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

/**
 *
 * @author lucas
 */

/**
     * Clase principal que contiene un ejemplo de uso del TAD Lista.
     */
public class UsoLista {

    public static void main(String[] args) {
        // Ejemplo de uso
        Lista<Object> lista = new MiLista<>();
        lista.agregar(2);
        lista.agregar(3);
        lista.agregar(4);
        lista.agregar(7);
        System.out.println("Lista original: " + lista);

        lista.remover(3);
        lista.agregar("Hola");
        lista.agregar("como");
        lista.agregar("estan");
        lista.agregar("luque");
        lista.agregar(2);
        lista.agregar("cerro");
        lista.agregar(10);

        System.out.println("Lista despues de modificar: " + lista);

        Lista<Object> copia = lista.copiar();
        System.out.println("Copia de la lista: " + copia);

        System.out.println("Cantidad de elementos: " + lista.contar());
        System.out.println("Posicion del elemento 'estan': " + lista.indice("estan"));
        System.out.println("Elemento en posicion 3: " + lista.indice(3));
        System.out.println("ultimo elemento (pop): " + lista.pop());
        System.out.println("Lista despues de pop: " + lista);

        Lista<Object> otraLista = new MiLista<>();
        otraLista.agregar("hola, que");
        otraLista.agregar(1);
        otraLista.agregar(3);

            // Crea una nueva instancia de MiLista<Integer> con elementos 4 y 5
        MiLista<Integer> nuevaLista = new MiLista<>();
        nuevaLista.agregar(4);
        nuevaLista.agregar(5);
            // Agregar la nueva lista a otraLista
        otraLista.agregar(nuevaLista);

        lista.extender(otraLista);
        System.out.println("Lista despues de extender: " + lista);
    }
}
