package view;

import business.FacilityManager;
import business.HotelManager;
import business.RoomManager;
import business.SeasonManager;
import core.ComboItem;
import core.Helper;
import entity.Room;
import entity.Season;

import javax.swing.*;
import java.awt.*;

public class RoomView extends Layout{
    private JPanel container;
    private JLabel lbl_room;
    private JTextField fld_room_name;
    private JTextField fld_room_price;
    private JLabel lbl_room_name;
    private JLabel lbl_room_price;
    private JButton btn_room_save;
    private JTextField fld_room_stock;
    private JLabel lbl_room_stock;
    private JComboBox cmb_room_hotel;
    private JComboBox cmb_season;
    private Room room;
    private RoomManager roomManager;
    private FacilityManager facilityManager;
    private HotelManager hotelManager;
    private SeasonManager seasonManager;

    public RoomView(Room room){
        this.roomManager = new RoomManager();
        this.facilityManager = new FacilityManager();
        this.hotelManager = new HotelManager();
        this.seasonManager = new SeasonManager();
        this.room = room;
        this.add(container);
        this.guiInitialize(300,400);
        fillComboboxHotel(cmb_room_hotel, hotelManager.findAll());
        fillComboboxSeason(cmb_season, seasonManager.findAll());

        // ComboBox Renderer
        cmb_room_hotel.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                if (value instanceof ComboItem) {
                    setText(((ComboItem) value).getValue());
                }
                return this;
            }
        });

        cmb_season.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                if (value instanceof ComboItem) {
                    setText(((ComboItem) value).getValue());
                }
                return this;
            }
        });

        if(room != null){
            int hotelId = room.getHotelId();
            int seasonId = room.getSeasonId();
            this.fld_room_name.setText(room.getName());
            this.cmb_room_hotel.setSelectedItem(new ComboItem(hotelId, hotelManager.findById(hotelId).getName()));
            this.cmb_season.setSelectedItem(new ComboItem(seasonId, seasonManager.findById(seasonId).getName()));
            this.fld_room_price.setText(String.valueOf(room.getPrice()));
            this.fld_room_stock.setText(Integer.toString(room.getStock()));
        }

        // Kaydet butonu
        btn_room_save.addActionListener(e -> {

            try {
            if(Helper.isFieldEmpty(this.fld_room_name) || Helper.isFieldEmpty(this.fld_room_price) || Helper.isFieldEmpty(this.fld_room_stock)){
                Helper.showMessage("fill");
            }else {
                boolean result = true;
                ComboItem selectedComboHotel = (ComboItem) cmb_room_hotel.getSelectedItem();
                ComboItem selectedComboSeason = (ComboItem) cmb_season.getSelectedItem();
                if(this.room == null){
                    Room obj = new Room(fld_room_name.getText(),Integer.parseInt(fld_room_price.getText()),Integer.parseInt(fld_room_stock.getText()), selectedComboHotel.getKey(), selectedComboSeason.getKey());
                    result = this.roomManager.save(obj);
                }else {
                    this.room.setName(fld_room_name.getText());
                    this.room.setPrice(Integer.parseInt(fld_room_price.getText()));
                    this.room.setStock(Integer.parseInt(fld_room_stock.getText()));
                    this.room.setHotelId(selectedComboHotel.getKey());
                    this.room.setSeasonId(selectedComboSeason.getKey());
                    result = this.roomManager.update(this.room);
                }

                if (result){
                    Helper.showMessage("Oda başarıyla kaydedildi");
                    dispose();
                }else {
                    Helper.showMessage("error");
                }
            }} catch (NumberFormatException ex) {
                Helper.showMessage("Lütfen sayı alanlarına numerik değerler giriniz");
            }
        });

    }
}
