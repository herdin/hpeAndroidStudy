package edu.jaen.android.ui.cusRow;

import android.app.Activity;
import android.content.Context;
import android.media.Image;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import static edu.jaen.android.ui.cusRow.Constants.COUNTRIES;
import static edu.jaen.android.ui.cusRow.Constants.imageData;

/**
 * ListView 조회내용을 xml파일에 정의하여 반복한다.
 * 
 */
public class HelloListView extends Activity {
	/** Called when the activity is first created. */

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		ListView listView = (ListView) findViewById(R.id.list);
		ArrayAdapter adapter = new MyAdapter(this, R.layout.row, 0, COUNTRIES);
		listView.setAdapter(adapter);

		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
				TextView textView = (TextView)view.findViewById(R.id.name);
				//Toast.makeText(HelloListView.this, textView.getText().toString() + " : " + R.id.name, Toast.LENGTH_SHORT).show();
				//토스트를 이미지뷰로 변경
				View toastView = View.inflate(HelloListView.this, R.layout.row_image, null);

				ImageView toastImageView = (ImageView)toastView.findViewById(R.id.rowImage);
				toastImageView.setImageResource(Constants.imageData[position%Constants.imageData.length]);
				TextView toastTextView = (TextView)toastView.findViewById(R.id.rowText);
				toastTextView.setText(textView.getText().toString());

				Toast toast = Toast.makeText(HelloListView.this, textView.getText().toString() + " : " + R.id.name, Toast.LENGTH_SHORT);
				toast.setGravity(Gravity.CENTER_VERTICAL, -100, -200);
				toast.setView(toastView);
				toast.show();
			}
		});

	}//END OF CLASS

	class MyAdapter extends ArrayAdapter {
		Context context;
		int resource;
		Object[] datas;
		public MyAdapter(Context context, int resource, int textViewResourceId, Object[] objects) {
			super(context, resource, textViewResourceId, objects);
			this.context = context;
			this.resource = resource;
			this.datas = objects;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			//layout xml 을 로딩, 파싱, 리플렉션으로 객체화 시켜줌
			LayoutInflater inf = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			//LayoutInflater inf2 = LayoutInflater.from(context);
			//View view0 = View.inflate(context, resource, null);

			View view = inf.inflate(resource, null);
			TextView tv = (TextView) view.findViewById(R.id.name);
			//tv.setText(COUNTRIES[position]);
			tv.setText((CharSequence) this.datas[position]);
			ImageView imageView = (ImageView)view.findViewById(R.id.image);
			imageView.setImageResource(Constants.imageData[position%Constants.imageData.length]);
			return view;
		}
	}//END OF FUNCTION
}//END OF CLASS
