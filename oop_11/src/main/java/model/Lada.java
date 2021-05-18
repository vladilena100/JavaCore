package model;

import typeofcar.Legkovie;

/**
 * Класс описывает общие свойства машин Lada
 */

public class Lada extends Legkovie {

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

    /**
     * Метод позволяет узнать сможете ли вы вытащить рыбу из отверстия
     * под ковриком для зимней рыбалки
     * @param diametrDirkiDlyaZimneyRibalki диаметр отверстия под ковриком для зимней рыбалки
     * @param diametrRyby диаметр рыбы
     * @return
     */

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
