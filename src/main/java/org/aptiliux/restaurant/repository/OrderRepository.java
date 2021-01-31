package org.aptiliux.restaurant.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.aptiliux.restaurant.model.Order;
import org.aptiliux.restaurant.repository.result.DailyWaiterSale;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface OrderRepository extends CrudRepository<Order, Long> {
	
	@Query("select o.waiter as waiter, SUM(p.quantity * p.product.price) as total "
			+ "from Order o JOIN o.products p "
			+ "where trunc(o.date) = trunc(:creationDate) "
			+ "group by o.waiter")
	Optional<List<DailyWaiterSale>> findWaiterDailySale(@Param("creationDate") Date date);
}