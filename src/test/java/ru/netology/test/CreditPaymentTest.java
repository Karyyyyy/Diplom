package ru.netology.test;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.val;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.data.DataHelperSQL;
import ru.netology.page.CreditPage;
import ru.netology.page.StartPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.netology.data.DataHelperSQL.cleanDataBase;

public class CreditPaymentTest {
    CreditPage creditPage = new CreditPage();
    StartPage startPage = new StartPage();

    @BeforeEach
    void CleanDataBaseAndOpenWeb() {
        cleanDataBase();
        startPage = open("http://localhost:8080", StartPage.class);
        startPage.buyPaymentByCard();
    }

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @Test
    void shouldApproveFirstCard() {
        val cardNumber = DataHelper.getFirstCardNumber();
        val month = DataHelper.getValidMonth();
        val year = DataHelper.getValidYear();
        val owner = DataHelper.getValidOwner();
        val cvc = DataHelper.getValidCvc();
        creditPage.fillOutLine(cardNumber, month, year, owner, cvc);
        creditPage.messageAboutSuccessfullPayment();
        val expected = DataHelper.getStatusFirstCard();
        val actual = DataHelperSQL.getPurchaseOnCreditCard();
        assertEquals(expected, actual);
    }


    @Test
    void shouldApproveOwnerNameWithTheLetter() {
        val cardNumber = DataHelper.getFirstCardNumber();
        val month = DataHelper.getValidMonth();
        val year = DataHelper.getValidYear();
        val owner = DataHelper.getLetterЁ();
        val cvc = DataHelper.getValidCvc();
        creditPage.fillOutLine(cardNumber, month, year, owner, cvc);
        creditPage.messageAboutSuccessfullPayment();
        val expected = DataHelper.getStatusFirstCard();
        val actual = DataHelperSQL.getPurchaseOnCreditCard();
        assertEquals(expected, actual);
    }


    @Test
    void shouldApproveDoubleNameOfTheOwner() {
        val cardNumber = DataHelper.getFirstCardNumber();
        val month = DataHelper.getValidMonth();
        val year = DataHelper.getValidYear();
        val owner = DataHelper.getDoubleNameOfTheOwner();
        val cvc = DataHelper.getValidCvc();
        creditPage.fillOutLine(cardNumber, month, year, owner, cvc);
        creditPage.messageAboutSuccessfullPayment();
        val expected = DataHelper.getStatusFirstCard();
        val actual = DataHelperSQL.getPurchaseOnCreditCard();
        assertEquals(expected, actual);
    }

    @Test
    void shouldApproveSecondCard() {
        val cardNumber = DataHelper.getSecondCardNumber();
        val month = DataHelper.getValidMonth();
        val year = DataHelper.getValidYear();
        val owner = DataHelper.getValidOwner();
        val cvc = DataHelper.getValidCvc();
        creditPage.fillOutLine(cardNumber, month, year, owner, cvc);
        creditPage.messageAboutUnsuccessfullPaymentRefused();
        val expected = DataHelper.getStatusSecondCard();
        val actual = DataHelperSQL.getPurchaseOnCreditCard();
        assertEquals(expected, actual);
    }

    @Test
    void shouldLessThan16DigitsInTheCard() {
        val cardNumber = DataHelper.getLessThan16DigitsInTheCard();
        val month = DataHelper.getValidMonth();
        val year = DataHelper.getValidYear();
        val owner = DataHelper.getValidOwner();
        val cvc = DataHelper.getValidCvc();
        creditPage.fillOutLine(cardNumber, month, year, owner, cvc);
        creditPage.messageAboutIncorrectDataFormat();
    }

    @Test
    void should16ZerosInTheCard() {
        val cardNumber = DataHelper.get16ZerosInTheCard();
        val month = DataHelper.getValidMonth(); //
        val year = DataHelper.getValidYear();
        val owner = DataHelper.getValidOwner();
        val cvc = DataHelper.getValidCvc();
        creditPage.fillOutLine(cardNumber, month, year, owner, cvc);
        creditPage.messageAboutUnsuccessfullPaymentRefused();
    }

