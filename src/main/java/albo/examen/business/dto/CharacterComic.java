package albo.examen.business.dto;

import java.util.List;

public class CharacterComic {

    private String character;

    private List<String> comics;

    public CharacterComic(String character, List<String> comics){
        setCharacter(character);
        setComics(comics);
    }

    public String getCharacter() {
        return character;
    }

    public void setCharacter(String character) {
        this.character = character;
    }

    public List<String> getComics() {
        return comics;
    }

    public void setComics(List<String> comics) {
        this.comics = comics;
    }
}
