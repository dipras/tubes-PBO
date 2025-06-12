package model;

import java.util.Date;
public class Report {
    private int id = 0;
    private String name;
    private int gross_profit;
    private Date to_date = null;
    private Date from_date = null;
    
    public Report(int id, String name, int gross_profit) {
        this.id = id;
        this.name = name;
        this.gross_profit = gross_profit;
        this.to_date = new Date();
    }
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getGrossProfit() {
        return gross_profit;
    }
    public void setGrossProfit(int gross_profit) {
        this.gross_profit = gross_profit;
    }
    public Date getCreatedAt() {
        return to_date;
    }
    
    public void setFromDate(Date from_date) {
        this.from_date = from_date;
    }
    
}
