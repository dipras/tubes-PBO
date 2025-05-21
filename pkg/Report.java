package pkg;

import java.util.Date;
public class Report {
    private int id = 0;
    private String name;
    private Date gross_profit = null;
    private Date created_at = null;
    private Date updated_at = null;
    
    public Report(int id, String name) {
        this.id = id;
        this.name = name;
        this.gross_profit = new Date();
        this.created_at = new Date();
        this.updated_at = new Date();
    }
    
}
