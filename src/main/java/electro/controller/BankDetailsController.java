package electro.controller;

import electro.model.BankDetails;
import electro.service.BankDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/bank")
@CrossOrigin("*")
public class BankDetailsController {

    @Autowired
    BankDetailsService bankDetailsService;

    @PostMapping("/add/{userId}")
    public ResponseEntity<String> addBankDetails(@PathVariable String userId, @RequestBody BankDetails bankDetails) {
        // Set the userId in the BankDetails object
        bankDetails.setUserId(userId);

        BankDetails addedBankDetails = bankDetailsService.addBankDetails(bankDetails);
        return new ResponseEntity<>("Successfully", HttpStatus.CREATED);
    }

    @GetMapping("/getAccountById/{userId}")
    public ResponseEntity<BankDetails> getAccountById(@PathVariable String userId) {
        Optional<BankDetails> optionalBankDetails = bankDetailsService.findAccountById(userId);

        if (optionalBankDetails.isPresent()) {
            BankDetails bankDetails = optionalBankDetails.get();
            return new ResponseEntity<>(bankDetails, HttpStatus.OK);
        } else {
            // Handle the case where BankDetails is not found
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}




