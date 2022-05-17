package atl.StibRide.model;

import atl.StibRide.dto.StationsDto;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * class to generate path
 *
 * @author g55301
 */
public class DijkstraAlgorithm {

    private final List<StationsDto> nodes;
    private final List<Edge> edges;
    private Set<StationsDto> settledNodes;
    private Set<StationsDto> unSettledNodes;
    private Map<StationsDto, StationsDto> predecessors;
    private Map<StationsDto, Integer> distance;

    /**
     * constructor of dijkstraAlgorithm
     *
     * @param graph represents the stib metro network.
     */
    public DijkstraAlgorithm(Graph graph) {
        //create a copy of the array so that we can operate on this array
        this.nodes = new ArrayList<>(graph.getStations());
        this.edges = new ArrayList<>(graph.getEdges());
    }

    /**
     * gets the smallest distances between source and others
     *
     * @param source given source
     */
    public void execute(StationsDto source) {
        settledNodes = new HashSet<>();
        unSettledNodes = new HashSet<>();
        distance = new HashMap<>();
        predecessors = new HashMap<>();
        distance.put(source, 0);
        unSettledNodes.add(source);
        while (unSettledNodes.size() > 0) {
            StationsDto node = getMinimum(unSettledNodes);
            settledNodes.add(node);
            unSettledNodes.remove(node);
            findMinimalDistances(node);
        }
    }

    /**
     * find minimal distance to given node
     *  
     * @param node given dto station
     */
    private void findMinimalDistances(StationsDto node) {
        List<StationsDto> adjacentNodes = getNeighbors(node);
        for (StationsDto target : adjacentNodes) {
            if (getShortestDistance(target) > getShortestDistance(node)
                    + getDistance(node, target)) {
                distance.put(target, getShortestDistance(node)
                        + getDistance(node, target));
                predecessors.put(target, node);
                unSettledNodes.add(target);
            }
        }
    }

     /**
     * gets distance between two nodes
     *  
     * @param node given dto station  start 
     * @param target given dto station end
     */
    private int getDistance(StationsDto node, StationsDto target) {
        for (Edge edge : edges) {
            if (edge.getSource().equals(node)
                    && edge.getDestination().equals(target)) {
                return edge.getWeight();
            }
        }
        throw new RuntimeException("Should not happen");
    }

      /**
     * gets neighbors
     *  
     * @param node given dto station  start 
     */
    private List<StationsDto> getNeighbors(StationsDto node) {
        List<StationsDto> neighbors = new ArrayList<StationsDto>();
        for (Edge edge : edges) {
            if (edge.getSource().equals(node)
                    && !isSettled(edge.getDestination())) {
                neighbors.add(edge.getDestination());
            }
        }
        return neighbors;
    }

       /**
     * gets station that's in minimum distance between stations
     *  
     * @param node given dto station  start 
     * @param target given dto station end
     */
    private StationsDto getMinimum(Set<StationsDto> stations) {
        StationsDto minimum = null;
        for (StationsDto station : stations) {
            if (minimum == null) {
                minimum = station;
            } else {
                if (getShortestDistance(station) < getShortestDistance(minimum)) {
                    minimum = station;
                }
            }
        }
        return minimum;
    }

       /**
     * says if choosen station is on settledNodes
     *  
     * @param station given dto station 
     */
    private boolean isSettled(StationsDto station) {
        return settledNodes.contains(station);
    }

        /**
     * gets shortest distance between node and destination
     *  
     * @param node given dto station  start 
     * @param target given dto station end
     */
    private int getShortestDistance(StationsDto destination) {
        Integer d = distance.get(destination);
        if (d == null) {
            return Integer.MAX_VALUE;
        } else {
            return d;
        }
    }

    /*
     * This method returns the path from the source to the selected target and
     * NULL if no path exists
     */
    public LinkedList<StationsDto> getPath(StationsDto target) {
        LinkedList<StationsDto> path = new LinkedList<StationsDto>();
        StationsDto step = target;
        // check if a path exists
        if (predecessors.get(step) == null) {
            return null;
        }
        path.add(step);
        while (predecessors.get(step) != null) {
            step = predecessors.get(step);
            path.add(step);
        }
        // Put it into the correct order
        Collections.reverse(path);
        return path;
    }
}
