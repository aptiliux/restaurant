package org.aptiliux.restaurant.dto.sale.daily;

public class WaiterSaleDTO {
	private WaiterDTO waiter;
	private Float total;


	public WaiterSaleDTO() {
	}

	public WaiterSaleDTO(WaiterDTO waiter, Float total) {
		this.waiter = waiter;
		this.total = total;
	}

	public WaiterDTO getWaiter() {
		return waiter;
	}

	public Float getTotal() {
		return total;
	}


}
