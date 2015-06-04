package ladsoft.asynctest.entity;

import java.math.BigDecimal;

/**
 * Created by suematsu on 5/16/15.

 */
public class Temperature {
    private BigDecimal day;
    private BigDecimal min;
    private BigDecimal max;
    private BigDecimal night;
    private BigDecimal eve;
    private BigDecimal morn;

    public BigDecimal getDay() {
        return day;
    }

    public void setDay(BigDecimal day) {
        this.day = day;
    }

    public BigDecimal getMin() {
        return min;
    }

    public void setMin(BigDecimal min) {
        this.min = min;
    }

    public BigDecimal getMax() {
        return max;
    }

    public void setMax(BigDecimal max) {
        this.max = max;
    }

    public BigDecimal getNight() {
        return night;
    }

    public void setNight(BigDecimal night) {
        this.night = night;
    }

    public BigDecimal getEve() {
        return eve;
    }

    public void setEve(BigDecimal eve) {
        this.eve = eve;
    }

    public BigDecimal getMorn() {
        return morn;
    }

    public void setMorn(BigDecimal morn) {
        this.morn = morn;
    }
}
