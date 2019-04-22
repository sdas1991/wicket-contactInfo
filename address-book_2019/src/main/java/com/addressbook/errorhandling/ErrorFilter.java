package com.addressbook.errorhandling;

import org.apache.wicket.feedback.FeedbackMessage;
import org.apache.wicket.feedback.IFeedbackMessageFilter;

public class ErrorFilter implements IFeedbackMessageFilter{
	
	private int errorLevel;

	public ErrorFilter(int errorLevel) {
		this.errorLevel = errorLevel;
	}

	@Override
	public boolean accept(FeedbackMessage message) {
		// TODO Auto-generated method stub
		return message.getLevel() == errorLevel;
	}

}
