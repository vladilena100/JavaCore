package typeofcar;

import com.car.Car;

public class Gruzovie extends Car {
    private int obemPritsepa;
    private int maxVesGruza;
    private final int startVesGruza = 0;
    private int ostalosVes;

    public Gruzovie() {

    }

    public Gruzovie(int obemPritsepa, int maxVesGruza) {
        this.obemPritsepa = obemPritsepa;
        this.maxVesGruza = maxVesGruza;
    }

    public int getObemPritsepa() {
        return obemPritsepa;
    }

    public void setObemPritsepa(int obemPritsepa) {
        this.obemPritsepa = obemPritsepa;
    }

    public int getMaxVesGruza() {
        return maxVesGruza;
    }

    public void setMaxVesGruza(int maxVesGruza) {
        this.maxVesGruza = maxVesGruza;
    }

    public int ostalosMesta(int maxVesGruza, int dobavitVes) {
        if(dobavitVes <= maxVesGruza) {
            ostalosVes = maxVesGruza - dobavitVes;
        }
        return ostalosVes;
    }
}
