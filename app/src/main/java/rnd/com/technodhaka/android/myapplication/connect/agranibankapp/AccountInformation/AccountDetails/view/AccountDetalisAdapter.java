package rnd.com.technodhaka.android.myapplication.connect.agranibankapp.AccountInformation.AccountDetails.view;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import rnd.com.technodhaka.android.myapplication.R;
import rnd.com.technodhaka.android.myapplication.connect.Animation.PageTransitions;


/**
 * Created by TD-Android on 12/7/2017.
 */
public class AccountDetalisAdapter extends BaseAdapter {

    List<AccountDetailsDatamodel> accountList;
    private Activity activity;
    private LayoutInflater inflater;
    int transitionCount = 1;

    public AccountDetalisAdapter(Activity activity, List<AccountDetailsDatamodel> mAccDetailsList) {
        this.activity = activity;
        this.accountList = mAccDetailsList;
    }

    private static class AccountHolder {
        public TextView tv_tdate;
        public TextView tv_remark;
        public TextView tv_debit;
        public TextView tv_credit;
        public TextView tv_amtbal_tk;

    }

   

    @Override
    public int getCount() {
        return this.accountList.size();
    }

    @Override
    public Object getItem(int position) {
        return this.accountList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return (long) position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        AccountHolder holder;
        View rowView = convertView;
        if (this.inflater == null) {
            this.inflater = (LayoutInflater) this.activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if (convertView == null) {
            rowView = this.inflater.inflate(R.layout.row_acc_details_list, null);
            holder = new AccountHolder();
            holder.tv_tdate = (TextView) rowView.findViewById(R.id.tv_acc_details_tDate);
            holder.tv_remark = (TextView) rowView.findViewById(R.id.tv_acc_details_Details);
            holder.tv_debit = (TextView) rowView.findViewById(R.id.tv_acc_details_Debit);
            holder.tv_credit = (TextView) rowView.findViewById(R.id.tv_acc_details_Credit);
            holder.tv_amtbal_tk = (TextView) rowView.findViewById(R.id.tv_acc_details_Balance);

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
        holder.tv_tdate.setText(( this.accountList.get(position)).getTdate());
        holder.tv_remark.setText(( this.accountList.get(position)).getRemark());
        holder.tv_debit.setText(( this.accountList.get(position)).getDebit());
        holder.tv_credit.setText(( this.accountList.get(position)).getCredit());
        holder.tv_amtbal_tk.setText(( this.accountList.get(position)).getAmtbal_tk());

        return rowView;
    }
}
