package rnd.com.technodhaka.android.myapplication.connect.simantobankapp.Activities;

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

import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import rnd.com.technodhaka.android.agranibank.R;
import rnd.com.technodhaka.android.agranibank.connect.Connectivity.NetworkAvailability;
import rnd.com.technodhaka.android.agranibank.connect.Dialogs.ShowDialogs;
import rnd.com.technodhaka.android.agranibank.connect.MaterialDesign.Elevation;
import rnd.com.technodhaka.android.agranibank.connect.Utility.SecurityInfo;
import rnd.com.technodhaka.android.agranibank.connect.Utility.SessionInfo;
import rnd.com.technodhaka.android.agranibank.connect.VolleyClasses.VolleyErrorHelper;
import rnd.com.technodhaka.android.agranibank.connect.shimantobankapp.AccountInformation.AccountListFragment;


public class AccountInfoActivity extends AppCompatActivity implements OnBackStackChangedListener {
    String TAG = "AccInfoBackStack";
    FragmentManager accInfoFragManager;
    int count;
    Fragment currentFragment;
    public String getEmail = "";
    public String getPassword = "";
    public String getUserCode = "";
    public String getUserName = "";
    private String lastFragment;
    private OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new C06031();
    ProgressDialog progress;
    Toolbar toolbar;
    FragmentTransaction transaction;

