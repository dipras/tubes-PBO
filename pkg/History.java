package pkg;

import java.util.Date;

public class History {

    private int id = 0;
    private int created_by = 0;
    private String method_payment = "";
    private int cashcharge = 0;
    private int cashback = 0;
    private int total = 0;
    private int stockId = 0;
    private Date created_at = null;
    private Date update_at = null;

    public History(int id, int created_by, String method_payment, int cashcharge, int cashback, int total) {
        this.id = id;
        this.created_by = created_by;
        this.method_payment = method_payment;
        this.cashcharge = cashcharge;
        this.cashback = cashback;
        this.total = total;
        this.created_at = new Date();
        this.update_at = new Date();
    }

    public History(int id, int created_by, String method_payment, int cashcharge, int cashback, int total, int stockId) {
        this.id = id;
        this.created_by = created_by;
        this.method_payment = method_payment;
        this.cashcharge = cashcharge;
        this.cashback = cashback;
        this.total = total;
        this.stockId = stockId;
        this.created_at = new Date();
        this.update_at = new Date();
    }

    public int getCreatedBy() {
        return created_by;
    }

    public String getMethodPayment() {
        return method_payment;
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

    public int getStockId() {
        return stockId;
    }

    public Date getCreatedAt() {
        return created_at;
    }
    public Date getUpdateAt() {
        return update_at;
    }


}
