package atl.StibRide.jdbc;

import atl.StibRide.dto.FavoryDto;
import atl.StibRide.exception.RepositoryException;
import java.lang.module.ResolutionException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *allows us to access data from favoryDto of the table of the database.
 * @author g55301
 */
public class FavoryDao implements Dao<String, FavoryDto> {

    private final Connection connexion;

    /**
     * constructor of favoriteDao.
     *
     */
    public FavoryDao() throws RepositoryException {
        connexion = DBManager.getInstance().getConnection();
    }

    /**
     * allows to get an instance of the class for security reasons.
     *
     * @return the class instance of this;
     */
    public static FavoryDao getInstance() throws RepositoryException {
        return FavoryDaoHolder.getInstance();
    }

    @Override
    public List<FavoryDto> selectAll() throws RepositoryException {
        String sql = "SELECT name,source,destination FROM FAVORY";
        List<FavoryDto> ourDto = new ArrayList<>();

        try {
            Statement stmt = connexion.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                ourDto.add(new FavoryDto(rs.getString(1), rs.getString(2),
                        rs.getString(3)));
            }
        } catch (Exception e) {
            throw new RepositoryException(e);
        }
        return ourDto;
    }

    @Override
    public FavoryDto select(String key) throws RepositoryException {
        if (key == null) {
            throw new RepositoryException("wrong keys");
        }
        String sql = "SELECT name,source,destination FROM FAVORY WHERE name=?";
        FavoryDto dto = null;
        try {
            PreparedStatement pstmt = connexion.prepareStatement(sql);
            pstmt.setString(1, key);
            ResultSet rs = pstmt.executeQuery();
            int count = 0;
            while (rs.next()) {
                dto = new FavoryDto(rs.getString(1), rs.getString(2), rs.getString(3));
                count++;
            }
            if (count > 1) {
                throw new ResolutionException("too many keys between " + key);
            }
        } catch (ResolutionException | SQLException e) {
            throw new RepositoryException(e);
        }
        return dto;
    }

    @Override
    public void delete(String key) throws RepositoryException {
        try {
            if (key == null) {
                throw new RepositoryException("wrong keys");
            }
            String query = "DELETE FROM FAVORY WHERE name=?";

            PreparedStatement pstmt = connexion.prepareStatement(query);
            pstmt.setString(1, key);
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(FavoryDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String insert(FavoryDto item) throws RepositoryException {
        try {
            if (item.getKey() == null) {
                throw new RepositoryException("wrong keys");
            }
            String query = "INSERT INTO FAVORY(name,source,destination) "
                    + "values(?,?,?)";
            PreparedStatement pstmt = connexion.prepareStatement(query);
            pstmt.setString(1, item.getKey());
            pstmt.setString(2, item.getSource());
            pstmt.setString(3, item.getDestination());
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(FavoryDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return item.getKey();
    }

    @Override
    public void update(FavoryDto item) throws RepositoryException {
        try {
            if (item.getKey() == null) {
                throw new RepositoryException("wrong keys");
            }
            String query = "UPDATE FAVORY SET source=?,destination=? "
                    + "WHERE name=?";
            PreparedStatement pstmt = connexion.prepareStatement(query);
            pstmt.setString(1, item.getSource());
            pstmt.setString(2, item.getDestination());
            pstmt.setString(3, item.getKey());
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(FavoryDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

      /**
     * creates a instance of the FavoriteDao for security reasons.
     */
    private static class FavoryDaoHolder {

        private static FavoryDao getInstance() throws RepositoryException {
            return new FavoryDao();
        }
    }
}
