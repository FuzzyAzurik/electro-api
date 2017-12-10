package dk.wortmann.electro.business.kwhspan.entity;

import dk.wortmann.electro.business.adaptor.LocalDateTimeAdaptor;
import dk.wortmann.electro.business.adaptor.LocalDateTimeXmlAdaptor;

import javax.inject.Named;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDateTime;


@Entity
@NamedNativeQueries({
        @NamedNativeQuery(name = KwhSpan.findAll, query = "SELECT \n" +
                "  TIMESTAMP(DATE_FORMAT(b.INSERTED_TIME, '%Y-%m-%d %H:%i:00')) AS SPAN_START,\n" +
                "  sum(b.KWH_VALUE)                                   AS KWH_SUM\n" +
                "FROM BLINK b\n" +
                "GROUP BY UNIX_TIMESTAMP(b.INSERTED_TIME) DIV 300, b.KWH_VALUE", resultClass = KwhSpan.class)
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
