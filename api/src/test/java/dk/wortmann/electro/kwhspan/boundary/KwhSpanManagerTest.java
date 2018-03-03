package dk.wortmann.electro.kwhspan.boundary;

import dk.wortmann.electro.kwhspan.entity.KwhSpan;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class KwhSpanManagerTest {

    @Mock
    EntityManager emMock;

    KwhSpanManager manager;

    @Before
    public void setUp() {
        this.manager = new KwhSpanManager(emMock);
    }

    @Test
    public void all() {
        KwhSpan inputKwhSpan = createTestKwhSpan();
        List<KwhSpan> input = Collections.singletonList(inputKwhSpan);

        TypedQuery<KwhSpan> typedQueryKwhSpan = mock(TypedQuery.class);
        when(emMock.createNamedQuery(eq(KwhSpan.findAll), eq(KwhSpan.class))).thenReturn(typedQueryKwhSpan);
        when(typedQueryKwhSpan.getResultList()).thenReturn(input);

        List<KwhSpan> result = manager.all();

        assertEquals(1, result.size());
        assertEquals(inputKwhSpan, result.get(0));
    }

    protected KwhSpan createTestKwhSpan() {
        KwhSpan kwhSpan = new KwhSpan();
//        kwhSpan.setKwhSum(0.04);
//        kwhSpan.setSpanStart(LocalDateTime.now());

        return kwhSpan;
    }
}