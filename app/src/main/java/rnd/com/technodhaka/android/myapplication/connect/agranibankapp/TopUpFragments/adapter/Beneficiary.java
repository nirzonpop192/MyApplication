package rnd.com.technodhaka.android.myapplication.connect.agranibankapp.TopUpFragments.adapter;

/**
 * Created by TD-Android on 12/21/2017.
 */
public class Beneficiary {
    private String user_id;
    private String tDate;
    private String utility_Acno;
    private String acName;
    private String utility_Type;
    private String curr_code;
    private String ent_Sys_date;
    private String edit_sys_date;
    private String activeyn;
    private String operator_id;
    private String phone_type;
    private String bene_status;
    private String bene_emailid;
    private String nick_name;

    public Beneficiary(String user_id, String tDate, String utility_Acno, String acName,
                       String utility_Type, String curr_code, String ent_Sys_date,
                       String edit_sys_date, String activeyn, String operator_id,
                       String phone_type, String bene_status, String bene_emailid, String nick_name) {
        this.user_id = user_id;
        this.tDate = tDate;
        this.utility_Acno = utility_Acno;
        this.acName = acName;
        this.utility_Type = utility_Type;
        this.curr_code = curr_code;
        this.ent_Sys_date = ent_Sys_date;
        this.edit_sys_date = edit_sys_date;
        this.activeyn = activeyn;
        this.operator_id = operator_id;
        this.phone_type = phone_type;
        this.bene_status = bene_status;
        this.bene_emailid = bene_emailid;
        this.nick_name = nick_name;
    }

    public Beneficiary(String user_id, String tDate, String utility_Acno, String nick_name) {
        this.user_id = user_id;
        this.tDate = tDate;
        this.utility_Acno = utility_Acno;
        this.nick_name = nick_name;
    }

    public Beneficiary() {
    }

    public String getUser_id() {
        return user_id;
    }

    public String gettDate() {
        return tDate;
    }

    public String getUtility_Acno() {
        return utility_Acno;
    }

    public String getAcName() {
        return acName;
    }

    public String getUtility_Type() {
        return utility_Type;
    }

    public String getCurr_code() {
        return curr_code;
    }

    public String getEnt_Sys_date() {
        return ent_Sys_date;
    }

    public String getEdit_sys_date() {
        return edit_sys_date;
    }

    public String getActiveyn() {
        return activeyn;
    }

    public String getOperator_id() {
        return operator_id;
    }

    public String getPhone_type() {
        return phone_type;
    }

    public String getBene_status() {
        return bene_status;
    }

    public String getBene_emailid() {
        return bene_emailid;
    }

    public String getNick_name() {
        return nick_name;
    }
}
