package rnd.com.technodhaka.android.myapplication.connect.Connectivity;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetworkAvailability {
    public static boolean isNetworkAvailable(Context c) {
        int[] networkTypes = new int[]{0, 1};
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) c.getSystemService
                    (Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
            for (int networkType : networkTypes) {
                if (activeNetworkInfo != null && activeNetworkInfo.getType() == networkType) {
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }
}
