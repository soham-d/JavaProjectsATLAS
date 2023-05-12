package com.amazon.dmataccountmanger.controller;

import java.util.List;

import com.amazon.dmataccountmanger.DmatUserSession;
import com.amazon.dmataccountmanger.db.ShareDAO;
import com.amazon.dmataccountmanger.db.UserSharesDAO;
import com.amazon.dmataccountmanger.model.Share;
import com.amazon.dmataccountmanger.model.User;
import com.amazon.dmataccountmanger.model.UserShares;

public class ShareService {
	
	private static ShareService service = new ShareService();

	public static ShareService getInstance() {
		return service;
	}
	
	ShareDAO sDAO = new ShareDAO();
	
	public List<Share> fetchAllShares() {				
		return sDAO.retrieve();
	}

	public List<UserShares> fetchAllUserShares(){
		User currUser = DmatUserSession.user;
		
		String fetchShares = "Select * from usershares where userid="+currUser.id+";";
		
		return new UserSharesDAO().retrieve(fetchShares);
	}
	
	public void displayPortfolio() {
		List<UserShares> portfolio = fetchAllUserShares();
		
		for(UserShares share:portfolio) {
			share.prettyPrint();
		}
	
	}
}
