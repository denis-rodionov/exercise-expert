package com.example.exerciseexpert

import com.mongodb.ConnectionString
import com.mongodb.MongoClientSettings
import com.mongodb.client.MongoClient
import com.mongodb.client.MongoClients
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration
import org.springframework.data.mongodb.core.MongoClientFactoryBean
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories


@Configuration
@EnableMongoRepositories
class ApplicationConfig : AbstractMongoClientConfiguration() {
    override fun getDatabaseName(): String {
        return "exercises"
    }

    override fun getMappingBasePackage(): String {
        return "com.example.exerciseexpert.repository"
    }

    @Bean
    override fun mongoClient(): MongoClient {
        val connectionString =
            ConnectionString("mongodb+srv://kotlin:123@cluster0.jtkhl.mongodb.net/Cluster0?retryWrites=true&w=majority")
        val settings = MongoClientSettings.builder()
            .applyConnectionString(connectionString)
            .build()
        return MongoClients.create(settings)
    }
}