package typeofcar;

import com.car.Car;

/**
 * Класс описывает общие свойства Спец.техники
 */

public class SpetsTekhnika extends Car {


    private String typeChassie;
    private String oblastPrimeneniya;

    public SpetsTekhnika() {

    }

    public SpetsTekhnika(String typeChassie, String oblastPrimeneniya) {
        this.typeChassie = typeChassie;
        this.oblastPrimeneniya = oblastPrimeneniya;
    }

    public String getTypeChassie() {
        return typeChassie;
    }

    public void setTypeChassie(String typeChassie) {
        this.typeChassie = typeChassie;
    }

    public String getOblastPrimeneniya() {
        return oblastPrimeneniya;
    }

    public void setOblastPrimeneniya(String oblastPrimeneniya) {
        this.oblastPrimeneniya = oblastPrimeneniya;
    }

    /**
     * Метод указывает какой тип шасси необходим для определенного покрытия
     * @param nomer номер покрытия
     * @return
     */

    public String nuzhniyTypChassie(int nomer) {
        String result = "";
        switch (nomer) {
            case 1:
                result = "гусенечный";
                break;
            case 2 :
                result = "колесный";
                break;
            default:
                result = "не верный номер";
                break;
        }
        return result;
    }
}
