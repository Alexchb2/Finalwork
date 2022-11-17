package test;

import com.codeborne.selenide.logevents.SelenideLogger;
import data.DataHelper;
import data.SQLHelper;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.val;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import page.DashboardPage;

import static com.codeborne.selenide.Selenide.open;
import static data.DataHelper.*;
import static data.SQLHelper.cleanDatabase;

public class BuyTest {

    @AfterAll
    static void teardown() {
        cleanDatabase();
    }

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @BeforeEach
    public void setUp() {
        open("http://localhost:8080");
    }

    @Test
    void shouldBeApprovedCard() {
        val cardInfo = new DataHelper.CardInfo(getApprovedCardNumber(), getValidMonth(), getValidYear(), getOwnerName(), getCVC());
        val dashboardPage = new DashboardPage();
        val buyPage = dashboardPage.Buy();
        buyPage.validBuy(cardInfo);
        buyPage.successfullBuy();
    }

    @Test
    void shouldBeDeclinedCard() {
        val cardInfo = new DataHelper.CardInfo(getDeclinedCardNumber(), getValidMonth(), getValidYear(), getOwnerName(), getCVC());
        val dashboardPage = new DashboardPage();
        val buyPage = dashboardPage.Buy();
        buyPage.validBuy(cardInfo);
        buyPage.successfullBuy();
    }

    @Test
    void PayWithoutQuestionnaire() {
        val cardInfo = new DataHelper.CardInfo(null, null, null, null, null);
        val dashboardPage = new DashboardPage();
        val buyPage = dashboardPage.Buy();
        buyPage.validBuy(cardInfo);
        buyPage.cardNumberErrorVisible();
        buyPage.monthErrorVisible();
        buyPage.yearErrorVisible();
        buyPage.ownerErrorVisible();
        buyPage.cvcErrorVisible();
    }

    @Test
    void PayUnknownCard() {
        val cardInfo = new DataHelper.CardInfo(getUnknownCardNumber(), getValidMonth(), getValidYear(), getOwnerName(), getCVC());
        val dashboardPage = new DashboardPage();
        val buyPage = dashboardPage.Buy();
        buyPage.validBuy(cardInfo);
        buyPage.declinedBuy();
    }

    @Test
    void PayShortCard() {
        val cardInfo = new DataHelper.CardInfo(getShortCardNumber(), getValidMonth(), getValidYear(), getOwnerName(), getCVC());
        val dashboardPage = new DashboardPage();
        val buyPage = dashboardPage.Buy();
        buyPage.validBuy(cardInfo);
        buyPage.cardNumberErrorVisible();
    }

    @Test
    void PayCardWithCyrillic() {
        val cardInfo = new DataHelper.CardInfo(getCardNumberWithCyrillic(), getValidMonth(), getValidYear(), getOwnerName(), getCVC());
        val dashboardPage = new DashboardPage();
        val buyPage = dashboardPage.Buy();
        buyPage.validBuy(cardInfo);
        buyPage.cardNumberErrorVisible();
    }

    @Test
    void PayCardWithLetters() {
        val cardInfo = new DataHelper.CardInfo(getCardNumberWithLetters(), getValidMonth(), getValidYear(), getOwnerName(), getCVC());
        val dashboardPage = new DashboardPage();
        val buyPage = dashboardPage.Buy();
        buyPage.validBuy(cardInfo);
        buyPage.cardNumberErrorVisible();
    }

    @Test
    void PayCardWithSigns() {
        val cardInfo = new DataHelper.CardInfo(getCardNumberWithSigns(), getValidMonth(), getValidYear(), getOwnerName(), getCVC());
        val dashboardPage = new DashboardPage();
        val buyPage = dashboardPage.Buy();
        buyPage.validBuy(cardInfo);
        buyPage.cardNumberErrorVisible();
    }

    @Test
    void PayWithoutCard() {
        val cardInfo = new DataHelper.CardInfo(null, getValidMonth(), getValidYear(), getOwnerName(), getCVC());
        val dashboardPage = new DashboardPage();
        val buyPage = dashboardPage.Buy();
        buyPage.validBuy(cardInfo);
        buyPage.cardNumberErrorVisible();
    }

    @Test
    void PayWithMonthOver12() {
        val cardInfo = new DataHelper.CardInfo(getApprovedCardNumber(), getMonthOver12(), getValidYear(), getOwnerName(), getCVC());
        val dashboardPage = new DashboardPage();
        val buyPage = dashboardPage.Buy();
        buyPage.validBuy(cardInfo);
        buyPage.monthErrorVisible();
    }

    @Test
    void PayMonthWithZeros() {
        val cardInfo = new DataHelper.CardInfo(getApprovedCardNumber(), getMonthWithZeros(), getValidYear(), getOwnerName(), getCVC());
        val dashboardPage = new DashboardPage();
        val buyPage = dashboardPage.Buy();
        buyPage.validBuy(cardInfo);
        buyPage.monthErrorVisible();
    }

