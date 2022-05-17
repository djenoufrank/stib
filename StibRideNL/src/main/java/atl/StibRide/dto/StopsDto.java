package atl.StibRide.dto;

/**
 * class to manage stops
 * @author g55301
 */
public class StopsDto extends Dto<PairDto> {

    private final int id_order;

    public StopsDto(Integer id_line, Integer id_station, int id_order) {
        super(new PairDto(id_line, id_station));
        this.id_order = id_order;
    }

    public int getId_order() {
        return id_order;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final StopsDto other = (StopsDto) obj;
        return this.id_order == other.id_order;
    }

    @Override
    public String toString() {
        return "StopsDto{" + "id_order=" + id_order + '}';
    }

    
}
