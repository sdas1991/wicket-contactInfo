package com.addressbook.viewcontrol;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import com.addressbook.dto.User;
import com.addressbook.viewcontrol.search.ResponsePage;

public class LoginPage extends WebPage{
	/**
	 * 
	 */
	private static final long serialVersionUID = -459295927972351123L;
	public LoginPage() {
		
		final User user=new User();
		Form<Object> form=new Form<Object>("form");
		
		form.add(new TextField<String>("userName",new PropertyModel<String>(user,"email")));
		form.add(new PasswordTextField("pswd",new PropertyModel<String>(user,"password")));
		
		form.add(new Button("submitLog") {
			
			/**
			 * 
			 */
			private static final long serialVersionUID = -6525001367274151206L;

			@Override
			public void onSubmit() {
				// TODO Auto-generated method stub
				super.onSubmit();
				
				if (user.getEmail().equalsIgnoreCase("admin") && user.getPassword().equalsIgnoreCase("admin")) {
					
					UserSession.getInstance().setUser(user);
					
					setResponsePage(ResponsePage.class);
				} else {
					System.out.println("invalid");

				}
			}
		});
		
		add(form);
	}
	
	
	

}
