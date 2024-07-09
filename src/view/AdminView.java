package view;

import business.*;
import core.BooleanCellRenderer;
import core.ComboItem;
import core.Helper;
import entity.Hotel;
import entity.Hostel;
import entity.Room;
import entity.User;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class AdminView extends Layout {
    private JPanel container;
    private JLabel lbl_welcome;
    private JPanel pnl_top;
    private JTabbedPane pnl_wrapper;
    private JButton btn_logout;
    private JPanel pnl_otel;
    private JTable tbl_hotel;
    private JScrollPane scrl_otel;
    private JPanel pnl_pension;
    private JPanel pnl_feature;
    private JTable tbl_hostel;
    private JScrollPane scrl_pension;
    private JTable tbl_facility;
    private JScrollPane scrl_feature;
    private JPanel pnl_season;
    private JTable tbl_season;
    private JScrollPane scrl_season;
    private JTable tbl_user;
    private JPanel pnl_user;
    private JScrollPane scrl_user;
    private JTable tbl_room;
    private JScrollPane scrl_room;
    private JPanel pnl_room;
    private JTable tbl_reservation;
    private JPanel pnl_reservation;
    private JScrollPane scrl_reservation;
    private JComboBox cmb_s_hostelType;
    private JComboBox cmb_s_room;
    private JButton btn_search_hotel;
    private JButton btn_cncl_otel;
    private JPanel pnl_search;
    private JScrollPane scrl_search;
    private JTable tbl_search;
    private JTextField tfl_checkIn_date;
    private JTextField tfl_hotel_city;
    private JTextField tfl_hotel_name;
    private JButton btn_search;
    private JTextField tfl_checkOut_date;
    private JButton btn_filter_user;
    private JComboBox cmb_user;

    private User user;
    private DefaultTableModel tmdl_hotel = new DefaultTableModel();
    private DefaultTableModel tmdl_search = new DefaultTableModel();
    private DefaultTableModel tmdl_hostel = new DefaultTableModel();
    private DefaultTableModel tmdl_feature = new DefaultTableModel();
    private DefaultTableModel tmdl_season = new DefaultTableModel();
    private DefaultTableModel tmdl_user = new DefaultTableModel();
    private DefaultTableModel tmdl_room = new DefaultTableModel();
    private DefaultTableModel tmdl_reservation = new DefaultTableModel();
    private HotelManager hotelManager;
    private HostelManager hostelManager;
    private FacilityManager facilityManager;
    private SeasonManager seasonManager;
    private ReservationManager reservationManager;
    private UserManager userManager;
    private RoomManager roomManager;
    private JPopupMenu hostelMenu;
    private JPopupMenu facilityMenu;
    private JPopupMenu hotelMenu;
    private JPopupMenu searchMenu;
    private JPopupMenu seasonMenu;
    private JPopupMenu userMenu;
    private JPopupMenu roomMenu;
    private JPopupMenu reservationMenu;
    private Object[] col_hotel;
    private  SearchManager searchManager;

    public AdminView(User user) {
        this.hotelManager = new HotelManager();
        this.hostelManager = new HostelManager();
        this.facilityManager = new FacilityManager();
        this.seasonManager = new SeasonManager();
        this.userManager = new UserManager();
        this.roomManager = new RoomManager();
        this.reservationManager = new ReservationManager();
        this.searchManager = new SearchManager();

        this.add(container);
        this.guiInitialize(1000, 400);
        this.user = user;
        if (user == null) {
            dispose();
        }




        this.lbl_welcome.setText("Hoşgeldiniz :" + this.user.getFullName());

        loadHotelTable(null);
        loadHotelComponent();
        loadHotelFilter();

        loadHostelTable();
        loadHostelComponent();

        loadFacilityTable();
        loadFacilityComponent();

        loadSeasonTable();
        loadSeasonComponent();

        loadUserFilter();
        loadUserTable(null);
        loadUserComponent();

        loadRoomTable();
        loadRoomComponent();

        loadReservationTable();
        loadReservationComponent();

        booleanTR(tbl_hotel, 8,14);
        tmdl_hotel.addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                booleanTR(tbl_hotel,8, 14);
            }
        });

        btn_logout.addActionListener(e -> dispose());

        btn_search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadSearchTable(null);
                loadSearchComponent();
                booleanTR(tbl_search,7, 13);
            }
        });
    }

    public void loadHostelTable() {
        Object[] col_hostel = {"ID", "Pansiyon Adı", "Pansiyon Ücreti"};
        ArrayList<Object[]> hostelList = this.hostelManager.getForTable(col_hostel.length);
        this.createTable(tmdl_hostel, tbl_hostel, col_hostel, hostelList);
    }

    public void loadHostelComponent() {
        this.tbl_hostel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int selected_row = tbl_hostel.rowAtPoint(e.getPoint());
                tbl_hostel.setRowSelectionInterval(selected_row, selected_row);
                if (e.isPopupTrigger()) {
                    showHostelMenu(e);
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    showHostelMenu(e);
                }
            }

            private void showHostelMenu(MouseEvent e) {
                hostelMenu.show(e.getComponent(), e.getX(), e.getY());
            }
        });

        this.hostelMenu = new JPopupMenu();
        this.hostelMenu.add("Yeni").addActionListener(e -> {
            HostelView hostelView = new HostelView(null);
            hostelView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadHostelTable();
                }
            });
        });
        this.hostelMenu.add("Güncelle").addActionListener(e -> {
            int selectPensionId = Integer.parseInt(tbl_hostel.getValueAt(tbl_hostel.getSelectedRow(), 0).toString());
            HostelView hostelView = new HostelView(this.hostelManager.findById(selectPensionId));
            hostelView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadHostelTable();
                }
            });
        });
        this.hostelMenu.add("Sil").addActionListener(e -> {
            if (Helper.confirm("sure")) {
                int selectPensionId = this.getTableSelectedRow(tbl_hostel, 0);
                if (this.hostelManager.delete(selectPensionId)) {
                    loadHostelTable();
                } else {
                    Helper.showMessage("error");
                }
            }
        });

        this.tbl_hostel.setComponentPopupMenu(hostelMenu);
    }

    public void loadFacilityTable() {
        Object[] col_feature = {"ID", "Tesis Özellik Adı"};
        ArrayList<Object[]> featureList = this.facilityManager.getForTable(col_feature.length);
        this.createTable(tmdl_feature, tbl_facility, col_feature, featureList);
    }

    public void loadFacilityComponent() {
        this.tbl_facility.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int selected_row = tbl_facility.rowAtPoint(e.getPoint());
                tbl_facility.setRowSelectionInterval(selected_row, selected_row);
                if (e.isPopupTrigger()) {
                    showFeatureMenu(e);
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    showFeatureMenu(e);
                }
            }

            private void showFeatureMenu(MouseEvent e) {
                facilityMenu.show(e.getComponent(), e.getX(), e.getY());
            }
        });

        this.facilityMenu = new JPopupMenu();
        this.facilityMenu.add("Yeni").addActionListener(e -> {
            FacilityView facilityView = new FacilityView(null);
            facilityView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadFacilityTable();
                }
            });
        });
        this.facilityMenu.add("Güncelle").addActionListener(e -> {
            int selectFeatureId = Integer.parseInt(tbl_facility.getValueAt(tbl_facility.getSelectedRow(), 0).toString());
            FacilityView facilityView = new FacilityView(this.facilityManager.findById(selectFeatureId));
            facilityView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadFacilityTable();
                }
            });
        });
        this.facilityMenu.add("Sil").addActionListener(e -> {
            if (Helper.confirm("sure")) {
                int selectFeatureId = this.getTableSelectedRow(tbl_facility, 0);
                if (this.facilityManager.delete(selectFeatureId)) {
                    loadFacilityTable();
                } else {
                    Helper.showMessage("error");
                }
            }
        });

        this.tbl_facility.setComponentPopupMenu(facilityMenu);

        btn_filter_user.addActionListener(e -> {
            String selectedRole = cmb_user.getSelectedItem().toString();
            loadUserTable(selectedRole);
        });
    }

    public void loadHotelTable(ArrayList<Object[]> hotelList) {
        this.col_hotel = new Object[]{"Otel ID", "Otel Adı", "Otel Adresi", "Şehir", "Mail", "Telefon No", "Yıldız", "Pansiyon Tipi", "Oda Servisi", "Spa", "Hotel Concierge", "Yüzme Havuzu", "Ücretsiz WiFi", "Ücretsiz Otopark", "Fitness Center"};
        if (hotelList == null) {
            hotelList = this.hotelManager.getForTable(this.col_hotel.length, this.hotelManager.findAll());
        }

        createTable(this.tmdl_hotel, this.tbl_hotel, col_hotel, hotelList);
    }
    public void loadSearchTable(ArrayList<Object[]> searchList) {
        try {
            Object[] col_search = new Object[]{"Otel Adı", "Otel Adresi", "Otel Şehri", "Otel Yıldızı", "Pansiyon Tipi", "Oda Tipi", "Oda ID'si", "Room Service", "Spa", "Hotel Concierge", "Yüzme Havuzu", "Ücretsiz WiFi", "Ücretsiz Otopark", "Fitness Center", "Sezon", "Stok", "Fiyat"};
            LocalDate checkIn = null;
            LocalDate checkOut = null;
            if (!tfl_checkIn_date.getText().trim().isEmpty()) {
                checkIn = LocalDate.parse(tfl_checkIn_date.getText());
            }
            if (!tfl_checkOut_date.getText().trim().isEmpty()) {
                checkOut = LocalDate.parse(tfl_checkOut_date.getText());
            }
            if (searchList == null) {
                searchList = this.searchManager.getForTable(col_search.length, this.searchManager.findByParameters(tfl_hotel_city.getText(), tfl_hotel_name.getText(), checkIn, checkOut));
            }

            createTable(this.tmdl_search, this.tbl_search, col_search, searchList);
        } catch (DateTimeParseException ex) {
            Helper.showMessage("Lütfen tarih alanlarını yyyy/MM/dd formatına göre sağlayınız");
        } catch (NumberFormatException ex) {
            Helper.showMessage("Lütfen sayı alanlarına numerik girdi veriniz");
        }
    }



    public void loadSearchComponent() {
        this.tbl_search.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int selected_row = tbl_search.rowAtPoint(e.getPoint());
                tbl_search.setRowSelectionInterval(selected_row, selected_row);
                if (e.isPopupTrigger()) {
                    showSearchMenu(e);
                }
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    showSearchMenu(e);
                }
            }
            private void showSearchMenu(MouseEvent e) {
                searchMenu.show(e.getComponent(), e.getX(), e.getY());
            }
        });
        this.searchMenu = new JPopupMenu();
        this.searchMenu.add("Yeni").addActionListener(e -> {
            ReservationView reservationView = new ReservationView(null);
            int roomId = this.getTableSelectedRow(tbl_search, 6);
            reservationView.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                roomManager.decreaseStock(roomId);
                loadReservationTable();
                loadRoomTable();
                loadSearchTable(null);
                booleanTR(tbl_search, 7, 13);
                }
            });
        });
    }

    public void loadHotelComponent() {
        this.tbl_hotel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int selected_row = tbl_hotel.rowAtPoint(e.getPoint());
                tbl_hotel.setRowSelectionInterval(selected_row, selected_row);
                if (e.isPopupTrigger()) {
                    showHotelMenu(e);
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    showHotelMenu(e);
                }
            }

            private void showHotelMenu(MouseEvent e) {
                hotelMenu.show(e.getComponent(), e.getX(), e.getY());
            }
        });

        this.hotelMenu = new JPopupMenu();
        this.hotelMenu.add("Yeni").addActionListener(e -> {
            HotelView hotelView = new HotelView(null);
            hotelView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadHotelTable(null);
                    loadHotelFilter();
                }
            });
        });
        this.hotelMenu.add("Güncelle").addActionListener(e -> {
            int selectFeatureId = Integer.parseInt(tbl_hotel.getValueAt(tbl_hotel.getSelectedRow(), 0).toString());
            HotelView hotelView = new HotelView(this.hotelManager.findById(selectFeatureId));
            hotelView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadHotelTable(null);
                    loadHotelFilter();
                }
            });
        });
        this.hotelMenu.add("Sil").addActionListener(e -> {
            if (Helper.confirm("sure")) {
                int selectOtelId = this.getTableSelectedRow(tbl_hotel, 0);
                if (this.hotelManager.delete(selectOtelId)) {
                    loadHotelTable(null);
                    loadHotelFilter();
                } else {
                    Helper.showMessage("error");
                }
            }
        });

        this.tbl_hotel.setComponentPopupMenu(hotelMenu);

        this.btn_search_hotel.addActionListener(e -> {
            ComboItem selectedHostel = (ComboItem) this.cmb_s_hostelType.getSelectedItem();
            ComboItem selectedRoom = (ComboItem) this.cmb_s_room.getSelectedItem();

            ArrayList<Hotel> hotelListBySearches = this.hotelManager.searchForTable(selectedHostel.getKey(), selectedRoom.getKey());

            ArrayList<Object[]> hotelRowListBySearch = this.hotelManager.getForTable(this.col_hotel.length, hotelListBySearches);
            loadHotelTable(hotelRowListBySearch);
        });

        this.btn_cncl_otel.addActionListener(e -> {
            this.cmb_s_hostelType.setSelectedItem(null);
            this.cmb_s_room.setSelectedItem(null);
            loadHotelTable(null);
        });
    }

    public void loadSeasonTable() {
        Object[] col_season = {"ID", "Sezon Adı", "Başlangıç Tarihi", "Bitiş Tarihi", "Fiyat Katsayısı"};
        ArrayList<Object[]> seasonList = this.seasonManager.getForTable(col_season.length);
        this.createTable(tmdl_season, tbl_season, col_season, seasonList);
    }

    public void loadSeasonComponent() {
        this.tbl_season.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int selected_row = tbl_season.rowAtPoint(e.getPoint());
                tbl_season.setRowSelectionInterval(selected_row, selected_row);
                if (e.isPopupTrigger()) {
                    showSeasonMenu(e);
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    showSeasonMenu(e);
                }
            }

            private void showSeasonMenu(MouseEvent e) {
                seasonMenu.show(e.getComponent(), e.getX(), e.getY());
            }
        });

        this.seasonMenu = new JPopupMenu();
        this.seasonMenu.add("Yeni").addActionListener(e -> {
            SeasonView seasonView = new SeasonView(null);
            seasonView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadSeasonTable();
                }
            });
        });
        this.seasonMenu.add("Güncelle").addActionListener(e -> {
            int selectFeatureId = Integer.parseInt(tbl_season.getValueAt(tbl_season.getSelectedRow(), 0).toString());
            SeasonView seasonView = new SeasonView(this.seasonManager.findById(selectFeatureId));
            seasonView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadSeasonTable();
                }
            });
        });
        this.seasonMenu.add("Sil");

        this.tbl_season.setComponentPopupMenu(seasonMenu);
    }

    public void loadUserTable(String role) {
        Object[] col_user = {"ID", "Kullanıcı Adı", "Şifre", "Rol", "İsim Soyisim"};
        ArrayList<Object[]> userList;
        if (role == null || role.equals("hepsi")) {
            userList = this.userManager.getForTable(col_user.length);
        } else {
            userList = this.userManager.getForTableByRole(col_user.length, role);
        }
        this.createTable(tmdl_user, tbl_user, col_user, userList);
    }

    public void loadUserComponent() {
        this.tbl_user.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int selected_row = tbl_user.rowAtPoint(e.getPoint());
                tbl_user.setRowSelectionInterval(selected_row, selected_row);
                if (e.isPopupTrigger()) {
                    showUserMenu(e);
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    showUserMenu(e);
                }
            }

            private void showUserMenu(MouseEvent e) {
                userMenu.show(e.getComponent(), e.getX(), e.getY());
            }
        });

        this.userMenu = new JPopupMenu();
        this.userMenu.add("Yeni").addActionListener(e -> {
            UserView userView = new UserView(null);
            userView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadUserTable(null);
                }
            });
        });
        this.userMenu.add("Güncelle").addActionListener(e -> {
            int selectUserId = this.getTableSelectedRow(tbl_user, 0);
            UserView userView = new UserView(this.userManager.findById(selectUserId));
            userView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadUserTable(null);
                }
            });
        });
        this.userMenu.add("Sil").addActionListener(e -> {
            if (Helper.confirm("sure")) {
                int selectUserId = this.getTableSelectedRow(tbl_user, 0);
                if (this.userManager.delete(selectUserId)) {
                    loadUserTable(null);
                } else {
                    Helper.showMessage("error");
                }
            }
        });

        this.tbl_user.setComponentPopupMenu(userMenu);
    }

    public void loadRoomTable() {
        Object[] col_room = {"ID", "Otel Adı" , "Oda Adı", "Oda Fiyatı", "Stok", "Dönem"};
        ArrayList<Object[]> roomList = this.roomManager.getForTable(col_room.length);
        this.createTable(tmdl_room, tbl_room, col_room, roomList);
    }

    public void loadRoomComponent() {
        this.tbl_room.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int selected_row = tbl_room.rowAtPoint(e.getPoint());
                tbl_room.setRowSelectionInterval(selected_row, selected_row);
                if (e.isPopupTrigger()) {
                    showRoomMenu(e);
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    showRoomMenu(e);
                }
            }

            private void showRoomMenu(MouseEvent e) {
                roomMenu.show(e.getComponent(), e.getX(), e.getY());
            }
        });

        this.roomMenu = new JPopupMenu();
        this.roomMenu.add("Yeni").addActionListener(e -> {
            RoomView roomView = new RoomView(null);
            roomView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadRoomTable();
                }
            });
        });
        this.roomMenu.add("Güncelle").addActionListener(e -> {
            int selectUserId = this.getTableSelectedRow(tbl_room, 0);
            RoomView roomView = new RoomView(this.roomManager.findById(selectUserId));
            roomView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadRoomTable();
                }
            });
        });
        this.roomMenu.add("Sil").addActionListener(e -> {
            if (Helper.confirm("sure")) {
                int selectUserId = this.getTableSelectedRow(tbl_room, 0);
                if (this.roomManager.delete(selectUserId)) {
                    loadRoomTable();
                } else {
                    Helper.showMessage("error");
                }
            }
        });

        this.tbl_room.setComponentPopupMenu(roomMenu);
    }

    public void loadReservationTable() {
        Object[] col_reservation = {"ID", "Müşteri İsim", "Otel Bilgileri", "Oda ID'si", "Başlangıç Tarihi", "Bitiş Tarihi", "Yetişkin Sayısı", "Çocuk Sayısı", "Toplam Ücret"};
        ArrayList<Object[]> reservationList = this.reservationManager.getForTable(col_reservation.length);
        this.createTable(tmdl_reservation, tbl_reservation, col_reservation, reservationList);
    }

    public void loadReservationComponent() {
        this.tbl_reservation.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int selected_row = tbl_reservation.rowAtPoint(e.getPoint());
                tbl_reservation.setRowSelectionInterval(selected_row, selected_row);
                if (e.isPopupTrigger()) {
                    showReservationMenu(e);
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    showReservationMenu(e);
                }
            }

            private void showReservationMenu(MouseEvent e) {
                reservationMenu.show(e.getComponent(), e.getX(), e.getY());
            }
        });

        this.reservationMenu = new JPopupMenu();
        this.reservationMenu.add("Güncelle").addActionListener(e -> {
            int selectReservationId = this.getTableSelectedRow(tbl_reservation, 0);
            ReservationView reservationView = new ReservationView(this.reservationManager.findById(selectReservationId));
            reservationView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadReservationTable();
                }
            });
        });
        this.reservationMenu.add("Sil").addActionListener(e -> {
            if (Helper.confirm("sure")) {
                int selectUserId = this.getTableSelectedRow(tbl_reservation, 0);
                int roomId = this.getTableSelectedRow(tbl_reservation, 3);
                roomManager.increaseStock(roomId);
                if (this.reservationManager.delete(selectUserId)) {
                    loadReservationTable();
                    loadSearchTable(null);
                    loadRoomTable();
                } else {
                    Helper.showMessage("error");
                }
            }
        });

        this.tbl_reservation.setComponentPopupMenu(reservationMenu);
    }

    public void loadHotelFilter() {
        this.cmb_s_hostelType.removeAllItems();
        for (Hostel obj : hostelManager.findAll()) {
            this.cmb_s_hostelType.addItem(new ComboItem(obj.getId(), obj.getName()));
        }
        this.cmb_s_hostelType.setSelectedItem(null);
        this.cmb_s_room.removeAllItems();
        for (Room obj : roomManager.findAll()) {
            this.cmb_s_room.addItem(new ComboItem(obj.getId(), obj.getName()));
        }
        this.cmb_s_room.setSelectedItem(null);

        this.cmb_s_hostelType.removeAllItems();
        for (Hostel obj : hostelManager.findAll()){
            this.cmb_s_hostelType.addItem(new ComboItem(obj.getId(),obj.getName()));
        }
        this.cmb_s_hostelType.setSelectedItem(null);
        this.cmb_s_room.removeAllItems();
        for(Room obj : roomManager.findAll()){
            this.cmb_s_room.addItem(new ComboItem(obj.getId(),obj.getName()));
        }
        this.cmb_s_room.setSelectedItem(null);

    }

    public void loadUserFilter() {
        this.cmb_user.removeAllItems();
        this.cmb_user.addItem("admin");
        this.cmb_user.addItem("employee");
        this.cmb_user.addItem("hepsi");
    }

    public void booleanTR(JTable table, int startNum, int endNum) {
        for (int i = startNum;i <= endNum;i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(new BooleanCellRenderer());
        }
    }

    public void hideUserManagement() {
        pnl_wrapper.remove(pnl_user);
    }

}
