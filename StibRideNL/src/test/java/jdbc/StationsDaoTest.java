package jdbc;

import atl.StibRide.config.ConfigManager;
import atl.StibRide.dto.StationsDto;
import atl.StibRide.exception.RepositoryException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

import atl.StibRide.jdbc.StationsDao;
import org.junit.jupiter.api.Test;

/**
 *
 * @author g55301
 */
public class StationsDaoTest {

    private final StationsDto choice1;
    private final StationsDto choice2;
    private static final int KEY = 8012;
    private final List<StationsDto> all;
    private StationsDao instance;
    
    public StationsDaoTest() {
        System.out.println("==== StationsDaoTest Constructor =====");
        choice1 = new StationsDto(KEY, "DE BROUCKERE");
        choice2 = new StationsDto(8112, "TOMBERG");

        all = new ArrayList<>();
        all.add(new StationsDto(8072, "MERODE"));
        all.add(new StationsDto(8082, "MONTGOMERY"));
        all.add(new StationsDto(8122, "ROODEBEEK"));
        all.add(new StationsDto(8152, "CRAINHEM"));
        all.add(new StationsDto(8202, "THIEFFRY"));
        all.add(choice1);

        try {
            ConfigManager.getInstance().load();
            instance = StationsDao.getInstance();
        } catch (RepositoryException | IOException ex) {
            org.junit.jupiter.api.Assertions.fail("Erreur de connection à la base de données de test", ex);
        }
    }

    @Test
    public void testSelectExist() throws Exception {
        System.out.println("testSelectExist");
        //Arrange
        StationsDto expected = choice1;
        //Action
        StationsDto result = instance.select(KEY);
        //Assert
        assertEquals(expected, result);
    }

//    @Test
//    public void testSelectNotExist() throws Exception {
//        System.out.println("testSelectNotExist");
//        //Arrange
//        //Action
//        StationsDto result = instance.select(choice2.getKey());
//        //Assert
//        assertNull(result);
//    }

    @Test
    public void testSelectIncorrectParameter() throws Exception {
        System.out.println("testSelectIncorrectParameter");
        //Arrange
        Integer incorrect = null;
        //Assert
        assertThrows(RepositoryException.class, () -> {
            //Action
            instance.select(incorrect);
        });
    }

}
