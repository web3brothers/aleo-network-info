package tech.web3brothers.aleonetworkstate;


import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.containers.PostgreSQLContainer;

@SpringBootTest
public class BaseIntegrationTest {

    static {
        startAndInitializePostgresTestContainer();
    }

    private static void startAndInitializePostgresTestContainer() {
        PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:13");

        postgres.withCommand("postgres", "-c", "fsync=off", "-N", "2000", "-B", "2048MB");
        postgres.start();

        System.setProperty("spring.datasource.url", postgres.getJdbcUrl());
        System.setProperty("spring.datasource.username", postgres.getUsername());
        System.setProperty("spring.datasource.password", postgres.getPassword());
    }


}
