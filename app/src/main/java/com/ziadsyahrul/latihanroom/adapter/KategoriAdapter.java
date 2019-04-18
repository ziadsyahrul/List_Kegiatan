package com.ziadsyahrul.latihanroom.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.ziadsyahrul.latihanroom.R;
import com.ziadsyahrul.latihanroom.UI.MainActivity;
import com.ziadsyahrul.latihanroom.UI.UpdateKategoriActivity;
import com.ziadsyahrul.latihanroom.UI.list.MainListActivity;
import com.ziadsyahrul.latihanroom.db.Constant;
import com.ziadsyahrul.latihanroom.db.ListDatabase;
import com.ziadsyahrul.latihanroom.model.KategoriModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class KategoriAdapter extends RecyclerView.Adapter<KategoriAdapter.ViewHolder> {

    private final Context context;

    private final List<KategoriModel> kategoriModelList;

    private ListDatabase listDatabase;
    private Bundle bundle;
    private AlertDialog alertDialog;

    public KategoriAdapter(Context context, List<KategoriModel> kategoriModelList) {
        this.context = context;
        this.kategoriModelList = kategoriModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.list_category, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {
        final KategoriModel kategoriModel = kategoriModelList.get(i);
        viewHolder.txtHari.setText(kategoriModel.getNama_hari());
        viewHolder.txtCategory.setText(kategoriModel.getTanggal());

        ColorGenerator generator = ColorGenerator.MATERIAL;
        int color = generator.getRandomColor();

        viewHolder.cvKategory.setCardBackgroundColor(color);
        viewHolder.overflow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Object database
                listDatabase = listDatabase.createDatabase(context);
                // object pop up menu
                PopupMenu popupMenu = new PopupMenu(view.getContext(), view);
                // Inflate menu ke dalam popup menu
                popupMenu.inflate(R.menu.popup_menu);
                // Menampilkan menu
                popupMenu.show();

                viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(context, MainListActivity.class);
                    }
                });

                // onClick pada salah satu menu pada popup menu
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getItemId()){
                            case R.id.delete:

                                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                                alertDialogBuilder.setMessage("Are you sure to Delete" + kategoriModel.getTanggal() +" ?");
                                alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int position) {
                                        // operasi delete data
                                        listDatabase.kelasDao().delete(kategoriModel);
                                        // Menghapus data yang telah dihapus pada list
                                        kategoriModelList.remove(i);
                                        // Memberi tahu bahwa data sudah hilang
                                        notifyItemRemoved(i);
                                        notifyItemRangeChanged(0, kategoriModelList.size());

                                        Toast.makeText(context, "Berhasil di hapus", Toast.LENGTH_SHORT).show();
                                    }
                                });

                                alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {

                                    }
                                });
                                AlertDialog alertDialog = alertDialogBuilder.create();
                                alertDialog.show();

                                break;

                            case R.id.edit:
                                // object Bundle
                                bundle = new Bundle();

                                // isi bundle dengan data
                                bundle.putInt(Constant.KEY_ID_KATEGORY, kategoriModel.getId_kelas());
                                bundle.putString(Constant.KEY_HARI, kategoriModel.getTanggal());
                                bundle.putString(Constant.KEY_NAMA_KEGIATAN, kategoriModel.getNama_hari());

                                context.startActivity(new Intent(context, UpdateKategoriActivity.class).putExtras(bundle));
                                break;
                        }

                        return false;
                    }
                });
            }
        });

        // pindah ke MainListActivity
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bundle = new Bundle();
                bundle.putInt(Constant.KEY_ID_KATEGORY, kategoriModel.getId_kelas());
                bundle.putString(Constant.KEY_NAMA_KEGIATAN, kategoriModel.getNama_hari());
                context.startActivity(new Intent(context, MainListActivity.class).putExtras(bundle));
            }
        });

    }

    @Override
    public int getItemCount() {
        return kategoriModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.txt_category)
        TextView txtCategory;
        @BindView(R.id.txt_hari)
        TextView txtHari;
        @BindView(R.id.overflow)
        ImageButton overflow;
        @BindView(R.id.cv_kategory)
        CardView cvKategory;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
