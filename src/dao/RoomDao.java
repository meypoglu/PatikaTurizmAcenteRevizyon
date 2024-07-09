package dao;

import core.Db;
import entity.Room;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RoomDao {
    private final Connection connection;

    public RoomDao() {
        this.connection = Db.getInstance();
    }

    public ArrayList<Room> findAll() {
        ArrayList<Room> roomList = new ArrayList<>();
        String query = "SELECT * FROM public.room ORDER BY room_id ASC";
        try {
            ResultSet rs = this.connection.createStatement().executeQuery(query);
            while (rs.next()) {
                roomList.add(this.match(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return roomList;
    }

    public Room findById(int id) {
        Room room = null;
        String query = "SELECT * FROM public.room WHERE room_id = ?";
        try {
            PreparedStatement pr = this.connection.prepareStatement(query);
            pr.setInt(1, id);
            ResultSet rs = pr.executeQuery();
            if (rs.next()) {
                room = this.match(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return room;
    }

    public boolean save(Room room){
        String query = "INSERT INTO public.room (room_name, room_price, room_stock, room_hotel_id, room_season_id) VALUES (?,?,?,?,?)";
        try {
            PreparedStatement pr = this.connection.prepareStatement(query);
            pr.setString(1,room.getName());
            pr.setInt(2,room.getPrice());
            pr.setInt(3,room.getStock());
            pr.setInt(4, room.getHotelId());
            pr.setInt(5, room.getSeasonId());
            return pr.executeUpdate() != -1;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return true;
    }

    public boolean update(Room room){
        String query = "UPDATE public.room SET room_name = ?, room_price = ?, room_stock = ?, room_hotel_id = ?, room_season_id = ? WHERE room_id = ?";
        try {
            PreparedStatement pr = this.connection.prepareStatement(query);
            pr.setString(1,room.getName());
            pr.setInt(2, room.getPrice());
            pr.setInt(3, room.getStock());
            pr.setInt(4, room.getHotelId());
            pr.setInt(5, room.getSeasonId());
            pr.setInt(6,room.getId());
            return pr.executeUpdate() != -1;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return true;
    }

    public boolean delete(int id){
        String query = "DELETE FROM public.room WHERE room_id = ?";
        try{
            PreparedStatement pr = this.connection.prepareStatement(query);
            pr.setInt(1,id);
            return pr.executeUpdate() != -1;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return true;
    }

    public Room match(ResultSet rs) throws SQLException {
        Room room = new Room();
        room.setId(rs.getInt("room_id"));
        room.setHotelId(rs.getInt("room_hotel_id"));
        room.setName(rs.getString("room_name"));
        room.setPrice(rs.getInt("room_price"));
        room.setStock(rs.getInt("room_stock"));
        room.setSeasonId(rs.getInt("room_season_id"));
        return room;
    }

    public void increaseStock(int roomId) {
        String query = "UPDATE public.room SET room_stock = room_stock + 1 WHERE room_id = ?";
        try {
            PreparedStatement pr = connection.prepareStatement(query);
            pr.setInt(1, roomId);
            pr.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void decreaseStock(int roomId) {
        String query = "UPDATE public.room SET room_stock = room_stock - 1 WHERE room_id = ?";
        try {
            PreparedStatement pr = connection.prepareStatement(query);
            pr.setInt(1, roomId);
            pr.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
