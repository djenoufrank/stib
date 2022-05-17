package atl.StibRide.repository;

import atl.StibRide.dto.LineDto;
import atl.StibRide.exception.RepositoryException;
import atl.StibRide.jdbc.LineDao;
import java.util.List;

/**
 * class line repository from dao line
 * @author g55301
 */
public class LineRepository implements Repository<Integer, LineDto>{
    private final LineDao dao;

      /**
     * constructor for Line repository
     *
     * @throws exception.RepositoryException
     */
    public LineRepository() throws RepositoryException {
        dao=LineDao.getInstance();
    }

    public LineRepository(LineDao dao) {
        this.dao = dao;
    }

    @Override
    public List<LineDto> getAll() throws RepositoryException {
        return dao.selectAll();
    }

    @Override
    public LineDto get(Integer key) throws RepositoryException {
       LineDto line= dao.select(key);
       return line;
    }

    @Override
    public boolean contains(Integer key) throws RepositoryException {
          LineDto line = dao.select(key);
        return line != null;
    }

    @Override
    public void update(LineDto item) throws RepositoryException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Integer add(LineDto item) throws RepositoryException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void remove(Integer key) throws RepositoryException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}