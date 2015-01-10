package ch.groovlet.model.resource;

import ch.groovlet.model.App;
import ch.groovlet.model.dao.SongDAO;
import ch.groovlet.model.representation.Song;
import ch.groovlet.model.service.SongService;
import org.skife.jdbi.v2.DBI;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/song")
public class SongResource {

    private final SongService songService;

    public SongResource() {
        songService = App.getService(SongService.class);
    }

    @GET
    public Response readAllSongs() {
        List<Song> songs = songService.readAll();
        return Response.ok(songs).build();
    }

    @POST
    public Response createSong(final Song song) throws URISyntaxException {
        final long newSongId = songService.create(song);
        return Response.created(new URI(String.valueOf(newSongId))).build();
    }

    @GET
    @Path("/{id}")
    public Response readSong(@PathParam("id") final long id) {
        final Song song = songService.readById(id);
        if (song == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(song).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteSong(@PathParam("id") final long id) {
        if (songService.readById(id) == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        songService.deleteById(id);
        return Response.noContent().build();
    }
}
