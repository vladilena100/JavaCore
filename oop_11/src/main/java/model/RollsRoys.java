package model;

import typeofcar.Legkovie;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RollsRoys extends Legkovie {

    private static final Logger LOG = LogManager.getLogger(RollsRoys.class);

    private int kolvoButilokVMiniBare;
    private boolean zontVDveri;
    private final int obemButilki = 700;
    private final int obemBokala = 200;
    private final int maxKolvoPassazhirov = 3;

    public RollsRoys() {

    }

    public RollsRoys(int maxSpeed, float obemDvigatela,
                     int kolvoButilokVMiniBare, int obemBagazhnika,
                     String kuzov, boolean zontVDveri) {
        super(maxSpeed, obemDvigatela, obemBagazhnika, kuzov);
        this.kolvoButilokVMiniBare = kolvoButilokVMiniBare;
        this.zontVDveri = zontVDveri;
    }

    public int getKolvoButilokVMiniBare() {
        return kolvoButilokVMiniBare;
    }

    public void setKolvoButilokVMiniBare(int kolvoButilokVMiniBare) {
        this.kolvoButilokVMiniBare = kolvoButilokVMiniBare;
    }

    public boolean getZontVDveri() {
        return zontVDveri;
    }

    public void setZontVDveri(boolean zontVDveri) {
        this.zontVDveri = zontVDveri;
    }

    public String neobkhodimoButilok(int people, int kolvoBokalovNaOdnogo) {
        String result = "";
        float tselieButilki = 0;
        if(people <= maxKolvoPassazhirov) {
            float nuzhnoShampanskogoMl = kolvoBokalovNaOdnogo * obemBokala * people;
            float butilki = nuzhnoShampanskogoMl / 700;
            float ostatok = butilki % 1;
            tselieButilki = butilki - ostatok;
            if(ostatok < 1) {
                tselieButilki += 1;
            }
        } else {
            result = "слишком много пассажиров";
        }
        if(tselieButilki <= kolvoButilokVMiniBare) {
            result = "шампанского хватит";
        } else {
            result = "нужно докупить шампанского";
        }
        return result;
    }
}
