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
 * Tarea 6 Grupo 13 Ejercicio 9
 * Integrantes:      Lucas Vallejos   CI: 5439993
 *                          Ivan Figueredo   CI: 6743661
 */


import org.jgrapht.*;
import org.jgrapht.graph.*;
import java.util.*;

public class Ejercicio6_tarea9 {
    public static void main(String[] args){
        Graph<Integer, DefaultEdge> g = new DefaultUndirectedGraph<>(DefaultEdge.class);

        g.addVertex(1);
        g.addVertex(2);
        g.addVertex(3);
        g.addVertex(4);

        g.addEdge(1, 2);
        g.addEdge(2, 3);
        g.addEdge(3, 4);
        g.addEdge(1, 4);

        System.out.println(g.toString());

        Integer[] result = BFS(g, 1);

        if (result != null) {
            System.out.println("Numerado:");
            for (int i = 1; i < result.length; i++) {
                System.out.println("Vertex " + i + ": " + result[i]);
            }
            if(isCompleto(g, result, 1)){
                System.out.println("\nEl grafo bipartito es completo.\n");
            }
        } else {
            System.out.println("Conflict detected, graph is not bipartite.");
        }
    }

    public static Integer[] BFS(Graph<Integer, DefaultEdge> g, Integer s) {
        Map<Integer, String> color = new HashMap<>();
        Map<Integer, Integer> d = new HashMap<>();
        Map<Integer, Integer> pi = new HashMap<>();
        Integer[] numerado = new Integer[g.vertexSet().size() + 1];
        int numero = 1;

        for (Integer u : g.vertexSet()) {
            color.put(u, "Blanco");
            d.put(u, Integer.MAX_VALUE);
            pi.put(u, null);
            numerado[u] = 0;
        }

        color.put(s, "Gris");
        numerado[s] = numero;
        d.put(s, 0);
        pi.put(s, null);

        Queue<Integer> q = new LinkedList<>();
        q.add(s);

        while (!q.isEmpty()) {
            System.out.print(q.toString() + " ");
            Integer u = q.poll();
            numero = (numerado[u] == 1) ? 2 : 1;
            System.out.print("numero actual: " + numero + "\n");

            for (DefaultEdge edge : g.edgesOf(u)) {
                Integer v = g.getEdgeTarget(edge);
                if (v.equals(u)) {
                    v = g.getEdgeSource(edge);
                }

                if (color.get(v).equals("Blanco")) {
                    numerado[v] = numero;
                    color.put(v, "Gris");
                    d.put(v, d.get(u) + 1);
                    pi.put(v, u);
                    q.add(v);
                    if (numerado[u].equals(numerado[v])) {
                        System.out.print(u + " es " + numerado[u] + " ," + v + " es  " + numerado[v] + "\n");
                        // return null;
                    }
                }
                color.put(u, "Negro");
            }
        }
        return numerado;
    }

    public static boolean isCompleto(Graph<Integer, DefaultEdge> g, Integer[] result, Integer s) {
        Map<Integer, String> color = new HashMap<>();
        Map<Integer, Integer> d = new HashMap<>();
        Map<Integer, Integer> pi = new HashMap<>();
        Map<Integer, Integer> grado = new HashMap<>();
        Integer contColor1 = 0, contColor2 = 0;

        for (int i = 1; i < result.length; i++) {
            if (result[i] == 1) {
                contColor1++;
            } else {
                contColor2++;
            }
        }

        for (Integer u : g.vertexSet()) {
            color.put(u, "Blanco");
            d.put(u, Integer.MAX_VALUE);
            pi.put(u, null);
        }

        color.put(s, "Gris");
        d.put(s, 0);
        pi.put(s, null);

        Queue<Integer> q = new LinkedList<>();
        q.add(s);

        while (!q.isEmpty()) {
            Integer u = q.poll();
            Integer contGradoVertice = 0;
            for (DefaultEdge edge : g.edgesOf(u)) {
                contGradoVertice++;
                Integer v = g.getEdgeTarget(edge);
                if (v.equals(u)) {
                    v = g.getEdgeSource(edge);
                }
                if (color.get(v).equals("Blanco")) {
                    color.put(v, "Gris");
                    d.put(v, d.get(u) + 1);
                    pi.put(v, u);
                    q.add(v);
                }
            }
            color.put(u, "Negro");
            grado.put(u, contGradoVertice);

            if (!(grado.get(u).equals(contColor1) || grado.get(u).equals(contColor2))) {
                return false;
            }
        }
        return true;
    }
}
