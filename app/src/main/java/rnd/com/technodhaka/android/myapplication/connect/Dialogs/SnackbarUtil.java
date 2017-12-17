package rnd.com.technodhaka.android.myapplication.connect.Dialogs;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.support.design.widget.Snackbar.Callback;
import android.view.View;
import android.view.View.OnClickListener;

public class SnackbarUtil {


    static class UnknownClassC07593 extends Callback {
        UnknownClassC07593() {
        }

        public void onDismissed(Snackbar snackbar, int event) {
            super.onDismissed(snackbar, event);
        }

        public void onShown(Snackbar snackbar) {
            super.onShown(snackbar);
        }
    }

    public static void netErrorMessage(View rootView, String mMessage) {
        Snackbar.make(rootView, (CharSequence) mMessage, 0).show();
    }

    public static void generalMessage(View rootView, String mMessage) {
        Snackbar.make(rootView, (CharSequence) mMessage, 0).show();
    }

    public static void showSnakbarTypeTwo(View rootView, String mMessage) {
        Snackbar.make(rootView, (CharSequence) mMessage, 0);
        Snackbar.make(rootView, (CharSequence) mMessage, -2).setAction((CharSequence) "Action", null).show();
    }

    public static void showSnakbarTypeThree(View rootView, final Activity activity) {
        Snackbar.make(rootView, (CharSequence) "NoInternetConnectivity", -2).setAction((CharSequence) "TryAgain", new OnClickListener() {
            public void onClick(View view) {
                Intent intent = activity.getIntent();
                activity.finish();
                activity.startActivity(intent);
            }
        }).setActionTextColor(Color.GRAY).setCallback(new C07581()).show();
    }

    static class C07581 extends Callback {
        C07581() {
        }

        public void onDismissed(Snackbar snackbar, int event) {
            super.onDismissed(snackbar, event);
        }

        public void onShown(Snackbar snackbar) {
            super.onShown(snackbar);
        }
    }


    public static void showSnakbarTypeFour(View rootView, final Activity activity, String mMessage) {
        Snackbar.make(rootView, (CharSequence) mMessage, -2).setAction((CharSequence) "TryAgain", new OnClickListener() {
            public void onClick(View view) {
                Intent intent = activity.getIntent();
                activity.finish();
                activity.startActivity(intent);
            }
        }).setActionTextColor(Color.GRAY).setCallback(new UnknownClassC07593()).show();
    }
}
