package hp.edu.binderservice;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private IService iService; //액티비티에서 호출할 서비스인터페이스
    private ICallback iCallback = new ICallback() { //서비스에서 호출할 액티비티 콜백인터페이스
        @Override
        public void onCallBack() {
            Log.d(getString(R.string.debugTag), "MainActivity::ICallback::onCallBack()");
            MainActivity.this.mainActivityFunc();
        }

        @Override
        public IBinder asBinder() {
            Log.d(getString(R.string.debugTag), "MainActivity::ICallback::asBinder()");
            return new Binder();
        }
    };

    private ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Log.d(getString(R.string.debugTag), "MainActivity::ServiceConnection::onServiceConnected()");
            MainActivity.this.iService = (IService)iBinder;
            MainActivity.this.iService.registCallback(MainActivity.this.iCallback);

            //aidl serivce 를 사용할 경우, 아래변수를 멤버변수를 넣고 사용하면된다.
            //AidlService aidlService = AidlService.Stub.asInterface(iBinder);
        }//END OF FUNCTION

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            Log.d(getString(R.string.debugTag), "MainActivity::ServiceConnection::onServiceDisconnected()");
        }//END OF FUNCTION
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(getString(R.string.debugTag), "MainActivity::onCreate()");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ComponentName componentName = new ComponentName("hp.edu.binderservice", "hp.edu.binderservice.MyService");
        Intent intent = new Intent();
        intent.setComponent(componentName);
        /* startService 로 호출 했을 경우와, bindService 로 호출 했을 경우
        * service 의 라이프사이클이 달라진다.
        *
        * */
        bindService(intent, conn, Context.BIND_AUTO_CREATE);

        findViewById(R.id.playButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(getString(R.string.debugTag), "MainActivity::playButton::OnClickListener::onClick() : " + iService.onPlay(3));
            }
        });
        findViewById(R.id.stopButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(getString(R.string.debugTag), "MainActivity::stopButton::OnClickListener::onClick() : " + iService.onStop(4));
            }
        });
        findViewById(R.id.pauseButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(getString(R.string.debugTag), "MainActivity::pauseButton::OnClickListener::onClick() : " + iService.onPause(5));
            }
        });
    }//END OF FUNCTION

    @Override
    protected void onDestroy() {
        Log.d(getString(R.string.debugTag), "MainActivity::onDestroy()");
        super.onDestroy();
        this.unbindService(conn);
        iService.unregistCallBack(iCallback);
    }//END OF FUNCTION

    public void mainActivityFunc() {
        Log.d(getString(R.string.debugTag), "MainActivity::mainActivityFunc()");
    }//END OF FUNCTION


}//END OF CLASS