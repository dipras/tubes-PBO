package page;

import java.util.ArrayList;


import main.Main;
import pkg.History;
import pkg.Stock;
import pkg.StockHistory;
import util.Util;

public class CheckoutPage {

    static ArrayList<History> listHistory = new ArrayList<>();
    static ArrayList<StockHistory> listStockHistory = new ArrayList<>();
    static int historyIdCounter = 1;
    static int stockHistoryIdCounter = 1;

    public static void view() {
        int choice = 0;

        while (choice != 9) {
            System.out.println("======================================");
            System.out.println("\nHalaman Checkout");
            System.out.println("======================================");
            System.out.println("1. Tambah Transaksi Baru");
            System.out.println("2. Lihat Riwayat Transaksi");
            System.out.println("9. Kembali ke Menu Utama");
            System.out.print("Masukan pilihan: ");
            choice = Util.getInput();

            switch (choice) {
                case 1 -> addTransaction();
                case 2 -> viewHistory();
                case 9 -> Main.mainMenu();
                default -> {
                    System.out.println("Pilihan tidak valid. Silakan coba lagi.");
            } }
        }
    }

    public static void addTransaction() {
        System.out.println("======================================");
        System.out.println("\nCheckout");

        System.out.println("Daftar Produk:");
        StockPage.listFood();

        int currentHistoryId = historyIdCounter++;
        int totalHarga = 0;
        boolean itemAdded = false;

        System.out.println("\nMasukkan ID produk dan jumlah yang ingin dibeli (ketik '0' untuk selesai):");

        while (true) {
            System.out.print("Masukkan ID Produk (0 untuk selesai): ");
            int productId = Util.getInput();
            if (productId == 0) {
                break;
            }

            Stock selectedStock = null;
            for (Stock stock : StockPage.listStock) {
                if (stock.getId() == productId) {
                    selectedStock = stock;
                    break;
                }
            }

            if (selectedStock == null) {
                System.out.println("Produk dengan ID tersebut tidak ditemukan.");
                continue;
            }

            System.out.print("Masukkan Jumlah: ");
            int quantity = Util.getInput();

            if (quantity <= 0) {
                System.out.println("Jumlah harus lebih dari 0.");
                continue;
            }

            for(int i = 0; i < quantity; i++) {
                StockHistory newStockHistory = new StockHistory(stockHistoryIdCounter++, productId, currentHistoryId);
                listStockHistory.add(newStockHistory);
            }

            totalHarga += selectedStock.getHarga() * quantity;
            itemAdded = true;

            System.out.println("Produk ditambahkan ke keranjang.");
        }

        if (!itemAdded) {
            System.out.println("Tidak ada produk yang dipilih untuk transaksi.");
            historyIdCounter--;
            return;
        }

        System.out.println("======================================");
        System.out.println("\nDetail Checkout");
        System.out.printf("%-20s %-10s %-15s\n", "Nama Produk", "Jumlah", "Harga Satuan");
        for (StockHistory sh : listStockHistory) {
            if (sh.getHstory_id() == currentHistoryId) {
                Stock stock = findStockById(sh.getStock_id());
                if (stock != null) {
                    System.out.printf("%-20s %-10d Rp%,d\n",
                            stock.getNama(),
                            1,
                            stock.getHarga());
                } else {
                    System.out.printf("Produk tidak ditemukan (ID: %d)\n", sh.getStock_id());
                }
            }
        }
        System.out.println("======================================");
        System.out.printf("Total Belanja: Rp%,d\n", totalHarga);

        String methodPayment;
        int cashCharge = 0;
        int cashBack = 0;


        while (true) {
            System.out.print("\nMasukkan Metode Pembayaran (misal: Cash, QRIS): ");
            methodPayment = Util.getInputStr();

            if (methodPayment.equalsIgnoreCase("Cash")) {

                System.out.print("Masukkan Jumlah Bayar: ");
                cashCharge = Util.getInput();
                if (cashCharge < totalHarga) {
                    System.out.println("Jumlah bayar kurang dari total belanja. Transaksi dibatalkan.");
                    historyIdCounter--;

                    listStockHistory.removeIf(sh -> sh.getHstory_id() == currentHistoryId);
                    return;
                }
                cashBack = cashCharge - totalHarga;
                break;
            } else if (methodPayment.equalsIgnoreCase("QRIS")) {

                System.out.print("Masukkan Jumlah Bayar: ");
                cashCharge = Util.getInput();
                if (cashCharge < totalHarga) {
                    System.out.println("Jumlah bayar kurang dari total belanja. Transaksi dibatalkan.");
                    historyIdCounter--;

                    listStockHistory.removeIf(sh -> sh.getHstory_id() == currentHistoryId);
                    return;
                }
                cashBack = cashCharge - totalHarga;
                break;

            } else {
                System.out.println("Metode pembayaran tidak valid. Silakan coba lagi.");

            }


        }

        History newHistory = new History(currentHistoryId, 1, methodPayment, cashCharge, cashBack, totalHarga);
        listHistory.add(newHistory);

        System.out.println("======================================");
        System.out.println("\nTransaksi berhasil dicatat!");
        System.out.printf("Total: Rp%,d\n", totalHarga);
        System.out.printf("Bayar: Rp%,d\n", cashCharge);
        System.out.printf("Kembali: Rp%,d\n", cashBack);
        System.out.println("======================================");
    }

