package si.fri.prpo.skupina14.api.v1.viri;

import com.kumuluz.ee.rest.beans.QueryParameters;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import si.fri.prpo.skupina14.anotacije.BeleziKlice;
import si.fri.prpo.skupina14.dtos.*;
import si.fri.prpo.skupina14.nakupovalniSeznam.entitete.*;
import si.fri.prpo.skupina14.zrna.*;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;

@Path("uporabniki")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class UporabnikiVir {
    @Context
    protected UriInfo uriInfo;

    @Inject
    private UporabnikZrno uporabnikZrno;
    @Inject
    private UpravljanjeUporabnikovZrno upravljanjeUporabnikovZrno;

    @Inject
    private UpravljanjeNakupovalnihSeznamovZrno upravljanjeNakupovalnihSeznamovZrno;
    //vaje 7
    @Schema(description = "Vrne seznam uporabnikov")
    @SecurityRequirement(name = "none")
    //////
    //vaje 6///////////////
    @Operation(description = "Vrne seznam uporabnikov.", summary = "Seznam uporabnikov",
            tags = "uporabniki", responses = {
            @ApiResponse(responseCode = "200",
                    description = "Seznam uporabnikov",
                    content = @Content(
                            array = @ArraySchema(
                                    schema = @Schema(implementation = Uporabnik.class)
                            )),
                    headers = {@Header(name = "X-Total-Count", description = "Stevilo vrnjenih uporabnikov")}
            )})
    /////////////
    @GET
    @BeleziKlice
    public Response pridobiUporabnike() {

        //return Response.ok(uporabnikZrno.pridobiVseUporabnike()).build();
        //vaje 6///////////////
        QueryParameters query = QueryParameters.query(uriInfo.getRequestUri().getQuery()).build();
        List<Uporabnik> uporabniki = uporabnikZrno.pridobiVseUporabnike(query);
        Long stUporabnikov = uporabnikZrno.pridobiStUporabnikov(query);

        return Response
                .ok(uporabniki)
                .header("X-Total-Count", stUporabnikov)
                .build();
        ////////////////////
    }
    //vaje 7
    @Schema(description = "Vrne podrobnosti uporabnikov")
    @SecurityRequirement(name = "none")
    //////
    //vaje 6////////////////
    @Operation(description = "Vrne podrobnosti uporabnika.", summary = "Podrobnosti uporabnika",
            tags = "uporabniki",
            responses = {@ApiResponse(responseCode = "200",
                    description = "Podrobnosti uporabnika",
                    content = @Content(
                            schema = @Schema(implementation = Uporabnik.class))
            )})
    ///////////////////////////
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
    //vaje 7
    @Schema(description = "Doda  uporabnika")
    @SecurityRequirement(name = "none")
    //////
    //vaje 6////////////////
    @Operation(description = "Registrira novega uporabnika. Če je username ali email uporabnika že zaseden vrne napako s statusom 400.", summary = "Registracija uporabnika",
            tags = "uporabniki",
            responses =
                    {@ApiResponse(responseCode = "201",
                            description = "Registracija uporabnika",
                            content = @Content(
                                    schema = @Schema(implementation = Uporabnik.class))
                    ),
                            @ApiResponse(responseCode = "400",
                                    description = "Neveljaven username ali email naslov.",
                                    content = @Content(
                                            schema = @Schema(implementation = Uporabnik.class))
                            )}

    )
    ///////////////
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
    //vaje 7
    @Schema(description = "Posodobi uporabnika")
    @SecurityRequirement(name = "none")
    //////
    //vaje 6////////////////
    @Operation(description = "Posodobi uporabnika", summary = "Posodobitev uporabnika",
            tags = "uporabniki",
            responses =
                    {@ApiResponse(responseCode = "201",
                            description = "Uporabnik posodobljen",
                            content = @Content(
                                    schema = @Schema(implementation = Uporabnik.class))
                    )}
    )
    ///////////////
    @PUT
    @Path("{id}")
    public Response posodobiUporabnika(@PathParam("id") Integer id, Uporabnik uporabnik) {
        return Response
                .status(Response.Status.CREATED)
                .entity(uporabnikZrno.posodobiUporabnika(id, uporabnik))
                .build();
    }
    //vaje 7
    @Schema(description = "Odstrani uporabnika")
    @SecurityRequirement(name = "none")
    //////
    //vaje 6////////////////
    @Operation(description = "Odstranitev določenega uporabnika", summary = "Odstranitev uporabnika",
            tags = "uporabniki",
            responses =
                    {@ApiResponse(responseCode = "204",
                            description = "Uporabnik odstranjen",
                            content = @Content(
                                    schema = @Schema(implementation = Uporabnik.class))
                    )}
    )
    ///////////////
    @DELETE
    @Path("{id}")
    public Response odstraniUporabnika(@PathParam("id") Integer id) {
        return Response
                .status(Response.Status.NO_CONTENT)
                .entity(uporabnikZrno.odstraniUporabnika(id))
                .build();
    }
}