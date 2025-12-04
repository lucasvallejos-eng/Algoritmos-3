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
 * Tarea 6 Grupo 13 Ejercicio 6
 * Integrantes:      Lucas Vallejos   CI: 5439993
 *                          Ivan Figueredo   CI: 6743661
 */
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DirectedMultigraph;

public class Transpuesta {

    private Graph<Integer, DefaultEdge> graph;

    // Constructor
    public Transpuesta(int v) {
        graph = new DirectedMultigraph<>(DefaultEdge.class);
        for (int i = 0; i < v; i++) {
            graph.addVertex(i);
        }
    }

    // Método para añadir una arista al grafo
    public void addEdge(int v, int w) {
        graph.addEdge(v, w);
    }

    // Método para obtener la transpuesta del grafo
    public Transpuesta getTranspose() {
        Transpuesta transposedGraph = new Transpuesta(graph.vertexSet().size());
        for (Integer v : graph.vertexSet()) {
            for (DefaultEdge edge : graph.outgoingEdgesOf(v)) {
                Integer target = graph.getEdgeTarget(edge);
                transposedGraph.addEdge(target, v);
            }
        }
        return transposedGraph;
    }

    // Método para imprimir el grafo como matriz de adyacencia
    public void printGraph() {
        System.out.print("    ");
        for (Integer vertex : graph.vertexSet()) {
            System.out.print(vertex + "   ");
        }
        System.out.println();

        for (Integer v1 : graph.vertexSet()) {
            System.out.print(v1 + " ");
            for (Integer v2 : graph.vertexSet()) {
                if (graph.containsEdge(v1, v2)) {
                    System.out.print("  *  ");
                } else {
                    System.out.print("  0  ");
                }
            }
            System.out.println();
        }
    }

    // Main para probar el método
    public static void main(String[] args) {
        Transpuesta g = new Transpuesta(5);

        g.addEdge(0, 1);
        g.addEdge(0, 4);
        g.addEdge(1, 2);
        g.addEdge(1, 3);
        g.addEdge(1, 4);
        g.addEdge(2, 3);
        g.addEdge(3, 4);

        System.out.println("Original Graph:");
        g.printGraph();

        Transpuesta gT = g.getTranspose();

        System.out.println("Transpuesta Graph:");
        gT.printGraph();
    }
}
