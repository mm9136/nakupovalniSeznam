package si.fri.prpo.skupina14.zrna;
import javax.enterprise.context.ApplicationScoped;

import si.fri.prpo.skupina14.nakupovalniSeznam.entitete.*;
import si.fri.prpo.skupina14.dtos.*;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.logging.Logger;
@ApplicationScoped
public class UpravljanjeArtiklovZrno {

    @Inject
    private ArtikelZrno ArtikelZrno;

    @Inject
    private NakupovalniSeznamZrno NakupovalniSeznamZrno;

    private Logger log = Logger.getLogger(UpravljanjeArtiklovZrno.class.getName());
    @PostConstruct
    private void init(){
        log.info("Inicijalizacija zrna " + UpravljanjeArtiklovZrno.class.getSimpleName());
    }

    @PreDestroy
    private void destroy(){
        log.info("Deinicijalizacija zrna " + UpravljanjeArtiklovZrno.class.getSimpleName());
    }
    @Transactional
    //ustvari novi artikel
    public Artikel ustvariArtikel(ArtikelDto ArtikelDto){

        Artikel a = new Artikel();
        a.setOpis(ArtikelDto.getOpis());
        a.setNaziv(ArtikelDto.getNaziv());
        a.setNakupovalniSeznam(ArtikelDto.getNakupovalniSeznam());

        return ArtikelZrno.dodajArtikel(a);
    }
    @Transactional
    //odstrani artikel
    public Integer odstraniArtikel(ArtikelDto ArtikelDto){
        Artikel artikel = ArtikelZrno.pridobiArtikel(ArtikelDto.getArtikelId());

        if (artikel == null) {
            log.info("Ne morem odstraniti artikla. Artikel ne obstaja.");
            return null;
        }
        return ArtikelZrno.odstraniArtikel(ArtikelDto.getArtikelId());
    }
}