    class C06031 implements OnNavigationItemSelectedListener {
        C06031() {
        }

        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            AccountInfoActivity.this.accInfoFragManager = AccountInfoActivity.this.getFragmentManager();
            int i;
            switch (item.getItemId()) {
                case R.id.navigation_dashboard:
                    AccountInfoActivity.this.count = AccountInfoActivity.this.accInfoFragManager.getBackStackEntryCount();
                    for (i = 0; i <= AccountInfoActivity.this.count; i++) {
                        AccountInfoActivity.this.accInfoFragManager.popBackStack();
                    }
                    AccountInfoActivity.this.startActivity(new Intent(AccountInfoActivity.this, DashboardActivity.class));
                    return true;
                case R.id.navigation_fund_transfar:
                    AccountInfoActivity.this.count = AccountInfoActivity.this.accInfoFragManager.getBackStackEntryCount();
                    Log.d("FragCount", AccountInfoActivity.this.count + "");
                    for (i = 0; i <= AccountInfoActivity.this.count; i++) {
                        AccountInfoActivity.this.accInfoFragManager.popBackStack();
                    }
                    AccountInfoActivity.this.startActivity(new Intent(AccountInfoActivity.this, GeneralFundTransfarActivity.class));
                    return true;
                case R.id.navigation_topup:
                    AccountInfoActivity.this.count = AccountInfoActivity.this.accInfoFragManager.getBackStackEntryCount();
                    for (i = 1; i <= AccountInfoActivity.this.count; i++) {
                        AccountInfoActivity.this.accInfoFragManager.popBackStack();
                    }
                    AccountInfoActivity.this.startActivity(new Intent(AccountInfoActivity.this, TopUpActivity.class));
                    return true;
                case R.id.navigation_more:
                    AccountInfoActivity.this.count = AccountInfoActivity.this.accInfoFragManager.getBackStackEntryCount();
                    for (i = 0; i <= AccountInfoActivity.this.count; i++) {
                        AccountInfoActivity.this.accInfoFragManager.popBackStack();
                    }
                    AccountInfoActivity.this.startActivity(new Intent(AccountInfoActivity.this, MoreActivity.class));
                    return true;
                default:
                    return false;
            }
        }
    }

    class C06042 implements Listener<String> {
        C06042() {
        }

        public void onResponse(String response) {
            response = response.replaceAll("\\\\", "");
            response = response.substring(1, response.length() - 1);
            Log.d("Logout_Response", response);
            SessionInfo.logoutVerify = response;
            AccountInfoActivity.this.progress.dismiss();
            if (SessionInfo.logoutVerify.equals("Y")) {
                Intent intent = new Intent(AccountInfoActivity.this, LoginPinActivity.class);
                intent.addFlags(335544320);
                AccountInfoActivity.this.startActivity(intent);
            }
        }
    }

    class C06053 implements ErrorListener {
        C06053() {
        }

        public void onErrorResponse(VolleyError error) {
            AccountInfoActivity.this.progress.dismiss();
            Log.d("Logout", "onErrorResponse: " + error);
            Toast.makeText(AccountInfoActivity.this, VolleyErrorHelper.getMessage(error, AccountInfoActivity.this), 1).show();
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.activity_account_info);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        this.toolbar = (Toolbar) findViewById(R.id.toolbar_menu);
        setSupportActionBar(this.toolbar);
        getSupportActionBar().setTitle((int) R.string.toolbar_title);
        getSupportActionBar().setIcon((int) R.mipmap.ic_launcher_round);
        this.toolbar.setTitleTextColor(-1);
        if (VERSION.SDK_INT >= 21) {
            Elevation.setElevation(this.toolbar, 10.0f);
        }
        this.progress = new ProgressDialog(this);
        this.progress.setTitle("Loading");
        this.progress.setMessage("Wait while loading...");
        this.progress.setCancelable(false);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            this.getUserName = extras.getString("UserName");
            this.getUserCode = extras.getString("UserCode");
            this.getPassword = extras.getString("Password");
            this.getEmail = extras.getString("UserEmail");
        }
        ((BottomNavigationView) findViewById(R.id.accountBottomNavigation)).setOnNavigationItemSelectedListener(this.mOnNavigationItemSelectedListener);
        this.currentFragment = new AccountListFragment();
        this.accInfoFragManager = getFragmentManager();
        this.accInfoFragManager.addOnBackStackChangedListener(this);
        this.transaction = this.accInfoFragManager.beginTransaction();
        this.transaction.replace(R.id.activity_account_info_layer, this.currentFragment);
        this.transaction.addToBackStack("AccountListFragment");
        this.transaction.commit();
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() != R.id.action_logout) {
            return super.onOptionsItemSelected(item);
        }
        if (NetworkAvailability.isNetworkAvailable(this)) {
            this.progress.show();
            String TAG = "Logout";
            String finalLogoutUrl = (SecurityInfo.baseUrl + "api/Security/Logout?") + "email=" + SecurityInfo.getUserEmail() + "&password=" + SecurityInfo.getUserPassword() + "&terminalIp=" + SecurityInfo.getTerminalIp() + "&sessionId=" + SessionInfo.sessionId;
            Log.d("finalLogoutUrl", finalLogoutUrl);
            try {
                Volley.newRequestQueue(this).add(new StringRequest(0, finalLogoutUrl, new C06042(), new C06053()));
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

    public void onBackStackChanged() {
        Log.d("AccInfoStack", "++++++++++++++++++++++++++++++++++++++++++++++++++");
        this.accInfoFragManager = getFragmentManager();
        for (int i = this.accInfoFragManager.getBackStackEntryCount() - 1; i >= 0; i--) {
            BackStackEntry entry = this.accInfoFragManager.getBackStackEntryAt(i);
            this.lastFragment = entry.getName();
            Log.i(this.TAG, "FoundFragment: " + i + " --> " + entry.getName());
        }
        Log.d("AccInfoStack", "++++++++++++++++++++++++++++++++++++++++++++++++++");
    }

    public void onBackPressed() {
        int count = this.accInfoFragManager.getBackStackEntryCount();
        if (count > 0) {
            this.lastFragment = this.accInfoFragManager.getBackStackEntryAt(count - 1).getName();
            Log.i(this.TAG, "LastFragment:--> " + this.lastFragment);
            Log.i(this.TAG, "Count:--> " + count);
            if (this.lastFragment.equals("AccountListFragment")) {
                startActivity(new Intent(this, DashboardActivity.class));
                return;
            } else {
                super.onBackPressed();
                return;
            }
        }
        super.onBackPressed();
    }
}
