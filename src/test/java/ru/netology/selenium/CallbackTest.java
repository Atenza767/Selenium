package ru.netology.selenium;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.Collections;
import java.util.List;
import java.util.logging.LogManager;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CallbackTest {
    private WebDriver driver;
    @BeforeAll
    static void setUpAll() {
// автоматическая настройка хромдрайвера с помощью WebDriver Manager
        WebDriverManager.chromedriver().setup();
    }
    @BeforeEach
    void setUp() {
        ChromeOptions options = new ChromeOptions();
// опции для управления режимами работы с памятью, будут полезны при запуске тестов в CI
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
// опция для включения headless-режима, обязателен при запуске тестов в CI
        options.addArguments("--headless");
        driver = new ChromeDriver(options);

        driver.get("http://localhost:9999");
    }
    @AfterEach
    public void tearDown() {
        driver.quit();
        driver = null;
    }
    @Test
    public void shouldTestSomething() {
        driver.findElement(By.cssSelector("[data-test-id='name']input")).sendKeys("Василий Петров");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("+79130000000");
        driver.findElement(By.cssSelector("[data-test-id='agreement']")).click();
        driver.findElement(By.cssSelector(".button")).click();
        String text = driver.findElement(By.cssSelector("[data-test-id='order-success']")).getText();
        assertEquals("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.", text.trim());



//TODO реализовать логику автотеста
    }
}