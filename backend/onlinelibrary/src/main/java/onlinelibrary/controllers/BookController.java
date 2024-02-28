package onlinelibrary.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import onlinelibrary.models.Book;
import onlinelibrary.payloads.requests.BookRequest;
import onlinelibrary.payloads.responses.Response;
import onlinelibrary.services.book.BookService;

import java.util.List;

@RestController
@RequestMapping("/book")

public class BookController {
    @Autowired
    private BookService bookService;

    @PostMapping
    public ResponseEntity<?> addBook(@RequestBody BookRequest bookRequest) {
        try {
            Response response = bookService.addBook(bookRequest);
            return ResponseEntity.status(response.getStatus()).body(response);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> getBooks() {
        try {
            Response response = bookService.getBooks();
            return ResponseEntity.status(response.getStatus()).body(response);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public String getBook(@PathVariable("id") Long id) {
        // Logic to fetch the book with the given id from the database
        // Replace this with your actual implementation

        return "Book with id " + id + " retrieved successfully";
    }

}
