package org.aptiliux.restaurant.controller;

import org.aptiliux.restaurant.configuration.jwt.UserPrincipal;
import org.aptiliux.restaurant.dto.order.create.ProductsDTO;
import org.aptiliux.restaurant.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ResponseHeader;

@RestController
@RequestMapping("/api/v1")
public class OrderController {

	private final OrderService orderService;

	public OrderController(OrderService orderService) {
		this.orderService = orderService;
	}

	@ApiOperation(value = "Create order for a logged-in waiter")
	@ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, allowEmptyValue = false, paramType = "header", dataTypeClass = String.class, 
	example = "Bearer access_token")
	@ApiResponse(code=201, message="Created", responseHeaders = {@ResponseHeader(name = "Location", description = "Url of resource created")})
	@ResponseStatus(value = HttpStatus.CREATED)
	@PostMapping("/orders")
	public ResponseEntity<Void> createOrder(@RequestBody ProductsDTO products,
			UriComponentsBuilder uriComponentBuilder) {
		UserPrincipal user = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Long orderId = orderService.createOrder(user.getUserId(), products);
		UriComponents uriComponents = uriComponentBuilder.path("/api/v1/orders/{id}").buildAndExpand(orderId);
		return ResponseEntity.created(uriComponents.toUri()).build();
	}

	@ApiOperation(value = "Returns an order")
	@ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, allowEmptyValue = false, paramType = "header", dataTypeClass = String.class, 
	example = "Bearer access_token")
	@ApiResponse(code=404, message="Not Found")
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	@GetMapping("/orders/{id}")
	public ResponseEntity<Void> getOrder(@PathVariable("id") Long orderId) {
		return ResponseEntity.notFound().build();
	}
}
