package net.jaydeep.claimsapp.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import net.jaydeep.claimsapp.domain.Person;
import net.jaydeep.claimsapp.service.PersonRepository;

@RestController
@RequestMapping("persons")
public class PersonController {
	
	@Autowired
	private PersonRepository personRepository;
	
	@RequestMapping(method = RequestMethod.GET)
	public Iterable<Person> list() {
		return personRepository.findAll();
	}
	
	@RequestMapping(path="/{personId}", method = RequestMethod.GET)
	public Person getPerson(@PathVariable Long personId) {
		return personRepository.findOne(personId);
	}
}
