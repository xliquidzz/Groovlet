package ch.groovlet.model.dao;

import ch.groovlet.model.dao.mappers.SongListMapper;
import ch.groovlet.model.representations.SongList;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.GetGeneratedKeys;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import java.util.List;

/**
 * Created by sandro on 20.12.2014.
 */
@RegisterMapper(SongListMapper.class)
public interface SongListDAO {

    @GetGeneratedKeys
    @SqlUpdate("INSERT INTO songlist (id, fk_adminId, name) VALUES (NULL, :adminId, :name)")
    long createSongList(@Bind("id") final long id, @Bind("adminId") final long adminId, @Bind("name") final String name);

    @SqlQuery("SELECT * FROM songlist")
    List<SongList> readAllSongLists();

    @SqlQuery("SELECT * FROM songlist WHERE id = :id")
    SongList readSongListById(@Bind("id") final long id);

    @SqlUpdate("DELETE FROM songlist WHERE id = :id")
    void deleteSongListById(@Bind("id") final long id);
}
