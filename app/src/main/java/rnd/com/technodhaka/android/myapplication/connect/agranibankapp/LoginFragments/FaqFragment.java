package rnd.com.technodhaka.android.myapplication.connect.agranibankapp.LoginFragments;

import android.app.Fragment;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.TextView.BufferType;

import rnd.com.technodhaka.android.myapplication.R;


public class FaqFragment extends Fragment {
    TextView faqBody;
    TextView faqHeader;
    View rootView;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.rootView = inflater.inflate(R.layout.fragment_faq, container, false);
        this.faqHeader = (TextView) this.rootView.findViewById(R.id.faqHeader);
        this.faqBody = (TextView) this.rootView.findViewById(R.id.faqBody);
        String faqBodyContent = "For any query/ complaints/ suggestions please visit your nearest SMBL Branch \n or \n Email:connect.Ibanking@shimantobank.com \n Give us a call:+8801769630360 ";
        this.faqHeader.setText("F A Q");
        if (VERSION.SDK_INT >= 24) {
            this.faqBody.setText(Html.fromHtml(faqBodyContent, 0));
        } else {
            Log.d("inCode", "Entered");
            this.faqBody.setText(Html.fromHtml(faqBodyContent), BufferType.SPANNABLE);
        }
        return this.rootView;
    }
}
