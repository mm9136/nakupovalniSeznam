package si.fri.prpo.skupina14.api.v1.viri;

import si.fri.prpo.skupina14.anotacije.BeleziKlice;
import si.fri.prpo.skupina14.dtos.*;
import si.fri.prpo.skupina14.nakupovalniSeznam.entitete.*;
import si.fri.prpo.skupina14.zrna.*;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("uporabniki")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class UporabnikiVir {
    @Inject
    private UporabnikZrno uporabnikZrno;
    @Inject
    private UpravljanjeUporabnikovZrno upravljanjeUporabnikovZrno;

    @GET
    @BeleziKlice
    public Response pridobiUporabnike() {
        return Response.ok(uporabnikZrno.pridobiVseUporabnike()).build();
    }
    @GET
    @Path("{id}")
    public Response pridobiUporabnika(@PathParam("id") Integer id) {
        Uporabnik uporabnik = uporabnikZrno.pridobiUporabnika(id);

        if (uporabnik != null) {
            return Response.ok(uporabnik).build();
        }
        else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
    @POST
    public Response dodajUporabnika(UporabnikDto uporabnik) {
        Uporabnik ustvarjen = upravljanjeUporabnikovZrno.ustvariUporabnika(uporabnik);
        if (ustvarjen == null) {
            return Response
                    .status(Response.Status.BAD_REQUEST).build();
        }
        return Response
                .status(Response.Status.CREATED)
                .entity(ustvarjen)
                .build();
    }
    @PUT
    @Path("{id}")
    public Response posodobiUporabnika(@PathParam("id") Integer id, Uporabnik uporabnik) {
        return Response
                .status(Response.Status.CREATED)
                .entity(uporabnikZrno.posodobiUporabnika(id, uporabnik))
                .build();
    }
    @DELETE
    @Path("{id}")
    public Response odstraniUporabnika(@PathParam("id") Integer id) {
        return Response
                .status(Response.Status.NO_CONTENT)
                .entity(uporabnikZrno.odstraniUporabnika(id))
                .build();
    }
}