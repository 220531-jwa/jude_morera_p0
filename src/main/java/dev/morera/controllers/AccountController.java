package dev.morera.controllers;

import java.util.List;
import dev.morera.services.AccountService;
import dev.morera.services.ClientService;
import io.javalin.http.Context;
import dev.morera.models.Account;
import dev.morera.models.Client;

public class AccountController {

	private static AccountService as = new AccountService();
	private static ClientService cs = new ClientService();

	public static void getAllAccounts(Context ctx) {
		ctx.status(200);
		List<Account> accs = as.getAllAccounts();
		ctx.json(accs);
	}

	public static void getAccountsFromClient(Context ctx) {
		int id = Integer.parseInt(ctx.pathParam("id"));
		//Client c = null;

		try {
			Client c = null;
			c = cs.getClientById(id);
			if(c != null) {
				List<Account> accs = as.getAccountsFromClient(id);
				ctx.json(accs);
			}
			else {
				ctx.status(404);
			}
		} catch (Exception e) {
			ctx.status(404);
			e.printStackTrace();
		};
	}
	
	public static void createNewAccount(Context ctx) {
		
		Account accountFromReqBody = ctx.bodyAsClass(Account.class);
		Account a = as.createAccount(accountFromReqBody);
		if (a != null) {
			ctx.json(a);
			ctx.status(201);	
		}
		else {
			ctx.status(500); //TODO: get proppa status
		}
		
	}
	


}//file