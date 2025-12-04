/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Unidad5;

/**
 *
 * @author lucas
 */

/**
 * Tarea 6 Grupo 13 Ejercicio 8 
 * Integrantes:      Lucas Vallejos   CI: 5439993
 *                          Ivan Figueredo   CI: 6743661
 */

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultDirectedGraph;

import java.util.*;
import org.jgrapht.graph.DirectedMultigraph;

public class Escape {
    private static Graph<Integer, DefaultEdge> graph;
    private static boolean[] visitado;
    private static boolean[] enPila;
    private static List<Integer> islasAtrapadas;

    private static void dfs(int isla) {
        if (enPila[isla]) {
            throw new IllegalStateException("Ciclo detectado");
        }

        if (visitado[isla]) {
            return;
        }

        visitado[isla] = true;
        enPila[isla] = true;

        boolean estaAtrapada = true;
        for (DefaultEdge edge : graph.outgoingEdgesOf(isla)) {
            int vecino = graph.getEdgeTarget(edge);
            if (!visitado[vecino]) {
                dfs(vecino);
            }
            if (graph.outDegreeOf(vecino) > 0) {
                estaAtrapada = false;
            }
        }

        if (estaAtrapada) {
            islasAtrapadas.add(isla);
        }

        enPila[isla] = false;
    }

    /*public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Leer los datos de entrada
        System.out.print("Ingrese el número de islas: ");
        int numIslas = scanner.nextInt();
        System.out.print("Ingrese el número de puentes: ");
        int numPuentes = scanner.nextInt();
        System.out.print("Ingrese la isla inicial: ");
        int islaInicial = scanner.nextInt();

        graph = new DefaultDirectedGraph<>(DefaultEdge.class);

        for (int i = 0; i < numIslas; i++) {
            graph.addVertex(i);
        }

        System.out.println("Ingrese los puentes (origen y destino):");
        for (int i = 0; i < numPuentes; i++) {
            int de = scanner.nextInt();
            int a = scanner.nextInt();
            graph.addEdge(de, a);
        }

        // Inicializar estructuras para DFS
        visitado = new boolean[numIslas];
        enPila = new boolean[numIslas];
        islasAtrapadas = new ArrayList<>();

        try {
            dfs(islaInicial);
            Collections.sort(islasAtrapadas);
            for (int isla : islasAtrapadas) {
                System.out.print(isla + " ");
            }
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
        }
    }
}*/




    public static void main(String[] args) {
        // Datos de entrada ya cargados
        int numIslands = 5;
        int numBridges = 4;
        int startIsland = 1;
        int[][] bridges = {
            {1, 3},
            {1, 0},
            {1, 4},
            {0, 2}
        };

        graph = new DirectedMultigraph<>(DefaultEdge.class);

        for (int i = 0; i < numIslands; i++) {
            graph.addVertex(i);
        }

        for (int i = 0; i < numBridges; i++) {
            int from = bridges[i][0];
            int to = bridges[i][1];
            graph.addEdge(from, to);
        }

        // Inicializar estructuras para DFS
        visitado = new boolean[numIslands];
        enPila = new boolean[numIslands];
        islasAtrapadas = new ArrayList<>();

        try {
            dfs(startIsland);
            Collections.sort(islasAtrapadas);
            for (int island : islasAtrapadas) {
                System.out.print(island + " ");
            }
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
        }
    }
}

