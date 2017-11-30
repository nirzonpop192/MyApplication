package rnd.com.technodhaka.android.myapplication.connect.simantobankapp.Activities;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.Process;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.BottomNavigationView.OnNavigationItemSelectedListener;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RequestQueue;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.regex.Pattern;

import rnd.com.technodhaka.android.myapplication.R;
import rnd.com.technodhaka.android.myapplication.connect.Connectivity.NetworkAvailability;
import rnd.com.technodhaka.android.myapplication.connect.Dialogs.ShowDialogs;
import rnd.com.technodhaka.android.myapplication.connect.MaterialDesign.Elevation;
import rnd.com.technodhaka.android.myapplication.connect.Utility.SecurityInfo;
import rnd.com.technodhaka.android.myapplication.connect.Utility.SessionInfo;
import rnd.com.technodhaka.android.myapplication.connect.VolleyClasses.VolleyErrorHelper;
import rnd.com.technodhaka.android.myapplication.connect.simantobankapp.LoginFragments.LoginRegisterFragment;


public class LoginRegisterActivity extends AppCompatActivity {
    Fragment currentFragment;
    boolean doubleBackToExitPressedOnce = false;
    String iid;
    EditText loginEmail;
    EditText loginPass;
    private OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new NavigationItemListener();
    FragmentManager manager;
    ProgressDialog progress;
    String remarks;
    long time;
    Toolbar toolbar;
    FragmentTransaction transaction;

    class RunnableHandlerThread implements Runnable {
        RunnableHandlerThread() {
        }

        public void run() {
            LoginRegisterActivity.this.doubleBackToExitPressedOnce = false;
        }
    }

