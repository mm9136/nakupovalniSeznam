package si.fri.prpo.skupina14.nakupovalniSeznam.entitete;

import java.time.LocalDate;
import java.util.Date;
import javax.persistence.*;
import java.time.Instant;
import java.util.List;

@Entity
@Table(name = "nakupovalni_seznam")
@NamedQueries(value =
        {
                @NamedQuery(name = "NakupovalniSeznam.getAll", query = "SELECT n FROM NakupovalniSeznam n"),
                @NamedQuery(name = "NakupovalniSeznam.getClient", query = "SELECT n.uporabnik FROM NakupovalniSeznam n WHERE n.id = :id"),
                @NamedQuery(name = "NakupovalniSeznam.getProductsOfClient", query = "SELECT n.artikli FROM NakupovalniSeznam n, Uporabnik u WHERE n.uporabnik.id = u.id AND u.uporabniskoIme = :uporabniskoIme"),
                @NamedQuery(name = "NakupovalniSeznam.getAllNames", query = "SELECT n.naziv FROM NakupovalniSeznam n")

        })

public class NakupovalniSeznam {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "naziv")
    private String naziv;
    @Column(name = "opis")
    private String opis;
    @Column(name = "ustvarjen")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ustvarjen;

    @ManyToOne()
    @JoinColumn(name = "uporabnik_id")
    private Uporabnik uporabnik;

    @OneToMany(mappedBy = "nakupovalniSeznam", fetch=FetchType.EAGER, cascade = CascadeType.REMOVE)
    private List<Artikel> artikli;

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE,
            CascadeType.DETACH
    })
    @JoinTable(name = "nakupovalni_seznam_oznaka",
            joinColumns = @JoinColumn(name = "nakupovalni_seznam_id"),
            inverseJoinColumns = @JoinColumn(name = "oznaka_id")
    )
    private List<Oznaka> oznake;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public Uporabnik getUporabnik() {
        return uporabnik;
    }

    public void setUporabnik(Uporabnik uporabnik) {
        this.uporabnik = uporabnik;
    }

    public List<Artikel> getArtikli() {
        return artikli;
    }

    public void setArtikli(List<Artikel> artikli) {
        this.artikli = artikli;
    }

    public List<Oznaka> getOznake() {
        return oznake;
    }

    public void setOznake(List<Oznaka> oznake) {
        this.oznake = oznake;
    }

    public Date getUstvarjen() {
        return ustvarjen;
    }

    public void setUstvarjen(Date ustvarjen) {
        this.ustvarjen = ustvarjen;
    }
}

