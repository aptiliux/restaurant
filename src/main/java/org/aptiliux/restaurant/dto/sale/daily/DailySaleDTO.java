package org.aptiliux.restaurant.dto.sale.daily;

import java.util.Date;
import java.util.List;

public class DailySaleDTO {
	private List<WaiterSaleDTO> waiterSales;
	private Float total;
	private Date date;

	public DailySaleDTO() {
	}

	public DailySaleDTO(List<WaiterSaleDTO> waiterSales, Float total, Date date) {
		this.waiterSales = waiterSales;
		this.total = total;
		this.date = date;
	}

	public List<WaiterSaleDTO> getWaiterSales() {
		return waiterSales;
	}

	public Float getTotal() {
		return total;
	}

	public Date getDate() {
		return date;
	}
}
