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
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SQLTest {
    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide()); }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @BeforeEach
    public void openSource() {
        open("http://localhost:8080");
        SQLHelper.cleanTable();
    }

    @Test
    void shouldBeApprovedWithApprovedCard() {
        val cardInfo = new DataHelper.CardInfo(getApprovedCardNumber(), getValidMonth(), getValidYear(), getOwnerName(), getCVC());
        val dashboardPage = new DashboardPage();
        val buyPage = dashboardPage.Buy();
        buyPage.validBuy(cardInfo);
        buyPage.successfullBuy();
        assertEquals("APPROVED", SQLHelper.getPaymentStatus());
    }

    @Test
    void shouldBeDeclinedWithDeclinedCard() {
        val cardInfo = new DataHelper.CardInfo(getDeclinedCardNumber(), getValidMonth(), getValidYear(), getOwnerName(), getCVC());
        val dashboardPage = new DashboardPage();
        val buyPage = dashboardPage.Buy();
        buyPage.validBuy(cardInfo);
        buyPage.cardNumberErrorVisible();
        assertEquals("DECLINED", SQLHelper.getPaymentStatus());
    }

}
