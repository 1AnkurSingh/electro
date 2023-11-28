package electro.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Builder
@Entity
@Data
@AllArgsConstructor
public class BankDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int accountId;

    private String mobileNumber;
    private String holderName;

    private String bankAccountNumber;

    private String bankIFSC;
    private String userId;

    public void setUserId(String userId) {
        this.userId = userId;
    }




    public String getBankIFSC(){
        return bankIFSC;
    }

    public void setBankIFSC(String bankIFSC){
        this.bankIFSC=bankIFSC.toUpperCase();
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getHolderName() {
        return holderName;
    }

    public void setHolderName(String holderName) {
        this.holderName = holderName;
    }

    public String getBankAccountNumber() {
        return bankAccountNumber;
    }

    public void setBankAccountNumber(String bankAccountNumber) {
        this.bankAccountNumber = bankAccountNumber;
    }

    public BankDetails() {
    }

    public BankDetails(String mobileNumber, String holderName, String bankAccountNumber, String bankIFSC) {
        this.mobileNumber = mobileNumber;
        this.holderName = holderName;
        this.bankAccountNumber = bankAccountNumber;
        this.bankIFSC = bankIFSC;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public BankDetails(int accountId, String mobileNumber, String holderName, String bankAccountNumber, String bankIFSC) {
        this.accountId = accountId;
        this.mobileNumber = mobileNumber;
        this.holderName = holderName;
        this.bankAccountNumber = bankAccountNumber;
        this.bankIFSC = bankIFSC;
    }

}
