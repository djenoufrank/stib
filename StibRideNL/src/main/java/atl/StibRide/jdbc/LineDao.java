package atl.StibRide.jdbc;

import atl.StibRide.dto.LineDto;
import atl.StibRide.exception.RepositoryException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * allows us to access data from line
 *
 * @author g55301
 */
public class LineDao implements Dao<Integer, LineDto> {

    private Connection connexion;

    /**
     * default constructor which will try the connection to the database.
     *
     * @throws exception.RepositoryException
     */
    public LineDao() throws RepositoryException {
        this.connexion = DBManager.getInstance().getConnection();
    }

    /**
     * allows to get an instance of the class for security reasons.
     *
     * @return the class instance;
     * @throws exception.RepositoryException
     */
    public static LineDao getInstance() throws RepositoryException {
        return LineDaoHolder.getInstance();
    }

    @Override
    public List<LineDto> selectAll() throws RepositoryException {
        String sql = "SELECT id FROM LINES";
        List<LineDto> ourDto = new ArrayList<>();
        try {
            Statement stmt = connexion.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                ourDto.add(new LineDto(rs.getInt(1)));
            }
        } catch (SQLException e) {
            throw new RepositoryException(e);
        }
        return ourDto;
    }

    @Override
    public LineDto select(Integer key) throws RepositoryException {
        if (key == null) {
            throw new RepositoryException("wrong key");
        }
        String sql = "SELECT id FROM LINES WHERE id = ?";
        LineDto dto = null;
        try {
            PreparedStatement pstmt = connexion.prepareStatement(sql);
            pstmt.setInt(1, key);
            ResultSet rs = pstmt.executeQuery();
            int count = 0;
            while (rs.next()) {
                dto = new LineDto(rs.getInt(1));
                count++;
            }
            if (count > 1) {
                throw new RepositoryException("key " + key + " not unique");
            }
        } catch (RepositoryException | SQLException e) {
            throw new RepositoryException(e);
        }
        return dto;
    }

    @Override
    public Integer insert(LineDto item) throws RepositoryException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Integer key) throws RepositoryException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(LineDto item) throws RepositoryException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * creates an instance of the class.
     */
    private static class LineDaoHolder {

        private static LineDao getInstance() throws RepositoryException {
            return new LineDao();
        }
    }

}
