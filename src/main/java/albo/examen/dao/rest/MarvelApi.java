package albo.examen.dao.rest;

import albo.examen.business.dto.Response;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * Clase para realizar la peticion a la API de Marvel, configurada en application.properties
 */
@Path("/v1")
@RegisterRestClient
public interface MarvelApi {

    /**
     * Metodo GET para obtener informacion de la API de Marvel
     * @param characterId
     * @param ts
     * @param apikey
     * @param hash
     * @return
     */
    @GET
    @Path("/public/characters/{characterId}/comics")
    @Produces(MediaType.APPLICATION_JSON)
    Response getData(@PathParam("characterId") String characterId, @QueryParam("ts") String ts,
                     @QueryParam("apikey") String apikey, @QueryParam("hash") String hash);

}
