package electro.repository;

import electro.model.BonusTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BonusTransactionRepository extends JpaRepository<BonusTransaction, Long> {
    BonusTransaction findFirstByOrderByIdDesc();
}
