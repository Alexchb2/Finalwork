package data;

import com.github.javafaker.Faker;
import lombok.Value;

import java.time.LocalDate;
import java.time.Year;
import java.util.Locale;

public class DataHelper {
    private static Faker faker = new Faker(new Locale("en"));


    private DataHelper(){
    }

    @Value
    public static class CardInfo {
        private String cardNumber;
        private String month;
        private String year;
        private String owner;
        private String cvc;
    }


    public static String getApprovedCardNumber() {
        return ("1111 2222 3333 4444");
    }

    public static String getDeclinedCardNumber() {
        return ("5555 6666 7777 8888");
    }

    public static String getUnknownCardNumber() {
        return ("5555 6666 1234 1234");
    }

    public static String getShortCardNumber() {
        return ("5555 6666 1234");
    }

    public static String getCardNumberWithSigns() {
        return ("5555 6666 7777 ****");
    }

    public static String getCardNumberWithLetters() {
        return ("5555 6666 7777 rrrr");
    }

    public static String getCardNumberWithCyrillic() {
        return ("5555 6666 7777 ыыыы");
    }

    public static String getValidMonth() {
        LocalDate localDate = LocalDate.now();
        return String.format("%02d", localDate.getMonthValue());
    }

    public static String getMonthOver12() {
        return ("22");
    }

    public static String getMonthWithCyrillic() {
        return ("жж");
    }

    public static String getMonthWithLetters() {
        return ("ee");
    }

    public static String getMonthWithSigns() {
        return ("**");
    }

    public static String getMonthWithOneFigure() {
        return ("2");
    }

    public static String getMonthWithZeros() {
        return ("00");
    }


    public static String getValidYear() {
        return String.format("%ty", Year.now());
    }

    public static String getPastYear() {
        LocalDate localDate = LocalDate.now();
        return String.format("y", localDate.minusYears(3));
    }

    public static String getYearWithOneFigure() {
        return ("4");
    }

    public static String getYearWithLetters() {
        return ("yy");
    }

    public static String getYearWithCyrillic() {
        return ("жж");
    }
    public static String getYearWithSigns() {
        return ("**");
    }


    //    Заполнение поля Владелец
    public static String getOwnerName() {
        return faker.name().fullName();
    }

    public static String getOwnerFirstName() {
        return faker.name().firstName();
    }

    public static String getOwnerNameCyrillic() {
        Faker faker = new Faker(new Locale("ru"));
        return faker.name().fullName();
    }

    public static String getOwnerNameWithFigure() {
        return "1234 Sveta";
    }

    public static String getOwnerNameShort() {
        return "A";
    }

    public static String getOwnerNameWithSigns() {
        return "*** ****";
    }

    public static String getOwnerNameLong() {
        return "Ssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss";
    }


    public static String getOwnerNameWithDoubleName() {
        return "Davin-sport Sveta";
    }

    //    Заполнение поля CVC
    public static String getCVC() {
        return "999";
    }

    public static String getCVCShort() {
        return "9";
    }

    public static String getCVCWithLetters() {
        return "fff";
    }

    public static String getCVCWithCyrillic() {
        return "жжж";
    }
    public static String getCVCWithSigns() {
        return "***";
    }

}
