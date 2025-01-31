package entity;

public class Facility {
    private int id;
    private String name;

    public Facility(int id, String name) {
        this.id = id;
        this.name = name;
    }
    public Facility(String name) {
        this.name = name;
    }

    public Facility() {
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
}
