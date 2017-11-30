package rnd.com.technodhaka.android.myapplication.connect.simantobankapp.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.BottomNavigationView.OnNavigationItemSelectedListener;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import rnd.com.technodhaka.android.myapplication.R;
import rnd.com.technodhaka.android.myapplication.connect.Animation.PageTransitions;
import rnd.com.technodhaka.android.myapplication.connect.Connectivity.NetworkAvailability;
import rnd.com.technodhaka.android.myapplication.connect.Dialogs.ShowDialogs;
import rnd.com.technodhaka.android.myapplication.connect.Dialogs.SnackbarUtil;
import rnd.com.technodhaka.android.myapplication.connect.MaterialDesign.Elevation;
import rnd.com.technodhaka.android.myapplication.connect.Utility.SecurityInfo;
import rnd.com.technodhaka.android.myapplication.connect.Utility.SessionInfo;
import rnd.com.technodhaka.android.myapplication.connect.VolleyClasses.VolleyErrorHelper;


public class DashboardActivity extends AppCompatActivity {
    LinearLayout accountDashboardLayout;
    private CoordinatorLayout coordinatorLayout;
    LinearLayout dashItem4;
    LinearLayout dashItem6;
    boolean doubleBackToExitPressedOnce = false;
    LinearLayout fundTransfarDashboardLayout;
    private OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new C06061();
    ProgressDialog progress;
    Toolbar toolbar;
    LinearLayout topUpDashboardLayout;

    class C03264 implements Runnable {
        C03264() {
        }

        public void run() {
            DashboardActivity.this.doubleBackToExitPressedOnce = false;
        }
    }

    class C06061 implements OnNavigationItemSelectedListener {
        C06061() {
        }

        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_dashboard:
                    DashboardActivity.this.startActivity(new Intent(DashboardActivity.this, DashboardActivity.class));
                    return true;
                case R.id.navigation_fund_transfar:
                    DashboardActivity.this.startActivity(new Intent(DashboardActivity.this, GeneralFundTransfarActivity.class));
                    return true;
                case R.id.navigation_more:
                    DashboardActivity.this.startActivity(new Intent(DashboardActivity.this, MoreActivity.class));
                    return true;
                case R.id.navigation_account_info:
                    DashboardActivity.this.startActivity(new Intent(DashboardActivity.this, AccountInfoActivity.class));
                    return true;
                default:
                    return false;
            }
        }
    }

    class LogoutResponseListener implements Listener<String> {
        LogoutResponseListener() {
        }

        public void onResponse(String response) {
            response = response.replaceAll("\\\\", "");
            response = response.substring(1, response.length() - 1);
            Log.d("Logout_Response", response);
            SessionInfo.logoutVerify = response;
            DashboardActivity.this.progress.dismiss();
            if (SessionInfo.logoutVerify.equals("Y")) {
                DashboardActivity.this.startActivity(new Intent(DashboardActivity.this, LoginPinActivity.class));
            }
        }
    }

    class C06083 implements ErrorListener {
        C06083() {
        }

        public void onErrorResponse(VolleyError error) {
            DashboardActivity.this.progress.dismiss();
            VolleyErrorHelper.getMessage(error, DashboardActivity.this);
            Log.d("Logout", "onErrorResponse: " + error);
            Toast.makeText(DashboardActivity.this, "Logout unsuccessful! Please try again!", 1).show();
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.activity_dashboard);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        this.coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinatorLayout);
        this.toolbar = (Toolbar) findViewById(R.id.toolbar_menu);
        setSupportActionBar(this.toolbar);
        getSupportActionBar().setTitle((int) R.string.toolbar_title);
        getSupportActionBar().setIcon((int) R.mipmap.ic_launcher_round);
        this.toolbar.setTitleTextColor(-1);
        if (VERSION.SDK_INT >= 21) {
            Elevation.setElevation(this.toolbar, 10.0f);
        }
        ((BottomNavigationView) findViewById(R.id.bottomNavigationDashBoard)).setOnNavigationItemSelectedListener(this.mOnNavigationItemSelectedListener);
        this.progress = new ProgressDialog(this);
        this.progress.setTitle("Loading");
        this.progress.setMessage("Wait while loading...");
        this.progress.setCancelable(false);
        this.accountDashboardLayout = (LinearLayout) findViewById(R.id.accountDashboardLayout);
        this.fundTransfarDashboardLayout = (LinearLayout) findViewById(R.id.fundTransfarDashboardLayout);
        this.topUpDashboardLayout = (LinearLayout) findViewById(R.id.topUpDashboardLayout);
        new PageTransitions(this, this.accountDashboardLayout).pageTransitionLeftToRight();
        new PageTransitions(this, this.fundTransfarDashboardLayout).pageTransitionRightToLeft();
        new PageTransitions(this, this.topUpDashboardLayout).pageTransitionLeftToRight();
        if (VERSION.SDK_INT >= 21) {
            Elevation.setElevation(this.toolbar, 10.0f);
        }
    }

    public void viewActivity(View v) {
        int continueTransfarId = v.getId();
        if (continueTransfarId == R.id.accountDashboardLayout) {
            startActivity(new Intent(this, AccountInfoActivity.class));
        } else if (continueTransfarId == R.id.fundTransfarDashboardLayout) {
            startActivity(new Intent(this, GeneralFundTransfarActivity.class));
        } else if (continueTransfarId == R.id.topUpDashboardLayout) {
            startActivity(new Intent(this, TopUpActivity.class));
        }
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() != R.id.action_logout) {
            return super.onOptionsItemSelected(item);
        }
        if (NetworkAvailability.isNetworkAvailable(this)) {
            this.progress.show();
            String TAG = "Logout";
            String finalLogoutUrl = (SecurityInfo.BASE_URL + "api/Security/Logout?") + "email=" + SecurityInfo.getUserEmail() + "&password=" + SecurityInfo.getUserPassword() + "&terminalIp=" + SecurityInfo.getTerminalIp() + "&sessionId=" + SessionInfo.sessionId;
            Log.d("finalLogoutUrl", finalLogoutUrl);
            try {
                Volley.newRequestQueue(this).add(new StringRequest(0, finalLogoutUrl, new LogoutResponseListener(), new C06083()));
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

    public void onBackPressed() {
        if (this.doubleBackToExitPressedOnce) {
            Intent intent = new Intent(this, LoginPinActivity.class);
            intent.addFlags(335544320);
            startActivity(intent);
            return;
        }
        this.doubleBackToExitPressedOnce = true;
        SnackbarUtil.generalMessage(this.coordinatorLayout, "Please click again to Log out");
        new Handler().postDelayed(new C03264(), 2000);
    }
}
