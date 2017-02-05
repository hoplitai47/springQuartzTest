package com.example;

import com.vaadin.data.util.converter.Converter;
import com.vaadin.ui.TextField;

public class XTextField extends TextField {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 0L;

	public XTextField(Converter<String, ?> converter) {
		this.setConverter(converter);
	}
	
}
