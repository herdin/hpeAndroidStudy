package edu.jaen.android.ui.effList;

import edu.jaen.android.ui.effList.R;

import android.app.ListActivity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class EfficientListActivity extends ListActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setListAdapter(new EfficientAdapter(this));
    }
    
    private static class EfficientAdapter extends BaseAdapter {
        private LayoutInflater mInflater;
        private Bitmap mIcon1;
        private Bitmap mIcon2;

        public EfficientAdapter(Context context) {
            // Cache the LayoutInflate to avoid asking for a new one each time.
            mInflater = LayoutInflater.from(context);

            // Icons bound to the rows.
            mIcon1 = BitmapFactory.decodeResource(context.getResources(), R.drawable.icon48x48_1);
            mIcon2 = BitmapFactory.decodeResource(context.getResources(), R.drawable.icon48x48_2);
        }
        public int getCount() {
            return DATA.length;
        }
        public Object getItem(int position) {
            return position;
        }
        public long getItemId(int position) {
            return position;
        }
        public View getView(int position, View convertView, ViewGroup parent) {
            //Array adapter 의 튜닝예제
            ViewHolder holder;
            Log.d("getView", "posisiton : " + position + " : convertView : " + (convertView==null));
            //초기에 화면을 채울때까지는 convertView 가 null 로 들어온다.
            //inflate 로 View 를 새로 생성한다.
            if (convertView == null) {
                convertView = mInflater.inflate(R.layout.list_item_icon_text, null);

                holder = new ViewHolder();
                holder.text = (TextView) convertView.findViewById(R.id.text);
                holder.icon = (ImageView) convertView.findViewById(R.id.icon);

                convertView.setTag(holder); //이럴떄 쓰라고 둔 tag
            }
            //화면이 넘어가서 없어질 View 가 convertView 로 넘어오면 해당 View 를 재사용한다
            else {
                holder = (ViewHolder) convertView.getTag();
            }
            // Bind the data efficiently with the holder.
            holder.text.setText(DATA[position]);
            holder.icon.setImageBitmap((position & 1) == 1 ? mIcon1 : mIcon2);

            return convertView;
        }

        static class ViewHolder {
            TextView text;
            ImageView icon;
        }
    }

    private static final String[] DATA = {
            "Abbaye de Belloc", "Abbaye du Mont des Cats", "Abertam",
            "Baguette Laonnaise", "Bakers", "Baladi", "Balaton", "Bandal",
            "Cachaille", "Caciocavallo", "Caciotta", "Caerphilly",
            "Danish Fontina", "Daralagjazsky", "Dauphin", "Delice des Fiouves",
            "Emental Grand Cru", "Emlett", "Emmental", "Epoisses de Bourgogne",
            "Feta (Australian)", "Figue", "Filetta", "Fin-de-Siecle",
            "Manouri", "Manur", "Marble Cheddar", "Marbled Cheeses",
            "Neufchatel", "Neufchatel (Australian)", "Niolo", "Nokkelost",
            "Olivet Bleu", "Olivet Cendre", "Orkney Extra Mature Cheddar",
            "Panela", "Pannerone", "Pant ys Gawn", "Parmesan (Parmigiano)",
            "Pourly", "Prastost", "Pressato", "Prince-Jean",
            "Processed Cheddar", "Provolone", "Provolone (Australian)",
            "Quartirolo Lombardo", "Quatre-Vents", "Quercy Petit",
            "Ragusano", "Raschera", "Reblochon", "Red Leicester",
            "Richelieu", "Ricotta", "Ricotta (Australian)", "Ricotta Salata",
            "Saaland Pfarr", "Saanenkaese", "Saga", "Sage Derby",
            "Tasmania Highland Chevre Log", "Taupiniere", "Teifi", "Telemea",
            "Tomme de Romans", "Tomme de Savoie", "Tomme des Chouans",
            "Vacherin-Fribourgeois", "Valencay", "Vasterbottenost", "Venaco",
            "Waimata Farmhouse Blue", "Washed Rind Cheese (Australian)",
            "Yarra Valley Pyramid", "Yorkshire Blue", "Zamorano",
            "Zanetti Grana Padano", "Zanetti Parmigiano Reggiano"};
}
