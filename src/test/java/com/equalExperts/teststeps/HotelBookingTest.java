package com.equalExperts.teststeps;


import com.equalExperts.pageobjects.HotelBookingPage;
import com.equalExperts.webutility.BaseDriverPage;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.support.PageFactory;

public class HotelBookingTest {

    HotelBookingPage hotelBookingPage = PageFactory.initElements(BaseDriverPage.getInstance().sharedDriver(), HotelBookingPage.class);
    String idOfTheVariable;
    @Given("^I Navigate to Hotel Booking Page$")
    public void iNavigateToHotelBookingPage() throws Throwable {
        hotelBookingPage.navigateToHotelBookingPage();
    }

    @Then("^I verify the title of the Page as \"([^\"]*)\"$")
    public void iVerifyTheTitleOfThePageAs(final String expectedTitle) throws Throwable {
        Assert.assertEquals("***Title Does not match***", expectedTitle, hotelBookingPage.getTitleOfPage());
    }

    @And("^I enter the FirstName as \"([^\"]*)\" LastName as \"([^\"]*)\" Price as \"([^\"]*)\"$")
    public void iEnterTheFirstNameAsLastNameAsPriceAs(final String actualFirstName, final String actualLastName, final String actualPrice) throws Throwable {
        hotelBookingPage.enterFirstName(actualFirstName);
        hotelBookingPage.enterLastName(actualLastName);
        hotelBookingPage.enterPrice(actualPrice);
    }

    @Then("^I Select the Deposit to be \"([^\"]*)\"$")
    public void iSelectTheDepositToBe(final String depositFlag) throws Throwable {
            hotelBookingPage.selectDepositToBe(depositFlag);
    }

    @And("^I Select the Check-in Date as \"([^\"]*)\"$")
    public void iSelectTheCheckInDateAs(final String checkInDate) throws Throwable {
        hotelBookingPage.selectCheckInDate(checkInDate);
    }

    @And("^I Select the Check-out Date as \"([^\"]*)\"$")
    public void iSelectTheCheckOutDateAs(final String checkOutDate) throws Throwable {
        hotelBookingPage.selectCheckOutDateAs(checkOutDate);
    }

    @And("^I click on Save button to Save the Booking$")
    public void iClickOnSaveButtonToSaveTheBooking() throws Throwable {
        hotelBookingPage.saveBooking();
    }

    @Then("^I find the Saved Booking with FirstName as \"([^\"]*)\"$")
    public void iFindTheSavedBookingWithFirstNameAs(final String firstName) throws Throwable {
        idOfTheVariable = hotelBookingPage.getTheIdOfTheElementFound(firstName);
        System.out.println(idOfTheVariable+"***Id Of the Variable***");
    }


    @And("^I Delete the Booking using the booking id saved above$")
    public void iDeleteTheBookingUsingTheBookingIdSavedAbove() throws Throwable {
        hotelBookingPage.clickOnDeleteButtonUsingFirstName(idOfTheVariable);
    }
}
