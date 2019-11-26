package albo.examen.business;

import albo.examen.business.dto.CharactersDB;
import albo.examen.business.dto.CollaboratorsDB;

public interface MarvelBusiness {

    CollaboratorsDB getCollaborators(String name);

    CharactersDB getCharacters(String name);

}
