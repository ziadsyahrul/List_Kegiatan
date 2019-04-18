package com.ziadsyahrul.latihanroom.UI;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ziadsyahrul.latihanroom.R;
import com.ziadsyahrul.latihanroom.db.ListDatabase;
import com.ziadsyahrul.latihanroom.model.KategoriModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TambahKategoriActivity extends AppCompatActivity {

    @BindView(R.id.edtNamaHari)
    EditText edtNamaHari;
    @BindView(R.id.edtHari)
    EditText edtTanggal;
    @BindView(R.id.btnSimpan)
    Button btnSimpan;

    // TODO 1
    private ListDatabase listDatabase;

    private String namaHari, tanggal;
    private int idCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_kategori);
        ButterKnife.bind(this);

        // TODO 2
        setTitle("Add Todo");

        // TODO 3
        listDatabase = ListDatabase.createDatabase(this);
    }
    @OnClick(R.id.btnSimpan)
    public void onViewClicked() {
        // TODO 4
        getData();
        // TODO 5 menyimpan data ke SQLite
        saveData();

        clearData();

        Snackbar.make(btnSimpan, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();

        Toast.makeText(this, "Berhasil disimpan", Toast.LENGTH_SHORT).show();
        finish();


    }

    private void clearData() {
        edtNamaHari.setText(" ");
        edtTanggal.setText(" ");
    }

    private void saveData() {
        // object kategorymodel untuk menampung data
        KategoriModel kategoriModel = new KategoriModel();
        // masukkan data ke dalam kategorymodel
        kategoriModel.setNama_hari(tanggal);
        kategoriModel.setTanggal(namaHari);
        // melakukan operasi insert menggunakan siswadatabase
        listDatabase.kelasDao().insert(kategoriModel);
    }

    private void getData() {
        namaHari = edtNamaHari.getText().toString();
        tanggal = edtTanggal.getText().toString();
    }
}
