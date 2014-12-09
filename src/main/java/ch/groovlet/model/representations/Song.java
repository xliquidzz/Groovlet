package ch.groovlet.model.representations;

public class Song {

    private long id;
    private String title;
    private String Genre;
    private int votes;
    private long artistId;
    private String youtubeString;

    public Song(long id, long artistId, String title, String Genre, int votes,
                String youtubeString) {
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
