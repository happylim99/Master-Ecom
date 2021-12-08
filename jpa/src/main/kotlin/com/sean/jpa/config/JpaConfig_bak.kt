package com.sean.jpa.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.env.Environment
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.jdbc.datasource.DriverManagerDataSource
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.orm.jpa.vendor.Database
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.annotation.EnableTransactionManagement
import java.util.*
import javax.persistence.EntityManagerFactory
import javax.sql.DataSource

//@Configuration
//@EnableTransactionManagement
//@EnableJpaRepositories(basePackages = ["com.sean.jpa"],
//        entityManagerFactoryRef = "entityManagerFactory",
//        transactionManagerRef = "transactionManager"
//)
class JpaConfig_bak(
    private val env: Environment
) {

    @Bean(name=["entityManagerFactory"])
    fun entityManagerFactory(): LocalContainerEntityManagerFactoryBean {
        var vendorAdapter = HibernateJpaVendorAdapter()
        vendorAdapter.setDatabase(Database.MYSQL)
        vendorAdapter.setGenerateDdl(true)

        var em = LocalContainerEntityManagerFactoryBean()
        em.dataSource = dataSource()
//        em.setPackagesToScan("com.thomasvitale.jpa.demo.model")
        em.jpaVendorAdapter = vendorAdapter
        val prop = additionalProperties()
        em.setJpaProperties(prop)
        return em
    }

    @Bean
    fun dataSource(): DataSource {
        var dataSource = DriverManagerDataSource()
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver")
        dataSource.url = env.getProperty("spring.datasource.url")
        dataSource.username = env.getProperty("spring.datasource.username")
        dataSource.password = env.getProperty("spring.datasource.password")
        return dataSource;
    }

    @Bean(name=["transactionManager"])
    fun transactionManager(emf: EntityManagerFactory): PlatformTransactionManager {
        var transactionManager = JpaTransactionManager()
        transactionManager.entityManagerFactory = entityManagerFactory().`object`
        return transactionManager
    }

    @Bean
    fun exceptionTranslation(): PersistenceExceptionTranslationPostProcessor {
        return PersistenceExceptionTranslationPostProcessor()
    }

    @Bean
    fun additionalProperties(): Properties {
        var properties = Properties()
        properties.setProperty("hibernate.hbm2ddl.auto",
            env.getProperty("spring.jpa.hibernate.ddl-auto"))
        properties.setProperty("hibernate.dialect",
            "org.hibernate.dialect.MySQL5InnoDBDialect")
//        properties.setProperty("hibernate.current_session_context_class",
//            env.getProperty("spring.jpa.properties.hibernate.current_session_context_class"))
//        properties.setProperty("hibernate.jdbc.lob.non_contextual_creation", env.getProperty("spring.jpa.properties.hibernate.jdbc.lob.non_contextual_creation"));
//        properties.setProperty("hibernate.show_sql",
//            env.getProperty("spring.jpa.show-sql"))
//        properties.setProperty("hibernate.format_sql",
//            env.getProperty("spring.jpa.properties.hibernate.format_sql"))
        return properties
    }

}