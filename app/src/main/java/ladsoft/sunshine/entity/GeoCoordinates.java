package ladsoft.sunshine.entity;

import java.math.BigDecimal;

/**
 * Created by suematsu on 5/16/15.
 */
public class GeoCoordinates {
    private BigDecimal lat;
    private BigDecimal lon;


    public BigDecimal getLat() {
        return lat;
    }

    public void setLat(BigDecimal lat) {
        this.lat = lat;
    }

    public BigDecimal getLon() {
        return lon;
    }

    public void setLon(BigDecimal lon) {
        this.lon = lon;
    }
}
