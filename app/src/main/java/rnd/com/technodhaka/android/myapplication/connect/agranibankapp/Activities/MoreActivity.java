package rnd.com.technodhaka.android.myapplication.connect.agranibankapp.Activities;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.BottomNavigationView.OnNavigationItemSelectedListener;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebSettings.PluginState;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import rnd.com.technodhaka.android.myapplication.R;
import rnd.com.technodhaka.android.myapplication.connect.MaterialDesign.Elevation;
import rnd.com.technodhaka.android.myapplication.connect.Utility.SecurityInfo;


public class MoreActivity extends AppCompatActivity {
    Fragment currentFragment;
    private OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new C06242();
    FragmentManager manager;
    WebView myWebView;
    View rootView;
    Toolbar toolbar;
    FragmentTransaction transaction;
    WebSettings webSettings;

    class C03291 extends WebViewClient {
        C03291() {
        }

        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }

    class C06242 implements OnNavigationItemSelectedListener {
        C06242() {
        }

        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_dashboard:
                    MoreActivity.this.startActivity(new Intent(MoreActivity.this, DashboardActivity.class));
                    return true;
                case R.id.navigation_fund_transfar:
                    MoreActivity.this.startActivity(new Intent(MoreActivity.this, GeneralFundTransfarActivity.class));
                    return true;
                case R.id.navigation_topup:
                    MoreActivity.this.startActivity(new Intent(MoreActivity.this, TopUpActivity.class));
                    return true;
                case R.id.navigation_account_info:
                    MoreActivity.this.startActivity(new Intent(MoreActivity.this, AccountInfoActivity.class));
                    return true;
                default:
                    return false;
            }
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.activity_more);
        this.toolbar = (Toolbar) findViewById(R.id.more_toolbar_menu);
        setSupportActionBar(this.toolbar);
        getSupportActionBar().setTitle((int) R.string.toolbar_title);
        this.toolbar.setTitleTextColor(-1);
        getSupportActionBar().setIcon((int) R.mipmap.ic_launcher_round);
        if (VERSION.SDK_INT >= 21) {
            Elevation.setElevation(this.toolbar, 10.0f);
        }
        this.myWebView = (WebView) findViewById(R.id.webViewMore);
        this.webSettings = this.myWebView.getSettings();
        this.webSettings.setJavaScriptEnabled(true);
        this.webSettings.setPluginState(PluginState.ON);
        this.myWebView.setWebViewClient(new C03291());
        this.myWebView.loadUrl(SecurityInfo.MORE_INFO_ACTIVITY);
        ((BottomNavigationView) findViewById(R.id.bottomNavigationMore)).setOnNavigationItemSelectedListener(this.mOnNavigationItemSelectedListener);
    }

    public void onBackPressed() {
        startActivity(new Intent(this, DashboardActivity.class));
    }
}
