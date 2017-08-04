package mycontac.hp.edu.mycontact;

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
    private DbManager dbManager;
    private int currentSelectedItemPosition = -1;
    private int currentSelectedItemId = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Constants.printDebug(this, "MainActivity.onCreate()");

        setContentView(R.layout.activity_main);
        this.dbManager = new DbManager(this);

        this.nameInput = (EditText)findViewById(R.id.nameInput);
        this.telInput = (EditText)findViewById(R.id.telInput);
        this.listView = (ListView)findViewById(R.id.listView);

        this.refreshListView();

        this.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Constants.printDebug(MainActivity.this, "AdapterView.OnItemClickListener.onItemClick() : POSITION : " + position);
                MainActivity.this.currentSelectedItemPosition = position;
                MainActivity.this.currentSelectedItemId = Integer.parseInt(((TextView)view.findViewById(R.id.idData)).getText().toString());
                Constants.printDebug(MainActivity.this, "AdapterView.OnItemClickListener.onItemClick() : SELECTED ITEM POSITION/ID : "
                        + MainActivity.this.currentSelectedItemPosition + "/" + MainActivity.this.currentSelectedItemId);
                view.setBackgroundResource(android.R.color.holo_orange_light);

                MainActivity.this.nameInput.setText(((TextView)view.findViewById(R.id.nameData)).getText().toString());
                MainActivity.this.telInput.setText(((TextView)view.findViewById(R.id.telData)).getText().toString());
            }
        });

        findViewById(R.id.insertButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameInput = MainActivity.this.nameInput.getText().toString();
                String telInput = MainActivity.this.telInput.getText().toString();
                MainActivity.this.dbManager.insert(nameInput, telInput);
                MainActivity.this.refreshListView();
            }
        });

        findViewById(R.id.updateButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int idInput = MainActivity.this.currentSelectedItemId;
                if(idInput < 0) {
                    Toast.makeText(MainActivity.this, "SELECT ONE ITEM.", Toast.LENGTH_SHORT).show();
                    return;
                }
                String nameInput = MainActivity.this.nameInput.getText().toString();
                String telInput = MainActivity.this.telInput.getText().toString();
                MainActivity.this.dbManager.update(idInput, nameInput, telInput);
                MainActivity.this.refreshListView();
                MainActivity.this.currentSelectedItemId = -1;
            }
        });

        findViewById(R.id.deleteButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int idInput = MainActivity.this.currentSelectedItemId;
                if(idInput < 0) {
                    Toast.makeText(MainActivity.this, "SELECT ONE ITEM.", Toast.LENGTH_SHORT).show();
                    return;
                }
                MainActivity.this.dbManager.delete(idInput);
                MainActivity.this.refreshListView();
                MainActivity.this.currentSelectedItemId = -1;
            }
        });
    }//END OF FUNCTION

    public void refreshListView() {
        Cursor cursor = this.dbManager.query();
        SimpleCursorAdapter sca = new SimpleCursorAdapter(this, R.layout.row, cursor, new String[]{"_id", "name", "tel"}, new int[]{R.id.idData, R.id.nameData, R.id.telData}, 0);
        this.listView.setAdapter(sca);
    }//END OF FUNCTION

}//END OF CLASS