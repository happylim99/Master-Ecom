package com.sean.ecom.config

import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.ClassPathResource
//import org.springframework.jdbc.datasource.init.DataSourceInitializer
//import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator
import javax.sql.DataSource


//@Configuration
class SqlConfig {

//    @Bean
//    fun dataSourceInitializer(@Qualifier("dataSource") dataSource: DataSource?): DataSourceInitializer? {
//        val resourceDatabasePopulator = ResourceDatabasePopulator()
//        resourceDatabasePopulator.addScript(ClassPathResource("/data.sql"))
//        val dataSourceInitializer = DataSourceInitializer()
//        dataSourceInitializer.setDataSource(dataSource!!)
//        dataSourceInitializer.setDatabasePopulator(resourceDatabasePopulator)
//        return dataSourceInitializer
//    }
}