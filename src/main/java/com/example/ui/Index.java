package com.example.ui;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.PostConstruct;

import com.example.util.TableBuilder;
import com.example.util.XStringToFloatConverter;
import org.apache.log4j.Logger;

import com.example.model.Person;
import com.example.model.PersonRange;
import com.vaadin.annotations.Theme;
import com.vaadin.data.fieldgroup.DefaultFieldGroupFieldFactory;
import com.vaadin.data.fieldgroup.FieldGroupFieldFactory;
import com.vaadin.data.util.converter.Converter;
import com.vaadin.data.util.BeanContainer;
import com.vaadin.data.util.converter.StringToFloatConverter;
import com.vaadin.data.validator.NullValidator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Field;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;

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
		//container.addItem(2, new Person("Adam", "Jensen", "m", 84.7845f));
		//container.addItem(3, new Person("Paul", "Denton", "m", 95.1825f));
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
		builder.setColumnNonEditableConverter("weight", new XStringToFloatConverter(5)).setColumnEditable("weight", true);

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
		
		TextField tf = (TextField) grid.getColumn("weight").getEditorField();
		tf.addTextChangeListener((e) -> {
			TextField field = (TextField) e.getComponent();
			
			float val = (float) field.getConvertedValue();
			
			Log.info("text change event, value: " + val);
		}); 
		//tf.addValueChangeListener((e) -> Log.info("value change event"));
		
		
		/*
		XTextField tf = new XTextField(new XStringToFloatConverter(5));
		grid.getColumn("weight").setEditorField(tf);
		*/
		
		grid.setEditorEnabled(true);
		grid.setEditorBuffered(false);
		
		
		
		
		grid.setEditorFieldFactory(new FieldGroupFieldFactory() {

			 private FieldGroupFieldFactory defaultFieldFactory =
					 	DefaultFieldGroupFieldFactory.get();
			
			private static final long serialVersionUID = 1L;

			private Converter<String, Float> floatConverter = new StringToFloatConverter();
			
			@SuppressWarnings({ "rawtypes", "unchecked" })
		    @Override
		    public <T extends Field> T createField(Class<?> dataType, Class<T> fieldType) {
		 
		        if (Float.class.isAssignableFrom(dataType)) {
		 
		        	Log.info("creating custom float field");
		            return (T) createFloatField();
		        }
		 
				Log.info("use default field factory for: " + dataType.getClass().toString());
		        
		        return defaultFieldFactory.createField(dataType, fieldType);
		    }
		 
		    @SuppressWarnings({ "rawtypes", "unchecked" })
		    protected <T extends Field> T createFloatField() {
		 
		        TextField field = new TextField();
		        field.setConverter(floatConverter);
		        field.setValidationVisible(true);
		        field.addValidator(new NullValidator("Set a value", false));
		        
		        Log.info("syle: "  + field.getPrimaryStyleName());
		        
		        return (T) field;
		    }
			
		
		});
		
		HorizontalLayout hl = new HorizontalLayout();
		hl.setDefaultComponentAlignment(Alignment.TOP_CENTER);
		
		hl.addComponent(grid);
		
		setContent(hl);
	}
	
}
