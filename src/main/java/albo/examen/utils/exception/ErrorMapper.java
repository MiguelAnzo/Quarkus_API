package albo.examen.utils.exception;

import javax.json.Json;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

@Provider
public class ErrorMapper implements ExceptionMapper<AlboExamException> {

    /**
     * Mapper para mandar el codigo de retorno que se encuentra en AlboExamException
     * @param e
     * @return
     */
    @Override
    public Response toResponse(AlboExamException e) {
        return Response.status(e.getCode()).entity(Json.createObjectBuilder().add("error", e.getMessage()).add("code", e.getCode()).build())
                .build();
    }
}
