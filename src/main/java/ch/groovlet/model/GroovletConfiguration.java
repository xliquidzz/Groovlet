package ch.groovlet.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.cache.CacheBuilderSpec;
import io.dropwizard.Configuration;
import io.dropwizard.auth.CachingAuthenticator;
import io.dropwizard.db.DataSourceFactory;

public class GroovletConfiguration extends Configuration {

    private static final String CACHE_POLICY = "authenticationCachePolicy: maximumSize=10000, expireAfterAccess=10m";

    @JsonProperty
    private String message;

    @JsonProperty
    private int messageRepetitions;

    @JsonProperty
    private DataSourceFactory database = new DataSourceFactory();

    @JsonProperty
    private String authenticationCachePolicy;

    public DataSourceFactory getDataSourceFactory() {
        return database;
    }

    public String getMessage() {
        return message;
    }

    public int getMessageRepetitions() {
        return messageRepetitions;
    }

<<<<<<< Updated upstream
    public CacheBuilderSpec getAuthenticationCachePolicy() {
        return CacheBuilderSpec.parse(CACHE_POLICY);
=======
    public String getAuthenticationCachePolicy() {
        return authenticationCachePolicy;
>>>>>>> Stashed changes
    }
}
