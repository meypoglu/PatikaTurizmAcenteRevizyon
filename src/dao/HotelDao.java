package dao;

import core.Db;
import entity.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class HotelDao {
    private final Connection connection;
    private final HostelDao hostelDao;
    private final FacilityDao facilityDao;
    private final RoomDao roomDao;

    public HotelDao() {
        this.connection = Db.getInstance();
        this.hostelDao = new HostelDao();
        this.facilityDao = new FacilityDao();
        this.roomDao = new RoomDao();
    }

    public ArrayList<Hotel> findAll(){
        ArrayList<Hotel> hotelList = new ArrayList<>();
        String query = "SELECT * FROM public.hotel";
        try {
            ResultSet rs = this.connection.createStatement().executeQuery(query);
            while (rs.next()){
                hotelList.add(this.match(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hotelList;
    }
    public Hotel findById(int id) {
        Hotel hotel = null;
        String query = "SELECT * FROM hotel WHERE hotel_id = ?";
        try {
            PreparedStatement pr = this.connection.prepareStatement(query);
            pr.setInt(1, id);
            ResultSet rs = pr.executeQuery();
            if (rs.next()) {
                hotel = this.match(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hotel;
    }

    public boolean save(Hotel hotel) {
        String query = "INSERT INTO public.hotel (hotel_name, hotel_address, hotel_mail, hotel_pno, hotel_star, hotel_hostel_type_id, hotel_room_service, hotel_spa, hotel_concierge, hotel_pool, hotel_wifi, hotel_park, hotel_fitness) VALUES (?, ?, ?, ?, ?, ?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement pr = this.connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            pr.setString(1, hotel.getName());
            pr.setString(2, hotel.getAddress());
            pr.setString(3, hotel.getMail());
            pr.setLong(4, hotel.getPhoneNo());
            pr.setInt(5, hotel.getStar());
            pr.setInt(6, hotel.getHosteltype().getId());
            pr.setBoolean(7, hotel.isRoomService());
            pr.setBoolean(8, hotel.isSpa());
            pr.setBoolean(9, hotel.isConcierge());
            pr.setBoolean(10, hotel.isPool());
            pr.setBoolean(11, hotel.isWifi());
            pr.setBoolean(12, hotel.isPark());
            pr.setBoolean(13, hotel.isFitness());
            int affectedRows = pr.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet generatedKeys = pr.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        hotel.setId(generatedKeys.getInt(6));
                    }
                }
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean update(Hotel hotel) {
        String query = "UPDATE public.hotel " +
                "SET hotel_name = ?, hotel_address = ?, hotel_mail = ?, hotel_pno = ?, hotel_star = ?, " +
                "hotel_hostel_type_id = ?, hotel_room_service = ?, hotel_spa = ?, hotel_concierge = ?, hotel_pool = ?, hotel_wifi = ?, hotel_park = ?, hotel_fitness = ? " +
                "WHERE hotel_id = ?";
        try {
            PreparedStatement ps = this.connection.prepareStatement(query);
            ps.setString(1, hotel.getName());
            ps.setString(2, hotel.getAddress());
            ps.setString(3, hotel.getMail());
            ps.setLong(4, hotel.getPhoneNo());
            ps.setInt(5, hotel.getStar());
            ps.setInt(6, hotel.getHosteltype().getId());
            ps.setBoolean(7, hotel.isRoomService());
            ps.setBoolean(8, hotel.isSpa());
            ps.setBoolean(9, hotel.isConcierge());
            ps.setBoolean(10, hotel.isPool());
            ps.setBoolean(11, hotel.isWifi());
            ps.setBoolean(12, hotel.isPark());
            ps.setBoolean(13, hotel.isFitness());
            ps.setInt(14, hotel.getId());
            return ps.executeUpdate() != -1;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public ArrayList<Hotel> selectByQuery(String query) {
        ArrayList<Hotel> hotelList = new ArrayList<>();
        try {
            ResultSet rs = this.connection.createStatement().executeQuery(query);
            while (rs.next()) {
                hotelList.add(this.match(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hotelList;
    }
    public boolean delete(int id){
        String query = "DELETE FROM public.hotel WHERE hotel_id = ?";
        try{
            PreparedStatement pr = this.connection.prepareStatement(query);
            pr.setInt(1,id);
            return pr.executeUpdate() != -1;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return true;
    }

    public Hotel match(ResultSet rs) throws SQLException {
        Hotel obj = new Hotel();
        obj.setId(rs.getInt("hotel_id"));
        obj.setName(rs.getString("hotel_name"));
        obj.setAddress(rs.getString("hotel_address"));
        obj.setCity(rs.getString("hotel_city"));
        obj.setMail(rs.getString("hotel_mail"));
        obj.setPhoneNo(rs.getLong("hotel_pno"));
        obj.setStar(rs.getInt("hotel_star"));
        int hostelId = rs.getInt("hotel_hostel_type_id");
        Hostel hostel = hostelDao.findById(hostelId);
        obj.setHosteltype(hostel);
        obj.setRoomService(rs.getBoolean("hotel_room_service"));
        obj.setSpa(rs.getBoolean("hotel_spa"));
        obj.setConcierge(rs.getBoolean("hotel_concierge"));
        obj.setPool(rs.getBoolean("hotel_pool"));
        obj.setWifi(rs.getBoolean("hotel_wifi"));
        obj.setPark(rs.getBoolean("hotel_park"));
        obj.setFitness(rs.getBoolean("hotel_fitness"));
        return obj;
    }

}
