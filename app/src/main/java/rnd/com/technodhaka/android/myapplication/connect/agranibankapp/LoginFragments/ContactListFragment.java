package rnd.com.technodhaka.android.myapplication.connect.agranibankapp.LoginFragments;

import android.app.Dialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;



import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import rnd.com.technodhaka.android.myapplication.R;
import rnd.com.technodhaka.android.myapplication.connect.agranibankapp.LoginFragments.adapter.BankContactInfo;
import rnd.com.technodhaka.android.myapplication.connect.agranibankapp.LoginFragments.adapter.ContactListAdapter;

public class ContactListFragment extends Fragment {
    private static final int REQUEST_PHONE_CALL = 1;
    String TAG = "Call Permission";
    private ContactListAdapter adapter;
    ArrayList<BankContactInfo> contactList;
    String contact_address;
    String contact_branch_name;
    String contact_email;
    String contact_phone;
    Fragment detailsFragment;
    Dialog dialog;
    Dialog emailDialog;
    private ListView listView;
    FragmentManager manager;
    String response = "[{\"id\":\"1\",\"name\":\"Head Office\",\"address\":\"Road # 2, Bir Uttam M. A. Rob Sarak, Shimanto Square, Dhanmondi,\nDhaka-1205, Bangladesh\",\"email\":\"information.desk@shimantobank.com\",\"phone\":\"+8809612999000\"}]";
    View rootView;
    FragmentTransaction transaction;

    class C03731 implements OnItemClickListener {
        C03731() {
        }

