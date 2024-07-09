package business;

import core.Helper;
import dao.FacilityDao;
import entity.Facility;

import java.util.ArrayList;

public class FacilityManager {
    private final FacilityDao facilityDao;

    public FacilityManager(){
        this.facilityDao =new FacilityDao();
    }

    public ArrayList<Facility> findAll(){
        return this.facilityDao.findAll();
    }
    public boolean save(Facility facility){
        if(facility.getId() != 0){
            Helper.showMessage("Hata");
        }
        return this.facilityDao.save(facility);
    }

    public Facility findById(int id){
        return this.facilityDao.findById(id);
    }

    public boolean update(Facility facility){
        if(this.findById(facility.getId()) == null){
            Helper.showMessage("Hata");
        }
        return this.facilityDao.update(facility);
    }
    public boolean delete(int id){
        if(this.findById(id)==null){
            Helper.showMessage(id+" numaralı obje bulunamadı");
            return false;
        }
        return this.facilityDao.delete(id);
    }
    public ArrayList<Object[]> getForTable(int size){
        ArrayList<Object[]> facilityRowList = new ArrayList<>();
        for(Facility facility : this.findAll()){
            Object[] rowObject = new Object[size];
            int i =0;
            rowObject[i++] = facility.getId();
            rowObject[i++] = facility.getName();
            facilityRowList.add(rowObject);
        }
        return facilityRowList;
    }
}
