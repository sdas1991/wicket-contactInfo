package com.addressbook.dto;

import java.io.Serializable;

//Serialized for implementaion for data objects
public class ModelBase implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6890686528047545077L;
	private boolean isValidModel;

	public boolean isValidModel() {
		return isValidModel;
	}

	public void setValidModel(boolean isValidModel) {
		this.isValidModel = isValidModel;
	}
	
	

}
