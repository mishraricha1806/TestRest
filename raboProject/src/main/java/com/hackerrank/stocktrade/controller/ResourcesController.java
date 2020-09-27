package com.hackerrank.stocktrade.controller;

import com.hackerrank.stocktrade.repository.TradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/erase")
public class ResourcesController {

	@Autowired
	private TradeRepository tradeRepository;

	@DeleteMapping
	public void deleteAllTrade() {
		tradeRepository.deleteAll();
	}

}
