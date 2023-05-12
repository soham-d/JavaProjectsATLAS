package com.amazon.dmataccountmanger.controller;

import java.util.List;
import java.util.Scanner;

import com.amazon.dmataccountmanger.DmatUserSession;
import com.amazon.dmataccountmanger.db.DB;
import com.amazon.dmataccountmanger.db.ShareDAO;
import com.amazon.dmataccountmanger.db.TransactionDAO;
import com.amazon.dmataccountmanger.db.UserDAO;
import com.amazon.dmataccountmanger.db.UserSharesDAO;
import com.amazon.dmataccountmanger.model.Share;
import com.amazon.dmataccountmanger.model.Transaction;
import com.amazon.dmataccountmanger.model.User;
import com.amazon.dmataccountmanger.model.UserShares;

public class TransactionService {

	Scanner scanner = new Scanner(System.in);

	private TransactionService() {

	}

	private static TransactionService service = new TransactionService();

	public static TransactionService getInstance() {
		return service;
	}
	

	User user = DmatUserSession.user;

	ShareService sService = ShareService.getInstance();
	UserDAO uDAO = new UserDAO();
	UserSharesDAO usDAO = new UserSharesDAO();
	DB db = DB.getInstance();

	public int depositAmount() {
		
		User user = DmatUserSession.user;
		
		String sql = "Select * from \"user\" where accountnumber="+user.accountNumber;		
		User user1 = uDAO.retrieve(sql).get(0);

		System.out.println();
		System.out.println("Enter the amount to be deposited...");
		int amount = scanner.nextInt();

		if (amount > 0) {

			int updatedBal = user1.accountBalance + amount;
			user1.accountBalance += amount;

			String sql1= "UPDATE \"User\" set accountBalance = " + updatedBal + "where accountNumber="
					+ user1.accountNumber;

			System.out.println("Initial Account Balance: Rs. " + user.accountBalance);

			int result = db.executeSQL(sql1);
			if (result > 0) {
				System.out.println("Deposit Success...");
				System.out.println("Updated Account Balance : Rs. " + updatedBal);
				DmatUserSession.user.accountBalance = user1.accountBalance;
				return 1;
			} else {
				return 0;
			}
		} else {
			System.err.println("Please enter valid amount...");
			return 0;
		}

	}

	public int withrawAmount() {
		
		User user = DmatUserSession.user;
		
		String sql = "Select * from \"user\" where accountnumber="+user.accountNumber;
		
		User user1 = uDAO.retrieve(sql).get(0);
		
		System.out.println("Current Account Balance: Rs."+user1.accountBalance);
		
		System.out.println("Please enter the amount to be withdrawn: ");
		int withdrawalAmt = scanner.nextInt();
		
		if(user1.accountBalance>=withdrawalAmt) {
			//Withdrawal logic
			int updatedBalance = user1.accountBalance-withdrawalAmt;
			user1.accountBalance -= withdrawalAmt;
			
			String sql1= "UPDATE \"User\" set accountBalance = " + updatedBalance + "where accountNumber="
					+ user1.accountNumber;
			
			if(db.executeSQL(sql1)>0) {
				System.out.println("Withdrawal request is successfully placed...");
				System.out.println("Account Balacne after withrawal: Rs."+updatedBalance);
				DmatUserSession.user = user1;
				return 1;
			}else {
				System.err.println("Something went wrong... Try again!");
				return 0;
			}
			
		}else {
			System.err.println("Withrawal not permitted, please try again!");
			return 0;
		}
	}

