package net.jaydeep.claimsapp.rest;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import net.jaydeep.claimsapp.domain.Person;
import net.jaydeep.claimsapp.service.PersonRepository;

@RunWith(SpringRunner.class)
@WebMvcTest(PersonController.class)
public class PersonControllerTests {
	
	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private PersonRepository personRepository;

	@Test
	public void getPersonList() throws Exception {
		List<Person> l = new ArrayList<Person>();
		Person p = new Person();
		p.setName("Test");
		p.setPhone("0123456789");
		p.setEmail("test@test.com");
		l.add(p);
		
		given(this.personRepository.findAll()).willReturn(l);
		
		this.mvc.perform(get("/persons").accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(content().json("[{'name':'Test', 'phone': '0123456789', 'email': 'test@test.com'}]"));
	}
	
	@Test
	public void getPerson() throws Exception {
		Person p = new Person();
		p.setName("Test");
		p.setPhone("0123456789");
		p.setEmail("test@test.com");
		
		given(this.personRepository.findOne(1L)).willReturn(p);
		
		this.mvc.perform(get("/persons/1").accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(content().json("{'name':'Test', 'phone': '0123456789', 'email': 'test@test.com'}"));
	}
}
