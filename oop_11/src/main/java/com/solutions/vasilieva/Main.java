package com.solutions.vasilieva;

import com.car.Car;

import model.Lada;
import model.RollsRoys;
import model.Tesla;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import typeofcar.Gruzovie;
import typeofcar.Legkovie;
import typeofcar.SpetsTekhnika;

import java.util.Scanner;

public class Main {

    private static final Logger LOG = LogManager.getLogger(Main.class);

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        Car car = new Car();
        Car car1 = new Car(250, 3);

        Legkovie legkovie = new Legkovie();
        Legkovie legkovie1 = new Legkovie(260, 2, 40, "Sedan");

        Gruzovie gruzovie = new Gruzovie();
        Gruzovie gruzovie1 = new Gruzovie(3000, 4000);

        SpetsTekhnika spetsTekhnika = new SpetsTekhnika();
        SpetsTekhnika spetsTekhnika1 = new SpetsTekhnika("Гусенечные", "Строительство");

        Lada lada = new Lada();
        Lada lada1 = new Lada(15, 28);

        Tesla tesla = new Tesla();
        Tesla tesla1 = new Tesla(75, true);

        RollsRoys rollsRoys = new RollsRoys();
        RollsRoys rollsRoys1 = new RollsRoys(300, 3.5f,
                5, 50,
                "Sedan", true);

        car.setMaxSpeed(252);
        car.setObemDvigatela(2);
        LOG.info("максмальная скорость " + car1.getMaxSpeed());
        LOG.info("объем двигателя " + car1.getObemDvigatela());
        LOG.info("максмальная скорость " + car.getMaxSpeed());
        LOG.info("объем двигателя " + car.getObemDvigatela());
        LOG.info("dobavlenie skorosti " + car1.razognat(133, car1.getMaxSpeed()));



        legkovie.setObemBagazhnika(70);
        legkovie.setKuzov("Хетчбек");
        legkovie.setMaxSpeed(200);
        int obemPredmeta = 23;
        LOG.info("объем багажника " + legkovie.getObemBagazhnika());
        LOG.info("тип кузова " + legkovie.getKuzov());
        LOG.info("максимальная скорость " + legkovie.getMaxSpeed());
        LOG.info("объем багажника " + legkovie1.getObemBagazhnika());
        LOG.info("тип кузова " + legkovie1.getKuzov());
        LOG.info("максимальная скорость " + legkovie1.getMaxSpeed());
        LOG.info("остаток места после загрузки " + legkovie1.ostatokMesta(
                legkovie1.getObemBagazhnika(), obemPredmeta));



        gruzovie.setObemPritsepa(5000);
        gruzovie.setMaxVesGruza(6000);
        int vesGruza = 2563;
        LOG.info("объем прицепа " + gruzovie1.getObemPritsepa());
        LOG.info("максимальный вес груза " + gruzovie1.getMaxVesGruza());
        LOG.info("остаток веса который можно доложить " + gruzovie.ostalosMesta(gruzovie.getMaxVesGruza(), vesGruza));


        spetsTekhnika.setTypeChassie("колесные");
        spetsTekhnika.setOblastPrimeneniya("Аграрная");
        LOG.info("тип шасси " + spetsTekhnika.getTypeChassie());
        LOG.info("область применения " + spetsTekhnika.getOblastPrimeneniya());
        LOG.info("тип шасси " + spetsTekhnika1.getTypeChassie());
        LOG.info("область применения " + spetsTekhnika1.getOblastPrimeneniya());
        LOG.info("на каком покрытии вы будете использовать технику?\n " +
                "1 - грунтовое покрытие \n" +
                "2 - асфальтированное покрытие");
        int number = in.nextInt();
        LOG.info("Вам подходит " + spetsTekhnika.nuzhniyTypChassie(number) + " тип шасси");


        lada.setProtsentRzhavchiny(10);
        lada.setDiametrDirkiDlyaZimneyRibalki(38);
        int diametrRyby = 13;
        LOG.info("процент ржавчины " + lada.getProtsentRzhavchiny());
        LOG.info("диаметр дырки для имней рыбалки " + lada.getDiametrDirkiDlyaZimneyRibalki());
        LOG.info("процент ржавчины " + lada1.getProtsentRzhavchiny());
        LOG.info("диаметр дырки для имней рыбалки " + lada1.getDiametrDirkiDlyaZimneyRibalki());
        LOG.info("вашу рыбу " + lada1.prohodRyby(lada1.getDiametrDirkiDlyaZimneyRibalki(), diametrRyby));


        tesla.setAvtopilot(false);
        tesla.setZaryadSolnechnoiPaneli(13);
        LOG.info("заряд солнечной батареи " + tesla.getZaryadSolnechnoiPaneli());
        LOG.info("автопилот " + tesla.getAvtopilot());
        LOG.info("заряд солнечной батареи " + tesla1.getZaryadSolnechnoiPaneli());
        LOG.info("автопилот " + tesla1.getAvtopilot());
        LOG.info("запаса хватит на " + tesla1.zapasKm(tesla1.getZaryadSolnechnoiPaneli()) + " км");


        int kolvoPassazhirov = 2;
        int kazhdomuBokalov = 3;
        rollsRoys.setZontVDveri(true);
        rollsRoys.setMaxSpeed(300);
        rollsRoys.setObemBagazhnika(60);
        rollsRoys.setKolvoButilokVMiniBare(5);
        LOG.info("для поездки " + rollsRoys1.neobkhodimoButilok(kolvoPassazhirov, kazhdomuBokalov));
    }
}