	public int buyShare() {

		// 1. Fetch current scrip prices & account details
		List<Share> shares = sService.fetchAllShares();
		User currUser = DmatUserSession.user;
		
		for (Share share:shares) {
			share.prettyPrint();
		}

//		2. select stock
		//scanner.nextLine();
		System.out.println("Select the stock to be bought: ");
		System.out.println("Enter the Stock SYMBOL:");
		String symbol = scanner.nextLine();
		
		Share sharetoBeBought = new Share();
		
		for(Share share:shares) {
			if(share.symbol.equalsIgnoreCase(symbol)) {
				sharetoBeBought = share;
			}
		}
//
//		3. Display Stock details
		System.out.println("Stock Chosen by you: ");
		sharetoBeBought.prettyPrint();
		
		System.out.println("Plesae enter the quanity to be bought: ");
		int quantity = scanner.nextInt();

//
//		4. Calculate amount, deduct from balance
		float amount = quantity * sharetoBeBought.price;
		
		if(currUser.accountBalance > amount) {
			//Purchase
			
			UserShares uShare = new UserShares();
			uShare.shareId = sharetoBeBought.id;
			uShare.userId = currUser.id;
			uShare.companyName = sharetoBeBought.companyName;
			uShare.shareCount = quantity;
			
			//Updating the userShares table
			//int resultUserShare = new UserSharesDAO().insert(uShare);
			
			
			Transaction transaction = new Transaction();
			transaction.pricePerShare = sharetoBeBought.price;
			transaction.shareCount = quantity;
			transaction.shareId = sharetoBeBought.id;
			transaction.userId = currUser.id;
			
			float transactionCharge = (float) (amount*(DB.TransactionCharges));
			if (transactionCharge <= 100) {
				transaction.transactionCharges = 100;
			}else {
				transaction.transactionCharges = transactionCharge;
			}
			//transaction.transactionCharges = (float) (amount*(1.0+DB.TransactionCharges));
			transaction.sttCharges = (float) (amount*(DB.STTPercentage));
			transaction.type = 1; // Buy Transaction
			
			float totalCharges = amount + transaction.transactionCharges + transaction.sttCharges;
			float availableBalance = ((float)currUser.accountBalance) - totalCharges;
			
			
			//Updating the transactions table
			int resultTransaction = new TransactionDAO().insert(transaction);
			
			
			// Updating USerSHares table
			
			
			String fetchUserShares = "Select * from usershares where userid="+currUser.id+" and shareid="+sharetoBeBought.id+";";
			
			List<UserShares> checkShareExists = usDAO.retrieve(fetchUserShares);
			
			int insertShareResult = 0;
			int updateShareResult= 0;
			
			if(checkShareExists.isEmpty()) {
				//Insert Share logic
				String insertUserShares = "INSERT INTO usershares (userid, "
						+ "shareid,sharecount,companyname) "
						+ "VALUES (" + currUser.id + ", " + sharetoBeBought.id + ","
								+ ""+quantity+",'"+sharetoBeBought.companyName+"');";
				insertShareResult =  db.executeSQL(insertUserShares);
				
				if(insertShareResult>0) {
					System.out.println("Inserted new share...");
				}
				
			}else {
				//Update Share logic
				UserShares existingShare = new UserShares();
				
				for(UserShares userShare : checkShareExists) {
					if(userShare.shareId == sharetoBeBought.id) {
						existingShare = userShare;
					}
				}
				int updatedQuantity = existingShare.shareCount + quantity;
				
				
				String updateUserShare = "UPDATE usershares set "
						+ "userid =" + currUser.id + ", shareid=" + sharetoBeBought.id + ""
						+ ", sharecount=" + updatedQuantity +", companyName='"+sharetoBeBought.companyName+"' where "
								+ "shareid = "+existingShare.id+";";
				
				updateShareResult = db.executeSQL(updateUserShare);
				if(updateShareResult>0) {
					System.out.println("updated existing share successfully...");
				}
			}
			
			
			
			//Deduct Amount from account balance...
			String sql = "UPDATE \"user\" set accountBalance="+availableBalance+" where accountNumber = "+currUser.accountNumber+";";
			int resultBal = db.executeSQL(sql);
			
			boolean buyTransactionStatus = false;
			if(updateShareResult > 0 || insertShareResult > 0 ) {
				buyTransactionStatus = true;
				
			}
			
			if( resultBal>0 && buyTransactionStatus && resultTransaction > 0) {
				System.out.println("Buy Tansaction Successful...");				
			}else {
				System.err.println("Buy Transaction Failed...");
			}
			
		}else {
			System.err.println("Insufficient Balance in the user account...");
		}
	
		
		return 0;
	}

