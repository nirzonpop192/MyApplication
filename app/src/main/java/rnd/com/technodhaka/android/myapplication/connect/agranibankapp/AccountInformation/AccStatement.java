package rnd.com.technodhaka.android.myapplication.connect.agranibankapp.AccountInformation;

public class AccStatement {
    public String balance;
    public String deposit;
    public String particular;
    public String transactionDate;
    public String withdrawal;

    public AccStatement() {
    }

    public AccStatement(String transactionDate, String particular, String deposit, String balance, String withdrawal) {
        this.transactionDate = transactionDate;
        this.balance = balance;
        this.particular = particular;
        this.deposit = deposit;
        this.withdrawal = withdrawal;
    }

    public String getTransactiondate() {
        return this.transactionDate;
    }

    public void setTransactiondate(String transactiondate) {
        this.transactionDate = transactiondate;
    }

    public String getParticular() {
        return this.particular;
    }

    public void setParticular(String particular) {
        this.particular = particular;
    }

    public String getDeposit() {
        return this.deposit;
    }

    public void setDeposit(String deposit) {
        this.deposit = deposit;
    }

    public String getBalance() {
        return this.balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getWithdrawal() {
        return this.withdrawal;
    }

    public void setWithdrawal(String withdrawal) {
        this.withdrawal = withdrawal;
    }
}
