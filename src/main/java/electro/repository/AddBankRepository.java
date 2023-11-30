package electro.repository;

import electro.model.AddBank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddBankRepository extends JpaRepository<AddBank,Integer> {

}
