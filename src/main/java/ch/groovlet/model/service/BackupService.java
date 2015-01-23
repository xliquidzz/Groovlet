package ch.groovlet.model.service;

import ch.groovlet.model.App;
import ch.groovlet.model.representation.Artist;
import ch.groovlet.model.representation.Song;
import ch.groovlet.model.response.ApiResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.skife.jdbi.v2.DBI;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sandro on 06.01.2015.
 */
public class BackupService implements Service {
    private final ArtistService artistService;
    private final SongService songService;
    /*
    private final SongListService songListService;
    private final UserService userService;
    */


    public BackupService() {

        songService = App.getService(SongService.class);
        /*
        songListService = App.getService(SongListService.class);
        userService = App.getService(UserService.class);
        */
        artistService = App.getService(ArtistService.class);
    }

    public List<Song> downloadBackup() {
        return songService.readAll();
    }

    public void restore(final List<Song> songs){
        App.getLogger().warn("restore invoked");
        songService.deleteAll();
        for(Song song : songs) {
            App.getLogger().debug(song.getId() + " " + song.getTitle());
            songService.create(song);
        }
    }
}
