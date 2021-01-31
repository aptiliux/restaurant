package org.aptiliux.restaurant.dto.order.create;

public class ProductDTO {
	private Long id;
	private Integer quantity;

	public ProductDTO() {
	}

	public ProductDTO(Long id, Integer quantity) {
		this.id = id;
		this.quantity = quantity;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
}
