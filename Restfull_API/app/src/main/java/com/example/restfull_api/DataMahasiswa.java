    package com.example.restfull_api;

    public class DataMahasiswa {
        private String nim, nama, telp, email;

        public DataMahasiswa(String nim, String nama, String telp, String email) {
            this.nim = nim;
            this.nama = nama;
            this.telp = telp;
            this.email = email;
        }

        // Getter
        public String getNim() { return nim; }
        public String getNama() { return nama; }
        public String getTelp() { return telp; }
        public String getEmail() { return email; }
    }