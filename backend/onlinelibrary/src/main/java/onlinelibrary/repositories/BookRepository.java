package onlinelibrary.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.mongodb.repository.MongoRepository;

import onlinelibrary.models.Book;
import onlinelibrary.models.User;

// public interface BookRepository extends MongoRepository<Book, String>{
    
// }

public interface BookRepository extends JpaRepository<Book,String> {

}