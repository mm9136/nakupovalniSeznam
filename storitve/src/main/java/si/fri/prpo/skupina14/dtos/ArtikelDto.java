package si.fri.prpo.skupina14.dtos;

import si.fri.prpo.skupina14.nakupovalniSeznam.entitete.*;

public class ArtikelDto {
    private Integer artikelId;
    private String naziv;
    private String opis;
    private NakupovalniSeznam nakupovalniSeznam;

    public Integer getArtikelId() {
        return artikelId;
    }

    public void setArtikelId(Integer artikelId) {
        this.artikelId = artikelId;
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

    public NakupovalniSeznam getNakupovalniSeznam() {
        return nakupovalniSeznam;
    }

    public void setNakupovalniSeznam(NakupovalniSeznam nakupovalniSeznam) {
        this.nakupovalniSeznam = nakupovalniSeznam;
    }
}
