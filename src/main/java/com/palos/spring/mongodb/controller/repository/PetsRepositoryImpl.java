package com.palos.spring.mongodb.controller.repository;

public class PetsRepositoryImpl implements PetsRepositoryCustom{

    private static String collectionName = "default";
    public static String c = "hello utils!";
    public PetsRepositoryImpl() {
    	System.out.println("EventDataRepositoryImp constructor called.");
    }
    
    @Override
    public String getCollectionName() {
        return collectionName;
    }

    @Override
	public void setCollectionName(String collectionName) {
        PetsRepositoryImpl.collectionName = collectionName;
    }
}