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
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RequestQueue;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import rnd.com.technodhaka.android.myapplication.R;
import rnd.com.technodhaka.android.myapplication.connect.Connectivity.NetworkAvailability;
import rnd.com.technodhaka.android.myapplication.connect.Dialogs.ShowDialogs;
import rnd.com.technodhaka.android.myapplication.connect.Utility.AccInfo;
import rnd.com.technodhaka.android.myapplication.connect.Utility.SecurityInfo;
import rnd.com.technodhaka.android.myapplication.connect.Utility.SessionInfo;
import rnd.com.technodhaka.android.myapplication.connect.VolleyClasses.VolleyErrorHelper;
import rnd.com.technodhaka.android.myapplication.connect.agranibankapp.AccountInformation.AccountDetails.AccountDetailsFragment;
import rnd.com.technodhaka.android.myapplication.connect.agranibankapp.Activities.DashboardActivity;
import rnd.com.technodhaka.android.myapplication.connect.agranibankapp.DevController;
import rnd.com.technodhaka.android.myapplication.connect.agranibankapp.View.SpinnerHelper;


public class AccountListFragment extends Fragment {
    FragmentManager accInfoFragManager;
    ArrayList<AccountInfo> accountList;
    //String accountModule;
    String mAccountNo;
    private AccountListAdapter adapter;
    String mBalance;
    private CoordinatorLayout coordinatorLayout;
    String mCurrency;
    String mAccountName;
    String mAccountType;
    //String description;
    Fragment detailsFragment;
    FragmentTransaction fTransaction;
    FrameLayout frameLayout;
    private ListView listView;
    private ArrayList<SpinnerHelper> mList;
    String module;
    ProgressDialog progress;
    View rootView;
    //String status;
    private Bundle bundle;


    class AccItemClickListener implements OnItemClickListener {
        AccItemClickListener() {
        }

        public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
            TextView moduleTextView = (TextView) view.findViewById(R.id.info_acc_module_text);
            String listAccNo = ((TextView) view.findViewById(R.id.info_acc_no_text)).getText().toString();
            String moduleText = moduleTextView.getText().toString();
            Log.d("listAccNo", listAccNo);
            Log.d("moduleText", moduleText);
//            if (moduleText.equals("DP")) {
            AccountListFragment.this.detailsFragment = new AccountDetailsFragment();

            Bundle bundle = new Bundle();
            bundle.putString("AccountNo", listAccNo);
            bundle.putString("Module", moduleText);
            bundle.putParcelableArrayList("DIM_ke", mList);

