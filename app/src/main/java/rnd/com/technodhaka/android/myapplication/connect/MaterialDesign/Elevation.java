package rnd.com.technodhaka.android.myapplication.connect.MaterialDesign;

import android.os.Build.VERSION;
import android.view.View;

public class Elevation {
    public static void setElevation(View view, float elevationPoint) {
        try {
            if (VERSION.SDK_INT >= 21) {
                view.setElevation(elevationPoint);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
