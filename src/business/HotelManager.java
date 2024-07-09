package business;

import core.Helper;
import dao.HotelDao;
import entity.Hotel;

import java.util.ArrayList;

public class HotelManager {
    private final HotelDao hotelDao;

    public HotelManager(){
        this.hotelDao =new HotelDao();
    }

    public ArrayList<Hotel> findAll(){
        return this.hotelDao.findAll();
    }
    public Hotel findById(int id){
        return this.hotelDao.findById(id);
    }
    public boolean delete(int id){
        if(this.findById(id)==null){
            Helper.showMessage(id+" numaralı otel bulunamadı");
            return false;
        }
        return this.hotelDao.delete(id);
    }

    public ArrayList<Object[]> getForTable(int size, ArrayList<Hotel> all){
        ArrayList<Object[]> hotelRowList = new ArrayList<>();
        for(Hotel hotel : all){
            Object[] rowObject = new Object[size];
            int i =0;
            rowObject[i++] = hotel.getId();
            rowObject[i++] = hotel.getName();
            rowObject[i++] = hotel.getAddress();
            rowObject[i++] = hotel.getCity();
            rowObject[i++] = hotel.getMail();
            rowObject[i++] = hotel.getPhoneNo();
            rowObject[i++] = hotel.getStar();
            rowObject[i++] = hotel.getHosteltype().getName();
            rowObject[i++] = hotel.isRoomService();
            rowObject[i++] = hotel.isSpa();
            rowObject[i++] = hotel.isConcierge();
            rowObject[i++] = hotel.isPool();
            rowObject[i++] = hotel.isWifi();
            rowObject[i++] = hotel.isPark();
            rowObject[i++] = hotel.isFitness();
            hotelRowList.add(rowObject);
        }
        return hotelRowList;
    }

    public boolean save(Hotel hotel){
        if(hotel.getId() != 0){
            Helper.showMessage("error");
        }
        return this.hotelDao.save(hotel);
    }
    public boolean update(Hotel hotel){
        if(this.findById(hotel.getId()) == null){
            Helper.showMessage("notfound");
        }
        return this.hotelDao.update(hotel);
    }
    public  ArrayList<Hotel> searchForTable(int hostelId, int room_id  ){
        String select = "SELECT * FROM public.hotel";
        ArrayList<String> wherelist = new ArrayList<>();
        if(hostelId != 0){
            wherelist.add("hotel_hostel_type_id ="+ hostelId);

        }
        String whereStr = String.join(" AND ",wherelist);
        String query = select;
        if(whereStr.length() > 0){
            query += " WHERE "+ whereStr;
        }
        return  this.hotelDao.selectByQuery(query);
    }
}
