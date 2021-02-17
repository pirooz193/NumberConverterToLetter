package com.company;

import java.text.DecimalFormat;

public class PersianNumberConverter {

    public static void main(String[] args) {
        System.out.println(PersianNumberConverter.convert(878569));
    }



        private static final String[] tensNames = {
                "",
                " ده",
                " بیست",
                " سی",
                " چهل",
                " پنجاه",
                " شست",
                " هفتاد",
                " هشتاد",
                " نود"
        };

        private static final String[] numNames = {
                "",
                " یک",
                " دو",
                " سه",
                " چهار",
                " پنج",
                " شش",
                " هفت",
                " هشت",
                " نه",
                " ده",
                " یازده",
                " دوازده",
                " سیزده",
                " چهارده",
                " پانزده",
                " شانزده",
                " هفده",
                " هجده",
                " نوزده"
        };

    private PersianNumberConverter() {}

        private static String convertLessThanOneThousand(int number) {
        String soFar;

        if (number % 100 < 20){
            soFar = numNames[number % 100];
            number /= 100;
        }
        else {
            soFar = numNames[number % 10];
            number /= 10;

            soFar = tensNames[number % 10] + soFar;
            number /= 10;
        }
        if (number == 0) return soFar;
        return numNames[number] + " صد و" + soFar;
    }


        public static String convert(long number) {
        // 0 to 999 999 999 999
        if (number == 0) { return "صفر"; }

        String snumber = Long.toString(number);

        // pad with "0"
        String mask = "000000000000";
//        String mask = "۰۰۰۰۰۰۰۰۰۰۰۰";
        DecimalFormat df = new DecimalFormat(mask);
        snumber = df.format(number);

        // XXXnnnnnnnnn
        int billions = Integer.parseInt(snumber.substring(0,3));
        // nnnXXXnnnnnn
        int millions  = Integer.parseInt(snumber.substring(3,6));
        // nnnnnnXXXnnn
        int hundredThousands = Integer.parseInt(snumber.substring(6,9));
        // nnnnnnnnnXXX
        int thousands = Integer.parseInt(snumber.substring(9,12));

        String tradBillions;
        switch (billions) {
            case 0:
                tradBillions = "";
                break;
            case 1 :
                tradBillions = convertLessThanOneThousand(billions)
                        + " میلیارد ";
                break;
            default :
                tradBillions = convertLessThanOneThousand(billions)
                        + " میلیارد ";
        }
        String result =  tradBillions;

        String tradMillions;
        switch (millions) {
            case 0:
                tradMillions = "";
                break;
            case 1 :
                tradMillions = convertLessThanOneThousand(millions)
                        + " میلیون و ";
                break;
            default :
                tradMillions = convertLessThanOneThousand(millions)
                        + " میلیون و  ";
        }
        result =  result + tradMillions;

        String tradHundredThousands;
        switch (hundredThousands) {
            case 0:
                tradHundredThousands = "";
                break;
            case 1 :
                tradHundredThousands = "یک هزار ";
                break;
            default :
                tradHundredThousands = convertLessThanOneThousand(hundredThousands)
                        + " هزار ";
        }
        result =  result + tradHundredThousands;

        String tradThousand;
        tradThousand = convertLessThanOneThousand(thousands);
        result =  result + tradThousand;

        // remove extra spaces!
        return result.replaceAll("^\\s+", "").replaceAll("\\b\\s{2,}\\b", " ");
    }

    }


