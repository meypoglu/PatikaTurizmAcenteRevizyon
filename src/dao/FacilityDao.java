package dao;

import core.Db;
import entity.Facility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class FacilityDao {
    private final Connection connection;

    public FacilityDao() {
        this.connection = Db.getInstance();
    }

    public ArrayList<Facility> findAll() {
        ArrayList<Facility> facilityList = new ArrayList<>();
        String query = "SELECT * FROM public.facility ORDER BY facility_id ASC";
        try {
            ResultSet rs = this.connection.createStatement().executeQuery(query);
            while (rs.next()) {
                facilityList.add(this.match(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return facilityList;
    }

    public Facility findById(int id) {
        Facility facility = null;
        String query = "SELECT * FROM public.facility WHERE facility_id = ?";
        try {
            PreparedStatement pr = this.connection.prepareStatement(query);
            pr.setInt(1, id);
            ResultSet rs = pr.executeQuery();
            if (rs.next()) {
                facility = this.match(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return facility;
    }

    public boolean save(Facility facility){
        String query = "INSERT INTO public.facility (facility_name) VALUES (?)";
        try {
            PreparedStatement pr = this.connection.prepareStatement(query);
            pr.setString(1, facility.getName());
            return pr.executeUpdate() != -1;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return true;
    }

    public boolean update(Facility facility){
        String query = "UPDATE public.facility SET facility_name = ? WHERE facility_id = ?";
        try {
            PreparedStatement pr = this.connection.prepareStatement(query);
            pr.setString(1, facility.getName());
            pr.setInt(2, facility.getId());
            return pr.executeUpdate() != -1;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return true;
    }
    public boolean delete(int id){
        String query = "DELETE FROM public.facility WHERE facility_id = ?";
        try{
            PreparedStatement pr = this.connection.prepareStatement(query);
            pr.setInt(1,id);
            return pr.executeUpdate() != -1;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return true;
    }

    public Facility match(ResultSet rs) throws SQLException {
        Facility facility = new Facility();
        facility.setId(rs.getInt("facility_id"));
        facility.setName(rs.getString("facility_name"));
        return facility;
    }
}