    @Test
    void shouldLettersSymbolsTextInTheCard() {
        val cardNumber = DataHelper.getLettersSymbolsText();
        val month = DataHelper.getValidMonth();
        val year = DataHelper.getValidYear();
        val owner = DataHelper.getValidOwner();
        val cvc = DataHelper.getValidCvc();
        creditPage.fillOutLine(cardNumber, month, year, owner, cvc);
        creditPage.messageAboutIncorrectDataFormat();
    }


    @Test
    void shouldEmptyFieldInTheCard() {
        val cardNumber = DataHelper.getEmptyFieldInTheCard();
        val month = DataHelper.getValidMonth();
        val year = DataHelper.getValidYear();
        val owner = DataHelper.getValidOwner();
        val cvc = DataHelper.getValidCvc();
        creditPage.fillOutLine(cardNumber, month, year, owner, cvc);
        creditPage.messageAboutIncorrectDataFormat();
    }


    @Test
    void shouldLettersSymbolsTextInTheMonth() {
        val cardNumber = DataHelper.getFirstCardNumber();
        val month = DataHelper.getLettersSymbolsTextInTheMonth();
        val year = DataHelper.getValidYear();
        val owner = DataHelper.getValidOwner();
        val cvc = DataHelper.getValidCvc();
        creditPage.fillOutLine(cardNumber, month, year, owner, cvc);
        creditPage.messageAboutIncorrectDataFormat();
    }


    @Test
    void shouldMonthNumberMore12() {
        val cardNumber = DataHelper.getFirstCardNumber();
        val month = DataHelper.getMonthNumberMore12();
        val year = DataHelper.getValidYear();
        val owner = DataHelper.getValidOwner();
        val cvc = DataHelper.getValidCvc();
        creditPage.fillOutLine(cardNumber, month, year, owner, cvc);
        creditPage.messageAboutIncorrectCardExpirationDate();
    }

    //2.7_Негативный тест с пустым полем месяца
    @Test
    void shouldMonthFieldEmpty() { //пустой месяц
        val cardNumber = DataHelper.getFirstCardNumber();
        val month = DataHelper.getMonthFieldEmpty();
        val year = DataHelper.getValidYear();
        val owner = DataHelper.getValidOwner();
        val cvc = DataHelper.getValidCvc();
        creditPage.fillOutLine(cardNumber, month, year, owner, cvc);
        creditPage.messageAboutIncorrectDataFormat();
    }

    @Test
    void shouldYearFieldPrevious() {
        val cardNumber = DataHelper.getFirstCardNumber();
        val month = DataHelper.getValidMonth();
        val year = DataHelper.getYearFieldPrevious();
        val owner = DataHelper.getValidOwner();
        val cvc = DataHelper.getValidCvc();
        creditPage.fillOutLine(cardNumber, month, year, owner, cvc);
        creditPage.messageAboutCardExpiration();
    }

    @Test
    void shouldYearMoreThan6YearsOfTheCurrentYear() {
        val cardNumber = DataHelper.getFirstCardNumber();
        val month = DataHelper.getValidMonth();
        val year = DataHelper.getMoreThan6YearsOfTheCurrentYear();
        val owner = DataHelper.getValidOwner();
        val cvc = DataHelper.getValidCvc();
        creditPage.fillOutLine(cardNumber, month, year, owner, cvc);
        creditPage.messageAboutIncorrectCardExpirationDate();
    }


    @Test
    void shouldYearZero() {
        val cardNumber = DataHelper.getFirstCardNumber();
        val month = DataHelper.getValidMonth();
        val year = DataHelper.getYearZero();
        val owner = DataHelper.getValidOwner();
        val cvc = DataHelper.getValidCvc();
        creditPage.fillOutLine(cardNumber, month, year, owner, cvc);
        creditPage.messageAboutCardExpiration();
    }


    @Test
    void shouldLettersSymbolsTextInTheYear() {
        val cardNumber = DataHelper.getFirstCardNumber();
        val month = DataHelper.getValidMonth();
        val year = DataHelper.getLettersSymbolsTextInTheYear();
        val owner = DataHelper.getValidOwner();
        val cvc = DataHelper.getValidCvc();
        creditPage.fillOutLine(cardNumber, month, year, owner, cvc);
        creditPage.messageAboutIncorrectDataFormat();
    }


