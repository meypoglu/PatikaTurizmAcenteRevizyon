package entity;

import core.ComboItem;

public class Hotel {
    private int id;
    private String name;
    private String address;
    private String city;
    private String mail;
    private long phoneNo;
    private int star;
    private Hostel hosteltype;
    private Room roomtype;
    private boolean roomService;
    private boolean spa;
    private boolean concierge;
    private boolean pool;
    private boolean wifi;
    private boolean park;
    private boolean fitness;

    public Hotel() {

    }

    public Hotel(String name, String address, String city, String mail, long phoneNo, int star, Hostel hosteltype, boolean roomService, boolean spa, boolean concierge, boolean pool, boolean wifi, boolean park, boolean fitness) {
        this.name = name;
        this.address = address;
        this.city = city;
        this.mail = mail;
        this.phoneNo = phoneNo;
        this.star = star;
        this.hosteltype = hosteltype;
        this.roomService = roomService;
        this.spa = spa;
        this.concierge = concierge;
        this.pool = pool;
        this.wifi = wifi;
        this.park = park;
        this.fitness = fitness;
    }

    public Hotel(int id, String name, String address, String mail, long phoneNo, int star, Hostel hosteltype, Room roomtype, boolean roomService, boolean spa, boolean concierge, boolean pool, boolean wifi, boolean park, boolean fitness) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.mail = mail;
        this.phoneNo = phoneNo;
        this.star = star;
        this.hosteltype = hosteltype;
        this.roomtype = roomtype;
        this.roomService = roomService;
        this.spa = spa;
        this.concierge = concierge;
        this.pool = pool;
        this.wifi = wifi;
        this.park = park;
        this.fitness = fitness;
    }

    public Room getRoomtype() {
        return roomtype;
    }

    public void setRoomtype(Room roomtype) {
        this.roomtype = roomtype;
    }

    public Hostel getHosteltype() {
        return hosteltype;
    }

    public void setHosteltype(Hostel hosteltype) {
        this.hosteltype = hosteltype;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public long getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(long phoneNo) {
        this.phoneNo = phoneNo;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }

    public ComboItem getComboItem(){
        return new ComboItem(this.getId(),this.getName() + " - " + this.getHosteltype().getName());
    }

    public boolean isRoomService() {
        return roomService;
    }

    public void setRoomService(boolean roomService) {
        this.roomService = roomService;
    }

    public boolean isSpa() {
        return spa;
    }

    public void setSpa(boolean spa) {
        this.spa = spa;
    }

    public boolean isConcierge() {
        return concierge;
    }

    public void setConcierge(boolean concierge) {
        this.concierge = concierge;
    }

    public boolean isPool() {
        return pool;
    }

    public void setPool(boolean pool) {
        this.pool = pool;
    }

    public boolean isWifi() {
        return wifi;
    }

    public void setWifi(boolean wifi) {
        this.wifi = wifi;
    }

    public boolean isPark() {
        return park;
    }

    public void setPark(boolean park) {
        this.park = park;
    }

    public boolean isFitness() {
        return fitness;
    }

    public void setFitness(boolean fitness) {
        this.fitness = fitness;
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", mail='" + mail + '\'' +
                ", phoneNo=" + phoneNo +
                ", star=" + star +
                ", hosteltype=" + hosteltype +
                ", roomtype=" + roomtype +
                ", roomService=" + roomService +
                ", spa=" + spa +
                ", concierge=" + concierge +
                ", pool=" + pool +
                ", wifi=" + wifi +
                ", park=" + park +
                ", fitness=" + fitness +
                '}';
    }
}
