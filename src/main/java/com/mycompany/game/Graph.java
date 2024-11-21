/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 *
 * @author USER
 */

class Graph {
    private Map<State, List<State>> adjacencyList;

    public Graph() {
        adjacencyList = new HashMap<>();
    }

    // Add a state as a vertex
    public void addVertex(State state) {
        adjacencyList.putIfAbsent(state, new ArrayList<>());
    }

    // Add an edge between two states
    public void addEdge(State from, State to) {
        adjacencyList.putIfAbsent(from, new ArrayList<>());
        adjacencyList.putIfAbsent(to, new ArrayList<>());

        adjacencyList.get(from).add(to);
    }

    public Map<State, List<State>> getAdjacencyList() {
        return adjacencyList;
    }
public State findWinningStateDFS() {
        if (adjacencyList.isEmpty()) {
            return null; // No states to search
        }

        // Get the first key in the adjacency list
        State firstState = adjacencyList.keySet().iterator().next();

        Set<State> visited = new HashSet<>();
        return dfsHelper(firstState, visited);
    }

    private State dfsHelper(State current, Set<State> visited) {
        if (current.isIsWinning()) {
            return current;
        }

        visited.add(current);

        for (State neighbor : adjacencyList.getOrDefault(current, new ArrayList<>())) {
            if (!visited.contains(neighbor)) {
                State result = dfsHelper(neighbor, visited);
                if (result != null) {
                    return result;
                }
            }
        }

        return null;
    }

    // Breadth-First Search (BFS) starting from the first element in the graph
    public State findWinningStateBFS() {
        if (adjacencyList.isEmpty()) {
            return null; // No states to search
        }

        // Get the first key in the adjacency list
        State firstState = adjacencyList.keySet().iterator().next();

        Set<State> visited = new HashSet<>();
        Queue<State> queue = new LinkedList<>();

        queue.add(firstState);
        visited.add(firstState);

        while (!queue.isEmpty()) {
            State current = queue.poll();

            if (current.isIsWinning()) {
                return current;
            }

            for (State neighbor : adjacencyList.getOrDefault(current, new ArrayList<>())) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                }
            }
        }

        return null;
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<State, List<State>> entry : adjacencyList.entrySet()) {
            sb.append(entry.getKey()).append(" -> ").append(entry.getValue()).append("\n");
        }
        return sb.toString();
    }
}
