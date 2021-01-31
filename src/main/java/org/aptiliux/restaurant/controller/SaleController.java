package org.aptiliux.restaurant.controller;

import org.aptiliux.restaurant.dto.sale.daily.DailySaleDTO;
import org.aptiliux.restaurant.service.SaleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class SaleController {

	private final SaleService saleService;

	public SaleController(SaleService saleService) {
		this.saleService = saleService;
	}

	@GetMapping("/sales/daily")
	public ResponseEntity<DailySaleDTO> getDailySales() {
		return ResponseEntity.ok(saleService.getDailySale());
	}
}
