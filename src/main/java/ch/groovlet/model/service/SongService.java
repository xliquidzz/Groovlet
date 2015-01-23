package ch.groovlet.model.service;

import ch.groovlet.model.dao.SongDAO;
import ch.groovlet.model.representation.Song;
import org.skife.jdbi.v2.DBI;

import java.util.List;

/**
 * Created by sandro on 20.12.2014.
 */
public class SongService implements Service {
    private final SongDAO songDAO;

    public SongService(DBI jdbi) {
        songDAO = jdbi.onDemand(SongDAO.class);
    }

    public Song readById(final long id) {
        return songDAO.readSongById(id);
    }

    public List<Song> readAll() {
        return songDAO.readAllSongs();
    }

    public long create(final Song song) {
        long newId = songDAO.createSong(song.getId(), song.getArtistId(), song.getTitle(), song.getGenre(),song.getVotes(), song.getYoutubeString());
        return newId;
    }

    public void updateById(final long id, final Song song) {
    }


    public void deleteById(final long id) {
        songDAO.deleteSongById(id);
    }

    public void deleteAll() {
        songDAO.deleteAll();
    }

}
