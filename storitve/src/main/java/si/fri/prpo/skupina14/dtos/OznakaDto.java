package si.fri.prpo.skupina14.dtos;

import si.fri.prpo.skupina14.nakupovalniSeznam.entitete.NakupovalniSeznam;

import java.util.List;

public class OznakaDto {
    private Integer oznakaId;
    private String ime;
    private String opis;
    private List<NakupovalniSeznam> nakupovalniSeznami;

    public Integer getOznakaId() {
        return oznakaId;
    }

    public void setOznakaId(Integer oznakaId) {
        this.oznakaId = oznakaId;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
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
