package com.crosska;

import java.util.Map;

public class Main {

    public static void main(String[] args) {

        //Option option = new Option("s", "search", true, "Login");

        int[] res1 = {2, 24, 15, 580};
        int[] res2 = {210, 49, 2043};
        int[] res3 = {1, 997, 641, 35, 98};
        ResultElem el1 = new ResultElem("ИЩЕМЭТО", res1, 12334);
        ResultElem el2 = new ResultElem("ИЩЕМТО", res2, 9445);
        ResultElem el3 = new ResultElem("ИЩЕМСАМОЕ", res3, 718252);

        JSONObject jsonObject = new JSONObject(123213, "C:/result.json");

        jsonObject.addNewResult(el1);
        jsonObject.addNewResult(el2);
        jsonObject.addNewResult(el3);

        JSONController jsonController = new JSONController();
        jsonController.writeResult(jsonObject);

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