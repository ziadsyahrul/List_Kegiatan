package com.ziadsyahrul.latihanroom.UI;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ziadsyahrul.latihanroom.R;
import com.ziadsyahrul.latihanroom.db.Constant;
import com.ziadsyahrul.latihanroom.db.ListDatabase;
import com.ziadsyahrul.latihanroom.model.KategoriModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UpdateKategoriActivity extends AppCompatActivity {

    @BindView(R.id.edtNamaKegiatan)
    EditText edtNamaKegiatan;
    @BindView(R.id.edtHari)
    EditText edtHari;
    @BindView(R.id.btnSimpan)
    Button btnSimpan;

    private Bundle bundle;

    private List<KategoriModel> kategoriModelList;
    private ListDatabase listDatabase;
    private int id_kategori;
    private String nama_hari, hari;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_kategori);
        ButterKnife.bind(this);

        setTitle("Update data");

        // menangkap data dari activity sebelumnya
        bundle = getIntent().getExtras();
        // penampung object list
        kategoriModelList = new ArrayList<>();
        // buat object database
        listDatabase = ListDatabase.createDatabase(this);

        // menampilkan data
        showData();
    }

    private void showData() {
        // mengambil data dari dalam bundle
        id_kategori = bundle.getInt(Constant.KEY_ID_KATEGORY);
        hari = bundle.getString(Constant.KEY_HARI);
        nama_hari = bundle.getString(Constant.KEY_NAMA_KEGIATAN);

        edtHari.setText(hari);
        edtNamaKegiatan.setText(nama_hari);
    }

    @OnClick(R.id.btnSimpan)
    public void onViewClicked() {
        // Mengambil data
        getData();
        // Mengirim data ke SQLite
        saveData();

        Toast.makeText(this, "Berhasil di Update !", Toast.LENGTH_SHORT).show();
        finish();
    }

    private void saveData() {
        // membuat object kategorymodel
        KategoriModel kategoriModel = new KategoriModel();
        // Memasukkan data ke dalam Kategorymodel
        kategoriModel.setId_kelas(id_kategori);
        kategoriModel.setTanggal(hari);
        kategoriModel.setNama_hari(nama_hari);
        // operasi update untuk mengupdate data ke Sqlite
        listDatabase.kelasDao().update(kategoriModel);
    }

    private void getData() {
        // mengambil inputan user dan dimasukkan didalam variable
        hari = edtHari.getText().toString();
        nama_hari = edtNamaKegiatan.getText().toString();
    }
}
