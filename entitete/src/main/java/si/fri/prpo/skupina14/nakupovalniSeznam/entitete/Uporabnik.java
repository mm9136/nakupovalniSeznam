package si.fri.prpo.skupina14.nakupovalniSeznam.entitete;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "uporabniki")
@NamedQueries(value =
        {
                @NamedQuery(name = "Uporabnik.getAll", query = "SELECT u FROM Uporabnik u"),
                @NamedQuery(name = "Uporabnik.getFullNameOfAUser", query = "SELECT CONCAT(u.ime, ' ', u.priimek) FROM Uporabnik u WHERE u.uporabniskoIme = :uporabniskoIme"),
                @NamedQuery(name = "Uporabnik.getAllUserNames", query = "SELECT u.uporabniskoIme FROM Uporabnik u"),
                @NamedQuery(name = "Uporabnik.getShoppingListsOfAUser", query = "SELECT u.nakupovalniSeznami FROM Uporabnik u WHERE u.uporabniskoIme = :uporabniskoIme"),
                @NamedQuery(name = "Uporabnik.getUserFromUserName", query = "SELECT u FROM Uporabnik u WHERE u.uporabniskoIme = :uporabniskoIme"),
                @NamedQuery(name = "Uporabnik.getUserFromEmail", query = "SELECT u From Uporabnik u WHERE u.email = :email")
        })
public class Uporabnik {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "ime")
    private String ime;
    @Column(name = "priimek")
    private String priimek;
    @Column(name = "uporabnisko_ime")
    private String uporabniskoIme;
    @Column(name = "email")
    private String email;


    @OneToMany(mappedBy = "uporabnik", cascade = CascadeType.REMOVE)
    private List<NakupovalniSeznam> nakupovalniSeznami;

    public Integer getId() { return id; }

    public void setId(Integer id) { this.id = id; }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPriimek() {
        return priimek;
    }

    public void setPriimek(String priimek) {
        this.priimek = priimek;
    }

    public String getUporabniskoIme() {
        return uporabniskoIme;
    }

    public void setUporabniskoIme(String uporabniskoIme) {
        this.uporabniskoIme = uporabniskoIme;
    }

    public List<NakupovalniSeznam> getNakupovalniSeznami() {
        return nakupovalniSeznami;
    }

    public void setNakupovalniSeznami(List<NakupovalniSeznam> nakupovalniSeznami) {
        this.nakupovalniSeznami = nakupovalniSeznami;
    }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    @Override
    public String toString() {
        return ime + " " +
                priimek + " " +
                "z uporabniškim imenom " + uporabniskoIme + " " +
                "in emailom " + email;
    }
}
