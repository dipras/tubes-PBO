package pkg;

import java.util.Date;

public class Category {
    private int id = 0;
    private String name;
    private Date created_at = null;
    private Date updated_at = null;

    
    public Category(int id, String name) {
        this.id = id;
        this.name = name;
        this.created_at = new Date();
        this.updated_at = new Date();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        this.updated_at = new Date();
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "id = " + id + "; name = " + name + "; Created At = " + created_at + "; Updated At = " + updated_at;
    }
}