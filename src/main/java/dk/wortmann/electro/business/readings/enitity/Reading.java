package dk.wortmann.electro.business.readings.enitity;


import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@NamedQueries({
        @NamedQuery(name = Reading.findAll, query = "SELECT r from Reading r")
})
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class Reading {
    private static final String PREFIX = "readings.entity.Reading";
    public static final String findAll = PREFIX + "findAll";

    @Id
    @GeneratedValue
    private long id;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}



