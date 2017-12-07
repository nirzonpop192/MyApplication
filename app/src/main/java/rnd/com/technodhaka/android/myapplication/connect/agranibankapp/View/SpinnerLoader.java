package rnd.com.technodhaka.android.myapplication.connect.agranibankapp.View;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.List;

import rnd.com.technodhaka.android.myapplication.R;

/**
 * Created by TD-Android on 12/6/2017.
 */
public class SpinnerLoader {

    public static void loadSpinner(Context context, Spinner spinner, List<CharSequence> lists, String groupCode, String strGroup) {
        int position = 0;
/*// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(context,
                R.array.planets_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);*/

        ArrayAdapter<CharSequence> dataAdapter = new ArrayAdapter<CharSequence>(context, R.layout.spinner_layout, lists);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);


        spinner.setSelection(position);

    }
}
