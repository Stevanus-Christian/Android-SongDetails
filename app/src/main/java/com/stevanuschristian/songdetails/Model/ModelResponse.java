package com.stevanuschristian.songdetails.Model;

import java.util.List;

public class ModelResponse {
    int kode;
    String pesan;
    List<ModelLagu> data;

    public int getKode() {
        return kode;
    }

    public String getPesan() {
        return pesan;
    }

    public List<ModelLagu> getData() {
        return data;
    }
}
