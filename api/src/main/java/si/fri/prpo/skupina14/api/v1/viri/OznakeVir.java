package si.fri.prpo.skupina14.api.v1.viri;
import com.kumuluz.ee.rest.beans.QueryParameters;
import com.kumuluz.ee.security.annotations.Secure;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import si.fri.prpo.skupina14.nakupovalniSeznam.entitete.*;
import si.fri.prpo.skupina14.zrna.*;

import javax.annotation.security.PermitAll;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;

@ApplicationScoped
@Path("oznake")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class OznakeVir {
    @Context
    protected UriInfo uriInfo;

    @Inject
    private OznakaZrno oznakaZrno;
    //vaje 7
    @Schema(description = "Vrne seznam oznak")
    @SecurityRequirement(name = "none")
    //////
    //vaje 6//////////
    @Operation(description = "Vrne seznam oznak.", summary = "Seznam oznak",
            tags = "oznake", responses = {
            @ApiResponse(responseCode = "200",
                    description = "Seznam oznak",
                    content = @Content(
                            array = @ArraySchema(
                                    schema = @Schema(implementation = Oznaka.class)
                            )),
                    headers = {@Header(name = "X-Total-Count", description = "Stevilo vrnjenih oznak")}
            )})
    //////////////////
    @GET
    public Response pridobiOznake() {
        //return Response.ok(oznakaZrno.pridobiVseOznake()).build();
        ////vaje 6////////////
        QueryParameters query = QueryParameters.query(uriInfo.getRequestUri().getQuery()).build();
        List<Oznaka> oznake = oznakaZrno.pridobiVseOznake(query);
        Long stOznak = oznakaZrno.pridobiStOznak(query);

        return Response
                .ok(oznake)
                .header("X-Total-Count", stOznak)
                .build();
        //////////////////////
    }
    //vaje 7
    @Schema(description = "Vrne podrobnosti oznak")
    @SecurityRequirement(name = "none")
    //////
    //vaje 6//////////
    @Operation(description = "Vrne podrobnosti oznake.", summary = "Podrobnosti oznake",
            tags = "oznake",
            responses = {@ApiResponse(responseCode = "200",
                    description = "Podrobnosti oznake",
                    content = @Content(
                            schema = @Schema(implementation = Oznaka.class))
            )})
    //////////////////
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
    //vaje 7
    @Schema(description = "Doda oznake")
    @SecurityRequirement(name = "none")
    //////
    //vaje 6//////////
    @Operation(description = "Dodajanje nove oznake,", summary = "Dodajanje oznake",
            tags = "oznake",
            responses =
                    {@ApiResponse(responseCode = "201",
                            description = "Dodajanje oznake",
                            content = @Content(
                                    schema = @Schema(implementation = Oznaka.class))
                    )}
    )
    //////////////////
    @POST
    public Response dodajOznako(Oznaka oznaka) {
        return Response
                .status(Response.Status.CREATED)
                .entity(oznakaZrno.dodajOznako(oznaka))
                .build();
    }
    //vaje 7
    @Schema(description = "Posodobi oznake")
    @SecurityRequirement(name = "none")
    //////
    //vaje 6//////////
    @Operation(description = "Posodobi oznako", summary = "Posodobitev oznake",
            tags = "oznake",
            responses =
                    {@ApiResponse(responseCode = "201",
                            description = "Oznaka posodobljena",
                            content = @Content(
                                    schema = @Schema(implementation = Oznaka.class))
                    )}
    )
    //////////////////
    @PUT
    @Path("{id}")
    public Response posodobiOznako(@PathParam("id") Integer id, Oznaka oznaka) {
        return Response
                .status(Response.Status.CREATED)
                .entity(oznakaZrno.posodobiOznako(id, oznaka))
                .build();
    }
    //vaje 7
    @Schema(description = "Odstrani oznake")
    @SecurityRequirement(name = "none")
    //////
    //vaje 6//////////
    @Operation(description = "Odstranitev določene oznake", summary = "Odstranitev oznake",
            tags = "oznake",
            responses =
                    {@ApiResponse(responseCode = "204",
                            description = "Oznaka odstranjena",
                            content = @Content(
                                    schema = @Schema(implementation = Oznaka.class))
                    )}
    )
    //////////////////
    @DELETE
    @Path("{id}")
    public Response odstraniOznako(@PathParam("id") Integer id) {
        return Response
                .status(Response.Status.NO_CONTENT)
                .entity(oznakaZrno.odstraniOznako(id))
                .build();
    }
}
