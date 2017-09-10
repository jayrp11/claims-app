package net.jaydeep.claimsapp.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertFalse;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class PersonEntityTest {
	@Autowired
	private TestEntityManager entityManager;
	
	private static Validator validator;
	
	@Before
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }
	
	@Test
	public void nameShouldNotBeNullTest() throws Exception {
		Person p = new Person();
		p.setPhone("012345678");
		p.setEmail("test@test.com");
		Set<ConstraintViolation<Person>> violations = validator.validate(p);
		assertFalse(violations.isEmpty());
	}
	
	@Test
	public void persistsTest() throws Exception {
		Person p = new Person();
		p.setName("Test1");
		p.setPhone("012345678");
		p.setEmail("test@test.com");
		
		Person person = this.entityManager.persistFlushFind(p);
		assertThat(person.getName()).isEqualTo("Test1");
		assertThat(person.getPhone()).isEqualTo("012345678");
		assertThat(person.getEmail()).isEqualTo("test@test.com");
	}
}
