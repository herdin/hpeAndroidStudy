package edu.jaen.android.app4.newframe;



import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

/**
 * FrameLayout
 *
 * @author SDJ
 */
public class SampleFrameLayoutActivity extends Activity {

	Button button01;
	ImageView imageView01;
	ImageView imageView02;
	int imageIndex = 0;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        button01 = (Button) findViewById(R.id.button01);
        imageView01 = (ImageView) findViewById(R.id.imageView01);
        imageView02 = (ImageView) findViewById(R.id.imageView02);

        button01.setOnClickListener(new OnClickListener() {
        	public void onClick(View v) {
        		changeImage();
        	}
        });

    }

    private void changeImage() {
    	if (imageIndex == 0) {
    		imageView01.setVisibility(View.VISIBLE);
    		imageView02.setVisibility(View.INVISIBLE);

    		imageIndex = 1;
    	} else if (imageIndex == 1) {
    		imageView01.setVisibility(View.INVISIBLE);
    		imageView02.setVisibility(View.VISIBLE);

    		imageIndex = 0;
    	}

    }

}