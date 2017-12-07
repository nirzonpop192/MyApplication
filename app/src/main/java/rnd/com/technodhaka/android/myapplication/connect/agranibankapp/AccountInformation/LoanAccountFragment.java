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
import android.widget.Spinner;
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

import rnd.com.technodhaka.android.myapplication.R;
import rnd.com.technodhaka.android.myapplication.connect.Animation.PageTransitions;
import rnd.com.technodhaka.android.myapplication.connect.Connectivity.NetworkAvailability;
import rnd.com.technodhaka.android.myapplication.connect.Dialogs.ShowDialogs;
import rnd.com.technodhaka.android.myapplication.connect.Utility.SecurityInfo;
import rnd.com.technodhaka.android.myapplication.connect.VolleyClasses.VolleyErrorHelper;
import rnd.com.technodhaka.android.myapplication.connect.agranibankapp.Activities.DashboardActivity;


public class LoanAccountFragment extends Fragment implements OnClickListener {

      FragmentManager accInfoFragManager;
    TextView accName;
    Spinner accNo;
    TextView accStatus;
    String accountNo = "";
    private CoordinatorLayout coordinatorLayout;
    Fragment detailsFragment;
    TextView emiDateTv;
    TextView emiSizeTv;
    FragmentTransaction fTransaction;
    TextView loanOutstandingTv;
    ProgressDialog progress;
    TextView remainingInstalmentTv;
    View rootView;
    TextView totalEmiTv;
    TextView userName;
    Button viewStatementLnButton;

    class C06011 implements Listener<String> {
        C06011() {
        }

        public void onResponse(String response) {
            response = response.replaceAll("\\\\", "");
            response = response.substring(1, response.length() - 1);
            Log.d("LnResponse", response);
            try {
                JSONObject jSONObject = new JSONObject(response);
                String moduleType = jSONObject.getString("Module");
                String accountNumber = jSONObject.getString("Accountno");
                String acName = jSONObject.getString("Acname");
                Log.d("AccountNameLn", acName);
                String status = jSONObject.getString("Status");
                String installmentDate = jSONObject.getString("InstalmentDT");
                if (installmentDate.equals(null)) {
                    installmentDate = "";
                } else {
                    installmentDate = new SimpleDateFormat("dd-MM-yyyy").format(new Date(Long.parseLong(installmentDate.substring(6, installmentDate.length() - 2))));
                }
                String NumberOfInst = jSONObject.getString("NumberOfInst");
                String remainingInstalment = jSONObject.getString("NoRemainingInstalment");
                String emiSize = BigDecimal.valueOf(jSONObject.getDouble("Instalment")).toPlainString();
                String loanOutstanding = BigDecimal.valueOf(jSONObject.getDouble("OutstandingBalance")).toPlainString().replace("-", "");
                if (jSONObject.getString("StatementAllowed").equals("Y")) {
                    LoanAccountFragment.this.viewStatementLnButton.setVisibility(View.VISIBLE);
                }
                LoanAccountFragment.this.userName.setText(acName);
//                LoanAccountFragment.this.spAccNo.setText(accountNumber);
                LoanAccountFragment.this.accStatus.setText(status);
                LoanAccountFragment.this.accName.setText(acName);
                LoanAccountFragment.this.remainingInstalmentTv.setText(remainingInstalment);
                LoanAccountFragment.this.emiDateTv.setText(installmentDate);
                LoanAccountFragment.this.totalEmiTv.setText(NumberOfInst);
                LoanAccountFragment.this.loanOutstandingTv.setText(loanOutstanding);
                LoanAccountFragment.this.emiSizeTv.setText(emiSize);
                LoanAccountFragment.this.progress.dismiss();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    class C06022 implements ErrorListener {
        C06022() {
        }

        public void onErrorResponse(VolleyError error) {
            try {
                LoanAccountFragment.this.progress.dismiss();
//                Log.fromDateSetListener("accountInfoDetails_Volley", "onErrorResponse: " + error);
                LoanAccountFragment.this.netExceptionDialog(VolleyErrorHelper.getMessage(error, LoanAccountFragment.this.getActivity()), "Failed!", "Back", "Try Again");
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
        rootView = inflater.inflate(R.layout.fragment_loan_account, container, false);
        coordinatorLayout = (CoordinatorLayout) getActivity().findViewById(R.id.coordinatorLayout);
        userName = (TextView) this.rootView.findViewById(R.id.userName);
        accNo = (Spinner) this.rootView.findViewById(R.id.spAccNumber);
        accStatus = (TextView) this.rootView.findViewById(R.id.accStatus);
        accName = (TextView) this.rootView.findViewById(R.id.accName);
        emiDateTv = (TextView) this.rootView.findViewById(R.id.emiDate);
        totalEmiTv = (TextView) this.rootView.findViewById(R.id.totalEmiTv);
        emiSizeTv = (TextView) this.rootView.findViewById(R.id.emiSizeTv);
        remainingInstalmentTv = (TextView) this.rootView.findViewById(R.id.remainingInstalmentTv);
        loanOutstandingTv = (TextView) this.rootView.findViewById(R.id.loanOutstandingTv);
        new PageTransitions(getActivity(), this.rootView).pageTransitionBottomToTop();
       // getAccountDetails();
        this.viewStatementLnButton = (Button) this.rootView.findViewById(R.id.viewStatementLnButton);
//        this.viewStatementLnButton.setOnClickListener(this);
        return this.rootView;
    }

    public void getAccountDetails() {
        this.progress = new ProgressDialog(getActivity());
        this.progress.setTitle("Loading");
        this.progress.setMessage("Wait while loading...");
        this.progress.setCancelable(false);
        String terminalIp = SecurityInfo.getTerminalIp();
        String browserInfo = SecurityInfo.getBrowserInfo();
        String TAG = "accountInfoDetails_Volley";
        String finalAccountInfoDetailsUrl = ((SecurityInfo.IB_URL + "api/Account/AccountDetails?") + "email=" + SecurityInfo.getUserEmail() + "&password=" + SecurityInfo.getUserPassword() + "&accountno=" + this.accountNo + "&terminalIp=" + terminalIp + "&browserInfo=" + browserInfo + "&remarks=" + SecurityInfo.getRemarks()).replaceAll(" ", "");
        Log.d("finaLoanInfoDetailsUrl", finalAccountInfoDetailsUrl);
        if (NetworkAvailability.isNetworkAvailable(getActivity())) {
            try {
                this.progress.show();
                RequestQueue mRequestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
                StringRequest strReq = new StringRequest(0, finalAccountInfoDetailsUrl, new C06011(), new C06022());
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
        if (v.getId() == R.id.viewStatementLnButton) {
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
        dialogText.setTextColor(getResources().getColor(R.color.colorTextPrimary));
        dialogTitle.setTextColor(SupportMenu.CATEGORY_MASK);
        okDialogButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                LoanAccountFragment.this.getAccountDetails();
                netErrorDialog.dismiss();
            }
        });
        cancelDialogButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                LoanAccountFragment.this.startActivity(new Intent(LoanAccountFragment.this.getActivity(), DashboardActivity.class));
                netErrorDialog.dismiss();
            }
        });
        netErrorDialog.show();
    }
}
