package atl.StibRide.model;

import atl.StibRide.dto.StationsDto;

/**
 * class to build one edge
 * @author g55301
 */
public class Edge {

    private final String name;
    private final StationsDto source;
    private final StationsDto destination;
    private final int weight = 1;

    public StationsDto getSource() {
        return source;
    }

    public StationsDto getDestination() {
        return destination;
    }
 /**
     * constructor of one edge
     *
     * @param name given name
     * @param source given source
     * @param destination given destination
     */
    public Edge(String name, StationsDto source, StationsDto destination) {
        this.name = name;
        this.source = source;
        this.destination = destination;
    }

    @Override
    public String toString() {
        return "Edge{" + "source=" + source.getName() + ", destination=" + destination.getName() + '}';
    }

    public String getName() {
        return name;
    }

    public int getWeight() {
        return weight;
    }

}
