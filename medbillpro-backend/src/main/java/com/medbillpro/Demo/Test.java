package com.medbillpro.Demo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class Test {
    public static void main(String[] args) throws InterruptedException {
        // Set path to ChromeDriver
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");

        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();

        // Open WhatsApp Web
        driver.get("https://web.whatsapp.com");

        System.out.println("Please scan the QR code manually...");
        Thread.sleep(15000); // Wait for QR code scan

        // Replace with your contact name
        String contactName = "John Doe";

        // Search and open chat
        WebElement searchBox = driver.findElement(By.xpath("//div[@title='Search input textbox']"));
        searchBox.click();
        searchBox.sendKeys(contactName);
        Thread.sleep(3000);

        WebElement contact = driver.findElement(By.xpath("//span[@title='" + contactName + "']"));
        contact.click();

        // Click on attach icon
        WebElement attachBtn = driver.findElement(By.xpath("//span[@data-icon='clip']"));
        attachBtn.click();
        Thread.sleep(1000);

        // Upload PDF file
        WebElement fileInput = driver.findElement(By.xpath("//input[@accept='*']"));
        fileInput.sendKeys("C:\\path\\to\\your\\file.pdf");
        Thread.sleep(3000);

        // Click send
        WebElement sendBtn = driver.findElement(By.xpath("//span[@data-icon='send']"));
        sendBtn.click();

        System.out.println("PDF sent successfully!");
        Thread.sleep(5000);
        driver.quit();
    }
}
