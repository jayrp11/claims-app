package net.jaydeep.claimsapp.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Claim {

	@Id
	@GeneratedValue
	private long id;
	
	@Column(nullable = false)
	private String description;
	
	@Column(nullable = false)
	private double amount;
	
	@ManyToOne
	private Person person;

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return String.format(
                "Claims[id=%d, description='%s', amount='%.2f', person_id='%d']",
                id, description, amount, person.getId());
	}
}
