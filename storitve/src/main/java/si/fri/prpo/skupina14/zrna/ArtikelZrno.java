package si.fri.prpo.skupina14.zrna;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.transaction.Transactional;

import com.kumuluz.ee.rest.beans.QueryParameters;
import com.kumuluz.ee.rest.utils.JPAUtils;
import si.fri.prpo.skupina14.nakupovalniSeznam.entitete.*;
import javax.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.logging.Logger;

@ApplicationScoped
public class ArtikelZrno {

    @PersistenceContext(unitName = "uporabniki-jpa")
    private EntityManager em;
    private Logger log = Logger.getLogger(UporabnikZrno.class.getName());

    public List<Artikel> pridobiVseArtikle(){
        return em.createNamedQuery("Artikel.getAll").getResultList();
    }

    @PostConstruct
    private void init(){
        log.info("Inicijalizacija zrna " + Artikel.class.getSimpleName());
    }

    @PreDestroy
    private void destroy(){
        log.info("Deinicijalizacija zrna " + Artikel.class.getSimpleName());
    }

    public Artikel pridobiArtikel (int artikelId) {
        return em.find(Artikel.class, artikelId);
    }

    @Transactional
    public Artikel dodajArtikel (Artikel artikel) {

        if (artikel != null) {
            em.persist(artikel);
        }

        return artikel;
    }

    @Transactional
    public Artikel posodobiArtikel (int artikelId, Artikel artikel) {
        Artikel a = em.find(Artikel.class, artikelId);
        artikel.setId(a.getId());
        em.merge(artikel);
        return artikel;
    }

    @Transactional
    public Integer odstraniArtikel (int artikelId) {
        Artikel artikel = pridobiArtikel(artikelId);

        if (artikel != null) {
            em.remove(artikel);
        }

        return artikelId;
    }

    public List<Artikel> pridobiVseArtikle(QueryParameters query){
        //return em.createNamedQuery("Artikel.getAll").getResultList();
        List<Artikel> artikli = JPAUtils.queryEntities(em, Artikel.class, query);
        return artikli;
    }

    public Long pridobiStArtiklov(QueryParameters query){
        Long count = JPAUtils.queryEntitiesCount(em, Oznaka.class, query);
        return count;
    }
}
