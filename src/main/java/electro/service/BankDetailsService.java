package electro.service;

import electro.model.BankDetails;
import electro.repository.BankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BankDetailsService {

    @Autowired
    BankRepository bankRepository;

    public BankDetails addBankDetails(BankDetails bankDetails){
        return bankRepository.save(bankDetails);
    }

    public Optional<BankDetails> findAccountById(String userId){
        int userIdAsInt = Integer.parseInt(userId);

        return bankRepository.findById(userIdAsInt);
    }


}
