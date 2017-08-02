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

    private Button playButton;
    private Button stopButton;
    private Button puaseButton;
    private IService iService;
    private ICallback iCallback = new ICallback() {
        @Override
        public void onCallBack() {
            Log.d(getString(R.string.debugTag), "MainActivity::ICallback::onCallBack()");
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