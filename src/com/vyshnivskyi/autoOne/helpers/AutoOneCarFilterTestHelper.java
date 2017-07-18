package com.vyshnivskyi.autoOne.helpers;

import com.vyshnivskyi.autoOne.pageControls.ourCarsPage.OurCarsPageControl;
import com.vyshnivskyi.autoOne.pageControls.ourCarsPage.carItem.CarItemControl;
import org.testng.Assert;

import java.util.List;

import static java.util.logging.Level.INFO;
import static java.util.logging.Logger.getGlobal;

public class AutoOneCarFilterTestHelper {
	private final OurCarsPageControl ourCarsPage = new OurCarsPageControl();
	private final String carLabel;

	public AutoOneCarFilterTestHelper(String carLabel) {
		this.carLabel = carLabel;
	}

	public AutoOneCarFilterTestHelper openOurCarsPageAndCheckCarFilter() {
		ourCarsPage
				.open()
				.waitUntilOpened()
				.checkRequiredFilter(carLabel);
		return this;
	}

	public AutoOneCarFilterTestHelper verifyExpectedFilterSelected() {
		getGlobal().log(INFO, "Started checking that expected filter selected");
		Assert.assertTrue(ourCarsPage.isCheckedRequiredFilter(carLabel),
				String.format("Expected car filter [%s] wasn't selected", carLabel));

		getGlobal().log(INFO, String.format("Expected car filter [%s] was selected", carLabel));
		return this;
	}

	public AutoOneCarFilterTestHelper verifyAllCarsAreFiltered() {
		getGlobal().log(INFO, "Started checking that all cars successfully filtered");
		List<CarItemControl> carItemsList = ourCarsPage.getCarItemsList();
		carItemsList.forEach(e -> Assert.assertTrue(e.doesCarManufactureContainsExpected(carLabel),
				"Car list has not filtered car with car manufacture ["
						+ e.getCarManufacture() +
						" doesn't equals to expected value [" + carLabel + "]"));

		getGlobal().log(INFO, "Cars were successfully filtered");
		return this;
	}

	public AutoOneCarFilterTestHelper verifyAllCarsHaveImages() {
		getGlobal().log(INFO, "Started checking that all cars have images");
		List<CarItemControl> carItemsList = ourCarsPage.getCarItemsList();
		carItemsList.forEach(e -> Assert.assertTrue(e.doesCarItemHasImage(),
				"[" + e.getCarManufacture() + "] car does't have an image"));

		getGlobal().log(INFO, "All cars have images");
		return this;
	}

	public AutoOneCarFilterTestHelper verifyAllCarsInformationIsComplete() {
		getGlobal().log(INFO, "Started checking that all cars have complete information");
		List<CarItemControl> carItemsList = ourCarsPage.getCarItemsList();
		carItemsList.forEach(e -> Assert.assertTrue(e.isCarItemInformationComplete(),
				"[" + e.getCarManufacture() + "] car information isn't complete"));

		getGlobal().log(INFO, "All cars information have complete information");
		return this;
	}
}
