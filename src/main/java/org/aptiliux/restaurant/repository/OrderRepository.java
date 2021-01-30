package org.aptiliux.restaurant.repository;

import org.aptiliux.restaurant.model.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Long> {

}
