package net.jaydeep.claimsapp.service;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import net.jaydeep.claimsapp.domain.Person;

@Repository
public interface PersonRepository extends PagingAndSortingRepository<Person, Long> {

}
