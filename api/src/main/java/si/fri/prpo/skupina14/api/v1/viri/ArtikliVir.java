package si.fri.prpo.skupina14.api.v1.viri;
import com.kumuluz.ee.configuration.utils.ConfigurationUtil;
import com.kumuluz.ee.rest.beans.QueryParameters;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import si.fri.prpo.skupina14.nakupovalniSeznam.entitete.*;
import si.fri.prpo.skupina14.zrna.*;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.*;
import java.util.List;
import java.util.logging.Logger;

@ApplicationScoped
@Path("artikli")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ArtikliVir extends Application {
    @Context
    protected UriInfo uriInfo;

    @Inject
    private ArtikelZrno artikelZrno;
    //vaje 7
    private Logger log = Logger.getLogger(ArtikliVir.class.getName());
    private Client httpClient;
    private String baseUrl;
    @PostConstruct
    private void init() {
        httpClient = ClientBuilder.newClient();
        log.info("Inicializacija zrna "+ ArtikliVir.class.getSimpleName());
        baseUrl = ConfigurationUtil.getInstance()
                .get("integrations.priporocilniSistemi.base-url")
                .orElse("http://localhost:8081/v1");


    }

    ////
    //vaje 7
    @Schema(description = "Vrne seznam artiklov")
    @SecurityRequirement(name = "none")
    //////
    /////////////vaje 6/////////////////
    @Operation(description = "Vrne seznam artiklov.", summary = "Seznam artiklov",
            tags = "artikli", responses = {
            @ApiResponse(responseCode = "200",
                    description = "Seznam artiklov",
                    content = @Content(
                            array = @ArraySchema(
                                    schema = @Schema(implementation = Artikel.class)
                            )),
                    headers = {@Header(name = "X-Total-Count", description = "Stevilo vrnjenih artiklov")}
            )})
    ////////////////////////
    @GET
    public Response pridobiArtikle() {
        //return Response.ok(artikelZrno.pridobiVseArtikle()).build();
        /////////////vaje 6/////////////////
        QueryParameters query = QueryParameters.query(uriInfo.getRequestUri().getQuery()).build();
        List<Artikel> artikli = artikelZrno.pridobiVseArtikle(query);
        Long stArtiklov = artikelZrno.pridobiStArtiklov(query);

        return Response
                .ok(artikli)
                .header("X-Total-Count", stArtiklov)
                .build();
        ////////////////////////
    }
    //vaje 7
    @Schema(description = "Vrne podrobnosti artiklov")
    @SecurityRequirement(name = "none")
    //////
    ////////////vaje 6/////////////////
    @Operation(description = "Vrne podrobnosti artikla.", summary = "Podrobnosti artikla",
            tags = "artikli",
            responses = {@ApiResponse(responseCode = "200",
                    description = "Podrobnosti artikla",
                    content = @Content(
                            schema = @Schema(implementation = Artikel.class))
            )})
    ////////////////////////
    @GET @Path("{id}")
    public Response pridobiArtikel(@PathParam("id") Integer id) {
        Artikel artikel = artikelZrno.pridobiArtikel(id);

        if (artikel != null) {
            log.info("artikel ni null");
            ///vaje 7
            //ko zeli uporabnik pridobit artikel ga doda med priporocila, ce je ta artikel ustrezno najden
            try{
                Response response = httpClient
                        .target(baseUrl + "/priporocila")
                        .request(MediaType.APPLICATION_JSON)
                        .post(Entity.entity(artikel, MediaType.APPLICATION_JSON));
            } catch (WebApplicationException | ProcessingException e){
                log.severe(e.getMessage());
                throw new InternalServerErrorException(e);
            }

            //ce je bil artikel uspešno dodan med priporocila to zalogiramo
//            if (response.getStatus() == 201) {
//                log.info("Artikel " + artikel.getNaziv() + " uspesno dodan med priporocila.");
//            }
//            ///
            return Response.ok(artikel).build();
        }
        else {
            log.info("artikel je null");
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
    //vaje 7
    @Schema(description = "Doda  artikl")
    @SecurityRequirement(name = "none")
    //////
    ////////////vaje 6/////////////////
    @Operation(description = "Dodajanje novega artikla,", summary = "Dodajanje artikla",
            tags = "artikli",
            responses =
                    {@ApiResponse(responseCode = "201",
                            description = "Dodajanje artikla",
                            content = @Content(
                                    schema = @Schema(implementation = Artikel.class))
                    )}
    )
    ////////////////////////
    @POST
    public Response dodajArtikel(Artikel artikel) {
        return Response
                .status(Response.Status.CREATED)
                .entity(artikelZrno.dodajArtikel(artikel))
                .build();
    }
    //vaje 7
    @Schema(description = "Posodobi  artikel")
    @SecurityRequirement(name = "none")
    //////
    ////////////vaje 6/////////////////
    @Operation(description = "Posodobi artikel", summary = "Posodobitev artikla",
            tags = "artikli",
            responses =
                    {@ApiResponse(responseCode = "201",
                            description = "Artikel posodobljen",
                            content = @Content(
                                    schema = @Schema(implementation = Artikel.class))
                    )}
    )
    ////////////////////////
    @PUT
    @Path("{id}")
    public Response posodobiArtikel(@PathParam("id") Integer id, Artikel artikel) {
        return Response
                .status(Response.Status.CREATED)
                .entity(artikelZrno.posodobiArtikel(id, artikel))
                .build();
    }
    //vaje 7
    @Schema(description = "Odstrani  artikel")
    @SecurityRequirement(name = "none")
    //////
    ////////////vaje 6/////////////////
    @Operation(description = "Odstranitev določenega artikla", summary = "Odstranitev artikla",
            tags = "artikli",
            responses =
                    {@ApiResponse(responseCode = "204",
                            description = "Artikel odstranjen",
                            content = @Content(
                                    schema = @Schema(implementation = Artikel.class))
                    )}
    )
    ////////////////////////
    @DELETE
    @Path("{id}")
    public Response odstraniArtikel(@PathParam("id") Integer id) {
        return Response
                .status(Response.Status.NO_CONTENT)
                .entity(artikelZrno.odstraniArtikel(id))
                .build();
    }
}

