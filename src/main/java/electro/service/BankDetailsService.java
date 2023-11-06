package electro.service;

import electro.model.BankDetails;
import electro.repository.BankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BankDetailsService {

    @Autowired
    BankRepository bankRepository;

    public BankDetails addBankDetails(BankDetails bankDetails){
        return bankRepository.save(bankDetails);
    }


}
