package dk.wortmann.electro.blink.enitity;

import dk.wortmann.electro.adaptor.LocalDateTimeXmlAdaptor;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDateTime;


@Entity
@NamedNativeQueries({
        @NamedNativeQuery(name = BlinkGroup.findAll, query = "" +
                "SELECT\n" +
                "  to_timestamp(floor((extract('epoch' FROM b.inserted_time) / ?1)) * ?1)\n" +
                "  AT TIME ZONE 'UTC' AS START,\n" +
                "  sum(b.kwh_value) AS KWH_SUM\n" +
                "FROM blink b\n" +
                "GROUP BY START\n" +
                "ORDER BY START DESC;", resultClass = BlinkGroup.class)
})
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class BlinkGroup {
    private static final String PREFIX = "kwhspan.entity.BlinkGroup";
    public static final String findAll = PREFIX + ".findAll";

    @Id
    @Column(name = "START")
    @XmlJavaTypeAdapter(value = LocalDateTimeXmlAdaptor.class)
    private LocalDateTime start;

    @Column(name = "KWH_SUM")
    private double kwhSum;

    public BlinkGroup() {
    }

    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime spanStart) {
        this.start = spanStart;
    }

    public double getKwhSum() {
        return kwhSum;
    }

    public void setKwhSum(double kwhSum) {
        this.kwhSum = kwhSum;
    }
}
