package com.vaadin.cbtest;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.data.provider.DataProvider;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

/**
 * This UI is the application entry point. A UI may either represent a browser
 * window (or tab) or some part of an HTML page where a Vaadin application is
 * embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is
 * intended to be overridden to add component to the user interface and
 * initialize non-component functionality.
 */
@Theme("mytheme")
public class MyUI extends UI {

	@Override
    protected void init(VaadinRequest vaadinRequest) {
        final VerticalLayout layout = new VerticalLayout();
        
        final ComboBox<Person> cb = new ComboBox<>("Select a person");
        
        final Label personLabel = new Label("Selected person");
        
        List<Person> persons = new ArrayList<>();
        persons.add(new Person(20, "Su"));
        persons.add(new Person(21, "Susann"));
        persons.add(new Person(22, "Susi"));
        persons.add(new Person(23, "Sue"));
        persons.add(new Person(24, "Suko"));
        persons.add(new Person(25, "Sulaiman"));
        persons.add(new Person(26, "Sumi"));
        persons.add(new Person(27, "Susen"));
        persons.add(new Person(28, "Sullyvan"));
        persons.add(new Person(29, "Sunny"));
        
        cb.setDataProvider(DataProvider.ofCollection(persons));
        cb.setItemCaptionGenerator(Person::getPrintName);
        cb.addValueChangeListener(e-> personLabel.setValue(e.getValue().getPrintName()));
        
        layout.addComponents(cb, personLabel);
        setContent(layout);
    }

	@WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
	@VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
	public static class MyUIServlet extends VaadinServlet {
	}

	public class Person {
		private String name;
		private int age;

		public Person(int age, String name) {
			super();
			this.age = age;
			this.name = name;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public int getAge() {
			return age;
		}

		public void setAge(int age) {
			this.age = age;
		}

		public String getPrintName() {
			return name + " [Age: " + age + "]";
		}

	}
}
