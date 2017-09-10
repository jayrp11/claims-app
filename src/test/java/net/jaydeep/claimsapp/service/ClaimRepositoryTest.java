package net.jaydeep.claimsapp.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import net.jaydeep.claimsapp.domain.Claim;
import net.jaydeep.claimsapp.domain.Person;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ClaimRepositoryTest {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private ClaimRepository claimRepository;
	
	@Test
	public void findByPersonIdTest() throws Exception {
		Person p = new Person();
		p.setName("Test");
		p.setPhone("0123456789");
		p.setEmail("test@test.com");
		
		Claim c = new Claim();
		c.setDescription("Desc1");
		c.setAmount(11.50d);
		c.setPerson(p);
		
		p = this.entityManager.persist(p);
		c = this.entityManager.persist(c);

		System.out.println(p);
		System.out.println(c);
		
		Iterable<Claim> claims = this.claimRepository.findByPersonId(p.getId());
		Claim c1 = claims.iterator().next();
		
		assertThat(c1.getDescription()).isEqualTo("Desc1");
		assertThat(c1.getAmount()).isEqualTo(11.50d);
	}
}
