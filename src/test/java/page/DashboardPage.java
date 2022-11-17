package page;

import com.codeborne.selenide.SelenideElement;


import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class DashboardPage {
    private SelenideElement buy = $(byText("Купить"));
    private SelenideElement buyCredit = $(byText("Купить в кредит"));

    public BuyPage Buy() {
        buy.click();
        return new BuyPage();
    }

    public BuyCreditPage BuyCredit(){
        buyCredit.click();
        return new BuyCreditPage();
    }
}
