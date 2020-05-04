package com.flipkart.utils;

import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;

import java.time.Duration;

public class GestureFactory {

    private TouchAction touchAction;
    public boolean elemnetFoundFlag=false;
    public String checkElementBeforeScroll;
    public String checkElementAfterScroll;


    public GestureFactory()
    {
        touchAction = new TouchAction((PerformsTouchActions) DriverManager.getDriver());
    }
    public int getScreenWidth()
    {
        return DriverManager.getDriver().manage().window().getSize().getWidth();
    }

    public Point getElementLocation(WebElement element)
    {
        return element.getLocation();
    }

    public Dimension getElementDimension(WebElement element) {
        return element.getSize();
    }
    public int getScreenHeight()
    {
        return DriverManager.getDriver().manage().window().getSize().getHeight();
    }

    public void swipeByCordinates(int x1, int y1, int x2, int y2)
    {
        touchAction.press(PointOption.point(x1, y1)).waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1))).moveTo(PointOption.point(x2, y2)).release().perform();
    }

    //******* Scroll Function **********//
    public void scroll(int scrollSteps)
    {
        // appWidth=100
            int appWidth = getScreenWidth() / 2;
            int appHeight = getScreenHeight() / 2;
            touchAction.press(PointOption.point(appWidth, appHeight)).waitAction(WaitOptions.waitOptions(Duration.ofSeconds(2))).moveTo(PointOption.point(appWidth, appHeight + scrollSteps)).release().perform();

     }

    /****** Horizontal Swipe *********/
    public void horizontalSwipe(WebElement element)
    {
        int startX,startY,endX,endY,offsetValue;
        offsetValue=250;
        startX = element.getSize().width - offsetValue;
        startY =DriverManager.getGesture().getElementLocation(element).y + element.getSize().height / 2;
        endX = DriverManager.getGesture().getElementLocation(element).x + offsetValue;
        endY = DriverManager.getGesture().getElementLocation(element).y+ element.getSize().height / 2;
        DriverManager.getGesture().swipeByCordinates(startX,startY,endX,endY);
    }
}

