package kaptenpotato.pilihpilih;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class GridActivity extends AppCompatActivity {
    private List<String> nama;
    private List<String> desc;
    private List<String> item;
    private GridView gridView;
    private MyAdapter myAdapter;

    int penghitung;
    int hitung;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid);
        nama = new ArrayList<String>();


        gridView = (GridView) findViewById(R.id.gridView);
        //nama = new ArrayList<String>();

        nama = getIntent().getStringArrayListExtra("nama");


        //desc = new ArrayList<String>();
        desc = getIntent().getStringArrayListExtra("desc");
        penghitung = (getIntent().getExtras().getInt("penghitung"));
        hitung = (getIntent().getExtras().getInt("hitung"));


        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(GridActivity.this, "Klik di " + nama.get(i), Toast.LENGTH_LONG).show();
            }
        });
        //Menyambungkan dengan adapter
        myAdapter = new MyAdapter(this, R.layout.grid_item, nama, desc);
        gridView.setAdapter(myAdapter);
        registerForContextMenu(gridView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.action_bar_menu, menu);
        MenuItem itemToHide = menu.findItem(R.id.switch_gridview);
        itemToHide.setVisible(false);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.switch_listview:
                Intent intent = new Intent(GridActivity.this, ListActivity.class);
                intent.putStringArrayListExtra("nama", (ArrayList<String>) nama);
                intent.putStringArrayListExtra("desc", (ArrayList<String>) desc);
                intent.putExtra("hitung", hitung);
                intent.putExtra("penghitung", penghitung);
                startActivity(intent);
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        menu.setHeaderTitle(this.nama.get(info.position));
        inflater.inflate(R.menu.context_menu, menu);
    }
}

