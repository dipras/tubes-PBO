package pkg;

import java.util.Date;

public class Stock {
    private int id;
    private String nama;
    private String category;
    private Date created_at;
    private Date updated_at;
    private int harga;

    public Stock(int id, String nama, String category, Date created_at, Date updated_at, int harga) {
        this.id = id;
        this.nama = nama;
        this.category = category;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.harga = harga;
    }

    public int getId() {
        return id;
    }
    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.updated_at = new Date();
        this.nama = nama;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.updated_at = new Date();
        this.category = category;
    }

    public Date getCreatedAt() {
        return created_at;
    }

    public Date getUpdatedAt() {
        return updated_at;
    }

    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
        this.updated_at = new Date();
        this.harga = harga;
    }
}