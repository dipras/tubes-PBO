package page;

import java.util.ArrayList;
import model.History;
import model.Stock;
import model.StockHistory;
import util.Util;
import util.Var;

public class CheckoutPage {

    public static void viewCheckout() {
        ArrayList<StockHistory> chosenStocks = new ArrayList<>();
        while (true) {
            int[] i = {1};
            page.StockPage.listStock.forEach(menu -> {
                System.out.println(i[0] + ". " + menu.getNama());
                i[0]++;
            });

            System.out.print("Masukan pilihan anda (ketik 0 untuk checkout): ");
            int choice = Util.getInput();
            if (choice == 0) {
                if (chosenStocks.size() < 1) {
                    System.out.println("Minimal memilih 1 menu!");
                    continue;
                }
                int[] grandtotal = {0};
                chosenStocks.forEach(s -> {
                    grandtotal[0] += s.total_price;
                });

                System.out.println("Total pembayaran: " + grandtotal[0]);
                System.out.print("Masukan uang anda: ");
                int charge = Util.getInput();

                if (grandtotal[0] > charge) {
                    System.out.println("Pembayaran kurang!");
                    continue;
                }
                int cashback = charge - grandtotal[0];

                History newHistory = new History(HistoryPage.getLatestId() + 1, Var.getCurrentUser(), charge, cashback, grandtotal[0], chosenStocks);
                HistoryPage.histories.add(newHistory);
                System.out.println("Berhasil membeli\n====================");
                break;
            } else {
                Stock newStock = StockPage.listStock.get(choice - 1);
                if (newStock == null) {
                    System.out.println("Menu yang dipilih tidak ada");
                    continue;
                }
                System.out.print("Banyaknya pembelian: ");
                int qty = Util.getInput();
                chosenStocks.add(new StockHistory(newStock, qty));

                System.out.println("### KERANJANG ###");
                int[] index = {1};
                int[] grandTotal = {0};
                chosenStocks.forEach(chose -> {
                    System.out.printf("%-3d %-20s x%-3d Rp. %-10d%n", index[0], chose.stock.getNama(), chose.qty, chose.total_price);
                    index[0]++;
                    grandTotal[0] += chose.total_price;
                });
                System.out.println("TOTAL SEMUA = Rp." + grandTotal[0]);
                System.out.println();
            }
        }
    }
}
