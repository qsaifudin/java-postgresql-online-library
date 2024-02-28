package onlinelibrary.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import onlinelibrary.models.Transaction;



@RestController
public class TransactionController {

	@GetMapping("/transactions")
	public List<Transaction> index() {
		return List.of(
			new Transaction(1, 2, null, null, 1),
			new Transaction(2, 3, null, null, 1)
		);
	}

}