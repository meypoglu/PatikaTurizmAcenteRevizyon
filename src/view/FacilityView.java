package view;

import business.FacilityManager;
import core.Helper;
import entity.Facility;

import javax.swing.*;

public class FacilityView extends Layout {
    private JPanel container;
    private JLabel lbl_feature;
    private JLabel lbl_feature_name;
    private JTextField fld_feature_name;
    private JButton btn_feature_save;
    private Facility facility;
    private FacilityManager facilityManager;

    public FacilityView(Facility facility){
        this.facilityManager = new FacilityManager();
        this.add(container);
        this.guiInitialize(300,200);
        this.facility = facility;

        if(facility != null){
            this.fld_feature_name.setText(facility.getName());
        }

        btn_feature_save.addActionListener(e -> {
            if(Helper.isFieldEmpty(this.fld_feature_name)){
                Helper.showMessage("fill");
            }else {
                boolean result = true;
                if(this.facility == null){
                    Facility obj = new Facility(fld_feature_name.getText());
                    result = this.facilityManager.save(obj);
                }else {
                    this.facility.setName(fld_feature_name.getText());
                    result = this.facilityManager.update(this.facility);
                }

                if (result){
                    Helper.showMessage("Feature saved successfully.");
                    dispose();
                }else {
                    Helper.showMessage("error");
                }
            }
        });
    }
}
