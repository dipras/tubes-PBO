package pkg;

public class StockHistory {
    private int id = 0;
    private int stock_id = 0;
    private int history_id = 0;
    
    public StockHistory(int id, int stock_id, int history_id) {
        this.id = id;
        this.stock_id = stock_id;
        this.history_id = history_id;
    }
    public int getid() {
        return id;
    }
    public int getstock_id() {
        return stock_id;
    }
    public int gethistory_id() {
        return history_id;
    }
}