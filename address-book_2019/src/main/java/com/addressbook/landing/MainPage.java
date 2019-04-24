package com.addressbook.landing;

import org.apache.wicket.Page;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Form;


public class MainPage extends WebPage{

	/**
	 * 
	 */
	private static final long serialVersionUID = 825061403293696665L;
	

	public MainPage() {
		
		
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
