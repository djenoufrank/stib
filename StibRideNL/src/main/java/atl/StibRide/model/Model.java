package atl.StibRide.model;

import atl.StibRide.config.ConfigManager;
import atl.StibRide.dto.FavoryDto;
import atl.StibRide.dto.StationsDto;
import atl.StibRide.exception.RepositoryException;
import atl.StibRide.pattern.Observable;
import atl.StibRide.pattern.Observer;
import atl.StibRide.repository.FavoryRepository;
import atl.StibRide.repository.StationsRepository;
import atl.StibRide.repository.StopsRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * mother class of model to gives answers of presenter questions
 * @author g55301
 */
public class Model implements Observable{
    private Research research;
    private final List<Observer> observers;
    private FavoryRepository favory;
    StationsRepository demo;
     /**
     * constructor of model
     *
     * @throws atl.StibRide.exception.RepositoryException
     */
    public Model() throws RepositoryException, IOException {
        research=new Research("","");  
         observers = new ArrayList<>();
         favory=new FavoryRepository();
            ConfigManager.getInstance().load();
            demo = new StationsRepository();
    }
    public FavoryRepository getFavory() {
        return favory;
    }

    

    public Research getResearch() {
        return research;
    }
    
    /**
     * research smalest path and notify presenter
     * @param origin given origin
     * @param end given destination
     * @throws IOException input output exception about database connction
     */
  public void compute(String origin,String end)  {
          List<StationsDto> stations = null;
       
        try {
            stations = demo.getAll();
       
            List<Edge> edges = demo.selectAllEdge();
            Graph graph=new Graph(stations,edges);
            DijkstraAlgorithm algo=new DijkstraAlgorithm(graph);
            
            StationsDto mySource = demo.selectGetName(origin);  
             algo.execute(mySource);
            StationsDto myDestination = demo.selectGetName(end); 
            List<StationsDto> list=algo.getPath(myDestination);
            StopsRepository stop=new StopsRepository();
            research=new Research("",""); 
            list.forEach((maList) -> {
                research.setTableResult(new PrintInformations(maList.getName(), stop.selectAllLineInOneStation(maList.getKey())));
              });
           } catch (RepositoryException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
      
               notifyObservers(research);
    }

    @Override
    public void addObserver(Observer obs) {
        observers.add(obs);
    }

    @Override
    public void notifyObservers(Object argument) {
        observers.forEach((observer) -> observer.update(this, argument));
    }

    public List<StationsDto> initStation() throws RepositoryException {
        List<StationsDto> stations = null;
     
           stations = demo.getAll();
        
        return stations;
    }
    
        public void insertion(String name, String origin,String destination) throws RepositoryException, IOException{       
        ConfigManager.getInstance().load();
                
        this.favory.add(new FavoryDto(name,origin, destination));
         notifyObservers(favory);
    }

    public void changeLangue(boolean langue) throws RepositoryException {
      
            demo.setLangue(langue);     
    }


    public void update(String favName, String origine, String destination) {
            FavoryRepository favory=null;
        try {
            favory = new FavoryRepository();
              ConfigManager.getInstance().load();
                favory.update(new FavoryDto(favName,origine,destination));
       
        } catch (RepositoryException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }

           notifyObservers(favory);
    }
    
    //        public static void main(String[] args) throws RepositoryException, IOException {
//
//        Model m=new Model();
//       
//            m.insertion("ss", "qsq","qsdqsd");
//        
//    }

    public void delete(String favorySelected) {
               FavoryRepository favory=null;
               if(favorySelected!=null){
        try {
            favory = new FavoryRepository();
              ConfigManager.getInstance().load();
                favory.remove(favorySelected);
       
        } catch (RepositoryException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
 notifyObservers(favory);
               }
    }
}
