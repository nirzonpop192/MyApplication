package rnd.com.technodhaka.android.myapplication.connect.Utility;

public class SecurityInfo {

    public static String IB_URL = "http://192.168.214.2/internet_bank/ib_api/";
    public static String CBI_URL = "http://192.168.214.2/internet_bank/cbi_api/";
    public static String TERMS_N_CONDITION_URL = "https://connect.shimantobank.com/Login/TermsConditions.aspx";
    public static  final String MORE_INFO_ACTIVITY="http://www.shimantobank.com/androidmore";
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

    public static String getFinalVerifyInstanceId(final String instanceId) {
        return SecurityInfo.IB_URL + "Api/Security/VerifyInstanceId?" + "instanceId="
                + instanceId + "&emi=" + "na" + "&sn=" + "na" + "&ip=" + "na" + "&email=" + "na";
    }

    public static String getFinalLogoutUrl(final String userEmail, final String userPassword,
                                           final String terminalIp, final String sessionId) {

        return SecurityInfo.IB_URL + "api/Security/Logout?" + "email=" + userEmail
                + "&password=" + userPassword + "&terminalIp=" + terminalIp
                + "&sessionId=" + sessionId;
    }
    public static String getFinalTopupMinMaxAmountUrl(final String userEmail){
        return SecurityInfo.IB_URL + "api/Account/MinimunRechargeAmount?" + "emailid=" + userEmail;
    }
    public static String getFinalMinMaxAmountUrl(final String userEmail, String beftnLimitTypeID,
                                                 String terminalIp, String sessionId, String browserInfo){

        return SecurityInfo.IB_URL + "api/Account/MinimunTransferAmount?" + "email=" + userEmail
                + "&limittypeid=" + beftnLimitTypeID + "&terminalIp=" +terminalIp
                + "&sessionId=" + sessionId + "&browserInfo=" + browserInfo;
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
