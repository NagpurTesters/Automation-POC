package POC.Yatra.stepdefination;

import POC.Yatra.pages.OnlineBookingPage;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class YatraStepDefination {
	OnlineBookingPage page=new OnlineBookingPage();
	
	@Given("^open yatra application$")
	public void Open_yatra_application() {
	    page.Open_yatra_application();
	}

	@When("^click on flight icon$")
	public void click_on_flight_icon() {
	    page.click_on_flight_icon();
	}

	@When("^user enter Source name$")
	public void user_enter_Source_name() {
	    page.user_enter_Source_name();
	}

	@When("^user enter Destination name$")
	public void user_enter_Destination_name() {
		page.user_enter_Destination_name();
	}

	@When("^user enter Depart date$")
	public void user_enter_Depart_date() {
		page.user_enter_Depart_date();
	}
	
	@When("^click on Search Button$")
	public void click_Search_Button() {
		page.click_Search_Button();
	}
	
	@Then("^verify count search result$")
	public void verify_count_searchresult() {
		page.verify_count_searchresult();
	}
	
	
	
	
	

}
