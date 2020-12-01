package si.fri.prpo.skupina14.api.v1.mappers;

import si.fri.prpo.skupina14.izjeme.NeveljavniRegistriraniUporabnikDtoException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class NeveljavniRegistriraniUporabnikDtoExceptionMapper implements ExceptionMapper<NeveljavniRegistriraniUporabnikDtoException>{

        @Override
    public Response toResponse(NeveljavniRegistriraniUporabnikDtoException exception) {
        return Response
                .status(Response.Status.BAD_REQUEST)
                .entity("{\"napaka\": \"" + exception.getMessage() + "\"}")
                .build();
    }
}
