package si.fri.prpo.skupina14.zrna;

import si.fri.prpo.skupina14.nakupovalniSeznam.entitete.*;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.logging.Logger;
@ApplicationScoped
public class OznakaZrno {
    @PersistenceContext(unitName = "uporabniki-jpa")
    private EntityManager em;
    private Logger log = Logger.getLogger(UporabnikZrno.class.getName());

    //pridobi vse uporabnike
    public List<Oznaka> pridobiVseOznake() {
        return em.createNamedQuery("Oznaka.getAll").getResultList();
    }

    @PostConstruct
    private void init(){
        log.info("Inicijalizacija zrna " + Oznaka.class.getSimpleName());
    }

    @PreDestroy
    private void destroy(){
        log.info("Deinicijalizacija zrna " + Oznaka.class.getSimpleName());
    }


    public Oznaka pridobiOznako(int id) {
        return em.find(Oznaka.class, id);

    }

    @Transactional
    public Oznaka dodajOznako(Oznaka o) {
        if (o!= null){
            em.persist(o);
        }
        return o;
    }

    @Transactional
    public Oznaka posodobiOznako(int id, Oznaka o) {
        Oznaka oz = em.find(Oznaka.class, id);
        o.setId(oz.getId());
        em.merge(o);
        return o;
    }

    @Transactional
    public Integer odstraniOznako(int id){
        Oznaka o = pridobiOznako(id);

        if(o != null){
            em.remove(o);
        }
        return id;
    }



}
