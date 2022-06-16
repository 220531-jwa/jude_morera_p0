package dev.morera.services;

import java.util.ArrayList;
import java.util.List;

import dev.morera.models.Account;
import dev.morera.repositories.AccountDAO;

public class AccountService {

	private static AccountDAO accountDAO = new AccountDAO();

	public List<Account> getAllAccounts() {
		return accountDAO.getAllAccounts();
	}

	public List<Account> getAccountsFromClient(int id, String greater, String lesser) {
		return accountDAO.getAccountsFromClient(id, greater, lesser);
		
		
	}

	public Account createAccount(int id, Account accountFromReqBody) {

		Account createdAccount = accountDAO.createAccount(id, accountFromReqBody);
		return createdAccount;
	}

	public Account getSpecAccount(int id, int aid) {
		return accountDAO.getSpecAccount(id, aid);
		
	}

	public boolean updateAccount(int id, int aid, Account aChanged) {
		return accountDAO.updateAccount(id, aid, aChanged);
	}
	
	public boolean deleteAccount(int id, int aid) {
		return accountDAO.deleteAccount(id, aid);
	}
	
	public Account singleMath(String action, int id, int aid) {
		
		if (action == null) {
			return null;
		}
		action = action.replaceAll("[{}\"\r\n ]", "");
		String[] splited = action.split(":");

		//System.out.println(splited[0].toLowerCase().equals("deposit"));
		if (splited[0].toLowerCase().equals("deposit"))
		
		{
			return accountDAO.addMoney(id, aid, splited);
		}
		if (splited[0].toLowerCase().equals("withdraw"))
		{
			Account feedback = accountDAO.subMoney(id, aid, splited);
			return feedback;
		}
		
				return null;
	}
	
	public List<Account> transferMath(String action, int id, int aid, int said) {
		
		if (action == null) {
			return null;
		}
		action = action.replaceAll("[{}\"\r\n ]", "");
		String[] splited = action.split(":");
		
		if (splited[0].toLowerCase().equals("amount")) {
			List<Account> accounts = new ArrayList<>();
			
			//Account feedback = accountDAO.subMoney(id, aid, splited);
			//if
						
			accounts.add(accountDAO.subMoney(id, aid, splited));
			//System.out.println(accounts.get(0));
			if(accounts.get(0).getId() == 0 && accounts.get(0).getOwner_id() == 0 ) {
				return accounts;
			}
			accounts.add(accountDAO.addMoney(id, said, splited));
			if(accounts.get(1) == null) {
				return null;
			}
			return accounts;
		}
				
				
				
		
		return null;
	}
	
	
	
	
	
	
	
	
	
	
	
}//file
