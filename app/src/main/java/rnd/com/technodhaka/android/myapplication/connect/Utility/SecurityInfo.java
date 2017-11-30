package rnd.com.technodhaka.android.myapplication.connect.Utility;

public class SecurityInfo {
    public static String BASE_URL = "https://connect.shimantobank.com:57443/";
    public static String TERMS_N_CONDITION_URL = "https://connect.shimantobank.com/Login/TermsConditions.aspx";
    public static String browserInfo;
    public static String instanceId;
    public static String isFirstLogin;
    public static String isFound;
    public static String remarks;
    public static String terminalIp;
    public static String userCode;
    public static String userEmail;
    public static String userName;
    public static String userPassword;

    public static String getFinalVerifyInstanceId(String instanceId){
        return  SecurityInfo.BASE_URL + "Api/Security/VerifyInstanceId?"+"instanceId=" + instanceId + "&emi=" + "na" + "&sn=" + "na" + "&ip=" + "na" + "&email=" + "na";
    }

    public static String getTerminalIp() {
        return terminalIp;
    }

    public static void setTerminalIp(String terminalIp) {
        terminalIp = terminalIp;
    }

    public static String getBrowserInfo() {
        return browserInfo;
    }

    public static void setBrowserInfo(String browserInfo) {
        browserInfo = browserInfo;
    }

    public static String getRemarks() {
        return remarks;
    }

    public static void setRemarks(String remarks) {
        remarks = remarks;
    }

    public static String getInstanceId() {
        return instanceId;
    }

    public static void setInstanceId(String instanceId) {
        instanceId = instanceId;
    }

    public static String getIsFirstLogin() {
        return isFirstLogin;
    }

    public static String getUserName() {
        return userName;
    }

    public static void setUserName(String userName) {
        userName = userName;
    }

    public static String isFirstLogin() {
        return isFirstLogin;
    }

    public static void setIsFirstLogin(String isFirstLogin) {
    }

    public static String getUserCode() {
        return userCode;
    }

    public static void setUserCode(String userCode) {
        userCode = userCode;
    }

    public static String getUserEmail() {
        return userEmail;
    }

    public static void setUserEmail(String userEmail) {
        userEmail = userEmail;
    }

    public static String getUserPassword() {
        return userPassword;
    }

    public static void setUserPassword(String userPassword) {
        userPassword = userPassword;
    }
}
