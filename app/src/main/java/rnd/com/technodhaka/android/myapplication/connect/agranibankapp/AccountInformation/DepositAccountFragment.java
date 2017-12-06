package rnd.com.technodhaka.android.myapplication.connect.agranibankapp.AccountInformation;

import android.app.Dialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.internal.view.SupportMenu;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RequestQueue;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;



public class DepositAccountFragment extends Fragment implements OnClickListener {
    @Override
    public void onClick(View view) {

    }
    /* String TAG = "DepositAccountFragment";
    FragmentManager accInfoFragManager;
    TextView accName;
    TextView accNo;
    TextView accOpenDate;
    TextView accStatus;
    String accountNo = "";
    TextView amountHold;
    TextView cashBalance;
    private CoordinatorLayout coordinatorLayout;
    Fragment detailsFragment;
    FragmentTransaction fTransaction;
    TextView netBalance;
    ProgressDialog progress;
    View rootView;
    TextView unclearedFunds;
    TextView userName;
    Button viewStatementButton;

    class C05991 implements Listener<String> {
        C05991() {
        }

        public void onResponse(String response) {
            response = response.replaceAll("\\\\", "");
            response = response.substring(1, response.length() - 1);
            Log.d("ResponseDp", response);
            try {
                Log.d("Region", "Entered");
                DepositAccountFragment.this.progress.dismiss();
                JSONObject jSONObject = new JSONObject(response);
                String moduleType = jSONObject.getString("Module");
                String minBalance = BigDecimal.valueOf(jSONObject.getDouble("MinBalance")).toPlainString();
                String accountNumber = jSONObject.getString("Accountno");
                String acName = jSONObject.getString("Acname");
                String openDate = jSONObject.getString("OpenDate");
                openDate = new SimpleDateFormat("dd-MM-yyyy").format(new Date(Long.parseLong(openDate.substring(6, openDate.length() - 2))));
                String balanceTK = BigDecimal.valueOf(jSONObject.getDouble("BalanceTK")).toPlainString();
                String netAvailableDrawableAmt = BigDecimal.valueOf(jSONObject.getDouble("DrawableAmt")).toPlainString();
                String status = jSONObject.getString("Status");
                String clearingTK = BigDecimal.valueOf(jSONObject.getDouble("ClearingTK")).toPlainString();
                String amountHoldBlockedAmt = jSONObject.getString("BlockedAmt");
                String statementAllowed = jSONObject.getString("StatementAllowed");
                Log.d("ClearingTk", clearingTK);
                Log.d("accountname", acName);
                if (statementAllowed.equals("Y")) {
                    DepositAccountFragment.this.viewStatementButton.setVisibility(0);
                }
                DepositAccountFragment.this.userName.setText(acName);
                Log.d("Region", "Entered");
                Log.d("userNamelove", acName);
                DepositAccountFragment.this.accNo.setText(accountNumber);
                DepositAccountFragment.this.accStatus.setText(status);
                DepositAccountFragment.this.accOpenDate.setText(openDate);
                DepositAccountFragment.this.accName.setText(acName);
                DepositAccountFragment.this.cashBalance.setText(balanceTK);
                DepositAccountFragment.this.amountHold.setText(amountHoldBlockedAmt);
                DepositAccountFragment.this.netBalance.setText(netAvailableDrawableAmt);
                DepositAccountFragment.this.unclearedFunds.setText(clearingTK);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    class C06002 implements ErrorListener {
        C06002() {
        }

        public void onErrorResponse(VolleyError error) {
            try {
                DepositAccountFragment.this.progress.dismiss();
                Log.d("accountInfoDetails_Volley", "onErrorResponse: " + error);
                DepositAccountFragment.this.netExceptionDialog(VolleyErrorHelper.getMessage(error, DepositAccountFragment.this.getActivity()), "Failed!", "Back", "Try Again");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        if (bundle != null) {
            this.accountNo = bundle.getString("AccountNo");
        }
        this.rootView = inflater.inflate(R.layout.fragment_deposit_account, container, false);
        this.coordinatorLayout = (CoordinatorLayout) getActivity().findViewById(R.id.coordinatorLayout);
        this.userName = (TextView) this.rootView.findViewById(R.id.userName);
        this.accNo = (TextView) this.rootView.findViewById(R.id.accNo);
        this.accStatus = (TextView) this.rootView.findViewById(R.id.accStatus);
        this.accOpenDate = (TextView) this.rootView.findViewById(R.id.accOpenDate);
        this.accName = (TextView) this.rootView.findViewById(R.id.accName);
        this.cashBalance = (TextView) this.rootView.findViewById(R.id.cashBalance);
        this.amountHold = (TextView) this.rootView.findViewById(R.id.amountOnHoldTv);
        this.unclearedFunds = (TextView) this.rootView.findViewById(R.id.unclearedFundTv);
        this.netBalance = (TextView) this.rootView.findViewById(R.id.netBalanceTv);
        new PageTransitions(getActivity(), this.rootView).pageTransitionBottomToTop();
        getAccountDetails();
        this.viewStatementButton = (Button) this.rootView.findViewById(R.id.viewStatementButton);
        this.viewStatementButton.setOnClickListener(this);
        return this.rootView;
    }

    private void getAccountDetails() {
        this.progress = new ProgressDialog(getActivity());
        this.progress.setTitle("Loading");
        this.progress.setMessage("Wait while loading...");
        this.progress.setCancelable(false);
        String terminalIp = SecurityInfo.getTerminalIp();
        String browserInfo = SecurityInfo.getBrowserInfo();
        String TAG = "accountInfoDetails_Volley";
        String finalAccountInfoDetailsUrlTreamed = ((SecurityInfo.baseUrl + "api/Account/AccountInfoDetails?") + "email=" + SecurityInfo.getUserEmail() + "&password=" + SecurityInfo.getUserPassword() + "&accountno=" + this.accountNo + "&terminalIp=" + terminalIp + "&browserInfo=" + browserInfo + "&remarks=" + SecurityInfo.getRemarks()).replaceAll(" ", "");
        Log.d("InfoDetailsUrlTreamed", finalAccountInfoDetailsUrlTreamed);
        Log.d("accountNo", this.accountNo);
        if (NetworkAvailability.isNetworkAvailable(getActivity())) {
            try {
                this.progress.show();
                RequestQueue mRequestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
                StringRequest strReq = new StringRequest(1, finalAccountInfoDetailsUrlTreamed, new C05991(), new C06002());
                strReq.setRetryPolicy(new DefaultRetryPolicy(40000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                mRequestQueue.add(strReq);
                return;
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }
        ShowDialogs.noInternetDialog(getActivity());
    }

    public void onClick(View v) {
        if (v.getId() == R.id.viewStatementButton) {
            this.detailsFragment = new AcStatementDateFragment();
            Bundle bundle = new Bundle();
            bundle.putString("AccountNo", this.accountNo);
            this.detailsFragment.setArguments(bundle);
            this.accInfoFragManager = getFragmentManager();
            this.fTransaction = this.accInfoFragManager.beginTransaction();
            this.fTransaction.replace(R.id.activity_account_info_layer, this.detailsFragment);
            this.fTransaction.addToBackStack("AcStatementDateFragment");
            this.fTransaction.commit();
        }
    }

    private void netExceptionDialog(String message, String title, String cancelBtn, String okBtn) {
        final Dialog netErrorDialog = new Dialog(getActivity());
        netErrorDialog.requestWindowFeature(1);
        netErrorDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        netErrorDialog.setContentView(R.layout.net_exception_dialog);
        netErrorDialog.setCancelable(false);
        Button okDialogButton = (Button) netErrorDialog.findViewById(R.id.dialogBtnPositive);
        Button cancelDialogButton = (Button) netErrorDialog.findViewById(R.id.dialogBtnNegative);
        TextView dialogTitle = (TextView) netErrorDialog.findViewById(R.id.ftSuccessTitle);
        TextView dialogText = (TextView) netErrorDialog.findViewById(R.id.dialogText);
        okDialogButton.setText(okBtn);
        cancelDialogButton.setText(cancelBtn);
        dialogTitle.setText(title);
        dialogText.setText(message);
        dialogText.setTextColor(-12303292);
        dialogTitle.setTextColor(SupportMenu.CATEGORY_MASK);
        okDialogButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                DepositAccountFragment.this.getAccountDetails();
                netErrorDialog.dismiss();
            }
        });
        cancelDialogButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                DepositAccountFragment.this.startActivity(new Intent(DepositAccountFragment.this.getActivity(), DashboardActivity.class));
                netErrorDialog.dismiss();
            }
        });
        netErrorDialog.show();
    }*/
}
