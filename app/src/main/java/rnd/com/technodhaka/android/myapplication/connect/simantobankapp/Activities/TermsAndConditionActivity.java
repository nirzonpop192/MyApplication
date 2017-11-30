package rnd.com.technodhaka.android.myapplication.connect.simantobankapp.Activities;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.View;
import android.widget.TextView;
import android.widget.TextView.BufferType;

import rnd.com.technodhaka.android.myapplication.R;
import rnd.com.technodhaka.android.myapplication.connect.Connectivity.NetworkAvailability;
import rnd.com.technodhaka.android.myapplication.connect.Dialogs.ShowDialogs;
import rnd.com.technodhaka.android.myapplication.connect.MaterialDesign.Elevation;
import rnd.com.technodhaka.android.myapplication.connect.Utility.SecurityInfo;


public class TermsAndConditionActivity extends AppCompatActivity {
    TextView loadMoreUrl;
    Toolbar toolbar;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.activity_terms_and_condition);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        this.toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(this.toolbar);
        getSupportActionBar().setTitle((int) R.string.toolbar_title);
        this.toolbar.setTitleTextColor(-1);
        getSupportActionBar().setIcon((int) R.mipmap.ic_launcher_round);
        if (VERSION.SDK_INT >= 21) {
            Elevation.setElevation(this.toolbar, 10.0f);
        }
        this.loadMoreUrl = (TextView) findViewById(R.id.detailsWebLink);
        this.loadMoreUrl.setPaintFlags(this.loadMoreUrl.getPaintFlags() | 8);
        TextView termsHeader = (TextView) findViewById(R.id.termsHeader);
        TextView termsItem = (TextView) findViewById(R.id.termsItem);
        String termsHeaderContent = "Terms & Conditions";
        String termsDescriptionContent = "";
        String termsItemContent = "<p>As a condition of use, I/We agree not to use the application for any purposes that is unlawful or prohibited by any terms, or any other purpose not reasonably intended by SMBL (Shimanto Bank Limited). \nI/we agree that I/we have read, understood and agree to the terms and Conditions for using this application. For details please click on the following link.</p>\n\n";
        if (VERSION.SDK_INT >= 24) {
            termsHeader.setText(Html.fromHtml(termsHeaderContent, 0));
            termsItem.setText(Html.fromHtml(termsItemContent, 0));
            return;
        }
        termsHeader.setText(Html.fromHtml(termsHeaderContent), BufferType.SPANNABLE);
        termsItem.setText(Html.fromHtml(termsItemContent), BufferType.SPANNABLE);
    }

    public void termsCancel(View view) {
        startActivity(new Intent(this, LoginRegisterActivity.class));
    }

    public void termsContinue(View view) {
        if (NetworkAvailability.isNetworkAvailable(this)) {
//            startActivity(new Intent(this, OtpTypeActivity.class));
        } else {
            ShowDialogs.noInternetDialog(this);
        }
    }

    public void onBackPressed() {
        startActivity(new Intent(this, LoginRegisterActivity.class));
    }

    public void transferUrl(View view) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(Uri.parse(SecurityInfo.TERMS_N_CONDITION_URL));
        startActivity(intent);
    }
}
