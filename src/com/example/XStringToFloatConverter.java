package com.example;

import java.text.NumberFormat;
import java.util.Locale;

import com.vaadin.data.util.converter.StringToFloatConverter;

public class XStringToFloatConverter extends StringToFloatConverter {

	private static final long serialVersionUID = 0L;

	private int precision;
	
	public XStringToFloatConverter(int precision) {
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
