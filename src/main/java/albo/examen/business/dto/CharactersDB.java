package albo.examen.business.dto;

import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class CharactersDB {

    private String last_sync;

    private List<CharacterComic> characters;

    public String getLast_sync() {
        return last_sync;
    }

    public void setLast_sync(String last_sync) {
        this.last_sync = last_sync;
    }

    public List<CharacterComic> getCharacters() {
        return characters;
    }

    public void setCharacters(List<CharacterComic> characters) {
        this.characters = characters;
    }

    /**
     * Constructor de la clase CharactersDB usada para mostrar los personajes con los que ha interactuado un
     * personaje de Marvel con el formato indicado en el examen. Realiza un mapeo de un Document hacia este pojo.
     * @param doc
     */
    public CharactersDB(Document doc) {
        setLast_sync(doc.getString("last_sync"));
        List<Document> list = doc.getList("characters", Document.class);
        characters = new ArrayList<>();
        for (Document doc1 : list) {
            characters.add(new CharacterComic(doc1.getString("character"), doc1.getList("Comics", String.class)));
        }
    }
}
