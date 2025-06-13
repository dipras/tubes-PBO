package model;

public class StockHistory {
    public final Stock stock;
    public final int qty;
    public final int total_price;
    public int hargaSaatTransaksi;

    public StockHistory(Stock stock, int qty) {
        this.stock = stock;
        this.qty = qty;
        this.total_price = stock.getHarga() * qty;
        this.hargaSaatTransaksi = stock.getHarga();
    }
}