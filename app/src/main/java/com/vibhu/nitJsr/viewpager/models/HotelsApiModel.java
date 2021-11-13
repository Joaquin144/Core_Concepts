package com.vibhu.nitJsr.viewpager.models;

public class HotelsApiModel {
    public String group,geoId,longitude,latitude,name;

    public HotelsApiModel(String group, String geoId, String longitude, String latitude, String name) {
        this.group = group;
        this.geoId = geoId;
        this.longitude = longitude;
        this.latitude = latitude;
        this.name = name;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getGeoId() {
        return geoId;
    }

    public void setGeoId(String geoId) {
        this.geoId = geoId;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
