package ch.groovlet.model.service;

import ch.groovlet.model.dao.ArtistDAO;
import ch.groovlet.model.representations.Artist;
import org.skife.jdbi.v2.DBI;

import java.util.List;

/**
 * Created by sandro on 20.12.2014.
 */
public class ArtistService implements Service{

    private final ArtistDAO artistDAO;

    public ArtistService(DBI jdbi) {
        artistDAO = jdbi.onDemand(ArtistDAO.class);
    }

    public Artist readById(final long id) {
        return artistDAO.readArtistById(id);
    }

    public List readAll() {
        return artistDAO.readAllArtists();
    }

    public long create(final long id, final String name) {
        long newId = artistDAO.createArtist(id, name);
        return newId;
    }

    public void updateById(final long id, final String name) {
        artistDAO.updateArtist(id, name);
    }

    public void deleteById(final long id) {
        artistDAO.deleteArtistById(id);
    }

    public void deleteAll() {
        artistDAO.deleteAll();
    }
}
