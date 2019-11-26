package albo.examen.dao;

import albo.examen.business.dto.CharactersDB;
import albo.examen.business.dto.CollaboratorsDB;

public interface MarvelDao {

    CollaboratorsDB getCollaborators(String id, String ts, String key, String hash);

    CharactersDB getCharacters(String id, String ts, String key, String hash);

}
