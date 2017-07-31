package hello.android.hp.edu.screen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class NextActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next);
        Intent intent = getIntent(); //전달받은 intent 를 받는다.
        String recvData = intent.getStringExtra(getString(R.string.dataKey)); //전달된 data 를 dataKey 를 key 로 꺼낸다.
        Toast.makeText(this, recvData, Toast.LENGTH_SHORT).show();
    }
}
