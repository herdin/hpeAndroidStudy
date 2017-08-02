package hp.edu.mypaint;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private MyCanvas myCanvas;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.myCanvas = (MyCanvas) findViewById(R.id.myCanvas);
        findViewById(R.id.tvRed).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(getString(R.string.debugTag), "RED COLOR CLICKED");
                myCanvas.setPaintColor(Color.RED);
            }
        });
        findViewById(R.id.tvBlue).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(getString(R.string.debugTag), "BLUE COLOR CLICKED");
                myCanvas.setPaintColor(Color.BLUE);
            }
        });
        findViewById(R.id.tvGreen).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(getString(R.string.debugTag), "GREEN COLOR CLICKED");
                myCanvas.setPaintColor(Color.GREEN);
            }
        });

        this.editText = (EditText) findViewById(R.id.et);
        findViewById(R.id.et).setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if(keyEvent.getAction() == KeyEvent.ACTION_UP) {

                    float strokeWidth;

                    try {
                        strokeWidth = Float.parseFloat(String.valueOf(MainActivity.this.editText.getText()));
                    } catch(NumberFormatException e) {
                        strokeWidth = 0F;
                    }
                    Log.d(getString(R.string.debugTag), "STROKE WIDTH SET TO " + strokeWidth);
                    MainActivity.this.myCanvas.setStrokeWidth(strokeWidth);
                }
                return false;
            }
        });
    }//END OF FUNCTION

}//END OF CLASS