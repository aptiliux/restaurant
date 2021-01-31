package org.aptiliux.restaurant.dto.sale.daily;

public class WaiterDTO {
	private Long id;
	private String name;
	private String lastName;
	private Long nationalIdentifier;

	public WaiterDTO() {
	}

	public WaiterDTO(Long id, String name, String lastName, Long nationalIdentifier) {
		this.id = id;
		this.name = name;
		this.lastName = lastName;
		this.nationalIdentifier = nationalIdentifier;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getLastName() {
		return lastName;
	}

	public Long getNationalIdentifier() {
		return nationalIdentifier;
	}
}
