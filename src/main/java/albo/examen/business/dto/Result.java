package albo.examen.business.dto;

public class Result {

    private String id;

    private String title;

    private Creators creators;

    private Characters characters;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Creators getCreators() {
        return creators;
    }

    public void setCreators(Creators creators) {
        this.creators = creators;
    }

    public Characters getCharacters() {
        return characters;
    }

    public void setCharacters(Characters characters) {
        this.characters = characters;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
