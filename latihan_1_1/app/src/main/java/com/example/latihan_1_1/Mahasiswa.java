package com.example.latihan_1_1;

public class Mahasiswa {
    private String nim;
    private String nama;
    private String fotoUrl;

    public Mahasiswa(String nim, String nama, String fotoUrl) {
        this.nim = nim;
        this.nama = nama;
        this.fotoUrl = fotoUrl;
    }

    public String getNim() {
        return nim;
    }

    public String getNama() {
        return nama;
    }

    public String getFotoUrl() {
        return fotoUrl;
    }
}
