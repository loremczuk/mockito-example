package pl.oremczuk.mockito;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ServiceTest {

    @Mock
    private Database database;

    @InjectMocks
    private Service service;

    @Spy
    private List<String> someList = new ArrayList<String>();

    @Captor
    ArgumentCaptor<String> captor;


    @Test
    public void shouldExecuteQueryWhenDatabaseIsConnected() {

        assertNotNull(database);
        Mockito.when(database.isConnected()).thenReturn(true);

        boolean query = service.query("* from *");
        assertTrue(query);

    }

    @Test
    public void shouldInjectSpyCorrectly() {

        someList.add("item1");
        someList.add("item2");
        someList.add("item3");

        Mockito.verify(someList).add("item1");
        Mockito.verify(someList).add("item2");
        Mockito.verify(someList).add("item3");

        assertEquals(3, someList.size());

        Mockito.doReturn(100).when(someList).size();
        assertEquals(100, someList.size());

    }

    @Test
    public void shouldCaptureAnArgumentCorrectly() {

        someList.add("first");

        Mockito.verify(someList).add(captor.capture());
        assertEquals("first", captor.getValue());

    }

}