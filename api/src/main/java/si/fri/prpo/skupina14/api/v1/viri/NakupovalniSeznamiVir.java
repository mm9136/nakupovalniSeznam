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
import si.fri.prpo.skupina14.dtos.*;
import si.fri.prpo.skupina14.nakupovalniSeznam.entitete.*;
import si.fri.prpo.skupina14.zrna.*;

import javax.annotation.security.PermitAll;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.List;

@ApplicationScoped
@Path("nakupovalniSeznami")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class NakupovalniSeznamiVir extends Application {
    @Context
    protected UriInfo uriInfo;

    @Inject
    private NakupovalniSeznamZrno nakupovalniSeznamZrno;

    @Inject
    private UpravljanjeNakupovalnihSeznamovZrno upravljanjeNakupovalnihSeznamovZrno;
    //vaje 7
    @Schema(description = "Pridobi nakupovalni seznam")
    @SecurityRequirement(name = "none")
    //////
    ///vaje6/////////
    @Operation(description = "Vrne seznam nakupovalnih seznamov.", summary = "Seznam nakupovalnih seznamov",
            tags = "nakupovalni seznami", responses = {
            @ApiResponse(responseCode = "200",
                    description = "Seznam nakupovalnih seznamov",
                    content = @Content(
                            array = @ArraySchema(
                                    schema = @Schema(implementation = NakupovalniSeznam.class)
                            )),
                    headers = {@Header(name = "X-Total-Count", description = "Stevilo vrnjenih nakupovalnih seznamov")}
            )})
    //////////////////////
    @GET
    public Response pridobiNakupovalneSezname() {
        //return Response.ok(nakupovalniSeznamZrno.pridobiVseNakupovalneSezname()).build();
        ///vaje6/////////
        QueryParameters query = QueryParameters.query(uriInfo.getRequestUri().getQuery()).build();
        List<NakupovalniSeznam> seznami = nakupovalniSeznamZrno.pridobiVseNakupovalneSezname(query);
        Long stSeznamov = nakupovalniSeznamZrno.pridobiStNakupovalnihSeznamov(query);

        return Response
                .ok(seznami)
                .header("X-Total-Count", stSeznamov)
                .build();
        //////////////////////
    }
    //vaje 7
    @Schema(description = "Vrne podrobnosti nakupovalni seznam")
    @SecurityRequirement(name = "none")
    //////
    ///vaje6/////////
    @Operation(description = "Vrne podrobnosti nakupovalnega seznama.", summary = "Podrobnosti nakupovalnega seznama",
            tags = "nakupovalni seznami",
            responses = {@ApiResponse(responseCode = "200",
                    description = "Podrobnosti nakupovalnega seznama",
                    content = @Content(
                            schema = @Schema(implementation = NakupovalniSeznam.class))
            )})
    //////////////////////
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
    //vaje 7
    @Schema(description = "Dodaj nakupovalni seznam")
    @SecurityRequirement(name = "none")
    //////
    ///vaje6/////////
    @Operation(description = "Ustvari nakupovalni seznam in ga doda uporabniku.", summary = "Ustvari nakupovalni seznam",
            tags = "nakupovalni seznami", responses = {
            @ApiResponse(responseCode = "201",
                    description = "Nakupovalni seznam ustvarjen in dodan uporabniku",
                    content = @Content(
                            array = @ArraySchema(
                                    schema = @Schema(implementation = NakupovalniSeznam.class)
                            )),
                    headers = {@Header(name = "X-Total-Count", description = "Stevilo vrnjenih nakupovalnih seznamov")}
            ),
            @ApiResponse(responseCode = "400",
                    description = "Uporabnik ni bil podan, nemorem ustvariti nakupovalnega seznama.",
                    content = @Content(
                            array = @ArraySchema(
                                    schema = @Schema(implementation = NakupovalniSeznam.class)
                            )),
                    headers = {@Header(name = "X-Total-Count", description = "Stevilo vrnjenih nakupovalnih seznamov")}
            )})
    //////////////////////
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
    //vaje 7
    @Schema(description = "Posodobi nakupovalni seznam")
    @SecurityRequirement(name = "none")
    //////
    ///vaje6/////////
    @Operation(description = "Posodobi nakupovalni seznam.", summary = "Posodobitev nakupovalnega seznama",
            tags = "nakupovalni seznami",
            responses = {@ApiResponse(responseCode = "201",
                    description = "Nakupovalni seznam podosobljen.",
                    content = @Content(
                            schema = @Schema(implementation = NakupovalniSeznam.class))
            )})

    //////////////////////
    @PUT
    @Path("{id}")
    public Response posodobiNakupovalniSeznam(@PathParam("id") Integer id, NakupovalniSeznam n) {
        return Response
                .status(Response.Status.CREATED)
                .entity(nakupovalniSeznamZrno.posodobiNakupovalniSeznam(id, n))
                .build();
    }
    ///vaje6/////////
    @Operation(description = "Doda artikel v nakupovalni seznam.", summary = "Dodajanje artikla nakupovalnemu seznamu",
            tags = "nakupovalni seznami",
            responses = {@ApiResponse(responseCode = "201",
                    description = "Artikel dodan nakupovalnemu seznamu.",
                    content = @Content(
                            schema = @Schema(implementation = NakupovalniSeznam.class))
            )})
    //////////////////////
    @PUT
    @Path("{idn}/artikli/{ida}")
    public Response dodajArtikelVSeznam(@PathParam("idn") Integer nakupovalniSeznamId, @PathParam("ida") Integer artikelId) {
        return Response
                .status(Response.Status.CREATED)
                .entity(upravljanjeNakupovalnihSeznamovZrno.dodajArtiklaVSeznam(nakupovalniSeznamId, artikelId))
                .build();
    }

    //vaje 7
    @Schema(description = "Izbrise nakupovalni seznam")
    @SecurityRequirement(name = "none")
    //////
    ///vaje6/////////
    @Operation(description = "Odstranitev nakupovalnega seznama", summary = "Odstranitev nakupovalnega seznama",
            tags = "nakupovalni seznami",
            responses =
                    {@ApiResponse(responseCode = "204",
                            description = "Nakupovalni seznam odstranjen",
                            content = @Content(
                                    schema = @Schema(implementation = NakupovalniSeznam.class))
                    ),
                            @ApiResponse(responseCode = "404",
                                    description = "Nakupovalni seznam ne obstaja",
                                    content = @Content(
                                            schema = @Schema(implementation = NakupovalniSeznam.class))
                            )}
    )
    //////////////////////
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
