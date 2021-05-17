package model;

import typeofcar.Legkovie;

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

    public double zapasKm(int zaryadSolnechnoiPaneli) {
        //double na1protsente = zapasKmOtSolnPanel / 100 * zaryadSolnechnoiPaneli;
        //zapas = na1protsente * zaryadSolnechnoiPaneli;
        return (double) zapasKmOtSolnPanel / 100 * zaryadSolnechnoiPaneli;
    }
}
