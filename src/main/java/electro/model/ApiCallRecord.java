package electro.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDateTime;
@Data
@Entity
public class ApiCallRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Add userId field
    private String userId;

    private boolean bonusClaimed; // New field to indicate bonus claimed
    private LocalDateTime bonusClaimTime; // New field to store bonus claim timestamp
    private int bonus;

    public void setUserId(String userId) {
        this.userId = userId;

    }


    // Getter and setter methods
}