package atl.StibRide.model;
import java.util.ArrayList;
import java.util.List;

/**
 *class to gives list of print information that we have to print in the table
 * @author g55301
 */
public class Research {
    private final List<PrintInformations> tableResult;
    private final String source;
    private final String destination;
    
    /**
     * constructor which takes the source and destination that user ill enter
     * @param source given source
     * @param destination given destinantion
     */
    public Research(String source, String destination) {
        tableResult=new ArrayList<>();
        this.source = source;
        this.destination = destination;
    }

    public String getSource() {
        return source;
    }

    public String getDestination() {
        return destination;
    }    
   
  

    public List<PrintInformations> getTableResult() {
        return tableResult;
    }

    /**
     * setter which takes one instance of printinformation
     * and add it to list of table result
     * @param info given instance of information
     */
    public void setTableResult(PrintInformations info) {
        this.tableResult.add(info);
    }


}

