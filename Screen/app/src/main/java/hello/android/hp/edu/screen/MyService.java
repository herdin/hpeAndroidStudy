package hello.android.hp.edu.screen;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.IntDef;
import android.util.Log;

public class MyService extends Service {
    public MyService() {
        //Log.d(getApplicationContext().getString(R.string.debugId), this.getClass().getSimpleName() + "::MyService()");
    }

    @Override
    public void onCreate() { //서비스의 라이프사이클 메소드
        super.onCreate();
        Log.d(getApplicationContext().getString(R.string.debugId), this.getClass().getSimpleName() + "::onCreate()");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) { //서비스의 라이프사이클 메소드
        Log.d(getApplicationContext().getString(R.string.debugId), this.getClass().getSimpleName() + "::onStartCommand()");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() { //서비스의 라이프사이클 메소드
        super.onDestroy();
        Log.d(getApplicationContext().getString(R.string.debugId), this.getClass().getSimpleName() + "::onDestroy()");
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        Log.d(getApplicationContext().getString(R.string.debugId), this.getClass().getSimpleName() + "::onBind: ");
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
