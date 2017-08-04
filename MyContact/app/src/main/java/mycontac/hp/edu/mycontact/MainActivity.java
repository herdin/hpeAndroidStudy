package mycontac.hp.edu.mycontact;

import android.content.ContentValues;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText nameInput;
    private EditText telInput;
    private ListView listView;
    //private DbManager dbManager;
    private int preSelectedItemPosition = -1;
    private int preSelectedItemId = -1;
    private View preSelectedView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Constants.printDebug(this, "MainActivity.onCreate()");

        setContentView(R.layout.activity_main);
        //this.dbManager = new DbManager(this);

        this.nameInput = (EditText)findViewById(R.id.nameInput);
        this.telInput = (EditText)findViewById(R.id.telInput);
        this.listView = (ListView)findViewById(R.id.listView);

        this.refreshListView();

        this.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View currentSelectedView, int position, long id) {
                Constants.printDebug(MainActivity.this, "AdapterView.OnItemClickListener.onItemClick() : POSITION : " + position);

                if(MainActivity.this.preSelectedView != null && !MainActivity.this.preSelectedView.equals(currentSelectedView)) {
                    Constants.printDebug(MainActivity.this, "AdapterView.OnItemClickListener.onItemClick() : DIFF");
                    MainActivity.this.preSelectedView.setBackgroundResource(android.R.color.white);
                }
                MainActivity.this.preSelectedItemPosition = position;
                MainActivity.this.preSelectedItemId = Integer.parseInt(((TextView)currentSelectedView.findViewById(R.id.idData)).getText().toString());
                MainActivity.this.preSelectedView = currentSelectedView;
                Constants.printDebug(MainActivity.this, "AdapterView.OnItemClickListener.onItemClick() : SELECTED ITEM POSITION/ID : "
                        + MainActivity.this.preSelectedItemPosition + "/" + MainActivity.this.preSelectedItemId);
                currentSelectedView.setBackgroundResource(android.R.color.holo_orange_light);
                MainActivity.this.nameInput.setText(((TextView)currentSelectedView.findViewById(R.id.nameData)).getText().toString());
                MainActivity.this.telInput.setText(((TextView)currentSelectedView.findViewById(R.id.telData)).getText().toString());
            }
        });

        this.listView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                Constants.printDebug(MainActivity.this, "AdapterView.OnItemSelectedListener.onItemSelected() : POSITION : " + position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Constants.printDebug(MainActivity.this, "AdapterView.OnItemSelectedListener.onNothingSelected()");
            }
        });

        findViewById(R.id.insertButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameInput = MainActivity.this.nameInput.getText().toString();
                String telInput = MainActivity.this.telInput.getText().toString();
                //MainActivity.this.dbManager.insert(nameInput, telInput);
                ContentValues contentValues = new ContentValues();
                contentValues.put(Constants.name, nameInput);
                contentValues.put(Constants.tel, telInput);
                getContentResolver().insert(Constants.cns_uri, contentValues);

                MainActivity.this.refreshListView();
            }
        });

        findViewById(R.id.updateButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int idInput = MainActivity.this.preSelectedItemId;
                if(idInput < 0) {
                    Toast.makeText(MainActivity.this, "SELECT ONE ITEM.", Toast.LENGTH_SHORT).show();
                    return;
                }
                String nameInput = MainActivity.this.nameInput.getText().toString();
                String telInput = MainActivity.this.telInput.getText().toString();
                //MainActivity.this.dbManager.update(idInput, nameInput, telInput);
                ContentValues contentValues = new ContentValues();
                contentValues.put(Constants._id, idInput);
                contentValues.put(Constants.name, nameInput);
                contentValues.put(Constants.tel, telInput);
                getContentResolver().update(Constants.cns_uri, contentValues, Constants._id + "=?", new String[]{MainActivity.this.preSelectedItemId+""});
                MainActivity.this.refreshListView();
                MainActivity.this.preSelectedItemId = -1;
                MainActivity.this.preSelectedItemPosition = -1;
                MainActivity.this.preSelectedView = null;
            }
        });

        findViewById(R.id.deleteButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int idInput = MainActivity.this.preSelectedItemId;
                if(idInput < 0) {
                    Toast.makeText(MainActivity.this, "SELECT ONE ITEM.", Toast.LENGTH_SHORT).show();
                    return;
                }
                //MainActivity.this.dbManager.delete(idInput);
                getContentResolver().delete(Constants.cns_uri, Constants._id + "=?", new String[]{MainActivity.this.preSelectedItemId+""});
                MainActivity.this.refreshListView();
                MainActivity.this.preSelectedItemId = -1;
                MainActivity.this.preSelectedItemPosition = -1;
                MainActivity.this.preSelectedView = null;
            }
        });
    }//END OF FUNCTION

    public void refreshListView() {
        //Cursor cursor = this.dbManager.query();
        Cursor cursor = managedQuery(Constants.cns_uri, null, null, null, null);
        SimpleCursorAdapter sca = new SimpleCursorAdapter(this, R.layout.row, cursor, new String[]{"_id", "name", "tel"}, new int[]{R.id.idData, R.id.nameData, R.id.telData}, 0);
        this.listView.setAdapter(sca);
    }//END OF FUNCTION

}//END OF CLASS