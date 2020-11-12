package si.fri.prpo.skupina14.api.v1.viri;
import si.fri.prpo.skupina14.nakupovalniSeznam.entitete.*;
import si.fri.prpo.skupina14.zrna.*;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
@ApplicationScoped
@Path("oznake")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class OznakeVir {
    @Inject
    private OznakaZrno oznakaZrno;
    @GET
    public Response pridobiOznake() {
        return Response.ok(oznakaZrno.pridobiVseOznake()).build();
    }

    @GET
    @Path("{id}")
    public Response pridobiOznako(@PathParam("id") Integer id) {
        Oznaka oznaka = oznakaZrno.pridobiOznako(id);

        if (oznaka != null) {
            return Response.ok(oznaka).build();
        }
        else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    public Response dodajOznako(Oznaka oznaka) {
        return Response
                .status(Response.Status.CREATED)
                .entity(oznakaZrno.dodajOznako(oznaka))
                .build();
    }

    @PUT
    @Path("{id}")
    public Response posodobiOznako(@PathParam("id") Integer id, Oznaka oznaka) {
        return Response
                .status(Response.Status.CREATED)
                .entity(oznakaZrno.posodobiOznako(id, oznaka))
                .build();
    }

    @DELETE
    @Path("{id}")
    public Response odstraniOznako(@PathParam("id") Integer id) {
        return Response
                .status(Response.Status.NO_CONTENT)
                .entity(oznakaZrno.odstraniOznako(id))
                .build();
    }
}
