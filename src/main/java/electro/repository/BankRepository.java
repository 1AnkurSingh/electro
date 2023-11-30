package electro.repository;

import electro.model.BankDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankRepository extends JpaRepository<BankDetails,Integer> {
//    boolean existsByUserId(String userId);
//
//    BankDetails findByUserId(String userId);
}
