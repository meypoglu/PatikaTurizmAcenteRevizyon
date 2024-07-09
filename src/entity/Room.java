package entity;

import core.ComboItem;

public class Room {
    private int id;
    private int hotelId;
    private String name;
    private int price;
    private int stock;
    private int seasonId;



    public Room(String name, int price, int stock, int hotelId, int seasonId) {
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.hotelId = hotelId;
        this.seasonId = seasonId;
    }

    public Room(int id, String name, int price,int stock, int hotelId, int seasonId) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock=stock;
        this.hotelId = hotelId;
        this.seasonId = seasonId;
    }

    public Room() {

    }

    public int getSeasonId() {
        return seasonId;
    }

    public void setSeasonId(int seasonId) {
        this.seasonId = seasonId;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getHotelId() {
        return hotelId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    public ComboItem getComboItem(){
        return new ComboItem(this.getId(),this.getName());
    }

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", hotelId=" + hotelId +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", stock=" + stock +
                '}';
    }
}
