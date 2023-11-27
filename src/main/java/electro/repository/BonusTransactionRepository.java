package electro.repository;

import electro.model.BonusTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface BonusTransactionRepository extends JpaRepository<BonusTransaction, Long> {
    BonusTransaction findFirstByOrderByIdDesc();

    boolean existsByUserId(String userId);

    Optional<BonusTransaction> findFirstByUserIdOrderByIdDesc(String userId);

    boolean existsByUserIdAndTransactionTimeBetween(String userId, LocalDateTime start, LocalDateTime end);



}
