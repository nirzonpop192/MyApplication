package rnd.com.technodhaka.android.myapplication.connect.agranibankapp.TopUpFragments;

import android.app.Dialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
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
import rnd.com.technodhaka.android.myapplication.connect.Connectivity.NetworkAvailability;
import rnd.com.technodhaka.android.myapplication.connect.Dialogs.ShowDialogs;
import rnd.com.technodhaka.android.myapplication.connect.Utility.SecurityInfo;
import rnd.com.technodhaka.android.myapplication.connect.Utility.SessionInfo;
import rnd.com.technodhaka.android.myapplication.connect.VolleyClasses.VolleyErrorHelper;

public class TopUpInternetBillFragment extends Fragment implements OnItemSelectedListener, OnClickListener {

    ProgressDialog AddTopUpBeneficiary;
    EditText addTopUpBeneficiaryMobileNumber;
    Spinner sp_MobileOperator;
    EditText addTopUpBeneficiaryName;
    Spinner sp_MobileType;
    String beneMobileNo;
    String beneMobileOperator;
    String beneMobileOperatorId;
    String benePhoneType;
    String benePhoneTypeId;
    String beneUtilityType = "MOBILE";
    String beneficiaryAccName;
    private String beneficiaryFailedResponse = "";
    private CoordinatorLayout coordinatorLayout;
    Fragment currentFragment;
    List<String> operatorList;
    HashMap<String, String> operatorListHashMap;
    List<String> pTypeList;
    HashMap<String, String> phoneTypeListHashMap;
    ProgressDialog progressLoadMobileOperator;
    ProgressDialog progressLoadPhoneType;
    View rootView;
    TextView selectedTopUpBeneficiaryMobileOperator;
    TextView selectedTopUpBeneficiaryPhoneType;
    FragmentManager topupFragManager;
    FragmentTransaction transaction;
    /**
     * operator Prefix such as 017
     *                         019     */
    String operatorPrefix;

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.topUpBeneficiaryContinue:
                selectedTopUpBeneficiaryMobileOperator = (TextView) TopUpInternetBillFragment.this.getView().findViewById(R.id.selectedTopUpBeneficiaryMobileOperator);
                selectedTopUpBeneficiaryPhoneType = (TextView) TopUpInternetBillFragment.this.getView().findViewById(R.id.selectedTopUpBeneficiaryPhoneType);
                addTopUpBeneficiaryName = (EditText) TopUpInternetBillFragment.this.getView().findViewById(R.id.addTopUpBeneficiaryName);
                addTopUpBeneficiaryMobileNumber = (EditText) TopUpInternetBillFragment.this.getView().findViewById(R.id.addTopUpBeneficiaryMobileNumber);
                if (TopUpInternetBillFragment.this.selectedTopUpBeneficiaryMobileOperator == null || TopUpInternetBillFragment.this.selectedTopUpBeneficiaryMobileOperator.getText().equals("") || TopUpInternetBillFragment.this.selectedTopUpBeneficiaryPhoneType == null || TopUpInternetBillFragment.this.selectedTopUpBeneficiaryPhoneType.getText().equals("") || TopUpInternetBillFragment.this.addTopUpBeneficiaryName.getText().toString().replaceAll(" ", "").equals(null) || TopUpInternetBillFragment.this.addTopUpBeneficiaryName.getText().toString().replaceAll(" ", "").equals("") || TopUpInternetBillFragment.this.addTopUpBeneficiaryMobileNumber == null || TopUpInternetBillFragment.this.addTopUpBeneficiaryMobileNumber.getText().equals("")) {
                    Toast.makeText(TopUpInternetBillFragment.this.getActivity(), "Please fill the necessary information ", Toast.LENGTH_SHORT).show();
                    return;
                }
                beneficiaryAccName = TopUpInternetBillFragment.this.addTopUpBeneficiaryName.getText().toString();
                beneficiaryAccName = TopUpInternetBillFragment.this.beneficiaryAccName.replaceAll(" ", "@@@");
                beneMobileNo = TopUpInternetBillFragment.this.addTopUpBeneficiaryMobileNumber.getText().toString();
                beneMobileOperator = TopUpInternetBillFragment.this.selectedTopUpBeneficiaryMobileOperator.getText().toString();
                benePhoneType = TopUpInternetBillFragment.this.selectedTopUpBeneficiaryPhoneType.getText().toString();
                beneMobileOperatorId = (String) TopUpInternetBillFragment.this.operatorListHashMap.get(TopUpInternetBillFragment.this.beneMobileOperator);
                benePhoneTypeId = (String) TopUpInternetBillFragment.this.phoneTypeListHashMap.get(TopUpInternetBillFragment.this.benePhoneType);
                AddTopUpBeneficiary();
                break;
            case R.id.topUpBeneficiaryCancel:
                TopUpInternetBillFragment.this.topupFragManager.popBackStack();
                break;
        }
    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_top_up_internet_bill, container, false);
        coordinatorLayout = (CoordinatorLayout) getActivity().findViewById(R.id.coordinatorLayout);
        topupFragManager = getFragmentManager();
        operatorListHashMap = new HashMap();
        phoneTypeListHashMap = new HashMap();
        new PageTransitions(getActivity(), this.rootView).pageTransitionBottomToTop();
        sp_MobileOperator =
                (Spinner) rootView.findViewById(R.id.addTopUpBeneficiaryMobileOperatorSpinner);

        sp_MobileOperator.setOnItemSelectedListener(this);
        sp_MobileOperator.setPrompt("Select Operator");
        operatorList = new ArrayList();
        operatorList.add("Select Operator:");
        LoadMobileOperator();
        ArrayAdapter<String> dataAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_expandable_list_item_1, this.operatorList);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_expandable_list_item_1
        );
        this.sp_MobileOperator.setAdapter(dataAdapter);
        LoadPhoneType();
        sp_MobileType = (Spinner) this.rootView.findViewById(R.id.addTopUpBeneficiaryPhoneTypeSpinner);

        sp_MobileType.setOnItemSelectedListener(this);
        sp_MobileType.setPrompt("Select Phone Type");
        ArrayAdapter<String> phoneTypeAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_expandable_list_item_1, this.pTypeList);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_expandable_list_item_1);
        sp_MobileType.setAdapter(phoneTypeAdapter);
        Button topUpBeneficiaryCancel = (Button) this.rootView.findViewById(R.id.topUpBeneficiaryCancel);
        ((Button) this.rootView.findViewById(R.id.topUpBeneficiaryContinue)).setOnClickListener(this);
        topUpBeneficiaryCancel.setOnClickListener(this);
        return this.rootView;
    }

    public void LoadMobileOperator() {
        this.progressLoadMobileOperator = new ProgressDialog(getActivity());
        this.progressLoadMobileOperator.setTitle("Loading");
        this.progressLoadMobileOperator.setMessage("Wait while loading...");
        this.progressLoadMobileOperator.setCancelable(false);
        String TAG = "LoadMobileOperatorList";
        String finalLoadMobileOperatorList = SecurityInfo.IB_URL + "index.php?" + "LoadPhoneType";
        ;
        Log.d("finalOperatorUrl", finalLoadMobileOperatorList);
        if (NetworkAvailability.isNetworkAvailable(getActivity())) {
            try {
                this.progressLoadMobileOperator.show();
                RequestQueue mRequestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
                StringRequest strReq = new StringRequest(0, finalLoadMobileOperatorList, new Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("ResponseTAInTry", response);
                        //String treamedResp = response.replaceAll("\\\\", "");
                        // String trimedResponse = treamedResp.substring(1, treamedResp.length() - 1);
                        //   Log.d("trimedResponse", trimedResponse);
                        try {

                            JSONObject jsonObject = new JSONObject(response);

                            JSONArray arrayResponse = jsonObject.getJSONArray("Operators");

                            for (int i = 0; i < arrayResponse.length(); i++) {
                                JSONObject obj = arrayResponse.getJSONObject(i);
                                String OperatorId = obj.getString("OperatorId");
                                String OperatorName = obj.getString("OperatorName");
                                String OperatorPrefix = obj.getString("OperatorPrefix");
                                operatorList.add(OperatorName);
                                operatorListHashMap.put(OperatorName, OperatorId);
                            }
                            TopUpInternetBillFragment.this.progressLoadMobileOperator.dismiss();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        try {
                            TopUpInternetBillFragment.this.progressLoadMobileOperator.dismiss();
                            Log.d("LoadMobileOperatorList", "onErrorResponse: " + error);
                            //        Toast.makeText(TopUpBeneficiaryFragment.this.getActivity(), VolleyErrorHelper.getMessage(error, TopUpBeneficiaryFragment.this.getActivity()), Toast.LENGTH_LONG).show();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
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

    public void LoadPhoneType() {
        this.progressLoadPhoneType = new ProgressDialog(getActivity());
        this.progressLoadPhoneType.setTitle("Loading");
        this.progressLoadPhoneType.setMessage("Wait while loading...");
        this.progressLoadPhoneType.setCancelable(false);
        this.pTypeList = new ArrayList();
        this.pTypeList.add("Select Phone Type:");
        String TAG = "LoadPhoneTypeList";
        String finalLoadPhoneTypeList = SecurityInfo.IB_URL + "index.php?" + "LoadPhoneType";
        Log.d("finalLoadPhoneTypeList", finalLoadPhoneTypeList);
        if (NetworkAvailability.isNetworkAvailable(getActivity())) {
            try {
                this.progressLoadPhoneType.show();
                RequestQueue mRequestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
                StringRequest strReq = new StringRequest(0, finalLoadPhoneTypeList, new Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("ResponseTAInTry", response);
                        //  String treamedResp = response.replaceAll("\\\\", "");
                        //String trimedResponse = treamedResp.substring(1, treamedResp.length() - 1);
                        //   Log.d("trimedResponse", trimedResponse);
                        try {
                            JSONObject arrayResponse = new JSONObject(response);

                            JSONObject dim = arrayResponse.getJSONObject("operator");
                            String OperatorId = dim.getString("OperatorId");
                            String OperatorName = dim.getString("OperatorName");
                            String OperatorPrefix = dim.getString("OperatorPrefix");
                            TopUpInternetBillFragment.this.pTypeList.add(OperatorName);
                            TopUpInternetBillFragment.this.phoneTypeListHashMap.put(OperatorName, OperatorId);
                            /*for (int i = 0; i < arrayResponse.length(); i++) {
                                JSONObject obj = arrayResponse.getJSONObject(i);
                                String ptName = obj.getString("PhoneTypeName");
                                String pTypeId = obj.getString("PhoneTypeId");

                            }*/
                            TopUpInternetBillFragment.this.progressLoadPhoneType.dismiss();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        try {
                            TopUpInternetBillFragment.this.progressLoadPhoneType.dismiss();
                            Log.d("LoadPhoneTypeList", "onErrorResponse: " + error);
                            Toast.makeText(TopUpInternetBillFragment.this.getActivity(),
                                    VolleyErrorHelper.getMessage(error, TopUpInternetBillFragment.this.getActivity()),
                                    Toast.LENGTH_LONG).show();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
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

    public void AddTopUpBeneficiary() {
        this.AddTopUpBeneficiary = new ProgressDialog(getActivity());
        this.AddTopUpBeneficiary.setTitle("Loading");
        this.AddTopUpBeneficiary.setMessage("Wait while loading...");
        this.AddTopUpBeneficiary.setCancelable(false);
        String TAG = "AddBeneficiary";
        String finalTopUpBeneficiaryUrl = (SecurityInfo.IB_URL + "api/Account/AddTopupBeneficiary?") + "loginid=" + SecurityInfo.getUserEmail().replaceAll(" ", "") + "&email=" + SecurityInfo.getUserEmail().replaceAll(" ", "") + "&password=" + SecurityInfo.getUserPassword().replaceAll(" ", "") + "&beneName=" + this.beneficiaryAccName + "&beneNickName=na&mobileNo=" + this.beneMobileNo.replaceAll(" ", "") + "&mobileOperator=" + this.beneMobileOperatorId + "&phoneType=" + this.benePhoneTypeId.replaceAll(" ", "") + "&utilityType=" + this.beneUtilityType + "&terminalIp=" + SecurityInfo.getTerminalIp().replaceAll(" ", "") + "&sessionId=" + SessionInfo.sessionId.replaceAll(" ", "") + "&browserInfo=" + SecurityInfo.getBrowserInfo().replaceAll(" ", "");
        Log.d("topUpBeneficiaryUrl", finalTopUpBeneficiaryUrl);
        if (NetworkAvailability.isNetworkAvailable(getActivity())) {
            this.AddTopUpBeneficiary.show();
            try {
                RequestQueue mRequestQueue = Volley.newRequestQueue(getActivity());
                StringRequest strReq = new StringRequest(0, finalTopUpBeneficiaryUrl, new Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        response = response.replaceAll("\\\\", "");
                        response = response.substring(1, response.length() - 1);
                        Log.d("add_beneficiary", response);
                        TopUpInternetBillFragment.this.AddTopUpBeneficiary.dismiss();
                        TopUpInternetBillFragment.this.beneficiaryFailedResponse = response.toString();
                        if (response.equals("Y")) {
                            TopUpInternetBillFragment.this.beneficiarySuccessDialog();
                        } else {
                            TopUpInternetBillFragment.this.beneficiaryFailedDialog(TopUpInternetBillFragment.this.beneficiaryFailedResponse);
                        }
                    }
                }, new ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        try {
                            TopUpInternetBillFragment.this.AddTopUpBeneficiary.dismiss();
                            Log.d("AddBeneficiary", "onErrorResponse: " + error);
                            Toast.makeText(TopUpInternetBillFragment.this.getActivity(), VolleyErrorHelper.getMessage(error, TopUpInternetBillFragment.this.getActivity()), Toast.LENGTH_LONG).show();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
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

    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()) {
            case R.id.addTopUpBeneficiaryMobileOperatorSpinner:
                String itemSource = parent.getItemAtPosition(position).toString();
                TextView selectedTopUpBeneficiaryMobileOperator = (TextView) getView().findViewById(R.id.selectedTopUpBeneficiaryMobileOperator);
                if (itemSource.equals("Select Operator:")) {
                    selectedTopUpBeneficiaryMobileOperator.setText("");
                    return;
                } else {
                    selectedTopUpBeneficiaryMobileOperator.setText(itemSource);
                    return;
                }
            case R.id.addTopUpBeneficiaryPhoneTypeSpinner:
                String itemSourcePT = parent.getItemAtPosition(position).toString();
                TextView selectedTopUpBeneficiaryPhoneType = (TextView) getView().findViewById(R.id.selectedTopUpBeneficiaryPhoneType);
                if (itemSourcePT.equals("Select Phone Type:")) {
                    selectedTopUpBeneficiaryPhoneType.setText("");
                    return;
                } else {
                    selectedTopUpBeneficiaryPhoneType.setText(itemSourcePT);
                    return;
                }
            default:
                return;
        }
    }

    private void beneficiarySuccessDialog() {
        final Dialog topupBeneDialog = new Dialog(getActivity());
        topupBeneDialog.requestWindowFeature(1);
        topupBeneDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        topupBeneDialog.setContentView(R.layout.net_exception_dialog);
        Button okDialogButton = (Button) topupBeneDialog.findViewById(R.id.dialogBtnPositive);
        Button cancelDialogButton = (Button) topupBeneDialog.findViewById(R.id.dialogBtnNegative);
        TextView dialogTitle = (TextView) topupBeneDialog.findViewById(R.id.ftSuccessTitle);
        TextView dialogText = (TextView) topupBeneDialog.findViewById(R.id.dialogText);
        okDialogButton.setText("Add Another");
        cancelDialogButton.setText("Back");
        dialogTitle.setText("Success!");
        dialogText.setText(this.beneMobileNo + " is added to your beneficiary list.");
        // dialogText.setTextColor(-12303292);
        // dialogTitle.setTextColor(-16711936);
        okDialogButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                TopUpInternetBillFragment.this.addTopUpBeneficiaryName.setText("");
                TopUpInternetBillFragment.this.addTopUpBeneficiaryMobileNumber.setText("");
                topupBeneDialog.dismiss();
            }
        });
        cancelDialogButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                TopUpInternetBillFragment.this.topupFragManager.popBackStack();
                topupBeneDialog.dismiss();
            }
        });
        topupBeneDialog.show();
    }

    private void beneficiaryFailedDialog(String response) {
        final Dialog topupBeneDialog = new Dialog(getActivity());
        topupBeneDialog.requestWindowFeature(1);
        topupBeneDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        topupBeneDialog.setContentView(R.layout.net_exception_dialog);
        Button okDialogButton = (Button) topupBeneDialog.findViewById(R.id.dialogBtnPositive);
        Button cancelDialogButton = (Button) topupBeneDialog.findViewById(R.id.dialogBtnNegative);
        TextView dialogTitle = (TextView) topupBeneDialog.findViewById(R.id.ftSuccessTitle);
        TextView dialogText = (TextView) topupBeneDialog.findViewById(R.id.dialogText);
        okDialogButton.setText("Retry");
        cancelDialogButton.setText("Back");
        dialogTitle.setText("Failed!");
        dialogText.setText(this.beneficiaryFailedResponse);
        //   dialogText.setTextColor(-12303292);
        //  dialogTitle.setTextColor(-16711936);
        okDialogButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                topupBeneDialog.dismiss();
            }
        });
        cancelDialogButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                TopUpInternetBillFragment.this.topupFragManager.popBackStack();
                topupBeneDialog.dismiss();
            }
        });
        topupBeneDialog.show();
    }

    public void onNothingSelected(AdapterView<?> adapterView) {
    }
}
