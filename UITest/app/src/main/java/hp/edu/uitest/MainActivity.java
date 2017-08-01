package hp.edu.uitest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView tv;
    private Button b;
    private EditText et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.tv = (TextView) findViewById(R.id.tv);
        this.b = (Button) findViewById(R.id.button);
        this.et = (EditText) findViewById(R.id.et);
    }//END OF FUNCTION

    @Override
    protected void onResume() {
        super.onResume();
    }//END OF FUNCTION




}//END OF CLASS
