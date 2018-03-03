package dk.wortmann.electro.reading.entity;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDateTime;


@Entity
@NamedQueries({
        @NamedQuery(name = Reading.findAll, query = "select r from Reading r")
})
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class Reading {
    private static final String PREFIX = "readings.entity.Reading";
    public static final String findAll = PREFIX + "findAll";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "INSERTED_TIME", nullable = false, updatable = false)
    private LocalDateTime insertedTime;

    @Column(name = "KWH_VALUE", nullable = false, updatable = false)
    private double kwhValue;

    @Column(name = "METER_ID", nullable = false)
    private long meterId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDateTime getInsertedTime() {
        return insertedTime;
    }

    public void setInsertedTime(LocalDateTime insertedTime) {
        this.insertedTime = insertedTime;
    }

    public double getKwhValue() {
        return kwhValue;
    }

    public void setKwhValue(double kwhValue) {
        this.kwhValue = kwhValue;
    }

    public long getMeterId() {
        return meterId;
    }

    public void setMeterId(long meterId) {
        this.meterId = meterId;
    }
}
