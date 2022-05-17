package atl.StibRide.dto;

/**
 *class to save stations
 * @author g55301
 */
public class StationsDto extends Dto<Integer> {

    private String name;

    public StationsDto(Integer key) {
        super(key);
    }

    /**
     * Creates a new instance of StationsDto.
     *
     * @param key id of the the station.
     * @param name of the station.
     */
    public StationsDto(Integer key, String name) {
        super(key);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "StationsDto{" + "name=" + name + '}';
    }

}
