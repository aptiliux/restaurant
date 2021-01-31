package org.aptiliux.restaurant.repository.result;

import org.aptiliux.restaurant.model.Waiter;

public interface DailyWaiterSale {
	Waiter getWaiter();
	Float getTotal();
}
