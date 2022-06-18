package com.stevanuschristian.songdetails.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.stevanuschristian.songdetails.API.APIRequestData;
import com.stevanuschristian.songdetails.API.RetroServer;
import com.stevanuschristian.songdetails.Adapter.AdapterLagu;
import com.stevanuschristian.songdetails.Model.ModelLagu;
import com.stevanuschristian.songdetails.Model.ModelResponse;
import com.stevanuschristian.songdetails.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rvLagu;
    private FloatingActionButton fabTambahLagu;
    private ProgressBar pbLagu;

    private RecyclerView.Adapter adLagu;
    private RecyclerView.LayoutManager lmLagu;
    private List<ModelLagu> listLagu = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvLagu = findViewById(R.id.rv_lagu);
        fabTambahLagu = findViewById(R.id.fab_tambah_lagu);
        pbLagu = findViewById(R.id.pb_lagu);

        lmLagu = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvLagu.setLayoutManager(lmLagu);

        fabTambahLagu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, TambahActivity.class));
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        retrieveAllLagu();
    }

    public void retrieveAllLagu(){
        pbLagu.setVisibility(View.VISIBLE);

        APIRequestData API = RetroServer.konekRetrofit().create(APIRequestData.class);
        Call<ModelResponse> proses = API.ardRetrieveAllData();

        proses.enqueue(new Callback<ModelResponse>() {
            @Override
            public void onResponse(Call<ModelResponse> call, Response<ModelResponse> response) {
                int kode = response.body().getKode();
                String pesan = response.body().getPesan();
                listLagu = response.body().getData();

                adLagu = new AdapterLagu(MainActivity.this, listLagu);
                rvLagu.setAdapter(adLagu);
                adLagu.notifyDataSetChanged();

                pbLagu.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<ModelResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Gagal Menghubungi Server!", Toast.LENGTH_SHORT).show();
                pbLagu.setVisibility(View.GONE);
            }
        });
    }
}