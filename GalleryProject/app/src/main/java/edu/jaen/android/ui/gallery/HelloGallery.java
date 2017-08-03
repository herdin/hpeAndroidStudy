package edu.jaen.android.ui.gallery;

import edu.jaen.android.ui.gallery.R;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class HelloGallery extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Gallery g = (Gallery) findViewById(R.id.gallery);
        g.setAdapter(new ImageAdapter(this));

        g.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                //Toast.makeText(HelloGallery.this, "" + position, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent();
                switch(position) {
                    case 0 :
                        ComponentName componentName = new ComponentName("edu.jaen.android.ui.effList", "edu.jaen.android.ui.effList.EfficientListActivity");
                        intent.setComponent(componentName);
                        break;
                    case 1 :
                        intent.setAction(Intent.ACTION_CALL);
                        intent.setData(Uri.parse("tel://01044445555"));
                        break;
                    case 2 :
                        intent.setAction(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse("http://www.naver.com"));
                        break;
                    case 3 :
                        intent.setAction(Intent.ACTION_MAIN);
                        intent.addCategory(Intent.CATEGORY_DEFAULT);
                        intent.addCategory(Intent.CATEGORY_HOME);
                        break;
                    case 4 :
                        intent.setAction("lgcns.hello");
                        intent.addCategory("lgcns.category");
                        break;
                }
                HelloGallery.this.startActivity(intent);
            }
        });
    }
    public class ImageAdapter extends BaseAdapter {
        private Context mContext;
        private Integer[] mImageIds = {
                R.drawable.sample_1,
                R.drawable.sample_2,
                R.drawable.sample_3,
                R.drawable.sample_4,
                R.drawable.sample_5,
                R.drawable.sample_6,
                R.drawable.sample_7
        };
        public ImageAdapter(Context c) {
            mContext = c;
         }
        public int getCount() {
            return mImageIds.length;
        }
        public Object getItem(int position) {
            return position;
        }
        public long getItemId(int position) {
            return position;
        }
        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView i = new ImageView(mContext);
            i.setImageResource(mImageIds[position]);
            i.setLayoutParams(new Gallery.LayoutParams(150, 100));
            i.setScaleType(ImageView.ScaleType.FIT_XY);
            return i;
        }
    }
}

