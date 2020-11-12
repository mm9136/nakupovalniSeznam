package si.fri.prpo.skupina14.api.v1.viri;
import si.fri.prpo.skupina14.nakupovalniSeznam.entitete.*;
import si.fri.prpo.skupina14.zrna.*;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
@ApplicationScoped
@Path("artikli")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ArtikliVir extends Application {
    @Inject
    private ArtikelZrno artikelZrno;

    @GET
    public Response pridobiArtikle() {
        return Response.ok(artikelZrno.pridobiVseArtikle()).build();
    }
    @GET @Path("{id}")
    public Response pridobiArtikel(@PathParam("id") Integer id) {
        Artikel artikel = artikelZrno.pridobiArtikel(id);

        if (artikel != null) {
            return Response.ok(artikel).build();
        }
        else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
    @POST
    public Response dodajArtikel(Artikel artikel) {
        return Response
                .status(Response.Status.CREATED)
                .entity(artikelZrno.dodajArtikel(artikel))
                .build();
    }
    @PUT
    @Path("{id}")
    public Response posodobiArtikel(@PathParam("id") Integer id, Artikel artikel) {
        return Response
                .status(Response.Status.CREATED)
                .entity(artikelZrno.posodobiArtikel(id, artikel))
                .build();
    }
    @DELETE
    @Path("{id}")
    public Response odstraniArtikel(@PathParam("id") Integer id) {
        return Response
                .status(Response.Status.NO_CONTENT)
                .entity(artikelZrno.odstraniArtikel(id))
                .build();
    }
}

