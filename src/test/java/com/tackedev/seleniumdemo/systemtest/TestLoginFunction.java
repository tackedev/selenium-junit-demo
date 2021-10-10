package com.tackedev.seleniumdemo.systemtest;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TestLoginFunction {

    private WebDriver driver;

    public TestLoginFunction() {
        System.setProperty("webdriver.firefox.driver", "geckodriver");
    }

    @BeforeEach
    public void init() {
        driver = new FirefoxDriver();
    }

    @Test
    void testInputRightReturnsWell() throws InterruptedException {
        driver.get("http://localhost:8080/SeleniumDemo");

        WebElement usernameInput = driver.findElement(By.name("username"));
        usernameInput.sendKeys("quangky");

        WebElement passwordInput = driver.findElement(By.name("password"));
        passwordInput.sendKeys("Quangky123");

        WebElement submitButton = driver.findElement(By.name("action"));
        submitButton.click();

        Thread.sleep(2000);
        // We should use Awaitility library instead Thread.sleep

        WebElement welcomeString = driver.findElement(By.xpath("/html/body/h3"));
        assertEquals("Hi, Lê Quang Kỳ", welcomeString.getText(),
                "Welcome string is not as expected when login successfully");
    }

    @Test
    void testInputWrongUsernameReturnsWrongMessage() throws InterruptedException {
        driver.get("http://localhost:8080/SeleniumDemo");

        WebElement usernameInput = driver.findElement(By.name("username"));
        usernameInput.sendKeys("phuocthanh");

        WebElement passwordInput = driver.findElement(By.name("password"));
        passwordInput.sendKeys("Quangky123");

        WebElement submitButton = driver.findElement(By.name("action"));
        submitButton.click();

        Thread.sleep(2000);
        // We should use Awaitility library instead Thread.sleep

        WebElement wrongMessage = driver.findElement(By.xpath("/html/body/form/font"));
        assertEquals("Wrong username or password!", wrongMessage.getText(),
                "Error message is not as expected when input wrong username");
    }

    @Test
    void testInputWrongPasswordReturnsWrongMessage() throws InterruptedException {
        driver.get("http://localhost:8080/SeleniumDemo");

        WebElement usernameInput = driver.findElement(By.name("username"));
        usernameInput.sendKeys("quangky");

        WebElement passwordInput = driver.findElement(By.name("password"));
        passwordInput.sendKeys("Quangky");

        WebElement submitButton = driver.findElement(By.name("action"));
        submitButton.click();

        Thread.sleep(2000);
        // We should use Awaitility library instead Thread.sleep

        WebElement wrongMessage = driver.findElement(By.xpath("/html/body/form/font"));
        assertEquals("Wrong username or password!", wrongMessage.getText(),
                "Error message is not as expected when input wrong password");
    }

    @Test
    void testInputBlackUsernameReturnsWrongMessage() throws InterruptedException {
        driver.get("http://localhost:8080/SeleniumDemo");

        WebElement usernameInput = driver.findElement(By.name("username"));
        usernameInput.sendKeys("        ");

        WebElement passwordInput = driver.findElement(By.name("password"));
        passwordInput.sendKeys("Quangky123");

        WebElement submitButton = driver.findElement(By.name("action"));
        submitButton.click();

        Thread.sleep(2000);
        // We should use Awaitility library instead Thread.sleep

        WebElement wrongMessage = driver.findElement(By.xpath("/html/body/form/font"));
        assertEquals("Username or password is blank!", wrongMessage.getText(),
                "Error message is not as expected when input blank username");
    }

    @Test
    void testInputBlackPasswordReturnsWrongMessage() throws InterruptedException {
        driver.get("http://localhost:8080/SeleniumDemo");

        WebElement usernameInput = driver.findElement(By.name("username"));
        usernameInput.sendKeys("quangky");

        WebElement passwordInput = driver.findElement(By.name("password"));
        passwordInput.sendKeys("      ");

        WebElement submitButton = driver.findElement(By.name("action"));
        submitButton.click();

        Thread.sleep(2000);
        // We should use Awaitility library instead Thread.sleep

        WebElement wrongMessage = driver.findElement(By.xpath("/html/body/form/font"));
        assertEquals("Username or password is blank!", wrongMessage.getText(),
                "Error message is not as expected when input blank password");
    }

    @AfterEach
    public void closeBrowser() {
        driver.close();
    }


}
