package com.palos.spring.mongodb.controller.repository;

import org.springframework.stereotype.Repository;

@Repository
public interface PetsRepositoryCustom  {
    String getCollectionName();
    void setCollectionName(String collectionName);
}