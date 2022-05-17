package atl.StibRide.jdbc;

import atl.StibRide.dto.StationsDto;
import atl.StibRide.exception.RepositoryException;
import atl.StibRide.model.Edge;
import java.lang.module.ResolutionException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * allows us to access data from stationsDto
 *
 * @author g55301
 */
public class StationsDao implements Dao<Integer, StationsDto> {

    private  Connection connexion;

    /**
     * default constructor which will try the connection to the database.
     *
     */
    public StationsDao() throws RepositoryException {
        connexion = DBManager.getInstance().getConnection();
    }

    /**
     * allows to get an instance of the class for security reasons.
     *
     * @return the class instance of this;
     * @throws atl.StibRide.exception.RepositoryException
     */
    public static StationsDao getInstance() throws RepositoryException {
        return StationsDaoHolder.getInstance();
    }

    @Override
    public List<StationsDto> selectAll() throws RepositoryException {
        String sql = "SELECT id,name FROM STATIONS";
        List<StationsDto> ourDto = new ArrayList<>();

        try {
            Statement stmt = connexion.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                ourDto.add(new StationsDto(rs.getInt(1), rs.getString(2)));
            }
        } catch (SQLException e) {
            throw new RepositoryException(e);
        }
        return ourDto;
    }

    @Override
    public StationsDto select(Integer key) throws RepositoryException {
        if (key == null) {
            throw new RepositoryException("wrong key");
        }
        String sql = "SELECT id,name FROM STATIONS WHERE id=?";
        StationsDto dto = null;
        try {
            PreparedStatement pstmt = connexion.prepareStatement(sql);
            pstmt.setInt(1, key);
            ResultSet rs = pstmt.executeQuery();
            int count = 0;
            while (rs.next()) {
                dto = new StationsDto(rs.getInt(1), rs.getString(2));
                count++;
            }
            if (count > 1) {
                throw new ResolutionException("too many key " + key);
            }
        } catch (ResolutionException | SQLException e) {
            throw new RepositoryException(e);
        }
        return dto;
    }

    @Override
    public Integer insert(StationsDto item) throws RepositoryException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Integer key) throws RepositoryException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(StationsDto item) throws RepositoryException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<Edge> selectAllEdge() throws RepositoryException {
        String sql = "SELECT * FROM (STOPS OPS1 JOIN STATIONS STA1 on "
                + "OPS1.id_station=STA1.id)S1 INNER JOIN(STOPS OPS2 JOIN"
                + " STATIONS STA2 on OPS2.id_station=STA2.id)S2 "
                + "on S1.id_line=S2.id_line WHERE (S1.id_order - 1 >0 AND "
                + "S2.id_order=S1.id_order - 1) OR (S1.id_order + 1<(SELECT "
                + "max(SS1.id_order)FROM STOPS SS1 JOIN STOPS SS2 on "
                + "SS1.id_line=SS2.id_line WHERE SS1.id_line=SS2.id_line)"
                + " AND S2.id_order=S1.id_order+1);";
        List<Edge> ourDto = new ArrayList<>();

        try {
            Statement stmt = connexion.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {

                String name = rs.getString(5) + rs.getString(10);
                StationsDto source = new StationsDto(rs.getInt(2), 
                        rs.getString(5));
                StationsDto destination = new StationsDto(rs.getInt(7), 
                        rs.getString(10));
                ourDto.add(new Edge(name, source, destination));
            }
        } catch (SQLException e) {
            throw new RepositoryException(e);
        }
        return ourDto;
    }

    public StationsDto selectGetName(String key) throws RepositoryException {
        if (key == null) {
            throw new RepositoryException("wrong key");
        }
        String sql = "SELECT id,name FROM STATIONS WHERE name=?";
        StationsDto dto = null;
        try {
            PreparedStatement pstmt = connexion.prepareStatement(sql);
            pstmt.setString(1, key);
            ResultSet rs = pstmt.executeQuery();
            int count = 0;
            while (rs.next()) {
                dto = new StationsDto(rs.getInt(1), rs.getString(2));
                count++;
            }
            if (count > 1) {
                throw new ResolutionException("too many key " + key);
            }
        } catch (ResolutionException | SQLException e) {
            throw new RepositoryException(e);
        }
        return dto;
    }

    /**
     * creates a instance of the FavoriteDao for security reasons.
     */
    private static class StationsDaoHolder {

        private static StationsDao getInstance() throws RepositoryException {
            return new StationsDao();
        }
    }
}
