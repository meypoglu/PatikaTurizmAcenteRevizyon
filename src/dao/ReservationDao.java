package dao;

import core.Db;
import entity.Hotel;
import entity.Reservation;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class ReservationDao {
    private final Connection connection;
    private final HotelDao hotelDao;

    public ReservationDao() {
        this.connection = Db.getInstance();
        this.hotelDao =new HotelDao();
    }

    public ArrayList<Reservation> findAll() {
        ArrayList<Reservation> reservationList = new ArrayList<>();
        String query = "SELECT * FROM public.reservation ORDER BY reservation_id ASC";
        try {
            ResultSet rs = this.connection.createStatement().executeQuery(query);
            while (rs.next()) {
                reservationList.add(this.match(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reservationList;
    }

    public Reservation findById(int id) {
        Reservation reservation = null;
        String query = "SELECT * FROM public.reservation WHERE reservation_id = ?";
        try {
            PreparedStatement pr = this.connection.prepareStatement(query);
            pr.setInt(1, id);
            ResultSet rs = pr.executeQuery();
            if (rs.next()) {
                reservation = this.match(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reservation;
    }

    public boolean save(Reservation reservation) {
        String query = "INSERT INTO public.reservation (reservation_customer_name, reservation_hotel_id, reservation_strt_date, reservation_end_date, reservation_adult_number, reservation_children_number, reservation_room_id, reservation_total) VALUES (?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement pr = this.connection.prepareStatement(query);
            pr.setString(1, reservation.getCustomer_name());
            pr.setInt(2, reservation.getOtel().getId());
            pr.setDate(3, Date.valueOf(reservation.getStartDate()));
            pr.setDate(4, Date.valueOf(reservation.getFinishDate()));
            pr.setInt(5, reservation.getAdultNumber());
            pr.setInt(6, reservation.getChildNumber());
            pr.setInt(7, reservation.getRoomId());
            pr.setInt(8, (int) reservation.getTotalPrice());
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean update(Reservation reservation) {
        String query = "UPDATE public.reservation SET reservation_customer_name = ?, reservation_hotel_id = ?, reservation_strt_date = ?, reservation_end_date = ?, reservation_adult_number = ?, reservation_children_number = ?, reservation_total = ? WHERE reservation_id = ?";
        try {
            PreparedStatement pr = this.connection.prepareStatement(query);
            pr.setString(1, reservation.getCustomer_name());
            pr.setInt(2, reservation.getOtel().getId());
            pr.setDate(3, Date.valueOf(reservation.getStartDate()));
            pr.setDate(4, Date.valueOf(reservation.getFinishDate()));
            pr.setInt(5, reservation.getAdultNumber());
            pr.setInt(6, reservation.getChildNumber());
            pr.setInt(7, (int) reservation.getTotalPrice());
            pr.setInt(8, reservation.getId());
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public boolean delete(int id){
        String query = "DELETE FROM public.reservation WHERE reservation_id = ?";
        try{
            PreparedStatement pr = this.connection.prepareStatement(query);
            pr.setInt(1,id);
            return pr.executeUpdate() != -1;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return true;
    }

    public Reservation match(ResultSet rs) throws SQLException {
        Reservation obj = new Reservation();
        obj.setId(rs.getInt("reservation_id"));
        obj.setCustomer_name(rs.getString("reservation_customer_name"));

        int otelId = rs.getInt("reservation_hotel_id");
        Hotel hotel = hotelDao.findById(otelId);
        obj.setOtel(hotel);
        obj.setRoomId(rs.getInt("reservation_room_id"));
        obj.setStartDate(LocalDate.parse(rs.getString("reservation_strt_date")));
        obj.setFinishDate(LocalDate.parse(rs.getString("reservation_end_date")));

        obj.setAdultNumber(rs.getInt("reservation_adult_number"));
        obj.setChildNumber(rs.getInt("reservation_children_number"));
        obj.setTotalPrice(rs.getInt("reservation_total"));

        return obj;
    }
}
