package ladsoft.sunshine.entity;

import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;

/**
 * Created by suematsu on 5/10/15.
 */
public abstract class BaseEntity {

    protected boolean objectContainsEntry(JSONObject jsonObject, String objectName){
        boolean result = false;

        if(jsonObject != null && jsonObject.has(objectName)) {
            result = true;
        }

        return result;
    }

    protected String getString(JSONObject jsonObject, String objectName) {
        String result = "";

        if(this.objectContainsEntry(jsonObject, objectName)) {
            try {
                if(!jsonObject.isNull(objectName)) {
                    result = jsonObject.getString(objectName);
                }
            }
            catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return result;
    }

    protected Integer getInteger(JSONObject jsonObject, String objectName) {
        Integer result = null;

        if(this.objectContainsEntry(jsonObject, objectName)) {
            try {
                if(!jsonObject.isNull(objectName)) {
                    result = jsonObject.getInt(objectName);
                }
            }
            catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return result;
    }

    protected BigDecimal getBigDecimal(JSONObject jsonObject, String objectName) {
        BigDecimal result = null;

        if(this.objectContainsEntry(jsonObject, objectName)) {
            try {
                if(!jsonObject.isNull(objectName)) {
                    result = BigDecimal.valueOf(jsonObject.getDouble(objectName));
                }
            }
            catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return result;
    }



}
