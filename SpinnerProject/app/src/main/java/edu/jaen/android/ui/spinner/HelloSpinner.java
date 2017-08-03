package edu.jaen.android.ui.spinner;

import edu.jaen.android.ui.spinner.R;
import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class HelloSpinner extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);        

        Spinner s = (Spinner) findViewById(R.id.spinner);
        /*ArrayAdapter adapter = ArrayAdapter.createFromResource(
                this, R.array.planets, android.R.layout.simple_spinner_item);*/
        ArrayAdapter adapter = ArrayAdapter.createFromResource(
                this, R.array.planets, android.R.layout.simple_spinner_dropdown_item);
        //adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s.setAdapter(adapter);
    }

}