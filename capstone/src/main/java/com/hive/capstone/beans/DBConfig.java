package com.hive.capstone.beans;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class DBConfig {

    @Bean
    public DataSource getDataSource() {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName("org.postgresql.Driver");
        dataSourceBuilder.url("jdbc:postgresql://ep-frosty-hall-a8sbeosf-pooler.eastus2.azure.neon.tech/hive-db?user=hive-db_owner&password=npg_w8ZXxHq2BnLh&sslmode=require");
        dataSourceBuilder.username("hive-db_owner");
        dataSourceBuilder.password("npg_w8ZXxHq2BnLh");
        return dataSourceBuilder.build();
    }
}
