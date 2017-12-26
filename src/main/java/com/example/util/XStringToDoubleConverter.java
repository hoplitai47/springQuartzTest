package com.example.util;

import java.text.NumberFormat;
import java.util.Locale;

import com.vaadin.data.util.converter.StringToDoubleConverter;

public class XStringToDoubleConverter extends StringToDoubleConverter {

	private static final long serialVersionUID = 0L;

	private int precision;
	
	public XStringToDoubleConverter(int precision) {
		super();	
		this.precision = precision;
	}
	
	@Override
    protected NumberFormat getFormat(Locale locale) {
        NumberFormat format = super.getFormat(locale);
        format.setGroupingUsed(false);
        format.setMaximumFractionDigits(this.precision);
        return format;
    }   
}
