package model;

import typeofcar.Legkovie;

/**
 * Класс описывает общие свойства машины Tesla
 */

public class Tesla extends Legkovie {

    private boolean avtopilot;
    private int zaryadSolnechnoiPaneli;
    private final int zapasKmOtSolnPanel = 20;
    private double zapas;

    public Tesla() {

    }

    public Tesla(int zaryadSolnechnoiPaneli, boolean avtopilot) {
        this.zaryadSolnechnoiPaneli = zaryadSolnechnoiPaneli;
        this.avtopilot = avtopilot;
    }

    public int getZaryadSolnechnoiPaneli() {
        return zaryadSolnechnoiPaneli;
    }

    public void setZaryadSolnechnoiPaneli(int zaryadSolnechnoiPaneli) {
        this.zaryadSolnechnoiPaneli = zaryadSolnechnoiPaneli;
    }

    public boolean getAvtopilot() {
        return avtopilot;
    }

    public void setAvtopilot(boolean avtopilot) {
        this.avtopilot = avtopilot;
    }

    /**
     * Метод позволяет узнать на какое кол-во км хватит заряда
     * солнечной панели
     * @param zaryadSolnechnoiPaneli заряд солнечной панели
     * @return
     */

    public double zapasKm(int zaryadSolnechnoiPaneli) {
        return (double) zapasKmOtSolnPanel / 100 * zaryadSolnechnoiPaneli;
    }
}
