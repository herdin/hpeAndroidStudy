package hp.edu.uitest;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity /* 이벤트처리1방식 implements OnClickListener*/{

    private TextView tv;
    private Button b;
    private Button b2;
    private EditText et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.tv = (TextView) findViewById(R.id.tv);
        this.b = (Button) findViewById(R.id.button);
        this.b2 = (Button) findViewById(R.id.button2);
        this.et = (EditText) findViewById(R.id.et);

        //이벤트처리1방식
//        b.setOnClickListener(this);

        //이벤트처리2방식
//        MyClick mc = new MyClick();
//        b.setOnClickListener(mc);
//        b2.setOnClickListener(mc);

        //이벤트처리3방식
        b.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                tv.setText(et.getText());
            }//END OF FUNCTION
        });
        b2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                et.setText("");
            }//END OF FUNCTION
        });
        et.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                Log.d(getString(R.string.debugTag), "onKey() : " + i + ", "  + keyEvent.getAction());
                if(keyEvent.getAction() == KeyEvent.ACTION_DOWN) {
                    if(i == KeyEvent.KEYCODE_ENTER) {
                        Toast.makeText(view.getContext(), et.getText(), Toast.LENGTH_SHORT).show();
                        b.callOnClick();
                        b2.callOnClick();
                        return true; //기본이벤트를 실행하지 않음
                    }
                }
                return false; //기본이벤트를 실행함
            }//END OF FUNCTION
        });

        findViewById(R.id.layout1).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                Log.d(getString(R.string.debugTag), "onTouch() : " + motionEvent.getX() + ", " + motionEvent.getY());
                switch(motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN :
                        return true;
                    case MotionEvent.ACTION_UP :
                        break;
                    case MotionEvent.ACTION_MOVE :
                        break;
                    default :
                        break;
                }
                return false;
            }
        });
    }//END OF FUNCTION

    @Override
    protected void onResume() {
        super.onResume();
    }//END OF FUNCTION

//    이벤트처리1방식
//    @Override
//    public void onClick(View view) {
//        tv.setText(et.getText());
//        et.setText("");
//    }//END OF FUNCTION

//    이벤트처리2방식
//    class MyClick implements OnClickListener {
//        @Override
//        public void onClick(View view) {
//            Log.d(getString(R.string.debugTag), String.valueOf(view.getId()));
//            switch(view.getId()) {
//                case R.id.button :
//                    tv.setText(et.getText());
//                    break;
//                case R.id.button2 :
//                    et.setText("");
//                    break;
//                default :
//                    break;
//            }
//        }//END OF FUNCTION
//    }//END OF CLASS

//    이벤트처리4방식
    public void click4(View view) {
        Log.d(getString(R.string.debugTag), "click4");
        tv.setText(et.getText());
        et.setText("");
    }//END OF FUNCTION

}//END OF CLASS
