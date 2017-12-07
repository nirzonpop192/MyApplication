package rnd.com.technodhaka.android.myapplication.connect.agranibankapp.AccountInformation;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import rnd.com.technodhaka.android.myapplication.R;
import rnd.com.technodhaka.android.myapplication.connect.Animation.PageTransitions;


public class AccStatementAdapter extends BaseAdapter {
    ArrayList<AccStatement> accountStatementList;
    private Activity activity;
    private LayoutInflater inflater;
    int transitionCount = 1;

    public static class AccountHolder {
        public TextView balance;
        public TextView deposit;
        public TextView particular;
        public TextView transactionDate;
        public TextView withdrawal;
    }

    public AccStatementAdapter(Activity activity, ArrayList<AccStatement> accountStatementList) {
        this.activity = activity;
        this.accountStatementList = accountStatementList;
    }

    public int getCount() {
        return this.accountStatementList.size();
    }

    public Object getItem(int position) {
        return this.accountStatementList.get(position);
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
            vi = this.inflater.inflate(R.layout.acc_statement_list, null);
            holder = new AccountHolder();
            holder.transactionDate = (TextView) vi.findViewById(R.id.acc_state_transaction_date_text);
            holder.particular = (TextView) vi.findViewById(R.id.acc_state_particular_text);
            holder.deposit = (TextView) vi.findViewById(R.id.acc_state_dp_text);
            holder.balance = (TextView) vi.findViewById(R.id.acc_state_blnc_text);
            holder.withdrawal = (TextView) vi.findViewById(R.id.acc_state_withdrawl_text);
            vi.setTag(holder);
            if (this.transitionCount % 2 == 0) {
                new PageTransitions(this.activity, vi).pageTransitionRightToLeft();
            } else {
                new PageTransitions(this.activity, vi).pageTransitionLeftToRight();
            }
            this.transitionCount++;
        } else {
            holder = (AccountHolder) vi.getTag();
            vi = convertView;
        }
        Log.d("HolderStatement", " hTdate " + ((AccStatement) this.accountStatementList.get(position)).getTransactiondate() + " deposit " + ((AccStatement) this.accountStatementList.get(position)).getDeposit() + " mBalance " + ((AccStatement) this.accountStatementList.get(position)).getBalance() + " withdrawal " + ((AccStatement) this.accountStatementList.get(position)).getWithdrawal());
        holder.transactionDate.setText(((AccStatement) this.accountStatementList.get(position)).getTransactiondate());
        holder.particular.setText(((AccStatement) this.accountStatementList.get(position)).getParticular());
        holder.deposit.setText(((AccStatement) this.accountStatementList.get(position)).getDeposit());
        holder.balance.setText(((AccStatement) this.accountStatementList.get(position)).getBalance());
        holder.withdrawal.setText(((AccStatement) this.accountStatementList.get(position)).getWithdrawal());
        return vi;
    }
}
