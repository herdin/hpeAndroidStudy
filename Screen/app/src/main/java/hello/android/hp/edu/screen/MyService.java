package hello.android.hp.edu.screen;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.IntDef;
import android.util.Log;

public class MyService extends Service {
    public MyService() {
        Log.d("####DEBUG", "MyService::MyService()");
    }

    @Override
    public void onCreate() { //서비스의 라이프사이클 메소드
        super.onCreate();
        Log.d("####DEBUG", "MyService::onCreate()");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) { //서비스의 라이프사이클 메소드
        Log.d("####DEBUG", "MyService::onStartCommand()");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() { //서비스의 라이프사이클 메소드
        super.onDestroy();
        Log.d("####DEBUG", "MyService::onDestroy()");
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        Log.d("####DEBUG", "MyService::onBind: ");
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
