package dev.morera.controllers;

import java.sql.SQLException;
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
		//System.out.println("COOL");
		ctx.status(200);
		List<Account> accs = as.getAllAccounts();
		ctx.json(accs);
	}

	public static void getAccountsFromClient(Context ctx) {
		int id = Integer.parseInt(ctx.pathParam("id"));
		//Client c = null;
		String greater = ctx.queryParam("amountGreaterThan");
		String lesser = ctx.queryParam("amountLessThan");
		//	System.out.println(greater + lesser);

		//		if (ctx.queryParam() != null) {
		//			
		//		}

		try {
			Client c = null;
			c = cs.getClientById(id);
			if(c != null) {
				List<Account> accs = as.getAccountsFromClient(id, greater, lesser);
				ctx.json(accs);
			}
			else {
				ctx.status(404);
			}
		} catch (Exception e) {
			ctx.status(404);
			e.printStackTrace();
		}
	}

	public static void createNewAccount(Context ctx) {
		int id = Integer.parseInt(ctx.pathParam("id"));
		Account accountFromReqBody = ctx.bodyAsClass(Account.class);
		Account a = as.createAccount(id, accountFromReqBody);
		if (a != null) {
			ctx.json(a);
			ctx.status(201);	
		}
		else {
			ctx.status(404); //TODO: get proppa status
		}

	}

	public static void getSpecAccount(Context ctx) {
		int id = Integer.parseInt(ctx.pathParam("id"));

		try {
			Client c = null;
			c = cs.getClientById(id);
			if(c != null) {
				int aid = Integer.parseInt(ctx.pathParam("aid"));
				Account acc = as.getSpecAccount(id, aid);
				if (acc != null) {
					ctx.status(200);
					ctx.json(acc);
				}
				else {
					ctx.status(404);
				}
			}
			else {
				ctx.status(404);
			}
		} catch (Exception e) {
			ctx.status(404);
			e.printStackTrace();
		}
	}

	public static void updateSpecAccount(Context ctx) {
		int id = Integer.parseInt(ctx.pathParam("id"));

		try {
			Client c = null;
			c = cs.getClientById(id);
			if(c != null) {
				int aid = Integer.parseInt(ctx.pathParam("aid"));
				Account aChanged = ctx.bodyAsClass(Account.class);


				if (as.updateAccount(id,aid,aChanged)) {
					ctx.status(200);
					//ctx.json(acc);
				}
				else {
					ctx.status(404);
				}
			}
			else {
				ctx.status(404);
			}
		} catch (Exception e) {
			ctx.status(404);
			e.printStackTrace();
		}
	}

	public static void deleteSpecAccount(Context ctx) {
		int id = Integer.parseInt(ctx.pathParam("id"));

		try {
			Client c = null;
			c = cs.getClientById(id);
			if(c != null) {
				int aid = Integer.parseInt(ctx.pathParam("aid"));



				if (as.deleteAccount(id, aid)) {
				}
				else {
					ctx.status(404);
				}
			}
			else {
				ctx.status(404);
			}
		} catch (Exception e) {
			ctx.status(404);
			e.printStackTrace();
		}
	}

	public static void changeBalance(Context ctx) {
		int id = Integer.parseInt(ctx.pathParam("id"));

		try {
			Client c = null;
			c = cs.getClientById(id);
			if(c != null) {
				int aid = Integer.parseInt(ctx.pathParam("aid"));
				String action = ctx.body();
				//System.out.println(action);
				Account acc =	as.singleMath(action, id, aid);
				if (acc.getId() == 0 && acc.getOwner_id() == 0 ) {
					ctx.status(422);
					ctx.json("Not enough money!");
					//ctx.json(acc)
				}
				else if (acc != null) {
					ctx.json(acc);
					ctx.status(200);
				}

			}
			else {ctx.status(404);
			}
		}

		catch (Exception e) {

			e.printStackTrace();
			ctx.status(404);
		}
	}

	public static void transferMoney(Context ctx) {

		int id = Integer.parseInt(ctx.pathParam("id"));
		int aid = Integer.parseInt(ctx.pathParam("aid"));
		int said = Integer.parseInt(ctx.pathParam("said"));
		try {
			Client c = null;
			c = cs.getClientById(id);
			if(c != null){
				String action = ctx.body();

				List<Account> accs = as.transferMath(action, id, aid, said);

				for (Account a : accs) { //for troubleshooting
					System.out.println(a);
				}

				System.out.println(accs.get(0).getId() == 0); //also troubleshooting
				System.out.println(accs.get(0).getOwner_id() == 0);

				if((accs.get(0).getId() == 0 )&& (accs.get(0).getOwner_id() == 0 )) {
					ctx.status(422);
					ctx.json("not enough money!");
				}
				else {
					ctx.status(200);
					ctx.json(accs);
				}
			}
		}//try
		catch(Exception e) {
			e.printStackTrace();
			ctx.status(404);
		}

	}//transfer

	//	public void accountGetter(Context ctx) {
	//String finda = ctx.queryParam(amountlessthan)
	//String req = ctx.body(); string builder? ;)

	/*body as class?
	 * include withdraw and deposit as double vars?
	 */
	//	}


}//file
