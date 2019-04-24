package com.addressbook;

import org.apache.wicket.Session;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.request.Request;
import org.apache.wicket.request.Response;


import com.addressbook.landing.LoginPage;
import com.addressbook.landing.MainPage;
import com.addressbook.landing.UserSession;

/**
 * Application object for your web application.
 * If you want to run this application without deploying, run the Start class.
 * 
 * @see com.addressbook.Start#main(String[])
 */
public class WicketApplication extends WebApplication
{
	/**
	 * @see org.apache.wicket.Application#getHomePage()
	 */
	@Override
	public Class<? extends WebPage> getHomePage()
	{
		return MainPage.class;
	}
	@Override
	public Session newSession(Request request, Response response) {
		// TODO Auto-generated method stub
		return new UserSession(request);
	}

	/**
	 * @see org.apache.wicket.Application#init()
	 */
	@Override
	public void init()
	{
		super.init();
		/*getComponentInstantiationListeners().add(new SpringComponentInjector(this));*/
		

		// add your configuration here
	}
}