    @Test
    void PayMonthWithOneFigure() {
        val cardInfo = new DataHelper.CardInfo(getApprovedCardNumber(), getMonthWithOneFigure(), getValidYear(), getOwnerName(), getCVC());
        val dashboardPage = new DashboardPage();
        val buyPage = dashboardPage.Buy();
        buyPage.validBuy(cardInfo);
        buyPage.monthErrorVisible();
    }

    @Test
    void PayMonthWithCyrillic() {
        val cardInfo = new DataHelper.CardInfo(getMonthWithCyrillic(), getMonthWithLetters(), getValidYear(), getOwnerName(), getCVC());
        val dashboardPage = new DashboardPage();
        val buyPage = dashboardPage.Buy();
        buyPage.validBuy(cardInfo);
        buyPage.monthErrorVisible();
    }

    @Test
    void PayMonthWithLetters() {
        val cardInfo = new DataHelper.CardInfo(getApprovedCardNumber(), getMonthWithLetters(), getValidYear(), getOwnerName(), getCVC());
        val dashboardPage = new DashboardPage();
        val buyPage = dashboardPage.Buy();
        buyPage.validBuy(cardInfo);
        buyPage.monthErrorVisible();
    }

    @Test
    void PayMonthWithSigns() {
        val cardInfo = new DataHelper.CardInfo(getApprovedCardNumber(), getMonthWithSigns(), getValidYear(), getOwnerName(), getCVC());
        val dashboardPage = new DashboardPage();
        val buyPage = dashboardPage.Buy();
        buyPage.validBuy(cardInfo);
        buyPage.monthErrorVisible();
    }

    @Test
    void PayWithoutMonth() {
        val cardInfo = new DataHelper.CardInfo(getApprovedCardNumber(), null, getValidYear(), getOwnerName(), getCVC());
        val dashboardPage = new DashboardPage();
        val buyPage = dashboardPage.Buy();
        buyPage.validBuy(cardInfo);
        buyPage.monthErrorVisible();
    }

    @Test
    void PayWithPastYear() {
        val cardInfo = new DataHelper.CardInfo(getApprovedCardNumber(), getValidMonth(), getPastYear(), getOwnerName(), getCVC());
        val dashboardPage = new DashboardPage();
        val buyPage = dashboardPage.Buy();
        buyPage.validBuy(cardInfo);
        buyPage.expiredCardErrorVisible();
    }

    @Test
    void PayWithYearWithOneFigure() {
        val cardInfo = new DataHelper.CardInfo(getApprovedCardNumber(), getValidMonth(), getYearWithOneFigure(), getOwnerName(), getCVC());
        val dashboardPage = new DashboardPage();
        val buyPage = dashboardPage.Buy();
        buyPage.validBuy(cardInfo);
        buyPage.yearErrorVisible();
    }

    @Test
    void PayWithYearWithCyrillic() {
        val cardInfo = new DataHelper.CardInfo(getApprovedCardNumber(), getValidMonth(), getYearWithCyrillic(), getOwnerName(), getCVC());
        val dashboardPage = new DashboardPage();
        val buyPage = dashboardPage.Buy();
        buyPage.validBuy(cardInfo);
        buyPage.yearErrorVisible();
    }

    @Test
    void PayWithYearWithLetters() {
        val cardInfo = new DataHelper.CardInfo(getApprovedCardNumber(), getValidMonth(), getYearWithLetters(), getOwnerName(), getCVC());
        val dashboardPage = new DashboardPage();
        val buyPage = dashboardPage.Buy();
        buyPage.validBuy(cardInfo);
        buyPage.yearErrorVisible();
    }

    @Test
    void PayWithYearWithSigns() {
        val cardInfo = new DataHelper.CardInfo(getApprovedCardNumber(), getValidMonth(), getYearWithSigns(), getOwnerName(), getCVC());
        val dashboardPage = new DashboardPage();
        val buyPage = dashboardPage.Buy();
        buyPage.validBuy(cardInfo);
        buyPage.yearErrorVisible();
    }

    @Test
    void PayWithoutYear() {
        val cardInfo = new DataHelper.CardInfo(getApprovedCardNumber(), getValidMonth(), null, getOwnerName(), getCVC());
        val dashboardPage = new DashboardPage();
        val buyPage = dashboardPage.Buy();
        buyPage.validBuy(cardInfo);
        buyPage.yearErrorVisible();
    }

    @Test
    void PayWithFirstName() {
        val cardInfo = new DataHelper.CardInfo(getApprovedCardNumber(), getValidMonth(), getValidYear(), getOwnerFirstName(), getCVC());
        val dashboardPage = new DashboardPage();
        val buyPage = dashboardPage.Buy();
        buyPage.validBuy(cardInfo);
        buyPage.ownerErrorVisible();
    }

