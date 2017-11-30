package rnd.com.technodhaka.android.myapplication.connect.simantobankapp.LoginFragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

import rnd.com.technodhaka.android.myapplication.R;
import rnd.com.technodhaka.android.myapplication.connect.Dialogs.ShowDialogs;


public class LoginRegisterFragment extends Fragment {
    TextView forgotPassword;
    View rootView;

    class C03791 implements OnClickListener {
        C03791() {
        }

        public void onClick(View v) {
            ShowDialogs.forgotPassword(LoginRegisterFragment.this.getActivity());
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.rootView = inflater.inflate(R.layout.fragment_login_register, container, false);
        this.forgotPassword = (TextView) this.rootView.findViewById(R.id.forgotPassword);
        this.forgotPassword.setOnClickListener(new C03791());
        return this.rootView;
    }
}
