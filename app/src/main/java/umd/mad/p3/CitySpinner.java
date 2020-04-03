package umd.mad.p3;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

public class CitySpinner implements AdapterView.OnItemSelectedListener {
    private Activity activity;

    public CitySpinner(Activity activity) {
        this.activity = activity;


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        parent.getItemAtPosition(position);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
