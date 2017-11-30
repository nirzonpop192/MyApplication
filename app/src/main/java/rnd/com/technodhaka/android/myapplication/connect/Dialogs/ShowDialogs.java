package rnd.com.technodhaka.android.myapplication.connect.Dialogs;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Process;
import android.support.v4.internal.view.SupportMenu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;





import rnd.com.technodhaka.android.myapplication.R;
import rnd.com.technodhaka.android.myapplication.connect.simantobankapp.Activities.LoginRegisterActivity;

public class ShowDialogs {
    public static void noInternetDialog(Context noInternetContext) {
        final Dialog noInternetDialog = new Dialog(noInternetContext);
        noInternetDialog.requestWindowFeature(1);
        noInternetDialog.setCancelable(false);
        noInternetDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        noInternetDialog.setContentView(R.layout.dialog);
        TextView dialogTitle = (TextView) noInternetDialog.findViewById(R.id.ftSuccessTitle);
        TextView dialogText = (TextView) noInternetDialog.findViewById(R.id.dialogText);
        Button dialogButton = (Button) noInternetDialog.findViewById(R.id.dialogBtn);
        dialogTitle.setText(R.string.no_Internet_dialog_title);
        dialogTitle.setTextColor(SupportMenu.CATEGORY_MASK);
        dialogText.setText(R.string.no_Internet_dialog_Text);
        dialogText.setTextColor(Color.BLACK);
        dialogButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                noInternetDialog.dismiss();
            }
        });
        noInternetDialog.show();
    }

    public static void dismissDialog(Context noInternetContext) {
        Dialog noInternetDialog = new Dialog(noInternetContext);
        noInternetDialog.setContentView(R.layout.dialog);
        noInternetDialog.dismiss();
    }

    public static void otpInputDialog(Context otpInputContext) {
        final Dialog otpInputDialog = new Dialog(otpInputContext);
        otpInputDialog.setCancelable(false);
        otpInputDialog.requestWindowFeature(1);
        otpInputDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        otpInputDialog.requestWindowFeature(1);
        otpInputDialog.setContentView(R.layout.dialog);
        TextView dialogText = (TextView) otpInputDialog.findViewById(R.id.dialogText);
        Button dialogButton = (Button) otpInputDialog.findViewById(R.id.dialogBtn);
        ((TextView) otpInputDialog.findViewById(R.id.ftSuccessTitle)).setText(R.string.otp_input_dialog_title);
        dialogText.setText(R.string.otp_input_dialog_text);
        dialogText.setTextColor(Color.BLACK);
        dialogButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                otpInputDialog.dismiss();
            }
        });
        otpInputDialog.show();
    }

    public static void forgotPassword(Context forgotPassContext) {
        final Dialog forgotPasswordDialog = new Dialog(forgotPassContext);
        forgotPasswordDialog.setCancelable(false);
        forgotPasswordDialog.requestWindowFeature(1);
        forgotPasswordDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        forgotPasswordDialog.setContentView(R.layout.dialog);
        Button dialogButton = (Button) forgotPasswordDialog.findViewById(R.id.dialogBtn);
        TextView dialogTitle = (TextView) forgotPasswordDialog.findViewById(R.id.ftSuccessTitle);
        TextView dialogText = (TextView) forgotPasswordDialog.findViewById(R.id.dialogText);
        dialogTitle.setText(R.string.forgot_password_dialog_title);
        dialogTitle.setTextColor(SupportMenu.CATEGORY_MASK);
        dialogText.setText(R.string.forgot_pass_dialog_title);
        dialogText.setTextColor(Color.BLACK);
        dialogButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                forgotPasswordDialog.dismiss();
            }
        });
        forgotPasswordDialog.show();
    }

    public static void invalidRegistraton(Context invalidRegContext) {
        final Dialog forgotPasswordDialog = new Dialog(invalidRegContext);
        forgotPasswordDialog.setCancelable(false);
        forgotPasswordDialog.requestWindowFeature(1);
        forgotPasswordDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        forgotPasswordDialog.setContentView(R.layout.dialog);
        Button dialogButton = (Button) forgotPasswordDialog.findViewById(R.id.dialogBtn);
        TextView dialogTitle = (TextView) forgotPasswordDialog.findViewById(R.id.ftSuccessTitle);
        TextView dialogText = (TextView) forgotPasswordDialog.findViewById(R.id.dialogText);
        dialogTitle.setText(R.string.invalid_registration_title);
        dialogTitle.setTextColor(SupportMenu.CATEGORY_MASK);
        dialogText.setText(R.string.invalid_reg_text);
        dialogText.setTextColor(Color.BLACK);
        dialogButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                forgotPasswordDialog.dismiss();
            }
        });
        forgotPasswordDialog.show();
    }

    /**
     *
     * @param netErrorContext net Error Context
     * @param content dialog text
     * @param title Title of the Dialog
     * @param cancelBtn cancel Button title
     * @param okBtn ok Button title
     * @param intent intent of invoking or starting Activity
     */
    public static void networkErrorException(final Context netErrorContext, String content,
                                             String title, String cancelBtn, String okBtn,
                                             final Intent intent) {
        final Dialog netErrorDialog = new Dialog(netErrorContext);
        netErrorDialog.setCancelable(false);
        netErrorDialog.requestWindowFeature(1);
        netErrorDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        netErrorDialog.setContentView(R.layout.net_exception_dialog);
        netErrorDialog.setCancelable(false);
        Button okDialogButton = (Button) netErrorDialog.findViewById(R.id.dialogBtnPositive);
        Button cancelDialogButton = (Button) netErrorDialog.findViewById(R.id.dialogBtnNegative);
        TextView dialogTitle = (TextView) netErrorDialog.findViewById(R.id.ftSuccessTitle);
        TextView dialogText = (TextView) netErrorDialog.findViewById(R.id.dialogText);
        okDialogButton.setText(okBtn);
        cancelDialogButton.setText(cancelBtn);
        dialogTitle.setText(title);
        dialogText.setText(content);
        dialogText.setTextColor(Color.BLACK);
        dialogTitle.setTextColor(SupportMenu.CATEGORY_MASK);
        okDialogButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                netErrorDialog.dismiss();
                ((Activity) netErrorContext).finish();
                ((Activity) netErrorContext).startActivity(intent);
            }
        });
        cancelDialogButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                netErrorDialog.dismiss();
                Intent intent = new Intent("android.intent.action.MAIN");
                intent.addCategory("android.intent.category.HOME");
                ((Activity) netErrorContext).startActivity(intent);
                Process.killProcess(Process.myPid());
            }
        });
        netErrorDialog.show();
    }

    public static void forgotPinDialog(final Context forgotPinContext, String content, String title, String cancelBtn, String okBtn, Intent intent) {
        final Dialog forgotPinDialog = new Dialog(forgotPinContext);
        forgotPinDialog.setCancelable(false);
        forgotPinDialog.requestWindowFeature(1);
        forgotPinDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        forgotPinDialog.setContentView(R.layout.net_exception_dialog);
        Button okDialogButton = (Button) forgotPinDialog.findViewById(R.id.dialogBtnPositive);
        Button cancelDialogButton = (Button) forgotPinDialog.findViewById(R.id.dialogBtnNegative);
        TextView dialogTitle = (TextView) forgotPinDialog.findViewById(R.id.ftSuccessTitle);
        TextView dialogText = (TextView) forgotPinDialog.findViewById(R.id.dialogText);
        okDialogButton.setText(okBtn);
        cancelDialogButton.setText(cancelBtn);
        dialogTitle.setText(title);
        dialogText.setText(content);
        dialogText.setTextColor(Color.BLACK);
        dialogTitle.setTextColor(SupportMenu.CATEGORY_MASK);
        okDialogButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Intent forgotPinIntent = new Intent(forgotPinContext, LoginRegisterActivity.class);
                forgotPinIntent.putExtra("FORGOT_PIN", "FP");
                forgotPinContext.startActivity(forgotPinIntent);
                forgotPinDialog.dismiss();
            }
        });
        cancelDialogButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                forgotPinDialog.dismiss();
            }
        });
        forgotPinDialog.show();
    }

    public static void resendOtpDialog(Context resendOtpContext, String content, String title, String cancelBtn, String okBtn, Activity mActivity) {
        final Dialog resendOtpDialog = new Dialog(resendOtpContext);
        resendOtpDialog.requestWindowFeature(1);
        resendOtpDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        resendOtpDialog.setContentView(R.layout.net_exception_dialog);
        Button okDialogButton = (Button) resendOtpDialog.findViewById(R.id.dialogBtnPositive);
        Button cancelDialogButton = (Button) resendOtpDialog.findViewById(R.id.dialogBtnNegative);
        TextView dialogTitle = (TextView) resendOtpDialog.findViewById(R.id.ftSuccessTitle);
        TextView dialogText = (TextView) resendOtpDialog.findViewById(R.id.dialogText);
        okDialogButton.setText(okBtn);
        cancelDialogButton.setText(cancelBtn);
        dialogTitle.setText(title);
        dialogText.setText(content);
        dialogText.setTextColor(Color.BLACK);
        dialogTitle.setTextColor(Color.BLACK);
        okDialogButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                resendOtpDialog.dismiss();
            }
        });
        cancelDialogButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                resendOtpDialog.dismiss();
            }
        });
        resendOtpDialog.show();
    }

    public static void accInfoExceptionDialog(Context accInfoContext, String content, String title, String cancelBtn, String okBtn, Activity mActivity) {
        final Dialog accInfoExceptionDialog = new Dialog(accInfoContext);
        accInfoExceptionDialog.requestWindowFeature(1);
        accInfoExceptionDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        accInfoExceptionDialog.setContentView(R.layout.net_exception_dialog);
        Button okDialogButton = (Button) accInfoExceptionDialog.findViewById(R.id.dialogBtnPositive);
        Button cancelDialogButton = (Button) accInfoExceptionDialog.findViewById(R.id.dialogBtnNegative);
        TextView dialogTitle = (TextView) accInfoExceptionDialog.findViewById(R.id.ftSuccessTitle);
        TextView dialogText = (TextView) accInfoExceptionDialog.findViewById(R.id.dialogText);
        okDialogButton.setText(okBtn);
        cancelDialogButton.setText(cancelBtn);
        dialogTitle.setText(title);
        dialogText.setText(content);
        dialogText.setTextColor(Color.BLACK);
        dialogTitle.setTextColor(Color.BLACK);
        okDialogButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                accInfoExceptionDialog.dismiss();
            }
        });
        cancelDialogButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                accInfoExceptionDialog.dismiss();
            }
        });
        accInfoExceptionDialog.show();
    }
}
