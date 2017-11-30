package rnd.com.technodhaka.android.myapplication.connect.Utility;

public class GeneralFundTransferInfo {
    public static String ammount = "";
    public static String fromAccount = "";
    public static String otpType = "";
    public static String remark = "";
    public static String sendMedium = "";
    public static String sendTo = "";
    public static Double smblDefaultMax_limit = Double.valueOf(100000.0d);
    public static Double smblDefaultMin_limit = Double.valueOf(500.0d);
    public static String smblFtSuccess = "";
    public static String smblLimitTypeid = "1";
    public static Double smblMax_limit = Double.valueOf(0.0d);
    public static Double smblMin_limit = Double.valueOf(0.0d);
    public static String toAccount = "";

    public static String getFromAccount() {
        return fromAccount;
    }

    public static String getToAccount() {
        return toAccount;
    }

    public static String getRemark() {
        return remark;
    }

    public static String getAmmount() {
        return ammount;
    }

    public static String getSendMedium() {
        return sendMedium;
    }

    public static String getSendTo() {
        return sendTo;
    }

    public static void setFromAccount(String fromAccount) {
        fromAccount = fromAccount;
    }

    public static void setToAccount(String toAccount) {
        toAccount = toAccount;
    }

    public static void setRemark(String remark) {
        remark = remark;
    }

    public static void setAmmount(String ammount) {
        ammount = ammount;
    }

    public static void setSendMedium(String sendMedium) {
        sendMedium = sendMedium;
    }

    public static void setSendTo(String sendTo) {
        sendTo = sendTo;
    }

    public static String getOtpType() {
        return otpType;
    }

    public static void setOtpType(String otpType) {
        otpType = otpType;
    }
}
