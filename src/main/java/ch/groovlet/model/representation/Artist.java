package ch.groovlet.model.representation;

/**
 * Created by sandro on 20.12.2014.
 */
public class Artist {
    private long id;
    private String name;

    public Artist(final long id, final String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
