package rnd.com.technodhaka.android.myapplication.connect.agranibankapp.Activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import rnd.com.technodhaka.android.myapplication.R;
import rnd.com.technodhaka.android.myapplication.connect.Connectivity.NetworkAvailability;
import rnd.com.technodhaka.android.myapplication.connect.Utility.SecurityInfo;
import rnd.com.technodhaka.android.myapplication.connect.Utility.SessionInfo;
import rnd.com.technodhaka.android.myapplication.connect.VolleyClasses.VolleyErrorHelper;

public class LoginActivity extends AppCompatActivity {

    private Context mContext;
    ProgressDialog progress;
    private CoordinatorLayout coordinatorLayout;

    private class LoginUserValidResponse implements Response.Listener<String> {
        LoginUserValidResponse() {
        }

        public void onResponse(String response) {
        /*    response = response.replaceAll("\\\\", "");
            response = response.substring(1, response.length() - 1);*/
            Log.d("LogIn_Response", response);
            try {
                JSONObject jsonObject = new JSONObject(response);
                SecurityInfo.setUserCode(jsonObject.getString("UserCode"));
                SessionInfo.setCustomerID(jsonObject.getString("UserCode"));
                JSONObject user = jsonObject.getJSONObject("user");
                SessionInfo.setUserName(user.getString("UserName"));
                String Password = (user.getString("Password"));
                String Designation = (user.getString("Designation"));
                String AccessNo = (user.getString("AccessNo"));
                String CreateBankUserCode = (user.getString("CreateBankUserCode"));
                String UserCreateDate = (user.getString("UserCreateDate"));
                String PasswordCreateDate = (user.getString("PasswordCreateDate"));
                String ActivateBankUserCode = (user.getString("ActivateBankUserCode"));
                String ActivateDate = (user.getString("ActivateDate"));
                String ChargeableYN = (user.getString("ChargeableYN"));
                String password_net = (user.getString("password_net"));
                String email_id = (user.getString("email_id"));
                String CusMstrAccountNo = (user.getString("CusMstrAccountNo"));
                String ActiveYN = (user.getString("ActiveYN"));
                String CusViewStatementYN = (user.getString("CusViewStatementYN"));
                String CusChequeBookInsYN = (user.getString("CusChequeBookInsYN"));
                String CusFundTransferYN = (user.getString("CusFundTransferYN"));
                String CusTransferLimit = (user.getString("CusTransferLimit"));
                String CusUtilityBillYN = (user.getString("CusUtilityBillYN"));
                String BanCreateBankerPerYN = (user.getString("BanCreateBankerPerYN"));
                String BanCreateUserPerYN = (user.getString("BanCreateUserPerYN"));
                String emailBanActivateUserPerYN_id = (user.getString("emailBanActivateUserPerYN_id"));
                String BanViewChequeBookIns = (user.getString("BanViewChequeBookIns"));
                String BanViewPartyBal = (user.getString("BanViewPartyBal"));
                String BanViewPartyHis = (user.getString("BanViewPartyHis"));
                String BanTransactionYN = (user.getString("BanTransactionYN"));
                String BanChatYN = (user.getString("BanChatYN"));
                String BanUserLevel = (user.getString("BanUserLevel"));
                String BanBranchCode = (user.getString("BanBranchCode"));
                String CustomerBanker = (user.getString("CustomerBanker"));
                String TrnUserCode = (user.getString("TrnUserCode"));
                String contact = (user.getString("contact"));
                String OTPOption = (user.getString("OTPOption"));
                String FirstLoginYN = (user.getString("FirstLoginYN"));
                String SingleSessYN = (user.getString("SingleSessYN"));
                String TrfACType = (user.getString("TrfACType"));
                String ApproveYN = (user.getString("ApproveYN"));
                String allowtrnYN = (user.getString("allowtrnYN"));



                /*SecurityInfo.setUserPassword(user.getString("Password"));
                SecurityInfo.isFirstLogin = "N";
                SessionInfo.setUserName(user.getString("UserName").toString());
                SessionInfo.setCusMstrAccountNo(user.getString("CusMstrAccountNo").toString());
                SessionInfo.setActiveYN(user.getString("ActiveYN").toString());
                SessionInfo.setCusFundTransferYN(user.getString("CusFundTransferYN").toString());
                SessionInfo.setCusTransferLimit(user.getString("CusTransferLimit").toString());
                SessionInfo.setCusUtilityBillYN(user.getString("CusUtilityBillYN").toString());
                SessionInfo.setBanTransactionYN(user.getString("BanTransactionYN").toString());
                SessionInfo.setBanUserLevel(user.getString("BanUserLevel").toString());
                SessionInfo.setBanBranchCode(user.getString("BanBranchCode").toString());
                SessionInfo.setCustomerBanker(user.getString("CustomerBanker").toString());
                SessionInfo.setTrnUserCode(user.getString("TrnUserCode").toString());
                SessionInfo.setBankerYN(user.getString("BankerYN").toString());
                SessionInfo.setTrnPrivilege(user.getString("TrnPrivilege").toString());
                SessionInfo.setContact(user.getString("Contact").toString());
                SessionInfo.setOTPOption(user.getString("OTPOption").toString());
                SessionInfo.sessionId = user.getString("SessionId");
                SecurityInfo.isFound = user.getString("IsFound");
                Log.d("sessionId", SessionInfo.sessionId);
                Log.d("UserCode", SecurityInfo.getUserCode());
                Log.d("Password", SecurityInfo.getUserPassword());
                Log.d("UserName", SessionInfo.getUserName());
                Log.d("CusMstrAccountNo", SessionInfo.getCusMstrAccountNo());*/
                LoginActivity.this.progress.dismiss();
                if (email_id.length() > 3)
                    LoginActivity.this.startActivity(new Intent(LoginActivity.this, DashboardActivity.class));
               /* if (SecurityInfo.isFound.equals("Y")) {
                    LoginPinActivity.this.mPinFirstDigitEditText.setText("");
                    LoginPinActivity.this.mPinSecondDigitEditText.setText("");
                    LoginPinActivity.this.mPinThirdDigitEditText.setText("");
                    LoginPinActivity.this.mPinForthDigitEditText.setText("");
                    LoginPinActivity.this.mPinFifthDigitEditText.setText("");
                    LoginPinActivity.this.mPinSixthDigitEditText.setText("");
                    LoginPinActivity.this.mPinHiddenEditText.setText("");
                    LoginPinActivity.this.startActivity(new Intent(LoginPinActivity.this, DashboardActivity.class));
                    return;
                }*/
//                Snackbar.make(LoginActivity.this.coordinatorLayout, (CharSequence) "Invalid user name, please try again!", Snackbar.LENGTH_LONG).show();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    class JsonErrorListener implements Response.ErrorListener {
        JsonErrorListener() {
        }

        public void onErrorResponse(VolleyError error) {
            try {
                LoginActivity.this.progress.dismiss();
                Snackbar.make(LoginActivity.this.coordinatorLayout, VolleyErrorHelper.getMessage(error, LoginActivity.this), 0).show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private String str_email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mContext = LoginActivity.this;

        final EditText edtEmail = (EditText) findViewById(R.id.edt_email);


        Button btn_Login = (Button) findViewById(R.id.btn_logIn);

        btn_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginActivity.this.progress = new ProgressDialog(LoginActivity.this);
                LoginActivity.this.progress.setTitle("Loading");
                LoginActivity.this.progress.setMessage("Wait while loading...");
                LoginActivity.this.progress.setCancelable(false);
                String TAG = "PinVerify";
                str_email = edtEmail.getText().toString();
                String emailLoginUrl = SecurityInfo.IB_URL + "index.php?email=" + str_email;
//        String terminalIp = SecurityInfo.getTerminalIp();
//        String browserInfo = SecurityInfo.getBrowserInfo();
                String finalLoginEmailId = emailLoginUrl;
                Log.d("finalLoginEmailId", finalLoginEmailId);

                if (NetworkAvailability.isNetworkAvailable(LoginActivity.this)) {
                    try {
                        LoginActivity.this.progress.show();
                        RequestQueue mRequestQueue = Volley.newRequestQueue(LoginActivity.this);
                        StringRequest strReq = new StringRequest(Request.Method.GET, finalLoginEmailId, new LoginUserValidResponse(), new JsonErrorListener());
                        strReq.setRetryPolicy(new DefaultRetryPolicy(40000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                        mRequestQueue.add(strReq);
                        return;
                    } catch (Exception e) {
                        e.printStackTrace();
                        return;
                    }
                }

            }
        });

    }
}
