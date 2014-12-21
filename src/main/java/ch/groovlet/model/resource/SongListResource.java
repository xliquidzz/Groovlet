package ch.groovlet.model.resource;

import ch.groovlet.model.dao.SongListDAO;
import ch.groovlet.model.representations.SongList;
import org.skife.jdbi.v2.DBI;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@Produces(MediaType.APPLICATION_JSON)
@Path("/songlist")
public class SongListResource {

    private final SongListDAO songListDAO;

    public SongListResource(final DBI dbi) {
        songListDAO = dbi.onDemand(SongListDAO.class);
    }

    @GET
    public Response getAllSongLists() {
        final List<SongList> allSongLists = songListDAO.readAllSongLists();
        return Response.ok(allSongLists).build();
    }

    @POST
    public Response createSongList(final SongList songList) throws URISyntaxException {
        final long newSongListId = songListDAO.createSongList(songList.getId(), songList.getAdminId(), songList.getName());
        return Response.created(new URI(String.valueOf(newSongListId))).build();
    }

    @GET
    @Path("/{id}")
    public Response readSongList(@PathParam("id") final long id) {
        final SongList songList = songListDAO.readSongListById(id);
        if (songList == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(songList).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteSongList(@PathParam("id") final long id) {
        if (songListDAO.readSongListById(id) == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        songListDAO.deleteSongListById(id);
        return Response.noContent().build();
    }
}
