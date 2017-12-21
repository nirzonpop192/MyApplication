package rnd.com.technodhaka.android.myapplication.connect.agranibankapp.TopUpFragments;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import rnd.com.technodhaka.android.myapplication.R;
import rnd.com.technodhaka.android.myapplication.connect.Connectivity.NetworkAvailability;
import rnd.com.technodhaka.android.myapplication.connect.Dialogs.ShowDialogs;
import rnd.com.technodhaka.android.myapplication.connect.Utility.SecurityInfo;
import rnd.com.technodhaka.android.myapplication.connect.agranibankapp.TopUpFragments.adapter.Beneficiary;
import rnd.com.technodhaka.android.myapplication.connect.agranibankapp.TopUpFragments.adapter.BeneficiaryListAdapter;


public class TopUpDeleteBeneficiaryFragment extends Fragment implements OnClickListener {
    private CoordinatorLayout coordinatorLayout;
    Fragment currentFragment;
    View rootView;
    FragmentManager topupFragManager;
    FragmentTransaction transaction;
    private ProgressDialog progressDialog;
    private ArrayList<Beneficiary> beneficiaryList;
    BeneficiaryListAdapter phoneTypeAdapter;
    ListView lv_beneficiary;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        this.rootView = inflater.inflate(R.layout.fragment_top_up_delete_beneficiary, container, false);

        coordinatorLayout = (CoordinatorLayout) getActivity().findViewById(R.id.coordinatorLayout);

         lv_beneficiary = (ListView) this.rootView.findViewById(R.id.lv_topUpBeneficiaryList);

        beneficiaryList= new ArrayList<>();
        LoadMobileOperator();


       // topUpStartButtonLayout.setOnClickListener(this);
/*
        LinearLayout topUpBeneciciaryLayout = (LinearLayout) this.rootView.findViewById(R.id.topUpBeneciciaryLayout);
        topUpBeneciciaryLayout.setOnClickListener(this);

        LinearLayout topUpBeneciciaryDeleteLayout = (LinearLayout) this.rootView.findViewById(R.id.topUpBeneciciaryDeleteLayout);
        topUpBeneciciaryDeleteLayout.setOnClickListener(this);

        new PageTransitions(getActivity(), topUpStartButtonLayout).pageTransitionBottomToTop();
        new PageTransitions(getActivity(), topUpBeneciciaryLayout).pageTransitionTopToBottom();
        new PageTransitions(getActivity(), topUpBeneciciaryDeleteLayout).pageTransitionBottomToTop();*/
        return this.rootView;
    }

    public void LoadMobileOperator() {
        this.progressDialog = new ProgressDialog(getActivity());
        this.progressDialog.setTitle("Loading");
        this.progressDialog.setMessage("Wait while loading...");
        this.progressDialog.setCancelable(false);
        String TAG = "LoadMobileOperatorList";
        String finalLoadMobileOperatorList = SecurityInfo.IB_URL + "index.php?" + "LoadBeneficiary";
        ;
        Log.d("finalOperatorUrl", finalLoadMobileOperatorList);
        if (NetworkAvailability.isNetworkAvailable(getActivity())) {
            try {
                this.progressDialog.show();
                RequestQueue mRequestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
                StringRequest strReq = new StringRequest(0, finalLoadMobileOperatorList, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("ResponseTAInTry", response);

                        try {

                            JSONObject jsonObject = new JSONObject(response);

                            JSONArray arrayResponse = jsonObject.getJSONArray("beneficiary");

                            for (int i = 0; i < arrayResponse.length(); i++) {
                                JSONObject obj = arrayResponse.getJSONObject(i);
                                String User_id = obj.getString("User_id");
                                String Tdate = obj.getString("Tdate");
                                String Utility_Acno = obj.getString("Utility_Acno");
                                String nick_name = obj.getString("nick_name");


                                beneficiaryList.add(new Beneficiary(User_id, Tdate, Utility_Acno, nick_name));

                            }
                            phoneTypeAdapter = new BeneficiaryListAdapter(getActivity(), beneficiaryList);
                            lv_beneficiary.setAdapter(phoneTypeAdapter);
                            progressDialog.dismiss();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        try {
                         progressDialog.dismiss();
                            Log.d("TopUpDelete", "onErrorResponse: " + error);
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


    public void onClick(View v) {

        /*switch (v.getId()){
            case R.id.topUpStartButtonLayout:
               currentFragment = new StartTopUpFragment();
               topupFragManager = getFragmentManager();
               transaction = this.topupFragManager.beginTransaction();
               transaction.replace(R.id.top_up_main_layout, this.currentFragment);
               transaction.addToBackStack("StartTopUpFragment");
               transaction.commit();
                break;
            case  R.id.topUpBeneciciaryLayout:
                currentFragment = new TopUpBeneficiaryFragment();
                topupFragManager = getFragmentManager();
                transaction = this.topupFragManager.beginTransaction();
                transaction.replace(R.id.top_up_main_layout, this.currentFragment);
                transaction.addToBackStack("TopUpBeneficiaryFragment");
                transaction.commit();
                break;
            case R.id.topUpBeneciciaryDeleteLayout:
                currentFragment = new TopUpBeneficiaryFragment();
                topupFragManager = getFragmentManager();
                transaction = this.topupFragManager.beginTransaction();
                transaction.replace(R.id.top_up_main_layout, this.currentFragment);
                transaction.addToBackStack("TopUpBeneficiaryDeleteFragment");
                transaction.commit();
                break;
        }*/

    }
}
