package view;

import core.ComboItem;
import core.Helper;
import entity.Hotel;
import entity.Room;
import entity.Season;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Layout extends JFrame {
    public void guiInitialize(int width, int height){
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setTitle("Turizm Acente");
        this.setSize(width,height);
        this.setLocation(Helper.getLocationPoint("x",this.getSize()),Helper.getLocationPoint("y",this.getSize()));
        this.setVisible(true);
    }

    public void createTable(DefaultTableModel model, JTable table, Object[] columns, ArrayList<Object[]> rows){
        model.setColumnIdentifiers(columns);
        table.setModel(model);
        table.getTableHeader().setReorderingAllowed(false);
        table.getColumnModel().getColumn(0).setMaxWidth(75);
        table.setEnabled(false);

        DefaultTableModel clearModel =(DefaultTableModel) table.getModel();
        clearModel.setRowCount(0);

        if(rows == null){
            rows = new ArrayList<>();
        }

        for(Object[] row : rows){
            model.addRow(row);
        }
    }

    public int getTableSelectedRow(JTable table, int index){
        return Integer.parseInt(table.getValueAt(table.getSelectedRow(),index).toString());
    }

    public void fillComboboxHotel(JComboBox combo, List<Hotel> fillerList) {
        combo.removeAllItems();
        for (Hotel hotel : fillerList) {
            combo.addItem(new ComboItem(hotel.getId(), hotel.getName()));
        }
    }

    public void fillComboboxSeason(JComboBox combo, List<Season> fillerList) {
        combo.removeAllItems();
        for (Season season : fillerList) {
            combo.addItem(new ComboItem(season.getId(), season.getName()));
        }
    }

    public void fillComboboxRoom(JComboBox combo, List<Room> fillerList) {
        combo.removeAllItems();
        for (Room room : fillerList) {
            combo.addItem(new ComboItem(room.getId(), room.getName()));
        }
    }
}
