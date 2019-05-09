package base;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class WaitElemMaven {
    private static WebElement element = null;

    public static WebElement waitElem(WebDriver driver, By by) throws InterruptedException {
        element = driver.findElement(by);

        while (!isDisplayed(element)) {
            Thread.sleep(1000);
            System.out.println("Element is not visible yet");
        }
        return element;

    }

    private static boolean isDisplayed(WebElement element) {
        try {
            if (element.isDisplayed())
                return element.isDisplayed();
        } catch (NoSuchElementException ex) {
            return false;
        }
        return false;
    }
}
