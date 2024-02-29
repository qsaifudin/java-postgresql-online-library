package onlinelibrary.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import onlinelibrary.models.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, String> {
    // Define a custom query to find transactions by user ID and status not equal to a specific value
    @Query("SELECT t FROM Transaction t WHERE t.userId = :userId AND t.status <> :status")
    List<Transaction> findByUserIdAndStatusNot(@Param("userId") String userId, @Param("status") String status);

}