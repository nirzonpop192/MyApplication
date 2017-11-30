package rnd.com.technodhaka.android.myapplication.connect.simantobankapp.Activities;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.PorterDuff.Mode;
import android.os.Bundle;
import android.os.Handler;
import android.os.Process;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;

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

import java.util.Timer;
import java.util.TimerTask;


import rnd.com.technodhaka.android.myapplication.connect.Connectivity.NetworkAvailability;
import rnd.com.technodhaka.android.myapplication.connect.Dialogs.ShowDialogs;
import rnd.com.technodhaka.android.myapplication.connect.Utility.SecurityInfo;
import rnd.com.technodhaka.android.myapplication.connect.Utility.SessionInfo;
import rnd.com.technodhaka.android.myapplication.R;

public class SplashActivity extends AppCompatActivity {
    private static final String TAG = "SplashActivityTAG";
    private Timer autoUpdate;
    boolean doubleBackToExitPressedOnce = false;
    String email = "na";
    String emi = "na";
    Handler handler = new Handler();
    String ip = "na";
    ProgressBar progressBar;
    TextView progressPercentage;
    int progressStatus = 0;
    String sn = "na";
    TextView textView1;
    int trackInternet = 0;
    String userCode;

    class ProgressStatusThread implements Runnable {

        class ProgressHandlerThread implements Runnable {
            ProgressHandlerThread() {
            }

            public void run() {
                SplashActivity.this.progressBar.setProgress(SplashActivity.this.progressStatus);
                SplashActivity.this.progressPercentage.setText(SplashActivity.this.progressStatus + "%");
            }
        }

        ProgressStatusThread() {
        }

        public void run() {

            while (SplashActivity.this.progressStatus < 100) {
                SplashActivity splashActivity = SplashActivity.this;
                splashActivity.progressStatus++;

                if (SplashActivity.this.progressStatus == 10) {
                    SplashActivity.this.instanceIdVerify();
                }
                SplashActivity.this.handler.post(new ProgressHandlerThread());
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if (SplashActivity.this.progressStatus == 100) {
                try {
                    if (SessionInfo.IsInstanceVerified.equals("N")) {
                           SplashActivity.this.startActivity(new Intent(SplashActivity.this, LoginRegisterActivity.class));
                        return;
                    }
                      SplashActivity.this.startActivity(new Intent(SplashActivity.this, LoginPinActivity.class));
                } catch (Exception e2) {
                    Log.d("error", e2 + "");
                }
            }
        }
    }

    /**
     * invoke from the {@link #onResume()} so it invoke in UI thread
     */
    class SplashTimerTacker extends TimerTask {

        class SplashUpdateThread implements Runnable {
            SplashUpdateThread() {
            }

            public void run() {
                SplashActivity.this.updateOnCreate();
            }
        }

        SplashTimerTacker() {
        }

        public void run() {
            SplashActivity.this.runOnUiThread(new SplashUpdateThread());
        }
    }

    class SplashErrorListener implements ErrorListener {
        SplashErrorListener() {
        }

        public void onErrorResponse(VolleyError error) {
            Log.d("SplashScreen", "Error --->    " + error.getMessage());
            try {
                ShowDialogs.networkErrorException(SplashActivity.this, "Connection timed out!",
                        "Sorry!", "Exit", "Try again", SplashActivity.this.getIntent());
                Log.d("SplashScreen", "Error --->    " + error.getMessage());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

    }

    /**
     * this method invoke from {@link SplashTimerTacker.SplashUpdateThread#run()}
     */
    private void updateOnCreate() {
        if (NetworkAvailability.isNetworkAvailable(this)) {
            this.progressPercentage = (TextView) findViewById(R.id.load_per);
            this.progressBar = (ProgressBar) findViewById(R.id.progressBar1);
            this.progressBar.setScaleY(3.0f);
            this.progressBar.getProgressDrawable().setColorFilter(Color.rgb(2, 125, 57), Mode.SRC_IN);
            new Thread(new ProgressStatusThread()).start();
            return;
        }
        if (this.trackInternet >= 1) {
            finish();
            startActivity(getIntent());
        }
        ShowDialogs.noInternetDialog(this);
        this.trackInternet++;
    }

    public void onResume() {
        super.onResume();
        this.autoUpdate = new Timer();
        this.autoUpdate.schedule(new SplashTimerTacker(), 0, 5000);
    }

    public void onPause() {
        this.autoUpdate.cancel();
        super.onPause();
    }

    /**
     *
     */
    private void instanceIdVerify() {
        String TAG = "SplashScreen";
        String instanceIdUrl = SecurityInfo.BASE_URL + "Api/Security/VerifyInstanceId?";
        String hUrl = SecurityInfo.BASE_URL + "Api/Security/HelloService?";

        final String verifyInstanceId = InstanceID.getInstance(this).getId();

        String finalVerifyInstanceId = instanceIdUrl + "instanceId=" + verifyInstanceId + "&emi="
                + this.emi + "&sn=" + this.sn + "&ip=" + this.ip + "&email=" + this.email;
        Log.d("finalVerifyInstanceId", finalVerifyInstanceId);
        try {
            RequestQueue mRequestQueue = Volley.newRequestQueue(this);
            StringRequest strReq = new StringRequest(0, finalVerifyInstanceId, new Listener<String>() {
                public void onResponse(String response) {
                    response = response.replaceAll("\\\\", "");
                    response = response.substring(1, response.length() - 1);
                    try {
                        Log.d("Instance_verify", response);
                        JSONObject user = new JSONObject(response);
                        SplashActivity.this.userCode = user.getString("UserCode").toString();
                        SessionInfo.IsInstanceVerified = user.getString("IsInstanceVerified");
                        SecurityInfo.setUserCode(user.getString("UserCode"));
                        SecurityInfo.setIsFirstLogin(user.getString("IsFirstLogin"));
                        SecurityInfo.setUserEmail(user.getString("Email"));
                        SecurityInfo.setUserPassword(user.getString("Password"));
                        SecurityInfo.setTerminalIp(verifyInstanceId);
                        SecurityInfo.setBrowserInfo("na");
                        SecurityInfo.setRemarks("na");
//                        Log.d("userCode1N", SecurityInfo.getUserCode());
//                        Log.d("IsUserVerified", SessionInfo.IsInstanceVerified);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new SplashErrorListener());
            strReq.setRetryPolicy(new DefaultRetryPolicy(40000, 3, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            mRequestQueue.add(strReq);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public void onBackPressed() {

        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addFlags(335544320);
        intent.addCategory(Intent.CATEGORY_HOME);
        startActivity(intent);
        finish();
        System.exit(0);
        Process.killProcess(Process.myPid());
    }
}