            detailsFragment.setArguments(bundle);
            accInfoFragManager = AccountListFragment.this.getFragmentManager();
            fTransaction = AccountListFragment.this.accInfoFragManager.beginTransaction();
            fTransaction.replace(R.id.activity_account_info_layer, AccountListFragment.this.detailsFragment);
            fTransaction.addToBackStack("AccountDetailsFragment");
            fTransaction.commit();
//                return;
//            }
            /*AccountListFragment.this.detailsFragment = new LoanAccountFragment();
            bundle = new Bundle();
            bundle.putString("AccountNo", listAccNo);
            bundle.putString("Module", moduleText);
            AccountListFragment.this.detailsFragment.setArguments(bundle);
            AccountListFragment.this.accInfoFragManager = AccountListFragment.this.getFragmentManager();
            AccountListFragment.this.fTransaction = AccountListFragment.this.accInfoFragManager.beginTransaction();
            AccountListFragment.this.fTransaction.replace(R.id.activity_account_info_layer, AccountListFragment.this.detailsFragment);
            AccountListFragment.this.fTransaction.addToBackStack("LoanAccountFragment");
            AccountListFragment.this.fTransaction.commit();*/
        }
    }

    class AccountSummaryResponse implements Listener<String> {
        AccountSummaryResponse() {
        }

        public void onResponse(String response) {
            Log.d("ResponseInAcListTry", response);

//            String treamedResp = response.replaceAll("\\\\", "");
//            String trimedResponse = treamedResp.substring(1, treamedResp.length() - 1);
//            Log.fromDateSetListener("treamedSesponse", trimedResponse);
            AccountListFragment.this.progress.dismiss();
            try {
//                Log.d("inTry1", "Entered");
                JSONObject jsonObject = new JSONObject(response);
                JSONObject user = jsonObject.getJSONObject("user");
//                Log.d("inTry2", "Entered");

                if (!jsonObject.isNull("customer_account")) {
                    JSONArray customerAccounts = jsonObject.getJSONArray("customer_account");
                    int size = customerAccounts.length();
                    mList = new ArrayList<SpinnerHelper>();
                    for (int i = 0; i < size; i++) {
                        JSONObject customerAccount = customerAccounts.getJSONObject(i);


                        AccountInfo userAccount = new AccountInfo();
                        mAccountNo = customerAccount.getString("accountno");
                        mBalance = customerAccount.getString("Bal_tk");
                        mCurrency = customerAccount.getString("curr_code");
                        mAccountName = customerAccount.getString("acname");
                        mAccountType = customerAccount.getString("acc_type");

                        Log.d("inTry mCurrency", mCurrency);
                        userAccount.accountNo = mAccountNo;
                        userAccount.balance = mBalance;
                        userAccount.currency = mCurrency;
                        userAccount.accountName = mAccountName;
                        userAccount.accountType = mAccountType;

                        AccountListFragment.this.accountList.add(userAccount);
                        SpinnerHelper spinnerHelper = new SpinnerHelper(i, String.valueOf(i), mAccountNo);
                        mList.add(spinnerHelper);
                    }
                }


            } catch (JSONException e) {
                e.printStackTrace();
            }
            AccountListFragment.this.adapter.notifyDataSetChanged();
        }
    }

    private class ResponseErrorListener implements ErrorListener {
        ResponseErrorListener() {
        }

        public void onErrorResponse(VolleyError error) {
            try {
                AccountListFragment.this.progress.dismiss();
                Log.d("AccountList", "onErrorResponse: " + error);
                AccountListFragment.this.netExceptionDialog(VolleyErrorHelper.getMessage(error, AccountListFragment.this.getActivity()), "Failed!", "Back", "Try Again");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        this.rootView = inflater.inflate(R.layout.fragment_account_list, container, false);

        this.coordinatorLayout = (CoordinatorLayout) getActivity().findViewById(R.id.coordinatorLayout);
        this.accountList = new ArrayList();

        AccInfo.setCurrency("bdt");
        AccInfo.setModule("all");

        this.listView = (ListView) this.rootView.findViewById(R.id.account_info_list_view);
        ((TextView) this.rootView.findViewById(R.id.accountHolder)).setText(SessionInfo.getUserName());
//        Log.fromDateSetListener("Gname", SessionInfo.getUserName());
        this.adapter = new AccountListAdapter(getActivity(), this.accountList);
        this.listView.setAdapter(this.adapter);
        this.listView.setOnItemClickListener(new AccItemClickListener());
        getAccountList();
        return this.rootView;
    }

    private void getAccountList() {
        this.progress = new ProgressDialog(getActivity());
        this.progress.setTitle("Loading");
        this.progress.setMessage("Wait while loading...");
        this.progress.setCancelable(false);
        String terminalIp = SecurityInfo.getTerminalIp();
        String browserInfo = SecurityInfo.getBrowserInfo();
        String TAG = "AccountList";
        String finalAccountListUrl = "";
        if (DevController.devMode)
            finalAccountListUrl = SecurityInfo.CBI_URL + "index.php?cus_id=" + "1001000931";
        else
            finalAccountListUrl = SecurityInfo.CBI_URL + "index.php?cus_id=" + SessionInfo.getCustomerID();
        Log.d("finalAccountListUrl", finalAccountListUrl);
        if (NetworkAvailability.isNetworkAvailable(getActivity())) {
            try {
                this.progress.show();
                RequestQueue mRequestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
                StringRequest strReq = new StringRequest(0, finalAccountListUrl, new AccountSummaryResponse(), new ResponseErrorListener());
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

    private void netExceptionDialog(String message, String title, String cancelBtn, String okBtn) {

        final Dialog netErrorDialog = new Dialog(getActivity());

        /**
         * no title
         */
        netErrorDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

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
        dialogText.setTextColor(getResources().getColor(R.color.common_google_signin_btn_text_dark));
        dialogTitle.setTextColor(SupportMenu.CATEGORY_MASK);
        okDialogButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                AccountListFragment.this.getAccountList();
                netErrorDialog.dismiss();
            }
        });
        cancelDialogButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                AccountListFragment.this.startActivity(new Intent(AccountListFragment.this.getActivity(), DashboardActivity.class));
                netErrorDialog.dismiss();
            }
        });
        netErrorDialog.show();
    }
}
