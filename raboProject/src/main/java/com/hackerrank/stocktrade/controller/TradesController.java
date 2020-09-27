package com.hackerrank.stocktrade.controller;

import com.hackerrank.stocktrade.model.Trade;
import com.hackerrank.stocktrade.model.User;
import com.hackerrank.stocktrade.repository.TradeRepository;
import com.hackerrank.stocktrade.repository.UserRepository;
import com.hackerrank.stocktrade.repository.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/trades")
public class TradesController {

	@Autowired
	private TradeRepository tradeRepository;

	@Autowired
	private UserRepository userRepository;

	@PostMapping("/trades")
	public Trade createEmployee(@Valid @RequestBody Trade trade) {
		return tradeRepository.save(trade);
	}

	@GetMapping
	public ResponseEntity<List<Trade>> getAllTrade()
			throws ResourceNotFoundException {
		List<Trade> trade = tradeRepository.findAll();
		if (trade.size() == 0)
			throw new ResourceNotFoundException("trade not found ");
		return ResponseEntity.ok().body(trade);

	}

	@GetMapping("/trades/{id}")
	public ResponseEntity<Trade> getTradeId(@PathVariable(value = "id") Long id)
			throws ResourceNotFoundException {
		Trade trade = tradeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("trade not found for this id :: " + id));
		return ResponseEntity.ok().body(trade);
	}

	@GetMapping("/trades/stocks/{symbol}")
	public ResponseEntity<List<Trade>> getTradeWithSymbol(@PathVariable(value = "symbol") String symbol)
			throws ResourceNotFoundException {
		List<Trade> trade = tradeRepository.findBySymbol(symbol);
		if (trade.size() == 0)
			throw new ResourceNotFoundException("trade not found for this symbol :: " + symbol);
		return ResponseEntity.ok().body(trade);
	}

	@GetMapping("/trades/users/{user}")
	public ResponseEntity<List<Trade>> getTradeWithUser(@PathVariable(value = "user") Long user)
			throws ResourceNotFoundException {
		User userentity = userRepository.findById(user)
				.orElseThrow(() -> new ResourceNotFoundException("user not found for this id :: " + user));
		List<Trade> trade = tradeRepository.findByUser(userentity);
		if (trade.size() == 0)
			throw new ResourceNotFoundException("trade not found for this user :: " + user);
		return ResponseEntity.ok().body(trade);
	}

	@GetMapping("/trades/users/{user}/stocks/{symbol}")
	public ResponseEntity<List<Trade>> getTradeWithUserAndSymbol(@PathVariable(value = "user") Long user, @PathVariable(value = "symbol") String symbol)
			throws ResourceNotFoundException {
		User userentity = userRepository.findById(user)
				.orElseThrow(() -> new ResourceNotFoundException("user not found for this id :: " + user));
		List<Trade> trade = tradeRepository.findByUserAndSymbol(userentity, symbol);
		if (trade.size() == 0)
			throw new ResourceNotFoundException("trade not found for this user and symbol combination :: " + user + " Symbol :: " + symbol);
		return ResponseEntity.ok().body(trade);
	}

}
