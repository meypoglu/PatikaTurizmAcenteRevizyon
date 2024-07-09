package entity;

import java.time.LocalDate;

public class Season {
    private int id;
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
    private double fare;

    public Season(String name, LocalDate startDate, LocalDate endDate, double fare) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.fare = fare;
    }

    public Season() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public double getFare() {
        return fare;
    }

    public void setFare(double fare) {
        this.fare = fare;
    }
}
