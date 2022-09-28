package ru.netology.data;

import com.github.javafaker.Faker;

import java.util.Locale;


public class DataHelper {
   private static Faker faker = new Faker(new Locale("ru"));

    private DataHelper() {
    }

    public static String getFirstCardNumber() {
        return "4444 4444 4444 4441";
    }

    public static String getSecondCardNumber() {
        return "4444 4444 4444 4442";
    }

    public static String getStatusFirstCard() {
        return "APPROVED";
    }

    public static String getStatusSecondCard() {
        return "DECLINED";
    }

    public static String getValidMonth() {
        return "02";
    }
    public static String getValidYear() {
        return "25";
    }

    public static String getValidOwner() {
        return faker.name().fullName();
    }

    public static String getValidCvc() {
        return "111";
    }
}
