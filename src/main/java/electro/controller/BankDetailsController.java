package electro.controller;

import electro.model.BankDetails;
import electro.service.BankDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bank")
@CrossOrigin("*")
public class BankDetailsController {

    @Autowired
    BankDetailsService  bankDetailsService;
    @PostMapping("/add")
    public ResponseEntity<String>addBankDetails(@RequestBody BankDetails bankDetails){
        BankDetails addedBankDetails = bankDetailsService.addBankDetails(bankDetails);
        return new ResponseEntity<>("Successfully", HttpStatus.CREATED);
    }



}
