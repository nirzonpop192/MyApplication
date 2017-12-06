package rnd.com.technodhaka.android.myapplication.connect.agranibankapp.TopUpFragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import rnd.com.technodhaka.android.myapplication.R;
import rnd.com.technodhaka.android.myapplication.connect.Animation.PageTransitions;


public class TopUpSuccessFragment extends Fragment {
    private CoordinatorLayout coordinatorLayout;
    View rootView;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.rootView = inflater.inflate(R.layout.fragment_top_up_success, container, false);
        this.coordinatorLayout = (CoordinatorLayout) getActivity().findViewById(R.id.coordinatorLayout);
        new PageTransitions(getActivity(), this.rootView).pageTransitionBottomToTop();
        return this.rootView;
    }
}
