package ru.selenium.util;

import org.apache.log4j.Logger;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class GenerateRandomData {
    private static final Logger log = Logger.getLogger(GenerateRandomData.class);
    static final String AB = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    static Random rnd = new Random();

    public String GetRandomText(int size, Boolean lowerCase) {
        StringBuilder builder = new StringBuilder();
        Random random = new Random();
        String text;
        char ch;
        for (int i = 0; i < size; i++) {
            ch = (char) ((int) (Math.floor(26 * random.nextInt() + 65)));
            builder.append(ch);
        }
        if (lowerCase) {
            text = builder.toString().toLowerCase();
        } else
            text = builder.toString().toUpperCase();

        return text;
    }

    public String GetRandomTextLogin() {
        String ch = generateData();

        return "ACHIV" + ch;
    }

    public String generateData() {
        StringBuilder builder = new StringBuilder();

        String dateStr = String.valueOf(new Date());
        DateFormat readFormat = new SimpleDateFormat(
                "EEE MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH);

        DateFormat writeFormat = new SimpleDateFormat("MM_dd_HH_mm_ss");
        Date date = null;
        try {
            date = readFormat.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        String ch = "";
        if (date != null) {
            ch = writeFormat.format(date);
        }
        builder.append(ch);
        return ch;
    }

    public String generateDataFolder() {
        StringBuilder builder = new StringBuilder();

        String dateStr = String.valueOf(new Date());
        DateFormat readFormat = new SimpleDateFormat(
                "EEE MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH);

        DateFormat writeFormat = new SimpleDateFormat("MM_dd_HH");
        Date date = null;
        try {
            date = readFormat.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        String ch = "";
        if (date != null) {
            ch = writeFormat.format(date);
        }
        builder.append(ch);
        return ch;
    }

    // / <summary>
    // / Случайное число от min до max
    // / </summary>
    // / <param name="min"></param>
    // / <param name="max"></param>
    // / <returns></returns>
    public String GetRandomInt(int min, int max) {
        Random random = new Random();
        if (max == min)
            return String.valueOf(min);
        else
            try {
                return String.valueOf(random.nextInt(max - min) + min);
            } catch (IllegalArgumentException e) {
                log.error("Min=" + min + ".Max=" + max + "\n" + e);
                throw e;
            }
    }

    // / <summary>
    // / Случайное количество цифр
    // / </summary>
    // / <returns></returns>
    public String getRandomInt(int size) {
        Random random = new Random();
        String rezult = "";
        for (int x = 1; x < size; x = x + 1)
            rezult = rezult + String.valueOf(random.nextInt(8) + 1);
        return rezult;
    }

    // / <summary>
    // / Случайное число от min до max
    // / </summary>
    // / <param name="min"></param>
    // / <param name="max"></param>
    // / <returns></returns>
    public String GetRandomFull(int size) {
        Random r = new Random();
        final int initialCapacity = 62;
        // a vector is a variable-size array
        final List<Character> chars = new Vector<Character>(initialCapacity);

        // add digits 0�9
        for (char c = '0'; c <= '9'; c++) {
            chars.add(c);
        }

        // add uppercase A�Z and '@'
        for (char c = 'A'; c <= 'Z'; c++) {
            chars.add(c);
        }

        // add lowercase a�z
        for (char c = 'a'; c <= 'z'; c++) {
            chars.add(c);
        }

        // Copy the chars over to a simple array, now that we know
        // the length. The .toArray method could have been used here,
        // but its usage is a pain.

        final char[] charArray = new char[size - 1];

        for (int i = 0; i < size - 1; i++) {
            charArray[i] = chars.get(r.nextInt(initialCapacity)).charValue();
        }

        return new String(charArray) + r.nextInt(9);
    }

    // / <summary>
    // / Случайный текст, size - длина,
    // / Текст вперемешку с цифрами
    // / Random random = new Random();
    // / </summary>
    // / <returns></returns>
    public String GetRandomFull(int size, boolean inti) {
        String retur = null;
        Random r = new Random();
        int initialCapacity = 52;
        // a vector is a variable-size array
        final List<Character> chars = new Vector<Character>(initialCapacity);

        // add uppercase A�Z and '@'
        for (char c = 'A'; c <= 'Z'; c++) {
            chars.add(c);
        }

        // add lowercase a�z
        for (char c = 'a'; c <= 'z'; c++) {
            chars.add(c);
        }
        if (!inti) {
            final char[] charArray = new char[size];

            for (int i = 0; i < size - 1; i++) {
                charArray[i] = chars.get(r.nextInt(initialCapacity))
                        .charValue();
            }

            retur = new String(charArray);
        }
        initialCapacity = 62;
        // add digits 0�9
        for (char c = '0'; c <= '9'; c++) {
            chars.add(c);
        }

        // Copy the chars over to a simple array, now that we know
        // the length. The .toArray method could have been used here,
        // but its usage is a pain.

        if (inti) {
            final char[] charArray = new char[size - 1];

            for (int i = 0; i < size - 2; i++) {
                charArray[i] = chars.get(r.nextInt(initialCapacity))
                        .charValue();
            }

            retur = new String(charArray) + r.nextInt(9);
        }

        return retur;
    }

    public String GetData() {
        return String.valueOf(GetRandomInt(1, 30) + "."
                + GetRandomInt(1, 12) + "." + GetRandomInt(1, 2050));
    }

    // / <summary>
    // / День, месяц, год. (не более реальной даты)
    // / </summary>
    // / <returns></returns>
    public String GetDataReal(int day, int mounth, int year) {
        day = 30;
        mounth = 12;
        year = 2013;
        return String.valueOf(GetRandomInt(1, day) + "."
                + GetRandomInt(1, mounth) + "." + GetRandomInt(1950, year));
    }

    public String GetEmail() {
        String dateStr = String.valueOf(new Date());

        DateFormat readFormat = new SimpleDateFormat(
                "EEE MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH);

        DateFormat writeFormat = new SimpleDateFormat("MM_dd_HH_mm_ss");
        Date date = null;
        try {
            date = readFormat.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        String ch = "";
        if (date != null) {
            ch = writeFormat.format(date);
        }

        return ch + "@forbet.net";
    }

    public String GetEmailGmail() {
        String dateStr = String.valueOf(new Date());

        DateFormat readFormat = new SimpleDateFormat(
                "EEE MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH);

        DateFormat writeFormat = new SimpleDateFormat("MM_dd_HH_mm_ss");
        Date date = null;
        try {
            date = readFormat.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        String ch = "";
        if (date != null) {
            ch = writeFormat.format(date);
        }

        return ch + "@gmail.com";
    }

    public String GetEmail(String text) {
        String dateStr = String.valueOf(new Date());

        DateFormat readFormat = new SimpleDateFormat(
                "EEE MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH);

        DateFormat writeFormat = new SimpleDateFormat("MM_dd_HH_mm_ss");
        Date date = null;
        try {
            date = readFormat.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        String ch = "";
        if (date != null) {
            ch = writeFormat.format(date);
        }

        return ch + text + "@insorg-mail.info";
    }

    public String GetPasswod() {
        return GetRandomFull(7);
    }

    // / <summary>
    // / true - случайный, false - повтор
    // / </summary>
    // / <returns></returns>
    public String GetPasswordconfirmation(boolean povtor) {
        String passed;
        if (povtor) {
            passed = GetRandomFull(8);
        } else {
            passed = GetPasswod();
        }
        return passed;
    }

    // / <summary>
    // / true - случайный, false - повтор
    // / </summary>
    // / <returns></returns>
    public synchronized String GetNumberPhone(boolean number) {
        String numberPhone;
        if (number) {
            numberPhone = (GetRandomInt(100, 1000) + "-"
                    + GetRandomInt(100, 1000) + "-" + GetRandomInt(10, 100)
                    + "-" + GetRandomInt(10, 100));
        } else {
            numberPhone = "1111111111";
        }

        return numberPhone;
    }

    // / <summary>
    // / true - случайный, false - повтор
    // / </summary>
    // / <returns></returns>
    public synchronized String GetNumberPhone() {


        return "9" + (GetRandomInt(10, 100)
                + GetRandomInt(100, 1000) + GetRandomInt(10, 100)
                + GetRandomInt(10, 100));
    }

    public String GetAbsolutRandomNumber() {
        SimpleDateFormat format = new SimpleDateFormat("dd MM mm ss");
        String newEmail = null;
        try {
            newEmail = format.parse(String.valueOf(new Date()))
                    + GetRandomInt(1, 9) + GetRandomInt(1, 9);
        } catch (ParseException e) {

            e.printStackTrace();
        }
        return newEmail;
    }

    public String GetRandomSpecSimvol() {
        String[] Spec = new String[] { ")", "#", "$", "%", "^", "&", "*",
                "?", "!", "№", ";", "%", ":", ",", "(", "\\", "/" };
        int time = (int) (Calendar.getInstance().getTime().getSeconds());
        if (time > 9) {
            return Spec[Integer.parseInt(String.valueOf(time).substring(0, 1))
                    + Integer.parseInt(String.valueOf(time).substring(1))
                    + Integer.parseInt(GetRandomInt(0, 4))];
        } else {
            return Spec[time];
        }
    }

    public String GetRandomRusText(int size) {
        String rus = "йцукенгшщзфывапролдячсмить";
        String eng = "qwertyuiopasdfghjklzxcvbnm";
        String strIn = GetRandomText(size, false);
        String strOut = "";
        for (char ch : strIn.toCharArray()) {
            for (int i = 0; i < eng.length(); i++) {
                if (ch == eng.toCharArray()[i]) {
                    strOut += rus.toCharArray()[i];
                }
            }
        }
        return strOut;
    }

    public String AutoReplace(int size) {
        String text = GetRandomText(size, true);
        String text1 = "";
        for (int v = 0; v < text.length(); ++v) {
            StringBuilder str_tr = new StringBuilder(text);
            // механизм транслитерации
            String.valueOf(str_tr).replace("q", "й");
            String.valueOf(str_tr).replace("w", "ц");
            String.valueOf(str_tr).replace("e", "у");
            String.valueOf(str_tr).replace("r", "к");
            String.valueOf(str_tr).replace("t", "е");
            String.valueOf(str_tr).replace("y", "н");
            String.valueOf(str_tr).replace("u", "г");
            String.valueOf(str_tr).replace("i", "ш");
            String.valueOf(str_tr).replace("o", "щ");
            String.valueOf(str_tr).replace("p", "з");
            String.valueOf(str_tr).replace("a", "ф");
            String.valueOf(str_tr).replace("s", "ы");
            String.valueOf(str_tr).replace("d", "в");
            String.valueOf(str_tr).replace("f", "а");
            String.valueOf(str_tr).replace("g", "п");
            String.valueOf(str_tr).replace("h", "р");
            String.valueOf(str_tr).replace("j", "о");
            String.valueOf(str_tr).replace("k", "л");
            String.valueOf(str_tr).replace("l", "д");
            String.valueOf(str_tr).replace("z", "я");
            String.valueOf(str_tr).replace("x", "ч");
            String.valueOf(str_tr).replace("c", "с");
            String.valueOf(str_tr).replace("v", "м");
            String.valueOf(str_tr).replace("b", "и");
            String.valueOf(str_tr).replace("n", "т");
            String.valueOf(str_tr).replace("m", "ь");
            text1 = String.valueOf(str_tr);
        }
        return text1;
    }

    public String Transliteration(int size) {
        String text = GetRandomText(size, true);
        String text1 = "";
        for (int v = 0; v < text.length(); ++v) {

            String str_tr = String.valueOf(new StringBuilder(text));
            // механизм транслитерации
            str_tr.replace("a", "а");
            str_tr.replace("b", "б");
            str_tr.replace("v", "в");
            str_tr.replace("g", "г");
            str_tr.replace("d", "д");
            str_tr.replace("e", "е");
            str_tr.replace("yo", "ё");
            str_tr.replace("zh", "ж");
            str_tr.replace("z", "з");
            str_tr.replace("i", "и");
            str_tr.replace("j", "й");
            str_tr.replace("k", "к");
            str_tr.replace("l", "л");
            str_tr.replace("m", "м");
            str_tr.replace("n", "н");
            str_tr.replace("o", "о");
            str_tr.replace("p", "п");
            str_tr.replace("r", "р");
            str_tr.replace("s", "с");
            str_tr.replace("t", "т");
            str_tr.replace("u", "у");
            str_tr.replace("f", "ф");
            str_tr.replace("h", "х");
            str_tr.replace("c", "ц");
            str_tr.replace("ch", "ч");
            str_tr.replace("sh", "ш");
            str_tr.replace("sch", "щ");
            str_tr.replace("j", "ъ");
            str_tr.replace("i", "ы");
            str_tr.replace("j", "ь");
            str_tr.replace("e", "э");
            str_tr.replace("yu", "ю");
            str_tr.replace("ya", "я");

            str_tr.replace("А", "A");
            str_tr.replace("Б", "B");
            str_tr.replace("В", "V");
            str_tr.replace("Г", "G");
            str_tr.replace("Д", "D");
            str_tr.replace("Е", "E");
            str_tr.replace("Ё", "Yo");
            str_tr.replace("Ж", "Zh");
            str_tr.replace("З", "Z");
            str_tr.replace("И", "I");
            str_tr.replace("Й", "J");
            str_tr.replace("К", "K");
            str_tr.replace("Л", "L");
            str_tr.replace("М", "M");
            str_tr.replace("Н", "N");
            str_tr.replace("О", "O");
            str_tr.replace("П", "P");
            str_tr.replace("Р", "R");
            str_tr.replace("С", "S");
            str_tr.replace("Т", "T");
            str_tr.replace("У", "U");
            str_tr.replace("Ф", "F");
            str_tr.replace("Х", "H");
            str_tr.replace("Ц", "C");
            str_tr.replace("Ч", "CH");
            str_tr.replace("Ш", "SH");
            str_tr.replace("Щ", "SCH");
            str_tr.replace("Ъ", "J");
            str_tr.replace("Ы", "I");
            str_tr.replace("Ь", "J");
            str_tr.replace("Э", "E");
            str_tr.replace("Ю", "YU");
            str_tr.replace("Я", "YA");
            text1 = str_tr;
        }
        return text1;
    }

    /*
     * Метод используется для проверки валидации на пробелы
     */
    public String ValidationSpaceAndSpecSimbol(int size, boolean space) {
        int second = (int) (Calendar.getInstance().getTime().getSeconds());
        String retur = null;
        String simbol = null;
        if (space) {
            simbol = " ";
        } else
            simbol = GetRandomSpecSimvol();

        if (second % 3 == 2) {
            retur = simbol + GetRandomFull(size);
        }
        if (second % 3 == 1) {
            if (size % 2 == 1) {
                retur = GetRandomFull(size / 2) + simbol
                        + GetRandomFull((size / 2) + 1);
            } else
                retur = GetRandomFull(size / 2) + simbol
                        + GetRandomFull(size / 2);

        }
        if (second % 3 == 0) {
            retur = GetRandomFull(size) + simbol;
        }

        return retur;
    }

    protected String randomString(int len) {
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++)
            sb.append(AB.charAt(rnd.nextInt(AB.length())));
        return sb.toString();
    }

    public String  generatiIp(){
        return GetRandomInt(100,255) + "." + GetRandomInt(10, 99)  + "." + GetRandomInt(10, 99)  + "." + GetRandomInt(10, 99);
    }


    public static String generateRandomeStringRu(int length) {
        String alphabet =
                new String("йцукенгшщзфывапролдячсмить"); //9
        int n = alphabet.length(); //10

        String result = new String();
        Random r = new Random(); //11

        for (int i = 0; i < length; i++) //12
            result = result + alphabet.charAt(r.nextInt(n)); //13

        return result;
    }

    public String getSex() {
        Random random = new Random();
        if (random.nextInt(10) > 5) {
            return "male";
        } else
            return "female";
    }

    public String getBirthday() {
        Random random = new Random();
        String year = String.valueOf(random.nextInt(100 - 50 + 1) + 1945);
        int month = random.nextInt(11) + 1;
        int day = random.nextInt(28) + 1;
        String monthS = null;
        String dayS = null;
        if (month < 10) {
            monthS = "0" + month;
        } else {
            monthS = String.valueOf(month);
        }
        if (day < 10) {
            dayS = "0" + day;
        } else {
            dayS = String.valueOf(day);
        }
        return year + "-" + monthS + "-" + dayS;
    }

    public String getIndex() {
        Random random = new Random();
        return String.valueOf(random.nextInt(899999) + 100000);
    }



    public String generateCurrentTime() {
        StringBuilder builder = new StringBuilder();

        String dateStr = String.valueOf(new Date());
        DateFormat readFormat = new SimpleDateFormat(
                "EEE MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH);

        DateFormat writeFormat = new SimpleDateFormat("MM-dd-hh-mm-ss-S");
        Date date = null;
        try {
            date = readFormat.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        String ch = "";
        if (date != null) {
            ch = writeFormat.format(date);
        }
        builder.append(ch);

        return ch;
    }

}
