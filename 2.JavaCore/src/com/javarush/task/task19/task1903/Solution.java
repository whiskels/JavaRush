package com.javarush.task.task19.task1903;

/* 
Адаптация нескольких интерфейсов
*/

import java.util.HashMap;
import java.util.Map;

public class Solution {
    public static Map<String, String> countries = new HashMap<String, String>();
    static {
        countries.put("UA","Ukraine");
        countries.put("RU","Russia");
        countries.put("CA","Canada");
    }

    public static void main(String[] args) {

    }

    public static class IncomeDataAdapter implements Customer, Contact {
        private IncomeData data;

        public IncomeDataAdapter(IncomeData data) {
            this.data = data;
        }

        @Override
        public String getCompanyName() {
            return data.getCompany();
        }

        @Override
        public String getCountryName() {
            String s = data.getCountryCode();
            String result = null;
            for (Map.Entry<String,String> entry : countries.entrySet()) {
                if (s.equals(entry.getKey())) {
                    result = entry.getValue();
                }
            }
            return result;
        }

        @Override
        public String getName() {
            String s = data.getContactLastName() + ", " + data.getContactFirstName();
            return s;
        }

        @Override
        public String getPhoneNumber() {
            String add = String.format("%010d", data.getPhoneNumber());
            while (add.length()<10) {
                add = "0" + add;
            }
            add = add.replaceAll("(\\d{3})(\\d{3})(\\d{2})(\\d{2})", "($1)$2-$3-$4");
            add = "+" + data.getCountryPhoneCode() + add;
            return add;
        }
    }


    public static interface IncomeData {
        String getCountryCode();        //For example: UA

        String getCompany();            //For example: JavaRush Ltd.

        String getContactFirstName();   //For example: Ivan

        String getContactLastName();    //For example: Ivanov

        int getCountryPhoneCode();      //For example: 38

        int getPhoneNumber();           //For example: 501234567
    }

    public static interface Customer {
        String getCompanyName();        //For example: JavaRush Ltd.

        String getCountryName();        //For example: Ukraine
    }

    public static interface Contact {
        String getName();               //For example: Ivanov, Ivan

        String getPhoneNumber();        //For example: +38(050)123-45-67
    }
}