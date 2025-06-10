package model;

import java.util.Date;
public class Report {
    private int id = 0;
    private String name;
    private int gross_profit;
    private Date created_at = null;
    private Date updated_at = null;
    
    public Report(int id, String name, int gross_profit) {
        this.id = id;
        this.name = name;
        this.gross_profit = gross_profit;
        this.created_at = new Date();
        this.updated_at = new Date();
    }
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.updated_at = new Date();
        this.name = name;
    }
    public int getGrossProfit() {
        return gross_profit;
    }
    public void setGrossProfit(int gross_profit) {
        this.updated_at = new Date();
        this.gross_profit = gross_profit;
    }
    public Date getCreatedAt() {
        return created_at;
    }
    public Date getUpdatedAt() {
        return updated_at;
    }
    
    
}
