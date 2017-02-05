package com.example.ui;

import java.math.RoundingMode;
import java.text.NumberFormat;
import java.text.ParsePosition;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;

import com.example.TableBuilder;
import com.example.XStringToFloatConverter;
import com.example.XTextField;
import com.example.TableBuilder.FieldFactoryEditables;
import com.example.model.Person;
import com.example.model.PersonRange;
import com.vaadin.annotations.Theme;
import com.vaadin.data.Container;
import com.vaadin.data.fieldgroup.FieldGroupFieldFactory;
import com.vaadin.data.util.converter.Converter;
import com.vaadin.data.util.BeanContainer;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.converter.StringToDoubleConverter;
import com.vaadin.data.util.converter.StringToFloatConverter;
import com.vaadin.data.util.converter.Converter.ConversionException;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.DateField;
import com.vaadin.ui.DefaultFieldFactory;
import com.vaadin.ui.Field;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Grid.Column;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.renderers.NumberRenderer;

@SpringUI
@Theme("valo")
public class Index extends UI {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 0L;

	private static final Logger Log = Logger.getLogger(Index.class);

	private PersonRange data;
	private BeanContainer<Integer, Person> container;
	
	
	public Index() {
		Log.info("Index Constructor");
		
		data = new PersonRange("TriOptimum");
		data.getPeople().add(new Person("JC", "Denton", "m", 88.1245f));
		data.getPeople().add(new Person("Adam", "Jensen", "m", 84.7845f));
		data.getPeople().add(new Person("Paul", "Denton", "m", 95.1825f));
		
		container = new BeanContainer<Integer, Person>(Person.class);
		container.setBeanIdProperty("id");
		
		//container.addAll(data.getPeople());
		container.addItem(1, new Person("JC", "Denton", "m", 88.1245f));
		container.addItem(2, new Person("Adam", "Jensen", "m", 84.7845f));
		container.addItem(3, new Person("Paul", "Denton", "m", 95.1825f));
	}
	
	@Override
	protected void init(VaadinRequest req) {
		
	}
	
	
	public Table constructTable() {
		Set<Object> columnOrder = new HashSet<>(
				Arrays.asList("firstName", "lastName", "sex", "weight"));

		
		TableBuilder builder = new TableBuilder(this.container);
		builder.setCaption(data.getCompanyUnit());
		builder.setEditable(true);
		builder.setColumnOrder(columnOrder);
		
		builder.setColumnHeader("firstName", "First Name");
		builder.setColumnEditable("firstName", false);
		
		builder.setColumnHeader("lastName", "Last Name");
		builder.setColumnEditable("lastName", false);
		
		builder.setColumnHeader("sex", "Sex");
		builder.setColumnEditable("sex", false);
		
		builder.setColumnHeader("weight", "Weight (kg)");
		builder.setColumnNonEditableConverter("weight", new XStringToFloatConverter(5));
		builder.setColumnEditable("weight", true);
		
		Table genTable = builder.build();
		
		return genTable;
	}
	
	@PostConstruct
	public void pc() {
		Log.info("Post Construct");
	
		
		Grid grid = new Grid();
		grid.setContainerDataSource(this.container);
		
		
		grid.removeAllColumns();
		grid.addColumn("firstName");
		grid.getColumn("firstName").setHeaderCaption("First Name");
		grid.getColumn("firstName").setEditable(false);
		
		grid.addColumn("lastName");
		grid.getColumn("lastName").setHeaderCaption("Last Name");
		grid.getColumn("lastName").setEditable(false);
		
		grid.addColumn("sex");
		grid.getColumn("sex").setHeaderCaption("Sex");
		grid.getColumn("sex").setEditable(false);
		
		grid.addColumn("weight");
		grid.getColumn("weight").setHeaderCaption("Weight (kg)");
		grid.getColumn("weight").setConverter(new XStringToFloatConverter(5));
		
		XTextField tf = new XTextField(new XStringToFloatConverter(5));
		
		grid.getColumn("weight").setEditorField(tf);
		

		
		grid.setEditorEnabled(true);
		grid.setEditorBuffered(false);
		
		
		
		HorizontalLayout hl = new HorizontalLayout();
		hl.setDefaultComponentAlignment(Alignment.TOP_CENTER);
		
		hl.addComponent(grid);
		
		setContent(hl);
	}
	
}
