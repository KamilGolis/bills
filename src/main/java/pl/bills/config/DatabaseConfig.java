package pl.bills.config;

public abstract class DatabaseConfig {
    protected void configureDataSource(org.apache.tomcat.jdbc.pool.DataSource dataSource) {
        dataSource.setMaxActive(20);
        dataSource.setMaxIdle(8);
        dataSource.setMinIdle(8);
        dataSource.setTestOnBorrow(false);
        dataSource.setTestOnReturn(false);
        dataSource.setValidationQuery("SELECT 1");
    }
}

@Configuration
@Profile("heroku")
class HerokuDatabaseConfig extends DatabaseConfig {

    @Value("${spring.datasource.uri}")
    private String databaseUri;

    @Bean
    public DataSource dataSource() throws URISyntaxException {
        org.apache.tomcat.jdbc.pool.DataSource dataSource = new org.apache.tomcat.jdbc.pool.DataSource();

        URI dbUri = new URI(databaseUri);

        dataSource.setUsername(dbUri.getUserInfo().split(":")[0]);
        dataSource.setPassword(dbUri.getUserInfo().split(":")[1]);
        dataSource.setUrl("jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath());

        configureDataSource(dataSource);

        return dataSource;
    }
}
