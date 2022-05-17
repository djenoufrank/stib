package atl.StibRide.dto;

/**
 *class to save favory
 * 
 * @author g55301
 */
public class FavoryDto extends Dto<String> {
    private String source;
    private String destination;

    public FavoryDto(String key) {
        super(key);
    }

    /**
     * Creates a new instance of FavoryDto.
     *
     * @param key id of the favory.
     * @param source of departure in the favory.
     * @param destination of arriving of the favory.
     */
    public FavoryDto(String key, String source, String destination) {
        super(key);
        
        this.source = source;
        this.destination = destination;
    }

    public String getSource() {
        return source;
    }

    public String getDestination() {
        return destination;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    @Override
    public String toString() {
        return "FavoryDto{source=" + source + ", destination=" + destination + '}';
    }

}