package com.jaen.provider.calllog;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CallLog;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.SimpleCursorAdapter.ViewBinder;
import android.widget.TextView;

public class CallLogActivity extends ListActivity {
	private Cursor c;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		c = managedQuery(CallLog.Calls.CONTENT_URI, null, null, null, null);

		//if(c!=null) detailCallLog(c);
		
		String[] from = new String[] { CallLog.Calls.NUMBER, CallLog.Calls.TYPE };
		int[] to = new int[] { android.R.id.text1, android.R.id.text2 };

		SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, 
			android.R.layout.simple_list_item_2, c, from, to,1);

		adapter.setViewBinder(new ViewBinder() {
			@Override
			public boolean setViewValue(View view, Cursor cursor, int columnIndex) {
				switch (view.getId()) {
					case android.R.id.text2:
						TextView tv = (TextView) view;
						int value = cursor.getInt(cursor.getColumnIndex(CallLog.Calls.TYPE));
						switch (value) {
							case CallLog.Calls.INCOMING_TYPE:
								tv.setText("����");
								break;
							case CallLog.Calls.MISSED_TYPE:
								tv.setText("������ ��ȭ");
								break;
							case CallLog.Calls.OUTGOING_TYPE:
								tv.setText("�߽�");
								break;
						}
						return true;
				}
				return false;
			}
		});
		setListAdapter(adapter);
	}
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		
		c.moveToPosition(position);
		String s=c.getString(c.getColumnIndexOrThrow(CallLog.Calls.NUMBER));
        Intent i = new Intent(Intent.ACTION_DIAL, Uri.parse("tel://"+s));
        startActivity(i);
	
		//Uri uri=ContentUris.withAppendedId(CallLog.Calls.CONTENT_URI, id);
		//Intent i=new Intent(Intent.ACTION_VIEW, uri);
		//startActivity(i);
	}
	private void detailCallLog(Cursor c) {
		int col=c.getColumnCount();
		Log.e("loglog","c.getColumnCount()="+col);

		for(int i=0; i<col; i++){
			Log.e("loglog",i+" "+c.getColumnName(i));
		}
		
		int row=c.getCount();
		Log.e("loglog",".");
		Log.e("loglog","c.getCount()="+row);
		StringBuilder sb=new StringBuilder();
		while(c.moveToNext()){
			for(int i=0; i<col; i++){
				sb.append(c.getString(i)+"\t");
			}
			Log.e("loglog",sb.toString());
			sb.setLength(0);
		}
	}
}













