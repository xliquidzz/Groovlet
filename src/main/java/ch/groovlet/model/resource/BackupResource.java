package ch.groovlet.model.resource;

import ch.groovlet.model.App;
import ch.groovlet.model.representations.Artist;
import ch.groovlet.model.service.BackupService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by sandro on 06.01.2015.
 */

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/backup")
public class BackupResource {
    private final BackupService backupService;

    public BackupResource() {
        this.backupService = App.getService(BackupService.class);
    }

    @GET
    @Path("/store")
    public Response store() {
        return Response.ok().build();
    }

    @POST
    @Path("/restore")
    public Response restore(final List<Artist> artists) {
        backupService.restore(artists);
        return Response.ok().build();
    }
}
