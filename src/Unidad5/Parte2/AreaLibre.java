/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Unidad5.Parte2;

/**
 *
 * @author lucas
 */


/**
 * Tarea 6 Grupo 13 Ejercicio 7 
 * Integrantes:      Lucas Vallejos   CI: 5439993
 *                          Ivan Figueredo   CI: 6743661
 */


import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.*;

public class AreaLibre {

    static int[][] directions = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    static int filas, columnas;
    static char[][] grid;
    static boolean[][] visitado;
    static Graph<String, DefaultEdge> graph;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el nombre del archivo de entrada: ");
       // String filename = scanner.nextLine()+"Unidad5/Parte2";
        String nombreArchivo = scanner.nextLine();
       
        // Imprimir la ruta actual de trabajo
        String currentDir = Paths.get("").toAbsolutePath().toString();
        //System.out.println("Directorio de trabajo actual: " + currentDir);
      
        String filename = currentDir+ "/src/Unidad5/Parte2/" + nombreArchivo+".txt";
     
        //System.out.print(filename);
        
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line = br.readLine();
            String[] dimensions = line.split(" ");
            filas = Integer.parseInt(dimensions[0]);
            columnas = Integer.parseInt(dimensions[1]);

            grid = new char[filas][columnas];
            visitado = new boolean[filas][columnas];
            graph = new SimpleGraph<>(DefaultEdge.class);

            for (int i = 0; i < filas; i++) {
                line = br.readLine();
                for (int j = 0; j < columnas; j++) {
                    grid[i][j] = line.charAt(j);
                    if (grid[i][j] == '.') {
                        String vertex = i + "," + j;
                        graph.addVertex(vertex);
                    }
                }
            }

            for (int i = 0; i < filas; i++) {
                for (int j = 0; j < columnas; j++) {
                    if (grid[i][j] == '.') {
                        String vertex = i + "," + j;
                        for (int[] dir : directions) {
                            int newX = i + dir[0];
                            int newY = j + dir[1];
                            if (newX >= 0 && newX < filas && newY >= 0 && newY < columnas && grid[newX][newY] == '.') {
                                String neighborVertex = newX + "," + newY;
                                graph.addEdge(vertex, neighborVertex);
                            }
                        }
                    }
                }
            }

            List<Integer> areas = new ArrayList<>();
            for (String vertex : graph.vertexSet()) {
                String[] coords = vertex.split(",");
                int x = Integer.parseInt(coords[0]);
                int y = Integer.parseInt(coords[1]);
                if (!visitado[x][y]) {
                    int areaSize = dfs(x, y);
                    areas.add(areaSize);
                }
            }

            Collections.sort(areas);
            System.out.println(areas.size());
            for (int area : areas) {
                System.out.print(area + " ");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static int dfs(int i, int j) {
        Stack<String> stack = new Stack<>();
        String startVertex = i + "," + j;
        stack.push(startVertex);
        visitado[i][j] = true;
        int areaSize = 0;

        while (!stack.isEmpty()) {
            String vertex = stack.pop();
            areaSize++;

            String[] coords = vertex.split(",");
            int x = Integer.parseInt(coords[0]);
            int y = Integer.parseInt(coords[1]);

            for (DefaultEdge edge : graph.edgesOf(vertex)) {
                String targetVertex = graph.getEdgeTarget(edge);
                if (targetVertex.equals(vertex)) {
                    targetVertex = graph.getEdgeSource(edge);
                }

                String[] targetCoords = targetVertex.split(",");
                int newX = Integer.parseInt(targetCoords[0]);
                int newY = Integer.parseInt(targetCoords[1]);

                if (!visitado[newX][newY]) {
                    stack.push(targetVertex);
                    visitado[newX][newY] = true;
                }
            }
        }

        return areaSize;
    }
}

