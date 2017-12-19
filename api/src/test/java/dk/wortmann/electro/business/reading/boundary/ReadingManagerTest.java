package dk.wortmann.electro.business.reading.boundary;

import dk.wortmann.electro.business.reading.entity.Reading;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ReadingManagerTest {

    @Mock
    EntityManager emMock;

    ReadingManager manager;

    @Before
    public void setUp() {
        manager = new ReadingManager(emMock);
    }

    @Test
    public void all() {
        Reading reading = new Reading();
        reading.setId(123L);

        // Mocks
        List<Reading> input = Collections.singletonList(reading);
        TypedQuery typedQueryMock = mock(TypedQuery.class);
        when(typedQueryMock.getResultList()).thenReturn(input);
        when(emMock.createNamedQuery(anyString(), eq(Reading.class))).thenReturn(typedQueryMock);

        List<Reading> result = manager.all();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(reading, result.get(0));
        verify(emMock, times(1)).createNamedQuery(eq(Reading.findAll), eq(Reading.class));

    }

    @Test
    public void save() {
        Reading input = createTestReading();

        when(emMock.merge(eq(input))).thenReturn(input);

        Reading result = manager.save(input);

        assertNotNull(result);
        assertEquals(input, result);
        verify(emMock, times(1)).merge(eq(input));
    }

    @Test
    public void findById() {
        Reading input = createTestReading();

        when(emMock.find(eq(Reading.class), eq(input.getId()))).thenReturn(input);

        Reading result = manager.findById(input.getId());

        assertEquals(input, result);
        verify(emMock, times(1)).find(eq(Reading.class), eq(input.getId()));
    }

    @Test
    public void delete() {
        Reading input = createTestReading();

        when(emMock.getReference(eq(Reading.class), eq(input.getId()))).thenReturn(input);

        manager.delete(input.getId());
        verify(emMock, times(1)).getReference(eq(Reading.class), eq(input.getId()));
        verify(emMock, times(1)).remove(eq(input));
    }

    public static Reading createTestReading() {
        Reading reading = new Reading();
        reading.setId(123L);
        reading.setInsertedTime(LocalDateTime.now());
        reading.setMeterId(99886);
        reading.setKwhValue(123.12);

        return reading;
    }
}