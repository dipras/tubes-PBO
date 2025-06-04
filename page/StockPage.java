package page;

import java.util.ArrayList;
import java.util.Date;
import pkg.Stock;
import util.Util;

public class StockPage {
    static ArrayList<Stock> listStock = new ArrayList<>();

    public static void view() {
        System.out.println("1. List Stock\n2. Tambah Stock \n3. Update Stock\n4. Hapus Stock");
        listStock.add(new Stock(1, "Nasi Goreng", "Makanan Berat", new Date(), new Date(), 20000));
        listStock.add(new Stock(2, "Bakso", "Makanan Berat", new Date(), new Date(), 18000));
        listStock.add(new Stock(3, "Es Teh", "Minuman", new Date(), new Date(), 5000));
        listStock.add(new Stock(4, "Pisang Goreng", "Cemilan", new Date(), new Date(), 8000));
        int choice = 0;

        while (choice != 9) {
            System.out.print("Masukan pilihan:");
            choice = Util.getInput();
            switch (choice) {
                case 1 ->
                    listFood();
                case 2 -> addFood();
                case 3 -> updateFood();
                case 4 -> deleteFood();
            }
        }
    }

    public static void listFood() {
        System.out.printf("%-5s %-15s %-15s %-15s %-15s %-10s\n",
                "ID", "Nama", "Kategori", "Created At", "Updated At", "Harga");

        listStock.forEach(p -> {
            System.out.printf("%-5d %-15s %-15s %-15s %-15s Rp%,d\n",
                    p.getId(),
                    p.getNama(),
                    p.getCategory(),
                    Util.dateFormat.format(p.getCreatedAt()),
                    Util.dateFormat.format(p.getUpdatedAt()),
                    p.getHarga()
            );
        });
    }


    public static void addFood() {
        System.out.print("Masukkan nama makanan: ");
        String nama = Util.getInputStr();
        System.out.print("Masukkan kategori: ");
        String kategori = Util.getInputStr();
        System.out.print("Masukkan harga: ");
        int harga = Util.getInput();
        int id = listStock.size() + 1;
        Date now = new Date();
        Stock newStock = new Stock(id, nama, kategori, now, now, harga);
        listStock.add(newStock);
        System.out.println("Makanan berhasil ditambahkan.");
    }

    public static void updateFood() {
        System.out.print("Masukkan ID makanan yang ingin diupdate: ");
        int id = Util.getInput();
        Stock stockToUpdate = null;
        for (Stock s : listStock) {
            if (s.getId() == id) {
                stockToUpdate = s;
                break;
            }
        }
        if (stockToUpdate == null) {
            System.out.println("Makanan dengan ID tersebut tidak ditemukan.");
            return;
        }
        System.out.print("Masukkan nama baru (kosongkan jika tidak ingin mengubah): ");
        String namaBaru = Util.getInputStr();
        if (!namaBaru.isEmpty()) {
            stockToUpdate.setNama(namaBaru);
        }
        System.out.print("Masukkan kategori baru (kosongkan jika tidak ingin mengubah): ");
        String kategoriBaru = Util.getInputStr();
        if (!kategoriBaru.isEmpty()) {
            stockToUpdate.setCategory(kategoriBaru);
        }
        System.out.print("Masukkan harga baru (0 jika tidak ingin mengubah): ");
        int hargaBaru = Util.getInput();
        if (hargaBaru > 0) {
            stockToUpdate.setHarga(hargaBaru);
        }
        System.out.println("Makanan berhasil diupdate.");
    }

    public static void deleteFood() {
        System.out.print("Masukkan ID makanan yang ingin dihapus: ");
        int id = Util.getInput();
        Stock stockToDelete = null;
        for (Stock s : listStock) {
            if (s.getId() == id) {
                stockToDelete = s;
                break;
            }
        }
        if (stockToDelete == null) {
            System.out.println("Makanan dengan ID tersebut tidak ditemukan.");
            return;
        }
        listStock.remove(stockToDelete);
        System.out.println("Makanan berhasil dihapus.");
    }
}