    class NavigationItemListener implements OnNavigationItemSelectedListener {
        NavigationItemListener() {
        }

        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_fund_transfar:
                 /*   LoginRegisterActivity.this.currentFragment = new ContactListFragment();
                    LoginRegisterActivity.this.transaction = LoginRegisterActivity.this.manager.beginTransaction();
                    LoginRegisterActivity.this.transaction.replace(R.id.login_body_layout, LoginRegisterActivity.this.currentFragment);
                    LoginRegisterActivity.this.transaction.commit();*/
                    return true;
                case R.id.navigation_more:
                  /*  LoginRegisterActivity.this.currentFragment = new FaqFragment();
                    LoginRegisterActivity.this.transaction = LoginRegisterActivity.this.manager.beginTransaction();
                    LoginRegisterActivity.this.transaction.replace(R.id.login_body_layout, LoginRegisterActivity.this.currentFragment);
                    LoginRegisterActivity.this.transaction.commit();*/
                    return true;
                case R.id.navigation_login:
            /*        LoginRegisterActivity.this.currentFragment = new LoginRegisterFragment();
                    LoginRegisterActivity.this.transaction = LoginRegisterActivity.this.manager.beginTransaction();
                    LoginRegisterActivity.this.transaction.replace(R.id.login_body_layout, LoginRegisterActivity.this.currentFragment);
                    LoginRegisterActivity.this.transaction.commit();*/
                    return true;
                default:
                    return false;
            }
        }
    }

    class StringRequestListener implements Listener<String> {
        StringRequestListener() {
        }

        public void onResponse(String response) {
            response = response.replaceAll("\\\\", "");
            response = response.substring(1, response.length() - 1);
            Log.d("Login_Response", response);
            try {
                JSONObject user = new JSONObject(response);
                SecurityInfo.setUserCode(user.getString("UserCode"));
                SecurityInfo.setIsFirstLogin(user.getString("IsFirstLogin"));
                SecurityInfo.setUserEmail(user.getString("Email"));
                SecurityInfo.setUserPassword(user.getString("Password"));
                SecurityInfo.isFirstLogin = user.getString("IsFirstLogin");
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
                SessionInfo.IsFound = user.getString("IsFound");
                LoginRegisterActivity.this.progress.dismiss();
                if (SecurityInfo.isFirstLogin.equals("Y")) {
                    LoginRegisterActivity.this.loginEmail.setText("");
                    LoginRegisterActivity.this.loginPass.setText("");
                    LoginRegisterActivity.this.startActivity(new Intent(LoginRegisterActivity.this, TermsAndConditionActivity.class));
                } else if (SecurityInfo.isFirstLogin.equals("InvalidDevice")) {
                    Toast.makeText(LoginRegisterActivity.this, "This email is not registered with this device.", Toast.LENGTH_SHORT).show();
                } else if (SecurityInfo.isFirstLogin.equals("REG")) {
                    ShowDialogs.invalidRegistraton(LoginRegisterActivity.this);
                } else if (SecurityInfo.isFirstLogin.equals("WrongEmailPassword")) {
                    Toast.makeText(LoginRegisterActivity.this, "Invalid email or password", Toast.LENGTH_SHORT).show();
                } else if (SecurityInfo.isFirstLogin.equals("NotActive")) {
                    Toast.makeText(LoginRegisterActivity.this, "This Email ID is not registered with SMBL internet banking service.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(LoginRegisterActivity.this, "Login unsuccessful, please try again.", Toast.LENGTH_SHORT).show();
                }
            } catch (JSONException e) {
                LoginRegisterActivity.this.progress.dismiss();
                e.printStackTrace();
            }
        }
    }

    class NetworkErrorListener implements ErrorListener {
        NetworkErrorListener() {
        }

        public void onErrorResponse(VolleyError error) {
            try {
                LoginRegisterActivity.this.progress.dismiss();
                Log.d("FirstLogin", "onErrorResponse: " + error);
                Toast.makeText(LoginRegisterActivity.this, VolleyErrorHelper.getMessage(error, LoginRegisterActivity.this), Toast.LENGTH_LONG).show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_register);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        this.toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(this.toolbar);
        getSupportActionBar().setTitle((int) R.string.toolbar_title);
        this.toolbar.setTitleTextColor(-1);

        getSupportActionBar().setIcon((int) R.mipmap.ic_launcher_round);
        if (VERSION.SDK_INT >= 21) {
            Elevation.setElevation(this.toolbar, 10.0f);
        }
        ((BottomNavigationView) findViewById(R.id.bottomNavigation)).
                setOnNavigationItemSelectedListener(this.mOnNavigationItemSelectedListener);
        this.progress = new ProgressDialog(this);
        this.progress.setTitle("Loading");
        this.progress.setMessage("Wait while loading...");
        this.progress.setCancelable(false);
        this.manager = getFragmentManager();
        this.currentFragment = new LoginRegisterFragment();
        this.transaction = this.manager.beginTransaction();
        this.transaction.replace(R.id.login_body_layout, this.currentFragment);
        this.transaction.commit();
    }

    public void onClickContinue(View view) {
        this.progress = new ProgressDialog(this);
        this.progress.setTitle("Loading");
        this.progress.setMessage("Wait while loading...");
        this.progress.setCancelable(false);

       this.loginEmail = (EditText) findViewById(R.id.loginEmail);
        this.loginPass = (EditText) findViewById(R.id.loginPass);
        String userEmail = this.loginEmail.getText().toString();
        String userPass = this.loginPass.getText().toString();


        if (emailValidator(userEmail)) {
            String terminalIp = SecurityInfo.getTerminalIp();
            String browserInfo = SecurityInfo.getBrowserInfo();
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                this.remarks = SecurityInfo.getRemarks();
            } else {
                String whatRemark = extras.getString("FORGOT_PIN");
                if (whatRemark == null) {
                    this.remarks = SecurityInfo.getRemarks();
                } else {
                    this.remarks = whatRemark;
                }
            }
            String TAG = "FirstLogin";
            String finalUrl = (SecurityInfo.BASE_URL + "Api/Security/FirstLogin?")
                    + "email=" + userEmail + "&password=" + userPass + "&terminalIp="
                    + terminalIp + "&browserInfo=" + browserInfo + "&remarks=" + this.remarks;
            Log.d("finalUrl", finalUrl);
            if (NetworkAvailability.isNetworkAvailable(this)) {
                this.progress.show();
                try {
                    RequestQueue mRequestQueue = Volley.newRequestQueue(this);
                    StringRequest strReq = new StringRequest(0, finalUrl, new StringRequestListener(), new NetworkErrorListener());
                    strReq.setRetryPolicy(new DefaultRetryPolicy(40000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                    mRequestQueue.add(strReq);
                    return;
                } catch (Exception e) {
                    e.printStackTrace();
                    return;
                }
            }
            ShowDialogs.noInternetDialog(this);
            return;
        }
        Toast.makeText(this, "Invalid Email Address, please type again.", Toast.LENGTH_SHORT).show();
    }

    public void onBackPressed() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            try {
                if (extras.getString("FORGOT_PIN").equals("FP")) {
                    //   startActivity(new Intent(this, LoginPinActivity.class));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (this.doubleBackToExitPressedOnce) {
            Intent intent = new Intent("android.intent.action.MAIN");
            intent.addCategory("android.intent.category.HOME");
            startActivity(intent);
            Process.killProcess(Process.myPid());
            return;
        }
        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please press again to Exit", Toast.LENGTH_SHORT).show();
        new Handler().postDelayed(new RunnableHandlerThread(), 2000);
    }

    /**
     *
     * @param email email address string
     * @return validation of email
     */
    public boolean emailValidator(String email) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        return Pattern.compile("^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$").matcher(email).matches();
    }
}
