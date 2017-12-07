package rnd.com.technodhaka.android.myapplication.connect.agranibankapp.AccountInformation;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import rnd.com.technodhaka.android.myapplication.R;
import rnd.com.technodhaka.android.myapplication.connect.Animation.PageTransitions;


public class AccountListAdapter extends BaseAdapter {
    ArrayList<AccountInfo> accountList;
    private Activity activity;
    private LayoutInflater inflater;
    int transitionCount = 1;

    public static class AccountHolder {
        public TextView accNo;
        public TextView accName;
        public TextView balance;
        public TextView currency;
        public TextView accType;

    }

    public AccountListAdapter(Activity activity, ArrayList<AccountInfo> accountList) {
        this.activity = activity;
        this.accountList = accountList;
    }

    public int getCount() {
        return this.accountList.size();
    }

    public Object getItem(int position) {
        return this.accountList.get(position);
    }

    public long getItemId(int position) {
        return (long) position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        AccountHolder holder;
        View rowView = convertView;
        if (this.inflater == null) {
            this.inflater = (LayoutInflater) this.activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if (convertView == null) {
            rowView = this.inflater.inflate(R.layout.acc_info_list, null);
            holder = new AccountHolder();
            holder.accNo = (TextView) rowView.findViewById(R.id.info_acc_no_text);
            holder.balance = (TextView) rowView.findViewById(R.id.info_acc_blnc_text);
            holder.accName = (TextView) rowView.findViewById(R.id.info_acc_name_txt);
            holder.accType = (TextView) rowView.findViewById(R.id.tv_info_acc_type);
            holder.currency = (TextView) rowView.findViewById(R.id.info_acc_curr_text);

            rowView.setTag(holder);
            if (this.transitionCount % 2 == 0) {
                new PageTransitions(this.activity, rowView).pageTransitionRightToLeft();
            } else {
                new PageTransitions(this.activity, rowView).pageTransitionLeftToRight();
            }
            this.transitionCount++;
        } else {
            holder = (AccountHolder) rowView.getTag();
            rowView = convertView;
        }
        holder.accNo.setText((this.accountList.get(position)).getAccountNo());
        holder.balance.setText((this.accountList.get(position)).getBalance());
        holder.currency.setText((this.accountList.get(position)).getCurrency());
        holder.accName.setText((this.accountList.get(position)).getAccountName());
        holder.accType.setText((this.accountList.get(position)).getAccountType());

        return rowView;
    }
}
