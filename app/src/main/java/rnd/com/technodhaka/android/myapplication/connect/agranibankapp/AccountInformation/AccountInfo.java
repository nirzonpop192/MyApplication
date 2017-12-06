package rnd.com.technodhaka.android.myapplication.connect.agranibankapp.AccountInformation;

public class AccountInfo {
    public String accountNo;
    public String accountName;
    public String balance;
    public String currency;
    public String accountType;
    //public String description;
    //public String module;
   // public String status;

    public AccountInfo(String accountNo, String balance , String acname, String currency, String accountType) {
        this.accountNo = accountNo;
        this.balance = balance;
        this.accountName = acname;
        this.currency = currency;
        this.accountType = accountType;
       // this.status = status;
       // this.description = description;
        //this.module = module;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public AccountInfo() {
    }

    public String getAccountNo() {
        return this.accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getBalance() {
        return this.balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }
    public String getCurrency() {
        return this.currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    /*    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getModule() {
        return this.module;
    }

    public void setModule(String module) {
        this.module = module;
    }*/
}
