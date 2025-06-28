package com.brandcheck;
import org.openqa.selenium.*;
import ru.yandex.qatools.ashot.*;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ScreenshotUtil {
    public static String captureElementScreenshot(WebDriver driver, WebElement element, String url, String name) {
        try {
            // Scroll element into view
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
            Thread.sleep(500); // Small wait for stability

            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            BufferedImage fullImg = ImageIO.read(screenshot);

            // Get element location and size
            Point point = element.getLocation();
            int x = point.getX();
            int y = point.getY();
            int eleWidth = element.getSize().getWidth();
            int eleHeight = element.getSize().getHeight();

            // Ensure cropping is within image bounds
            int maxWidth = fullImg.getWidth();
            int maxHeight = fullImg.getHeight();
            eleWidth = Math.min(eleWidth, maxWidth - x);
            eleHeight = Math.min(eleHeight, maxHeight - y);

            if (eleWidth <= 0 || eleHeight <= 0) {
                System.out.println("⚠️ Invalid element dimensions, skipping screenshot for: " + name);
                return null;
            }

            BufferedImage eleScreenshot = fullImg.getSubimage(x, y, eleWidth, eleHeight);

            // Save the cropped image
            String folderPath = "screenshots";
            new File(folderPath).mkdirs();
            String filePath = folderPath + "/" + name.replaceAll("[^a-zA-Z0-9]", "_") + ".png";
            ImageIO.write(eleScreenshot, "png", new File(filePath));
            return filePath;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


}

