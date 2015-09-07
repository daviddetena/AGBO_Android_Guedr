package com.daviddetena.guedr.model;

/**
 * Created by daviddetena on 07/09/15.
 */
public class Forecast {

    private float mMaxTemp;
    private float mMinTemp;
    private float mHumidity;
    private String mDescription;
    private String mIcon;

    /**
     * Constructor con todos los campos
     * @param description
     * @param humidity
     * @param icon
     * @param maxTemp
     * @param minTemp
     */
    public Forecast(String description, float humidity, String icon, float maxTemp, float minTemp) {
        mDescription = description;
        mHumidity = humidity;
        mIcon = icon;
        mMaxTemp = maxTemp;
        mMinTemp = minTemp;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public float getHumidity() {
        return mHumidity;
    }

    public void setHumidity(float humidity) {
        mHumidity = humidity;
    }

    public String getIcon() {
        return mIcon;
    }

    public void setIcon(String icon) {
        mIcon = icon;
    }

    public float getMaxTemp() {
        return mMaxTemp;
    }

    public void setMaxTemp(float maxTemp) {
        mMaxTemp = maxTemp;
    }

    public float getMinTemp() {
        return mMinTemp;
    }

    public void setMinTemp(float minTemp) {
        mMinTemp = minTemp;
    }


    @Override
    public String toString() {
        return getDescription();
    }
}
