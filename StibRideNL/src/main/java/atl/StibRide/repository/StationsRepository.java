
package atl.StibRide.repository;

import atl.StibRide.dto.StationsDto;
import atl.StibRide.exception.RepositoryException;
import atl.StibRide.jdbc.StationsDao;
import atl.StibRide.jdbc.StationsNLDao;
import atl.StibRide.model.Edge;
import java.util.List;

/**
 * repository for stations
 * @author g55301
 */
public class StationsRepository implements Repository<Integer, StationsDto>{
     private  StationsDao dao;
     private  StationsNLDao daoNL;
     private boolean langue=false;
       /**
     * constructor for stations repositories
     *
     * @throws atl.StibRide.exception.RepositoryException
     */
    public StationsRepository() throws RepositoryException {
        dao=StationsDao.getInstance();
        daoNL=StationsNLDao.getInstance();
      
    }
    
    public StationsRepository(StationsDao dao) {
        this.dao = dao;
    }
     public StationsRepository(StationsNLDao daoNL) {
        this.daoNL = daoNL;
    }

    @Override
    public List<StationsDto> getAll() throws RepositoryException {
       if(langue==false){
           return dao.selectAll();
       }else{
           return daoNL.selectAll();
       }
    }

    @Override
    public StationsDto get(Integer key) throws RepositoryException {
      if(langue==false){
          StationsDto station= dao.select(key);
          return station;
      }else{
           StationsDto station= daoNL.select(key);
          return station;
      }
       
    }
    @Override
    public boolean contains(Integer key) throws RepositoryException {
       if(langue==false){
           StationsDto refreshItem = dao.select(key);
           return refreshItem != null;
       }else{
           StationsDto refreshItem = daoNL.select(key);
           return refreshItem != null;
       }
        
    }
    
    public List<Edge> selectAllEdge() throws RepositoryException{
    if(langue==false){
        return dao.selectAllEdge();
    }else{
        return daoNL.selectAllEdge();
    }
    }

    public StationsDto selectGetName(String key) throws RepositoryException{
           if(langue==false){
               return dao.selectGetName(key);
           }else{
               return daoNL.selectGetName(key);
           }
    }

    @Override
    public void update(StationsDto item) throws RepositoryException {
       if(langue==false){
           if(contains(item.getKey())){
           dao.update(item);
       }
       }
       else{
             if(contains(item.getKey())){
           daoNL.update(item);
       }
       }
    }
      public void setLangue(boolean langue) {
        this.langue=langue;
    }
    @Override
    public Integer add(StationsDto item) throws RepositoryException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void remove(Integer key) throws RepositoryException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
