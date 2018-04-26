package com.equalExperts.pageobjects;

import com.equalExperts.webutility.BaseDriverPage;
import com.equalExperts.webutility.ConstantUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class HotelBookingPage {

    //private static int MAXWAITSECS = 30;
    static WebDriverWait wait = new WebDriverWait(BaseDriverPage.getInstance().sharedDriver(), 30);

    @FindBy(css = "#firstname")
    private static WebElement firstNameTextBox;

    @FindBy(css = "#lastname")
    private static WebElement lastNameTextBox;

    @FindBy(css = "#totalprice")
    private static WebElement totalPriceTextBox;

    @FindBy(css = "#depositpaid")
    private static WebElement depositPaidDropDown;

    @FindBy(css = "#checkin")
    private static WebElement checkInDatePicker;

    @FindBy(css = "#checkout")
    private static WebElement checkOutDatePicker;

    @FindBy(css = "input[value=\" Save \"]")
    private static WebElement saveButton;

    @FindBy(css = ".row")
    private List<WebElement> activeTabListOfClaimId;

    private static final String CURRENT_BOOKINGS = "#bookings>.row";


    public void navigateToHotelBookingPage() throws InterruptedException {
        BaseDriverPage.getInstance().sharedDriver().get(ConstantUtils.HOTEL_BOOKING_PAGE_URL);
        for(int i=0;i<=5;i++){
            Thread.sleep(1000);
        }
    }
    public String getTitleOfPage() throws Exception{
        return BaseDriverPage.getInstance().sharedDriver().getTitle();
    }

    public void enterFirstName(final String actualFirstName) {
        wait.until(ExpectedConditions.visibilityOf(firstNameTextBox));
        firstNameTextBox.sendKeys(actualFirstName);
    }

    public void enterLastName(final String actualLastName) {
        wait.until(ExpectedConditions.visibilityOf(lastNameTextBox));
        lastNameTextBox.sendKeys(actualLastName);
    }

    public void enterPrice(final String actualPrice) {
        wait.until(ExpectedConditions.visibilityOf(totalPriceTextBox));
        totalPriceTextBox.sendKeys(actualPrice);
    }

    public void selectDepositToBe(String depositFlag) {
        Select select = new Select(depositPaidDropDown);
        select.selectByVisibleText(depositFlag);
    }

    public void selectCheckInDate(String checkInDate) {
        checkInDatePicker.sendKeys(checkInDate);
    }

    public void selectCheckOutDateAs(String checkOutDate) {
        checkOutDatePicker.sendKeys(checkOutDate);
        checkOutDatePicker.sendKeys(Keys.TAB);
    }

    public void saveBooking() {
        int numberBeforSaving = verifyCurrentBookings();
        wait.until(ExpectedConditions.elementToBeClickable(saveButton));
        saveButton.click();
        forElementAdded(CURRENT_BOOKINGS,numberBeforSaving);
    }
    public int verifyCurrentBookings(){
        return BaseDriverPage.getInstance().sharedDriver().findElements(By.cssSelector(CURRENT_BOOKINGS)).size();
    }
    public void forElementAdded(final String cssLocator, final int originalCount) {
        wait.until(new ExpectedCondition<Boolean>() {
            @Nullable
            public Boolean apply(@Nullable WebDriver driver) {
                int actualSize = BaseDriverPage.getInstance().sharedDriver().findElements(By.cssSelector(cssLocator)).size();
                System.out.println(actualSize+"***Actual Size***");
                return actualSize == originalCount + 1;
            }
        });
    }

    public List<String> checkIfFirstNameIsInTheBookingTable() {
        List<String> claimIdText= new ArrayList<>();
        for(WebElement webElement:activeTabListOfClaimId){
            String webElementText = webElement.getText();
            claimIdText.add(webElementText);
        }
        return claimIdText;
    }

    public String getTheIdOfTheElementFound(String firstName) {
        List<String> claimIdText= new ArrayList<>();
        String webElementText;
        String webElementAttribute = null;
        for(WebElement webElement:activeTabListOfClaimId){
             webElementText = webElement.getText();
            claimIdText.add(webElementText);
                if (webElementText.contains(firstName)) {
                    webElementAttribute = webElement.getAttribute("id");
                    //System.out.println(webElementAttribute+"***WebElement Attribute***");
                }

        }
        return webElementAttribute;
        }

    public void clickOnDeleteButtonUsingFirstName(String idOfTheVariable){
        String actualDeleteElementToClick= "input[onclick" +
                "*=" +
                "'deleteBooking" +
                "(" +
                idOfTheVariable +
                ")'" +
                "]";
        System.out.println(actualDeleteElementToClick+"***The Formed Xpath Of The Delete Element***");
        WebElement elementToBeClicked = BaseDriverPage.getInstance().sharedDriver().findElement(By.cssSelector(actualDeleteElementToClick));
        wait.until(ExpectedConditions.elementToBeClickable(elementToBeClicked));
        elementToBeClicked.click();
    }
}
