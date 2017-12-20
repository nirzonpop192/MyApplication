package rnd.com.technodhaka.android.myapplication.connect.agranibankapp.Activities;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentManager.BackStackEntry;
import android.app.FragmentManager.OnBackStackChangedListener;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.BottomNavigationView.OnNavigationItemSelectedListener;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.RequestQueue.RequestFilter;
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
import rnd.com.technodhaka.android.myapplication.connect.Utility.SecurityInfo;
import rnd.com.technodhaka.android.myapplication.connect.Utility.SessionInfo;
import rnd.com.technodhaka.android.myapplication.connect.Utility.TopupInfo;
import rnd.com.technodhaka.android.myapplication.connect.VolleyClasses.VolleyErrorHelper;
import rnd.com.technodhaka.android.myapplication.connect.agranibankapp.TopUpFragments.TopUpMainFragment;

public class TopUpActivity extends AppCompatActivity implements OnBackStackChangedListener {
    public static CoordinatorLayout coordinatorLayout;
    String TAG = "BackStack";
    int count;
    Fragment currentFragment;
    private String lastFragment;
    private OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new C06393();
    ProgressDialog progress;
    Toolbar toolbar;
    FragmentManager topupFragManager;
    FragmentTransaction transaction;

    class C06371 implements Listener<String> {
        C06371() {
        }

        public void onResponse(String response) {
            response = response.replaceAll("\\\\", "");
            response = response.substring(1, response.length() - 1);
            Log.d("Logout_Response", response);
            SessionInfo.logoutVerify = response;
            TopUpActivity.this.progress.dismiss();
            if (SessionInfo.logoutVerify.equals("Y")) {
                Intent intent = new Intent(TopUpActivity.this, LoginPinActivity.class);
                intent.addFlags(335544320);
                TopUpActivity.this.startActivity(intent);
            }
        }
    }

    class C06382 implements ErrorListener {
        C06382() {
        }

