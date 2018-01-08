package com.bogdevich.auth.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Datasource configuration class.
 *
 * @author Eugene Bogdevich
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.bogdevich.auth.repository")
public class DatasourceConfiguration {

}
