package com.vyshnivskyi.autoOne.pageControls.ourCarsPage.filterBox;

import org.openqa.selenium.By;

import javax.swing.text.html.HTML;

public class FilterBoxElements {
	public static final By CHECK_BOX_LIST_SELECTOR = By.className("checkbox-list");
	public static final By CHECK_BOX_ITEM_CONTAINER_SELECTOR = By.tagName("li");
	public static final By CHECK_BOX_SELECTOR = By.className("checkbox");
	public static final By CHECK_BOX_MANUFACTURE_LABEL_SELECTOR = By.className("label");
	public static final HTML.Attribute CHECKED_CHECK_BOX_ATTRIBUTE = HTML.Attribute.CLASS;
	public static final String CHECKED_CHECK_BOX_ATTRIBUTE_VALUE = "checked";
	public static final By SELECTED_ITEMS_LIST_SELECTOR = By.className("select2-selection__rendered");
	public static final By SELECTED_CHOICE_SELECTOR = By.className("select2-selection__choice");
}
