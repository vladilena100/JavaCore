package typeofcar;

import com.car.Car;

public class Legkovie extends Car {
    private String kuzov;
    private int obemBagazhnika;
    int ostatokObema;

    public Legkovie() {

    }

    public Legkovie(int maxSpeed, float obemDvigatela, int obemBagazhnika, String kuzov) {
        super(maxSpeed, obemDvigatela);
        this.obemBagazhnika = obemBagazhnika;
        this.kuzov = kuzov;
    }

    public String getKuzov() {
        return kuzov;
    }

    public void setKuzov(String kuzov) {
        this.kuzov = kuzov;
    }

    public int getObemBagazhnika() {
        return obemBagazhnika;
    }

    public void setObemBagazhnika(int obemBagazhnika) {
        this.obemBagazhnika = obemBagazhnika;
    }

    public int ostatokMesta (int obemBagazhnika, int predmet) {
        if(predmet <= obemBagazhnika) {
            ostatokObema = obemBagazhnika - predmet;
        }
        return ostatokObema;
    }
}
