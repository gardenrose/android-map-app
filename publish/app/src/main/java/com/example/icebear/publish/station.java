package com.example.icebear.publish;


import android.view.ViewDebug;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;


public class station {


    String name;
    double lat;
    double lng;
    int sensorTemperature;
    int sensorHumidity;
    int sensorWindSpeed;

    public station() {

    }

    public station(String Name, double lat, double lng) {
        this.name = Name;
        this.lat = lat;
        this.lng = lng;
    }

    public void generiraj() {
        Random r1 = new Random();
        Random r2 = new Random();
        Random r3 = new Random();
        sensorTemperature = -5 + r1.nextInt(49);
        sensorHumidity = r2.nextInt(100);
        sensorWindSpeed = 11 + r3.nextInt(10);

    }

    public String getName() {
        return name;
    }

    public double getLat() {
        return lat;
    }

    public double getLng() {
        return lng;
    }

    public int getSensorTemperature() {
        return sensorTemperature;
    }

    public int getSensorHumidity() {
        return sensorHumidity;
    }

    public int getSensorWindSpeed() {
        return sensorWindSpeed;
    }
}