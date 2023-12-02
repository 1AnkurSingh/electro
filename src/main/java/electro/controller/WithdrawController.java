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
    public List<Map<String, Object>> getWithdrawDataByUserId(@PathVariable Long userId,
                                                             @RequestHeader(name = "TimeZone", defaultValue = "Asia/Kolkata") String clientTimeZone) {
        List<Withdraw> withdrawData = withdrawService.getWithdrawDataByUserId(userId);

        ZoneId clientZoneId = ZoneId.of(clientTimeZone);

        List<Map<String, Object>> formattedWithdrawData = new ArrayList<>();

        for (Withdraw withdraw : withdrawData) {
            Map<String, Object> formattedWithdraw = new HashMap<>();
            formattedWithdraw.put("id", withdraw.getId());

            LocalDateTime timestampIST = withdraw.getTimestamp().atZone(clientZoneId).toLocalDateTime();
            formattedWithdraw.put("timestamp", timestampIST.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm:ss")));

            formattedWithdraw.put("amount", withdraw.getAmount());
            formattedWithdraw.put("userId", withdraw.getUserId());
            formattedWithdrawData.add(formattedWithdraw);
        }

        return formattedWithdrawData;
    }




}



