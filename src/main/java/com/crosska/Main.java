package com.crosska;

import java.util.Map;

public class Main {

    public static void main(String[] args) {

        Parameters parameters = new Parameters();
        Map<String, Object> map = parameters.getParameters();

        int param = (Integer) map.get("defaultColumn");
        if (args.length == 1) {
            try {
                param = Integer.parseInt(args[0]);
                if (param < 0) {
                    System.out.println("Ошибка, параметр должен быть больше 0");
                    return;
                }
            } catch (NumberFormatException ex) {
                System.out.println("Ошибка, укажите параметр числом");
                return;
            }
        } else if (args.length > 1) {
            System.out.println("Ошибка, доступен запуск с одним параметром или без параметром");
            return;
        }
        System.out.println("Параметр " + param);

        AirportControl flightControl = new AirportControl();
        flightControl.find(param);
    }

}