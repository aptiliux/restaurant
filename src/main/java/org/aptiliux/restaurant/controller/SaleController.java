package org.aptiliux.restaurant.controller;

import org.aptiliux.restaurant.configuration.jms.Sender;
import org.aptiliux.restaurant.dto.sale.daily.DailySaleDTO;
import org.aptiliux.restaurant.service.SaleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/api/v1")
public class SaleController {

	private final SaleService saleService;

	private final Sender sender;

	public SaleController(SaleService saleService, Sender sender) {
		this.saleService = saleService;
		this.sender = sender;
	}

	@PostMapping("/sales/daily")
	public ResponseEntity<?> getDailySales(UriComponentsBuilder uriComponentBuilder) {

		String workUUID = sender.sendMessage();
		UriComponents uriComponents = uriComponentBuilder.path("/api/v1/sales/daily/status/{workUUID}")
				.buildAndExpand(workUUID);
		return ResponseEntity.accepted().location(uriComponents.toUri()).header("Retry-After", "5000").build();
	}

	@GetMapping("/sales/daily/status/{workUUID}")
	public ResponseEntity<?> getDailySalesStatusWorker(@PathVariable("workUUID") String workUUID,
			UriComponentsBuilder uriComponentBuilder) {
		UriComponents uriComponents = uriComponentBuilder.path("/api/v1/sales/daily/work/{workUUID}")
				.buildAndExpand(workUUID);
		return ResponseEntity.status(HttpStatus.FOUND).location(uriComponents.toUri()).build();
	}

	@GetMapping("/sales/daily/work/{workUUID}")
	public ResponseEntity<DailySaleDTO> getDailySalesFromWorker(@PathVariable("workUUID") String workUUID) {
		return ResponseEntity.ok(saleService.getDailySale());
	}
}
