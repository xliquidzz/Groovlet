package ch.groovlet.model.dao;

import ch.groovlet.model.dao.mappers.ArtistMapper;
import ch.groovlet.model.representations.Artist;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.GetGeneratedKeys;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import java.util.List;

/**
 * Created by sandro on 20.12.2014.
 */
@RegisterMapper(ArtistMapper.class)
public interface ArtistDAO {
    @GetGeneratedKeys
    @SqlUpdate("INSERT INTO artist (id, name) VALUES (NULL, :name)")
    long createArtist(@Bind("id") long id, @Bind("name") String name);

    @SqlQuery("SELECT * FROM artist WHERE id = :id")
    Artist readArtistById(@Bind("id") final long id);

    @SqlUpdate("DELETE FROM artist WHERE id = :id")
    void deleteArtistById(@Bind("id") final long id);

    @SqlQuery("SELECT * FROM artist")
    List<Artist> readAllArtists();
}
