package si.fri.prpo.skupina14.api.v1.viri;
import si.fri.prpo.skupina14.dtos.*;
import si.fri.prpo.skupina14.nakupovalniSeznam.entitete.*;
import si.fri.prpo.skupina14.zrna.*;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
@ApplicationScoped
@Path("nakupovalniSeznami")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class NakupovalniSeznamiVir extends Application {

    @Inject
    private NakupovalniSeznamZrno nakupovalniSeznamZrno;

    @Inject
    private UpravljanjeNakupovalnihSeznamovZrno upravljanjeNakupovalnihSeznamovZrno;

    @GET
    public Response pridobiNakupovalneSezname() {
        return Response.ok(nakupovalniSeznamZrno.pridobiVseNakupovalneSezname()).build();
    }

    @GET
    @Path("{id}")
    public Response pridobiNakupovalniSeznam(@PathParam("id") Integer id) {
        NakupovalniSeznam n = nakupovalniSeznamZrno.pridobiNakupovalniSeznam(id);

        if (n != null) {
            return Response.ok(n).build();
        }
        else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    public Response dodajNakupovalniSeznam(NakupovalniSeznamDto n) {
        NakupovalniSeznam ustvarjen = upravljanjeNakupovalnihSeznamovZrno.ustvariNakupovalniSeznam(n);
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
    public Response posodobiNakupovalniSeznam(@PathParam("id") Integer id, NakupovalniSeznam n) {
        return Response
                .status(Response.Status.CREATED)
                .entity(nakupovalniSeznamZrno.posodobiNakupovalniSeznam(id, n))
                .build();
    }

    @PUT
    @Path("{idn}/artikli/{ida}")
    public Response dodajArtikelVSeznam(@PathParam("idn") Integer nakupovalniSeznamId, @PathParam("ida") Integer artikelId) {
        return Response
                .status(Response.Status.CREATED)
                .entity(upravljanjeNakupovalnihSeznamovZrno.dodajArtiklaVSeznam(nakupovalniSeznamId, artikelId))
                .build();
    }

    @DELETE
    @Path("{id}")
    public Response odstraniNakupovalniSeznam(@PathParam("id") Integer id) {
        Integer idOdstranjenega = upravljanjeNakupovalnihSeznamovZrno.odstraniNakupovalniSeznam(id);
        if (idOdstranjenega == null) {
            return Response
                    .status(Response.Status.NOT_FOUND).build();
        }
        return Response
                .status(Response.Status.NO_CONTENT).build();
    }
}
