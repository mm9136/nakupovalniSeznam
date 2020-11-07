package si.fri.prpo.skupina14.zrna;

import si.fri.prpo.skupina14.nakupovalniSeznam.entitete.*;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.List;
import java.util.logging.Logger;
import java.util.UUID;

@ApplicationScoped
public class UporabnikZrno {

    @PersistenceContext(unitName = "uporabniki-jpa")
    private EntityManager em;
    private Logger log = Logger.getLogger(UporabnikZrno.class.getName());
    private String idZrna;

    //getAll users
    public List<Uporabnik> pridobiVseUporabnike() {
        List<Uporabnik> uporabniki = em.createNamedQuery("Uporabnik.getAll").getResultList();
        return uporabniki;
    }

    //vrnitev vseh uporabnikov z CriteriaAPI
    public List<Uporabnik> getUporabnikiCriteriaAPI () {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Uporabnik> query = criteriaBuilder.createQuery(Uporabnik.class);
        Root<Uporabnik> from = query.from(Uporabnik.class);
        query.select(from);
        return em.createQuery(query).getResultList();
    }


}