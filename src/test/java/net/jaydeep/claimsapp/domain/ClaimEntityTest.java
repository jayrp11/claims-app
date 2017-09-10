package net.jaydeep.claimsapp.domain;

import static org.junit.Assert.assertFalse;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ClaimEntityTest {

	private static Validator validator;
	
	@Before
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }
	
	@Test
	public void descriptionShouldNotBeNullTest() throws Exception {
		Claim c = new Claim();
		c.setAmount(11.50d);
		Set<ConstraintViolation<Claim>> violations = validator.validate(c);
		assertFalse(violations.isEmpty());
	}
	
	@Test
	public void amountShouldNotBeNullTest() throws Exception {
		Claim c1 = new Claim();
		c1.setDescription("Desc1");
		Set<ConstraintViolation<Claim>> violations = validator.validate(c1);
		assertFalse(violations.isEmpty());
	}
}
