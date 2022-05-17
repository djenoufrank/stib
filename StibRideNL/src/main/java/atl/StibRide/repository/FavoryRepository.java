/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atl.StibRide.repository;

import atl.StibRide.dto.FavoryDto;
import atl.StibRide.exception.RepositoryException;
import atl.StibRide.jdbc.FavoryDao;
import java.util.List;

/**
 * class repository for favory
 * @author g55301
 */
public class FavoryRepository implements Repository<String, FavoryDto>{
     private final FavoryDao dao;

       /**
     * constructor of favory repository
     *
     * @throws exception.RepositoryException
     */
    public FavoryRepository() throws RepositoryException {
        dao=FavoryDao.getInstance();
    }

    
     @Override
 public String add(FavoryDto item) throws RepositoryException {
        String key = item.getKey();
        if (contains(item.getKey())) {
            dao.update(item);
        } else {
            key = dao.insert(item);
        }
        return key;
    }

    @Override
    public void remove(String key) throws RepositoryException {
        dao.delete(key);
    }

    @Override
    public List<FavoryDto> getAll() throws RepositoryException {
        return dao.selectAll();
    }

    @Override
    public FavoryDto get(String key) throws RepositoryException {
        FavoryDto refreshItem = dao.select(key);
        return refreshItem;
    }

    @Override
    public boolean contains(String key) throws RepositoryException {
        FavoryDto refreshItem = dao.select(key);
        return refreshItem != null;
    }

    @Override
    public void update(FavoryDto item) throws RepositoryException {
       if(contains(item.getKey())){
           dao.update(item);
       }
    }
}