package rnd.com.technodhaka.android.myapplication.connect.agranibankapp.TopUpFragments.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import rnd.com.technodhaka.android.myapplication.R;

/**
 * Created by TD-Android on 12/21/2017.
 */
public class BeneficiaryListAdapter extends BaseAdapter {
    private Activity activity;
    ArrayList<Beneficiary> contactList;
    private LayoutInflater inflater;

    public static class AccountHolder {
        public TextView address;
        public TextView email;
        public TextView name;
        public TextView phone;
    }
    public BeneficiaryListAdapter(Activity activity, ArrayList<Beneficiary> contactList) {
        this.activity = activity;
        this.contactList = contactList;
    }

    public int getCount() {
        return this.contactList.size();
    }

    public Object getItem(int position) {
        return this.contactList.get(position);
    }

    public long getItemId(int position) {
        return (long) position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        AccountHolder holder;
        View vi = convertView;
        if (this.inflater == null) {
            this.inflater = (LayoutInflater) this.activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if (convertView == null) {
            vi = this.inflater.inflate(R.layout.list_row_beneficiary, null);
            holder = new AccountHolder();
            holder.name = (TextView) vi.findViewById(R.id.contact_branch_name);
            holder.address = (TextView) vi.findViewById(R.id.contact_address);
            holder.phone = (TextView) vi.findViewById(R.id.contact_phone);
            holder.email = (TextView) vi.findViewById(R.id.contact_email);
            vi.setTag(holder);
        } else {
            holder = (AccountHolder) vi.getTag();
            vi = convertView;
        }
        holder.name.setText("" + ((Beneficiary) this.contactList.get(position)).getNick_name());
        holder.address.setText("Phone: " + ((Beneficiary) this.contactList.get(position)).getUtility_Acno());
/*        holder.phone.setText(((Beneficiary) this.contactList.get(position)).getPhone());
        holder.email.setText(((Beneficiary) this.contactList.get(position)).getEmail());*/
        return vi;
    }
}
