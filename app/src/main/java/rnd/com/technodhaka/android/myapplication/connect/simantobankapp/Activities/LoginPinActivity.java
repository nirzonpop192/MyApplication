package rnd.com.technodhaka.android.myapplication.connect.simantobankapp.Activities;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.Process;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.BottomNavigationView.OnNavigationItemSelectedListener;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.view.View.OnKeyListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RequestQueue;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.iid.InstanceID;

import org.json.JSONException;
import org.json.JSONObject;

import rnd.com.technodhaka.android.myapplication.R;
import rnd.com.technodhaka.android.myapplication.connect.Connectivity.NetworkAvailability;
import rnd.com.technodhaka.android.myapplication.connect.Dialogs.ShowDialogs;
import rnd.com.technodhaka.android.myapplication.connect.MaterialDesign.Elevation;
import rnd.com.technodhaka.android.myapplication.connect.Utility.SecurityInfo;
import rnd.com.technodhaka.android.myapplication.connect.Utility.SessionInfo;
import rnd.com.technodhaka.android.myapplication.connect.VolleyClasses.VolleyErrorHelper;
import rnd.com.technodhaka.android.myapplication.connect.simantobankapp.LoginFragments.ContactListFragment;


public class LoginPinActivity extends AppCompatActivity implements OnFocusChangeListener, OnKeyListener, TextWatcher {
    private CoordinatorLayout coordinatorLayout;
    Fragment currentFragment;
    boolean doubleBackToExitPressedOnce = false;
    TextView forgotPin;
    String instanceId;
    private OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new C06161();
    private EditText mPinFifthDigitEditText;
    private EditText mPinFirstDigitEditText;
    private EditText mPinForthDigitEditText;
    private EditText mPinHiddenEditText;
    private EditText mPinSecondDigitEditText;
    private EditText mPinSixthDigitEditText;
    private EditText mPinThirdDigitEditText;
    FragmentManager manager;
    String pinText;
    ProgressDialog progress;
    ProgressDialog progressInstance;
    Toolbar toolbar;
    FragmentTransaction transaction;

    class DoubleBackPressButtonTracker implements Runnable {
        DoubleBackPressButtonTracker() {
        }

        public void run() {
            LoginPinActivity.this.doubleBackToExitPressedOnce = false;
        }
    }

    public class MainLayout extends LinearLayout {
        public MainLayout(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            ((LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE)).inflate(R.layout.activity_login_pin, this);
        }

        protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
            int proposedHeight = MeasureSpec.getSize(heightMeasureSpec);
            int actualHeight = getHeight();
            Log.d("TAG", "proposed: " + proposedHeight + ", actual: " + actualHeight);
            if (actualHeight >= proposedHeight) {
                if (LoginPinActivity.this.mPinHiddenEditText.length() == 0) {
                    LoginPinActivity.this.setFocusedPinBackground(LoginPinActivity.this.mPinFirstDigitEditText);
                } else {
                    LoginPinActivity.this.setDefaultPinBackground(LoginPinActivity.this.mPinFirstDigitEditText);
                }
            }
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }

