package si.fri.prpo.skupina14.servlet;
import si.fri.prpo.skupina14.dtos.*;
import si.fri.prpo.skupina14.nakupovalniSeznam.entitete.*;
import si.fri.prpo.skupina14.zrna.*;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/nakupovalniSeznam")
public class JPAServlet extends HttpServlet {

    @Inject
    private UporabnikZrno uporabnikZrno;

    @Inject UpravljanjeNakupovalnihSeznamovZrno upravljanjeNakupovalnihSeznamovZrno;

    @Inject
    UpravljanjeUporabnikovZrno upravljanjeUporabnikovZrno;

    @Inject
    UpravljanjeArtiklovZrno upravljanjeArtiklovZrno;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");

        PrintWriter writer = resp.getWriter();

        //izpis vseh uporabnikov
        List<Uporabnik> uporabniki = uporabnikZrno.pridobiVseUporabnike();
        writer.println("<h2>1) Izpis uporabnikov iz podatkovne baze:</h2>");
        uporabniki.forEach( u -> writer.println("<p>"+ u.toString() + "</p>"));

        //izpis vseh uporabnikov s CriteriaAPI
        writer.println("<h2>2) Izpis uporabnikov iz podatkovne baze z CriteriaAPI:</h2>");
        List<Uporabnik> uporabnikiCriteria = uporabnikZrno.getUporabnikiCriteriaAPI();
        uporabnikiCriteria.forEach( u -> writer.println("<p>"+ u.toString() + "</p>"));

        ///VAJE 4
        writer.println("<h4> Sporočila o ustvarjanju nakupovalnegaSeznama,uporabnika ter artiklov:</h4>");

        //uporabnik 1 zahteva ustvaritev novega nakupovalnega seznama
        NakupovalniSeznamDto nakupovalniSeznamDto = new NakupovalniSeznamDto();
        nakupovalniSeznamDto.setUporabnikId(2);
        nakupovalniSeznamDto.setNaziv("Nakup pijač");
        nakupovalniSeznamDto.setOpis("Najboljše žgane pijače");
        NakupovalniSeznam seznam = upravljanjeNakupovalnihSeznamovZrno.ustvariNakupovalniSeznam(nakupovalniSeznamDto);
        nakupovalniSeznamDto.setId(seznam.getId());
        //ce se je seznam uspesno ustvaril
        if(seznam != null && seznam.getId() != null) {
            writer.println("<p>Ustvarjen nov seznam</p>");
        }
        else {
            writer.println("<p>Seznama ni bilo mogoče ustvariti</p>");
        }

        //na stran se registrira nov uporabnik
        UporabnikDto noviUporabnik = new UporabnikDto();
        noviUporabnik.setIme("Nina");
        noviUporabnik.setPriimek("Blazevska");
        noviUporabnik.setEmail("dinoblunt@gmail.com");
        noviUporabnik.setUporabniskoIme("nb1234");
        Uporabnik ustvarjen = upravljanjeUporabnikovZrno.ustvariUporabnika(noviUporabnik);

        //ce se je uporabnik uspesno ustvaril
        if(ustvarjen != null && ustvarjen.getId() != null) {
            writer.println("<p>Registriral se je nov uporabnik</p>");
        }
        else {
            writer.println("<p>Uporabnika ni bilo mogoče registrirati</p>");
        }

        //v nakupovalnem seznamu dodamo nove artikle
        ArtikelDto noviArtikel = new ArtikelDto();
        noviArtikel.setNakupovalniSeznam(seznam);
        noviArtikel.setNaziv("Coca cola");
        noviArtikel.setOpis("Gazirana pijaca");
        Artikel artikel = upravljanjeArtiklovZrno.ustvariArtikel(noviArtikel);
        noviArtikel.setArtikelId(artikel.getId());


        ArtikelDto noviArtikel2 = new ArtikelDto();
        noviArtikel2.setNakupovalniSeznam(seznam);
        noviArtikel2.setNaziv("Voda");
        noviArtikel2.setOpis("Mineralna voda");
        Artikel artikel2 = upravljanjeArtiklovZrno.ustvariArtikel(noviArtikel2);
        noviArtikel2.setArtikelId(artikel2.getId());

        /*
        //odstranjevanje nakupovalnega seznama
        Integer seznam = upravljanjeNakupovalnihSeznamovZrno.odstraniNakupovalniSeznam(3);
        Integer seznam2 = upravljanjeNakupovalnihSeznamovZrno.odstraniNakupovalniSeznam(4);
        //izpisemo vse artikle v nakupovalnem seznamu
        //writer.append(upravljanjeNakupovalnihSeznamovZrno.izpisiVseArtikle(nakupovalniSeznamDto));
        */
    }
}