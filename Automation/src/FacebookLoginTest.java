import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

public class FacebookLoginTest {

    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver","C:\\webdrivers\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        // --- Test Case 1: Valid Login ---
        driver.get("https://www.facebook.com/");
        driver.findElement(By.id("email")).sendKeys("valid_email@gmail.com");
        driver.findElement(By.id("pass")).sendKeys("correct123");
        driver.findElement(By.name("login")).click();
        Thread.sleep(2000);
        System.out.println("TC01 - Valid Login: " + (driver.getCurrentUrl().contains("facebook.com") ? "PASS" : "FAIL"));

        // --- Test Case 2: Invalid Password ---
        driver.get("https://www.facebook.com/");
        driver.findElement(By.id("email")).sendKeys("valid_email@gmail.com");
        driver.findElement(By.id("pass")).sendKeys("wrongpass");
        driver.findElement(By.name("login")).click();
        Thread.sleep(2000);
        boolean errorDisplayed = driver.getPageSource().contains("The password you entered is incorrect");
        System.out.println("TC02 - Invalid Password: " + (errorDisplayed ? "PASS" : "FAIL"));

        // --- Test Case 3: Blank Username ---
        driver.get("https://www.facebook.com/");
        driver.findElement(By.id("email")).clear();
        driver.findElement(By.id("pass")).sendKeys("correct123");
        driver.findElement(By.name("login")).click();
        Thread.sleep(2000);
        boolean blankUserError = driver.getPageSource().contains("required");
        System.out.println("TC03 - Blank Username: " + (blankUserError ? "PASS" : "FAIL"));

        // --- Test Case 4: Blank Password ---
        driver.get("https://www.facebook.com/");
        driver.findElement(By.id("email")).sendKeys("valid_email@gmail.com");
        driver.findElement(By.id("pass")).clear();
        driver.findElement(By.name("login")).click();
        Thread.sleep(2000);
        boolean blankPassError = driver.getPageSource().contains("required") || driver.getPageSource().contains("incorrect");
        System.out.println("TC04 - Blank Password: " + (blankPassError ? "PASS" : "FAIL"));

        // --- Test Case 5: Invalid Email ---
        driver.get("https://www.facebook.com/");
        driver.findElement(By.id("email")).sendKeys("wrong_email@gmail.com");
        driver.findElement(By.id("pass")).sendKeys("somepass");
        driver.findElement(By.name("login")).click();
        Thread.sleep(2000);
        boolean invalidEmailError = driver.getPageSource().contains("not connected") || driver.getPageSource().contains("account");
        System.out.println("TC05 - Invalid Email: " + (invalidEmailError ? "PASS" : "FAIL"));

        // Close browser
        driver.quit();
    }
}
