package albo.examen.dao.impl;

import albo.examen.business.dto.*;
import albo.examen.dao.MarvelDao;
import albo.examen.dao.rest.MarvelApi;
import albo.examen.utils.exception.AlboExamException;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@ApplicationScoped
public class MarvelDaoImpl implements MarvelDao {

    /**
     * Inyecta el cliente REST para consumir la API de Marvel
     */
    @Inject
    @RestClient
    MarvelApi marvelApi;

    /**
     * Inyecta la clase usada para la conexion a mongoDB
     */
    @Inject
    DatabaseMongo db;

    /**
     * Esta clase se encarga de:
     * - Realizar la consulta a la API de marvel
     * - Obtener una conexion a la coleccion de mongoDB a utilizar
     * - Revisar si el documento del personaje existe, si no, crearlo
     * - Iterar para obtener los los colaboradores y actualizarlos en BD
     * - Actualizar la fecha de la sincronizacion
     * - Regresa un nuevo objeto de CollaboratorsDB, mapeando en el constructor los datos actualizados
     * @param id ID del personaje
     * @param ts TS para la conexion a la API de Marvel
     * @param key Llave publica para la conexion a la API de MArvel
     * @param hash Hash para la conexion a la API de Marvel
     * @return Objeto CollaboratorsDB
     */
    @Override
    public CollaboratorsDB getCollaborators(String id, String ts, String key, String hash) {
        try {
            Response response = marvelApi.getData(id, ts, key, hash);
            MongoCollection<Document> collection = db.getCollection();
            Document doc = db.getDocument(id, collection);
            List<Result> results = response.getData().getResults();
            for (Result result : results) {
                for (Item item : result.getCreators().getItems()) {
                    if (item.getRole() != null) {
                        db.updateArrayField(id, item.getRole(), item.getName(), collection);
                    }
                }
            }
            db.updateField(id, "last_sync", new SimpleDateFormat("dd/MM/yyyy hh:mm:ss").format(new Date()), collection);
            doc = db.getDocument(id, collection);
            return new CollaboratorsDB(doc);
        } catch (Exception e) {
            throw new AlboExamException("Error [" + e.getMessage() + "]", 500);
        }
    }

    /**
     * Esta clase se encarga de:
     * - Realizar la consulta a la API de marvel
     * - Obtener una conexion a la coleccion de mongoDB a utilizar
     * - Revisar si el documento del personaje existe, si no, crearlo
     * - Iterar para obtener los los personajes y actualizarlos en BD
     * - Actualizar la fecha de la sincronizacion
     * - Regresa un nuevo objeto de CharactersDB, mapeando en el constructor los datos actualizados
     * @param id ID del personaje
     * @param ts TS para la conexion a la API de Marvel
     * @param key Llave publica para la conexion a la API de MArvel
     * @param hash Hash para la conexion a la API de Marvel
     * @return Objeto CollaboratorsDB
     */
    @Override
    public CharactersDB getCharacters(String id, String ts, String key, String hash) {
        try {
            Response response = marvelApi.getData(id, ts, key, hash);
            List<Result> results = response.getData().getResults();
            MongoCollection<Document> collection = db.getCollection();
            Document doc = db.getDocument(id, collection);
            for (Result result : results) {
                for (Item item : result.getCharacters().getItems()) {
                    db.updateCharacter(id, item.getName(), "characters.$.Comics", result.getTitle(), collection);
                }
            }
            db.updateField(id, "last_sync", new SimpleDateFormat("dd/MM/yyyy hh:mm:ss").format(new Date()), collection);
            doc = db.getDocument(id, collection);
            return new CharactersDB(doc);
        } catch (Exception e) {
            throw new AlboExamException("Error [" + e.getMessage() + "]", 500);
        }
    }

}
