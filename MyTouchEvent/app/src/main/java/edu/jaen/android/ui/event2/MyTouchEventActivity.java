package edu.jaen.android.ui.event2;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

public class MyTouchEventActivity extends Activity {
	/** Called when the activity is first created. */
	int count = 0;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// setContentView(R.layout.main);
		View myView = new View(this);
		myView.setOnTouchListener(new MyTouchHandler());
		setContentView(myView);
	}

	class MyTouchHandler implements View.OnTouchListener {

		@Override
		public boolean onTouch(View v, MotionEvent event) {
			// TODO Auto-generated method stub
			if (event.getAction() == MotionEvent.ACTION_DOWN) {
				Toast.makeText(MyTouchEventActivity.this,
						"터치 이벤트 발생 : " + count++, Toast.LENGTH_LONG).show();
				return true;
			}
			return false;
		}

	}
}