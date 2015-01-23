package ch.groovlet.model;

import ch.groovlet.model.representation.User;
import ch.groovlet.model.service.BackupService;

import ch.groovlet.model.resource.ArtistResource;
import ch.groovlet.model.resource.SongListResource;
import ch.groovlet.model.resource.SongResource;
import ch.groovlet.model.resource.UserResource;
import ch.groovlet.model.service.ArtistService;
import ch.groovlet.model.service.Service;
import ch.groovlet.model.resource.*;
import ch.groovlet.model.service.SongService;
import com.codahale.metrics.MetricRegistry;
import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.auth.CachingAuthenticator;
import io.dropwizard.auth.basic.BasicAuthProvider;
import io.dropwizard.auth.basic.BasicCredentials;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.skife.jdbi.v2.DBI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class App extends Application<GroovletConfiguration> {
    private static final Logger LOGGER = LoggerFactory.getLogger(App.class);

    private CachingAuthenticator<BasicCredentials, User> cachedAuthenticator;

    private static final Map<Integer, Service> services = new HashMap<Integer, Service>();

    public static void main(String[] args) throws Exception {
        LOGGER.info("Starting application with arguments: %s", new Object[]{args});
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

        final DBIFactory factory = new DBIFactory();
        final DBI jdbi = factory.build(environment, configuration.getDataSourceFactory(), "mysql");


        services.put(ArtistService.class.hashCode(), new ArtistService(jdbi));
        services.put(SongService.class.hashCode(), new SongService(jdbi));
        services.put(BackupService.class.hashCode(), new BackupService());
        /*
        services.put(SongListService.class.hashCode(), new SongListService(jdbi));
        services.put(UserService.class.hashCode(), new UserService(jdbi));
*/
        environment.jersey().register(new UserResource(jdbi));
        environment.jersey().register(new SongResource());
        environment.jersey().register(new ArtistResource());
        environment.jersey().register(new SongListResource(jdbi));
        environment.jersey().register(new BackupResource());

        GroovletAuthenticator authenticator = new GroovletAuthenticator(jdbi);
        environment.jersey().register(new BasicAuthProvider<User>(cachedAuthenticator, "REALM MESSAGE"));

        environment.jersey().setUrlPattern("/api/*");
    }

    public static Logger getLogger() {
        return LOGGER;
    }

}