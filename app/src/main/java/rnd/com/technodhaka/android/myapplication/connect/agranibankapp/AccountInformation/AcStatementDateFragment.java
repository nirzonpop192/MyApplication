package rnd.com.technodhaka.android.myapplication.connect.agranibankapp.AccountInformation;

import android.app.Dialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Calendar;


public class AcStatementDateFragment extends Fragment implements OnClickListener {
    @Override
    public void onClick(View view) {

    }
    /* FragmentManager accInfoFragManager;
    String accountNo = "";
    private CoordinatorLayout coordinatorLayout;
    Dialog dialog;
    DisplayMetrics displayMetrics;
    String endDate = "";
    TextView endDateText;
    Calendar endDay;
    Fragment fragment;
    View rootView;
    String startDate = "";
    TextView startDateText;
    Calendar startDay;
    Calendar today;
    FragmentTransaction transaction;
    int width;

    class C03161 implements OnClickListener {
        C03161() {
        }

        public void onClick(View v) {
            DatePicker startDate = (DatePicker) AcStatementDateFragment.this.dialog.findViewById(R.id.ac_date_picker);
            int sDay = startDate.getDayOfMonth();
            Log.d("sDay", String.valueOf(sDay));
            int sMonth = startDate.getMonth();
            int sYear = startDate.getYear();
            AcStatementDateFragment.this.startDay = Calendar.getInstance();
            AcStatementDateFragment.this.startDay.set(Calendar.DAY_OF_MONTH, sDay);
            AcStatementDateFragment.this.startDay.set(Calendar.MONTH, sMonth);
            AcStatementDateFragment.this.startDay.set(Calendar.YEAR, sYear);
            long difference = AcStatementDateFragment.this.today.getTimeInMillis() - AcStatementDateFragment.this.startDay.getTimeInMillis();
            Log.d("difference", String.valueOf(difference));
            long days = difference / 86400000;
            Log.d("days", String.valueOf(days));
            AcStatementDateFragment.this.startDateText = (TextView) AcStatementDateFragment.this.getView().findViewById(R.id.startDateText);
            if (days > 90) {
                SnackbarUtil.generalMessage(AcStatementDateFragment.this.coordinatorLayout, "Statement of only last 3 months is allowed");
                AcStatementDateFragment.this.startDateText.setText("");
            } else if (days < 0) {
                SnackbarUtil.generalMessage(AcStatementDateFragment.this.coordinatorLayout, "Start date can not be greater than present date");
                AcStatementDateFragment.this.startDateText.setText("");
            } else {
                AcStatementDateFragment.this.startDateText.setText((sMonth + 1) + "/" + sDay + "/" + sYear);
            }
            AcStatementDateFragment.this.dialog.dismiss();
        }
    }

    class C03172 implements OnClickListener {
        C03172() {
        }

        public void onClick(View v) {
            DatePicker endDate = (DatePicker) AcStatementDateFragment.this.dialog.findViewById(R.id.ac_date_picker);
            int eDay = endDate.getDayOfMonth();
            int eMonth = endDate.getMonth();
            int eYear = endDate.getYear();
            AcStatementDateFragment.this.endDay = Calendar.getInstance();
            AcStatementDateFragment.this.endDay.set(Calendar.DAY_OF_MONTH, eDay);
            AcStatementDateFragment.this.endDay.set(Calendar.MONTH, eMonth);
            AcStatementDateFragment.this.endDay.set(Calendar.YEAR, eYear);
            long days = (AcStatementDateFragment.this.today.getTimeInMillis() - AcStatementDateFragment.this.endDay.getTimeInMillis()) / 86400000;
            AcStatementDateFragment.this.endDateText = (TextView) AcStatementDateFragment.this.getView().findViewById(R.id.endDateText);
            if (days > 90) {
                AcStatementDateFragment.this.endDateText.setText("");
                SnackbarUtil.generalMessage(AcStatementDateFragment.this.coordinatorLayout, "Statement of only last 3 months is allowed");
            } else if (days < 0) {
                AcStatementDateFragment.this.endDateText.setText("");
                SnackbarUtil.generalMessage(AcStatementDateFragment.this.coordinatorLayout, "End date can not be a grater than present date");
            } else {
                AcStatementDateFragment.this.endDateText.setText((eMonth + 1) + "/" + eDay + "/" + eYear);
            }
            AcStatementDateFragment.this.dialog.dismiss();
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.rootView = inflater.inflate(R.layout.fragment_ac_statement_date, container, false);
        this.coordinatorLayout = (CoordinatorLayout) getActivity().findViewById(R.id.coordinatorLayout);
        this.displayMetrics = this.rootView.getResources().getDisplayMetrics();
        this.width = this.displayMetrics.widthPixels;
        this.today = Calendar.getInstance();
        new PageTransitions(getActivity(), this.rootView).pageTransitionBottomToTop();
        Bundle bundle = getArguments();
        if (bundle != null) {
            this.accountNo = bundle.getString("AccountNo");
        }
        ((TextView) this.rootView.findViewById(R.id.accountNoTextView)).setText(this.accountNo);
        ((LinearLayout) this.rootView.findViewById(R.id.startDateLayout)).setOnClickListener(this);
        ((LinearLayout) this.rootView.findViewById(R.id.endDateLayout)).setOnClickListener(this);
        ((Button) this.rootView.findViewById(R.id.viewFullStatementButton)).setOnClickListener(this);
        return this.rootView;
    }

    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.viewFullStatementButton) {
            this.startDateText = (TextView) getView().findViewById(R.id.startDateText);
            this.endDateText = (TextView) getView().findViewById(R.id.endDateText);
            if (this.startDateText.equals(null) || this.endDateText.equals(null) || this.startDateText.getText().equals("") || this.endDateText.getText().equals("")) {
                SnackbarUtil.generalMessage(this.coordinatorLayout, "Start and End Date both must be selected !");
            } else if (this.endDay.getTimeInMillis() - this.startDay.getTimeInMillis() >= 0) {
                this.startDate = this.startDateText.getText().toString();
                this.endDate = this.endDateText.getText().toString();
                this.fragment = new AccountStatementFragment();
                Bundle bundle = new Bundle();
                bundle.putString("AccountNo", this.accountNo);
                bundle.putString("StartDate", this.startDate);
                bundle.putString("EndDate", this.endDate);
                this.fragment.setArguments(bundle);
                this.accInfoFragManager = getFragmentManager();
                this.transaction = this.accInfoFragManager.beginTransaction();
                this.transaction.replace(R.id.activity_account_info_layer, this.fragment);
                this.transaction.addToBackStack("AccountStatementFragment");
                this.transaction.commit();
            } else {
                SnackbarUtil.generalMessage(this.coordinatorLayout, "Start date can not be greater than end date");
            }
        } else if (id == R.id.startDateLayout) {
            this.dialog = new Dialog(getActivity());
            if (VERSION.SDK_INT >= 21) {
                this.dialog.setContentView(R.layout.date_picker_layout);
            } else {
                this.dialog.setContentView(R.layout.date_picker_layout_under_lolipop);
            }
            this.dialog.setTitle("Select Start Date:");
            ((TextView) this.dialog.findViewById(R.id.date_type_text)).setText("Start Date");
            ((Button) this.dialog.findViewById(R.id.cancelContactButton)).setOnClickListener(new C03161());
            this.dialog.show();
        } else if (id == R.id.endDateLayout) {
            this.dialog = new Dialog(getActivity());
            if (VERSION.SDK_INT >= 21) {
                this.dialog.setContentView(R.layout.date_picker_layout);
            } else {
                this.dialog.setContentView(R.layout.date_picker_layout_under_lolipop);
            }
            this.dialog.setTitle("Select End Date:");
            ((TextView) this.dialog.findViewById(R.id.date_type_text)).setText("End Date");
            ((Button) this.dialog.findViewById(R.id.cancelContactButton)).setOnClickListener(new C03172());
            this.dialog.show();
        }
    }*/
}