    @Test
    void shouldYearFieldEmpty() {
        val cardNumber = DataHelper.getFirstCardNumber();
        val month = DataHelper.getValidMonth();
        val year = DataHelper.getYearFieldEmpty();
        val owner = DataHelper.getValidOwner();
        val cvc = DataHelper.getValidCvc();
        creditPage.fillOutLine(cardNumber, month, year, owner, cvc);
        creditPage.messageAboutIncorrectDataFormat();
    }


    @Test
    void shouldOnlyNameOwner() {
        val cardNumber = DataHelper.getFirstCardNumber();
        val month = DataHelper.getValidMonth();
        val year = DataHelper.getValidYear();
        val owner = DataHelper.getOnNameOwnertr();
        val cvc = DataHelper.getValidCvc();
        creditPage.fillOutLine(cardNumber, month, year, owner, cvc);
        creditPage.messageAboutSuccessfullPayment();
    }


    @Test
    void shouldNameNndPatronymicWithSmallLetterInTheOwner() {
        val cardNumber = DataHelper.getFirstCardNumber();
        val month = DataHelper.getValidMonth();
        val year = DataHelper.getValidYear();
        val owner = DataHelper.getNameNndPatronymicWithSmallLetterInTheOwner();
        val cvc = DataHelper.getValidCvc();
        creditPage.fillOutLine(cardNumber, month, year, owner, cvc);
        creditPage.messageAboutSuccessfullPayment();
    }

    @Test
    void shouldMoreThan30CharactersInTheOwner() {
        val cardNumber = DataHelper.getFirstCardNumber();
        val month = DataHelper.getValidMonth();
        val year = DataHelper.getValidYear();
        val owner = DataHelper.getMoreThan30CharactersInTheOwner();
        val cvc = DataHelper.getValidCvc();
        creditPage.fillOutLine(cardNumber, month, year, owner, cvc);
        creditPage.messageAboutSuccessfullPayment();
    }


    @Test
    void shouldLettersSymbolsTextInTheOwner() {
        val cardNumber = DataHelper.getFirstCardNumber();
        val month = DataHelper.getValidMonth();
        val year = DataHelper.getValidYear();
        val owner = DataHelper.getLettersSymbolsTextInTheOwner();
        val cvc = DataHelper.getValidCvc();
        creditPage.fillOutLine(cardNumber, month, year, owner, cvc);
        creditPage.messageAboutSuccessfullPayment();
    }


    @Test
    void shouldOwnerFieldEmpty() {
        val cardNumber = DataHelper.getFirstCardNumber();
        val month = DataHelper.getValidMonth();
        val year = DataHelper.getValidYear();
        val owner = DataHelper.getOwnerFieldEmpty();
        val cvc = DataHelper.getValidCvc();
        creditPage.fillOutLine(cardNumber, month, year, owner, cvc);
        creditPage.messageAboutTheMandatoryFillingInOfTheField();
    }


    @Test
    void shouldCvcZero() {
        val cardNumber = DataHelper.getFirstCardNumber();
        val month = DataHelper.getValidMonth();
        val year = DataHelper.getValidYear();
        val owner = DataHelper.getValidOwner();
        val cvc = DataHelper.getCvcZero();
        creditPage.fillOutLine(cardNumber, month, year, owner, cvc);
        creditPage.messageAboutSuccessfullPayment();
    }

    @Test
    void shouldLettersSymbolsTextInTheCvc() {
        val cardNumber = DataHelper.getFirstCardNumber();
        val month = DataHelper.getValidMonth();
        val year = DataHelper.getValidYear();
        val owner = DataHelper.getValidOwner();
        val cvc = DataHelper.getLettersSymbolsTextInTheCvc();
        creditPage.fillOutLine(cardNumber, month, year, owner, cvc);
        creditPage.messageAboutIncorrectDataFormat();
    }

    @Test
    void shouldEmptyFieldInTheCvc() {
        val cardNumber = DataHelper.getFirstCardNumber();
        val month = DataHelper.getValidMonth();
        val year = DataHelper.getValidYear();
        val owner = DataHelper.getValidOwner();
        val cvc = DataHelper.getCvcFieldEmpty();
        creditPage.fillOutLine(cardNumber, month, year, owner, cvc);
        creditPage.messageAboutIncorrectDataFormat();
    }
}