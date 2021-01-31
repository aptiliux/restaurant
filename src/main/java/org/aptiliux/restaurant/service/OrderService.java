package org.aptiliux.restaurant.service;

import org.aptiliux.restaurant.dto.order.create.ProductsDTO;

public interface OrderService {
	Long createOrder(Long waiterId, ProductsDTO products);
}
