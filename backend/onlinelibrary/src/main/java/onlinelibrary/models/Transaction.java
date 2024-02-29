package onlinelibrary.models;

import java.time.LocalDateTime;

import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "transactions")
public class Transaction {
    @Id
    @GeneratedValue
    @UuidGenerator
    private String id;

    private String bookId;
    private String userId;
    private LocalDateTime borrowedDate;
    private LocalDateTime returnDate;
    private String status;

    public Transaction() {
    }

    public Transaction(String bookId, String userId, LocalDateTime borrowedDate, LocalDateTime returnDate, String status) {
        this.bookId = bookId;
        this.userId = userId;
        this.borrowedDate = borrowedDate;
        this.returnDate = returnDate;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public LocalDateTime getBorrowedDate() {
        return borrowedDate;
    }

    public void setBorrowedDate(LocalDateTime borrowedDate) {
        this.borrowedDate = borrowedDate;
    }

    public LocalDateTime getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDateTime returnDate) {
        this.returnDate = returnDate;
    }

    public String getSuccess() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
