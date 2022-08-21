package com.example.exerciseexpert

import com.mongodb.ConnectionString
import com.mongodb.MongoClientSettings
import com.mongodb.client.MongoClient
import com.mongodb.client.MongoClients
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration
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
            //ConnectionString("mongodb+srv://kotlin:123@cluster0.jtkhl.mongodb.net/Cluster0?retryWrites=true&w=majority")
            //ConnectionString("mongodb://kotlin:123@cluster0-shard-00-00.jtkhl.mongodb.net:27017,cluster0-shard-00-01.jtkhl.mongodb.net:27017,cluster0-shard-00-02.jtkhl.mongodb.net:27017/myFirstDatabase?ssl=true&replicaSet=atlas-t0laxf-shard-0&authSource=admin&retryWrites=true&w=majority")
            ConnectionString("mongodb://127.0.0.1:27017/?directConnection=true&serverSelectionTimeoutMS=2000")
        val settings = MongoClientSettings.builder()
            .applyConnectionString(connectionString)
            .build()
        return MongoClients.create(settings)
    }
}