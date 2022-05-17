package atl.StibRide.jdbc;

import atl.StibRide.dto.PairDto;
import atl.StibRide.dto.StopsDto;
import atl.StibRide.exception.RepositoryException;
import java.lang.module.ResolutionException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * allows us to access data from favoryDto of the table of the database.
 *
 * @author g55301
 */
public class StopsDao implements Dao<PairDto, StopsDto> {

    private  Connection connexion;

    /**
     * default constructor of favoriteDao.
     *
     * @throws exception.RepositoryException
     */
    public StopsDao() throws RepositoryException {
        connexion = DBManager.getInstance().getConnection();
    }

    /**
     * allows to get an instance of the class for security reasons.
     *
     * @return the class instance of this;
     * @throws exception.RepositoryException
     */
    public static StopsDao getInstance() throws RepositoryException {
        return StopsDaoHolder.getInstance();
    }

    @Override
    public List<StopsDto> selectAll() throws RepositoryException {
        String sql = "SELECT id_line,id_station,id_order FROM STOPS";
        List<StopsDto> ourDto = new ArrayList<>();

        try {
            Statement stmt = connexion.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                ourDto.add(new StopsDto(rs.getInt(1), rs.getInt(2), rs.getInt(3)));
            }
        } catch (SQLException e) {
            throw new RepositoryException(e);
        }
        return ourDto;
    }

    @Override
    public StopsDto select(PairDto pairKey) throws RepositoryException {
        if (pairKey == null) {
            throw new RepositoryException("wrong keys pair combination");
        }
        String sql = "SELECT id_line,id_station,id_order FROM STOPS WHERE id_line=? AND id_station=?";
        StopsDto dto = null;
        try {
            PreparedStatement pstmt = connexion.prepareStatement(sql);
            pstmt.setInt(1, pairKey.getId_line());
            pstmt.setInt(2, pairKey.getId_station());
            ResultSet rs = pstmt.executeQuery();
            int count = 0;
            while (rs.next()) {
                dto = new StopsDto(rs.getInt(1), rs.getInt(2), rs.getInt(3));
                count++;
            }
            if (count > 1) {
                throw new ResolutionException("too many keys between " + pairKey.getId_line() + " and " + pairKey.getId_station());
            }
        } catch (ResolutionException | SQLException e) {
            throw new RepositoryException(e);
        }
        return dto;
    }

    @Override
    public PairDto insert(StopsDto item) throws RepositoryException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(PairDto pairKey) throws RepositoryException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(StopsDto item) throws RepositoryException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String selectAllLineInOneStation(int key) {
        String sql = "SELECT id_line FROM STOPS WHERE id_station=?";
        String lines = "[";
        try {
            PreparedStatement pstmt = connexion.prepareStatement(sql);
            pstmt.setInt(1, key);
            ResultSet result = pstmt.executeQuery();
            while (result.next()) {
                lines += result.getString(1) + " ";
            }
            lines += "]";
        } catch (ResolutionException | SQLException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
        return lines;
    }

    /**
     * creates a instance of the FavoriteDao for security reasons.
     */
    private static class StopsDaoHolder {

        private static StopsDao getInstance() throws RepositoryException {
            return new StopsDao();
        }
    }
}
