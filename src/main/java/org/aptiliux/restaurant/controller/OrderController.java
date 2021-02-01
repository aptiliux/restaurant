package org.aptiliux.restaurant.controller;

import org.aptiliux.restaurant.configuration.jwt.UserPrincipal;
import org.aptiliux.restaurant.dto.order.create.ProductsDTO;
import org.aptiliux.restaurant.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/api/v1")
public class OrderController {

	private final OrderService orderService;

	public OrderController(OrderService orderService) {
		this.orderService = orderService;
	}

	@PostMapping("/orders")
	public ResponseEntity<Void> createOrder(@RequestBody ProductsDTO products,
			UriComponentsBuilder uriComponentBuilder) {
		UserPrincipal user = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Long orderId = orderService.createOrder(user.getUserId(), products);
		UriComponents uriComponents = uriComponentBuilder.path("/api/v1/orders/{id}").buildAndExpand(orderId);
		return ResponseEntity.created(uriComponents.toUri()).build();
	}

	@GetMapping("/orders/{id}")
	public ResponseEntity<Void> getOrder(@PathVariable("id") Long orderId) {
		return ResponseEntity.notFound().build();
	}
}
