package com.stevanuschristian.songdetails.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.stevanuschristian.songdetails.API.APIRequestData;
import com.stevanuschristian.songdetails.API.RetroServer;
import com.stevanuschristian.songdetails.Model.ModelResponse;
import com.stevanuschristian.songdetails.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TambahActivity extends AppCompatActivity {
    private EditText etJudul, etPenyanyi, etAlbum, etAliran;
    Button btnSimpan;
    private String judul, penyanyi, album, aliran;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah);

        initView();

        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                judul = etJudul.getText().toString();
                penyanyi = etPenyanyi.getText().toString();
                album = etAlbum.getText().toString();
                aliran = etAliran.getText().toString();

                if(judul.trim().equals("")){
                    etJudul.setError("Judul Lagu Harus Diisi!");
                }
                else if(penyanyi.trim().equals("")){
                    etPenyanyi.setError("Penyanyi Harus Diisi!");
                }
                else if(album.trim().equals("")){
                    etAlbum.setError("Album Harus Diisi!");
                }
                else if(aliran.trim().equals("")){
                    etAliran.setError("Aliran Harus Diisi!");
                }
                else {
                    createLagu();
                }
            }
        });
    }

    private void initView(){
        etJudul = findViewById(R.id.et_judul);
        etPenyanyi = findViewById(R.id.et_penyanyi);
        etAlbum = findViewById(R.id.et_album);
        etAliran = findViewById(R.id.et_aliran);

        btnSimpan = findViewById(R.id.btn_simpan);
    }

    private void createLagu(){
        APIRequestData API = RetroServer.konekRetrofit().create(APIRequestData.class);
        Call<ModelResponse> proses = API.ardCreateData(judul, penyanyi, album, aliran);

        proses.enqueue(new Callback<ModelResponse>() {
            @Override
            public void onResponse(Call<ModelResponse> call, Response<ModelResponse> response) {
                int kode = response.body().getKode();
                String pesan = response.body().getPesan();

                if(kode == 1){
                    Toast.makeText(TambahActivity.this, "Selamat! Sukses Menambah Data Lagu", Toast.LENGTH_SHORT).show();
                    finish();
                }
                else {
                    Toast.makeText(TambahActivity.this, "Perhatian! Gagal Menambah Data Lagu", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ModelResponse> call, Throwable t) {
                Toast.makeText(TambahActivity.this, "Gagal Menghubungi Server!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}