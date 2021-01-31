package org.aptiliux.restaurant.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.aptiliux.restaurant.dto.sale.daily.DailySaleDTO;
import org.aptiliux.restaurant.dto.sale.daily.WaiterDTO;
import org.aptiliux.restaurant.dto.sale.daily.WaiterSaleDTO;
import org.aptiliux.restaurant.repository.OrderRepository;
import org.aptiliux.restaurant.repository.result.DailyWaiterSale;
import org.aptiliux.restaurant.service.SaleService;
import org.springframework.stereotype.Service;

@Service
public class SaleServiceImpl implements SaleService {

	private OrderRepository orderRepository;

	public SaleServiceImpl(OrderRepository orderRepository) {
		this.orderRepository = orderRepository;
	}

	public DailySaleDTO getDailySale() {
		Date today = new Date();
		Optional<List<DailyWaiterSale>> optionalWaiterDailySale = orderRepository.findWaiterDailySale(today);
		List<DailyWaiterSale> waiterDailySale = optionalWaiterDailySale.orElse(new ArrayList<DailyWaiterSale>());
		List<WaiterSaleDTO> waiterSaleDTO = waiterDailySale.stream()
				.map(wds -> new WaiterSaleDTO(new WaiterDTO(wds.getWaiter().getId(), wds.getWaiter().getName(),
						wds.getWaiter().getLastName(), wds.getWaiter().getNationalIdentifier()), wds.getTotal()))
				.collect(Collectors.toList());
		Float totalDailySale = (float) waiterSaleDTO.stream().mapToDouble(ws -> ws.getTotal()).sum();
		return new DailySaleDTO(waiterSaleDTO, totalDailySale, today);
	}
}
