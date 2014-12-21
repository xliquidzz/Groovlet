package ch.groovlet.model.representations;

/**
 * Created by sandro on 20.12.2014.
 */
public class SongList {

    private long id;
    private long adminId;
    private String name;

    public SongList(final long id, final long adminId, final String name) {
        this.id = id;
        this.adminId = adminId;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public long getId() {
        return id;
    }

    public long getAdminId() {
        return adminId;
    }
}
