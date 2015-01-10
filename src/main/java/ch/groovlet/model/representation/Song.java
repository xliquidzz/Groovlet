package ch.groovlet.model.representation;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Song {

    private long id;
    private long artistId;
    private String title;
    private String Genre;
    private int votes;
    private String youtubeString;

    @JsonCreator
    public Song(@JsonProperty("id") long id, @JsonProperty("fk_artistId") long artistId, @JsonProperty("title") String title, @JsonProperty("genre") String Genre, @JsonProperty("votes") int votes,
                @JsonProperty("youtubeString") String youtubeString) {
        this.id = id;
        this.artistId = artistId;
        this.title = title;
        this.Genre = Genre;
        this.votes = votes;
        this.youtubeString = youtubeString;
    }

    public String getTitle() {
        return title;
    }

    public long getId() {
        return id;
    }

    public long getArtistId() {
        return artistId;
    }

    public String getGenre() {
        return Genre;
    }

    public int getVotes() {
        return votes;
    }

    public String getYoutubeString() {
        return youtubeString;
    }
}
