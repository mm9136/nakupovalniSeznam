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
    //getFullNameOfAUser with his username
    public List<String> getImeInPriimekUporabnika(String uporabniskoIme) {
        List<String> imenaInPriimki = em.createNamedQuery("Uporabnik.getFullNameOfAUser").setParameter("uporabniskoIme", uporabniskoIme).getResultList();
        return imenaInPriimki;
    }
    //getAllUserNames
    public List<String> getVsaUporabniskaImena() {
        List<String> uporabniskaImena = em.createNamedQuery("Uporabnik.getAllUserNames").getResultList();
        return uporabniskaImena;
    }
    //getShoppingListsOfAUser by user's username
    public List<NakupovalniSeznam> getVseNakupovalneSeznameUporabnika (String uporabniskoIme) {
        List<NakupovalniSeznam> nakupovalniSeznami = em.createNamedQuery("Uporabnik.getShoppingListsOfAUser").setParameter("uporabniskoIme", uporabniskoIme).getResultList();
        return nakupovalniSeznami;
    }
    //getUserFromUserName if exists
    public Uporabnik getUporabnikZUporabniskimImenom (String uporabniskoIme) {
        Uporabnik uporabnik = null;
        try {
            uporabnik = (Uporabnik) em.createNamedQuery("Uporabnik.getUserFromUserName").setParameter("uporabniskoIme", uporabniskoIme).getSingleResult();
        }
        catch (NoResultException nre){
            //Ignore this because as per your logic this is ok!
        }
        return uporabnik;
    }
    //getUserFromEmail if exists
    public Uporabnik getUporabnikZEmailom (String email) {
        Uporabnik uporabnik = null;
        try {
            uporabnik = (Uporabnik)em.createNamedQuery("Uporabnik.getUserFromEmail").setParameter("email", email).getSingleResult();
        }
        catch (NoResultException nre) { }
        return uporabnik;
    }

    //vrnitev vseh uporabnikov z CriteriaAPI
    public List<Uporabnik> getUporabnikiCriteriaAPI () {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Uporabnik> query = criteriaBuilder.createQuery(Uporabnik.class);
        Root<Uporabnik> from = query.from(Uporabnik.class);
        query.select(from);
        return em.createQuery(query).getResultList();
    }

    /////////////////vaje4:Vsa zrna naj v log zapisujejo sporočila o inicializaciji in uničenju zrna.
    @PostConstruct
    private void init(){
        idZrna = UUID.randomUUID().toString();
        log.info("Inicijalizacija zrna " + Uporabnik.class.getSimpleName() + " ID: " + idZrna);
    }

    @PreDestroy
    private void destroy(){
        log.info("Deinicijalizacija zrna " + Uporabnik.class.getSimpleName());
    }

    //////////////CRUD:read,create,update in delete
    public Uporabnik pridobiUporabnika (int uporabnikId) {
        return em.find(Uporabnik.class, uporabnikId);
    }
    @Transactional
    public Uporabnik dodajUporabnika (Uporabnik uporabnik) {

        if (uporabnik != null) {
            em.persist(uporabnik);
        }

        return uporabnik;
    }

    @Transactional
    public Uporabnik posodobiUporabnika (int uporabnikId, Uporabnik uporabnik) {
        Uporabnik u = em.find(Uporabnik.class, uporabnikId);

        uporabnik.setId(u.getId());
        em.merge(uporabnik);
        return uporabnik;
    }

    @Transactional
    public Integer odstraniUporabnika (int uporabnikId) {
        Uporabnik uporabnik = pridobiUporabnika(uporabnikId);

        if (uporabnik != null) {
            em.remove(uporabnik);
        }

        return uporabnikId;
    }

}