	public int sellShare() {
		
		//Step1: Get holding details from UserShares table
		User currUser = DmatUserSession.user;
		Share shareToSell = new Share();
		UserShares userShare = new UserShares();
		
		String sql = "SELECT * FROM usershares where userid = "+currUser.id+";";
		List<UserShares> userShares = usDAO.retrieve(sql); 
		
		System.out.println("Shares currently held by you: ");
		for(UserShares uShare:userShares) {
			uShare.prettyPrint();
		}
		
		//Step2: Input: quantity and execute sell transaction
		
		System.out.println("Enter the Share ID for the share to be sold...");
		int sellShareId = scanner.nextInt();
		
		System.out.println("Enter the quantity for sale...");
		int sellQuantity = scanner.nextInt();
		
		for(UserShares uShare:userShares) {
			
			if (uShare.shareId == sellShareId) {
				userShare = uShare;
			}
		}
		
		// Retrieve the share detail from share table
		
		String shareSql = "SELECT * from share where id="+userShare.shareId;
		
		List<Share> share = new ShareDAO().retrieve(shareSql);
		
		shareToSell = share.get(0);
		
		
		if(userShare.shareCount >= sellQuantity ) {
			//Sell share
			
			String updateUserShares = "";
			
			if(userShare.shareCount == sellQuantity) {
				//Delete from table logic
				updateUserShares = "DELETE from UserShares where userid="+currUser.id+" and shareid = "+userShare.shareId+";";
			}
			else {
				//Update table logic
				int updatedShareCount = userShare.shareCount - sellQuantity;
				updateUserShares = "UPDATE usershares set sharecount ="+updatedShareCount+" where userid="+currUser.id+" and shareid = "+shareToSell.id+";";
			}
			
						
			//Update UserShares
			int updateUserShareResult = db.executeSQL(updateUserShares);
			
			//Update Transaction
			
			float amount = sellQuantity * shareToSell.price;
			
			Transaction sellTransaction = new Transaction();
			sellTransaction.type = 2; //Sell Transaction
			sellTransaction.userId = currUser.id;
			sellTransaction.shareId = shareToSell.id;
			sellTransaction.pricePerShare = shareToSell.price;
			sellTransaction.shareCount = sellQuantity;
			
			float transactionCharge = (float) (amount*(DB.TransactionCharges));
			if (transactionCharge <= 100) {
				sellTransaction.transactionCharges = 100;
			}else {
				sellTransaction.transactionCharges = transactionCharge;
			}
			//transaction.transactionCharges = (float) (amount*(1.0+DB.TransactionCharges));
			sellTransaction.sttCharges = (float) (amount*(DB.STTPercentage));

			
			float totalCharges = amount + sellTransaction.transactionCharges + sellTransaction.sttCharges;
			float availableBalance = ((float)currUser.accountBalance) - totalCharges;
			
			
			//Updating the transactions table
			int resultTransaction = new TransactionDAO().insert(sellTransaction);
			
			
			//Update User Account Balance
			String updateUsersql = "UPDATE \"user\" set accountBalance="+availableBalance+" where accountNumber = "+currUser.accountNumber+";";
			int resultBal = db.executeSQL(updateUsersql);
			
			
			if(resultBal>0 && resultTransaction>0 && updateUserShareResult>0) {
				System.out.println("Sell Transaction Successful...");
			}else {
				System.err.println("Something went wrong.. Please try again!");
			}
			
		}else {
			System.err.println("Order Can't be Processed due to insufficent share holding... Please try again..");
		}		
		
		
		return 0;
		
	}
	
public void viewTransactionReport() {
		

		System.out.println("Enter Transaction Type: ");
		System.out.println("1. BUY Transactions");
		System.out.println("2. SELL Transactions");
		System.out.println("3. ALL Transactions");
		
		int selectedType = scanner.nextInt();
		
		List<Transaction> objects = null;
		
		if(selectedType == 3) {
			
			String fetchTransaction = "SELECT * from \"transaction\" where userid="+DmatUserSession.user.id;
			objects = new TransactionDAO().retrieve(fetchTransaction);
		}else if(selectedType==1) {
			String fetchTransaction = "SELECT * from \"transaction\" where userid="+DmatUserSession.user.id+" and type=1";
			objects = new TransactionDAO().retrieve(fetchTransaction);
		}else if(selectedType ==2) {
			String fetchTransaction = "SELECT * from \"transaction\" where userid="+DmatUserSession.user.id+" and type=2";
			objects = new TransactionDAO().retrieve(fetchTransaction);
		}else {
			System.err.println("Invalid type select...");
		}
		
		for(Transaction object : objects) {
			object.prettyPrint();
		}
	}
	

}



