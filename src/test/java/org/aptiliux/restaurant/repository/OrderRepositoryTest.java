package org.aptiliux.restaurant.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.Date;
import org.aptiliux.restaurant.model.Order;
import org.aptiliux.restaurant.model.Product;
import org.aptiliux.restaurant.model.Waiter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class OrderRepositoryTest {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private WaiterRepository waiterRepository;

	@Autowired
	private ProductRepository productRepository;

	@Test
	void whenSavingOneOrder_thenOrderIsOk() {
		Waiter waiter1 = waiterRepository.findById(1L).get();
		Product product1 = productRepository.findById(1L).get();
		Product product2 = productRepository.findById(2L).get();

		Order order1 = new Order(waiter1, new Date());
		order1.addProduct(product1, 3);
		order1.addProduct(product2, 5);
		orderRepository.save(order1);

		Order foundOrder = orderRepository.findById(order1.getId()).get();
		assertThat(foundOrder.getWaiter()).isEqualTo(waiter1);
		assertThat(foundOrder.getProducts().size()).isEqualTo(2);
		assertThat(foundOrder.getProducts().stream().map(orderProduct -> orderProduct.getProduct()))
				.containsAll(Arrays.asList(product1, product2));
	}

	@Test
	void whenSavingTwiceSameProduct_thenOrderIsOk() {
		Waiter waiter1 = waiterRepository.findById(1L).get();
		Product product1 = productRepository.findById(1L).get();
		Product product2 = productRepository.findById(2L).get();

		Order order1 = new Order(waiter1, new Date());
		order1.addProduct(product1, 3);
		order1.addProduct(product2, 5);
		order1.addProduct(product1, 2);
		orderRepository.save(order1);

		Order foundOrder = orderRepository.findById(order1.getId()).get();
		assertThat(foundOrder.getWaiter()).isEqualTo(waiter1);
		assertThat(foundOrder.getProducts().size()).isEqualTo(3);
		assertThat(foundOrder.getProducts().stream().map(orderProduct -> orderProduct.getProduct()))
				.containsAll(Arrays.asList(product1, product2));
		assertThat(foundOrder.getProducts().stream().map(orderProduct -> orderProduct.getQuantity()))
				.containsAll(Arrays.asList(3, 5, 2));
	}

	@Test
	void whenSavingTwoOrdersSameWaiter_thenOrdersAreOk() {
		Waiter waiter1 = waiterRepository.findById(1L).get();
		Product product1 = productRepository.findById(1L).get();
		Product product2 = productRepository.findById(2L).get();

		Order order1 = new Order(waiter1, new Date());
		order1.addProduct(product1, 3);
		order1.addProduct(product2, 5);
		orderRepository.save(order1);

		Order order2 = new Order(waiter1, new Date());
		order2.addProduct(product1, 1);
		orderRepository.save(order2);

		Order foundOrder1 = orderRepository.findById(order1.getId()).get();
		assertThat(foundOrder1.getWaiter()).isEqualTo(waiter1);
		assertThat(foundOrder1.getProducts().size()).isEqualTo(2);
		assertThat(foundOrder1.getProducts().stream().map(orderProduct -> orderProduct.getProduct()))
				.containsAll(Arrays.asList(product1, product2));

		Order foundOrder2 = orderRepository.findById(order2.getId()).get();
		assertThat(foundOrder2.getWaiter()).isEqualTo(waiter1);
		assertThat(foundOrder2.getProducts().size()).isEqualTo(1);
		assertThat(foundOrder2.getProducts().stream().map(orderProduct -> orderProduct.getProduct()))
				.containsAll(Arrays.asList(product1));
	}
}
