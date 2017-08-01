package hello.android.hp.edu.screen;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class FirstActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
    }//END OF FUNCTION

    

    public void goNext(View view) {
        Toast.makeText(this, "this is Toast", Toast.LENGTH_SHORT).show(); //토스터 한번 띄워주고
        Intent intent = new Intent(this, NextActivity.class); //activity manager 에게 activity 정보를 알려주고
        //intent 에 담을 수 있는 정보는 1024 가 한계다.
        intent.putExtra(getString(R.string.dataKey), "this is data"); //intent 에 string 데이터를 dataKey 를 key 로 넣어준다.
        startActivity(intent); //intent 를 전달하라고 요청한다. activity 가 떠있지않으면 띄우고 전달, 안떠있으면 띄운다.

    }//END OF FUNCTION

    public void startService(View v) {
        Toast.makeText(this, "startService", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent();
        intent.setClass(this, MyService.class);
        startService(intent);
    }//END OF FUNCTION

    public void stopService(View v) {
        Toast.makeText(this, "stopService", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent();
        intent.setClass(this, MyService.class);
        stopService(intent);
    }//END OF FUNCTION

    public void startBroadcast(View v) {
        Log.d("####DEBUG", "startBroadcast");
        Intent intent = new Intent();
        //방법1 : 내 프로젝트안의 클래스를 호출할때
        //intent.setClass(this, MyReceiver.class);
        //방법2 : 패키지와 클래스명을 알고있을때, 다른 프로젝트의 클래스도 호출가능
        ComponentName cn = new ComponentName("hello.android.hp.edu.screen", "hello.android.hp.edu.screen.MyReceiver");
        intent.setComponent(cn);
        sendBroadcast(intent);
        //메세지처리를 할 수있도록 변경했기 떄문에 오류난다.
    }//END OF FUNCTION

}//END OF CLASS