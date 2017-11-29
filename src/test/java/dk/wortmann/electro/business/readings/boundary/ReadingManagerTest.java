package dk.wortmann.electro.business.readings.boundary;

import dk.wortmann.electro.MockitoExtension;
import dk.wortmann.electro.business.readings.enitity.Reading;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
//@RunWith(JUnitPlatform.class)
class ReadingManagerTest {

    @Mock
    private EntityManager emMock;

    private ReadingManager manager;

    @BeforeEach
    void setUp() {
        manager = new ReadingManager(emMock);
    }

    @Test
    void findById() {
        when(emMock.merge(any(Reading.class))).thenReturn(new Reading());

        Reading result = manager.save(new Reading());

        assertNotNull(result);
    }

    @Test
    void delete() {
        Reading input = new Reading();
        input.setId(123L);

        when(emMock.getReference(any(), anyLong())).thenReturn(input);

        manager.delete(123L);

        verify(emMock, times(1)).remove(eq(input));
    }

    @Test
    void all() {
        Reading inputReading = new Reading();
        inputReading.setId(123L);
        List<Reading> input = Collections.singletonList(inputReading);

        TypedQuery typedQueryMock = mock(TypedQuery.class);
        when(emMock.createNamedQuery(eq(Reading.findAll), eq(Reading.class))).thenReturn(typedQueryMock);
        when(typedQueryMock.getResultList()).thenReturn(input);

        List<Reading> result = manager.all();

        assertEquals(1, result.size());
        assertEquals(inputReading, result.get(0));
    }

    @Test
    void save() {
        Reading inputReading = new Reading();
        inputReading.setId(123L);

        when(emMock.merge(eq(inputReading))).thenReturn(inputReading);

        Reading result = manager.save(inputReading);

        verify(emMock, times(1)).merge(eq(inputReading));
        assertEquals(inputReading, result);

    }
}