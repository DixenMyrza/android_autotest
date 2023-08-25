package com.android_testing.config;

import oracle.jdbc.pool.OracleDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
public class DatabaseConfig {

    @Autowired
    private PropertiesConfig propertiesConfig;


    @Bean
    @Qualifier("dataSource")
    public DataSource dataSourceWebApp() throws SQLException {
        OracleDataSource dataSource = new OracleDataSource();
        dataSource.setURL(propertiesConfig.getDatasource().getUrl());
        dataSource.setUser(propertiesConfig.getDatasource().getUsername());
        dataSource.setPassword(propertiesConfig.getDatasource().getPassword());

        return dataSource;
    }


    @Bean
    @Qualifier("jdbcTemplateWebApp")
    public JdbcTemplate jdbcTemplateWebApp() throws SQLException {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSourceWebApp());
        jdbcTemplate.setResultsMapCaseInsensitive(true);

        return jdbcTemplate;
    }
}