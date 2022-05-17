package atl.StibRide.model;


/**
 * class to gives to main table 
 * @author g55301
 */
public class PrintInformations {
    private final  String station;
    private final  String line;

     /**
     * constructor of Print information 
     *
     * @param station given station
     * @param line given line
     */
    public PrintInformations(String station, String line) {
        this.station = station;
        this.line = line;
    }

    public String getStation() {
        return station;
    }

    public String getLine() {
        return line;
    }   

    @Override
    public String toString() {
        return "PrintInformations{" + "station=" + station + ", line=" + line + '}';
    }
    
}
