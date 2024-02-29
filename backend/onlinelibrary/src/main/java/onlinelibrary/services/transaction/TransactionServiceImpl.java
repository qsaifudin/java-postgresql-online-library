package onlinelibrary.services.transaction;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import onlinelibrary.models.Book;
import onlinelibrary.models.Transaction;
import onlinelibrary.models.User;
import onlinelibrary.payloads.requests.TransactionRequest;
import onlinelibrary.payloads.responses.Response;
import onlinelibrary.repositories.BookRepository;
import onlinelibrary.repositories.TransactionRepository;
import onlinelibrary.repositories.UserRepository;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public Response addBorrowedTransaction(TransactionRequest request) {
        try {
            // Check if the user has any existing borrowed transactions
            List<Transaction> existingTransactions = transactionRepository.findByUserIdAndStatusNot(request.getUserId(),
                    "returned");
            if (!existingTransactions.isEmpty()) {
                return new Response(HttpStatus.OK.value(), "User already has an active borrowed transaction",
                        null,false);
            }

            // Create a new Transaction object
            Transaction transaction = new Transaction();
            transaction.setBookId(request.getBookId());
            transaction.setUserId(request.getUserId());
            transaction.setBorrowedDate(LocalDateTime.now());
            transaction.setReturnDate(transaction.getBorrowedDate().plusWeeks(1));
            transaction.setStatus("borrowed"); // Set default status to pending

            // Save the transaction to the database
            transaction = transactionRepository.save(transaction);

            // Create a success response
            Response response = new Response();
            response.setStatus(HttpStatus.CREATED.value());
            response.setMessage("Transaction added successfully");
            response.setData(transaction);
            response.setSuccess(true);

            return response;
        } catch (Exception e) {
            // If an error occurs, return an error response
            return new Response(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Failed to add transaction " + e, null,false);
        }
    }

    @Override
    public Response getTransaction() {
        try {
            // Retrieve all transactions from the database
            Iterable<Transaction> transactions = transactionRepository.findAll(Sort.by(Sort.Direction.DESC, "borrowedDate"));

            // Create a list to store the enhanced transactions
            List<Map<String, Object>> enhancedTransactions = new ArrayList<>();

            // Iterate through each transaction and fetch related book and user data
            for (Transaction transaction : transactions) {
                // Fetch the book data based on the bookId
                Optional<Book> optionalBook = bookRepository.findById(transaction.getBookId());
                Book book = optionalBook.orElse(null);

                // Fetch the user data based on the userId
                Optional<User> optionalUser = userRepository.findById(transaction.getUserId());
                User user = optionalUser.orElse(null);

                // Check if the return date is before the current date
                LocalDateTime currentDate = LocalDateTime.now();
                if ("borrowed".equals(transaction.getSuccess()) && currentDate.isAfter(transaction.getReturnDate())) {
                    // Update the transaction status to "expired"
                    transaction.setStatus("expired");
                    // Save the updated transaction to the database
                    transactionRepository.save(transaction);
                }

                // Create a map to store the enhanced transaction data
                Map<String, Object> enhancedTransaction = new HashMap<>();
                enhancedTransaction.put("id", transaction.getId());
                enhancedTransaction.put("book", book); // Include book data
                enhancedTransaction.put("user", user); // Include user data
                enhancedTransaction.put("borrowedDate", transaction.getBorrowedDate());
                enhancedTransaction.put("returnDate", transaction.getReturnDate());
                enhancedTransaction.put("status", transaction.getSuccess());

                // Add the enhanced transaction to the list
                enhancedTransactions.add(enhancedTransaction);
            }

            // Create a success response with the list of enhanced transactions
            Response response = new Response();
            response.setStatus(HttpStatus.OK.value());
            response.setMessage("Transactions retrieved successfully");
            response.setData(enhancedTransactions);

            return response;
        } catch (Exception e) {
            // If an error occurs, return an error response
            return new Response(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Failed to get transactions " + e, null,false);
        }
    }

    @Override
    public Response getBorrowedTransactionsByUserId(String userId) {
        try {
            // Retrieve transactions by user ID where status is not equal to "returned"
            List<Transaction> transactions = transactionRepository.findByUserIdAndStatusNot(userId, "returned");

            // Create a list to store the enhanced transactions
            List<Map<String, Object>> enhancedTransactions = new ArrayList<>();

            // Iterate through each transaction and fetch related book data
            for (Transaction transaction : transactions) {
                // Fetch the book data based on the bookId
                Optional<Book> optionalBook = bookRepository.findById(transaction.getBookId());
                Book book = optionalBook.orElse(null);

                // Create a map to store the enhanced transaction data
                Map<String, Object> enhancedTransaction = new HashMap<>();
                enhancedTransaction.put("transaction", transaction);
                enhancedTransaction.put("book", book);

                // Add the enhanced transaction to the list
                enhancedTransactions.add(enhancedTransaction);
            }

            // Create a success response with the list of enhanced transactions
            Response response = new Response();
            response.setStatus(HttpStatus.OK.value());
            response.setMessage("Borrowed transactions retrieved successfully");
            response.setData(enhancedTransactions);
            response.setSuccess(true);

            return response;
        } catch (Exception e) {
            // If an error occurs, return an error response
            return new Response(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Failed to get borrowed transactions", null, false);
        }
    }

    @Override
    public Response returnBookTransaction(String transactionId) {
        try {
            // Retrieve the transaction by its ID from the database
            Optional<Transaction> optionalTransaction = transactionRepository.findById(transactionId);
            Transaction transaction = optionalTransaction.orElse(null);

            if (transaction == null) {
                // If the transaction does not exist, return a not found response
                return new Response(HttpStatus.NOT_FOUND.value(), "Transaction not found", null,false);
            }

            // Update the status of the transaction to "returned"
            transaction.setStatus("returned");

            // Save the updated transaction to the database
            transactionRepository.save(transaction);

            // Create a success response
            Response response = new Response();
            response.setStatus(HttpStatus.OK.value());
            response.setMessage("Transaction updated successfully");
            response.setSuccess(true);
            response.setData(transaction);

            return response;
        } catch (Exception e) {
            // If an error occurs, return an error response
            return new Response(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    "Failed to update transaction: " + e.getMessage(), null,false);
        }
    }
}
