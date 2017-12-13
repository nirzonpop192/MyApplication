package rnd.com.technodhaka.android.myapplication.connect.agranibankapp.AccountInformation.AccountDetails;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.internal.view.SupportMenu;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import rnd.com.technodhaka.android.myapplication.R;
import rnd.com.technodhaka.android.myapplication.connect.Animation.PageTransitions;
import rnd.com.technodhaka.android.myapplication.connect.Connectivity.NetworkAvailability;
import rnd.com.technodhaka.android.myapplication.connect.Dialogs.ShowDialogs;
import rnd.com.technodhaka.android.myapplication.connect.Utility.SecurityInfo;
import rnd.com.technodhaka.android.myapplication.connect.VolleyClasses.VolleyErrorHelper;
import rnd.com.technodhaka.android.myapplication.connect.agranibankapp.AccountInformation.AccountDetails.view.AccountDetailsDatamodel;
import rnd.com.technodhaka.android.myapplication.connect.agranibankapp.AccountInformation.AccountDetails.view.AccountDetalisAdapter;
import rnd.com.technodhaka.android.myapplication.connect.agranibankapp.Activities.DashboardActivity;
import rnd.com.technodhaka.android.myapplication.connect.agranibankapp.View.SpinnerHelper;
import rnd.com.technodhaka.android.myapplication.connect.agranibankapp.View.SpinnerLoader;


public class AccountDetailsFragment extends Fragment {

    String TAG = "AccountDetailsFragment";
    FragmentManager accInfoFragManager;
    TextView accName;
    Spinner spAccNo;
    String mAccNo;
    //    TextView accOpenDate;
    TextView accStatus;
    Button btnSubmit;
    String accountNo = "";
    private List<SpinnerHelper> mList = new ArrayList<SpinnerHelper>();
    private List<CharSequence> nList = new ArrayList<CharSequence>();
    //    TextView amountHold;
//    TextView cashBalance;
    private CoordinatorLayout coordinatorLayout;
    Fragment detailsFragment;
    FragmentTransaction fTransaction;
    //    TextView netBalance;
    ProgressDialog progress;
    View rootView;
    //    TextView unclearedFunds;
    TextView userName;
    TextView tvFromDate, tvToDate;
    List<AccountDetailsDatamodel> mAccDetailsList = new ArrayList<AccountDetailsDatamodel>();
    //    Button viewStatementButton;
    private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
    private Calendar calendar = Calendar.getInstance();
    private String mTdate;
    private String mRemark;
    private String mDebit;
    private String mCredit;
    private String mAmtbal_tk;
    private ListView listView;
    private AccountDetalisAdapter adapter;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        if (bundle != null) {
            this.accountNo = bundle.getString("AccountNo");
            this.mList = bundle.getParcelableArrayList("DIM_ke");

        }
        if (mList != null) {
            for (int i = 0; i < mList.size(); i++) {

                nList.add(mList.get(i).getDatabaseValue());
            }
        }

        rootView = inflater.inflate(R.layout.fragment_deposit_account, container, false);
        coordinatorLayout = (CoordinatorLayout) getActivity().findViewById(R.id.coordinatorLayout);
        userName = (TextView) this.rootView.findViewById(R.id.userName);
        spAccNo = (Spinner) this.rootView.findViewById(R.id.spAccNumber);
        tvFromDate = (TextView) this.rootView.findViewById(R.id.tv_acc_from_date);
        tvToDate = (TextView) this.rootView.findViewById(R.id.tv_acc_To_date);
        accStatus = (TextView) this.rootView.findViewById(R.id.accStatus);
        btnSubmit = (Button) this.rootView.findViewById(R.id.btnSubmit);
//        accOpenDate = (TextView) this.rootView.findViewById(R.id.accOpenDate);
        accName = (TextView) this.rootView.findViewById(R.id.accName);
//        cashBalance = (TextView) this.rootView.findViewById(R.id.cashBalance);
//        amountHold = (TextView) this.rootView.findViewById(R.id.amountOnHoldTv);
//        unclearedFunds = (TextView) this.rootView.findViewById(R.id.unclearedFundTv);
//        netBalance = (TextView) this.rootView.findViewById(R.id.netBalanceTv);
        new PageTransitions(getActivity(), this.rootView).pageTransitionBottomToTop();
//        getAccountDetails();
        loadAcountNo();
        btnSubmit.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                getAccountDetails();
            }
        });

        this.listView = (ListView) this.rootView.findViewById(R.id.lv_acc_details_list);
