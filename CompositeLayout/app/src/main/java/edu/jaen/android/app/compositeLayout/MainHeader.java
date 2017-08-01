package edu.jaen.android.app.compositeLayout;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class MainHeader implements OnClickListener {

	private Activity activity;

	public MainHeader(Activity act) {
		this.activity = act;
	}

	public void init() {

		activity.findViewById(R.id.mailImgBut).setOnClickListener(this);
		activity.findViewById(R.id.gallImgBut).setOnClickListener(this);
		activity.findViewById(R.id.uplaodImgBut).setOnClickListener(this);
		activity.findViewById(R.id.shareImgBut).setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.mailImgBut:
			Toast.makeText(activity, "Mail Image Button Click..",
					Toast.LENGTH_SHORT).show();
			break;
		case R.id.gallImgBut:
			showImageData();
			break;
		case R.id.uplaodImgBut:
			Toast.makeText(activity, "upLoad Image Button Click..",
					Toast.LENGTH_SHORT).show();
			break;
		case R.id.shareImgBut:
			shareData();
			break;

		default:
			break;
		}
	}

	private void shareData() {

		String mytitle = "SDJ";
		String myText = "SDJ Share...";
		Intent localIntent4 = new Intent("android.intent.action.SEND");
		localIntent4.setType("text/plain");
		localIntent4.putExtra("android.intent.extra.TEXT", myText);
		if ((mytitle != null) && (mytitle.length() > 0))
			localIntent4.putExtra("android.intent.extra.SUBJECT", mytitle);
		activity.startActivity(Intent.createChooser(localIntent4, "Share to"));

	}

	private void showImageData() {
		Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
		intent.setType("image/*");
		activity.startActivityForResult(intent, 1);
	}

}
