package view;

import business.HostelManager;
import core.Helper;
import entity.Hostel;

import javax.swing.*;

public class HostelView extends Layout {
    private JPanel container;
    private JTextField fld_pension_name;
    private JButton btn_pension_save;
    private JLabel lbl_pension_name;
    private JLabel lbl_pension;
    private JTextField tfl_hostel_price;
    private Hostel hostel;
    private HostelManager hostelManager;

    public HostelView(Hostel hostel){
        this.hostelManager = new HostelManager();
        this.add(container);
        this.guiInitialize(300,200);
        this.hostel = hostel;

        if(hostel != null){
            this.fld_pension_name.setText(hostel.getName());
            this.tfl_hostel_price.setText(String.valueOf(hostel.getHostelPrice()));
        }

        btn_pension_save.addActionListener(e -> {

            try {
            if(Helper.isFieldEmpty(this.fld_pension_name) || Helper.isFieldEmpty(this.tfl_hostel_price)){
                Helper.showMessage("fill");
            }else {
                boolean result = true;
                if(this.hostel == null){
                    Hostel obj = new Hostel(fld_pension_name.getText(), Integer.parseInt(tfl_hostel_price.getText()));
                    result = this.hostelManager.save(obj);
                }else {
                    this.hostel.setName(fld_pension_name.getText());
                    this.hostel.setHostelPrice(Integer.parseInt(tfl_hostel_price.getText()));
                    result = this.hostelManager.update(this.hostel);
                }

                if (result){
                    Helper.showMessage("done");
                    dispose();
                }else {
                    Helper.showMessage("error");
                }
            } } catch (NumberFormatException ex) {
                Helper.showMessage("Lütfen sayı alanlarına numerik değerler giriniz");
            }
        });
    }
}
