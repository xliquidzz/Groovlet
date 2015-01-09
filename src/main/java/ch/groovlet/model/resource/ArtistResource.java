package ch.groovlet.model.resource;

import ch.groovlet.model.App;
import ch.groovlet.model.representation.Artist;
import ch.groovlet.model.service.ArtistService;
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

    private final ArtistService artistService;

    public ArtistResource(final DBI dbi) {
        artistService = App.getService(ArtistService.class);
    }

    @GET
    public Response getAllArtists() {
        final List<Artist> allArtists = artistService.getAllArtists();
        return Response.ok(allArtists).build();
    }

    @POST
    public Response createArtist(final Artist artist) throws URISyntaxException {
        final long newArtistId = artistService.createArtist(artist.getName());
        return Response.created(new URI(String.valueOf(newArtistId))).build();
    }

    @GET
    @Path("/{id}")
    public Response readArtist(@PathParam("id") final long id) {
        final Artist artist = artistService.readArtistById(id);
        if (artist == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(artist).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteArtist(@PathParam("id") final long id) {
        if (artistService.readArtistById(id) == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        artistService.deleteArtist(id);
        return Response.noContent().build();
    }

    @PUT
    public Response updateArtist( @PathParam("id") final long id, @PathParam("name") final String name) throws URISyntaxException {
        if (artistService.readArtistById(id) == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        artistService.updateArtist(id, name);
        return Response.ok(new Artist(id, name)).build();
    }
}


