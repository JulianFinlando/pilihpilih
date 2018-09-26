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
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import kaptenpotato.pilihpilih.MyAdapter;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {
    private ListView listView;
    private List<String> nama;
    private List<String> desc;
    //private List<String> item;
    private MyAdapter myAdapter;

    private int penghitung = 0;
    private int hitung = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gridlist);

        listView = (ListView) findViewById(R.id.listView);

        //item = getIntent().getStringArrayListExtra("item");

       // if (item == null) {
            nama = new ArrayList<String>();
            nama.add("Xiaomi Redmi Note 5 Plus 12345678901234567890123456789012345678901234567890");
            nama.add("iPhone XS");
            nama.add("Nokia 6");
            nama.add("Vivo Y83");


            desc = new ArrayList<String>();
            desc.add("HP kok iklan");
            desc.add("Mending beli ROG njir");
            desc.add("Buat ngelempar segawon");
            desc.add("Cowok kok beli Vivo, bencong ya lu");


            //Tampilan visual data
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, nama);
            //Link adapter untuk List
            listView.setAdapter(adapter);
        /*} else {
            nama = getIntent().getStringArrayListExtra("nama");
            desc = getIntent().getStringArrayListExtra("desc");
            penghitung = (getIntent().getExtras().getInt("penghitung"));
            hitung = (getIntent().getExtras().getInt("hitung"));
        }*/


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(ListActivity.this, "Klik di " + nama.get(i), Toast.LENGTH_LONG).show();
            }
        });

        //Link custom adapter
        myAdapter = new MyAdapter(this, R.layout.list_item, nama, desc);
        listView.setAdapter(myAdapter);
        registerForContextMenu(listView);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.action_bar_menu, menu);
        MenuItem itemToHide = menu.findItem(R.id.switch_listview);
        itemToHide.setVisible(false);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.switch_gridview:
                Intent intent = new Intent(ListActivity.this, GridActivity.class);
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

