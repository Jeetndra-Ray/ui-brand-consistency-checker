package com.brandcheck;

import com.brandcheck.ScreenshotUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BrandingValidatorTest {
    WebDriver driver;
    List<ValidationResult> allResults = new ArrayList<>();

    @BeforeClass
    public void setup() {
        driver = new ChromeDriver();
    }

    @Test
    public void validateBranding() throws IOException {
        List<String> urls = ConfigLoader.getUrls();
        for (String url : urls) {
            driver.get(url);
            validateElements(url, "h1");
            validateElements(url, "h2");
            validateElements(url, "h3");
            validateElements(url, "p");
        }
    }

    public void validateElements(String url, String tag) throws IOException {
        List<WebElement> elements = driver.findElements(By.tagName(tag));
        int elementIndex = 0;

        for (WebElement e : elements) {
            String text = e.getText().trim().replaceAll("\\s+", " ");
            boolean spacingOK = TextValidator.hasOnlyOneSpaceBetweenWords(text);
            boolean spellingOK = SpellChecker.checkSpelling(text).isEmpty();
            String font = e.getCssValue("font-family");
            String fontSize = e.getCssValue("font-size");
            String fontWeight = e.getCssValue("font-weight");

            // Take screenshot only if spacing or spelling is incorrect
            String screenshotPath = "";
            if (!spacingOK || !spellingOK) {
                screenshotPath = ScreenshotUtil.captureElementScreenshot(driver, e, url, tag + "_" + elementIndex);
            }

            ValidationResult result = new ValidationResult(url, tag, text, spacingOK, spellingOK, font, fontSize, fontWeight);
            result.setScreenshotPath(screenshotPath);  // ⬅️ Make sure this setter is in your ValidationResult.java
            allResults.add(result);
            elementIndex++;
        }
    }


    @AfterClass
    public void tearDown() throws IOException {
        driver.quit();
        ReportExporter.exportToCSV(allResults, "branding_report.csv");
        HtmlReportExporter.exportToHTML(allResults, "branding_report.html");
    }
}