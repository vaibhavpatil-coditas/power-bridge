package com.coditas.powerbridge.tenant;

import lombok.RequiredArgsConstructor;
import org.flywaydb.core.Flyway;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;

@Service
@RequiredArgsConstructor
public class TenantMigrationService {

    private final DataSource dataSource;

    public void onboardTenant(String tenantId) {
        try(Connection connection = dataSource.getConnection()){
            connection.createStatement()
                    .execute("CREATE SCHEMA IF NOT EXISTS " + tenantId);
        }catch (Exception exception){
            throw new RuntimeException("Unable to onboard");
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
