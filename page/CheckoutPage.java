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

    public static void viewCheckout() {
        int choice = 0;

        while (choice != 9) {
            System.out.println("======================================");
            System.out.println("\nHalaman Checkout");
            System.out.println("======================================");
            System.out.println("1. Checkout");
            System.out.println("9. Kembali ke Menu Utama");
            System.out.print("Masukan pilihan: ");
            choice = Util.getInput();

            switch (choice) {
                case 1 -> addTransaction();
                case 9 -> Main.mainMenu();
                default -> {
                    System.out.println("Pilihan tidak valid. Silakan coba lagi.");
                    viewCheckout();
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
            System.out.print("Masukkan ID Produk (0 untuk Checkout): ");
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
        
        
        ArrayList<Integer> ProductId = new ArrayList<>();

        
        for (StockHistory sh : listStockHistory) {
            if (sh.getHstory_id() == currentHistoryId) {
                int currentProductId = sh.getStock_id();

                
                boolean alreadyDisplayed = false;
                for (int id : ProductId) {
                    if (id == currentProductId) {
                        alreadyDisplayed = true;
                        break;
                    }
                }

                if (!alreadyDisplayed) {
                    
                    int quantity = 0;
                    for (StockHistory innerSh : listStockHistory) {
                        if (innerSh.getHstory_id() == currentHistoryId && innerSh.getStock_id() == currentProductId) {
                            quantity++;
                        }
                    }

                    Stock stock = findStockById(currentProductId);
                    if (stock != null) {
                        System.out.printf("%-20s %-10d Rp%,d\n",
                                stock.getNama(),
                                quantity,
                                stock.getHarga());
                    } else {
                        System.out.printf("Produk tidak ditemukan (ID: %d)\n", currentProductId);
                    }

                    
                    ProductId.add(currentProductId);
                }
            }
        }
       

        System.out.println("======================================");
        System.out.printf("Total Belanja: Rp%,d\n", totalHarga);

        String methodPayment;
        int cashCharge = 0;
        int cashBack = 0;
        int created_by = Main.currentUser.getId(); 


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

        History newHistory = new History(currentHistoryId, created_by, methodPayment, cashCharge, cashBack, totalHarga);
        listHistory.add(newHistory);

        System.out.println("======================================");
        System.out.println("\nTransaksi berhasil dicatat!");
        System.out.printf("ID User: %,d\n", created_by);
        System.out.printf("Total: Rp%,d\n", totalHarga);
        System.out.printf("Bayar: Rp%,d\n", cashCharge);
        System.out.printf("Kembali: Rp%,d\n", cashBack);
        System.out.println("======================================");
    }

    static Stock findStockById(int id) {
        for (Stock stock : StockPage.listStock) {
            if (stock.getId() == id) {
                return stock;
            }
        }
        return null;
    }
}
