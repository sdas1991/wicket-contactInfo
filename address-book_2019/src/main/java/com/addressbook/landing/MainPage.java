package com.addressbook.landing;

import org.apache.wicket.Page;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.feedback.FeedbackMessage;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.FeedbackPanel;

import com.addressbook.errorhandling.ErrorFilter;


public class MainPage extends WebPage{

	/**
	 * 
	 */
	private static final long serialVersionUID = 825061403293696665L;
	

	public MainPage() {
		FeedbackPanel errorFeedBackPanel = new FeedbackPanel("feedback",
				new ErrorFilter(FeedbackMessage.ERROR));
		FeedbackPanel succesFeedBackPanel = new FeedbackPanel("success",
				new ErrorFilter(FeedbackMessage.SUCCESS));
		
		add(errorFeedBackPanel);
		add(succesFeedBackPanel);
		
		add(new AjaxLink<String>("signUp") {

			/**
			 * 
			 */
			private static final long serialVersionUID = 6573750371569433358L;

			@Override
			public void onClick(AjaxRequestTarget target) {
				// TODO Auto-generated method stub
				setResponsePage(SignUp.class);
			}
		});
		
		add(new AjaxLink<String>("LogIn") {

			/**
			 * 
			 */
			private static final long serialVersionUID = 6573750371569433358L;

			@Override
			public void onClick(AjaxRequestTarget target) {
				// TODO Auto-generated method stub
				setResponsePage(LoginPage.class);
			}
		});
		
		
		
		
	}

}
