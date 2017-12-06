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

public class TopUpBeneficiaryFragment extends Fragment implements OnItemSelectedListener {
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
    /*ProgressDialog AddTopUpBeneficiary;
    EditText addTopUpBeneficiaryMobileNumber;
    Spinner addTopUpBeneficiaryMobileOperatorSpinner;
    EditText addTopUpBeneficiaryName;
    Spinner addTopUpBeneficiaryPhoneTypeSpinner;
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

    class C03871 implements OnClickListener {
        C03871() {
        }

        public void onClick(View v) {
            TopUpBeneficiaryFragment.this.selectedTopUpBeneficiaryMobileOperator = (TextView) TopUpBeneficiaryFragment.this.getView().findViewById(R.id.selectedTopUpBeneficiaryMobileOperator);
            TopUpBeneficiaryFragment.this.selectedTopUpBeneficiaryPhoneType = (TextView) TopUpBeneficiaryFragment.this.getView().findViewById(R.id.selectedTopUpBeneficiaryPhoneType);
            TopUpBeneficiaryFragment.this.addTopUpBeneficiaryName = (EditText) TopUpBeneficiaryFragment.this.getView().findViewById(R.id.addTopUpBeneficiaryName);
            TopUpBeneficiaryFragment.this.addTopUpBeneficiaryMobileNumber = (EditText) TopUpBeneficiaryFragment.this.getView().findViewById(R.id.addTopUpBeneficiaryMobileNumber);
            if (TopUpBeneficiaryFragment.this.selectedTopUpBeneficiaryMobileOperator == null || TopUpBeneficiaryFragment.this.selectedTopUpBeneficiaryMobileOperator.getText().equals("") || TopUpBeneficiaryFragment.this.selectedTopUpBeneficiaryPhoneType == null || TopUpBeneficiaryFragment.this.selectedTopUpBeneficiaryPhoneType.getText().equals("") || TopUpBeneficiaryFragment.this.addTopUpBeneficiaryName.getText().toString().replaceAll(" ", "").equals(null) || TopUpBeneficiaryFragment.this.addTopUpBeneficiaryName.getText().toString().replaceAll(" ", "").equals("") || TopUpBeneficiaryFragment.this.addTopUpBeneficiaryMobileNumber == null || TopUpBeneficiaryFragment.this.addTopUpBeneficiaryMobileNumber.getText().equals("")) {
                Toast.makeText(TopUpBeneficiaryFragment.this.getActivity(), "Please fill the necessary information ", 0).show();
                return;
            }
            TopUpBeneficiaryFragment.this.beneficiaryAccName = TopUpBeneficiaryFragment.this.addTopUpBeneficiaryName.getText().toString();
            TopUpBeneficiaryFragment.this.beneficiaryAccName = TopUpBeneficiaryFragment.this.beneficiaryAccName.replaceAll(" ", "@@@");
            TopUpBeneficiaryFragment.this.beneMobileNo = TopUpBeneficiaryFragment.this.addTopUpBeneficiaryMobileNumber.getText().toString();
            TopUpBeneficiaryFragment.this.beneMobileOperator = TopUpBeneficiaryFragment.this.selectedTopUpBeneficiaryMobileOperator.getText().toString();
            TopUpBeneficiaryFragment.this.benePhoneType = TopUpBeneficiaryFragment.this.selectedTopUpBeneficiaryPhoneType.getText().toString();
            TopUpBeneficiaryFragment.this.beneMobileOperatorId = (String) TopUpBeneficiaryFragment.this.operatorListHashMap.get(TopUpBeneficiaryFragment.this.beneMobileOperator);
            TopUpBeneficiaryFragment.this.benePhoneTypeId = (String) TopUpBeneficiaryFragment.this.phoneTypeListHashMap.get(TopUpBeneficiaryFragment.this.benePhoneType);
            TopUpBeneficiaryFragment.this.AddTopUpBeneficiary();
        }
    }

    class C03882 implements OnClickListener {
        C03882() {
        }

        public void onClick(View v) {
            TopUpBeneficiaryFragment.this.topupFragManager.popBackStack();
        }
    }

    class C06923 implements Listener<String> {
        C06923() {
        }

        public void onResponse(String response) {
            Log.d("ResponseTAInTry", response);
            String treamedResp = response.replaceAll("\\\\", "");
            String trimedResponse = treamedResp.substring(1, treamedResp.length() - 1);
            Log.d("trimedResponse", trimedResponse);
            try {
                Log.d("inTry1", "Entered");
                JSONArray arrayResponse = new JSONArray(trimedResponse);
                Log.d("inTry2", "Entered");
                for (int i = 0; i < arrayResponse.length(); i++) {
                    JSONObject obj = arrayResponse.getJSONObject(i);
                    String operatorName = obj.getString("OperatorName");
                    String operatorID = obj.getString("OperatorID");
                    TopUpBeneficiaryFragment.this.operatorList.add(operatorName);
                    TopUpBeneficiaryFragment.this.operatorListHashMap.put(operatorName, operatorID);
                }
                TopUpBeneficiaryFragment.this.progressLoadMobileOperator.dismiss();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    class C06934 implements ErrorListener {
        C06934() {
        }

        public void onErrorResponse(VolleyError error) {
            try {
                TopUpBeneficiaryFragment.this.progressLoadMobileOperator.dismiss();
                Log.d("LoadMobileOperatorList", "onErrorResponse: " + error);
                Toast.makeText(TopUpBeneficiaryFragment.this.getActivity(), VolleyErrorHelper.getMessage(error, TopUpBeneficiaryFragment.this.getActivity()), 1).show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    class C06945 implements Listener<String> {
        C06945() {
        }

        public void onResponse(String response) {
            Log.d("ResponseTAInTry", response);
            String treamedResp = response.replaceAll("\\\\", "");
            String trimedResponse = treamedResp.substring(1, treamedResp.length() - 1);
            Log.d("trimedResponse", trimedResponse);
            try {
                JSONArray arrayResponse = new JSONArray(trimedResponse);
                for (int i = 0; i < arrayResponse.length(); i++) {
                    JSONObject obj = arrayResponse.getJSONObject(i);
                    String ptName = obj.getString("PhoneTypeName");
                    String pTypeId = obj.getString("PhoneTypeId");
                    TopUpBeneficiaryFragment.this.pTypeList.add(ptName);
                    TopUpBeneficiaryFragment.this.phoneTypeListHashMap.put(ptName, pTypeId);
                }
                TopUpBeneficiaryFragment.this.progressLoadPhoneType.dismiss();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    class C06956 implements ErrorListener {
        C06956() {
        }

        public void onErrorResponse(VolleyError error) {
            try {
                TopUpBeneficiaryFragment.this.progressLoadPhoneType.dismiss();
                Log.d("LoadPhoneTypeList", "onErrorResponse: " + error);
                Toast.makeText(TopUpBeneficiaryFragment.this.getActivity(), VolleyErrorHelper.getMessage(error, TopUpBeneficiaryFragment.this.getActivity()), 1).show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    class C06967 implements Listener<String> {
        C06967() {
        }

        public void onResponse(String response) {
            response = response.replaceAll("\\\\", "");
            response = response.substring(1, response.length() - 1);
            Log.d("add_beneficiary", response);
            TopUpBeneficiaryFragment.this.AddTopUpBeneficiary.dismiss();
            TopUpBeneficiaryFragment.this.beneficiaryFailedResponse = response.toString();
            if (response.equals("Y")) {
                TopUpBeneficiaryFragment.this.beneficiarySuccessDialog();
            } else {
                TopUpBeneficiaryFragment.this.beneficiaryFailedDialog(TopUpBeneficiaryFragment.this.beneficiaryFailedResponse);
            }
        }
    }

    class C06978 implements ErrorListener {
        C06978() {
        }

        public void onErrorResponse(VolleyError error) {
            try {
                TopUpBeneficiaryFragment.this.AddTopUpBeneficiary.dismiss();
                Log.d("AddBeneficiary", "onErrorResponse: " + error);
                Toast.makeText(TopUpBeneficiaryFragment.this.getActivity(), VolleyErrorHelper.getMessage(error, TopUpBeneficiaryFragment.this.getActivity()), 1).show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.rootView = inflater.inflate(R.layout.fragment_top_up_beneficiary, container, false);
        this.coordinatorLayout = (CoordinatorLayout) getActivity().findViewById(R.id.coordinatorLayout);
        this.topupFragManager = getFragmentManager();
        this.operatorListHashMap = new HashMap();
        this.phoneTypeListHashMap = new HashMap();
        new PageTransitions(getActivity(), this.rootView).pageTransitionBottomToTop();
        this.addTopUpBeneficiaryMobileOperatorSpinner = (Spinner) this.rootView.findViewById(R.id.addTopUpBeneficiaryMobileOperatorSpinner);
        this.addTopUpBeneficiaryMobileOperatorSpinner.setOnItemSelectedListener(this);
        this.addTopUpBeneficiaryMobileOperatorSpinner.setPrompt("Select Operator");
        this.operatorList = new ArrayList();
        this.operatorList.add("Select Operator:");
        LoadMobileOperator();
        ArrayAdapter<String> dataAdapter = new ArrayAdapter(getActivity(), 17367048, this.operatorList);
        dataAdapter.setDropDownViewResource(17367049);
        this.addTopUpBeneficiaryMobileOperatorSpinner.setAdapter(dataAdapter);
        LoadPhoneType();
        this.addTopUpBeneficiaryPhoneTypeSpinner = (Spinner) this.rootView.findViewById(R.id.addTopUpBeneficiaryPhoneTypeSpinner);
        this.addTopUpBeneficiaryPhoneTypeSpinner.setOnItemSelectedListener(this);
        this.addTopUpBeneficiaryPhoneTypeSpinner.setPrompt("Select Phone Type");
        ArrayAdapter<String> phoneTypeAdapter = new ArrayAdapter(getActivity(), 17367048, this.pTypeList);
        dataAdapter.setDropDownViewResource(17367049);
        this.addTopUpBeneficiaryPhoneTypeSpinner.setAdapter(phoneTypeAdapter);
        Button topUpBeneficiaryCancel = (Button) this.rootView.findViewById(R.id.topUpBeneficiaryCancel);
        ((Button) this.rootView.findViewById(R.id.topUpBeneficiaryContinue)).setOnClickListener(new C03871());
        topUpBeneficiaryCancel.setOnClickListener(new C03882());
        return this.rootView;
    }

    public void LoadMobileOperator() {
        this.progressLoadMobileOperator = new ProgressDialog(getActivity());
        this.progressLoadMobileOperator.setTitle("Loading");
        this.progressLoadMobileOperator.setMessage("Wait while loading...");
        this.progressLoadMobileOperator.setCancelable(false);
        String TAG = "LoadMobileOperatorList";
        String finalLoadMobileOperatorList = ((SecurityInfo.baseUrl + "api/Account/LoadMobileOperator?") + "emailid=" + SecurityInfo.getUserEmail()).replaceAll(" ", "");
        Log.d("finalOperatorUrl", finalLoadMobileOperatorList);
        if (NetworkAvailability.isNetworkAvailable(getActivity())) {
            try {
                this.progressLoadMobileOperator.show();
                RequestQueue mRequestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
                StringRequest strReq = new StringRequest(0, finalLoadMobileOperatorList, new C06923(), new C06934());
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
        String finalLoadPhoneTypeList = ((SecurityInfo.baseUrl + "api/Account/LoadPhoneType?") + "emailid=" + SecurityInfo.getUserEmail()).replaceAll(" ", "");
        Log.d("finalLoadPhoneTypeList", finalLoadPhoneTypeList);
        if (NetworkAvailability.isNetworkAvailable(getActivity())) {
            try {
                this.progressLoadPhoneType.show();
                RequestQueue mRequestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
                StringRequest strReq = new StringRequest(0, finalLoadPhoneTypeList, new C06945(), new C06956());
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
        String finalTopUpBeneficiaryUrl = (SecurityInfo.baseUrl + "api/Account/AddTopupBeneficiary?") + "loginid=" + SecurityInfo.getUserEmail().replaceAll(" ", "") + "&email=" + SecurityInfo.getUserEmail().replaceAll(" ", "") + "&password=" + SecurityInfo.getUserPassword().replaceAll(" ", "") + "&beneName=" + this.beneficiaryAccName + "&beneNickName=na&mobileNo=" + this.beneMobileNo.replaceAll(" ", "") + "&mobileOperator=" + this.beneMobileOperatorId + "&phoneType=" + this.benePhoneTypeId.replaceAll(" ", "") + "&utilityType=" + this.beneUtilityType + "&terminalIp=" + SecurityInfo.getTerminalIp().replaceAll(" ", "") + "&sessionId=" + SessionInfo.sessionId.replaceAll(" ", "") + "&browserInfo=" + SecurityInfo.getBrowserInfo().replaceAll(" ", "");
        Log.d("topUpBeneficiaryUrl", finalTopUpBeneficiaryUrl);
        if (NetworkAvailability.isNetworkAvailable(getActivity())) {
            this.AddTopUpBeneficiary.show();
            try {
                RequestQueue mRequestQueue = Volley.newRequestQueue(getActivity());
                StringRequest strReq = new StringRequest(0, finalTopUpBeneficiaryUrl, new C06967(), new C06978());
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
        dialogText.setTextColor(-12303292);
        dialogTitle.setTextColor(-16711936);
        okDialogButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                TopUpBeneficiaryFragment.this.addTopUpBeneficiaryName.setText("");
                TopUpBeneficiaryFragment.this.addTopUpBeneficiaryMobileNumber.setText("");
                topupBeneDialog.dismiss();
            }
        });
        cancelDialogButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                TopUpBeneficiaryFragment.this.topupFragManager.popBackStack();
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
        dialogText.setTextColor(-12303292);
        dialogTitle.setTextColor(-16711936);
        okDialogButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                topupBeneDialog.dismiss();
            }
        });
        cancelDialogButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                TopUpBeneficiaryFragment.this.topupFragManager.popBackStack();
                topupBeneDialog.dismiss();
            }
        });
        topupBeneDialog.show();
    }

    public void onNothingSelected(AdapterView<?> adapterView) {
    }*/
}
