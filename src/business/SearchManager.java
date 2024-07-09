package business;

import dao.SearchDao;
import entity.Room;
import entity.Search;

import java.time.LocalDate;
import java.util.ArrayList;

public class SearchManager {
    private final SearchDao searchDao;

    public SearchManager() {
        this.searchDao = new SearchDao();
    }

    public ArrayList<Object[]> getForTable(int size, ArrayList<Search> all){
        ArrayList<Object[]> searchRowList = new ArrayList<>();
        for(Search search : all){
            Object[] rowObject = new Object[size];
            int i =0;
            rowObject[i++] = search.getHotelName();
            rowObject[i++] = search.getHotelAddress();
            rowObject[i++] = search.getHotelCity();
            rowObject[i++] = search.getHotelStar();
            rowObject[i++] = search.getHostelType();
            rowObject[i++] = search.getRoomType();
            rowObject[i++] = search.getRoomId();
            rowObject[i++] = search.isRoomService();
            rowObject[i++] = search.isSpa();
            rowObject[i++] = search.isConcierge();
            rowObject[i++] = search.isPool();
            rowObject[i++] = search.isWifi();
            rowObject[i++] = search.isPark();
            rowObject[i++] = search.isFitness();
            rowObject[i++] = search.getSeason();
            rowObject[i++] = search.getStock();
            rowObject[i++] = search.getPrice();
            searchRowList.add(rowObject);
        }
        return searchRowList;
    }

    public ArrayList<Search> findByParameters(String city, String hotelName, LocalDate checkIn, LocalDate checkOut) {
        return this.searchDao.findByParameters(city, hotelName, checkIn, checkOut);
    }

}
