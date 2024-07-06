package com.sr.goecho;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.zaxxer.hikari.HikariDataSource;

@Configuration
public class DataSourceConfig {

  @Bean
  public DataSource dataSource() {
    HikariDataSource dataSource = new HikariDataSource();
    dataSource.setJdbcUrl(String.format("jdbc:mysql://%s:%d/%s?useSSL=false",
                                         "mysql", // Internal hostname (replace if different)
                                         3306, // Default MySQL port
                                         System.getenv("MYSQL_DATABASE")));
    dataSource.setUsername("root");
    dataSource.setPassword(System.getenv("MYSQL_ROOT_PASSWORD"));
    // Other configuration options for HikariCP
    return dataSource;
  }
}
