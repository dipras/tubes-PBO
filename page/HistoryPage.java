package page;

import java.util.ArrayList;
import model.History;

public class HistoryPage {

    static ArrayList<History> histories = new ArrayList<>();

    public static void viewHistory() {
        if (histories.isEmpty()) {
            System.out.println("Belum ada history transaksi.");
            return;
        }
        for (History history : histories) {
            System.out.println("ID: " + history.getId());
            System.out.println("Dibuat oleh: " + history.getCreatedBy().getName());
            System.out.println("Tanggal: " + history.getCreatedAt());
            System.out.println("Total: " + history.getTotal());
            System.out.println("Cash Charge: " + history.getCashcharge());
            System.out.println("Cashback: " + history.getCashback());
            System.out.println("Daftar Barang:");
            for (model.StockHistory sh : history.addStock()) {
                System.out.println("  - " + sh.stock.getNama() + " | Qty: " + sh.qty + " | Harga: " + sh.stock.getHarga() + " | Total: " + sh.total_price);
            }
            System.out.println("--------------------------------------------------");
        }
    }

    static int getLatestId() {
        if(histories.size() < 1) return 0;
        return histories.get(histories.size() - 1).getId();
    }
}
