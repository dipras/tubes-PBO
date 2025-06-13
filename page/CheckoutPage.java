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
            for (int i = 0; i < page.StockPage.listStock.size(); i++) {
                System.out.println((i + 1) + ". " + page.StockPage.listStock.get(i).getNama());
            }

            System.out.print("Masukan pilihan anda (ketik 0 untuk checkout): ");
            int choice = Util.getInput();
            if (choice == 0) {
                if (chosenStocks.size() < 1) {
                    System.out.println("Minimal memilih 1 menu!");
                    continue;
                }
                int grandtotal = 0;
                for (int i = 0; i < chosenStocks.size(); i++) {
                    grandtotal += chosenStocks.get(i).total_price;
                }

                System.out.println("Total pembayaran: " + grandtotal);
                System.out.print("Masukan uang anda: ");
                int charge = Util.getInput();

                if (grandtotal > charge) {
                    System.out.println("Pembayaran kurang!");
                    continue;
                }
                int cashback = charge - grandtotal;

                History newHistory = new History(HistoryPage.getLatestId() + 1, Var.getCurrentUser(), charge, cashback, grandtotal, chosenStocks);
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
                int grandTotal = 0;
                for (int i = 0; i < chosenStocks.size(); i++) {
                    StockHistory chose = chosenStocks.get(i);
                    System.out.printf("%-3d %-20s x%-3d Rp. %-10d%n", i + 1, chose.stock.getNama(), chose.qty, chose.total_price);
                    grandTotal += chose.total_price;
                }
                System.out.println("TOTAL SEMUA = Rp." + grandTotal);
                System.out.println();
            }
        }
    }
}
