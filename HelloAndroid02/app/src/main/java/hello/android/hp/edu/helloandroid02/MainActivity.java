package hello.android.hp.edu.helloandroid02;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        TextView tv = new TextView(this);
//        tv.setText("Hello");
//        setContentView(tv);
        setContentView(R.layout.activity_main);
    }
}
