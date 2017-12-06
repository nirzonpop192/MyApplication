package rnd.com.technodhaka.android.myapplication.connect.agranibankapp.Activities;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentManager.BackStackEntry;
import android.app.FragmentManager.OnBackStackChangedListener;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.BottomNavigationView.OnNavigationItemSelectedListener;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
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

import rnd.com.technodhaka.android.myapplication.R;
import rnd.com.technodhaka.android.myapplication.connect.Connectivity.NetworkAvailability;
import rnd.com.technodhaka.android.myapplication.connect.Dialogs.ShowDialogs;
import rnd.com.technodhaka.android.myapplication.connect.MaterialDesign.Elevation;
import rnd.com.technodhaka.android.myapplication.connect.Utility.BEFTNinfo;
import rnd.com.technodhaka.android.myapplication.connect.Utility.GeneralFundTransferInfo;
import rnd.com.technodhaka.android.myapplication.connect.Utility.SecurityInfo;
import rnd.com.technodhaka.android.myapplication.connect.Utility.SessionInfo;
import rnd.com.technodhaka.android.myapplication.connect.VolleyClasses.VolleyErrorHelper;


public class GeneralFundTransfarActivity extends AppCompatActivity implements OnBackStackChangedListener {
    @Override
    public void onBackStackChanged() {

    }
    /* String TAG = "BackStack";
    ProgressDialog beftnProgress;
    int count;
    Fragment currentFragment;
    private String lastFragment;
    private OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new C06091();
    FragmentManager manager;
    ProgressDialog progress;
    ProgressDialog smblProgress;
    Toolbar toolbar;
    FragmentTransaction transaction;

    class C06091 implements OnNavigationItemSelectedListener {
        C06091() {
        }

        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            GeneralFundTransfarActivity.this.manager = GeneralFundTransfarActivity.this.getFragmentManager();
            int i;
            switch (item.getItemId()) {
                case R.id.navigation_dashboard:
                    GeneralFundTransfarActivity.this.count = GeneralFundTransfarActivity.this.manager.getBackStackEntryCount();
                    for (i = 0; i <= GeneralFundTransfarActivity.this.count; i++) {
                        GeneralFundTransfarActivity.this.manager.popBackStack();
                    }
                    GeneralFundTransfarActivity.this.startActivity(new Intent(GeneralFundTransfarActivity.this, DashboardActivity.class));
                    return true;
                case R.id.navigation_topup:
                    GeneralFundTransfarActivity.this.count = GeneralFundTransfarActivity.this.manager.getBackStackEntryCount();
                    for (i = 0; i <= GeneralFundTransfarActivity.this.count; i++) {
                        GeneralFundTransfarActivity.this.manager.popBackStack();
                    }
                    GeneralFundTransfarActivity.this.startActivity(new Intent(GeneralFundTransfarActivity.this, TopUpActivity.class));
                    return true;
                case R.id.navigation_more:
                    GeneralFundTransfarActivity.this.count = GeneralFundTransfarActivity.this.manager.getBackStackEntryCount();
                    for (i = 0; i <= GeneralFundTransfarActivity.this.count; i++) {
                        GeneralFundTransfarActivity.this.manager.popBackStack();
                    }
                    GeneralFundTransfarActivity.this.startActivity(new Intent(GeneralFundTransfarActivity.this, MoreActivity.class));
                    return true;
                case R.id.navigation_account_info:
                    GeneralFundTransfarActivity.this.count = GeneralFundTransfarActivity.this.manager.getBackStackEntryCount();
                    for (i = 0; i <= GeneralFundTransfarActivity.this.count; i++) {
                        GeneralFundTransfarActivity.this.manager.popBackStack();
                    }
                    GeneralFundTransfarActivity.this.startActivity(new Intent(GeneralFundTransfarActivity.this, AccountInfoActivity.class));
                    return true;
                default:
                    return false;
            }
        }
    }

    class C06102 implements Listener<String> {
        C06102() {
        }

        public void onResponse(String response) {
            response = response.replaceAll("\\\\", "");
            response = response.substring(1, response.length() - 1);
            Log.d("Logout_Response", response);
            SessionInfo.logoutVerify = response;
            GeneralFundTransfarActivity.this.progress.dismiss();
            if (SessionInfo.logoutVerify.equals("Y")) {
                Intent intent = new Intent(GeneralFundTransfarActivity.this, LoginPinActivity.class);
                intent.addFlags(335544320);
                GeneralFundTransfarActivity.this.startActivity(intent);
            }
        }
    }

    class C06113 implements ErrorListener {
        C06113() {
        }

        public void onErrorResponse(VolleyError error) {
            GeneralFundTransfarActivity.this.progress.dismiss();
            Log.d("Logout", "onErrorResponse: " + error);
            Toast.makeText(GeneralFundTransfarActivity.this,
                    VolleyErrorHelper.getMessage(error, GeneralFundTransfarActivity.this), Toast.LENGTH_LONG).show();
        }
    }

    class BeftnMinMaxAmountResponseListener implements Listener<String> {
        BeftnMinMaxAmountResponseListener() {
        }

        public void onResponse(String response) {
            response = response.replaceAll("\\\\", "");
            response = response.substring(2, response.length() - 2);
            Log.d("beftnMinMaxAmount", response);
            GeneralFundTransfarActivity.this.beftnProgress.dismiss();
            try {
                JSONObject user = new JSONObject(response);
                BEFTNinfo.beftnMin_limit = Double.valueOf(Double.parseDouble(user.getString("min_limit")));
                BEFTNinfo.beftnMax_limit = Double.valueOf(Double.parseDouble(user.getString("max_limit")));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    class C06135 implements ErrorListener {
        C06135() {
        }

        public void onErrorResponse(VolleyError error) {
            try {
                GeneralFundTransfarActivity.this.beftnProgress.dismiss();
                Log.d("minMaxAmount", "onErrorResponse: " + error);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    class C06146 implements Listener<String> {
        C06146() {
        }

        public void onResponse(String response) {
            Log.d("smblMinMaxAmountUn", response);
            response = response.replaceAll("\\\\", "");
            response = response.substring(2, response.length() - 2);
            Log.d("smblMinMaxAmount", response);
            GeneralFundTransfarActivity.this.smblProgress.dismiss();
            try {
                JSONObject user = new JSONObject(response);
                double min = Double.parseDouble(user.getString("min_limit"));
                GeneralFundTransferInfo.smblMin_limit = Double.valueOf(Double.parseDouble(user.getString("min_limit")));
                GeneralFundTransferInfo.smblMax_limit = Double.valueOf(Double.parseDouble(user.getString("max_limit")));
                Log.d("minSaikat", min + "");
                Log.d("maxSaikat", GeneralFundTransferInfo.smblMax_limit + "");
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    class C06157 implements ErrorListener {
        C06157() {
        }

        public void onErrorResponse(VolleyError error) {
            try {
                GeneralFundTransfarActivity.this.smblProgress.dismiss();
                Log.d("minMaxAmount", "onErrorResponse: " + error);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general_fund_transfar);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        this.toolbar = (Toolbar) findViewById(R.id.toolbar_menu);
        setSupportActionBar(this.toolbar);
        getSupportActionBar().setTitle(R.string.toolbar_title);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher_round);
        this.toolbar.setTitleTextColor(-1);
        if (VERSION.SDK_INT >= 21) {
            Elevation.setElevation(this.toolbar, 10.0f);
        }
        ((BottomNavigationView) findViewById(R.id.generalFtNavigation)).setOnNavigationItemSelectedListener(this.mOnNavigationItemSelectedListener);
        beftnMinMaxAmount();
        generalMinMaxAmount();
        Log.d("backStackActivity", "backstack");
        this.manager = getFragmentManager();
        this.manager.addOnBackStackChangedListener(this);
        this.currentFragment = new FundTransfarMainFragment();
        this.transaction = this.manager.beginTransaction();
        this.transaction.replace(R.id.general_ft_main_layout, this.currentFragment);
        this.transaction.addToBackStack("FundTransfarMainFragment");
        this.transaction.commit();
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        this.progress = new ProgressDialog(this);
        this.progress.setTitle("Loading");
        this.progress.setMessage("Wait while loading...");
        this.progress.setCancelable(false);
        int id = item.getItemId();
        String TAG = "Logout";

        String finalLogoutUrl = SecurityInfo.getFinalLogoutUrl(SecurityInfo.getUserEmail(),
                SecurityInfo.getUserPassword(), SecurityInfo.getTerminalIp(), SessionInfo.sessionId);

        Log.d("finalLogoutUrl", finalLogoutUrl);
        if (id != R.id.action_logout) {
            return super.onOptionsItemSelected(item);
        }
        if (NetworkAvailability.isNetworkAvailable(this)) {
            this.progress.show();
            try {
                RequestQueue mRequestQueue = Volley.newRequestQueue(this);
                StringRequest strReq = new StringRequest(0, finalLogoutUrl, new C06102(), new C06113());
                strReq.setRetryPolicy(new DefaultRetryPolicy(40000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                mRequestQueue.add(strReq);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            ShowDialogs.noInternetDialog(this);
        }
        return true;
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    private void beftnMinMaxAmount() {
        this.beftnProgress = new ProgressDialog(this);
        this.beftnProgress.setTitle("Loading");
        this.beftnProgress.setMessage("Wait while loading...");
        this.beftnProgress.setCancelable(false);
        String TAG = "minMaxAmount";

        String finalBeftnlMinMaxAmountUrl = SecurityInfo.getFinalMinMaxAmountUrl(SecurityInfo.getUserEmail(),
                BEFTNinfo.beftnLimittypeid, SecurityInfo.terminalIp, SessionInfo.sessionId, SecurityInfo.browserInfo);

        Log.d("finalMinMaxAmountUrl", finalBeftnlMinMaxAmountUrl);
        if (NetworkAvailability.isNetworkAvailable(this)) {
            this.beftnProgress.show();
            try {
                RequestQueue mRequestQueue = Volley.newRequestQueue(this);
                StringRequest strReq = new StringRequest(0, finalBeftnlMinMaxAmountUrl, new BeftnMinMaxAmountResponseListener(), new C06135());
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

    private void generalMinMaxAmount() {
        this.smblProgress = new ProgressDialog(this);
        this.smblProgress.setTitle("Loading");
        this.smblProgress.setMessage("Wait while loading...");
        this.smblProgress.setCancelable(false);
        String TAG = "minMaxAmount";

        String finalSmblMinMaxAmountUrl = SecurityInfo.getFinalMinMaxAmountUrl(SecurityInfo.getUserEmail(),
                GeneralFundTransferInfo.smblLimitTypeid, SecurityInfo.terminalIp, SessionInfo.sessionId
                , SecurityInfo.browserInfo);

        Log.d("finalSmblMinMaxUrl", finalSmblMinMaxAmountUrl);
        if (NetworkAvailability.isNetworkAvailable(this)) {
            this.smblProgress.show();
            try {
                RequestQueue mRequestQueue = Volley.newRequestQueue(this);
                StringRequest strReq = new StringRequest(0, finalSmblMinMaxAmountUrl, new C06146(), new C06157());
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

    public void onBackPressed() {
        int count = this.manager.getBackStackEntryCount();
        if (count > 0) {
            this.lastFragment = this.manager.getBackStackEntryAt(count - 1).getName();
            Log.i(this.TAG, "LastFragment:--> " + this.lastFragment);
            Log.i(this.TAG, "Count:--> " + count);
            if (this.lastFragment.equals("BeftnFtSuccessFragment")) {
                Log.i(this.TAG, "Caled ");
                this.manager.popBackStack("BeftnOtpTypeFragment", 1);
                return;
            } else if (this.lastFragment.equals("GeneralFtSuccessFragment")) {
                this.manager.popBackStack("GeneralFtOtpTypeFragment", 1);
                return;
            } else if (this.lastFragment.equals("FundTransfarMainFragment")) {
                startActivity(new Intent(this, DashboardActivity.class));
                return;
            } else {
                Log.i(this.TAG, "First if Called ");
                super.onBackPressed();
                return;
            }
        }
        Log.i(this.TAG, "Else Called ");
        super.onBackPressed();
    }

    public void onBackStackChanged() {
        Log.d("Stack", "++++++++++++++++++++++++++++++++++++++++++++++++++");
        for (int i = this.manager.getBackStackEntryCount() - 1; i >= 0; i--) {
            BackStackEntry entry = this.manager.getBackStackEntryAt(i);
            this.lastFragment = entry.getName();
            Log.i(this.TAG, "FoundFragment: " + i + " --> " + entry.getName());
        }
        Log.d("Stack", "++++++++++++++++++++++++++++++++++++++++++++++++++");
    }*/
}
