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
import rnd.com.technodhaka.android.myapplication.connect.agranibankapp.Activities.DashboardActivity;


public class AccountListFragment extends Fragment {
    FragmentManager accInfoFragManager;
    ArrayList<AccountInfo> accountList;
    //String accountModule;
    String accountNo;
    private AccountListAdapter adapter;
    String balance;
    private CoordinatorLayout coordinatorLayout;
    String currency;
    String accountName;
    String accountType;
    //String description;
    Fragment detailsFragment;
    FragmentTransaction fTransaction;
    FrameLayout frameLayout;
    private ListView listView;
    String module;
    ProgressDialog progress;
    View rootView;
    //String status;
    private Bundle bundle;
    private boolean devMode = true;

    class AccItemClickListener implements OnItemClickListener {
        AccItemClickListener() {
        }

        public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
            TextView moduleTextView = (TextView) view.findViewById(R.id.info_acc_module_text);
            String listAccNo = ((TextView) view.findViewById(R.id.info_acc_no_text)).getText().toString();
            String moduleText = moduleTextView.getText().toString();
            Log.d("listAccNo", listAccNo);
            Log.d("moduleText", moduleText);
           /* if (moduleText.equals("DP")) {
                AccountListFragment.this.detailsFragment = new DepositAccountFragment();

                Bundle bundle = new Bundle();
                bundle.putString("AccountNo", listAccNo);
                bundle.putString("Module", moduleText);

                AccountListFragment.this.detailsFragment.setArguments(bundle);
                AccountListFragment.this.accInfoFragManager = AccountListFragment.this.getFragmentManager();
                AccountListFragment.this.fTransaction = AccountListFragment.this.accInfoFragManager.beginTransaction();
                AccountListFragment.this.fTransaction.replace(R.id.activity_account_info_layer, AccountListFragment.this.detailsFragment);
                AccountListFragment.this.fTransaction.addToBackStack("DepositAccountFragment");
                AccountListFragment.this.fTransaction.commit();
                return;
            }*/
            AccountListFragment.this.detailsFragment = new LoanAccountFragment();
            bundle = new Bundle();
            bundle.putString("AccountNo", listAccNo);
            bundle.putString("Module", moduleText);
            AccountListFragment.this.detailsFragment.setArguments(bundle);
            AccountListFragment.this.accInfoFragManager = AccountListFragment.this.getFragmentManager();
            AccountListFragment.this.fTransaction = AccountListFragment.this.accInfoFragManager.beginTransaction();
            AccountListFragment.this.fTransaction.replace(R.id.activity_account_info_layer, AccountListFragment.this.detailsFragment);
            AccountListFragment.this.fTransaction.addToBackStack("LoanAccountFragment");
            AccountListFragment.this.fTransaction.commit();
        }
    }

    class AccountSummaryResponse implements Listener<String> {
        AccountSummaryResponse() {
        }

        public void onResponse(String response) {
            Log.d("ResponseInAcListTry", response);

//            String treamedResp = response.replaceAll("\\\\", "");
//            String trimedResponse = treamedResp.substring(1, treamedResp.length() - 1);
//            Log.d("treamedSesponse", trimedResponse);
            AccountListFragment.this.progress.dismiss();
            try {
                Log.d("inTry1", "Entered");
                JSONObject jsonObject = new JSONObject(response);
                JSONObject user = jsonObject.getJSONObject("user");
                Log.d("inTry2", "Entered");

                if (!jsonObject.isNull("customer_account")) {
                    JSONArray customerAccounts = jsonObject.getJSONArray("customer_account");
                    int size = customerAccounts.length();

                    for (int i = 0; i < size; i++) {
                        JSONObject customerAccount = customerAccounts.getJSONObject(i);


                        AccountInfo userAccount = new AccountInfo();
                        AccountListFragment.this.accountNo = customerAccount.getString("accountno");
                        AccountListFragment.this.balance = customerAccount.getString("Bal_tk");
                        AccountListFragment.this.currency = customerAccount.getString("curr_code");
                        AccountListFragment.this.accountName = customerAccount.getString("acname");
                        AccountListFragment.this.accountType = customerAccount.getString("acc_type");

                        Log.d("inTry currency", AccountListFragment.this.currency);
                        userAccount.accountNo = AccountListFragment.this.accountNo;
                        userAccount.balance = AccountListFragment.this.balance;
                        userAccount.currency = AccountListFragment.this.currency;
                        userAccount.accountName = AccountListFragment.this.accountName;
                        userAccount.accountType = AccountListFragment.this.accountType;

                        AccountListFragment.this.accountList.add(userAccount);

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
//        Log.d("Gname", SessionInfo.getUserName());
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
        if (devMode)
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