//        ((TextView) this.rootView.findViewById(R.id.accountHolder)).setText(SessionInfo.getUserName());
//        Log.fromDateSetListener("Gname", SessionInfo.getUserName());
        this.adapter = new AccountDetalisAdapter(getActivity(), this.mAccDetailsList);
        this.listView.setAdapter(this.adapter);
        this.listView.setOnItemClickListener(new AccItemClickListener());
        tvFromDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDate(v);
            }
        });
        tvToDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDate(v);
            }
        });
//        this.viewStatementButton = (Button) this.rootView.findViewById(R.id.viewStatementButton);
//        this.viewStatementButton.setOnClickListener(this);
        return this.rootView;
    }

    class AccItemClickListener implements AdapterView.OnItemClickListener {
        AccItemClickListener() {
        }

        public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

        }
    }

    /**
     * DatePicker code Start
     **/
    public void updateFromDate() {
        updateTextColor(tvFromDate);
        tvFromDate.setText(format.format(calendar.getTime()));
    }

    public void updateToDate() {
        updateTextColor(tvToDate);
        tvToDate.setText(format.format(calendar.getTime()));
    }

    public void setDate(View view) {
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        if (view.getId() == R.id.tv_acc_from_date)
            new DatePickerDialog(getActivity(), fromDateSetListener,
                    year, month, dayOfMonth).show();
        else
            new DatePickerDialog(getActivity(), toDateSetListener,
                    year, month, dayOfMonth).show();
    }

    DatePickerDialog.OnDateSetListener fromDateSetListener = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            setCalender(year, monthOfYear, dayOfMonth);
            updateFromDate();
        }
    };

    DatePickerDialog.OnDateSetListener toDateSetListener = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            setCalender(year, monthOfYear, dayOfMonth);
            updateToDate();
        }
    };

    private void updateTextColor(TextView view) {
        view.setTextColor(Color.BLACK);
    }

    private void setCalender(int year, int monthOfYear, int dayOfMonth) {
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, monthOfYear);
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
    }

    private void loadAcountNo() {


        SpinnerLoader.loadSpinner(getActivity(), spAccNo, nList, "1", "23");

        spAccNo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mAccNo = spAccNo.getSelectedItem().toString();
//                Log.d("DIM","str"+str);
//                strGroup = ((SpinnerHelper) spGroup.getSelectedItem()).getValue();
//                idGroup = ((SpinnerHelper) spGroup.getSelectedItem()).getId();
//
//                if (idGroup.length() > 2)
//                    loadActiveStatus();


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    } // end Load Spinner

    private void getAccountDetails() {
        this.progress = new ProgressDialog(getActivity());
        this.progress.setTitle("Loading");
        this.progress.setMessage("Wait while loading...");
        this.progress.setCancelable(false);
        String terminalIp = SecurityInfo.getTerminalIp();
        String browserInfo = SecurityInfo.getBrowserInfo();
        String TAG = "accountInfoDetails_Volley";
        String finalAccountInfoDetailsUrlTreamed = (SecurityInfo.CBI_URL + "index.php?") + "acc_no=" + mAccNo;
        Log.d("InfoDetailsUrlTreamed", finalAccountInfoDetailsUrlTreamed);
        Log.d("mAccountNo", this.accountNo);
        if (NetworkAvailability.isNetworkAvailable(getActivity())) {
            try {
                this.progress.show();
                RequestQueue mRequestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
                StringRequest strReq = new StringRequest(Request.Method.GET, finalAccountInfoDetailsUrlTreamed, new DipositeResponseListener(), new C06002());
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

//    public void onClick(View v) {
//        if (v.getId() == R.id.viewStatementButton) {
//            this.detailsFragment = new AcStatementDateFragment();
//            Bundle bundle = new Bundle();
//            bundle.putString("AccountNo", this.accountNo);
//            this.detailsFragment.setArguments(bundle);
//            this.accInfoFragManager = getFragmentManager();
//            this.fTransaction = this.accInfoFragManager.beginTransaction();
//            this.fTransaction.replace(R.id.activity_account_info_layer, this.detailsFragment);
//            this.fTransaction.addToBackStack("AcStatementDateFragment");
//            this.fTransaction.commit();
//        }
//    }

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
//                AccountDetailsFragment.this.getAccountDetails();
                netErrorDialog.dismiss();
            }
        });
        cancelDialogButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                AccountDetailsFragment.this.startActivity(new Intent(AccountDetailsFragment.this.getActivity(), DashboardActivity.class));
                netErrorDialog.dismiss();
            }
        });
        netErrorDialog.show();
    }

    class DipositeResponseListener implements Listener<String> {
        DipositeResponseListener() {
        }

        public void onResponse(String response) {
//            response = response.replaceAll("\\\\", "");
//            response = response.substring(1, response.length() - 1);
            Log.d("ResponseDp", response);
            try {
                Log.d("Region", "Entered");
                AccountDetailsFragment.this.progress.dismiss();

                Log.d("inTry1", "Entered");
                JSONObject jsonObject = new JSONObject(response);
                JSONObject user = jsonObject.getJSONObject("user");
//                Log.d("inTry2", "Entered");

                if (!jsonObject.isNull("account_details")) {
                    JSONArray customerAccounts = jsonObject.getJSONArray("account_details");
                    int size = customerAccounts.length();
//                    mList = new ArrayList<SpinnerHelper>();
                    for (int i = 0; i < size; i++) {
                        JSONObject customerAccount = customerAccounts.getJSONObject(i);


                        mTdate = customerAccount.getString("tdate").substring(0,10);
                        mRemark = customerAccount.getString("remark");
                        mDebit = customerAccount.getString("DEBIT");
                        mCredit = customerAccount.getString("CREDIT");
                        mAmtbal_tk = customerAccount.getString("amtbal_tk");

                        AccountDetailsDatamodel userAccount = new AccountDetailsDatamodel(mTdate
                                , mRemark, mDebit, mCredit, mAmtbal_tk);

                        mAccDetailsList.add(userAccount);
//                        Log.d("inTry mCurrency", mCurrency);
//                        userAccount.accountNo = mAccountNo;
//                        userAccount.balance = mBalance;
//                        userAccount.currency = mCurrency;
//                        userAccount.accountName = mAccountName;
//                        userAccount.accountType = mAccountType;

//                        AccountListFragment.this.accountList.add(userAccount);
//                        SpinnerHelper spinnerHelper = new SpinnerHelper(i, String.valueOf(i), mAccountNo);
//                        mList.add(spinnerHelper);
                    }
                }
//                JSONObject jSONObject = new JSONObject(response);
//                String moduleType = jSONObject.getString("Module");
//                String minBalance = BigDecimal.valueOf(jSONObject.getDouble("MinBalance")).toPlainString();
//                String accountNumber = jSONObject.getString("Accountno");
//                String acName = jSONObject.getString("Acname");
//                String openDate = jSONObject.getString("OpenDate");
//                openDate = new SimpleDateFormat("dd-MM-yyyy").format(new Date(Long.parseLong(openDate.substring(6, openDate.length() - 2))));
//                String balanceTK = BigDecimal.valueOf(jSONObject.getDouble("BalanceTK")).toPlainString();
//                String netAvailableDrawableAmt = BigDecimal.valueOf(jSONObject.getDouble("DrawableAmt")).toPlainString();
//                String status = jSONObject.getString("Status");
//                String clearingTK = BigDecimal.valueOf(jSONObject.getDouble("ClearingTK")).toPlainString();
//                String amountHoldBlockedAmt = jSONObject.getString("BlockedAmt");
//                String statementAllowed = jSONObject.getString("StatementAllowed");
//                Log.d("ClearingTk", clearingTK);
//                Log.d("accountname", acName);
//                if (statementAllowed.equals("Y")) {
//                    AccountDetailsFragment.this.viewStatementButton.setVisibility(View.VISIBLE);
//                }
//                AccountDetailsFragment.this.userName.setText(acName);
//                Log.d("Region", "Entered");
//                Log.d("userNamelove", acName);
////                AccountDetailsFragment.this.spAccNo.setText(accountNumber);
//                AccountDetailsFragment.this.accStatus.setText(status);
////                AccountDetailsFragment.this.accOpenDate.setText(openDate);
//                AccountDetailsFragment.this.accName.setText(acName);
////                AccountDetailsFragment.this.cashBalance.setText(balanceTK);
////                AccountDetailsFragment.this.amountHold.setText(amountHoldBlockedAmt);
////                AccountDetailsFragment.this.netBalance.setText(netAvailableDrawableAmt);
////                AccountDetailsFragment.this.unclearedFunds.setText(clearingTK);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            AccountDetailsFragment.this.adapter.notifyDataSetChanged();
        }
    }

    class C06002 implements ErrorListener {
        C06002() {
        }

        public void onErrorResponse(VolleyError error) {
            try {
                AccountDetailsFragment.this.progress.dismiss();

                AccountDetailsFragment.this.netExceptionDialog(VolleyErrorHelper.getMessage(error, AccountDetailsFragment.this.getActivity()), "Failed!", "Back", "Try Again");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
