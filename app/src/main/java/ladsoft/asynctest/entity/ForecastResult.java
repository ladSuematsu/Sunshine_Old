package ladsoft.asynctest.entity;

import java.math.BigDecimal;

/**
 * Created by suematsu on 5/10/15.
 */
public class ForecastResult {

    private String cod;
    private BigDecimal message;
    private int cnt;
    private City city;
    private Forecast [] list;

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public BigDecimal getMessage() {
        return message;
    }

    public void setMessage(BigDecimal message) {
        this.message = message;
    }

    public int getCnt() {
        return cnt;
    }

    public void setCnt(int cnt) {
        this.cnt = cnt;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public ForecastResult() {
    }

    public Forecast[] getList() {
        return list;
    }

    public void setList(Forecast[] list) {
        this.list = list;
    }

//    public Forecast(JSONObject jsonObject) {
//        this.placeName = this.getString(jsonObject, "");
//        this.status = this.getString(jsonObject, "");
//        this.minTemp = this.getBigDecimal(jsonObject, "");
//        this.maxTemp = this.getBigDecimal(jsonObject, "");
//    }

//    public BigDecimal getMinTemp() {
//        return minTemp;
//    }
//
//    public BigDecimal getMaxTemp() {
//        return maxTemp;
//    }
//
//    public String getPlaceName() {
//        return placeName;
//    }
//
//    public String getStatus() {
//        return status;
//    }
//
//    public void setMinTemp(BigDecimal minTemp) {
//        this.minTemp = minTemp;
//    }
//
//    public void setMaxTemp(BigDecimal maxTemp) {
//        this.maxTemp = maxTemp;
//    }
//
//    public void setPlaceName(String placeName) {
//        this.placeName = placeName;
//    }
//
//    public void setStatus(String status) {
//        this.status = status;
//    }
}

