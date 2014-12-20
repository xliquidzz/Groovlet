package ch.groovlet.model.resource;

import ch.groovlet.model.dao.SongDAO;
import ch.groovlet.model.representations.Song;
import org.skife.jdbi.v2.DBI;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;

@Produces(MediaType.APPLICATION_JSON)
@Path("/song")
public class SongResource {

    private final SongDAO songDAO;

    public SongResource(final DBI dbi) {
        songDAO = dbi.onDemand(SongDAO.class);
    }

    @POST
    public Response createSong(final Song song) throws URISyntaxException {
        final long newSongId = songDAO.createSong(song.getId(),
                song.getArtistId(), song.getTitle(), song.getGenre(),
                song.getVotes(), song.getYoutubeString());
        return Response.created(new URI(String.valueOf(newSongId))).build();
    }

    @GET
    @Path("/{id}")
    public Response readSong(@PathParam("id") final long id) {
        final Song song = songDAO.readSongById(id);
        if (song == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(song).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteSong(@PathParam("id") final long id) {
        if (songDAO.readSongById(id) == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        songDAO.deleteSongById(id);
        return Response.noContent().build();
    }


}
