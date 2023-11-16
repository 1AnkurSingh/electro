package electro.repository;

import electro.model.Refer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Ref;

public interface ReferRepository extends JpaRepository<Refer,Integer> {


}
