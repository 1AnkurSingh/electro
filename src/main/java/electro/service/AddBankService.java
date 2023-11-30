package electro.service;

import electro.model.AddBank;
import electro.model.Withdraw;
import electro.repository.AddBankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddBankService {
    @Autowired
    AddBankRepository addBankRepository;


    public AddBank addBank (AddBank addBank){
        return  addBankRepository.save(addBank);
    }

}
