package com.vyshnivskyi.autoOne.pageControls.ourCarsPage.carItem;

import org.openqa.selenium.WebElement;

import java.util.List;

import static com.vyshnivskyi.autoOne.infrastructure.executors.WebSelectorExecutor.isDisplayed;
import static com.vyshnivskyi.autoOne.pageControls.ourCarsPage.carItem.CarItemElements.*;
import static javax.swing.text.html.HTML.Attribute.SRC;

public class CarItemControl {
	private final WebElement carItemContainer;

	public CarItemControl(WebElement carItemContainer) {
		this.carItemContainer = carItemContainer;
	}

	public boolean doesCarManufactureContainsExpected(String expectedManufacture) {
		return carItemContainer
				.findElement(CAR_INFO_SELECTOR)
				.findElement(CAR_NAME_SELECTOR)
				.getText()
				.startsWith(expectedManufacture);
	}

	public boolean doesCarItemHasImage() {
		if (!isDisplayed(carItemContainer.findElement(CAR_IMAGE_SELECTOR))) return false;
		String carImageSrc = carItemContainer
				.findElement(CAR_IMAGE_SELECTOR)
				.findElement(CAR_IMAGE_SRC_SELECTOR)
				.getAttribute(SRC.toString());
		if ((carImageSrc).isEmpty()) return false;
		return carImageSrc.endsWith(".jpeg");
	}

	public boolean isCarItemInformationComplete() {
		List<WebElement> carInfoElementsList = getCarInfoElementsList();
		if (carInfoElementsList.size() != CAR_INFO_ELEMENTS_COUNT) return false;
		for (WebElement carInfoElement : carInfoElementsList) {
			if(!isCarInfoElementContentFull(carInfoElement)) return false;
		}
		return true;
	}

	public String getCarManufacture() {
		return carItemContainer
				.findElement(CAR_INFO_SELECTOR)
				.findElement(CAR_NAME_SELECTOR)
				.getText();
	}

	private boolean isCarInfoElementContentFull(WebElement carInfoElement) {
		List<WebElement> carInfoElementContentList = carInfoElement.findElements(CAR_INFO_ELEMENT_CONTENT);
		if (carInfoElementContentList.size() < 2) return false;
		for (WebElement carInfoElementContent : carInfoElementContentList) {
			if (carInfoElementContent.getText().isEmpty()) return false;
		}
		return true;
	}

	private List<WebElement> getCarInfoElementsList() {
		return carItemContainer
				.findElement(CAR_INFO_SELECTOR)
				.findElements(CAR_INFO_ELEMENT_SELECTOR);
	}
}
