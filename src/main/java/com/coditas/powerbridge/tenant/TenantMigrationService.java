package com.coditas.powerbridge.tenant;

import com.coditas.powerbridge.exception.UnableToOnboardException;
import lombok.RequiredArgsConstructor;
import org.flywaydb.core.Flyway;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Statement;

@Service
@RequiredArgsConstructor
public class TenantMigrationService {

    private final DataSource dataSource;

    public void onboardTenant(String tenantId) {
        try(Connection connection = dataSource.getConnection()){
            Statement statement = connection.createStatement();
            statement.execute("CREATE SCHEMA IF NOT EXISTS " + tenantId);
            statement.close();
        }catch (Exception exception){
            throw new UnableToOnboardException("Unable to onboard");
        }

        Flyway flyway = Flyway.configure()
                .dataSource(dataSource)
                .schemas(tenantId)
                .defaultSchema(tenantId)
                .locations("classpath:db/migration/tenant")
                .load();

        flyway.migrate();
    }
}
