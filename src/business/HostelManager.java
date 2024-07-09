package business;

import core.Helper;
import dao.HostelDao;
import entity.Hostel;

import java.util.ArrayList;

public class HostelManager {
    private final HostelDao hostelDao;

    public HostelManager(){
        this.hostelDao =new HostelDao();
    }

    public ArrayList<Hostel> findAll(){
        return this.hostelDao.findAll();
    }

    public boolean save(Hostel hostel){
        if(hostel.getId() != 0){
            Helper.showMessage("error");
        }
        return this.hostelDao.save(hostel);
    }

    public Hostel findById(int id){
        return this.hostelDao.findById(id);
    }

    public boolean update(Hostel hostel){
        if(this.findById(hostel.getId()) == null){
            Helper.showMessage("notfound");
        }
        return this.hostelDao.update(hostel);
    }
    public boolean delete(int id){
        if(this.findById(id)==null){
            Helper.showMessage(id+" numaralı obje bulunamadı");
            return false;
        }
        return this.hostelDao.delete(id);
    }
    public ArrayList<Object[]> getForTable(int size){
        ArrayList<Object[]>hostelRowList = new ArrayList<>();
        for(Hostel hostel : this.findAll()){
            Object[] rowObject = new Object[size];
            int i =0;
            rowObject[i++] = hostel.getId();
            rowObject[i++] = hostel.getName();
            rowObject[i++] = hostel.getHostelPrice();
            hostelRowList.add(rowObject);
        }
        return hostelRowList;
    }
}
