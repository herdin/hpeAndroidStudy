package edu.jaen.android.ui.spinner;

import edu.jaen.android.ui.spinner.R;
import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

public class HelloSpinner extends Activity {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        Spinner s = (Spinner) findViewById(R.id.spinner);
        /*ArrayAdapter adapter = ArrayAdapter.createFromResource(
                this, R.array.planets, android.R.layout.simple_spinner_item);*/
        ArrayAdapter adapter = ArrayAdapter.createFromResource(
                //this, R.array.planets, android.R.layout.simple_spinner_dropdown_item);
                this, R.array.planets, android.R.layout.simple_spinner_dropdown_item);
        //R.array... -> 프로젝트 res 폴더
        //android.R.layout... -> 안드로이드 라이브러리
        //adapter.setDropDownViewResource(android.R.layout.simple_expandable_list_item_1);
        //adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s.setAdapter(adapter);

        ListView listView = (ListView)findViewById(R.id.listView);
        ArrayAdapter adapter2 = ArrayAdapter.createFromResource(this, R.array.planets, android.R.layout.simple_gallery_item);
        listView.setAdapter(adapter2);
    }//END OF FUNCTION
}//END OF CLASS