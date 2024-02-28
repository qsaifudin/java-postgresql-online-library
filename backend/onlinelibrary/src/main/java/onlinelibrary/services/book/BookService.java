package onlinelibrary.services.book;

import onlinelibrary.payloads.requests.BookRequest;
import onlinelibrary.payloads.responses.Response;

public interface BookService {
    Response addBook(BookRequest request);

    Response getBooks();

    Response getBookById(String uuid);

    Response updateBookById(String uuid, BookRequest request);

    Response deleteBookById(String uuid);
}
