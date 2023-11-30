package electro.controller;

import electro.model.AddBank;
import electro.repository.AddBankRepository;
import electro.service.AddBankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/bank")

public class AddBankController {
    @Autowired
    AddBankService addBankService;

    @PostMapping("/add")
    public AddBank addBank(@RequestBody AddBank addBank){
        return addBankService.addBank(addBank);
    }

}
