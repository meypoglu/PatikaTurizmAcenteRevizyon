package entity;

public class Search {
    private int roomId;
    private  String hotelName;
    private  String hotelAddress;
    private  int hotelStar;
    private  String hotelCity;
    private  String hostelType;
    private  String roomType;
    private  boolean roomService;
    private  boolean spa;
    private  boolean concierge;
    private  boolean pool;
    private  boolean wifi;
    private  boolean park;
    private  boolean fitness;
    private  int stock;
    private  int price;
    private String season;
    private int seasonId;

    public Search(int roomId, int seasonId, String hotelName, String hotelAddress, int hotelStar, String hotelCity, String hostelType, String roomType, boolean roomService, boolean spa, boolean concierge, boolean pool, boolean wifi, boolean park, boolean fitness, int stock, int price, String season) {
        this.roomId = roomId;
        this.hotelName = hotelName;
        this.hotelAddress = hotelAddress;
        this.hotelCity = hotelCity;
        this.hostelType = hostelType;
        this.roomType = roomType;
        this.roomService = roomService;
        this.spa = spa;
        this.concierge = concierge;
        this.pool = pool;
        this.wifi = wifi;
        this.park = park;
        this.fitness = fitness;
        this.stock = stock;
        this.price = price;
        this.hotelStar = hotelStar;
        this.season = season;
        this.seasonId = seasonId;
    }

    public Search() {
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public int getSeasonId() {
        return seasonId;
    }

    public void setSeasonId(int seasonId) {
        this.seasonId = seasonId;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public void setHotelAddress(String hotelAddress) {
        this.hotelAddress = hotelAddress;
    }

    public void setHotelStar(int hotelStar) {
        this.hotelStar = hotelStar;
    }

    public void setHotelCity(String hotelCity) {
        this.hotelCity = hotelCity;
    }

    public void setHostelType(String hostelType) {
        this.hostelType = hostelType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public void setRoomService(boolean roomService) {
        this.roomService = roomService;
    }

    public void setSpa(boolean spa) {
        this.spa = spa;
    }

    public void setConcierge(boolean concierge) {
        this.concierge = concierge;
    }

    public void setPool(boolean pool) {
        this.pool = pool;
    }

    public void setWifi(boolean wifi) {
        this.wifi = wifi;
    }

    public void setPark(boolean park) {
        this.park = park;
    }

    public void setFitness(boolean fitness) {
        this.fitness = fitness;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getHotelStar() {
        return hotelStar;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public int getRoomId() {
        return roomId;
    }

    public String getHotelName() {
        return hotelName;
    }

    public String getHotelAddress() {
        return hotelAddress;
    }

    public String getHotelCity() {
        return hotelCity;
    }

    public String getHostelType() {
        return hostelType;
    }

    public String getRoomType() {
        return roomType;
    }

    public boolean isRoomService() {
        return roomService;
    }

    public boolean isSpa() {
        return spa;
    }

    public boolean isConcierge() {
        return concierge;
    }

    public boolean isPool() {
        return pool;
    }

    public boolean isWifi() {
        return wifi;
    }

    public boolean isPark() {
        return park;
    }

    public boolean isFitness() {
        return fitness;
    }

    public int getStock() {
        return stock;
    }

    public int getPrice() {
        return price;
    }
}
