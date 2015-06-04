package ladsoft.asynctest.entity;

public class City {
    private int id;
    private String name;
    private String country;
    private GeoCoordinates coord;

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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public GeoCoordinates getCoord() {
        return coord;
    }

    public void setCoord(GeoCoordinates coord) {
        this.coord = coord;
    }
}
