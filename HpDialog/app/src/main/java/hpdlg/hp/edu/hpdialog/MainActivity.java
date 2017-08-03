package hpdlg.hp.edu.hpdialog;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    public static final int BASIC_DIALOG = 1;
    public static final int PROGRESS_DIALOG = 2;
    public ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.buttonDialog).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //이렇게 다이얼로그를 만들면 매번 객체를 생성하므로 성능에 문제가 된다. gc
//                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
//                builder.setTitle("Hello");
//                builder.setIcon(R.mipmap.ic_launcher);
//                builder.setMessage("?");
//                builder.setPositiveButton("RUN", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        Log.d(MainActivity.this.getString(R.string.debugTag), "DialogInterface.OnClickListener.onClick()");
//                    }
//                });
//                builder.show();
                /*
                아래방식으로 만들면 showDialog 내부적으로 맵을 가지고있어서
                다이얼로그가 없으면 onCreateDialog 를 호출하고
                이전에 생성된게 있으면 만들어졌던것을 반환한다.
                 */
                showDialog(MainActivity.BASIC_DIALOG);
            }
        });

        findViewById(R.id.buttonProgressDialog).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(MainActivity.PROGRESS_DIALOG);

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        for(int i=0; i<100; i++) {
                            try {
                                Thread.sleep(100);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            MainActivity.this.progressDialog.setProgress(i+1);
                        }
                        MainActivity.this.progressDialog.dismiss();
                    }
                }).start();
            }
        });
    }//END OF FUNCTION

    @Override
    protected Dialog onCreateDialog(int id) {
        switch(id) {
            case MainActivity.BASIC_DIALOG :
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Hello");
                builder.setIcon(R.mipmap.ic_launcher);
                builder.setMessage("?");
                builder.setPositiveButton("RUN", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Log.d(MainActivity.this.getString(R.string.debugTag), "DialogInterface.OnClickListener.onClick()");
                    }
                });
                return builder.create();
            case MainActivity.PROGRESS_DIALOG :
                MainActivity.this.progressDialog = new ProgressDialog(MainActivity.this);
                progressDialog.setTitle("PROGORESS DIALOG TITLE");
                progressDialog.setMessage("PROGRESS DIALOG MESSAGE");
                progressDialog.setIcon(android.R.drawable.progress_horizontal);
                progressDialog.setButton("CLOSE", (DialogInterface.OnClickListener) null);
                progressDialog.setMax(100);
                progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
//                progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                return progressDialog;
        }
        return super.onCreateDialog(id);
    }
}//END OF CLASS