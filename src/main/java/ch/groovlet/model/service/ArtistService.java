package ch.groovlet.model.service;

import ch.groovlet.model.dao.ArtistDAO;
import ch.groovlet.model.representation.Artist;
import org.skife.jdbi.v2.DBI;

import java.util.List;

/**
 * Created by sandro on 20.12.2014.
 */
public class ArtistService implements Service {

    private final ArtistDAO artistDAO;

    public ArtistService(final DBI dbi) {
        artistDAO = dbi.onDemand(ArtistDAO.class);
    }

    public List<Artist> getAllArtists() {
       return artistDAO.readAllArtists();
    }

    public long createArtist(final String name) {
        return artistDAO.createArtist(name);
    }

    public Artist readArtistById(final long id) {
        return artistDAO.readArtistById(id);
    }

    public void updateArtist(final long id,final String name) {
        artistDAO.updateArtist(id, name);
    }

    public void deleteArtist(final long id) {
        artistDAO.deleteArtistById(id);
    }
}
