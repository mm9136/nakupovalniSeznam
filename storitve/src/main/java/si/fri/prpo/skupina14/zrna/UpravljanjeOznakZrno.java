package si.fri.prpo.skupina14.zrna;
import javax.enterprise.context.ApplicationScoped;
import si.fri.prpo.skupina14.dtos.*;
import si.fri.prpo.skupina14.nakupovalniSeznam.entitete.*;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.logging.Logger;

@ApplicationScoped
public class UpravljanjeOznakZrno {

    private Logger log = Logger.getLogger(UpravljanjeOznakZrno.class.getName());

    @PostConstruct
    private void init(){
        log.info("Inicijalizacija zrna " + UpravljanjeOznakZrno.class.getSimpleName());
    }

    @PreDestroy
    private void destroy(){
        log.info("Deinicijalizacija zrna " + UpravljanjeOznakZrno.class.getSimpleName());
    }

    @Inject
    private OznakaZrno oznakaZrno;
    @Transactional
    //ustvari novo oznako
    public Oznaka ustvariOznako(OznakaDto oznakaDto){

        Oznaka o = new Oznaka();
        o.setIme(oznakaDto.getIme());
        o.setOpis(oznakaDto.getOpis());
        o.setNakupovalniSeznami(oznakaDto.getNakupovalniSeznami());

        return oznakaZrno.dodajOznako(o);
    }
    @Transactional
    //odstrani oznako
    public Integer odstraniOznako(OznakaDto oznakaDto){
        Oznaka oznaka = oznakaZrno.pridobiOznako(oznakaDto.getOznakaId());

        if (oznaka == null) {
            log.info("Ne morem odstraniti oznako. Oznaka ne obstaja.");
            return null;
        }
        return oznakaZrno.odstraniOznako(oznaka.getId());
    }

}

