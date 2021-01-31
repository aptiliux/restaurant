package org.aptiliux.restaurant.dto.order.create;

import java.util.List;

public class ProductsDTO {
	private List<ProductDTO> products;

	public ProductsDTO() {
	}

	public ProductsDTO(List<ProductDTO> products) {
		this.products = products;
	}

	public List<ProductDTO> getProducts() {
		return products;
	}

	public void setProducts(List<ProductDTO> products) {
		this.products = products;
	}
}
