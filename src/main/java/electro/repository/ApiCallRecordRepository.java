package electro.repository;

import electro.model.ApiCallRecord;
import electro.model.BonusTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface ApiCallRecordRepository extends JpaRepository<ApiCallRecord, Long> {
    boolean existsByBonusClaimTimeBetween(LocalDateTime start, LocalDateTime end);
//    Optional<BonusTransaction> findFirstByUserIdOrderByIdDesc(String userId);

    Optional<ApiCallRecord> findFirstByUserIdOrderByIdDesc(String userId);


}