    public static void viewHistory() {
        System.out.println("======================================");
        System.out.println("\nRiwayat Transaksi");
        System.out.println("======================================");

        if (listHistory.isEmpty()) {
            System.out.println("Belum ada riwayat transaksi.");
            return;
        }

        System.out.printf("%-5s %-15s %-15s %-15s %-15s %-15s %-15s\n",
                "ID", "Tanggal", "Metode Bayar", "Bayar", "Kembali", "Total", "User ID");
        System.out.println("---------------------------------------------------------------------------------------------------");


        for (History history : listHistory) {
            System.out.printf("%-5d %-15s %-15s %-15s %-15s %-15s %-15d\n",
                    history.getId(),
                    Util.dateFormat.format(history.getCreatedAt()),
                    history.getMethodPayment(),
                    String.format("Rp%,d", history.getCashcharge()),
                    String.format("Rp%,d", history.getCashback()),
                    String.format("Rp%,d", history.getTotal()),
                    history.getCreatedBy());
        }
        System.out.println("---------------------------------------------------------------------------------------------------");

        while (true) {
            System.out.print("Lihat Detail Item? Masukkan ID Transaksi (0 untuk selesai): ");
            int detailHistoryId = Util.getInput();

            if (detailHistoryId == 0) {
                break;
            }

            boolean historyFound = false;
            for (History history : listHistory) {
                if (history.getId() == detailHistoryId) {
                    historyFound = true;
                    break;
                }
            }

            if (historyFound) {
                System.out.println("\n  Detail Item untuk Transaksi ID " + detailHistoryId + ":");
                boolean itemFoundInHistory = false;
                for(StockHistory sh : listStockHistory) {
                    if (sh.getHstory_id() == detailHistoryId) {
                        itemFoundInHistory = true;
                        Stock stock = findStockById(sh.getStock_id());
                        if (stock != null) {
                             System.out.printf("    - %-15s (ID: %d) @ Rp%,d\n",
                                    stock.getNama(),
                                    stock.getId(),
                                    stock.getHarga());
                        } else {
                             System.out.printf("    - Produk tidak ditemukan (ID: %d)\n", sh.getStock_id());
                        }
                    }
                }
                if (!itemFoundInHistory) {
                     System.out.println("    Tidak ada item tercatat untuk transaksi ini.");
                }
                System.out.println("======================================");
            } else {
                System.out.println("Transaksi dengan ID " + detailHistoryId + " tidak ditemukan.");
            }
        }

        System.out.println("======================================");
    }

    private static Stock findStockById(int id) {
        for (Stock stock : StockPage.listStock) {
            if (stock.getId() == id) {
                return stock;
            }
        }
        return null;
    }
}
