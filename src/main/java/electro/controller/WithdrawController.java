package electro.controller;

import electro.model.Withdraw;
import electro.repository.WithdrawRepository;
import electro.service.WithdrawService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin("*")

public class WithdrawController {

        @Autowired
        WithdrawService withdrawService;

    @PostMapping("/withdrawById/{userId}")
    public String withdrawById(@PathVariable Long userId, @RequestBody Withdraw withdraw) {
        withdraw.setUserId(userId);  // Set userId from the path variable
        withdrawService.withdrawById(withdraw);
        return "Withdrawal Done!!";
    }
    @GetMapping("/getwithdrawdataByuserId/{userId}")
    public List<Map<String, Object>> getWithdrawDataByUserId(@PathVariable Long userId) {
        List<Withdraw> withdrawData = withdrawService.getWithdrawDataByUserId(userId);

        // Specify the time zone for India (IST)
        ZoneId indiaTimeZone = ZoneId.of("Asia/Kolkata");

        // Create a new list to hold the formatted data
        List<Map<String, Object>> formattedWithdrawData = new ArrayList<>();

        for (Withdraw withdraw : withdrawData) {
            Map<String, Object> formattedWithdraw = new HashMap<>();
            formattedWithdraw.put("id", withdraw.getId());

            // Convert the LocalDateTime to IST
            LocalDateTime timestampIST = withdraw.getTimestamp().atZone(indiaTimeZone).toLocalDateTime();
            formattedWithdraw.put("timestamp", timestampIST.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm:ss")));

            formattedWithdraw.put("amount", withdraw.getAmount());
            formattedWithdraw.put("userId", withdraw.getUserId());
            formattedWithdrawData.add(formattedWithdraw);
        }

        return formattedWithdrawData;
    }



}



