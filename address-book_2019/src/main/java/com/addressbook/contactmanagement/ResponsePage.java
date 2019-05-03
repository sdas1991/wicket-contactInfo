package com.addressbook.contactmanagement;


import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.feedback.FeedbackMessage;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.FeedbackPanel;

import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.model.util.ListModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;


import com.addressbook.dto.ContactPerson;
import com.addressbook.errorhandling.ErrorFilter;
import com.addressbook.landing.LoginPage;
import com.addressbook.landing.UserSession;

public class ResponsePage extends WebPage{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2788311229442360682L;
	private ContactPerson person;
	
	
	

	public ResponsePage() {
		
		FeedbackPanel errorFeedBackPanel = new FeedbackPanel("feedback",
				new ErrorFilter(FeedbackMessage.ERROR));
		FeedbackPanel succesFeedBackPanel = new FeedbackPanel("success",
				new ErrorFilter(FeedbackMessage.SUCCESS));
		
		add(errorFeedBackPanel);
		add(succesFeedBackPanel);
		
		
		
		add(new Label("msg", new Model<String>(UserSession.getInstance().getUser().getEmail())));
		
		add(new ResponseForm("search-form"));
		
		
		
	}
	 
	 
	
	
	
	
	
	@SuppressWarnings("rawtypes")
	private class ResponseForm extends Form{
		
		/**
		 * 
		 */
		private static final long serialVersionUID = -7754723128701497191L;
		private String searchString;

		@SuppressWarnings({ "unchecked" })
		public ResponseForm(String id) {
			super(id);
			
			
			add(new TextField("searchString",
	                  new PropertyModel(this, "searchString")));
	        
			
			add(new AjaxLink<String>("addContact") {

				/**
				 * 
				 */
				private static final long serialVersionUID = 6573750371569433358L;

				@Override
				public void onClick(AjaxRequestTarget target) {
					
					setResponsePage(AddContact.class);
				}
			});
	        
	        add(new Link<String>("signOut") {
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
		}
		
		public void onSubmit() {
			PageParameters params = new PageParameters();
            params.add("searchString", searchString);
            setResponsePage(ContactPage.class, params);
		}
		
		 
	}

}
