package rnd.com.technodhaka.android.myapplication.connect.agranibankapp.AccountInformation.AccountDetails.view;

/**
 * Created by TD-Android on 12/7/2017.
 */
public class AccountDetailsDatamodel {
    private String tdate;
    private String remark;
    private String debit;
    private String credit;
    private String amtbal_tk;

    public AccountDetailsDatamodel(String mTdate, String mRemark, String mDebit, String mCredit, String mAmtbal_tk) {
        this.tdate = mTdate;
        this.remark = mRemark;
        this.debit = mDebit;
        this.credit = mCredit;
        this.amtbal_tk = mAmtbal_tk;
    }

    public String getTdate() {
        return tdate;
    }

    public String getRemark() {
        return remark;
    }

    public String getDebit() {
        return debit;
    }

    public String getCredit() {
        return credit;
    }

    public String getAmtbal_tk() {
        return amtbal_tk;
    }
}
