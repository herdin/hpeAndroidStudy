package mythread.hp.edu.mythreadapp;

import android.animation.ObjectAnimator;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public TextView tvbcnt;
    public TextView tvtcnt;
    public int cnt = 0;
    public int cnt2 = 0;
    public boolean threadFlag = true;

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Log.d(getString(R.string.debugTag), "HANDLER RECEIVE MESSAGE");
            switch(msg.what) {
                case 123:
                    Toast.makeText(MainActivity.this, msg.obj.toString(), Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.thread1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Log.d(getString(R.string.debugTag), "USER THREAD " + Thread.currentThread().getId() + " START");
                        try {
                            Thread.sleep(10000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        //유저 스레드에서 UI 호출을 하려고하면 안된다. -> 프레임워크 오류
//                        Toast.makeText(MainActivity.this, "FIN", Toast.LENGTH_SHORT).show();
                        Message message = new Message();
                        message.arg1 = 0;
                        message.arg2 = 1;
                        message.obj = "END THREAD" + Thread.currentThread().getId();
                        message.what = 123;
                        MainActivity.this.handler.sendMessage(message);
                        Log.d(getString(R.string.debugTag), "USER THREAD" + Thread.currentThread().getId() + " END");
                    }
                }, Thread.currentThread().toString()).start();
            }
        });

        findViewById(R.id.thread2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Log.d(getString(R.string.debugTag), "USER THREAD " + Thread.currentThread().getId() + " START");
                        try {
                            Thread.sleep(10000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        Runnable runnable = new Runnable() {
                            @Override
                            public void run() {
                                Log.d(getString(R.string.debugTag), "USER THREAD " + Thread.currentThread().getId() + " START");
                                Toast.makeText(MainActivity.this, "USER THREAD " + Thread.currentThread().getId() + " FINISHED", Toast.LENGTH_SHORT).show();
                                Log.d(getString(R.string.debugTag), "USER THREAD " + Thread.currentThread().getId() + " END");
                            }
                        };
                        //runnable 을 보내면 핸들메세지가 필요가없다.
                        MainActivity.this.handler.post(runnable);

                        Log.d(getString(R.string.debugTag), "USER THREAD " + Thread.currentThread().getId() + " END");
                    }
                }).start();
            }
        });

        this.tvbcnt = (TextView)findViewById(R.id.tvbcnt);
        this.tvtcnt = (TextView)findViewById(R.id.tvtcnt);
        findViewById(R.id.thread3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.this.cnt++;
                MainActivity.this.tvbcnt.setText("cnt : " + cnt);
            }
        });

        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.d(getString(R.string.debugTag), "USER THREAD " + Thread.currentThread().getId() + " START");
                while(threadFlag) {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            MainActivity.this.cnt2++;
                            MainActivity.this.tvtcnt.setText("cnt2 : " + MainActivity.this.cnt2);
                        }
                    });
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                //Log.d(getString(R.string.debugTag), "USER THREAD " + Thread.currentThread().getId() + " END");
            }
        }, "AUTO CNT2 INC THREAD").start();
    }//END OF FUNCTION

    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.threadFlag = false;
    }//END OF FUNCTION
}//END OF CLASS