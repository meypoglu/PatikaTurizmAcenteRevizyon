package view;

import business.HotelManager;
import business.ReservationManager;
import business.RoomManager;
import core.ComboItem;
import core.Helper;
import entity.Hotel;
import entity.Reservation;
import entity.Room;

import javax.swing.*;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class ReservationView extends Layout {
    private JPanel container;
    private JLabel lbl_reservation;
    private JTextField fld_customer_name;
    private JComboBox cmb_hotel;
    private JTextField fld_adult_number;
    private JTextField fld_child_number;
    private JButton btn_reservation_save;
    private JLabel lbl_customer_name;
    private JLabel lbl_otel;
    private JLabel lbl_start_date;
    private JTextField fld_strt_date;
    private JTextField fld_fnsh_date;
    private JLabel lbl_fnsh_date;
    private JLabel lbl_adult_number;
    private JLabel lbl_child_number;
    private JComboBox cmb_room_type;
    private ReservationManager reservationManager;
    private HotelManager hotelManager;
    private Reservation reservation;
    private RoomManager roomManager;

    public ReservationView(Reservation reservation) {
        this.reservation = reservation;
        this.reservationManager = new ReservationManager();
        this.hotelManager = new HotelManager();
        this.roomManager = new RoomManager();
        this.add(container);
        this.guiInitialize(500,500);

        fillComboboxHotel(cmb_hotel, hotelManager.findAll());
        fillComboboxRoom(cmb_room_type, roomManager.findAll());

        if(reservation != null){
            int roomId = reservation.getRoomId();
            int hotelId = reservation.getHotel().getId();
            this.fld_customer_name.setText(reservation.getCustomer_name());
            this.cmb_room_type.setSelectedItem(new ComboItem(roomId, roomManager.findById(roomId).getName()));
            this.cmb_hotel.setSelectedItem(new ComboItem(hotelId, hotelManager.findById(hotelId).getName()));
            this.fld_strt_date.setText(String.valueOf(Date.valueOf(reservation.getStartDate())));
            this.fld_fnsh_date.setText(String.valueOf(Date.valueOf(reservation.getFinishDate())));
            this.fld_adult_number.setText(Integer.toString(reservation.getAdultNumber()));
            this.fld_child_number.setText(Integer.toString(reservation.getChildNumber()));
        }

        this.btn_reservation_save.addActionListener(e -> {
            try {
            if(Helper.isFieldListEmpty(new JTextField[]{fld_customer_name,fld_adult_number})){
                Helper.showMessage("fill");
            }else {

                String customer_name = this.fld_customer_name.getText();

                LocalDate strt_date = LocalDate.parse(this.fld_strt_date.getText());
                LocalDate fnsh_date = LocalDate.parse(this.fld_fnsh_date.getText());


                String adult_number = this.fld_adult_number.getText();
                String child_number = this.fld_child_number.getText();

                ComboItem selectedHotelItem = (ComboItem) cmb_hotel.getSelectedItem();
                ComboItem selectedRoomItem = (ComboItem) cmb_room_type.getSelectedItem();

                if (selectedHotelItem == null) {
                    Helper.showMessage("Lütfen bir otel seçiniz.");
                    return;
                }
                if (selectedRoomItem == null) {
                    Helper.showMessage("Lütfen bir oda tipi seçiniz.");
                }
                int hotelId = selectedHotelItem.getKey();
                Hotel selectedHotel = this.hotelManager.findById(hotelId);
                if (selectedHotel == null) {
                    Helper.showMessage(("Seçilen otel bulunamadı."));
                    return;
                }
                int roomId = selectedRoomItem.getKey();
                Room selectedRoom = this.roomManager.findById(roomId);
                if (selectedRoom == null) {
                    Helper.showMessage("Seçilen oda bulunamadı.");
                    return;
                }

                boolean result;
                if (this.reservation == null) {
                    Reservation newReservation = new Reservation(selectedRoom.getId(), customer_name, selectedHotel, selectedRoom, strt_date, fnsh_date, Integer.parseInt(adult_number), Integer.parseInt(child_number));
                    result = this.reservationManager.save(newReservation);
                } else {
                    int cmbHotelId = ((ComboItem) cmb_hotel.getSelectedItem()).getKey();
                    int cmbRoomId = ((ComboItem) cmb_room_type.getSelectedItem()).getKey();
                    this.reservation.setCustomer_name(fld_customer_name.getText());
                    this.reservation.setHotel(hotelManager.findById(cmbHotelId));
                    this.reservation.setRoomId(cmbRoomId);
                    this.reservation.setStartDate(LocalDate.parse(fld_strt_date.getText()));
                    this.reservation.setFinishDate(LocalDate.parse(fld_fnsh_date.getText()));
                    this.reservation.setAdultNumber(Integer.parseInt(fld_adult_number.getText()));
                    this.reservation.setChildNumber(Integer.parseInt(fld_child_number.getText()));
                    result = this.reservationManager.update(this.reservation);
                }

                if (result) {
                    Helper.showMessage("Rezervasyon başarıyla kaydedildi.");
                    dispose();
                } else {
                    Helper.showMessage("Rezervasyon kaydedilemedi.");
                }

            }
            } catch (DateTimeParseException ex) {
                Helper.showMessage("Lütfen tarihleri yyyy/MM/dd formatında giriniz");
            } catch(NumberFormatException ex) {
                Helper.showMessage("Lütfen sayı alanlarına numerik değerler giriniz");
            }
        });
    }


}

