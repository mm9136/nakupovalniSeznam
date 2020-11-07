package si.fri.prpo.skupina14.servlet;
import si.fri.prpo.skupina14.nakupovalniSeznam.entitete.Uporabnik;
import si.fri.prpo.skupina14.zrna.UporabnikZrno;

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

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Uporabnik> uporabniki = uporabnikZrno.pridobiVseUporabnike();
        List<Uporabnik> uporabnikiCriteria = uporabnikZrno.getUporabnikiCriteriaAPI();
        List<String> uporabnikZDanimEmailom = uporabnikZrno.getImeInPriimekUporabnika("mmilsss");

        resp.setContentType("text/html; charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");

        PrintWriter writer = resp.getWriter();

        //izpis vseh uporabnikov
        writer.println("<h2>1) Izpis uporabnikov iz podatkovne baze:</h2>");
        uporabniki.forEach( u -> writer.println("<p>"+ u.toString() + "</p>"));

        //izpis vseh uporabnikov s CriteriaAPI
        writer.println("<h2>2) Izpis uporabnikov iz podatkovne baze z CriteriaAPI:</h2>");
        uporabnikiCriteria.forEach( u -> writer.println("<p>"+ u.toString() + "</p>"));

        //izpis uporabnikovega imena in priimka z danim emailom
        writer.println("<h2>3) Izpis  uporabnika z danim emailom:</h2>");
        uporabnikZDanimEmailom.forEach( u -> writer.println("<p>"+ u.toString() + "</p>"));

    }
}