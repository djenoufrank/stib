package atl.StibRide.repository;

import atl.StibRide.dto.PairDto;
import atl.StibRide.dto.StopsDto;
import atl.StibRide.exception.RepositoryException;
import atl.StibRide.jdbc.StopsDao;
import java.util.List;

/**
 *repository for stops
 * @author g55301
 */
public class StopsRepository implements Repository<PairDto, StopsDto>{
     private final StopsDao dao;

     /**
      * constructor for stops repository
      * 
      * @throws RepositoryException 
      */
    public StopsRepository() throws RepositoryException {
        dao=StopsDao.getInstance();
    }

      /**
      * default constructor for stops repository
      * 
      */
    public StopsRepository(StopsDao dao) {
        this.dao = dao;
    }
    

    @Override
    public PairDto add(StopsDto item) throws RepositoryException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void remove(PairDto pairKey) throws RepositoryException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<StopsDto> getAll() throws RepositoryException {
        return dao.selectAll();
    }

    @Override
    public StopsDto get(PairDto pairKey) throws RepositoryException {
       StopsDto line= dao.select(pairKey);
       return line;
    }

    @Override
    public boolean contains(PairDto pairKey) throws RepositoryException {
       StopsDto refreshItem = dao.select(pairKey);
        return refreshItem != null;
    }    

    @Override
    public void update(StopsDto item) throws RepositoryException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public String selectAllLineInOneStation(int key){
        return dao.selectAllLineInOneStation(key);
    }
}
