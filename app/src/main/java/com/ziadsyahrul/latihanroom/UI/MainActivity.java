package com.ziadsyahrul.latihanroom.UI;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.ziadsyahrul.latihanroom.R;
import com.ziadsyahrul.latihanroom.adapter.KategoriAdapter;
import com.ziadsyahrul.latihanroom.db.ListDatabase;
import com.ziadsyahrul.latihanroom.model.KategoriModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.rv_List)
    RecyclerView rvList;

    // TODO 1
    private ListDatabase listDatabase;
    private List<KategoriModel> kategoriModelList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // TODO 2
        listDatabase = ListDatabase.createDatabase(this);

        // object list
        kategoriModelList = new ArrayList<>();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               startActivity(new Intent(MainActivity.this, TambahKategoriActivity.class));
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Mengosongkan list agar list dapat dipakai dengan data yang baru
        kategoriModelList.clear();
        // Mengambil data dari sqlite
        getData();
        // Mensetting dan proses menampilkan data ke recyclerview
        rvList.setLayoutManager(new GridLayoutManager(this, 1));
        rvList.setAdapter(new KategoriAdapter(this, kategoriModelList));

    }

    private void getData() {
        // mengambil data dari database
        kategoriModelList = listDatabase.kelasDao().select();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
