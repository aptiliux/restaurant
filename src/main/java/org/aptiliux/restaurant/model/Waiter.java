package org.aptiliux.restaurant.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_waiter")
public class Waiter {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "last_name", nullable = false)
	private String lastName;

	@Column(name = "national_identifier", nullable = false)
	private Long nationalIdentifier;

	public Waiter() {
	}

	public Waiter(String name, String lastName, Long nationalIdentifier) {
		this.name = name;
		this.lastName = lastName;
		this.nationalIdentifier = nationalIdentifier;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Long getNationalIdentifier() {
		return nationalIdentifier;
	}

	public void setNationalIdentifier(Long nationalIdentifier) {
		this.nationalIdentifier = nationalIdentifier;
	}
}
