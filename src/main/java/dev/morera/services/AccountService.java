package dev.morera.services;

import java.util.List;

import dev.morera.models.Account;
import dev.morera.repositories.AccountDAO;

public class AccountService {

	private static AccountDAO accountDAO = new AccountDAO();

	public List<Account> getAllAccounts() {
		return accountDAO.getAllAccounts();
	}

	public List<Account> getAccountsFromClient(int id) {
		return accountDAO.getAccountsFromClient(id);
		
		
	}

	public Account createAccount(Account accountFromReqBody) {

		Account createdAccount = accountDAO.createAccount(accountFromReqBody);
		return createdAccount;
	}
	
}
