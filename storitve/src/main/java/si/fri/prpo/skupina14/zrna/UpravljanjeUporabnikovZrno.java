package si.fri.prpo.skupina14.zrna;

import javax.enterprise.context.ApplicationScoped;

import si.fri.prpo.skupina14.dtos.*;
import si.fri.prpo.skupina14.izjeme.NeveljavniRegistriraniUporabnikDtoException;
import si.fri.prpo.skupina14.nakupovalniSeznam.entitete.*;
import si.fri.prpo.skupina14.dtos.*;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.logging.Logger;
@ApplicationScoped
public class UpravljanjeUporabnikovZrno {

    private Logger log = Logger.getLogger(UpravljanjeNakupovalnihSeznamovZrno.class.getName());

    @Inject
    private UporabnikZrno uporabnikZrno;

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
    public Uporabnik ustvariUporabnika (UporabnikDto uporabnikDto) {
        if (uporabnikDto.getEmail() == null) {
            String msg = "Ne morem registrirati uporabnika brez email naslova";
            log.severe(msg);
            throw new NeveljavniRegistriraniUporabnikDtoException(msg);
        }
        else if (uporabnikDto.getUporabniskoIme() == null) {
            String msg = "Ne morem regitrirati uporabnika brez uporabniskega imena";
            log.severe(msg);
            throw new NeveljavniRegistriraniUporabnikDtoException(msg);
        }
        Uporabnik uporabljenoUporabniskoIme = uporabnikZrno.getUporabnikZUporabniskimImenom(uporabnikDto.getUporabniskoIme());
        Uporabnik uporabljenEmail = uporabnikZrno.getUporabnikZEmailom(uporabnikDto.getEmail());

        //ce obstaja ze uporabnik s tem emailom ne naredi novega uporabnika
        if (uporabljenEmail != null) {
            String msg = "Uporabniški račun s tem email naslovom že obstaja";
            log.severe(msg);
            throw new NeveljavniRegistriraniUporabnikDtoException(msg);
        }

        //ce obstaja uporabnik s tem uporabniskim imenom ne naredi uporabnika in zahteva drugo ime
        if (uporabljenoUporabniskoIme != null) {
            String msg = "Uporabniško ime je že zasedeno. Izberite drugega.";
            log.severe(msg);
            throw new NeveljavniRegistriraniUporabnikDtoException(msg);
        }

        //ce ne obstaja se tak uporabnik da ustvari
        Uporabnik novi = new Uporabnik();
        novi.setIme(uporabnikDto.getIme());
        novi.setPriimek(uporabnikDto.getPriimek());
        novi.setUporabniskoIme(uporabnikDto.getUporabniskoIme());
        novi.setEmail(uporabnikDto.getEmail());

        return uporabnikZrno.dodajUporabnika(novi);
    }
}
