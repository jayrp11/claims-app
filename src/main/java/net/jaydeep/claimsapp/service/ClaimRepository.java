package net.jaydeep.claimsapp.service;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import net.jaydeep.claimsapp.domain.Claim;

@Repository
public interface ClaimRepository extends PagingAndSortingRepository<Claim, Long> {
	Iterable<Claim> findByPersonId(Long id);
}
