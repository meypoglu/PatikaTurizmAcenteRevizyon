package dao;

import business.HostelManager;
import core.Db;
import entity.Room;
import entity.Search;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class SearchDao {
    private final Connection connection;
    private HostelManager hostelManager;

    public SearchDao() {
        this.connection = Db.getInstance();
        this.hostelManager = new HostelManager();
    }

    public ArrayList<Search> findByParameters(String city, String hotelName, LocalDate checkIn, LocalDate checkOut) {
        ArrayList<Search> searchList = new ArrayList<>();
        String query = "SELECT h.hotel_name, h.hotel_address, h.hotel_city, h.hotel_star, h.hotel_hostel_type_id, h.hotel_room_service, h.hotel_spa, h.hotel_concierge, h.hotel_pool, h.hotel_wifi, h.hotel_park, h.hotel_fitness, r.room_id,\n" +
                "       r.room_name, r.room_stock, r.room_price, s.season_name, s.season_id \n" +
                "FROM hotel h \n" +
                "JOIN room r ON h.hotel_id = r.room_hotel_id \n" +
                "JOIN season s ON r.room_season_id = s.season_id \n" +
                "WHERE (COALESCE(?, h.hotel_city) = h.hotel_city) AND \n" +
                "      (COALESCE(?, h.hotel_name) = h.hotel_name) AND \n" +
                "      (COALESCE(?, s.season_strt_date) BETWEEN s.season_strt_date AND s.season_end_date) AND \n" +
                "      (COALESCE(?, s.season_end_date) BETWEEN s.season_strt_date AND s.season_end_date) AND \n" +
                "      r.room_stock > 0;";
        try {
            PreparedStatement pr = this.connection.prepareStatement(query);
            if (city.trim().isEmpty()) {
                city = null;
            }
            if (hotelName.trim().isEmpty()) {
                hotelName = null;
            }
            if (checkIn == null) {
                pr.setDate(3, null);
            } else {
                pr.setDate(3, Date.valueOf(checkIn));
            }
            if (checkOut == null) {
                pr.setDate(4, null);
            } else {
                pr.setDate(4, Date.valueOf(checkOut));
            }
            pr.setString(1, city);
            pr.setString(2, hotelName);

            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                searchList.add(this.match(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return searchList;
    }

    public Search match(ResultSet rs) throws SQLException {
        Search search = new Search();
        search.setRoomId(rs.getInt("room_id"));
        search.setHotelName(rs.getString("hotel_name"));
        search.setHotelAddress(rs.getString("hotel_address"));
        search.setHotelCity(rs.getString("hotel_city"));
        search.setHotelStar(rs.getInt("hotel_star"));
        search.setHostelType(hostelManager.findById(rs.getInt("hotel_hostel_type_id")).getName());
        search.setRoomService(rs.getBoolean("hotel_room_service"));
        search.setSpa(rs.getBoolean("hotel_spa"));
        search.setConcierge(rs.getBoolean("hotel_concierge"));
        search.setPool(rs.getBoolean("hotel_pool"));
        search.setWifi(rs.getBoolean("hotel_wifi"));
        search.setPark(rs.getBoolean("hotel_park"));
        search.setFitness(rs.getBoolean("hotel_fitness"));
        search.setRoomType(rs.getString("room_name"));
        search.setStock(rs.getInt("room_stock"));
        search.setPrice(rs.getInt("room_price"));
        search.setSeason(rs.getString("season_name"));
        search.setSeasonId(rs.getInt("season_id"));
        return search;
    }
}
