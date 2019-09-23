package com.palos.spring.mongodb.controller.template;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.authentication.UserCredentials;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.mongodb.DB;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.WriteConcern;

@Configuration
public class MongoConfig extends AbstractMongoConfiguration {
	@Value("${spring.data.mongodb.uri}")
	private String  uri;
	
    @Override
    protected String getDatabaseName() {
        return "testing";
    }

    @Override
    public MongoClient mongoClient() {
    	return new MongoClient(new MongoClientURI(uri));

    }
  

    
 
}