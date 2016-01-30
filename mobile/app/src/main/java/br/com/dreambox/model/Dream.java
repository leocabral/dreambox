package br.com.dreambox.model;

import com.google.gson.JsonObject;

import java.util.Date;

public class Dream {

    private String title;
    private String description;
    private Dreamer dreamer;
    private Date creationDate;
    private Boolean realized;
    private long id;

    public Dream() {
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Dreamer getDreamer() {
        return dreamer;
    }

    public Boolean isRealized() {
        return realized;
    }

    public static Dream fromJson(JsonObject obj) {
        Dream dream = new Dream();

        dream.dreamer = Dreamer.fromJson(obj.get("dreamer").getAsJsonObject());
        dream.title = obj.get("name_search").getAsString();
        dream.description = obj.get("description").getAsString();
        dream.creationDate = null;
        dream.realized = false;
        dream.id = obj.get("id").getAsInt();

        return dream;
    }
}
