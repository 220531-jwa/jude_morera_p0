package dev.morera.services;

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

		System.out.println(splited[0].toLowerCase().equals("deposit"));
		if (splited[0].toLowerCase().equals("deposit"))
		
		{
			return accountDAO.addMoney(id, aid, splited);
		}
				return null;
	}
	
}
