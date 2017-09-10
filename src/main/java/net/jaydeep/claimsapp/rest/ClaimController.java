package net.jaydeep.claimsapp.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import net.jaydeep.claimsapp.domain.Claim;
import net.jaydeep.claimsapp.service.ClaimRepository;

@RestController
@RequestMapping("persons/{personId}/claims")
public class ClaimController {

	@Autowired
	private ClaimRepository claimRepository;
	
	@RequestMapping(method = RequestMethod.GET)
	public Iterable<Claim> list(@PathVariable Long personId) {
		return claimRepository.findByPersonId(personId);
	}
}