        public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
            TextView contact_branch_name_text = (TextView) view.findViewById(R.id.contact_branch_name);
            TextView contact_address_text = (TextView) view.findViewById(R.id.contact_address);
            TextView contact_email_text = (TextView) view.findViewById(R.id.contact_email);
            TextView contact_phone_text = (TextView) view.findViewById(R.id.contact_phone);
            ContactListFragment.this.contact_branch_name = contact_branch_name_text.getText().toString();
            ContactListFragment.this.contact_address = contact_address_text.getText().toString();
            ContactListFragment.this.contact_email = contact_email_text.getText().toString();
            ContactListFragment.this.contact_phone = contact_phone_text.getText().toString();
            ContactListFragment.this.ContactDialog();
        }
    }

    class C03742 implements OnClickListener {
        C03742() {
        }

        public void onClick(View v) {
            try {
                if (VERSION.SDK_INT < 23) {
                    ContactListFragment.this.call();
                } else {
                    ContactListFragment.this.checkPermissions();
                }
            } catch (Exception e) {
                Toast.makeText(ContactListFragment.this.getActivity(), "Can not able to make a Phone Call", 0).show();
            }
            ContactListFragment.this.dialog.dismiss();
        }
    }

    class C03753 implements OnClickListener {
        C03753() {
        }

        public void onClick(View v) {
            Intent emailIntent = new Intent("android.intent.action.SEND");
            emailIntent.setData(Uri.parse("mailto:"));
            emailIntent.setType("text/plain");
            emailIntent.putExtra("android.intent.extra.EMAIL", new String[]{ContactListFragment.this.contact_email});
            try {
                ContactListFragment.this.startActivity(Intent.createChooser(emailIntent, "Send mail..."));
            } catch (ActivityNotFoundException e) {
                Toast.makeText(ContactListFragment.this.getActivity(), "There is no email client installed.", 0).show();
            }
            ContactListFragment.this.dialog.dismiss();
        }
    }

    class C03764 implements OnClickListener {
        C03764() {
        }

        public void onClick(View v) {
            ContactListFragment.this.dialog.dismiss();
        }
    }

    class C03775 implements OnClickListener {
        C03775() {
        }

        public void onClick(View v) {
            EditText email_subject_text = (EditText) ContactListFragment.this.emailDialog.findViewById(R.id.email_subject);
            EditText email_body_text = (EditText) ContactListFragment.this.emailDialog.findViewById(R.id.email_body);
            Intent emailIntent = new Intent("android.intent.action.SEND");
            emailIntent.setData(Uri.parse("mailto:"));
            emailIntent.setType("text/plain");
            emailIntent.putExtra("android.intent.extra.EMAIL", new String[]{ContactListFragment.this.contact_email});
            emailIntent.putExtra("android.intent.extra.SUBJECT", email_subject_text.getText().toString());
            emailIntent.putExtra("android.intent.extra.TEXT", email_body_text.getText().toString());
            try {
                ContactListFragment.this.startActivity(Intent.createChooser(emailIntent, "Send mail..."));
            } catch (ActivityNotFoundException e) {
                Toast.makeText(ContactListFragment.this.getActivity(), "There is no email client installed.", 0).show();
            }
            ContactListFragment.this.emailDialog.dismiss();
        }
    }

    class C03786 implements OnClickListener {
        C03786() {
        }

        public void onClick(View v) {
            ContactListFragment.this.emailDialog.dismiss();
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.rootView = inflater.inflate(R.layout.fragment_contact_list, container, false);
        ViewBranchList();
        return this.rootView;
    }

    public void ViewBranchList() {
        this.contactList = new ArrayList();
        this.listView = (ListView) this.rootView.findViewById(R.id.branch_contact_list_view);
        ((TextView) this.rootView.findViewById(R.id.headerTitle)).setText("Branch and Contacts");
        this.adapter = new ContactListAdapter(getActivity(), this.contactList);
        this.listView.setAdapter(this.adapter);
        this.listView.setOnItemClickListener(new C03731());
        Log.d("inTry3", "Entered 3");
        String TAG = "BookList";
        Log.d("finalMovieListUrl", "http://design.ismithpasha.com/api/" + "contacts.php");
        try {
            Log.d("ResponseInTry", this.response);
            String treamedResp = this.response.replaceAll("\\\\", "");
            Log.d("treamedSesponse", treamedResp.substring(1, treamedResp.length() - 1));
            try {
                Log.d("inTry12", "Entered");
                JSONArray arrayResponse = new JSONArray(treamedResp);
                Log.d("inTry2", "Entered");
                for (int i = 0; i < arrayResponse.length(); i++) {
                    BankContactInfo bankContackInfo = new BankContactInfo();
                    Log.d("f1", "E1");
                    JSONObject obj = arrayResponse.getJSONObject(i);
                    Log.d("f2", "E2");
                    String bName = obj.getString("name");
                    String bAddress = obj.getString("address");
                    String phoneNo = obj.getString("phone");
                    String emailId = obj.getString("email");
                    bankContackInfo.setName(bName);
                    bankContackInfo.setAddress(bAddress);
                    bankContackInfo.setEmail(emailId);
                    bankContackInfo.setPhone(phoneNo);
                    this.contactList.add(bankContackInfo);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            this.adapter.notifyDataSetChanged();
        } catch (Exception e2) {
            e2.printStackTrace();
            Log.d("Exception 1", e2.toString());
        }
    }

    public void ContactDialog() {
        this.dialog = new Dialog(getActivity());
        this.dialog.setContentView(R.layout.contact_dialog);
        TextView contact_address_text = (TextView) this.dialog.findViewById(R.id.contact_address);
        TextView contact_email_text = (TextView) this.dialog.findViewById(R.id.contact_email);
        TextView contact_phone_text = (TextView) this.dialog.findViewById(R.id.contact_phone);
        ((TextView) this.dialog.findViewById(R.id.contact_branch_name)).setText(this.contact_branch_name);
        contact_address_text.setText(this.contact_address);
        contact_email_text.setText(this.contact_email);
        contact_phone_text.setText(this.contact_phone);
        LinearLayout contact_email_layout = (LinearLayout) this.dialog.findViewById(R.id.contact_email_layout);
        Button cancelContactButton = (Button) this.dialog.findViewById(R.id.cancelContactButton);
        ((LinearLayout) this.dialog.findViewById(R.id.contact_phone_layout)).setOnClickListener(new C03742());
        contact_email_layout.setOnClickListener(new C03753());
        cancelContactButton.setOnClickListener(new C03764());
        this.dialog.show();
    }

    private void checkPermissions() {
        if (ContextCompat.checkSelfPermission(getActivity(), "android.permission.CALL_PHONE") == 0) {
            call();
        } else if (VERSION.SDK_INT >= 23) {
            if (shouldShowRequestPermissionRationale("android.permission.CALL_PHONE")) {
                Toast.makeText(getActivity(), "Call permission is needed to make this call", 1).show();
            }
            requestPermissions(new String[]{"android.permission.CALL_PHONE"}, 1);
        }
    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode != 1) {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        } else if (grantResults[0] == 0) {
            call();
        } else {
            Toast.makeText(getActivity(), "Call Permission was not granted", 0).show();
        }
    }

    public void SendEmailDialog() {
        this.emailDialog = new Dialog(getActivity());
        this.emailDialog.setContentView(R.layout.send_email);
        ((EditText) this.emailDialog.findViewById(R.id.email_to)).setText(this.contact_email);
        Button emailSendCancel = (Button) this.emailDialog.findViewById(R.id.emailSendCancel);
        ((Button) this.emailDialog.findViewById(R.id.emailSendContinue)).setOnClickListener(new C03775());
        emailSendCancel.setOnClickListener(new C03786());
        this.emailDialog.show();
    }

    public void call() {
        Intent callIntent = new Intent("android.intent.action.CALL");
        callIntent.setData(Uri.parse("tel:" + this.contact_phone));
        startActivity(callIntent);
    }
}
