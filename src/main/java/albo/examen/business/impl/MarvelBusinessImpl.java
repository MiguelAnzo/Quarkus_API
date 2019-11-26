package albo.examen.business.impl;

import albo.examen.business.MarvelBusiness;
import albo.examen.business.dto.CharactersDB;
import albo.examen.business.dto.CollaboratorsDB;
import albo.examen.dao.MarvelDao;
import albo.examen.utils.exception.AlboExamException;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class MarvelBusinessImpl implements MarvelBusiness {

    /**
     * Inyeccion de la clase del dao
     */
    @Inject
    MarvelDao dao;

    /**
     * Informacion de conexion hacia la api de marvel, idealmente deberia estar previamente almacenada en base de
     * datos de manera cifrada, se encuentra aqui por conveniencia para propositos del examen.
     */
    private static final String TS = "1";
    private static final String APIKEY = "91f34e288cff41a3ac1c19414fcab4d5";
    private static final String HASH = "9024affeddcea1b0bed23155a7e2a2d8";

    /**
     * Clase de business para collaborators, se encarga de hacer la relacion de nombre-id y de llamar a la clase de dao
     * @param name
     * @return
     */
    @Override
    public CollaboratorsDB getCollaborators(String name) {

        String id = getId(name);
        return dao.getCollaborators(id, TS, APIKEY, HASH);

    }

    /**
     * Clase de business para characters, se encarga de hacer la relacion de nombre-id y de llamar a la clase de dao
     * @param name
     * @return
     */
    @Override
    public CharactersDB getCharacters(String name) {
        String id = getId(name);
        return dao.getCharacters(id, TS, APIKEY, HASH);
    }

    /**
     * Metodo auxiliar para obtener el id de Marvel del personaje, en caso de no encontrarlo lanza una excepcion de
     * parametros incorrectos. El Id se obtuvo previamente mediante la API de Marvel.
     * Este metodo se encuentra asi debido a que se necesita el id para hacer la consulta al endpoint que contiene
     * la informacion que necesitamos. En caso de querer que sirva para cualquier personaje se podria realizar una
     * llamada previa a la API de Marvel para obtener el Id, pero se necesitaria el nombre real del personaje
     * ej. "Iron Man" en ves de "ironman"
     * @param name
     * @return
     */
    private String getId(String name) {
        switch (name) {
            case "ironman":
                return "1009368";
            case "capamerica":
                return "1009220";
        }
        throw new AlboExamException("Parametros incorrectos", 400);
    }

}
