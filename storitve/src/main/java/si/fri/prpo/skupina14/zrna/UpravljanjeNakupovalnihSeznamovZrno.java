package si.fri.prpo.skupina14.zrna;

import javax.enterprise.context.ApplicationScoped;

import si.fri.prpo.skupina14.dtos.*;
import si.fri.prpo.skupina14.nakupovalniSeznam.entitete.*;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;
@ApplicationScoped
public class UpravljanjeNakupovalnihSeznamovZrno {

    private Logger log = Logger.getLogger(UpravljanjeNakupovalnihSeznamovZrno.class.getName());

    @Inject
    private UporabnikZrno uporabnikZrno;

    @Inject
    private ArtikelZrno artikelZrno;

    @Inject
    private NakupovalniSeznamZrno nakupovalniSeznamZrno;

    @PostConstruct
    private void init(){
        log.info("Inicijalizacija zrna " + UpravljanjeNakupovalnihSeznamovZrno.class.getSimpleName());
    }

    @PreDestroy
    private void destroy(){
        log.info("Deinicijalizacija zrna " + UpravljanjeNakupovalnihSeznamovZrno.class.getSimpleName());
    }
    @Transactional
    public NakupovalniSeznam ustvariNakupovalniSeznam (NakupovalniSeznamDto nakupovalniSeznamDto) {
        //nakupovalni seznam se ustvari samo, če je podan uporabnik, ki ga je ustvaril
        Uporabnik uporabnik = uporabnikZrno.pridobiUporabnika(nakupovalniSeznamDto.getUporabnikId());

        if (uporabnik == null) {

            log.info("Ne morem ustvariti novega nakupovalnega seznama. Uporabnik ne obstaja.");
            return null;

        }

        NakupovalniSeznam nakupovalniSeznam = new NakupovalniSeznam();
        nakupovalniSeznam.setUporabnik(uporabnik);
        nakupovalniSeznam.setNaziv(nakupovalniSeznamDto.getNaziv());
        nakupovalniSeznam.setOpis(nakupovalniSeznamDto.getOpis());
        nakupovalniSeznam.setUstvarjen(new Date());

        NakupovalniSeznam dodan = nakupovalniSeznamZrno.dodajNakupovalniSeznam(nakupovalniSeznam);
        uporabnik.getNakupovalniSeznami().add(dodan);
        uporabnikZrno.posodobiUporabnika(uporabnik.getId(), uporabnik);

        return dodan;
    }
    @Transactional
    public Integer odstraniNakupovalniSeznam (Integer id) {
        //pridobis nakupovalni seznam in pripadajocega uporabnika
        NakupovalniSeznam nakupovalniSeznam = nakupovalniSeznamZrno.pridobiNakupovalniSeznam(id);
        if (nakupovalniSeznam == null) {
            log.info("Nakupovalni seznam ne obstaja");
            return null;
        }
        Uporabnik uporabnik = nakupovalniSeznam.getUporabnik();
        //iz uporabnika odstranimo seznam nato odstranimo se seznam iz baze
        Iterator itr = uporabnik.getNakupovalniSeznami().iterator();
        while (itr.hasNext()) {
            NakupovalniSeznam n = (NakupovalniSeznam)itr.next();
            Integer idn = n.getId();
            if (idn == nakupovalniSeznam.getId()) {
                itr.remove();
                log.info("Izbrisali smo nakupovalni seznam z id: "+idn);
                break;
            }
        }
        uporabnikZrno.posodobiUporabnika(uporabnik.getId(), uporabnik);
        //odstranimo nakupovalni seznam iz baze
        return nakupovalniSeznamZrno.odstraniNakupovalniSeznam(nakupovalniSeznam.getId());

    }
    @Transactional
    public NakupovalniSeznam dodajArtiklaVSeznam(Integer nakupovalniSeznamId, Integer artikelId){
        Artikel a = artikelZrno.pridobiArtikel(artikelId);
        NakupovalniSeznam ns = nakupovalniSeznamZrno.pridobiNakupovalniSeznam(nakupovalniSeznamId);
        List<Artikel> seznamArtiklov = ns.getArtikli();
        seznamArtiklov.add(a);
        ns.setArtikli(seznamArtiklov);
        return nakupovalniSeznamZrno.posodobiNakupovalniSeznam(ns.getId(),ns);
    }

    public String izpisiVseArtikle(NakupovalniSeznamDto nakupovalniSeznamDto){
        NakupovalniSeznam ns = nakupovalniSeznamZrno.pridobiNakupovalniSeznam(nakupovalniSeznamDto.getId());
        List<Artikel> seznamArtiklov = ns.getArtikli();
        String izpis = "<p>Prikaz artiklov seznama " + ns.getNaziv() + "<ul>";

        for(Artikel a : seznamArtiklov){
            izpis += ("<li>"+a.getId() + ". " + a.getNaziv() + " - " + a.getOpis() + "</li>");
        }
        izpis += "</ul></p>";
        return izpis;
    }
}