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

        coordinatorLayout = (CoordinatorLayout) getActivity().findViewById(R.id.coordinatorLayout);

        LinearLayout topUpStartButtonLayout = (LinearLayout) this.rootView.findViewById(R.id.topUpStartButtonLayout);
        topUpStartButtonLayout.setOnClickListener(this);

        LinearLayout topUpBeneciciaryLayout = (LinearLayout) this.rootView.findViewById(R.id.topUpBeneciciaryLayout);
        topUpBeneciciaryLayout.setOnClickListener(this);

        LinearLayout topUpBeneciciaryDeleteLayout = (LinearLayout) this.rootView.findViewById(R.id.topUpBeneciciaryDeleteLayout);
        topUpBeneciciaryDeleteLayout.setOnClickListener(this);

        new PageTransitions(getActivity(), topUpStartButtonLayout).pageTransitionBottomToTop();
        new PageTransitions(getActivity(), topUpBeneciciaryLayout).pageTransitionTopToBottom();
        new PageTransitions(getActivity(), topUpBeneciciaryDeleteLayout).pageTransitionBottomToTop();
        return this.rootView;
    }

    public void onClick(View v) {

        switch (v.getId()){
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
                currentFragment = new TopUpDeleteBeneficiaryFragment();
                topupFragManager = getFragmentManager();
                transaction = this.topupFragManager.beginTransaction();
                transaction.replace(R.id.top_up_main_layout, this.currentFragment);
                transaction.addToBackStack("TopUpBeneficiaryDeleteFragment");
                transaction.commit();
                break;
        }

    }
}
