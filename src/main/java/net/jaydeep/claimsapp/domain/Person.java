package net.jaydeep.claimsapp.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonView;

import net.jaydeep.claimsapp.view.View;

@Entity
public class Person {
	@Id
	@GeneratedValue
	@JsonView(View.Summary.class)
	private long id;
	
	@JsonView(View.Summary.class)
	@Column(nullable = false)
	private String name;
	
	@JsonView(View.Summary.class)
	private String phone;
	
	@JsonView(View.Summary.class)
	private String email;
	
	@OneToMany
	@JoinColumn(name="person_id")
	@JsonView(View.Detail.class)
	private List<Claim> claims;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	@Override
	public String toString() {
		return String.format(
                "Person[id=%d, name='%s', phone='%s', email='%s']",
                id, name, phone, email);
	}

	public List<Claim> getClaims() {
		return claims;
	}

	public void setClaims(List<Claim> claims) {
		this.claims = claims;
	}
}
