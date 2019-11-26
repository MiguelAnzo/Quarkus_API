package albo.examen.dao.impl;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import org.bson.Document;
import org.bson.conversions.Bson;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class DatabaseMongo {

    /**
     * Este metodo crea una instancia del cliente de mongodb, selecciona la BD y la coleccion, de no existir son
     * creadas automaticamente.
     * @return
     */
    public MongoCollection<Document> getCollection() {
        MongoClient client = new MongoClient();
        MongoDatabase db = client.getDatabase("MarvelExamen");
        return db.getCollection("Marvel");
    }

    /**
     * Obtiene el documento completo de un personaje y lo regresa como tipo Document.
     * De no encontrarse un documento en BD para dicho personaje se crea uno nuevo.
     * @param name
     * @param collection
     * @return
     */
    public Document getDocument(String name, MongoCollection collection) {
        Document doc = (Document) collection.find(getFilter("Id", name)).first();
        if (doc == null) {
            collection.insertOne(new Document("Id", name));
            doc = (Document) collection.find(getFilter("Id", name)).first();
        }
            return doc;
    }

    /**
     * Hace una actualizacion a un campo Array del documento con el id pasado como parametro.
     * Si el elemento que se va a agregar ya existe no es agregado de nuevo.
     * @param name Id del documento a actualizar
     * @param key Nombre del Array a actualizar
     * @param value Valor a agregar
     * @param collection Instancia de la coleccion usada para la conexion
     */
    public void updateArrayField(String name, String key, String value, MongoCollection collection) {
        collection.updateOne(getFilter("Id", name), Updates.addToSet(key, value));
    }

    /**
     * Hace una actualizacion a un campo del documento con el id pasado como parametro.
     * @param name Id del documento a actualizar
     * @param key Nombre del campo a actualizar
     * @param value Valor nuevo
     * @param collection Instancia de la coleccion usada para la conexion
     */
    public void updateField(String name, String key, String value, MongoCollection collection) {
        collection.updateOne(getFilter("Id", name), new Document("$set", new Document(key, value)));
    }

    /**
     * Hace una actualizacion a un campo Array del subdocumento con el id pasado como parametro.
     * Si el elemento que se va a agregar ya existe no es agregado de nuevo.
     * Primero se crea el subdocumento en caso de que no exista
     * @param name Id del documento a actualizar
     * @param ch Nombre del personaje
     * @param key Campo a actualizar en el subdocumento
     * @param value Valor nuevo a agregarse
     * @param collection Instancia de la coleccion usada para la conexion
     */
    public void updateCharacter(String name, String ch, String key, String value, MongoCollection collection) {
        Document doc1 = new Document("character", ch);
        collection.updateOne(getFilter("Id", name), Updates.addToSet("characters", doc1));
        collection.updateOne(getAndFilter(name, ch), Updates.addToSet(key, value));

    }

    /**
     * Crea un filtro simple para busqueda en BD
     * @param key
     * @param value
     * @return
     */
    private Bson getFilter(String key, String value) {
        return Filters.eq(key, value);
    }


    /**
     * Crea un filtro dolble para el metodo updateCharacter
     * @param filter1
     * @param filter2
     * @return
     */
    private Bson getAndFilter(String filter1, String filter2) {
        return Filters.and(
                Filters.eq("Id", filter1),
                Filters.eq("characters.character", filter2)
        );
    }

}
