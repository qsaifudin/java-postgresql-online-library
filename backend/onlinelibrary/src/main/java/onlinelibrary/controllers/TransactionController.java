package onlinelibrary.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import onlinelibrary.payloads.requests.TransactionRequest;
import onlinelibrary.payloads.responses.Response;
import onlinelibrary.services.transaction.TransactionService;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping
    public ResponseEntity<?> addBorrowedTransaction(@RequestBody TransactionRequest transactionRequest) {
        try {
            Response response = transactionService.addBorrowedTransaction(transactionRequest);
            return ResponseEntity.status(response.getStatus()).body(response);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> getTransactions() {
        try {
            Response response = transactionService.getTransaction();
            return ResponseEntity.status(response.getStatus()).body(response);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @GetMapping("/borrowed/{userId}")
    public ResponseEntity<?> getBorrowedTransactionsByUserId(@PathVariable("userId") String userId) {
        try {
            Response response = transactionService.getBorrowedTransactionsByUserId(userId);
            return ResponseEntity.status(response.getStatus()).body(response);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @PostMapping("/return_book/{transactionId}")
    public ResponseEntity<?> returnBookTransaction(@PathVariable String transactionId) {
        try {
            Response response = transactionService.returnBookTransaction(transactionId);
            return ResponseEntity.status(response.getStatus()).body(response);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
}
