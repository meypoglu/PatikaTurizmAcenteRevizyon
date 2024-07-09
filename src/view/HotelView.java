package view;

import business.FacilityManager;
import business.HotelManager;
import business.HostelManager;
import business.RoomManager;
import core.ComboItem;
import core.Helper;
import entity.Hotel;
import entity.Hostel;
import entity.Room;

import javax.swing.*;

public class HotelView extends Layout {
    private JPanel container;
    private JTextField fld_hotel_name;
    private JTextField fld_hotel_address;
    private JTextField fld_hotel_mail;
    private JTextField fld_hotel_phoneno;
    private JTextField fld_hotel_star;
    private JComboBox<ComboItem> cmb_hotel_hostel_type;
    private JLabel lbl_otel;
    private JLabel lbl_otel_name;
    private JLabel lbl_otel_address;
    private JLabel lbl_otel_mail;
    private JLabel lbl_otel_phoneno;
    private JLabel lbl_otel_star;
    private JLabel lbl_otel_pensiontype;
    private JList list_features;
    private JButton btn_otel_save;
    private JComboBox<ComboItem> cmb_room;
    private JLabel lbl_otel_features;
    private JRadioButton btn_room_service;
    private JRadioButton btn_spa;
    private JRadioButton btn_concierge;
    private JRadioButton btn_pool;
    private JRadioButton btn_wifi;
    private JRadioButton btn_park;
    private JRadioButton btn_fitness;
    private JTextField tfl_city;
    private Hotel hotel;
    private HotelManager hotelManager;
    private HostelManager hostelManager;
    private FacilityManager facilityManager;
    private RoomManager roomManager;

    public HotelView(Hotel hotel){
        this.hotelManager = new HotelManager();
        this.hostelManager = new HostelManager();
        this.facilityManager = new FacilityManager();
        this.roomManager = new RoomManager();
        this.add(container);
        this.guiInitialize(700, 700);
        this.hotel = hotel;

        if (hotel != null) {
            this.fld_hotel_name.setText(hotel.getName());
            this.fld_hotel_address.setText(hotel.getAddress());
            this.tfl_city.setText(hotel.getCity());
            this.fld_hotel_mail.setText(hotel.getMail());
            this.fld_hotel_phoneno.setText(Long.toString(hotel.getPhoneNo()));
            this.fld_hotel_star.setText(String.valueOf(hotel.getStar()));
            this.cmb_hotel_hostel_type.addItem(new ComboItem(hotel.getHosteltype().getId(), hotel.getHosteltype().getName()));
            this.btn_room_service.setSelected(hotel.isRoomService());
            this.btn_spa.setSelected(hotel.isSpa());
            this.btn_concierge.setSelected(hotel.isConcierge());
            this.btn_pool.setSelected(hotel.isPool());
            this.btn_wifi.setSelected(hotel.isWifi());
            this.btn_park.setSelected(hotel.isPark());
            this.btn_fitness.setSelected(hotel.isFitness());
        }

        for (Hostel hostel : this.hostelManager.findAll()) {
            this.cmb_hotel_hostel_type.addItem(new ComboItem(hostel.getId(), hostel.getName()));
        }


        btn_otel_save.addActionListener(e -> {

            try {
            if (Helper.isFieldEmpty(this.fld_hotel_name)) {
                Helper.showMessage("Lütfen otel adını giriniz");
            } else {
                try {
                    String name = this.fld_hotel_name.getText();
                    String address = this.fld_hotel_address.getText();
                    String city = this.tfl_city.getText();
                    String mail = this.fld_hotel_mail.getText();
                    long phoneNo = Long.parseLong(this.fld_hotel_phoneno.getText());
                    int star = Integer.parseInt(this.fld_hotel_star.getText());

                    ComboItem selectedHostelItem = (ComboItem) this.cmb_hotel_hostel_type.getSelectedItem();
                    int hostelTypeId = selectedHostelItem.getKey();
                    Hostel selectedHostel = this.hostelManager.findById(hostelTypeId);

                    boolean roomService = this.btn_room_service.isSelected();
                    boolean spa = this.btn_spa.isSelected();
                    boolean concierge = this.btn_concierge.isSelected();
                    boolean pool = this.btn_pool.isSelected();
                    boolean wifi = this.btn_wifi.isSelected();
                    boolean park = this.btn_park.isSelected();
                    boolean fitness = this.btn_fitness.isSelected();

                    Hotel newHotel = new Hotel(name, address, city, mail, phoneNo, star, selectedHostel, roomService, spa, concierge, pool, wifi, park, fitness );
                    boolean result;

                    if (this.hotel == null) {
                        result = this.hotelManager.save(newHotel);
                    } else {
                        newHotel.setId(this.hotel.getId());
                        result = this.hotelManager.update(newHotel);
                    }

                    if (result) {
                        Helper.showMessage("Otel başarıyla kaydedildi");
                        dispose();
                    } else {
                        Helper.showMessage("Otel kaydedilirken hatayla karşılaşıldı");
                    }
                } catch (NumberFormatException ex) {
                    Helper.showMessage("Telefon numarası ve yıldız için geçerli girdi sağlayın");
                }
            } } catch (NumberFormatException ex) {
                Helper.showMessage("Lütfen sayı alanlarına numerik değerler giriniz");
            }
        });
    }
}
