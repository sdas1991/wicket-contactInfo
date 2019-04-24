package com.addresbook.dao;

import java.io.Serializable;

public class ModelDAO implements Serializable{
	
	private boolean isValidDAO;

	public boolean isValidDAO() {
		return isValidDAO;
	}

	public void setValidDAO(boolean isValidDAO) {
		this.isValidDAO = isValidDAO;
	}
	

}
