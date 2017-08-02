package hp.edu.binderservice;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteCallbackList;
import android.support.annotation.IntDef;
import android.util.Log;

public class MyService extends Service { //스타트서비스/바인드서비스 누구에 의해 호출되냐에 따라 라이프사이클이 두개임

    RemoteCallbackList<ICallback> remote = new RemoteCallbackList<>();

    class MyBinder extends Binder implements IService {
        @Override
        public void onPlay() {
            MyService.this.play();
        }//END OF FUNCTION

        @Override
        public String onPlay(int flag1) {
            MyService.this.play();
            return "input flag1 : " + flag1;
        }//END OF FUNCTION

        @Override
        public void onStop() {
            MyService.this.stop();
        }//END OF FUNCTION

        @Override
        public String onStop(int flag2) {
            MyService.this.stop();
            return "input flag2 : " + flag2;
        }//END OF FUNCTION

        @Override
        public void onPause() {
            MyService.this.puase();
        }//END OF FUNCTION

        @Override
        public String onPause(int flag3) {
            MyService.this.puase();
            return "input flag3 : " + flag3;
        }//END OF FUNCTION

        @Override
        public void registCallback(ICallback iCallback) {
            remote.register(iCallback);
        }//END OF FUNCTION

        @Override
        public void unregistCallBack(ICallback iCallback) {
            remote.unregister(iCallback);
        }//END OF FUNCTION

    }//END OF CLASS

    public MyService() {}//END OF FUNCTION

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(getString(R.string.debugTag), "MyService::onCreate()");
    }//END OF FUNCTION

    @Override
    public IBinder onBind(Intent intent) {
        Log.d(getString(R.string.debugTag), "MyService::onBind()");
        //return null; //null 을 반환하면 서비스를 바인드한입장에서 서비스가 연결되었는지 알 수 없다.
        return new MyBinder();
    }//END OF FUNCTION

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(getString(R.string.debugTag), "MyService::onUnbind()");
        return super.onUnbind(intent);
    }//END OF FUNCTION

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(getString(R.string.debugTag), "MyService::onDestroy()");
    }//END OF FUNCTION

    //startService 했을때 타는 라이프사이클, onBind 했을때는 안탄다
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(getString(R.string.debugTag), "MyService::onStartCommand()");
        //Bundle bundle = intent.getExtras();
        return super.onStartCommand(intent, flags, startId);
    }//END OF FUNCTION

    public void play() {
        Log.d(getString(R.string.debugTag), "MyService::play()");

        new Thread() {
            public void run() {
                super.run();
                for(int j=0; j<10; j++) {
                    int node = remote.beginBroadcast();
                    for(int i=0; i<node; i++) {
                        remote.getBroadcastItem(i).onCallBack();
                    }
                    remote.finishBroadcast();
                    try {
                        sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }//END OF FUNCTION

    public void stop() {
        Log.d(getString(R.string.debugTag), "MyService::stop()");
    }//END OF FUNCTION

    public void puase() {
        Log.d(getString(R.string.debugTag), "MyService::puase()");
    }//END OF FUNCTION

}//END OF CLASS
