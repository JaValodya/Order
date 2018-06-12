package com.weborder;

import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import net.bytebuddy.utility.RandomString;

public class Order {

	public static void main(String[] args) {

		String login = "Tester";
		String password = "test";

		Random rd = new Random();
		int quantity = rd.nextInt(100);

		RandomString rs = new RandomString();
		String name = "John " + rs.nextString() + " Smith";

		String street = "123 Any st";
		String city = "Anytown";
		String state = "Virginia";

		// Random rd2 = new Random();
		int min = 10000;
		int max = 99999;
		String zipCode = String.valueOf(rd/* 2 */.nextInt(max - min) + min); // (man + min)-min

		// Random rd3 = new Random();
		String cardType = String.valueOf(rd/* 3 */.nextInt(3));// 0--Visa, 1--MasterCard, 2--AmericanExpress

		// long visaNumber = 4000_0000_0000_0000L; //16 digits, starts with 4
		// long masterCardNumber = 5000_0000_0000_0000L; //16 digits, starts with 5
		// long americanExpressNumber = 30000_00000_00000L; //15 digits, starts with 3

		String visaNumber = "4"; // 16 digits, starts with 4
		String masterCardNumber = "5"; // 16 digits, starts with 5
		String americanExpressNumber = "3"; // 15 digits, starts with 3
		String cardNumber = "";
		// Random rd4 = new Random();
		if (Integer.parseInt(cardType) == 0/* visa */) {
			for (int i = 1; i < 16; i++) {
				visaNumber += rd/* 4 */.nextInt(10);
			}
			cardNumber = visaNumber;
		} else if (Integer.parseInt(cardType) == 1 /* MasterCard */) {
			for (int i = 1; i < 16; i++) {
				masterCardNumber += rd/* 4 */.nextInt(10);
			}
			cardNumber = masterCardNumber;
		} else if (Integer.parseInt(cardType) == 2/* AmericanExpress */ ) {
			for (int i = 1; i < 14; i++) {
				americanExpressNumber += rd/* 4 */.nextInt(10);
			}
			cardNumber = americanExpressNumber;
		}

		int month = rd.nextInt(13 - 1) + 1;
		String monthStr = "";
		if (month < 10) {
			monthStr = "0" + month;
		} else {
			monthStr = String.valueOf(month);
		}

		String expirationDate = monthStr + "/" + String.valueOf(rd.nextInt(99 - 17) + 17);// (mm/yy)

		String expected = "New order has been successfully added.";
		String actual = "";

		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\Red\\Documents\\selenium dependencies\\drivers\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.navigate().to("http://secure.smartbearsoftware.com/samples/TestComplete12/WebOrders/Login.aspx");

		driver.findElement(By.name("ctl00$MainContent$username")).sendKeys(login);
		driver.findElement(By.name("ctl00$MainContent$password")).sendKeys(password);

		driver.findElement(By.name("ctl00$MainContent$login_button")).click();

		driver.findElement(By.linkText("Order")).click();

		driver.findElement(By.name("ctl00$MainContent$fmwOrder$txtQuantity")).sendKeys(String.valueOf(quantity));

		// Enters name
		driver.findElement(By.name("ctl00$MainContent$fmwOrder$txtName")).sendKeys(name);

		// Enters street
		driver.findElement(By.name("ctl00$MainContent$fmwOrder$TextBox2")).sendKeys(street);

		// Enters city
		driver.findElement(By.name("ctl00$MainContent$fmwOrder$TextBox3")).sendKeys(city);

		// Enters State
		driver.findElement(By.name("ctl00$MainContent$fmwOrder$TextBox4")).sendKeys(state);

		// Enters zipCode
		driver.findElement(By.name("ctl00$MainContent$fmwOrder$TextBox5")).sendKeys(zipCode);

		// Selects the card type
		driver.findElement(By.id("ctl00_MainContent_fmwOrder_cardList_" + cardType)).click();

		// Enters cards number
		driver.findElement(By.name("ctl00$MainContent$fmwOrder$TextBox6")).sendKeys(cardNumber);

		// Enters cards expiration date
		driver.findElement(By.name("ctl00$MainContent$fmwOrder$TextBox1")).sendKeys(expirationDate);

		// Process the request
		driver.findElement(By.id("ctl00_MainContent_fmwOrder_InsertButton")).click();

		// checks if "New order has been successfully added."

		// actual = driver.findElement(By.className("buttons_process")).getText(); //
		// .contains -- needs to be used
		// or
		actual = driver.findElement(By.xpath("//strong")).getText();

		if (actual.equals(expected)) {
			System.out.println("pass");
			System.out.println("Actual:\t" + actual);
		} else {
			System.out.println("fail");
			System.out.println("Expected:\t" + expected);
			System.out.println("Actual:\t" + actual);

			
			System.out.println("new message for git to update");
		}
	}

}
