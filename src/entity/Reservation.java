package entity;

import business.SeasonManager;

import java.time.LocalDate;

import static java.util.concurrent.TimeUnit.DAYS;
import java.time.temporal.ChronoUnit;
public class Reservation {
    private int id;
    private int roomId;
    private String customer_name;
    private Hotel hotel;
    private Room room;
    private LocalDate startDate;
    private LocalDate finishDate;
    private int adultNumber;
    private int childNumber;
    private double totalPrice;
    private SeasonManager seasonManager = new SeasonManager();

    public Reservation(int id, int roomId, String customerName, Hotel hotel, LocalDate startDate, LocalDate finishDate, int adultNumber, int childNumber, int totalPrice) {
        this.id = id;
        this.roomId = roomId;
        this.customer_name = customerName;
        this.hotel = hotel;
        this.startDate = startDate;
        this.finishDate = finishDate;
        this.adultNumber = adultNumber;
        this.childNumber = childNumber;
        this.totalPrice = calculateTotalPrice();
    }

    public Reservation(String customerName, Hotel hotel, LocalDate startDate, LocalDate finishDate, int adultNumber, int childNumber, int totalPrice) {
        this.customer_name = customerName;
        this.hotel = hotel;
        this.startDate = startDate;
        this.finishDate = finishDate;
        this.adultNumber = adultNumber;
        this.childNumber = childNumber;
        this.roomId = roomId;
        this.totalPrice = calculateTotalPrice();
    }

    public Reservation() {}

    public Reservation(int roomId, String customerName, Hotel hotel, Room room, LocalDate startDate, LocalDate finishDate, int adultNumber, int childNumber) {
        this.customer_name = customerName;
        this.hotel = hotel;
        this.room = room;
        this.startDate = startDate;
        this.finishDate = finishDate;
        this.adultNumber = adultNumber;
        this.childNumber = childNumber;
        this.roomId = roomId;
        this.totalPrice = calculateTotalPrice();
    }

    public int getId() {
        return id;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public Hotel getOtel() {
        return hotel;
    }

    public Room getRoom() {
        return room;
    }

    public void setOtel(Hotel hotel) {
        this.hotel = hotel;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(LocalDate finishDate) {
        this.finishDate = finishDate;
    }

    public int getAdultNumber() {
        return adultNumber;
    }

    public void setAdultNumber(int adultNumber) {
        this.adultNumber = adultNumber;
    }

    public int getChildNumber() {
        return childNumber;
    }

    public void setChildNumber(int childNumber) {
        this.childNumber = childNumber;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    private double calculateTotalPrice() {
        int roomPrice = room.getPrice();
        double seasonPrice = seasonManager.findById(room.getSeasonId()).getFare();
        int hostelPrice = hotel.getHosteltype().getHostelPrice();
        int days = (int) ChronoUnit.DAYS.between(getStartDate(), getFinishDate());
        if (childNumber != 0) {
            double sum = days * ((roomPrice * adultNumber) + ((roomPrice * childNumber) / 2) + hostelPrice);
            double finalSum = sum + (sum * seasonPrice);
            return finalSum;
        } else {
            double sum =  days * ((roomPrice * adultNumber) + hostelPrice);
            double finalSum = sum + (sum * seasonPrice);
            return finalSum;
        }
    }
}
