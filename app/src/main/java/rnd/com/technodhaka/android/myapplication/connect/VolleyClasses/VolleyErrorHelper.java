package rnd.com.technodhaka.android.myapplication.connect.VolleyClasses;

import android.content.Context;
import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NetworkResponse;
import com.android.volley.NoConnectionError;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.HashMap;
import java.util.Map;

import rnd.com.technodhaka.android.myapplication.R;


public class VolleyErrorHelper {

    static class VolleyTypeTokenMap extends TypeToken<Map<String, String>> {
        VolleyTypeTokenMap() {
        }
    }

    public static String getMessage(Object error, Context context) {
        if (error instanceof TimeoutError) {
            return context.getResources().getString(R.string.timeout);
        }
        if (isServerProblem(error)) {
            return handleServerError(error, context);
        }
        if (isNetworkProblem(error)) {
            return context.getResources().getString(R.string.nointernet);
        }
        return context.getResources().getString(R.string.generic_error);
    }

    private static String handleServerError(Object error, Context context) {
        NetworkResponse response = ((VolleyError) error).networkResponse;
        if (response == null) {
            return context.getResources().getString(R.string.generic_error);
        }
        switch (response.statusCode) {
            case 401:
            case 404:
            case 422:
                try {
                    HashMap<String, String> result = (HashMap) new Gson().fromJson(new String(response.data), new VolleyTypeTokenMap().getType());
                    if (result != null && result.containsKey("error")) {
                        return (String) result.get("error");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return ((VolleyError) error).getMessage();
            default:
                return context.getResources().getString(R.string.timeout);
        }
    }

    private static boolean isServerProblem(Object error) {
        return (error instanceof ServerError) || (error instanceof AuthFailureError);
    }

    private static boolean isNetworkProblem(Object error) {
        return (error instanceof NetworkError) || (error instanceof NoConnectionError);
    }
}
