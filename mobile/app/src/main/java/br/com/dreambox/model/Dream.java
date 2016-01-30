package br.com.dreambox.model;

import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

public class Dream {

    private String title;
    private String description;
    private Dreamer dreamer;
    private Date creationDate;
    private Boolean realized;
    private int id;

    public Dream(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public Dream() {}

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Dreamer getDreamer() {
        return dreamer;
    }

    public void setDreamer(Dreamer dreamer) {
        this.dreamer = dreamer;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Boolean getRealized() {
        return realized;
    }

    public void setRealized(Boolean realized) {
        this.realized = realized;
    }

    public void setId(int id) {this.id = id;}
    public int getId(){return this.id;}

    public void fromJson(JsonObject obj) throws JSONException {
        this.dreamer = new Dreamer();
        this.dreamer.nameFromJson(obj.get("dreamer").getAsLong());

        this.creationDate = null;
        this.realized = false;
    }
}
