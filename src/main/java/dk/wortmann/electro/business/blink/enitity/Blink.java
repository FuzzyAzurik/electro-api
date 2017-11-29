package dk.wortmann.electro.business.blink.enitity;


import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@NamedQueries({
        @NamedQuery(name = Blink.findAll, query = "SELECT r from Blink r")
})
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class Blink {
    private static final String PREFIX = "readings.entity.Blink";
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



