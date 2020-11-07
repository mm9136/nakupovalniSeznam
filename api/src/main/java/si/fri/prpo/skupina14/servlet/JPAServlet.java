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

        // izpis uporabnikov na spletno stran
        resp.setContentType("text/html; charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");

        PrintWriter writer = resp.getWriter();

        //izpis vseh uporabnikov
        writer.append("<h2>1) Poizvedba po vseh podatkih uporabnikov v podatkovni bazi</h2>");
        uporabniki.stream().forEach( u -> writer.append("<p>"+ u.toString() + "</p>"));
    }
}