    @Test
    void PayWithNameCyrillic() {
        val cardInfo = new DataHelper.CardInfo(getApprovedCardNumber(), getValidMonth(), getValidYear(), getOwnerNameCyrillic(), getCVC());
        val dashboardPage = new DashboardPage();
        val buyPage = dashboardPage.Buy();
        buyPage.validBuy(cardInfo);
        buyPage.ownerErrorVisible();
    }

    @Test
    void PayWithShortName() {
        val cardInfo = new DataHelper.CardInfo(getApprovedCardNumber(), getValidMonth(), getValidYear(), getOwnerNameShort(), getCVC());
        val dashboardPage = new DashboardPage();
        val buyPage = dashboardPage.Buy();
        buyPage.validBuy(cardInfo);
        buyPage.ownerErrorVisible();
    }

    @Test
    void PayWithNameWithFigure() {
        val cardInfo = new DataHelper.CardInfo(getApprovedCardNumber(), getValidMonth(), getValidYear(), getOwnerNameWithFigure(), getCVC());
        val dashboardPage = new DashboardPage();
        val buyPage = dashboardPage.Buy();
        buyPage.validBuy(cardInfo);
        ;
        buyPage.ownerErrorVisible();
    }

    @Test
    void PayWithNameWithSigns() {
        val cardInfo = new DataHelper.CardInfo(getApprovedCardNumber(), getValidMonth(), getValidYear(), getOwnerNameWithSigns(), getCVC());
        val dashboardPage = new DashboardPage();
        val buyPage = dashboardPage.Buy();
        buyPage.validBuy(cardInfo);
        buyPage.ownerErrorVisible();
    }

    @Test
    void PayWithLongName() {
        val cardInfo = new DataHelper.CardInfo(getApprovedCardNumber(), getValidMonth(), getValidYear(), getOwnerNameLong(), getCVC());
        val dashboardPage = new DashboardPage();
        val buyPage = dashboardPage.Buy();
        buyPage.validBuy(cardInfo);
        buyPage.ownerErrorVisible();
    }

    @Test
    void PayWithDoubleName() {
        val cardInfo = new DataHelper.CardInfo(getApprovedCardNumber(), getValidMonth(), getValidYear(), getOwnerNameWithDoubleName(), getCVC());
        val dashboardPage = new DashboardPage();
        val buyPage = dashboardPage.Buy();
        buyPage.validBuy(cardInfo);
        buyPage.successfullBuy();
    }

    @Test
    void PayWithoutName() {
        val cardInfo = new DataHelper.CardInfo(getApprovedCardNumber(), getValidMonth(), getValidYear(), null, getCVC());
        val dashboardPage = new DashboardPage();
        val buyPage = dashboardPage.Buy();
        buyPage.validBuy(cardInfo);
        buyPage.ownerErrorVisible();
    }

    @Test
    void PayWithCVCShort() {
        val cardInfo = new DataHelper.CardInfo(getApprovedCardNumber(), getValidMonth(), getValidYear(), getOwnerName(), getCVCShort());
        val dashboardPage = new DashboardPage();
        val buyPage = dashboardPage.Buy();
        buyPage.validBuy(cardInfo);
        buyPage.cvcErrorVisible();
    }

    @Test
    void PayCVCWithCyrillic() {
        val cardInfo = new DataHelper.CardInfo(getApprovedCardNumber(), getValidMonth(), getValidYear(), getOwnerName(), getCVCWithCyrillic());
        val dashboardPage = new DashboardPage();
        val buyPage = dashboardPage.Buy();
        buyPage.validBuy(cardInfo);
        buyPage.cvcErrorVisible();
    }

    @Test
    void PayCVCWithLetters() {
        val cardInfo = new DataHelper.CardInfo(getApprovedCardNumber(), getValidMonth(), getValidYear(), getOwnerName(), getCVCWithLetters());
        val dashboardPage = new DashboardPage();
        val buyPage = dashboardPage.Buy();
        buyPage.validBuy(cardInfo);
        buyPage.cvcErrorVisible();
    }

    @Test
    void PayWithCVCWithSigns() {
        val cardInfo = new DataHelper.CardInfo(getApprovedCardNumber(), getValidMonth(), getValidYear(), getOwnerName(), getCVCWithSigns());
        val dashboardPage = new DashboardPage();
        val buyPage = dashboardPage.Buy();
        buyPage.validBuy(cardInfo);
        ;
        buyPage.cvcErrorVisible();
    }

    @Test
    void PayWithoutCVC() {
        val cardInfo = new DataHelper.CardInfo(getApprovedCardNumber(), getValidMonth(), getValidYear(), getOwnerName(), null);
        val dashboardPage = new DashboardPage();
        val buyPage = dashboardPage.Buy();
        buyPage.validBuy(cardInfo);
        buyPage.cvcErrorVisible();
    }
}
