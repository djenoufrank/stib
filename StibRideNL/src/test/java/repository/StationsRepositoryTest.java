package repository;

import atl.StibRide.dto.StationsDto;
import atl.StibRide.exception.RepositoryException;
import atl.StibRide.jdbc.StationsDao;
import atl.StibRide.repository.StationsRepository;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.times;
import org.mockito.junit.jupiter.MockitoExtension;


/**
 *
 * @author g55301
 */
@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
public class StationsRepositoryTest {

    @Mock
    private StationsDao mock;

    private final StationsDto choice1;

    private final StationsDto choice2;
    
    private static final int KEY = 8012;

    private final List<StationsDto> all;

    public StationsRepositoryTest() {
        System.out.println("StationsRepositoryTest Constructor");
        //Test data
        choice1 = new StationsDto(KEY, "DE BROUCKERE");
        choice2 = new StationsDto(8112, "TOMBERG");

        all = new ArrayList<>();
        all.add(choice1);
        all.add(choice2);
    }

    @BeforeEach
    void init() throws RepositoryException {
        System.out.println("==== BEFORE EACH =====");
        //Mock behaviour
        Mockito.lenient().when(mock.select(choice1.getKey())).thenReturn(choice1);
        Mockito.lenient().when(mock.select(choice2.getKey())).thenReturn(null);
        Mockito.lenient().when(mock.selectAll()).thenReturn(all);
        Mockito.lenient().when(mock.select(null)).thenThrow(RepositoryException.class);
    }

    @Test
    public void testGetExist() throws Exception {
        System.out.println("testGetExist");
        //Arrange
        StationsDto expected = choice1;
        StationsRepository repository = new StationsRepository(mock);
        //Action
        StationsDto result = repository.get(KEY);
        //Assert        
        assertEquals(expected, result);
        Mockito.verify(mock, times(1)).select(KEY);
    }

   /* @Test
    public void testGetNotExist() throws Exception {
        System.out.println("testGetNotExist");
        //Arrange
        StationsRepository repository = new StationsRepository(mock);
        //Action
        StationsDto result = repository.get(choice2.getKey());
        //Assert        
        assertNull(result);
        Mockito.verify(mock, times(1)).select(choice2.getKey());
    }*/

    @Test
    public void testGetIncorrectParameter() throws Exception {
        System.out.println("testGetIncorrectParameter");
        //Arrange
        StationsRepository repository = new StationsRepository(mock);
        Integer incorrect = null;
        //Assert
        assertThrows(RepositoryException.class, () -> {
            //Action
            repository.get(incorrect);
        });
        Mockito.verify(mock, times(1)).select(null);
    }   
}
