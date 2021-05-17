package model;

import typeofcar.Legkovie;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Lada extends Legkovie {

    private static final Logger LOG = LogManager.getLogger(Lada.class);

    private int protsentRzhavchiny;
    private int diametrDirkiDlyaZimneyRibalki;

    public Lada() {

    }

    public Lada(int protsentRzhavchiny, int diametrDirkiDlyaZimneyRibalki) {
        this.protsentRzhavchiny = protsentRzhavchiny;
        this.diametrDirkiDlyaZimneyRibalki = diametrDirkiDlyaZimneyRibalki;
    }

    public int getProtsentRzhavchiny() {
        return protsentRzhavchiny;
    }

    public void setProtsentRzhavchiny(int protsentRzhavchiny) {
        this.protsentRzhavchiny = protsentRzhavchiny;
    }

    public int getDiametrDirkiDlyaZimneyRibalki() {
        return diametrDirkiDlyaZimneyRibalki;
    }

    public void setDiametrDirkiDlyaZimneyRibalki(int diametrDirkiDlyaZimneyRibalki) {
        this.diametrDirkiDlyaZimneyRibalki = diametrDirkiDlyaZimneyRibalki;
    }

    public String prohodRyby(int diametrDirkiDlyaZimneyRibalki, int diametrRyby) {
        String result = "";
        if(diametrRyby < diametrDirkiDlyaZimneyRibalki) {
            result = "можно достать через дырку для зимней рыбалки";
        } else {
            result = "нельзя достать через дырку для зимней рыбалки";
        }
        return result;
    }
}
