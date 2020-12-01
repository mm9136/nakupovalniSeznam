package si.fri.prpo.skupina14.zrna;
import com.kumuluz.ee.rest.beans.QueryParameters;
import com.kumuluz.ee.rest.utils.JPAUtils;
import si.fri.prpo.skupina14.nakupovalniSeznam.entitete.*;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.logging.Logger;
import java.util.List;
@ApplicationScoped
public class NakupovalniSeznamZrno {
    @PersistenceContext(unitName = "uporabniki-jpa")
    private EntityManager em;
    private Logger log = Logger.getLogger(UporabnikZrno.class.getName());

    public List<NakupovalniSeznam> pridobiVseNakupovalneSezname(){
       return em.createNamedQuery("NakupovalniSeznam.getAll").getResultList();
    }
    @PostConstruct
    private void init(){
        log.info("Inicijalizacija zrna " + NakupovalniSeznam.class.getSimpleName());
    }

    @PreDestroy
    private void destroy(){
        log.info("Deinicijalizacija zrna" + NakupovalniSeznam.class.getSimpleName());
    }

    public NakupovalniSeznam pridobiNakupovalniSeznam(int id) {
        return em.find(NakupovalniSeznam.class, id);
    }

    @Transactional
    public NakupovalniSeznam dodajNakupovalniSeznam(NakupovalniSeznam n) {
        if (n!= null){
            em.persist(n);
        }
        return n;
    }

    @Transactional
    public NakupovalniSeznam posodobiNakupovalniSeznam(int id, NakupovalniSeznam n) {
        NakupovalniSeznam ns = em.find(NakupovalniSeznam.class, id);
        n.setId(ns.getId());
        em.merge(n);
        return n;
    }

    @Transactional
    public Integer odstraniNakupovalniSeznam(int id){
        NakupovalniSeznam n = pridobiNakupovalniSeznam(id);

        if(n != null){
            em.remove(n);
        }
        return id;
    }

    public List<NakupovalniSeznam> pridobiVseNakupovalneSezname(QueryParameters query){
        //return em.createNamedQuery("NakupovalniSeznam.getAll").getResultList();
        List<NakupovalniSeznam> ns = JPAUtils.queryEntities(em, NakupovalniSeznam.class, query);
        return ns;
    }

    public Long pridobiStNakupovalnihSeznamov(QueryParameters query){
        Long count = JPAUtils.queryEntitiesCount(em, NakupovalniSeznam.class, query);
        return count;
    }
}
