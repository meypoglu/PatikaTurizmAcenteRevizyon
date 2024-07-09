package dao;

import core.Db;
import entity.Hostel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class HostelDao {
    private final Connection connection;

    public HostelDao() {
        this.connection = Db.getInstance();
    }

    public ArrayList<Hostel> findAll() {
        ArrayList<Hostel> hostelList = new ArrayList<>();
        String query = "SELECT * FROM hostel ORDER BY hostel_id ASC";
        try {
            ResultSet rs = this.connection.createStatement().executeQuery(query);
            while (rs.next()) {
                hostelList.add(this.match(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hostelList;
    }

    public Hostel findById(int id) {
        Hostel hostel = null;
        String query = "SELECT * FROM hostel WHERE hostel_id = ?";
        try {
            PreparedStatement pr = this.connection.prepareStatement(query);
            pr.setInt(1, id);
            ResultSet rs = pr.executeQuery();
            if (rs.next()) {
                hostel = this.match(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hostel;
    }
    public boolean save(Hostel hostel){
        String query = "INSERT INTO public.hostel (hostel_name, hostel_price) VALUES (?, ?)";
        try {
            PreparedStatement pr = this.connection.prepareStatement(query);
            pr.setString(1, hostel.getName());
            pr.setInt(2, hostel.getHostelPrice());
            return pr.executeUpdate() != -1;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return true;
    }
    public boolean update(Hostel hostel){
        String query = "UPDATE public.hostel SET hostel_name = ?, hostel_price = ? WHERE hostel_id = ?";
        try {
            PreparedStatement pr = this.connection.prepareStatement(query);
            pr.setString(1, hostel.getName());
            pr.setInt(2, hostel.getHostelPrice());
            pr.setInt(3, hostel.getId());
            return pr.executeUpdate() != -1;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return true;
    }
    public boolean delete(int id){
        String query = "DELETE FROM public.hostel WHERE hostel_id = ?";
        try{
            PreparedStatement pr = this.connection.prepareStatement(query);
            pr.setInt(1,id);
            return pr.executeUpdate() != -1;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return true;
    }

    public Hostel match(ResultSet rs) throws SQLException {
        Hostel hostel = new Hostel();
        hostel.setId(rs.getInt("hostel_id"));
        hostel.setName(rs.getString("hostel_name"));
        hostel.setHostelPrice(rs.getInt("hostel_price"));
        return hostel;
    }
}