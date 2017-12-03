package dk.wortmann.electro.business.kwhspan.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDateTime;

@Entity
@NamedQueries({
        @NamedQuery(name = "KwhSpan.getAll", query = "select " +
                "new dk.wortmann.electro.business.kwhspan.entity.KwhSpan(func('DATE_FORMAT', b.insertedTime, '%Y-%m-%d %H:%i:00'), sum(b.kwhValue)) " +
                "from Blink b group by func('DIV', func('UNIX_TIMESTAMP', b.insertedTime), 300), b.kwhValue")
})
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class KwhSpan {
    private static final String PREFIX = "kwhspan.entity.KwhSpan";
    public static final String findAll = PREFIX + "findAll";

    @Id
    private LocalDateTime spanStart;
    private double kwhSum;

    public KwhSpan() {
    }

    public KwhSpan(LocalDateTime start, double kwhSum) {
        this.spanStart = start;
        this.kwhSum = kwhSum;
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
