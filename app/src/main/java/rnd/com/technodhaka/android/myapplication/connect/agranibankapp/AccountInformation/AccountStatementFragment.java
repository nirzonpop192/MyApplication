package rnd.com.technodhaka.android.myapplication.connect.agranibankapp.AccountInformation;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
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

import java.math.BigDecimal;
import java.util.ArrayList;


public class AccountStatementFragment extends Fragment {
   /* String mAccountNo = "";
    ArrayList<AccStatement> accountStatementList;
    private AccStatementAdapter adapter;
    private CoordinatorLayout coordinatorLayout;
    String email = "";
    String endDate = "";
    private ListView listView;
    String password = "";
    ProgressDialog progress;
    View rootView;
    String startDate = "";
    String userCode = "";

    class C03211 implements OnItemClickListener {
        C03211() {
        }

        public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        }
    }

    class C05972 implements Listener<String> {
        C05972() {
        }

        public void onResponse(String response) {
            String treamedResp = response.replaceAll("\\\\", "");
            String trimedResponse = treamedResp.substring(1, treamedResp.length() - 1);
            Log.fromDateSetListener("treamedSesponse", trimedResponse);
            Log.fromDateSetListener("ResponseInTry", trimedResponse);
            AccountStatementFragment.this.progress.dismiss();
            try {
                Log.fromDateSetListener("inTry1", "Entered");
                JSONArray arrayResponse = new JSONArray(trimedResponse);
                for (int i = 0; i < arrayResponse.length(); i++) {
                    JSONObject obj = arrayResponse.getJSONObject(i);
                    String tdate = obj.getString("date");
                    String sBalance = BigDecimal.valueOf(obj.getDouble("Balance")).toPlainString();
                    String sDeposit = BigDecimal.valueOf(obj.getDouble("Deposit")).toPlainString();
                    String sParticular = obj.getString("Particular");
                    String sWithdrawal = BigDecimal.valueOf(obj.getDouble("Withdrawal")).toPlainString();
                    Log.fromDateSetListener("balanceWithdrawal", sWithdrawal);
                    Log.fromDateSetListener("Ste", " hTdate " + tdate + " deposit " + sDeposit + " mBalance " + sBalance + " withdrawal " + sWithdrawal);
                    AccStatement userStatement = new AccStatement();
                    userStatement.transactionDate = tdate;
                    userStatement.particular = sParticular;
                    userStatement.deposit = sDeposit;
                    userStatement.mBalance = sBalance;
                    userStatement.withdrawal = sWithdrawal;
                    AccountStatementFragment.this.accountStatementList.add(userStatement);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            AccountStatementFragment.this.adapter.notifyDataSetChanged();
        }
    }

    class C05983 implements ErrorListener {
        C05983() {
        }

        public void onErrorResponse(VolleyError error) {
            try {
                AccountStatementFragment.this.progress.dismiss();
                Log.fromDateSetListener("AccountStatement", "onErrorResponse: " + error);
                SnackbarUtil.netErrorMessage(AccountStatementFragment.this.coordinatorLayout, VolleyErrorHelper.getMessage(error, AccountStatementFragment.this.getActivity()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.rootView = inflater.inflate(R.layout.fragment_account_statement, container, false);
        this.coordinatorLayout = (CoordinatorLayout) getActivity().findViewById(R.id.coordinatorLayout);
        this.accountStatementList = new ArrayList();
        new PageTransitions(getActivity(), this.rootView).pageTransitionBottomToTop();
        Bundle bundle = getArguments();
        if (bundle != null) {
            this.mAccountNo = bundle.getString("AccountNo");
            this.startDate = bundle.getString("StartDate");
            this.endDate = bundle.getString("EndDate");
        }
        this.password = SecurityInfo.getUserPassword();
        this.email = SecurityInfo.getUserEmail();
        this.userCode = SecurityInfo.getUserCode();
        TextView dateTextView = (TextView) this.rootView.findViewById(R.id.dateRangeStTextView);
        ((TextView) this.rootView.findViewById(R.id.accountNoStatementTextView)).setText(this.mAccountNo);
        dateTextView.setText("From " + this.startDate + " to " + this.endDate);
        this.listView = (ListView) this.rootView.findViewById(R.id.account_statement_list_view);
        this.adapter = new AccStatementAdapter(getActivity(), this.accountStatementList);
        this.listView.setAdapter(this.adapter);
        this.listView.setOnItemClickListener(new C03211());
        String terminalIp = SecurityInfo.getTerminalIp();
        String browserInfo = SecurityInfo.getBrowserInfo();
        String remarks = SecurityInfo.getRemarks();
        accStatement();
        return this.rootView;
    }

    public void accStatement() {
        this.progress = new ProgressDialog(getActivity());
        this.progress.setTitle("Loading");
        this.progress.setMessage("Wait while loading...");
        this.progress.setCancelable(false);
        String TAG = "AccountStatement";
        String finalAccountStatementUrl = ((SecurityInfo.baseUrl + "api/Account/AccountStatement?") + "userCode=" + this.userCode + "&accountno=" + this.mAccountNo + "&sdate=" + this.startDate + "&edate=" + this.endDate + "&email=" + this.email + "&password=" + this.password + "&terminalIp=" + SecurityInfo.terminalIp + "&browserInfo=" + SecurityInfo.browserInfo + "&remarks=" + SecurityInfo.remarks).replaceAll(" ", "");
        Log.fromDateSetListener("fAccountStatementUrl", finalAccountStatementUrl);
        if (NetworkAvailability.isNetworkAvailable(getActivity())) {
            try {
                this.progress.show();
                RequestQueue mRequestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
                StringRequest strReq = new StringRequest(0, finalAccountStatementUrl, new C05972(), new C05983());
                strReq.setRetryPolicy(new DefaultRetryPolicy(40000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                mRequestQueue.add(strReq);
                return;
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }
        ShowDialogs.noInternetDialog(getActivity());
    }*/
}
