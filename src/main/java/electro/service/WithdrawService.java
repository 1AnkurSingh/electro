package electro.service;
import electro.model.Withdraw;
import electro.repository.WithdrawRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
public class WithdrawService {

    @Autowired
    private WithdrawRepository withdrawRepository;


    public Withdraw withdrawById(Withdraw withdraw) {
        return withdrawRepository.save(withdraw);
    }
}



