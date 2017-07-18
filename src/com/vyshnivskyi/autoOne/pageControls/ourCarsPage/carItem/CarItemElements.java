package com.vyshnivskyi.autoOne.pageControls.ourCarsPage.carItem;

import org.openqa.selenium.By;

public class CarItemElements {
	public static final By CAR_IMAGE_SELECTOR = By.className("car-img");
	public static final By CAR_IMAGE_SRC_SELECTOR = By.tagName("img");
	public static final By CAR_INFO_SELECTOR = By.className("car-info");
	public static final By CAR_NAME_SELECTOR = By.className("car-name");
	public static final By CAR_INFO_ELEMENT_SELECTOR = By.tagName("tr");
	public static final By CAR_INFO_ELEMENT_CONTENT = By.tagName("td");
	public static final int CAR_INFO_ELEMENTS_COUNT = 7;
}
