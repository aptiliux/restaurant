package org.aptiliux.restaurant.service.impl;

import java.util.Date;

import org.aptiliux.restaurant.dto.order.create.ProductDTO;
import org.aptiliux.restaurant.dto.order.create.ProductsDTO;
import org.aptiliux.restaurant.model.Order;
import org.aptiliux.restaurant.model.Product;
import org.aptiliux.restaurant.model.Waiter;
import org.aptiliux.restaurant.repository.OrderRepository;
import org.aptiliux.restaurant.service.OrderService;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

	private final OrderRepository orderRepository;

	public OrderServiceImpl(OrderRepository orderRepository) {
		this.orderRepository = orderRepository;
	}

	public Long createOrder(Long waiterId, ProductsDTO products) {

		Waiter waiter = new Waiter(waiterId);
		Order order = new Order(waiter, new Date());
		for (ProductDTO productDTO : products.getProducts()) {
			order.addProduct(new Product(productDTO.getId()), productDTO.getQuantity());
		}
		orderRepository.save(order);
		return order.getId();
	}
}
