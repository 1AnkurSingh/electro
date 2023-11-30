package electro.service;

import electro.model.BankDetails;
import electro.model.Withdraw;
import electro.repository.BankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BankDetailsService {
    private final BankRepository bankDetailsRepository;

    @Autowired
    public BankDetailsService(BankRepository bankDetailsRepository) {
        this.bankDetailsRepository = bankDetailsRepository;
    }

    public BankDetails addBankDetails(BankDetails bankDetails) {
        return bankDetailsRepository.save(bankDetails);
    }


}

