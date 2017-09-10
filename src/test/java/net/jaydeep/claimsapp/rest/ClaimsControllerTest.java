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

import net.jaydeep.claimsapp.domain.Claim;
import net.jaydeep.claimsapp.service.ClaimRepository;

@RunWith(SpringRunner.class)
@WebMvcTest(ClaimController.class)
public class ClaimsControllerTest {
	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private ClaimRepository claimRepository;
	
	@Test
	public void getClaimList() throws Exception {
		List<Claim> l = new ArrayList<Claim>();
		Claim c = new Claim();
		c.setDescription("Desc1");
		c.setAmount(11.50d);
		l.add(c);
		
		given(this.claimRepository.findByPersonId(1L)).willReturn(l);
		
		this.mvc.perform(get("/persons/1/claims").accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(content().json("[{'description':'Desc1', 'amount': 11.50}]"));
	}
}
