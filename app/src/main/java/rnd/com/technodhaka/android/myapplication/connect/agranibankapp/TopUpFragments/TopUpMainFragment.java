package rnd.com.technodhaka.android.myapplication.connect.agranibankapp.TopUpFragments;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import rnd.com.technodhaka.android.myapplication.R;
import rnd.com.technodhaka.android.myapplication.connect.Animation.PageTransitions;


public class TopUpMainFragment extends Fragment implements OnClickListener {
    private CoordinatorLayout coordinatorLayout;
    Fragment currentFragment;
    View rootView;
    FragmentManager topupFragManager;
    FragmentTransaction transaction;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        this.rootView = inflater.inflate(R.layout.fragment_top_up_main, container, false);

        this.coordinatorLayout = (CoordinatorLayout) getActivity().findViewById(R.id.coordinatorLayout);

        LinearLayout topUpStartButtonLayout = (LinearLayout) this.rootView.findViewById(R.id.topUpStartButtonLayout);
        topUpStartButtonLayout.setOnClickListener(this);

        LinearLayout topUpBeneciciaryLayout = (LinearLayout) this.rootView.findViewById(R.id.topUpBeneciciaryLayout);

        topUpBeneciciaryLayout.setOnClickListener(this);
        new PageTransitions(getActivity(), topUpStartButtonLayout).pageTransitionBottomToTop();
        new PageTransitions(getActivity(), topUpBeneciciaryLayout).pageTransitionTopToBottom();
        return this.rootView;
    }

    public void onClick(View v) {
        int ftId = v.getId();
        if (ftId == R.id.topUpStartButtonLayout) {
            this.currentFragment = new StartTopUpFragment();
            this.topupFragManager = getFragmentManager();
            this.transaction = this.topupFragManager.beginTransaction();
            this.transaction.replace(R.id.top_up_main_layout, this.currentFragment);
            this.transaction.addToBackStack("StartTopUpFragment");
            this.transaction.commit();
        } else if (ftId == R.id.topUpBeneciciaryLayout) {
            this.currentFragment = new TopUpBeneficiaryFragment();
            this.topupFragManager = getFragmentManager();
            this.transaction = this.topupFragManager.beginTransaction();
            this.transaction.replace(R.id.top_up_main_layout, this.currentFragment);
            this.transaction.addToBackStack("TopUpBeneficiaryFragment");
            this.transaction.commit();
        }
    }
}
