package page;

import java.util.ArrayList;

import main.Main;
import pkg.History;
import pkg.Stock;
import pkg.StockHistory;

import util.Util;

public class ReportPage {

    public static void viewReport() {
        System.out.println("======================================");
        System.out.println("\nLaporan Riwayat Transaksi");
        System.out.println("======================================");

        ArrayList<History> listHistory = CheckoutPage.listHistory;
        ArrayList<StockHistory> listStockHistory = CheckoutPage.listStockHistory;

        if (listHistory.isEmpty()) {
            System.out.println("Belum ada riwayat transaksi.");
            System.out.println("======================================");
            System.out.print("Tekan Enter untuk kembali...");
            Util.getInputStr();
            Main.mainMenu();
        }

        System.out.printf("%-15s %-15s %-15s %-15s %-15s %-15s %-15s\n",
                "ID Transaksi", "Tanggal", "Metode Bayar", "Bayar", "Kembali", "Total", "User ID");
        

        for (History history : listHistory) {
            System.out.printf("%-15d %-15s %-15s %-15s %-15s %-15s %-15d\n",
                    history.getId(),
                    Util.dateFormat.format(history.getCreatedAt()),
                    history.getMethodPayment(),
                    String.format("Rp%,d", history.getCashcharge()),
                    String.format("Rp%,d", history.getCashback()),
                    String.format("Rp%,d", history.getTotal()),
                    history.getCreatedBy());
        }
        
        long gross_profit = calculateTotalGrossProfit(listHistory);
        System.out.println("======================================");
        System.out.printf("Total Gross Profit: Rp%,d\n", gross_profit);
        System.out.println("======================================");
        

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
                        Stock stock = CheckoutPage.findStockById(sh.getStock_id());
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
                
            } else {
                System.out.println("Transaksi dengan ID " + detailHistoryId + " tidak ditemukan.");
            }
        }

        System.out.println("======================================");
        System.out.print("Tekan Enter untuk kembali...");
        Util.getInputStr();
        Main.mainMenu();
    }

    
    public static long calculateTotalGrossProfit(ArrayList<History> histories) {
        long totalGrossProfit = 0;
        for (History history : histories) {
            totalGrossProfit += history.getTotal();
        }
        return totalGrossProfit;
    }
   
}
