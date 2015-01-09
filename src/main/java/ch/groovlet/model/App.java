package ch.groovlet.model;

import ch.groovlet.model.representation.Artist;
import ch.groovlet.model.resource.ArtistResource;
import ch.groovlet.model.resource.SongListResource;
import ch.groovlet.model.resource.SongResource;
import ch.groovlet.model.resource.UserResource;
import ch.groovlet.model.service.ArtistService;
import ch.groovlet.model.service.Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JSR310Module;
import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.skife.jdbi.v2.DBI;

import java.util.HashMap;
import java.util.Map;

public class App extends Application<GroovletConfiguration> {

    private static final Map<Integer, Service> services = new HashMap<>();

    public static void main(String[] args) throws Exception {
        new App().run(args);
    }

    public static <T extends Service> T getService(Class<T> serviceClass) {
        return serviceClass.cast(services.get(serviceClass.hashCode()));
    }

    @Override
    public void initialize(Bootstrap<GroovletConfiguration> bootstrap) {
        bootstrap.addBundle(new AssetsBundle("/assets", "/", "index.html"));

    }

    @Override
    public void run(GroovletConfiguration configuration, Environment environment)
            throws Exception {
        for (int i = 0; i < configuration.getMessageRepetitions(); i++) {
            System.out.println(configuration.getMessage());
        }

        final DBIFactory factory = new DBIFactory();
        final DBI jdbi = factory.build(environment, configuration.getDataSourceFactory(), "mysql");


        services.put(ArtistService.class.hashCode(), new ArtistService(jdbi));

        environment.jersey().register(new UserResource(jdbi));
        environment.jersey().register(new SongResource(jdbi));
        environment.jersey().register(new ArtistResource(jdbi));
        environment.jersey().register(new SongListResource(jdbi));

        //environment.jersey().register(new BasicAuthProvider<User>(new GroovletAuthenticator(jdbi), "Web Service Realm"));

        environment.jersey().setUrlPattern("/api/*");
    }
}