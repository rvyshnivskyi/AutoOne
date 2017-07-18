package com.vyshnivskyi.autoOne.pageControls.ourCarsPage;

import com.vyshnivskyi.autoOne.infrastructure.waiter.WhileDo;
import com.vyshnivskyi.autoOne.pageControls.AbstractPage;
import com.vyshnivskyi.autoOne.pageControls.ourCarsPage.carItem.CarItemControl;
import com.vyshnivskyi.autoOne.pageControls.ourCarsPage.filterBox.FilterBoxControl;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.vyshnivskyi.autoOne.infrastructure.executors.WebSelectorExecutor.$;
import static com.vyshnivskyi.autoOne.infrastructure.models.ConstantInstances.TIME_OUT;
import static com.vyshnivskyi.autoOne.infrastructure.models.ConstantInstances.WEB_DRIVER;
import static com.vyshnivskyi.autoOne.pageControls.ourCarsPage.OurCarsPageElements.*;

public class OurCarsPageControl extends AbstractPage {
	private FilterBoxControl filterBox;
	private WebElement checkedFilterItem;

	@Override
	public OurCarsPageControl waitUntilOpened() {
		WhileDo.wait(this::isOpened, "Our cars page is opened", TIME_OUT).run();
		Logger.getGlobal().log(Level.INFO, "Our cars page was opened");
		filterBox = new FilterBoxControl($(FILTER_BOX_CONTAINER_SELECTOR).findElement());
		return this;
	}

	@Override
	public boolean isOpened() {
		return $(FILTER_BOX_CONTAINER_SELECTOR).isDisplayed();
	}

	public OurCarsPageControl open() {
		WEB_DRIVER.get(URL);
		return this;
	}

	public OurCarsPageControl checkRequiredFilter(String requiredFilterLabel) {
		checkedFilterItem = filterBox.checkRequiredFilterItem(requiredFilterLabel);
		Logger.getGlobal().log(Level.INFO, "Required filter [" + requiredFilterLabel + "] was checked");
		waitUntilFilteringResultLoaded();
		return this;
	}

	public boolean isCheckedRequiredFilter(String requiredFilterLabel) {
		if (!filterBox.isFilterItemChecked(checkedFilterItem)) return false;
		return (filterBox.isRequiredFilterSelected(requiredFilterLabel));
	}

	public List<CarItemControl> getCarItemsList() {
		List<CarItemControl> carItemsList = new ArrayList<>();
		$(CAR_LIST_CONTAINER_SELECTOR)
				.findElements(CAR_LIST_ITEM_CONTAINER)
				.forEach(e -> carItemsList.add(new CarItemControl(e)));
		return carItemsList;
	}

	private void waitUntilFilteringResultLoaded() {
		WhileDo.wait(
				() -> $(LOADING_TICKER_SELECTOR).isNotDisplayed(),
				"Loading ticker is displayed",
				TIME_OUT).run();
	}
}
