package si.fri.prpo.skupina14.nakupovalniSeznam.entitete;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "oznaka")
@NamedQueries(value =
        {
                @NamedQuery(name = "Oznaka.getAll", query = "SELECT o FROM Oznaka o"),
                @NamedQuery(name = "Oznaka.getAllNames", query = "SELECT o.ime FROM Oznaka o"),
                @NamedQuery(name = "Oznaka.getDescriptionOfLabel", query = "SELECT o.opis FROM Oznaka o WHERE o.ime = :ime"),
                @NamedQuery(name = "Oznaka.getNakupovalniSeznami", query = "SELECT o.nakupovalniSeznami FROM Oznaka o WHERE o.ime = :ime")

        })

public class Oznaka {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "ime")
    private String ime;
    @Column(name = "opis")
    private String opis;

    @ManyToMany(mappedBy = "oznake")
    private List<NakupovalniSeznam> nakupovalniSeznami;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String naslov) {
        this.ime = ime;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public List<NakupovalniSeznam> getNakupovalniSeznami() {
        return nakupovalniSeznami;
    }

    public void setNakupovalniSeznami(List<NakupovalniSeznam> nakupovalniSeznami) {
        this.nakupovalniSeznami = nakupovalniSeznami;
    }

}
