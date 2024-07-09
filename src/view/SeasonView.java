package view;

import business.SeasonManager;
import core.Helper;
import entity.Season;

import javax.swing.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class SeasonView extends Layout{
    private JPanel container;
    private JTextField tfl_season_name;
    private JTextField tfl_strt_date;
    private JTextField tfl_end_date;
    private JTextField tfl_price_multiplier;
    private JButton btn_season_save;
    private Season season;
    private SeasonManager seasonManager;
    public SeasonView(Season season){
        this.seasonManager = new SeasonManager();
        this.add(container);
        this.guiInitialize(300,400);
        this.season = season;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        if (season != null) {
            tfl_season_name.setText(season.getName());
            tfl_strt_date.setText(season.getStartDate().format(formatter));
            tfl_end_date.setText(season.getEndDate().format(formatter));
            tfl_price_multiplier.setText(Double.toString(season.getFare()));
        }


        btn_season_save.addActionListener(e -> {
            if (Helper.isFieldEmpty(this.tfl_season_name) ||  Helper.isFieldEmpty(this.tfl_strt_date) || Helper.isFieldEmpty(this.tfl_end_date) || Helper.isFieldEmpty(this.tfl_price_multiplier)) {
                Helper.showMessage("fill");
            } else {
                boolean result = true;
                try {
                    if (this.season == null) {
                        Season obj = new Season(tfl_season_name.getText(), LocalDate.parse(tfl_strt_date.getText(), formatter), LocalDate.parse(tfl_end_date.getText(), formatter) , Double.parseDouble(tfl_price_multiplier.getText()));
                        result = this.seasonManager.save(obj);
                    } else {
                        this.season.setName(tfl_season_name.getText());
                        this.season.setStartDate(LocalDate.parse(tfl_strt_date.getText()));
                        this.season.setEndDate(LocalDate.parse(tfl_end_date.getText()));
                        this.season.setFare(Double.parseDouble(tfl_price_multiplier.getText()));
                        result = this.seasonManager.update(this.season);
                    }

                    if (result) {
                        Helper.showMessage("Sezon başarıyla kaydedildi");
                        dispose();
                    } else {
                        Helper.showMessage("error");
                    }
                } catch (DateTimeParseException ex) {
                    Helper.showMessage("Tarih formatını yyyy-MM-dd şeklinde giriniz");
                } catch (NumberFormatException ex) {
                    Helper.showMessage("Fiyat çarpanı formatı hatalı. Geçerli bir sayı giriniz");
                }
            }
        });

    }
}
