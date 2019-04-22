package com.addressbook.viewcontrol.search;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget.IListener;
import org.apache.wicket.feedback.FeedbackMessage;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.model.util.ListModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import com.addressbook.dto.Address;
import com.addressbook.dto.ContactPerson;
import com.addressbook.errorhandling.ErrorFilter;
import com.addressbook.viewcontrol.LoginPage;
import com.addressbook.viewcontrol.UserSession;

public class ResponsePage extends WebPage{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2788311229442360682L;
	final ContactPerson person;
	final Address address;
	private ListModel<Address> listAddress;

	public ResponsePage() {
		
		person=new ContactPerson();
		address=new Address();
		listAddress = new ListModel<>();
		
		
		Form<ContactPerson> form = new ResponseForm("contactForm", new CompoundPropertyModel<ContactPerson>(person));
		
		form.add(new TextField<String>("namePlace",new PropertyModel<String>(person,"namePerson")));
		form.add(new TextField<String>("emailPlace",new PropertyModel<String>(person,"emailAdd")));
		form.add(new TextField<String>("phonePlace",new PropertyModel<String>(person,"phoneNumber")));
		
		form.add(new TextField<String>("addPlace",new PropertyModel<String>(address,"addressLine")));
		listAddress.getObject().add(address);
		
		person.setAddress(listAddress.getObject());
		
		
		add(new Label("msg", new Model<String>(UserSession.getInstance().getUser().getEmail())));
		
		form.add(new Link<String>("signOut") {
			/**
			 * 
			 */
			private static final long serialVersionUID = 5953529955254239528L;

			@Override
			public void onClick() {
				UserSession.getInstance().invalidate();
				
				setResponsePage(LoginPage.class);
				
			}
			
		});
		
		FeedbackPanel errorFeedBackPanel = new FeedbackPanel("feedback",
				new ErrorFilter(FeedbackMessage.ERROR));
		FeedbackPanel succesFeedBackPanel = new FeedbackPanel("succes",
				new ErrorFilter(FeedbackMessage.SUCCESS));
		
		add(errorFeedBackPanel);
		add(succesFeedBackPanel);
		add(form);
	}
	
	
	

}
