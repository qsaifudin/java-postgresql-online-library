package onlinelibrary.models;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UuidGenerator;
// import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

// @Document("book")
@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue
    @UuidGenerator
    private String id;

    private String title;
    private String description;
    private String cover;

    @CreationTimestamp
    private LocalDateTime createdAt;

    public Book() {
    }

    // Constructor
    public Book(String id, String title, String description, String cover) {
        this.title = title;
        this.description = description;
        this.cover = cover;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
