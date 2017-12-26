package com.example.util;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.vaadin.data.Container;
import com.vaadin.data.util.converter.Converter;
import com.vaadin.ui.Component;
import com.vaadin.ui.DefaultFieldFactory;
import com.vaadin.ui.Field;
import com.vaadin.ui.Table;

public class TableBuilder {
	
	private String caption;
	private Container.Indexed container;
	private Map<Object, String> customHeaders = new HashMap<>();
	private Set<Object> columnOrder;
	private Set<Object> columnsNonEditable = new HashSet<>();
	private Map<Object, Converter<String, ?>> columnsNonEditableConverter = new HashMap<>();
	private Boolean isTableEditable = new Boolean(false);
	
	public class FieldFactoryEditables extends DefaultFieldFactory {
		private static final long serialVersionUID = 0L;

		public FieldFactoryEditables() {
			super();
		}
		
		public FieldFactoryEditables(Set<Object> nonEditablePropertyIds) {
			super();
			
			this.nonEditablePropertyIds = nonEditablePropertyIds;
		}
		
		private Set<Object> nonEditablePropertyIds;
		
		@SuppressWarnings("rawtypes")
		@Override
		public Field createField(Container container, Object itemId,
				Object propertyId, Component uiContext) {
			
			if(nonEditablePropertyIds.contains(propertyId))
				return null;

	        // Otherwise use the default field factory 
	        return super.createField(container, itemId, propertyId,
	                                 uiContext);
		}
		
	};
	
	public TableBuilder() {
		this(null);
	}
	
	public TableBuilder(Container.Indexed container) {
		this.container = container;
	}
	
	public TableBuilder setCaption(String caption) {
		this.caption = caption;
		return this;
	}
	
	public TableBuilder setColumnHeader(Object propertyId, String header) {
		this.customHeaders.put(propertyId, header);
		return this;
	}
	
	public TableBuilder setColumnEditable(Object propertyId, boolean editable) {
		if(!editable)
			this.columnsNonEditable.add(propertyId);
		else
			this.columnsNonEditable.remove(propertyId);

		return this;
	}
	
	public TableBuilder setColumnOrder(Set<Object> propertyIds) {
		this.columnOrder = propertyIds;
		return this;
	}
	
	public TableBuilder setEditable(Boolean editable) {
		this.isTableEditable = editable;
		return this;
	}
	
	public TableBuilder setColumnNonEditableConverter(Object propertyId,
			Converter<String, ?> converter) {
		this.columnsNonEditableConverter.put(propertyId, converter);
		return this;
	}
	
	public Table build() {
		Table table = new Table();
		
		// set container data source and column visibility (& order)
		if(this.columnOrder == null) {
			table.setContainerDataSource(this.container);
		} else {
			table.setContainerDataSource(this.container, this.columnOrder);
		}
		
		// set column headers
		this.customHeaders.forEach((key,val) -> table.setColumnHeader(key, val));
		
		// set table caption
		if(this.caption != null)
			table.setCaption(this.caption);
		
		// set table editable preferences
		table.setEditable(this.isTableEditable);
		table.setTableFieldFactory(new FieldFactoryEditables(this.columnsNonEditable));
		this.columnsNonEditableConverter.forEach((key,val) -> table.setConverter(key, val));
		
		return table;
	}
	
	
}
