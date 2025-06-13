package model;

import java.util.ArrayList;
import java.util.Date;

public class History {

    private int id = 0;
    private User created_by;
    private int cashcharge = 0;
    private int cashback = 0;
    private int total = 0;
    private ArrayList<StockHistory> stocks = new ArrayList<>();
    private Date created_at = null;
    private Date update_at = null;

    public History(int id, User created_by, int cashcharge, int cashback, int total, ArrayList<StockHistory> stocks) {
        this.id = id;
        this.created_by = created_by;
        this.cashcharge = cashcharge;
        this.cashback = cashback;
        this.total = total;
        this.stocks = stocks;
        this.created_at = new Date();
        this.update_at = new Date();
    }

    public User getCreatedBy() {
        return created_by;
    }

    public int getCashcharge() {
        return cashcharge;
    }

    public int getCashback() {
        return cashback;
    }

    public int getTotal() {
        return total;
    }

    public int getId() {
        return id;
    }

    public void addStock(StockHistory stock) {
        this.stocks.add(stock);
    }

    public ArrayList<StockHistory> getStock() {
        return this.stocks;
    }

    public Date getCreatedAt() {
        return created_at;
    }
    public Date getUpdateAt() {
        return update_at;
    }


}
