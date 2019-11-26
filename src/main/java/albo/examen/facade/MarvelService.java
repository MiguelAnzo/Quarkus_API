package albo.examen.facade;

import albo.examen.business.MarvelBusiness;
import albo.examen.business.dto.CharactersDB;
import albo.examen.business.dto.CollaboratorsDB;
import albo.examen.utils.exception.AlboExamException;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/marvel")
public class MarvelService {

    /**
     * Inyecta la dependencia de la clase de Business
     */
    @Inject
    MarvelBusiness marvelBusiness;

    /**
     * Metodo GET para obtener y actualizar los colaboradores de una personaje de Marvel
     * @param name
     * @return
     * @throws AlboExamException
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/collaborators/{name}")
    public CollaboratorsDB getCollaborators(@PathParam("name") String name) throws AlboExamException {
        return marvelBusiness.getCollaborators(name);
    }

    /**
     * Metodo GET para obtener y actualizar los personajes con los que ha interactuado un personaje de Marvel
     * @param name
     * @return
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/characters/{name}")
    public CharactersDB getCharacters(@PathParam("name") String name) {
        return marvelBusiness.getCharacters(name);
    }

}
