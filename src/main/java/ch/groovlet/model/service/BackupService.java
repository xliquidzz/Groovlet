package ch.groovlet.model.service;

import ch.groovlet.model.App;
import ch.groovlet.model.representation.Artist;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.skife.jdbi.v2.DBI;

import java.io.*;
import java.util.List;

/**
 * Created by sandro on 06.01.2015.
 */
public class BackupService implements Service {
    private final ArtistService artistService;
       /*s
    private final SongService songService;

    private final SongListService songListService;
    private final UserService userService;*/


    public BackupService(final DBI dbi) {
        /*
        songService = App.getService(SongService.class);
        songListService = App.getService(SongListService.class);
        userService = App.getService(UserService.class);
        */
        artistService = App.getService(ArtistService.class);
    }

    public void downloadBackup() {
        List<Artist> artists = artistService.readAll();

    }

    public void uploadBackup() {

    }

    public void store(){
        try {
            List<Artist> artists = artistService.readAll();

            final ByteArrayOutputStream out = new ByteArrayOutputStream();
            final ObjectMapper mapper = new ObjectMapper();

            mapper.writeValue(out, artists);

            final byte[] data = out.toByteArray();

            File file = new File("src/main/resources/backup/backup.json");

            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(new String(data));
            bw.close();

            System.out.println("Done");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void restore(List<Artist> artists){
        App.getLogger().debug("restore invoked");

        artistService.deleteAll();
        for(Artist artist : artists) {
            App.getLogger().debug(artist.getId() + " " + artist.getName());
            artistService.create(artist.getName());
        }
        /*writeToDatabase(artists);
        try {
            BufferedReader bufferedReader = null;
            bufferedReader = new BufferedReader(new FileReader("src/main/resources/backup/backup.json"));

            StringBuffer stringBuffer = new StringBuffer();
            String line = null;

            while((line = bufferedReader.readLine())!= null){
                stringBuffer.append(line).append("\n");
            }

            List<Artist> artists = json(new String(stringBuffer));

            writeToDatabase(artists);
            return artists;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;*/
    }

    public void writeToDatabase(List<Artist> artistsToWriteInto) {


        for (Artist artist : artistsToWriteInto) {
            artistService.create(artist.getName());
        }
    }


    public List<Artist> json(String jsonString) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        List<Artist> artists = objectMapper.readValue(jsonString,objectMapper.getTypeFactory().constructCollectionType(
                        List.class, Artist.class));

        return artists;
    }
}
