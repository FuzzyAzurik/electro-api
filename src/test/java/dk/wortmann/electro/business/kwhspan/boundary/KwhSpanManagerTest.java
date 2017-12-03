package dk.wortmann.electro.business.kwhspan.boundary;

import dk.wortmann.electro.MockitoExtension;
import dk.wortmann.electro.business.kwhspan.entity.KwhSpan;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class KwhSpanManagerTest {

    @Mock
    EntityManager emMock;

    KwhSpanManager manager;

    @BeforeEach
    void setUp() {
        this.manager = new KwhSpanManager(emMock);
    }

    @Test
    @DisplayName("find all kwhSpans")
    void all() {
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
        kwhSpan.setKwhSum(0.04);
        kwhSpan.setSpanStart(LocalDateTime.now());

        return kwhSpan;
    }
}