        public void onErrorResponse(VolleyError error) {
            try {
                TopUpActivity.this.progress.dismiss();
                Log.d("Logout", "onErrorResponse: " + error);
                Toast.makeText(TopUpActivity.this, VolleyErrorHelper.getMessage(error, TopUpActivity.this), Toast.LENGTH_LONG).show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    class C06393 implements OnNavigationItemSelectedListener {
        C06393() {
        }

        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            int i;
            switch (item.getItemId()) {
                case R.id.navigation_dashboard:
                    TopUpActivity.this.count = TopUpActivity.this.topupFragManager.getBackStackEntryCount();
                    for (i = 0; i <= TopUpActivity.this.count; i++) {
                        TopUpActivity.this.topupFragManager.popBackStack();
                    }
                    TopUpActivity.this.startActivity(new Intent(TopUpActivity.this, DashboardActivity.class));
                    return true;
                case R.id.navigation_fund_transfar:
                    TopUpActivity.this.count = TopUpActivity.this.topupFragManager.getBackStackEntryCount();
                    for (i = 0; i <= TopUpActivity.this.count; i++) {
                        TopUpActivity.this.topupFragManager.popBackStack();
                    }
                    TopUpActivity.this.startActivity(new Intent(TopUpActivity.this, GeneralFundTransfarActivity.class));
                    return true;
                case R.id.navigation_more:
                    TopUpActivity.this.count = TopUpActivity.this.topupFragManager.getBackStackEntryCount();
                    for (i = 0; i <= TopUpActivity.this.count; i++) {
                        TopUpActivity.this.topupFragManager.popBackStack();
                    }
                    TopUpActivity.this.startActivity(new Intent(TopUpActivity.this, MoreActivity.class));
                    return true;
                case R.id.navigation_account_info:
                    TopUpActivity.this.count = TopUpActivity.this.topupFragManager.getBackStackEntryCount();
                    for (i = 0; i <= TopUpActivity.this.count; i++) {
                        TopUpActivity.this.topupFragManager.popBackStack();
                    }
                    TopUpActivity.this.startActivity(new Intent(TopUpActivity.this, AccountInfoActivity.class));
                    return true;
                default:
                    return false;
            }
        }
    }





    class C06426 implements RequestFilter {
        C06426() {
        }

        public boolean apply(Request<?> request) {
            return true;
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_up);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        this.toolbar = (Toolbar) findViewById(R.id.toolbar_menu);
        setSupportActionBar(this.toolbar);
        getSupportActionBar().setTitle( R.string.toolbar_title);
        getSupportActionBar().setIcon( R.mipmap.ic_launcher_round);
        this.toolbar.setTitleTextColor(-1);
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinatorLayout);

        topupMinMaxAmount();

        this.topupFragManager = getFragmentManager();
        this.topupFragManager.addOnBackStackChangedListener(this);
        this.currentFragment = new TopUpMainFragment();
        this.transaction = this.topupFragManager.beginTransaction();
        this.transaction.replace(R.id.top_up_main_layout, this.currentFragment);
        this.transaction.addToBackStack("TopUpMainFragment");
        this.transaction.commit();
        ((BottomNavigationView) findViewById(R.id.bottomNavigationTopup)).setOnNavigationItemSelectedListener(this.mOnNavigationItemSelectedListener);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
 /*       if (item.getItemId() != R.id.action_logout) {
            return super.onOptionsItemSelected(item);
        }*/
        this.progress = new ProgressDialog(this);
        this.progress.setTitle("Loading");
        this.progress.setMessage("Wait while loading...");
        this.progress.setCancelable(false);
        if (NetworkAvailability.isNetworkAvailable(this)) {
            this.progress.show();
            String TAG = "Logout";
            String finalLogoutUrl =SecurityInfo.getFinalLogoutUrl(SecurityInfo.getUserEmail(),
                    SecurityInfo.getUserPassword(),SecurityInfo.getTerminalIp(),SessionInfo.sessionId);

            Log.d("finalLogoutUrl", finalLogoutUrl);

            try {
                RequestQueue mRequestQueue = Volley.newRequestQueue(this);
                StringRequest strReq = new StringRequest(0, finalLogoutUrl, new C06371(), new C06382());
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
//        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    private void topupMinMaxAmount() {
        this.progress = new ProgressDialog(this);
        this.progress.setTitle("Loading");
        this.progress.setMessage("Wait while loading...");
        this.progress.setCancelable(true);
        String TAG = "topupMinMaxAmount";
        String finalTopupMinMaxAmountUrl = SecurityInfo.getFinalTopupMinMaxAmountUrl(SecurityInfo.getUserEmail());
        Log.d("torupMinMaxUrl", finalTopupMinMaxAmountUrl);
        if (NetworkAvailability.isNetworkAvailable(this)) {
            this.progress.show();
            try {
                RequestQueue mRequestQueue = Volley.newRequestQueue(this);
                StringRequest strReq = new StringRequest(0, finalTopupMinMaxAmountUrl,
                        new Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Log.d("topupMinMax", response);
                                response = response.replaceAll("\\\\", "");
                                response = response.substring(2, response.length() - 2);
                                Log.d("topupMinMaxAmount", response);
                                TopUpActivity.this.progress.dismiss();
                                // Json parsing
                                try {
                                    JSONObject limit = new JSONObject(response);
                                    TopupInfo.postpaidTopupMax_limit = Double.valueOf(Double.parseDouble(limit.getString("Postpaid_max_limit")));
                                    TopupInfo.postpaidTopupMin_limit = Double.valueOf(Double.parseDouble(limit.getString("Postpaid_min_limit")));
                                    TopupInfo.prepaidTopupMax_limit = Double.valueOf(Double.parseDouble(limit.getString("Prepaid_max_limit")));
                                    TopupInfo.prepaidTopupMin_limit = Double.valueOf(Double.parseDouble(limit.getString("Prepaid_min_limit")));
                                    Log.d("TopUpLimit", "preMin" + TopupInfo.prepaidTopupMin_limit + "preMax" + TopupInfo.prepaidTopupMax_limit + "postMIn" + TopupInfo.postpaidTopupMin_limit + "postMax" + TopupInfo.postpaidTopupMax_limit);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        },
                        new ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                try {
                                    TopUpActivity.this.progress.dismiss();
                                    Log.d("topupMinMaxAmount", "onErrorResponse: " + error);
                                    Toast.makeText(TopUpActivity.this, VolleyErrorHelper.getMessage(error, TopUpActivity.this), Toast.LENGTH_SHORT).show();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                strReq.setRetryPolicy(new DefaultRetryPolicy(40000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                mRequestQueue.add(strReq);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void onBackStackChanged() {
        Log.d("TopUpStack", "++++++++++++++++++++++++++++++++++++++++++++++++++");
        int count = this.topupFragManager.getBackStackEntryCount();
        Log.d("countFragment", count + "");
        for (int i = count - 1; i >= 0; i--) {
            BackStackEntry entry = this.topupFragManager.getBackStackEntryAt(i);
            this.lastFragment = entry.getName();
            Log.i(this.TAG, "FoundFragment: " + i + " --> " + entry.getName());
        }
        Log.d("TopUpStack", "++++++++++++++++++++++++++++++++++++++++++++++++++");
    }

    public void onBackPressed() {
        Volley.newRequestQueue(this).cancelAll(new C06426());
        int count = this.topupFragManager.getBackStackEntryCount();
        if (count > 0) {
            this.lastFragment = this.topupFragManager.getBackStackEntryAt(count - 1).getName();
            Log.i(this.TAG, "LastFragment:--> " + this.lastFragment);
            Log.i(this.TAG, "Count:--> " + count);
            if (this.lastFragment.equals("TopUpMainFragment")) {
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
