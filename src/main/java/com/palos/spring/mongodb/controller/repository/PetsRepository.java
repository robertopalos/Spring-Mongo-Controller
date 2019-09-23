package com.palos.spring.mongodb.controller.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PetsRepository extends MongoRepository<Pets, String>, PetsRepositoryCustom{
  Pets findBy_id(ObjectId _id);
}
