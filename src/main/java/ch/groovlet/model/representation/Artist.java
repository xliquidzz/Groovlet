package ch.groovlet.model.representation;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;

/**
 * Created by sandro on 20.12.2014.
 */
public class Artist implements Representation{

    private long id;

    private String name;

    @JsonCreator
    public Artist(@JsonProperty("id") final long id, @JsonProperty("name") final String name) {
        this.id = id;
        this.name = name;
    }

    @JsonSetter("name")
    public void setName(@JsonProperty("name") final String name) {
        this.name = name;
    }
    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
