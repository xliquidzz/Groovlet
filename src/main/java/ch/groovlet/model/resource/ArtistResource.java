package ch.groovlet.model.resource;

import ch.groovlet.model.App;
<<<<<<< HEAD
import ch.groovlet.model.representation.Artist;
import ch.groovlet.model.service.ArtistService;
import org.skife.jdbi.v2.DBI;
=======
import ch.groovlet.model.representations.Artist;
import ch.groovlet.model.service.ArtistService;
>>>>>>> origin/master

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/artist")
public class ArtistResource {

    private final ArtistService artistService;

<<<<<<< HEAD
    public ArtistResource(final DBI dbi) {
        artistService = App.getService(ArtistService.class);
=======
    public ArtistResource() {
        this.artistService = App.getService(ArtistService.class);
>>>>>>> origin/master
    }

    @GET
    public Response getAllArtists() {
<<<<<<< HEAD
        final List<Artist> allArtists = artistService.getAllArtists();
=======
        final List<Artist> allArtists = artistService.readAll();
>>>>>>> origin/master
        return Response.ok(allArtists).build();
    }

    @POST
    public Response createArtist(final Artist artist) throws URISyntaxException {
<<<<<<< HEAD
        final long newArtistId = artistService.createArtist(artist.getName());
=======
        final long newArtistId = artistService.create(artist.getId(), artist.getName());
>>>>>>> origin/master
        return Response.created(new URI(String.valueOf(newArtistId))).build();
    }

    @PUT
    @Path("/{id}")
    public Response updateArtist(@PathParam("id") final long id, final Artist artist) {
        if (artistService.readById(id) == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        artistService.updateById(id, artist.getName());
        return Response.ok(new Artist(id, artist.getName())).build();
    }


    @GET
    @Path("/{id}")
    public Response readArtist(@PathParam("id") final long id) {
<<<<<<< HEAD
        final Artist artist = artistService.readArtistById(id);
=======
        final Artist artist =(Artist) artistService.readById(id);
>>>>>>> origin/master
        if (artist == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(artist).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteArtist(@PathParam("id") final long id) {
<<<<<<< HEAD
        if (artistService.readArtistById(id) == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        artistService.deleteArtist(id);
=======
        if (artistService.readById(id) == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        artistService.deleteById(id);
>>>>>>> origin/master
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


