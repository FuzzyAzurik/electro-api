package dk.wortmann.electro.kwhspan.entity;

import dk.wortmann.electro.adaptor.LocalDateTimeXmlAdaptor;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDateTime;


@Entity
@NamedNativeQueries({
        @NamedNativeQuery(name = KwhSpan.findAll, query = "" +
                "SELECT\n" +
                "  sum(b.kwh_value),\n" +
                "  to_timestamp(floor((extract('epoch' FROM b.inserted_time) / 300)) * 300)\n" +
                "  AT TIME ZONE 'UTC' AS interval_alias\n" +
                "FROM blink b\n" +
                "GROUP BY interval_alias\n" +
                "ORDER BY interval_alias;")
})
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class KwhSpan {
    private static final String PREFIX = "kwhspan.entity.KwhSpan";
    public static final String findAll = PREFIX + ".findAll";

    @Id
    @Column(name = "SPAN_START")
    @XmlJavaTypeAdapter(value = LocalDateTimeXmlAdaptor.class)
    private LocalDateTime spanStart;

    @Column(name = "KWH_SUM")
    private double kwhSum;

    public KwhSpan() {
    }

    public LocalDateTime getSpanStart() {
        return spanStart;
    }

    public void setSpanStart(LocalDateTime spanStart) {
        this.spanStart = spanStart;
    }

    public double getKwhSum() {
        return kwhSum;
    }

    public void setKwhSum(double kwhSum) {
        this.kwhSum = kwhSum;
    }
}
