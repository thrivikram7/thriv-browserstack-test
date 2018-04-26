@VerifyHotelBooking @ci
Feature: Verify the Hotel Booking page

  @creationOfBooking
  Scenario: Verify Creation of a booking

    Given I Navigate to Hotel Booking Page
    Then I verify the title of the Page as "Hotel booking form"
    And I enter the FirstName as "Thriv" LastName as "TestLastName" Price as "459.09"
    Then I Select the Deposit to be "false"
    And I Select the Check-in Date as "2018-03-30"
    And I Select the Check-out Date as "2018-04-05"
    And I click on Save button to Save the Booking
    Then I find the Saved Booking with FirstName as "Thriv"

  @deletionOfBooking
  Scenario: Verifyt Deletion of a booking

      Given I Navigate to Hotel Booking Page
      Then I verify the title of the Page as "Hotel booking form"
      Then I find the Saved Booking with FirstName as "Thriv"
      And I Delete the Booking using the booking id saved above
