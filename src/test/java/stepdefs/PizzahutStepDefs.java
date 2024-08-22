package stepdefs;

import java.time.Duration;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class PizzahutStepDefs {

	WebDriver driver = Hooks.driver;

	Map<String, String> personalDetails;

	@Given("I have launched the application")
	public void i_have_launched_the_application() {
		// Write code here that turns the phrase above into concrete actions

		driver.get("https://www.pizzahut.co.in/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
	}

	@When("I enter the location as {string}")
	public void i_enter_the_location_as(String location) throws InterruptedException {
		// Write code here that turns the phrase above into concrete actions 459
		Thread.sleep(500);
		driver.findElement(By.cssSelector("input[placeholder*='Enter your location']")).sendKeys(location);
	}

	@And("I select the very first suggestion from the list")
	public void i_select_the_very_first_suggestion_from_the_list() {
		// Write code here that turns the phrase above into concrete actions

		driver.findElement(By.xpath("//div[contains(text(),'Pune Railway Station')]")).click();

	}

	@Then("I should land on the Deals page")
	public void i_should_land_on_the_Deals_page() throws InterruptedException {
		// Write code here that turns the phrase above into concrete actions

		Thread.sleep(5000);
		String expTitle = "Online Pizza Order, Pizza Deals, Pizza Hut Online Orders | Pizza Hut India";
		String actTitle = driver.getTitle();

		Assert.assertEquals(expTitle, actTitle);
	}

	@And("I select the tab as {string}")
	public void i_select_the_tab_as(String tabName) {
		// Write code here that turns the phrase above into concrete actions

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.findElement(By.xpath("//a[@data-synth='link--pizzas--side']//span[text()='" + tabName + "']")).click();
	}

	@And("I add {string} to the basket")
	public void i_add_to_the_basket(String addItem) {
		// Write code here that turns the phrase above into concrete actions WebElement

		driver.findElement(By.xpath("//div[text()='" + addItem + "']/ancestor::a//span[text()='Add'][1]")).click();
	}

	@And("I note down the price displayed on the screen")
	public void i_note_down_the_price_displayed_on_the_screen() {
		// Write code here that turns the phrase above into concrete actions
		String price = driver
				.findElement(
						By.xpath("//div[text()='Mazedar Makhni Paneer']/ancestor::a//span[contains(@class,'w-auto')]"))
				.getText();
	}

	@Then("I should see the pizza {string} is added to the cart")
	public void i_should_see_the_pizza_is_added_to_the_cart(String expAddedItem) {
		// Write code here that turns the phrase above into concrete actions

		String actAddedItem = driver.findElement(By.xpath("//div[@data-synth='basket-item-type--pizza']")).getText();

		Assert.assertEquals(expAddedItem, actAddedItem);
	}

	@And("price is also displayed correctly")
	public void price_is_also_displayed_correctly() {
		// Write code here that turns the phrase above into concrete actions

		String expPrice = driver
				.findElement(
						By.xpath("//div[text()='Mazedar Makhni Paneer']/ancestor::a//span[contains(@class,'w-auto')]"))
				.getText();
		String actPrice = driver.findElement(By.xpath("//div[contains(@class,'basket-item-product-price')]")).getText();

		Assert.assertEquals(expPrice, actPrice);
	}

	@And("I click on the Checkout button")
	public void i_click_on_the_Checkout_button() {
		// Write code here that turns the phrase above into concrete actions

		driver.findElement(By.xpath("//span[text()='Checkout']")).click();
	}

	@Then("I should be landed on the secured checkout page")
	public void i_should_be_landed_on_the_secured_checkout_page() throws InterruptedException {
		// Write code here that turns the phrase above into concrete actions

		Thread.sleep(4000);
		String ExpTitle = "Checkout | Pizza Hut India";
		String ActTitle = driver.getTitle();
		Assert.assertEquals(ExpTitle, ActTitle);
	}

	@And("I enter the personal details")
	public void i_enter_the_personal_details(DataTable dataTable) {

		List<Map<String, String>> userMap = dataTable.asMaps(String.class, String.class);

		for (Map<String, String> details : userMap) {
			WebElement name = driver.findElement(By.xpath("//input[@name='name']"));
			name.sendKeys(details.get("Name"));
			WebElement mobile = driver.findElement(By.id("checkout__phone"));
			mobile.sendKeys(details.get("Mobile"));
			WebElement email = driver.findElement(By.id("checkout__email"));
			email.sendKeys(details.get("Email"));

		}
	}

	@And("I enter the address details")
	public void i_enter_the_address_details(io.cucumber.datatable.DataTable dataTable) {

		List<String> addressDetails = dataTable.asList(String.class);
		for (String addressDetail : addressDetails) {
			driver.findElement(By.id("checkout__deliveryAddress.interior")).sendKeys(addressDetail);
		}
	}
}
