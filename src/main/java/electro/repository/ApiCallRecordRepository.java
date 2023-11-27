package electro.repository;

import electro.model.ApiCallRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface ApiCallRecordRepository extends JpaRepository<ApiCallRecord, Long> {
    boolean existsByBonusClaimTimeBetween(LocalDateTime start, LocalDateTime end);

}
