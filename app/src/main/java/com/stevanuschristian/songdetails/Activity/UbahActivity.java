package com.stevanuschristian.songdetails.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

public class UbahActivity extends AppCompatActivity {
    private EditText etJudul, etPenyanyi, etAlbum, etAliran;
    private Button btnUbah;
    private String xID, yJudul, yPenyanyi, yAlbum, yAliran, xJudul, xPenyanyi, xAlbum, xAliran;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubah);

        initView();

        Intent terimaData = getIntent();
        xID = terimaData.getStringExtra("xID");
        xJudul = terimaData.getStringExtra("xJudul");
        xPenyanyi = terimaData.getStringExtra("xPenyanyi");
        xAlbum = terimaData.getStringExtra("xAlbum");
        xAliran = terimaData.getStringExtra("xAliran");

        etJudul.setText(xJudul);
        etPenyanyi.setText(xPenyanyi);
        etAlbum.setText(xAlbum);
        etAliran.setText(xAliran);

        btnUbah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                yJudul = etJudul.getText().toString();
                yPenyanyi = etPenyanyi.getText().toString();
                yAlbum = etAlbum.getText().toString();
                yAliran = etAliran.getText().toString();

                if(yJudul.trim().equals("")){
                    etJudul.setError("Judul Lagu Harus Diisi!");
                }
                else if(yPenyanyi.trim().equals("")){
                    etPenyanyi.setError("Penyanyi Harus Diisi!");
                }
                else if(yAlbum.trim().equals("")){
                    etAlbum.setError("Album Harus Diisi!");
                }
                else if(yAliran.trim().equals("")){
                    etAliran.setError("Aliran Harus Diisi!");
                }
                else {
                    updateLagu();
                }
            }
        });
    }

    private void initView(){
        etJudul = findViewById(R.id.et_judul);
        etPenyanyi = findViewById(R.id.et_penyanyi);
        etAlbum = findViewById(R.id.et_album);
        etAliran = findViewById(R.id.et_aliran);

        btnUbah = findViewById(R.id.btn_ubah);
    }

    private void updateLagu(){
        APIRequestData API = RetroServer.konekRetrofit().create(APIRequestData.class);
        Call<ModelResponse> proses = API.ardUpdateData(xID, yJudul, yPenyanyi, yAlbum, yAliran);

        proses.enqueue(new Callback<ModelResponse>() {
            @Override
            public void onResponse(Call<ModelResponse> call, Response<ModelResponse> response) {
                int kode = response.body().getKode();
                String pesan = response.body().getPesan();

                if(kode == 1){
                    Toast.makeText(UbahActivity.this, "Selamat! Sukses Mengubah Data Lagu", Toast.LENGTH_SHORT).show();
                    finish();
                }
                else {
                    Toast.makeText(UbahActivity.this, "Perhatian! Gagal Mengubah Data Lagu", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ModelResponse> call, Throwable t) {
                Toast.makeText(UbahActivity.this, "Gagal Menghubungi Server!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}