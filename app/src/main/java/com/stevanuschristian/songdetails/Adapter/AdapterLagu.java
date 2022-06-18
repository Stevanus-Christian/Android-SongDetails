package com.stevanuschristian.songdetails.Adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.stevanuschristian.songdetails.API.APIRequestData;
import com.stevanuschristian.songdetails.API.RetroServer;
import com.stevanuschristian.songdetails.Activity.MainActivity;
import com.stevanuschristian.songdetails.Activity.UbahActivity;
import com.stevanuschristian.songdetails.Model.ModelLagu;
import com.stevanuschristian.songdetails.Model.ModelResponse;
import com.stevanuschristian.songdetails.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdapterLagu extends RecyclerView.Adapter<AdapterLagu.HolderLagu> {
    private Context ctx;
    private List<ModelLagu> listLagu;

    public AdapterLagu(Context ctx, List<ModelLagu> listLagu){
        this.ctx = ctx;
        this.listLagu = listLagu;
    }

    @NonNull
    @Override
    public HolderLagu onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_lagu, parent, false);
        HolderLagu HL = new HolderLagu(view);

        return HL;
    }

    @Override
    public void onBindViewHolder(@NonNull HolderLagu holder, int position) {
        ModelLagu ML = listLagu.get(position);

        holder.tvID.setText(String.valueOf(ML.getId()));
        holder.tvJudul.setText(ML.getJudul());
        holder.tvPenyanyi.setText(ML.getPenyanyi());
        holder.tvAlbum.setText(ML.getAlbum());
        holder.tvAliran.setText(ML.getAliran());
    }

    @Override
    public int getItemCount() {
        if (listLagu != null) {
            return listLagu.size();
        }
        else {
            Toast.makeText(ctx, "Data Tidak Tersedia!", Toast.LENGTH_SHORT).show();
            return 0;
        }
    }

    public class HolderLagu extends RecyclerView.ViewHolder{
        TextView tvID, tvJudul, tvPenyanyi, tvAlbum, tvAliran;
        ImageView ivLagu;

        public HolderLagu(@NonNull View itemView) {
            super(itemView);

            tvID = itemView.findViewById(R.id.tv_id);
            tvJudul = itemView.findViewById(R.id.tv_judul);
            tvPenyanyi = itemView.findViewById(R.id.tv_penyanyi);
            tvAlbum = itemView.findViewById(R.id.tv_album);
            tvAliran = itemView.findViewById(R.id.tv_aliran);
            ivLagu = itemView.findViewById(R.id.iv_lagu);

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    AlertDialog.Builder pesan = new AlertDialog.Builder(ctx);
                    pesan.setTitle("Perhatian!");
                    pesan.setMessage("Operasi yang Anda inginkan?");
                    pesan.setCancelable(true);

                    pesan.setNegativeButton("Hapus", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            deleteLagu();
                            dialogInterface.dismiss();
                            ((MainActivity) ctx).retrieveAllLagu();
                        }
                    });

                    pesan.setPositiveButton("Ubah", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent kirimData = new Intent(ctx, UbahActivity.class);

                            kirimData.putExtra("xID", tvID.getText().toString());
                            kirimData.putExtra("xJudul", tvJudul.getText().toString());
                            kirimData.putExtra("xPenyanyi", tvPenyanyi.getText().toString());
                            kirimData.putExtra("xAlbum", tvAlbum.getText().toString());
                            kirimData.putExtra("xAliran", tvAliran.getText().toString());

                            ctx.startActivity(kirimData);
                        }
                    });

                    pesan.show();
                    return false;
                }
            });
        }

        private void deleteLagu(){
            int id = Integer.parseInt(tvID.getText().toString());

            APIRequestData API = RetroServer.konekRetrofit().create(APIRequestData.class);
            Call<ModelResponse> proses = API.ardDeleteData(id);

            proses.enqueue(new Callback<ModelResponse>() {
                @Override
                public void onResponse(Call<ModelResponse> call, Response<ModelResponse> response) {
                    int kode = response.body().getKode();
                    String pesan = response.body().getPesan();

                    if(kode == 1){
                        Toast.makeText(ctx, "Selamat! Sukses Menghapus Data Lagu", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(ctx, "Perhatian! Sukses Menghapus Data Lagu", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ModelResponse> call, Throwable t) {
                    Toast.makeText(ctx, "Gagal Menghubungi Server!", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
