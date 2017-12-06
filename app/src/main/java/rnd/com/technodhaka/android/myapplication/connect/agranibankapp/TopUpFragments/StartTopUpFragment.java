package rnd.com.technodhaka.android.myapplication.connect.agranibankapp.TopUpFragments;

import android.app.Dialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.internal.view.SupportMenu;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

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
import java.util.HashMap;
import java.util.List;

import rnd.com.technodhaka.android.myapplication.R;
import rnd.com.technodhaka.android.myapplication.connect.Animation.PageTransitions;
import rnd.com.technodhaka.android.myapplication.connect.Utility.TopupInfo;
import rnd.com.technodhaka.android.myapplication.connect.VolleyClasses.VolleyErrorHelper;

public class StartTopUpFragment extends Fragment implements OnItemSelectedListener {
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
    /*  public static final String TAG = "ServiceRequest";
    List<String> accountList;
    HashMap<String, String> accountListHashMap;
    String accountNo;
    Spinner addTopUpAccountSpinner;
    EditText addTopUpAmount;
    Spinner addTopUpPhoneNumberSpinner;
    String amount;
    private CoordinatorLayout coordinatorLayout;
    Fragment currentFragment;
    private String errorMessage = "Connection timed out, please try again";
    RequestQueue mRequestQueue;
    String mobileNo;
    String mobileOperator;
    String operatorId;
    HashMap<String, String> operatorIdHashMap;
    List<String> phoneList;
    HashMap<String, String> phoneListHashMap;
    String phoneType;
    String phoneTypeId;
    HashMap<String, String> phoneTypeIdHashMap;
    Double postMax;
    Double postMin;
    Double preMax;
    Double preMin;
    ProgressDialog progress;
    ProgressDialog progressGetAccountList;
    ProgressDialog progressGetMobileOperatorById;
    ProgressDialog progressGetPhoneTypeById;
    ProgressDialog progressLoadMobileBeneficiary;
    ProgressDialog progressTopup;
    View rootView;
    TextView selectedTopUpAccount;
    TextView selectedTopUpMobileOperator;
    TextView selectedTopUpPhoneNumber;
    TextView selectedTopUpPhoneType;
    Snackbar snackbar;
    FragmentManager topupFragManager;
    TextView topupPostpaidLimit;
    TextView topupPrepaidLimit;
    FragmentTransaction transaction;

    class C03851 implements OnClickListener {
        C03851() {
        }

        public void onClick(View v) {
            StartTopUpFragment.this.selectedTopUpAccount = (TextView) StartTopUpFragment.this.getView().findViewById(R.id.selectedTopUpAccount);
            StartTopUpFragment.this.selectedTopUpPhoneNumber = (TextView) StartTopUpFragment.this.getView().findViewById(R.id.selectedTopUpPhoneNumber);
            StartTopUpFragment.this.selectedTopUpMobileOperator = (TextView) StartTopUpFragment.this.getView().findViewById(R.id.selectedTopUpMobileOperator);
            StartTopUpFragment.this.selectedTopUpPhoneType = (TextView) StartTopUpFragment.this.getView().findViewById(R.id.selectedTopUpPhoneType);
            StartTopUpFragment.this.addTopUpAmount = (EditText) StartTopUpFragment.this.getView().findViewById(R.id.addTopUpAmount);
            String topUpAmmount = StartTopUpFragment.this.addTopUpAmount.getText().toString();
            if (topUpAmmount.equals(null) || topUpAmmount.equals("") || StartTopUpFragment.this.selectedTopUpPhoneNumber == null || StartTopUpFragment.this.selectedTopUpPhoneNumber.getText().equals("")) {
                Toast.makeText(StartTopUpFragment.this.getActivity(), "Please input mandatory fields ", 0).show();
                return;
            }
            StartTopUpFragment.this.accountNo = StartTopUpFragment.this.selectedTopUpAccount.getText().toString();
            StartTopUpFragment.this.mobileOperator = StartTopUpFragment.this.selectedTopUpMobileOperator.getText().toString();
            StartTopUpFragment.this.mobileNo = StartTopUpFragment.this.selectedTopUpPhoneNumber.getText().toString();
            StartTopUpFragment.this.phoneType = StartTopUpFragment.this.selectedTopUpPhoneType.getText().toString().toLowerCase();
            StartTopUpFragment.this.amount = StartTopUpFragment.this.addTopUpAmount.getText().toString();
            if (StartTopUpFragment.this.phoneType.equals("prepaid")) {
                if (Double.parseDouble(StartTopUpFragment.this.amount) < StartTopUpFragment.this.preMin.doubleValue() || Double.parseDouble(StartTopUpFragment.this.amount) > StartTopUpFragment.this.preMax.doubleValue()) {
                    Toast.makeText(StartTopUpFragment.this.getActivity(), "Allowed top-up for prepaid is between " + StartTopUpFragment.this.preMin + " and " + StartTopUpFragment.this.preMax + " ", 0).show();
                } else {
                    StartTopUpFragment.this.confirmTopUpDialog();
                }
            }
            if (!StartTopUpFragment.this.phoneType.equals("postpaid")) {
                return;
            }
            if (Double.parseDouble(StartTopUpFragment.this.amount) < StartTopUpFragment.this.postMin.doubleValue() || Double.parseDouble(StartTopUpFragment.this.amount) > StartTopUpFragment.this.postMax.doubleValue()) {
                Toast.makeText(StartTopUpFragment.this.getActivity(), "Allowed top-up for postpaid is between " + StartTopUpFragment.this.postMin + " and " + StartTopUpFragment.this.postMax + " ", 0).show();
            } else {
                StartTopUpFragment.this.confirmTopUpDialog();
            }
        }
    }

    class C03862 implements OnClickListener {
        C03862() {
        }

        public void onClick(View v) {
            StartTopUpFragment.this.topupFragManager.popBackStack();
        }
    }

    class C06853 implements Listener<String> {
        C06853() {
        }

        public void onResponse(String response) {
            Log.d("SourceAccount", response);
            String treamedResp = response.replaceAll("\\\\", "");
            String trimedResponse = treamedResp.substring(1, treamedResp.length() - 1);
            Log.d("treamedSesponse", trimedResponse);
            try {
                JSONArray arrayResponse = new JSONArray(trimedResponse);
                for (int i = 0; i < arrayResponse.length(); i++) {
                    AccountInfo userAccount = new AccountInfo();
                    JSONObject obj = arrayResponse.getJSONObject(i);
                    String accountname = obj.getString("accountname");
                    String accountno = obj.getString("accountno");
                    StartTopUpFragment.this.accountList.add(accountname);
                    StartTopUpFragment.this.accountListHashMap.put(accountname, accountno);
                }
                StartTopUpFragment.this.progressGetAccountList.dismiss();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    class C06864 implements ErrorListener {
        C06864() {
        }

        public void onErrorResponse(VolleyError error) {
            try {
                StartTopUpFragment.this.progressGetAccountList.dismiss();
                Log.d("SourceAccountList", "onErrorResponse: " + error);
                Toast.makeText(StartTopUpFragment.this.getActivity(), VolleyErrorHelper.getMessage(error, StartTopUpFragment.this.getActivity()), 1).show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    class C06875 implements Listener<String> {
        C06875() {
        }

        public void onResponse(String response) {
            Log.d("MobileBeneRes", response);
            String treamedResp = response.replaceAll("\\\\", "");
            String trimedResponse = treamedResp.substring(1, treamedResp.length() - 1);
            Log.d("MobileBeneResteamed", trimedResponse);
            try {
                JSONArray arrayResponse = new JSONArray(trimedResponse);
                for (int i = 0; i < arrayResponse.length(); i++) {
                    JSONObject obj = arrayResponse.getJSONObject(i);
                    String beneficiaryPhone = obj.getString("beneficiary");
                    String utilityAc = obj.getString("Utility_Acno");
                    String operator_id = obj.getString("operator_id");
                    String phone_type = obj.getString("phone_type");
                    StartTopUpFragment.this.phoneList.add(beneficiaryPhone);
                    StartTopUpFragment.this.phoneListHashMap.put(beneficiaryPhone, utilityAc);
                    StartTopUpFragment.this.operatorIdHashMap.put(beneficiaryPhone, operator_id);
                    StartTopUpFragment.this.phoneTypeIdHashMap.put(beneficiaryPhone, phone_type);
                }
                StartTopUpFragment.this.progressLoadMobileBeneficiary.dismiss();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    class C06886 implements ErrorListener {
        C06886() {
        }

        public void onErrorResponse(VolleyError error) {
            try {
                StartTopUpFragment.this.progressLoadMobileBeneficiary.dismiss();
                Log.d("LoadPhoneList", "onErrorResponse: " + error);
                Toast.makeText(StartTopUpFragment.this.getActivity(), VolleyErrorHelper.getMessage(error, StartTopUpFragment.this.getActivity()), 1).show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    class C06897 implements Listener<String> {
        C06897() {
        }

        public void onResponse(String response) {
            Log.d("mobOperatorRes", response);
            String treamedResp = response.replaceAll("\\\\", "");
            String trimedResponse = treamedResp.substring(1, treamedResp.length() - 1);
            Log.d("mobOperatorResTreamed", trimedResponse);
            try {
                JSONArray arrayResponse = new JSONArray(trimedResponse);
                for (int i = 0; i < arrayResponse.length(); i++) {
                    String operatorName = arrayResponse.getJSONObject(i).getString("OperatorName");
                    TextView selectedTopUpMobileOperator = (TextView) StartTopUpFragment.this.getView().findViewById(R.id.selectedTopUpMobileOperator);
                    selectedTopUpMobileOperator.setVisibility(0);
                    selectedTopUpMobileOperator.setText(operatorName);
                }
                StartTopUpFragment.this.progressGetMobileOperatorById.dismiss();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    class C06908 implements ErrorListener {
        C06908() {
        }

        public void onErrorResponse(VolleyError error) {
            try {
                StartTopUpFragment.this.progressGetMobileOperatorById.dismiss();
                Log.d("LoadPhoneList", "onErrorResponse: " + error);
                Toast.makeText(StartTopUpFragment.this.getActivity(), VolleyErrorHelper.getMessage(error, StartTopUpFragment.this.getActivity()), 1).show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    class C06919 implements Listener<String> {
        C06919() {
        }

        public void onResponse(String response) {
            Log.d("PhoneTypeRes", response);
            String treamedResp = response.replaceAll("\\\\", "");
            String trimedResponse = treamedResp.substring(1, treamedResp.length() - 1);
            Log.d("PhoneTypeResTrimmed", trimedResponse);
            try {
                JSONArray arrayResponse = new JSONArray(trimedResponse);
                for (int i = 0; i < arrayResponse.length(); i++) {
                    String PhoneTypeName = arrayResponse.getJSONObject(i).getString("PhoneTypeName");
                    TextView selectedTopUpPhoneType = (TextView) StartTopUpFragment.this.getView().findViewById(R.id.selectedTopUpPhoneType);
                    selectedTopUpPhoneType.setVisibility(0);
                    selectedTopUpPhoneType.setText(PhoneTypeName);
                }
                StartTopUpFragment.this.progressGetPhoneTypeById.dismiss();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.rootView = inflater.inflate(R.layout.fragment_start_top_up, container, false);
        this.coordinatorLayout = (CoordinatorLayout) getActivity().findViewById(R.id.coordinatorLayout);
        this.topupFragManager = getFragmentManager();
        new PageTransitions(getActivity(), this.rootView).pageTransitionBottomToTop();
        this.topupPrepaidLimit = (TextView) this.rootView.findViewById(R.id.topupPrepaidLimit);
        this.topupPostpaidLimit = (TextView) this.rootView.findViewById(R.id.topupPostpaidLimit);
        if (TopupInfo.prepaidTopupMin_limit.doubleValue() == 0.0d || TopupInfo.prepaidTopupMax_limit.doubleValue() == 0.0d) {
            this.preMin = TopupInfo.prepaidTopupDefaultMin_limit;
            this.preMax = TopupInfo.prepaidTopupDefaultMax_limit;
            this.topupPrepaidLimit.setText("* Allowed top-up for prepaid is between " + this.preMin + " and " + this.preMax + " ");
        } else {
            this.preMin = TopupInfo.prepaidTopupMin_limit;
            this.preMax = TopupInfo.prepaidTopupMax_limit;
            this.topupPrepaidLimit.setText("* Allowed top-up for prepaid is between " + this.preMin + " and " + this.preMax + " ");
        }
        if (TopupInfo.postpaidTopupMin_limit.doubleValue() == 0.0d || TopupInfo.postpaidTopupMax_limit.doubleValue() == 0.0d) {
            this.postMin = TopupInfo.postpaidTopupDefaultMin_limit;
            this.postMax = TopupInfo.postpaidTopupDefaultMax_limit;
            this.topupPostpaidLimit.setText("* Allowed top-up for postpaid is between " + this.postMin + " and " + this.postMax + " ");
        } else {
            this.postMin = TopupInfo.postpaidTopupMin_limit;
            this.postMax = TopupInfo.postpaidTopupMax_limit;
            this.topupPostpaidLimit.setText("* Allowed top-up for postpaid is between " + this.postMin + " and " + this.postMax + " ");
        }
        this.accountListHashMap = new HashMap();
        this.phoneListHashMap = new HashMap();
        this.operatorIdHashMap = new HashMap();
        this.phoneTypeIdHashMap = new HashMap();
        this.addTopUpAccountSpinner = (Spinner) this.rootView.findViewById(R.id.addTopUpAccountSpinner);
        this.addTopUpAccountSpinner.setOnItemSelectedListener(this);
        this.addTopUpAccountSpinner.setPrompt("Select Account");
        GetAccountList();
        ArrayAdapter<String> dataAdapter = new ArrayAdapter(getActivity(), 17367048, this.accountList);
        dataAdapter.setDropDownViewResource(17367049);
        this.addTopUpAccountSpinner.setAdapter(dataAdapter);
        this.addTopUpPhoneNumberSpinner = (Spinner) this.rootView.findViewById(R.id.addTopUpPhoneNumberSpinner);
        this.addTopUpPhoneNumberSpinner.setOnItemSelectedListener(this);
        this.addTopUpPhoneNumberSpinner.setPrompt("Select Operator");
        LoadMobileBeneficiary();
        ArrayAdapter<String> phoneAdapter = new ArrayAdapter(getActivity(), 17367048, this.phoneList);
        dataAdapter.setDropDownViewResource(17367049);
        this.addTopUpPhoneNumberSpinner.setAdapter(phoneAdapter);
        Button topUpCancel = (Button) this.rootView.findViewById(R.id.topUpCancel);
        ((Button) this.rootView.findViewById(R.id.topUpContinue)).setOnClickListener(new C03851());
        topUpCancel.setOnClickListener(new C03862());
        return this.rootView;
    }

    public void GetAccountList() {
        this.progressGetAccountList = new ProgressDialog(getActivity());
        this.progressGetAccountList.setTitle("Loading");
        this.progressGetAccountList.setMessage("Wait while loading...");
        this.progressGetAccountList.setCancelable(false);
        this.accountList = new ArrayList();
        this.accountList.add("Select Account:");
        String TAG = "SourceAccountList";
        String finalSourceAccountList = ((SecurityInfo.baseUrl + "api/DropDown/SourceAccountList?") + "email=" + SecurityInfo.getUserEmail() + "&password=" + SecurityInfo.getUserPassword() + "&terminalIp=" + SecurityInfo.getTerminalIp() + "&browserInfo=" + SecurityInfo.getBrowserInfo() + "&remarks=na").replaceAll(" ", "");
        Log.d("finalSourceAccountList", finalSourceAccountList);
        if (NetworkAvailability.isNetworkAvailable(getActivity())) {
            this.progressGetAccountList.show();
            try {
                this.mRequestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
                StringRequest strReq = new StringRequest(0, finalSourceAccountList, new C06853(), new C06864());
                strReq.setRetryPolicy(new DefaultRetryPolicy(40000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                this.mRequestQueue.add(strReq);
                return;
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }
        ShowDialogs.noInternetDialog(getActivity());
    }

    public void LoadMobileBeneficiary() {
        this.progressLoadMobileBeneficiary = new ProgressDialog(getActivity());
        this.progressLoadMobileBeneficiary.setTitle("Loading");
        this.progressLoadMobileBeneficiary.setMessage("Wait while loading...");
        this.progressLoadMobileBeneficiary.setCancelable(false);
        this.phoneList = new ArrayList();
        this.phoneList.add("Select Phone Number:");
        String TAG = "LoadPhoneList";
        String finalLoadMobileBeneficiaryUrl = ((SecurityInfo.baseUrl + "api/Account/LoadMobileBeneficiary?") + "userid=" + SecurityInfo.getUserEmail() + "&onlyActive=Y").replaceAll(" ", "");
        Log.d("LoadMobileBeneficiary", finalLoadMobileBeneficiaryUrl);
        if (NetworkAvailability.isNetworkAvailable(getActivity())) {
            try {
                this.progressLoadMobileBeneficiary.show();
                this.mRequestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
                StringRequest strReq = new StringRequest(0, finalLoadMobileBeneficiaryUrl, new C06875(), new C06886());
                strReq.setRetryPolicy(new DefaultRetryPolicy(40000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                strReq.setTag("LoadPhoneList");
                this.mRequestQueue.add(strReq);
                return;
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }
        ShowDialogs.noInternetDialog(getActivity());
    }

    public void GetMobileOperatorById() {
        this.progressGetMobileOperatorById = new ProgressDialog(getActivity());
        this.progressGetMobileOperatorById.setTitle("Loading");
        this.progressGetMobileOperatorById.setMessage("Wait while loading...");
        this.progressGetMobileOperatorById.setCancelable(false);
        this.phoneList = new ArrayList();
        this.phoneList.add("Select Phone Number:");
        String TAG = "LoadPhoneList";
        String finalGetMobileOperatorByIdUrl = ((SecurityInfo.baseUrl + "api/Account/GetOperatorNameByOperatorIdForSMBL?") + "operatorId=" + this.operatorId).replaceAll(" ", "");
        Log.d("GetMobileOperatorById", finalGetMobileOperatorByIdUrl);
        if (NetworkAvailability.isNetworkAvailable(getActivity())) {
            try {
                this.progressGetMobileOperatorById.show();
                this.mRequestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
                StringRequest strReq = new StringRequest(0, finalGetMobileOperatorByIdUrl, new C06897(), new C06908());
                strReq.setRetryPolicy(new DefaultRetryPolicy(40000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                this.mRequestQueue.add(strReq);
                return;
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }
        ShowDialogs.noInternetDialog(getActivity());
    }

    public void GetPhoneTypeById() {
        this.progressGetPhoneTypeById = new ProgressDialog(getActivity());
        this.progressGetPhoneTypeById.setTitle("Loading");
        this.progressGetPhoneTypeById.setMessage("Wait while loading...");
        this.progressGetPhoneTypeById.setCancelable(false);
        this.phoneList = new ArrayList();
        this.phoneList.add("Select Phone Number:");
        String TAG = "LoadPhoneList";
        String finalGetPhoneTypeByIdUrl = ((SecurityInfo.baseUrl + "api/Account/GetPhoneTypeNameByTypeIdForSMBL?") + "phoneTypeId=" + this.phoneTypeId).replaceAll(" ", "");
        Log.d("finalGetPhoneTypeById", finalGetPhoneTypeByIdUrl);
        if (NetworkAvailability.isNetworkAvailable(getActivity())) {
            try {
                this.progressGetPhoneTypeById.show();
                this.mRequestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
                StringRequest strReq = new StringRequest(0, finalGetPhoneTypeByIdUrl, new C06919(), new ErrorListener() {
                    public void onErrorResponse(VolleyError error) {
                        try {
                            StartTopUpFragment.this.progressGetPhoneTypeById.dismiss();
                            Log.d("LoadPhoneList", "onErrorResponse: " + error);
                            Toast.makeText(StartTopUpFragment.this.getActivity(), VolleyErrorHelper.getMessage(error, StartTopUpFragment.this.getActivity()), 1).show();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
                strReq.setRetryPolicy(new DefaultRetryPolicy(40000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                this.mRequestQueue.add(strReq);
                return;
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }
        ShowDialogs.noInternetDialog(getActivity());
    }

    public void TopUpTransaction() {
        this.progressTopup = new ProgressDialog(getActivity());
        this.progressTopup.setTitle("Loading");
        this.progressTopup.setMessage("Wait while loading...");
        this.progressTopup.setCancelable(false);
        this.phoneList = new ArrayList();
        this.phoneList.add("Select Phone Number:");
        String TAG = "LoadPhoneList";
        String finalTopUpTransactionUrl = ((SecurityInfo.baseUrl + "api/Account/InsertTopUpTransactionForSMBL?") + "loginid=" + SecurityInfo.userCode + "&accountNo=" + this.accountNo + "&userid=" + SecurityInfo.getUserEmail() + "&password=" + SecurityInfo.getUserPassword() + "&mobileNo=" + this.mobileNo + "&mobileOperator=" + this.operatorId + "&phoneType=" + this.phoneTypeId + "&amount=" + this.amount + "&callingMethod=post&terminalIp=" + SecurityInfo.instanceId + "&sessionId=" + SessionInfo.sessionId + "&browserInfo=").replaceAll(" ", "");
        Log.d("topupUrl", finalTopUpTransactionUrl);
        if (NetworkAvailability.isNetworkAvailable(getActivity())) {
            this.progressTopup.show();
            try {
                this.mRequestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
                StringRequest strReq = new StringRequest(0, finalTopUpTransactionUrl, new Listener<String>() {
                    public void onResponse(String response) {
                        Log.d("topupRes", response);
                        String treamedResp = response.replaceAll("\\\\", "");
                        String trimedResponse = treamedResp.substring(1, treamedResp.length() - 1);
                        Log.d("topupResTrimed", trimedResponse);
                        StartTopUpFragment.this.progressTopup.dismiss();
                        if (trimedResponse.equals("Y")) {
                            final Dialog topupDialog = new Dialog(StartTopUpFragment.this.getActivity());
                            topupDialog.requestWindowFeature(1);
                            topupDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
                            topupDialog.setContentView(R.layout.net_exception_dialog);
                            Button okDialogButton = (Button) topupDialog.findViewById(R.id.dialogBtnPositive);
                            Button cancelDialogButton = (Button) topupDialog.findViewById(R.id.dialogBtnNegative);
                            TextView dialogTitle = (TextView) topupDialog.findViewById(R.id.ftSuccessTitle);
                            TextView dialogText = (TextView) topupDialog.findViewById(R.id.dialogText);
                            okDialogButton.setText("Another Topup");
                            cancelDialogButton.setText("Back");
                            dialogTitle.setText("Topup Successful!");
                            dialogText.setText("BDT" + StartTopUpFragment.this.amount + " has been recharged to " + StartTopUpFragment.this.mobileNo + "\nfrom " + StartTopUpFragment.this.accountNo);
                            dialogText.setTextColor(-12303292);
                            dialogTitle.setTextColor(-16711936);
                            okDialogButton.setOnClickListener(new OnClickListener() {
                                public void onClick(View v) {
                                    StartTopUpFragment.this.addTopUpAmount.setText("");
                                    topupDialog.dismiss();
                                }
                            });
                            cancelDialogButton.setOnClickListener(new OnClickListener() {
                                public void onClick(View v) {
                                    StartTopUpFragment.this.topupFragManager.popBackStack();
                                    topupDialog.dismiss();
                                }
                            });
                            topupDialog.show();
                            return;
                        }
                        topupDialog = new Dialog(StartTopUpFragment.this.getActivity());
                        topupDialog.requestWindowFeature(1);
                        topupDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
                        topupDialog.setContentView(R.layout.net_exception_dialog);
                        okDialogButton = (Button) topupDialog.findViewById(R.id.dialogBtnPositive);
                        cancelDialogButton = (Button) topupDialog.findViewById(R.id.dialogBtnNegative);
                        dialogTitle = (TextView) topupDialog.findViewById(R.id.ftSuccessTitle);
                        dialogText = (TextView) topupDialog.findViewById(R.id.dialogText);
                        okDialogButton.setText("Try again");
                        cancelDialogButton.setText("Back");
                        dialogTitle.setText("Sorry Topup Failed !");
                        dialogText.setText(response.toString());
                        dialogText.setTextColor(-12303292);
                        dialogTitle.setTextColor(SupportMenu.CATEGORY_MASK);
                        okDialogButton.setOnClickListener(new OnClickListener() {
                            public void onClick(View v) {
                                StartTopUpFragment.this.addTopUpAmount.setText("");
                                topupDialog.dismiss();
                            }
                        });
                        cancelDialogButton.setOnClickListener(new OnClickListener() {
                            public void onClick(View v) {
                                StartTopUpFragment.this.topupFragManager.popBackStack();
                                topupDialog.dismiss();
                            }
                        });
                        topupDialog.show();
                    }
                }, new ErrorListener() {
                    public void onErrorResponse(VolleyError error) {
                        try {
                            StartTopUpFragment.this.progressTopup.dismiss();
                            Log.d("LoadPhoneList", "onErrorResponse: " + error);
                            Toast.makeText(StartTopUpFragment.this.getActivity(), "Connection timed Out", 1).show();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
                strReq.setRetryPolicy(new DefaultRetryPolicy(40000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                this.mRequestQueue.add(strReq);
                return;
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }
        ShowDialogs.noInternetDialog(getActivity());
    }

    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()) {
            case R.id.addTopUpAccountSpinner:
                String itemSource = parent.getItemAtPosition(position).toString();
                TextView selectedTopUpAccount = (TextView) getView().findViewById(R.id.selectedTopUpAccount);
                if (itemSource.equals("Select Account:")) {
                    selectedTopUpAccount.setText("");
                    return;
                }
                String acNo = (String) this.accountListHashMap.get(itemSource);
                GeneralFundTransferInfo.setFromAccount(acNo);
                selectedTopUpAccount.setText(acNo);
                return;
            case R.id.addTopUpPhoneNumberSpinner:
                String itemSourcePT = parent.getItemAtPosition(position).toString();
                TextView selectedTopUpPhoneNumber = (TextView) getView().findViewById(R.id.selectedTopUpPhoneNumber);
                TextView selectedTopUpMobileOperator = (TextView) getView().findViewById(R.id.selectedTopUpMobileOperator);
                TextView selectedTopUpPhoneType = (TextView) getView().findViewById(R.id.selectedTopUpPhoneType);
                if (itemSourcePT.equals("Select Phone Number:")) {
                    selectedTopUpPhoneNumber.setText("");
                    selectedTopUpMobileOperator.setText("");
                    selectedTopUpPhoneType.setText("");
                    return;
                }
                String phoneNo = (String) this.phoneListHashMap.get(itemSourcePT);
                this.operatorId = (String) this.operatorIdHashMap.get(itemSourcePT);
                this.phoneTypeId = (String) this.phoneTypeIdHashMap.get(itemSourcePT);
                GetMobileOperatorById();
                GetPhoneTypeById();
                selectedTopUpPhoneNumber.setText(phoneNo);
                return;
            default:
                return;
        }
    }

    public void confirmTopUpDialog() {
        final Dialog confirmTopupDialog = new Dialog(getActivity());
        confirmTopupDialog.requestWindowFeature(1);
        confirmTopupDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        confirmTopupDialog.setContentView(R.layout.net_exception_dialog);
        Button okDialogButton = (Button) confirmTopupDialog.findViewById(R.id.dialogBtnPositive);
        Button cancelDialogButton = (Button) confirmTopupDialog.findViewById(R.id.dialogBtnNegative);
        TextView dialogTitle = (TextView) confirmTopupDialog.findViewById(R.id.ftSuccessTitle);
        TextView dialogText = (TextView) confirmTopupDialog.findViewById(R.id.dialogText);
        okDialogButton.setText("Proceed");
        cancelDialogButton.setText("Cancel");
        dialogTitle.setText("Confirm Topup?");
        dialogText.setText("BDT " + this.amount + " will be recharged to " + this.mobileNo + "\nfrom " + this.accountNo);
        dialogText.setTextColor(-12303292);
        dialogTitle.setTextColor(-16711936);
        okDialogButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                StartTopUpFragment.this.TopUpTransaction();
                confirmTopupDialog.dismiss();
            }
        });
        cancelDialogButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                confirmTopupDialog.dismiss();
            }
        });
        confirmTopupDialog.show();
    }

    public void onNothingSelected(AdapterView<?> adapterView) {
    }*/
}
