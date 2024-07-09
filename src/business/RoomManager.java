package business;

import core.Helper;
import dao.RoomDao;
import entity.Room;
import entity.Season;

import java.util.ArrayList;

public class RoomManager {
    private final RoomDao roomDao;
    private final HotelManager hotelManager;
    private final SeasonManager seasonManager;

    public RoomManager(){
        this.roomDao = new RoomDao();
        this.hotelManager = new HotelManager();
        this.seasonManager = new SeasonManager();
    }

    public ArrayList<Room> findAll(){
        return this.roomDao.findAll();
    }
    public boolean save(Room room){
        if(room.getId() != 0){
            Helper.showMessage("error");
        }
        return this.roomDao.save(room);
    }

    public boolean update(Room room){
        if(this.findById(room.getId()) == null){
            Helper.showMessage("notfound");
        }
        return this.roomDao.update(room);
    }

    public boolean delete(int id){
        if(this.findById(id)==null){
            Helper.showMessage(id+" numaralı oda bulunamadı");
            return false;
        }
        return this.roomDao.delete(id);
    }

    public ArrayList<Object[]> getForTable(int size){
        ArrayList<Object[]> roomRowList = new ArrayList<>();
        for(Room room : this.findAll()){
            Object[] rowObject = new Object[size];
            int i =0;
            rowObject[i++] = room.getId();
            rowObject[i++] = hotelManager.findById(room.getHotelId()).getName();
            rowObject[i++] = room.getName();
            rowObject[i++] = room.getPrice();
            rowObject[i++] = room.getStock();
            rowObject[i++] = seasonManager.findById(room.getSeasonId()).getName();
            roomRowList.add(rowObject);
        }
        return roomRowList;
    }

    public Room findById(int id){
        return this.roomDao.findById(id);
    }

    public void increaseStock(int roomId) {
        this.roomDao.increaseStock(roomId);
    }

    public void decreaseStock(int roomId) {
        this.roomDao.decreaseStock(roomId);
    }

}
