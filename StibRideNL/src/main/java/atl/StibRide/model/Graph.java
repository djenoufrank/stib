package atl.StibRide.model;

import atl.StibRide.dto.StationsDto;
import java.util.List;

/**
 * class to build one and only one graph
 * @author g55301
 */
public class Graph {
        private final List<StationsDto> stations;
        private final List<Edge> edges;

         /**
     * constructor of one graph depends to given stations and edges
     *
     * @param stations given stations
     * @param edges given edges
     */
    public Graph(List<StationsDto> stations, List<Edge> edges) { 
        this.stations = stations;
        this.edges = edges;
    }

    public List<StationsDto> getStations() {
        return stations;
    }

    public List<Edge> getEdges() {
        return edges;
    }   
}
