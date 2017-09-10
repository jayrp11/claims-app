package net.jaydeep.claimsapp.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonView;

import net.jaydeep.claimsapp.view.View;

@Entity
public class Claim {

	@Id
	@GeneratedValue
	@JsonView(View.Summary.class)
	private long id;
	
	@Column(nullable = false)
	@JsonView(View.Summary.class)
	private String description;
	
	@Column(nullable = false)
	@JsonView(View.Summary.class)
	private double amount;

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
                "Claims[id=%d, description='%s', amount='%.2f']",
                id, description, amount);
	}
}