    class C06161 implements OnNavigationItemSelectedListener {
        C06161() {
        }

        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            LoginPinActivity.this.manager = LoginPinActivity.this.getFragmentManager();
            switch (item.getItemId()) {
                case R.id.navigation_fund_transfar:
                    LoginPinActivity.this.currentFragment = new ContactListFragment();
                    LoginPinActivity.this.transaction = LoginPinActivity.this.manager.beginTransaction();
                    LoginPinActivity.this.transaction.replace(R.id.login_pin_body_layout, LoginPinActivity.this.currentFragment);
                    LoginPinActivity.this.transaction.commit();
                    return true;
                case R.id.navigation_more:
                    LoginPinActivity.this.currentFragment = new FaqFragment();
                    LoginPinActivity.this.transaction = LoginPinActivity.this.manager.beginTransaction();
                    LoginPinActivity.this.transaction.replace(R.id.login_pin_body_layout, LoginPinActivity.this.currentFragment);
                    LoginPinActivity.this.transaction.commit();
                    return true;
                case R.id.navigation_login:
                    LoginPinActivity.this.finish();
                    LoginPinActivity.this.startActivity(LoginPinActivity.this.getIntent());
                    return true;
                default:
                    return false;
            }
        }
    }

    class C06172 implements Listener<String> {
        C06172() {
        }

        public void onResponse(String response) {
            response = response.replaceAll("\\\\", "");
            response = response.substring(1, response.length() - 1);
            Log.d("Instance_verify", response);
            try {
                JSONObject user = new JSONObject(response);
                SessionInfo.IsInstanceVerified = user.getString("IsInstanceVerified");
                if (SessionInfo.IsInstanceVerified.equals("Y")) {
                    SecurityInfo.setUserEmail(user.getString("Email"));
                    SecurityInfo.setUserPassword(user.getString("Password"));
                    LoginPinActivity.this.progressInstance.dismiss();
                    LoginPinActivity.this.pinVerify();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    class NetErrorListener implements ErrorListener {
        NetErrorListener() {
        }

        public void onErrorResponse(VolleyError error) {
            try {
                LoginPinActivity.this.progressInstance.dismiss();
                ShowDialogs.networkErrorException(LoginPinActivity.this, "Connection timed out!",
                        "Sorry!", "Exit", "Try again", LoginPinActivity.this.getIntent());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    class PinVerifyJsonResponseListener implements Listener<String> {
        PinVerifyJsonResponseListener() {
        }

        public void onResponse(String response) {
            response = response.replaceAll("\\\\", "");
            response = response.substring(1, response.length() - 1);
            Log.d("PIN_verify_Response", response);
            try {
                JSONObject user = new JSONObject(response);
                SecurityInfo.setUserCode(user.getString("UserCode"));
                SecurityInfo.setUserEmail(user.getString("Email"));
                SecurityInfo.setUserPassword(user.getString("Password"));
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
                Log.d("CusMstrAccountNo", SessionInfo.getCusMstrAccountNo());
                LoginPinActivity.this.progress.dismiss();
                if (SecurityInfo.isFound.equals("Y")) {
                    LoginPinActivity.this.mPinFirstDigitEditText.setText("");
                    LoginPinActivity.this.mPinSecondDigitEditText.setText("");
                    LoginPinActivity.this.mPinThirdDigitEditText.setText("");
                    LoginPinActivity.this.mPinForthDigitEditText.setText("");
                    LoginPinActivity.this.mPinFifthDigitEditText.setText("");
                    LoginPinActivity.this.mPinSixthDigitEditText.setText("");
                    LoginPinActivity.this.mPinHiddenEditText.setText("");
                    LoginPinActivity.this.startActivity(new Intent(LoginPinActivity.this, DashboardActivity.class));
                    return;
                }
                Snackbar.make(LoginPinActivity.this.coordinatorLayout, (CharSequence) "Wrong PIN, please try again!", 0).show();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    class JsonErrorListener implements ErrorListener {
        JsonErrorListener() {
        }

        public void onErrorResponse(VolleyError error) {
            try {
                LoginPinActivity.this.progress.dismiss();
                Snackbar.make(LoginPinActivity.this.coordinatorLayout, VolleyErrorHelper.getMessage(error, LoginPinActivity.this), 0).show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_pin);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        this.forgotPin = (TextView) findViewById(R.id.forgotPin);
        this.coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinatorLayout);
        this.toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(this.toolbar);
        getSupportActionBar().setTitle(R.string.toolbar_title);
        this.toolbar.setTitleTextColor(-1);
        getSupportActionBar().setIcon((int) R.mipmap.ic_launcher_round);
        if (VERSION.SDK_INT >= 21) {
            Elevation.setElevation(this.toolbar, 10.0f);
        }
        this.instanceId = InstanceID.getInstance(this).getId();
        Log.d("instanceId", this.instanceId);
        init();
        setPINListeners();
        ((BottomNavigationView) findViewById(R.id.login_pin_navigation)).setOnNavigationItemSelectedListener(this.mOnNavigationItemSelectedListener);
        SecurityInfo.setInstanceId(this.instanceId);
        Log.d("SecInstance", SecurityInfo.getInstanceId());
    }

    public void afterTextChanged(Editable s) {
    }

    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    public void hideSoftKeyboard(EditText editText) {
        if (editText != null) {
            ((InputMethodManager) getSystemService(INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(editText.getWindowToken(), 0);
        }
    }

    private void init() {
        this.mPinFirstDigitEditText = (EditText) findViewById(R.id.login_pin_first_edittext);
        this.mPinSecondDigitEditText = (EditText) findViewById(R.id.login_pin_second_edittext);
        this.mPinThirdDigitEditText = (EditText) findViewById(R.id.login_pin_third_edittext);
        this.mPinForthDigitEditText = (EditText) findViewById(R.id.login_pin_forth_edittext);
        this.mPinFifthDigitEditText = (EditText) findViewById(R.id.login_pin_fifth_edittext);
        this.mPinSixthDigitEditText = (EditText) findViewById(R.id.login_pin_sixth_edittext);
        this.mPinHiddenEditText = (EditText) findViewById(R.id.login_pin_hidden_edittext);
    }

    public void pinLoginContinue(View v) {
        this.mPinHiddenEditText = (EditText) findViewById(R.id.login_pin_hidden_edittext);
        this.pinText = this.mPinHiddenEditText.getText().toString();
        if (this.pinText.length() < 6) {
            Snackbar.make(this.coordinatorLayout, "Please insert all of the Pin !!!", 0).show();
        } else {
            instanceIdVerify();
        }
    }

    private void instanceIdVerify() {
        this.progressInstance = new ProgressDialog(this);
        this.progressInstance.setTitle("Loading");
        this.progressInstance.setMessage("Wait while loading...");
        this.progressInstance.setCancelable(false);
        String TAG = "SplashScreen";

        String finalVerifyInstanceId = SecurityInfo.getFinalVerifyInstanceId(InstanceID.getInstance(this).getId());
        Log.d("finalVerifyInstanceId", finalVerifyInstanceId);
        try {
            this.progressInstance.show();
            RequestQueue mRequestQueue = Volley.newRequestQueue(this);
            StringRequest strReq = new StringRequest(0, finalVerifyInstanceId, new C06172(), new NetErrorListener());
            strReq.setRetryPolicy(new DefaultRetryPolicy(40000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            mRequestQueue.add(strReq);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void pinVerify() {
        this.progress = new ProgressDialog(this);
        this.progress.setTitle("Loading");
        this.progress.setMessage("Wait while loading...");
        this.progress.setCancelable(false);
        String TAG = "PinVerify";
        String pinVerifyUrl = SecurityInfo.BASE_URL + "api/Security/Login?";
        String terminalIp = SecurityInfo.getTerminalIp();
        String browserInfo = SecurityInfo.getBrowserInfo();
        String finalPinVerifyUrl = pinVerifyUrl + "email=" + SecurityInfo.getUserEmail() + "&password=" + SecurityInfo.getUserPassword() + "&pin=" + this.pinText + "&terminalIp=" + terminalIp + "&browserInfo=" + browserInfo + "&remarks=" + SecurityInfo.getRemarks();
        Log.d("finalPinVerifyUrl", finalPinVerifyUrl);
        if (NetworkAvailability.isNetworkAvailable(this)) {
            try {
                this.progress.show();
                RequestQueue mRequestQueue = Volley.newRequestQueue(this);
                StringRequest strReq = new StringRequest(0, finalPinVerifyUrl, new PinVerifyJsonResponseListener(), new JsonErrorListener());
                strReq.setRetryPolicy(new DefaultRetryPolicy(40000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                mRequestQueue.add(strReq);
                return;
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }
        ShowDialogs.noInternetDialog(this);
    }

    public void onFocusChange(View v, boolean hasFocus) {
        switch (v.getId()) {
            case R.id.login_pin_first_edittext:
                if (hasFocus) {
                    setFocus(this.mPinHiddenEditText);
                    showSoftKeyboard(this.mPinHiddenEditText);
                    return;
                }
                return;
            case R.id.login_pin_second_edittext:
                if (hasFocus) {
                    setFocus(this.mPinHiddenEditText);
                    showSoftKeyboard(this.mPinHiddenEditText);
                    return;
                }
                return;
            case R.id.login_pin_third_edittext:
                if (hasFocus) {
                    setFocus(this.mPinHiddenEditText);
                    showSoftKeyboard(this.mPinHiddenEditText);
                    return;
                }
                return;
            case R.id.login_pin_forth_edittext:
                if (hasFocus) {
                    setFocus(this.mPinHiddenEditText);
                    showSoftKeyboard(this.mPinHiddenEditText);
                    return;
                }
                return;
            case R.id.login_pin_fifth_edittext:
                if (hasFocus) {
                    setFocus(this.mPinHiddenEditText);
                    showSoftKeyboard(this.mPinHiddenEditText);
                    return;
                }
                return;
            case R.id.login_pin_sixth_edittext:
                if (hasFocus) {
                    setFocus(this.mPinHiddenEditText);
                    showSoftKeyboard(this.mPinHiddenEditText);
                    return;
                }
                return;
            default:
                return;
        }
    }

    public boolean onKey(View v, int keyCode, KeyEvent event) {
        if (event.getAction() != 0) {
            return false;
        }
        switch (v.getId()) {
            case R.id.login_pin_hidden_edittext:
                if (keyCode != 67) {
                    return false;
                }
                this.mPinSixthDigitEditText.setText("");
                this.mPinFifthDigitEditText.setText("");
                this.mPinForthDigitEditText.setText("");
                this.mPinThirdDigitEditText.setText("");
                this.mPinSecondDigitEditText.setText("");
                this.mPinFirstDigitEditText.setText("");
                this.mPinHiddenEditText.setText("");
                return true;
            default:
                return false;
        }
    }

    public void onTextChanged(CharSequence s, int start, int before, int count) {
        setDefaultPinBackground(this.mPinFirstDigitEditText);
        setDefaultPinBackground(this.mPinSecondDigitEditText);
        setDefaultPinBackground(this.mPinThirdDigitEditText);
        setDefaultPinBackground(this.mPinForthDigitEditText);
        setDefaultPinBackground(this.mPinFifthDigitEditText);
        setDefaultPinBackground(this.mPinSixthDigitEditText);
        if (s.length() == 0) {
            setFocusedPinBackground(this.mPinFirstDigitEditText);
            this.mPinFirstDigitEditText.setText("");
        } else if (s.length() == 1) {
            setFocusedPinBackground(this.mPinSecondDigitEditText);
            this.mPinFirstDigitEditText.setText(s.charAt(0) + "");
            this.mPinSecondDigitEditText.setText("");
            this.mPinThirdDigitEditText.setText("");
            this.mPinForthDigitEditText.setText("");
            this.mPinFifthDigitEditText.setText("");
            this.mPinSixthDigitEditText.setText("");
        } else if (s.length() == 2) {
            setFocusedPinBackground(this.mPinThirdDigitEditText);
            this.mPinSecondDigitEditText.setText(s.charAt(1) + "");
            this.mPinThirdDigitEditText.setText("");
            this.mPinForthDigitEditText.setText("");
            this.mPinFifthDigitEditText.setText("");
            this.mPinSixthDigitEditText.setText("");
        } else if (s.length() == 3) {
            setFocusedPinBackground(this.mPinForthDigitEditText);
            this.mPinThirdDigitEditText.setText(s.charAt(2) + "");
            this.mPinForthDigitEditText.setText("");
            this.mPinFifthDigitEditText.setText("");
            this.mPinSixthDigitEditText.setText("");
        } else if (s.length() == 4) {
            setFocusedPinBackground(this.mPinFifthDigitEditText);
            this.mPinForthDigitEditText.setText(s.charAt(3) + "");
            this.mPinFifthDigitEditText.setText("");
            this.mPinSixthDigitEditText.setText("");
        } else if (s.length() == 5) {
            setFocusedPinBackground(this.mPinSixthDigitEditText);
            this.mPinFifthDigitEditText.setText(s.charAt(4) + "");
            this.mPinSixthDigitEditText.setText("");
        } else if (s.length() == 6) {
            setDefaultPinBackground(this.mPinSixthDigitEditText);
            this.mPinSixthDigitEditText.setText(s.charAt(5) + "");
            hideSoftKeyboard(this.mPinSixthDigitEditText);
        }
    }

    private void setDefaultPinBackground(EditText editText) {
        setViewBackground(editText, getResources().getDrawable(R.drawable.general_border_box));
    }

    public static void setFocus(EditText editText) {
        if (editText != null) {
            editText.setFocusable(true);
            editText.setFocusableInTouchMode(true);
            editText.requestFocus();
        }
    }

    private void setFocusedPinBackground(EditText editText) {
        setViewBackground(editText, getResources().getDrawable(R.drawable.general_border_box));
    }

    private void setPINListeners() {
        this.mPinHiddenEditText.addTextChangedListener(this);
        this.mPinFirstDigitEditText.setOnFocusChangeListener(this);
        this.mPinSecondDigitEditText.setOnFocusChangeListener(this);
        this.mPinThirdDigitEditText.setOnFocusChangeListener(this);
        this.mPinForthDigitEditText.setOnFocusChangeListener(this);
        this.mPinFifthDigitEditText.setOnFocusChangeListener(this);
        this.mPinSixthDigitEditText.setOnFocusChangeListener(this);
        this.mPinFirstDigitEditText.setOnKeyListener(this);
        this.mPinSecondDigitEditText.setOnKeyListener(this);
        this.mPinThirdDigitEditText.setOnKeyListener(this);
        this.mPinForthDigitEditText.setOnKeyListener(this);
        this.mPinFifthDigitEditText.setOnKeyListener(this);
        this.mPinSixthDigitEditText.setOnKeyListener(this);
        this.mPinHiddenEditText.setOnKeyListener(this);
    }

    public void setViewBackground(View view, Drawable background) {
        if (view != null && background != null) {
            if (VERSION.SDK_INT >= 16) {
                view.setBackground(background);
            } else {
                view.setBackgroundDrawable(background);
            }
        }
    }

    public void showSoftKeyboard(EditText editText) {
        if (editText != null) {
            ((InputMethodManager) getSystemService(INPUT_METHOD_SERVICE)).showSoftInput(editText, 0);
        }
    }

    public void forgotPin(View view) {
        ShowDialogs.forgotPinDialog(this, "You have re-register this app to reset pin.",
                "Forgot Pin!", "Back", "Re-register", getIntent());
    }

    public void onBackPressed() {
        if (this.doubleBackToExitPressedOnce) {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            startActivity(intent);
            Process.killProcess(Process.myPid());
            return;
        }
        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click again to go Exit", Toast.LENGTH_SHORT).show();
        new Handler().postDelayed(new DoubleBackPressButtonTracker(), 2000);
    }
}
