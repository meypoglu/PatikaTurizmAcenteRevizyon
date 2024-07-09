package business;

import core.Helper;
import dao.ReservationDao;
import entity.Reservation;

import java.util.ArrayList;

public class ReservationManager {
    private final ReservationDao reservationDao;

    public ReservationManager(){
        this.reservationDao=new ReservationDao();
    }

    public ArrayList<Reservation> findAll(){
        return this.reservationDao.findAll();
    }

    public boolean save(Reservation reservation){
        if(reservation.getId() != 0){
            Helper.showMessage("error");
        }
        return this.reservationDao.save(reservation);
    }

    public  Reservation findById(int id){
        return this.reservationDao.findById(id);
    }

    public boolean update(Reservation reservation){
        if(this.findById(reservation.getId()) == null){
            Helper.showMessage("notfound");
        }
        return this.reservationDao.update(reservation);
    }
    public boolean delete(int id){
        if(this.findById(id)==null){
            Helper.showMessage(id+" kayıtlı rezervasyon bulunamadı.");
            return false;
        }
        return this.reservationDao.delete(id);
    }
    public ArrayList<Object[]> getForTable(int size){
        ArrayList<Object[]> reservationRowList = new ArrayList<>();
        for(Reservation reservation : this.findAll()){
            Object[] rowObject = new Object[size];
            int i =0;
            rowObject[0] = reservation.getId();
            rowObject[1] = reservation.getCustomer_name();
            rowObject[2] = reservation.getOtel().toString();
            rowObject[3] = reservation.getRoomId();
            rowObject[4] = reservation.getStartDate();
            rowObject[5] = reservation.getFinishDate();
            rowObject[6] = reservation.getAdultNumber();
            rowObject[7] = reservation.getChildNumber();
            rowObject[8] = reservation.getTotalPrice();

            reservationRowList.add(rowObject);
        }
        return reservationRowList;
    }
}
