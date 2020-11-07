package si.fri.prpo.skupina14.zrna;

import si.fri.prpo.skupina14.nakupovalniSeznam.entitete.NakupovalniSeznam;
import si.fri.prpo.skupina14.nakupovalniSeznam.entitete.Uporabnik;


import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Arrays;
import java.util.List;

@ApplicationScoped
public class UporabnikZrno {

    @PersistenceContext(unitName = "uporabniki-jpa")
    private EntityManager em;

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

}