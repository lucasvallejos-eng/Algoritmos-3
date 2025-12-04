/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Unidad3.Busquedas;

/**
 *
 * @author lucas
 */
//package Tareas_Java.Tarea_5.Ejercicio1;


/**
 * Tarea 5 Grupo 13 Ejercicio 1 
 * Integrantes: Lucas Vallejos CI: 5439993
 *              Ivan Figueredo CI: 6743661
 */
import java.util.*;


class Punto {
    double x, y;

    public Punto(double x, double y) {
        this.x = x;
        this.y = y;
    }
}

class Linea {
    Punto p1, p2;

    public Linea(Punto p1, Punto p2) {
        this.p1 = p1;
        this.p2 = p2;
    }

    public double pendiente() {
        if (p1.x == p2.x) {
            return Double.POSITIVE_INFINITY; // Pendiente infinito para líneas verticales
        } else {
            return (p2.y - p1.y) / (p2.x - p1.x);
        }
    }
}

public class PuntosColineales {

    public static void main(String[] args) {
        // Leer puntos del usuario (ejemplo)
        /*int n = 5; // Número de puntos
        Punto[] puntos = new Punto[n];
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < n; i++) {
            System.out.print("Ingrese coordenadas del punto " + (i + 1) + ": ");
            double x = scanner.nextDouble();
            double y = scanner.nextDouble();
            puntos[i] = new Punto(x, y);
            */
            Punto[] puntos = {
                new Punto(1, 2),
                new Punto(2, 3),
                new Punto(3, 4),
                new Punto(4, 5),
                new Punto(5, 6),
                new Punto(1, 4), // Punto colineal con (2, 3) y (3, 4)
                new Punto(2, 5), // Punto colineal con (1, 2) y (3, 4)
                new Punto(3, 6), // Punto colineal con (1, 2) y (3, 4)
        
        };

        // Encontrar grupos de puntos colineales
        List<Set<Punto>> colineales = encontrarColineales(puntos);

        // Imprimir resultados
        if (colineales.isEmpty()) {
            System.out.println("No se encontraron grupos de puntos colineales.");
        } else {
            System.out.println("Grupos de puntos colineales:");
            for (Set<Punto> grupo : colineales) {
                System.out.print("[");
                for (Punto punto : grupo) {
                    System.out.print("(" + punto.x + ", " + punto.y + "), ");
                }
                System.out.println("]");
            }
        }
    }

    static List<Set<Punto>> encontrarColineales(Punto[] puntos) {
        
        List<Set<Punto>> colineales = new ArrayList<>();
        HashMap<Double, Set<Punto>> pendientes = new HashMap<>(); // Mapa para almacenar puntos con la misma pendiente

        for (int i = 0; i < puntos.length; i++) {
            for (int j = i + 1; j < puntos.length; j++) {
                Linea linea = new Linea(puntos[i], puntos[j]);
                double pendiente = linea.pendiente();

                // Agregar puntos al mapa de pendientes
                Set<Punto> conjuntoPendiente = pendientes.getOrDefault(pendiente, new HashSet<>());
                conjuntoPendiente.add(puntos[i]);
                conjuntoPendiente.add(puntos[j]);
                pendientes.put(pendiente, conjuntoPendiente);
            }
        }

        // Identificar grupos de 4 o más puntos colineales
        for (Set<Punto> conjuntoPendiente : pendientes.values()) {
            if (conjuntoPendiente.size() >= 4) {
                colineales.add(conjuntoPendiente);
            }
        }

        return colineales;
    }
}
