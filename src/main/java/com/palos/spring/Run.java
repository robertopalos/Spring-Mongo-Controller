package com.palos.spring;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.apache.commons.beanutils.PropertyUtils;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.palos.spring.mongodb.controller.repository.Pets;
import com.palos.spring.mongodb.controller.repository.PetsRepository;
import com.palos.spring.mongodb.controller.template.dal.PersonDAL;
import com.palos.spring.mongodb.controller.template.model.Person;




/**
 * Aug 25, 2019
 * @author Jose Roberto Palos Sandoval
 * @version 1
 * @description To start everything.
 */
@SpringBootApplication
@RestController
public class Run implements CommandLineRunner {
	 @Autowired
	  private PetsRepository repository;
	  private static final Logger LOG = LoggerFactory.getLogger("Run");
	  private final PersonDAL personDAL;
	  @Autowired
	  public Run(PersonDAL personDAL) {
	     this.personDAL = personDAL;
	  }
	    @Autowired
	    private ApplicationContext appContext;
	    

	/**
	 * @param args
	 * Use: 
	 */
	public static void main(String[] args) {
		SpringApplication.run(Run.class, args);
	}
	
	@Override
    public void run(String... args) throws Exception {
		System.setProperty("java.net.preferIPv4Stack" , "true");
		
		
	     personDAL.savePerson(new Person(
	             "Shubham", Arrays.asList("Harry potter", "Waking Up"), new Date(769372200000L)));

	}

	public void printAllBeans() {
		String[] beans = appContext.getBeanDefinitionNames();
        Arrays.sort(beans);
        for (String bean : beans) {
            System.out.println(bean);
        }
	}
	
	  @RequestMapping(value = "/", method = RequestMethod.GET)
	  public List<Pets> getAllPets() {
		  System.out.println("Fetching all records.");
	    return repository.findAll();
	  }
	  
	  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
	  public Pets getPetById(@PathVariable("d") ObjectId id) {
		  System.out.println("Fetching single record.");
	    return repository.findBy_id(id);
	  }
	 
	  @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	  public void modifyPetById(@PathVariable("id") ObjectId id, @Valid @RequestBody Pets pets) {
	    pets.set_id(id);
	    repository.save(pets);
	  }
	 
	  @RequestMapping(value = "/", method = RequestMethod.POST)
	  public Pets createPet(@Valid @RequestBody Pets pets, @RequestHeader("mongo-collection") String collectionName) {
		  System.out.println(collectionName);
		  repository.setCollectionName(collectionName);
	    pets.set_id(ObjectId.get());
	    repository.save(pets);
	    return pets;
	  }
	 
	  @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	  public void deletePet(@PathVariable ObjectId id) {
	    repository.delete(repository.findBy_id(id));
	  }
}
