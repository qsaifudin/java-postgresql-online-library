package onlinelibrary.services.transaction;

import onlinelibrary.payloads.requests.TransactionRequest;
import onlinelibrary.payloads.responses.Response;

public interface TransactionService {
    Response addBorrowedTransaction(TransactionRequest request);

    Response returnBookTransaction(String transactionId);
    Response getBorrowedTransactionsByUserId(String userId);

    Response getTransaction();
}
