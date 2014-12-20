package ch.groovlet.model.resource;

import ch.groovlet.model.dao.ArtistDAO;
import ch.groovlet.model.dao.SongDAO;
import ch.groovlet.model.representations.Artist;
import ch.groovlet.model.representations.Song;
import org.skife.jdbi.v2.DBI;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@Produces(MediaType.APPLICATION_JSON)
@Path("/artist")
public class ArtistResource {

    private final ArtistDAO artistDAO;

    public ArtistResource(final DBI dbi) {
        artistDAO = dbi.onDemand(ArtistDAO.class);
    }

    @GET
    public Response getAllArtists() {
        final List<Artist> allArtists = artistDAO.readAllArtists();
        return Response.ok(allArtists).build();
    }

    @POST
    public Response createArtist(final Artist artist) throws URISyntaxException {
        final long newArtistId = artistDAO.createArtist(artist.getId(), artist.getName());
        return Response.created(new URI(String.valueOf(newArtistId))).build();
    }

    @GET
    @Path("/{id}")
    public Response readArtist(@PathParam("id") final long id) {
        final Song song = artistDAO.readArtistById(id);
        if (song == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(song).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteArtist(@PathParam("id") final long id) {
        if (artistDAO.readArtistById(id) == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        artistDAO.deleteArtistById(id);
        return Response.noContent().build();
    }
}


