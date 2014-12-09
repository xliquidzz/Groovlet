package ch.groovlet.model;

import ch.groovlet.model.resource.SongResource;
import ch.groovlet.model.resource.UserResource;
import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.skife.jdbi.v2.DBI;

public class App extends Application<GroovletConfiguration> {


    public static void main(String[] args) throws Exception {
        new App().run(args);
    }

    @Override
    public void initialize(Bootstrap<GroovletConfiguration> bootstrap) {
        bootstrap.addBundle(new AssetsBundle("/assets", "/index/", "index.html", "assets"));
    }

    @Override
    public void run(GroovletConfiguration configuration, Environment environment)
            throws Exception {
        for (int i = 0; i < configuration.getMessageRepetitions(); i++) {
            System.out.println(configuration.getMessage());
        }

        final DBIFactory factory = new DBIFactory();
        final DBI jdbi = factory.build(environment, configuration.getDataSourceFactory(), "mysql");

        environment.jersey().register(new UserResource(jdbi));
        environment.jersey().register(new SongResource(jdbi));

    }
}