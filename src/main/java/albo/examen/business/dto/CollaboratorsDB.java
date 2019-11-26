package albo.examen.business.dto;

import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class CollaboratorsDB {

    private String last_sync;

    private List<String> editors;

    private List<String> writers;

    private List<String> letterers;

    private List<String> colorists;

    private List<String> pencillers;

    private List<String> pencilers;

    private List<String> pencillers_cover;

    private List<String> pencilers_cover;

    private List<String> inkers;

    private List<String> colorists_cover;

    /**
     * Constructor de la clase CollaboratorsDB usada para mostrar los colaboradoresde un personaje de Marvel con
     * el formato indicado en el examen. Realiza un mapeo de un Document hacia este pojo.
     * @param doc
     */
    public CollaboratorsDB(Document doc) {
        setLast_sync(doc.getString("last_sync"));
        setColorists(doc.getList("colorist", String.class));
        setColorists_cover(doc.getList("colorist (cover)", String.class));
        setEditors(doc.getList("editor", String.class));
        setInkers(doc.getList("inker", String.class));
        setLetterers(doc.getList("letterer", String.class));
        setPencillers(doc.getList("penciller", String.class));
        setPencilers(doc.getList("penciler", String.class));
        setPencillers_cover(doc.getList("penciller (cover)", String.class));
        setPencilers_cover(doc.getList("penciler (cover)", String.class));
        setWriters(doc.getList("writer", String.class));
    }

    public String getLast_sync() {
        return last_sync;
    }

    private void setLast_sync(String last_sync) {
        this.last_sync = last_sync;
    }

    public List<String> getEditors() {
        return editors;
    }

    private void setEditors(List<String> editors) {
        this.editors = editors;
    }

    public List<String> getWriters() {
        return writers;
    }

    private void setWriters(List<String> writers) {
        this.writers = writers;
    }

    public List<String> getLetterers() {
        return letterers;
    }

    private void setLetterers(List<String> letterers) {
        this.letterers = letterers;
    }

    public List<String> getColorists() {
        return colorists;
    }

    private void setColorists(List<String> colorists) {
        this.colorists = colorists;
    }

    public List<String> getPencillers() {
        return pencillers;
    }

    private void setPencillers(List<String> pencillers) {
        this.pencillers = pencillers;
    }

    public List<String> getPencillers_cover() {
        return pencillers_cover;
    }

    private void setPencillers_cover(List<String> pencillers_cover) {
        this.pencillers_cover = pencillers_cover;
    }

    public List<String> getInkers() {
        return inkers;
    }

    private void setInkers(List<String> inkers) {
        this.inkers = inkers;
    }

    public List<String> getColorists_cover() {
        return colorists_cover;
    }

    private void setColorists_cover(List<String> colorists_cover) {
        this.colorists_cover = colorists_cover;
    }

    public List<String> getPencilers() {
        return pencilers;
    }

    private void setPencilers(List<String> pencilers) {
        this.pencilers = pencilers;
    }

    public List<String> getPencilers_cover() {
        return pencilers_cover;
    }

    private void setPencilers_cover(List<String> pencilers_cover) {
        this.pencilers_cover = pencilers_cover;
    }
}
