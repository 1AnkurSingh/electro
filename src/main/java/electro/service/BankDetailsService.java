package electro.service;

import electro.model.BankDetails;
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

    public boolean doesUserExist(String userId) {
        return bankDetailsRepository.existsByUserId(userId);
    }

    public BankDetails addBankDetails(BankDetails bankDetails) {
        // Your logic to add bank details to the database
        return bankDetailsRepository.save(bankDetails);
    }

    public BankDetails findBankDetailsByUserId(String userId){

        return bankDetailsRepository.findByUserId(userId);
    }
}

