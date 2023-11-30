package electro.controller;

import electro.model.AddBank;
import electro.model.Withdraw;
import electro.service.AddBankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/bank")

public class AddBankController {
    @Autowired
    AddBankService addBankService;


    @PostMapping("/addBankDetails/{userId}")
    public AddBank addBankDetails(@PathVariable String userId, @RequestBody AddBank addBank) {
        addBank.setUserId(userId);
        return addBankService.addBank(addBank);

    }
}
