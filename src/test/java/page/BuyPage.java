package page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import data.DataHelper;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;

public class BuyPage {

    private SelenideElement cardNumber = $(byText("Номер карты")).parent().$(".input__control");
    private SelenideElement month = $(byText("Месяц")).parent().$(".input__control");
    private SelenideElement year = $(byText("Год")).parent().$(".input__control");
    private SelenideElement owner = $(byText("Владелец")).parent().$(".input__control");
    private SelenideElement cvc = $(byText("CVC/CVV")).parent().$(".input__control");
    private SelenideElement continueButton = $(byText("Продолжить"));
    private SelenideElement cardNumberError = $(byText("Номер карты")).parent().$(withText("Неверный формат"));
    private SelenideElement monthError = $(byText("Месяц")).parent().$(withText("Неверный формат"));
    private SelenideElement monthErrorPeriod = $(byText("Месяц")).parent().$(withText("Неверно указан срок действия карты"));

    private SelenideElement yearError = $(byText("Год")).parent().$(withText("Неверный формат"));
    private SelenideElement expiredCardError = $(byText("Истек срок действия карты")).parent().$(".input__sub");
    private SelenideElement ownerError = $(byText("Владелец")).parent().$(withText("Поле обязательно для заполнения"));
    private SelenideElement cvcError = $(byText("CVC/CVV")).parent().$(withText("Неверный формат"));
    private SelenideElement messageDecline = $(withText("Ошибка! Банк отказал в проведении операции."));

    public void validBuy(DataHelper.CardInfo info) {
        cardNumber.setValue(info.getCardNumber());
        month.setValue(info.getMonth());
        year.setValue(info.getYear());
        owner.setValue(info.getOwner());
        cvc.setValue(info.getCvc());
        continueButton.click();
    }

    public void notValidBuy() {
        continueButton.click();
        cardNumberError.shouldBe(visible);
        monthError.shouldBe(visible);
        yearError.shouldBe(visible);
        ownerError.shouldBe(visible);
        cvcError.shouldBe(visible);
    }

    public void cardNumberErrorVisible() {
        cardNumberError.shouldBe(visible);
    }

    public void monthErrorPeriodVisible() {
        monthErrorPeriod.shouldBe(visible);
    }
    public void monthErrorVisible() {
        monthError.shouldBe(visible);
    }

    public void yearErrorVisible() {
        yearError.shouldBe(visible);
    }

    public void expiredCardErrorVisible() {
        expiredCardError.shouldBe(visible);
    }

    public void ownerErrorVisible() {
        ownerError.shouldBe(visible);
    }

    public void cvcErrorVisible() {
        cvcError.shouldBe(visible);
    }

    public void successfullBuy() {
        $(".notification_status_ok").shouldBe(Condition.visible, Duration.ofSeconds(30));
    }

    public void declinedBuy() {
        messageDecline.shouldBe(visible, Duration.ofSeconds(15));
    }
}
