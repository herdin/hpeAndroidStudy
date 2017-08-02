package hp.edu.mypaint;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    MyCanvas mc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.mc = (MyCanvas) findViewById(R.id.myCanvas);
//        mc.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(MainActivity.this, MainActivity.this.mc.getMyText(), Toast.LENGTH_SHORT).show();
//            }//END OF FUCNTION
//        });

    }//END OF FUNCTION

}//END OF CLASS