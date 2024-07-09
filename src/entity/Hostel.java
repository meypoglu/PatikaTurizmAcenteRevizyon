package entity;

public class Hostel {
    private int id;
    private String name;
    private int hostelPrice;

    public Hostel(int id, String name, int hostelPrice) {
        this.id = id;
        this.name = name;
        this.hostelPrice = hostelPrice;
    }
    public Hostel(String name, int hostelPrice) {
        this.name = name;
        this.hostelPrice = hostelPrice;
    }

    public Hostel() {
    }

    public int getHostelPrice() {
        return hostelPrice;
    }

    public void setHostelPrice(int hostelPrice) {
        this.hostelPrice = hostelPrice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
