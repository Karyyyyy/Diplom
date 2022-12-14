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

    public static String getLetterEWithDots() {
        return "Иванов Фёдор";
    }

    public static String getDoubleNameOfTheOwner() {
        return "Анна-Мария";
    } // двойное имя

    public static String getLessThan16DigitsInTheCard() {
        return "1234 5678 9123 456";
    }

    public static String get16ZerosInTheCard() {
        return "0000 0000 0000 0000";
    }

    public static String getLettersSymbolsText() {
        return "трпd sf/* @&%,";
    }

    public static String getEmptyFieldInTheCard() {
        return "";
    }

    public static String getLettersSymbolsTextInTheMonth() {
        return "@&";
    }

    public static String getMonthNumberMore12() {
        return "20";
    }

    public static String getMonthFieldEmpty() {
        return "";
    }

    public static String getYearFieldPrevious() {
        return "21";
    }

    public static String getMoreThan6YearsOfTheCurrentYear() {
        return "28";
    }

    public static String getYearZero() {
        return "00";
    }

    public static String getLettersSymbolsTextInTheYear() {
        return "@&";
    }

    public static String getYearFieldEmpty() {
        return "";
    }

    public static String getOnNameOwnertr() {
        return "Ivanov";
    }

    public static String getNameNndPatronymicWithSmallLetterInTheOwner() {
        return "вася васильевич";
    }

    public static String getMoreThan30CharactersInTheOwner() {
        return "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
    }

    public static String getLettersSymbolsTextInTheOwner() {
        return "@&";
    }

    public static String getOwnerFieldEmpty() {
        return "";
    }

    public static String getCvcZero() {
        return "00";
    }

    public static String getLettersSymbolsTextInTheCvc() {
        return "@&";
    }

    public static String getCvcFieldEmpty() {
        return "";
    }
}


