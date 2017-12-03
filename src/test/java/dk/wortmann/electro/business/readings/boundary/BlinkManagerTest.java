package dk.wortmann.electro.business.readings.boundary;

import dk.wortmann.electro.MockitoExtension;
import dk.wortmann.electro.business.blink.boundary.BlinkManager;
import dk.wortmann.electro.business.blink.enitity.Blink;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.TypedQuery;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BlinkManagerTest {

    @Mock
    private EntityManager emMock;

    private BlinkManager manager;

    @BeforeEach
    void setUp() {
        manager = new BlinkManager(emMock);
    }

    @Test
    @DisplayName("find blink by id")
    void findById() {
        when(emMock.merge(any(Blink.class))).thenReturn(new Blink());

        Blink result = manager.save(new Blink());

        assertNotNull(result);
    }

    @Test
    @DisplayName("delete blink by id")
    void delete() {
        Blink input = createTestBlink();

        when(emMock.getReference(any(), anyLong())).thenReturn(input);

        manager.delete(123L);

        verify(emMock, times(1)).remove(eq(input));
    }

    @Test
    @DisplayName("delete blink by id, with no existing entity")
    void delete_NoEntity() {
        Blink input = createTestBlink();

        when(emMock.getReference(any(), anyLong())).thenThrow(EntityNotFoundException.class);
        manager.delete(123L);

        verify(emMock, times(0)).remove(eq(input));
    }

    @Test
    @DisplayName("find all blinks")
    void all() {
        Blink inputBlink = createTestBlink();
        List<Blink> input = Collections.singletonList(inputBlink);

        TypedQuery<Blink> typedQueryMock = mock(TypedQuery.class);
        when(emMock.createNamedQuery(eq(Blink.findAll), eq(Blink.class))).thenReturn(typedQueryMock);
        when(typedQueryMock.getResultList()).thenReturn(input);

        List<Blink> result = manager.all();

        assertEquals(1, result.size());
        assertEquals(inputBlink, result.get(0));
    }

    @Test
    @DisplayName("save blink")
    void save() {
        Blink input = createTestBlink();

        when(emMock.merge(eq(input))).thenReturn(input);

        Blink result = manager.save(input);

        verify(emMock, times(1)).merge(eq(input));
        assertEquals(input, result);
    }

    protected Blink createTestBlink() {
        Blink blink = new Blink();
        blink.setId(1234L);
        blink.setInsertedTime(LocalDateTime.now());
        blink.setLightRatio(10.4);
        blink.setLightValue(12);
        blink.setKwhValue(0.0001);
        blink.setMeterId(99886L);

        return blink;
    }
}