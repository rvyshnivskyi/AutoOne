package com.vyshnivskyi.autoOne.pageControls.ourCarsPage.filterBox;

import com.vyshnivskyi.autoOne.infrastructure.executors.WebSelectorExecutor;
import org.openqa.selenium.WebElement;

import javax.swing.text.html.HTML;
import java.util.List;
import java.util.NoSuchElementException;

import static com.vyshnivskyi.autoOne.pageControls.ourCarsPage.filterBox.FilterBoxElements.*;

public class FilterBoxControl {
	private final WebElement filterBoxContainerSelector;

	public FilterBoxControl(WebElement filterBoxContainerSelector) {
		this.filterBoxContainerSelector = filterBoxContainerSelector;
	}

	public WebElement checkRequiredFilterItem(String label) {
		List<WebElement> allFilterItemsList = getAllFilterItems();
		for (WebElement filterItem : allFilterItemsList) {
			if(isFilterItemLabelContainsText(filterItem, label)) {
				WebSelectorExecutor.clickHiddenOrCoveredElement(filterItem.findElement(CHECK_BOX_SELECTOR));
				return filterItem;
			}
		}
		throw new NoSuchElementException(String.format("Can't find filter item check-box with [%s] label", label));
	}

	public boolean isRequiredFilterSelected(String filterLabel) {
		List<WebElement> selectedItemList = getSelectedItemsList();
		for(WebElement selectedItem : selectedItemList) {
			if(isSelectedItemTitleContainsText(selectedItem, filterLabel)) return true;
		}
		return false;
	}

	public boolean isFilterItemChecked(WebElement filterItem) {
		return filterItem
				.getAttribute(CHECKED_CHECK_BOX_ATTRIBUTE.toString())
				.equals(FilterBoxElements.CHECKED_CHECK_BOX_ATTRIBUTE_VALUE);
	}

	private List<WebElement> getSelectedItemsList() {
		return filterBoxContainerSelector.findElement(SELECTED_ITEMS_LIST_SELECTOR).findElements(SELECTED_CHOICE_SELECTOR);
	}

	private boolean isSelectedItemTitleContainsText(WebElement selectedItem, String text) {
		return selectedItem.getAttribute(HTML.Attribute.TITLE.toString()).contains(text);
	}

	private boolean isFilterItemLabelContainsText(WebElement filterItem, String text) {
		return filterItem
				.findElement(CHECK_BOX_MANUFACTURE_LABEL_SELECTOR)
				.getText()
				.contains(text);
	}

	private List<WebElement> getAllFilterItems() {
		return filterBoxContainerSelector
				.findElement(CHECK_BOX_LIST_SELECTOR)
				.findElements(CHECK_BOX_ITEM_CONTAINER_SELECTOR);
	}
}
