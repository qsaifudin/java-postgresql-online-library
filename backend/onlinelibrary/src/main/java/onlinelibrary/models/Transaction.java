package onlinelibrary.models;

import java.util.Date;

public class Transaction {
    private Date borrowedDate;
    private Date returnDate;
    private int status;
    private int bookId;
    private int userId;

    public Transaction() {
    }

    public Transaction(int bookId, int userId, Date borrowedDate, Date returnDate, int status) {
        this.bookId = bookId;
        this.userId = userId;
        this.borrowedDate = borrowedDate;
        this.returnDate = returnDate;
        this.status = status;
    }

    // Getters and setters for all the fields

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Date getBorrowedDate() {
        return borrowedDate;
    }

    public void setBorrowedDate(Date borrowedDate) {
        this.borrowedDate = borrowedDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
