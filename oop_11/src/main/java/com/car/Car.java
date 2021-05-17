package com.car;

public class Car {

    private  int maxSpeed;
    private final int startSpeed = 0;
    private float obemDvigatela;
    int speed;

    public  Car() {

    }

    public Car(int maxSpeed, float obemDvigatela) {
        this.maxSpeed = maxSpeed;
        this.obemDvigatela = obemDvigatela;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public Number getObemDvigatela() {
        return obemDvigatela;
    }

    public void setObemDvigatela(float obemDvigatela) {
        this.obemDvigatela = obemDvigatela;
    }

    public int razognat(int plusSpeed, int maxSpeed) {
        if (plusSpeed <= maxSpeed) {
            speed = startSpeed + plusSpeed;
        }
        return speed;
    }
}
