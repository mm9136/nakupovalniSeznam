package si.fri.prpo.skupina14.dtos;

public class NakupovalniSeznamDto {
    private Integer id;
    private Integer uporabnikId;
    private String naziv;
    private String opis;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUporabnikId() {
        return uporabnikId;
    }

    public void setUporabnikId(Integer upodabnikId) {
        this.uporabnikId = upodabnikId;
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
}
