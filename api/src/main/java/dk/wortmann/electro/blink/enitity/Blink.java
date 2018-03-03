package dk.wortmann.electro.blink.enitity;


import dk.wortmann.electro.adaptor.LocalDateTimeXmlAdaptor;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDateTime;

@Entity
@NamedQueries({
        @NamedQuery(name = Blink.findAll, query = "SELECT b from Blink b")
})
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class Blink {
    private static final String PREFIX = "blinks.entity.Blink";
    public static final String findAll = PREFIX + "findAll";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "LIGHT_VALUE", nullable = false)
    private int lightValue;

    @Column(name = "LIGHT_RATIO", nullable = false)
    private double lightRatio;

    @Column(name = "INSERTED_TIME", nullable = false, updatable = false)
    @XmlJavaTypeAdapter(value = LocalDateTimeXmlAdaptor.class)
    private LocalDateTime insertedTime;

    @Column(name = "KWH_VALUE", nullable = false)
    private double kwhValue;

    @Column(name = "METER_ID", nullable = false)
    private long meterId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getLightValue() {
        return lightValue;
    }

    public void setLightValue(int lightValue) {
        this.lightValue = lightValue;
    }

    public double getLightRatio() {
        return lightRatio;
    }

    public void setLightRatio(double lightRatio) {
        this.lightRatio = lightRatio;
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



