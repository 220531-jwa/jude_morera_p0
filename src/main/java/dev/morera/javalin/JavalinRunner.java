package dev.morera.javalin;

/*
 * Project reqs:
 * 
 * POST /clients => Creates a new client return a 201 status code

GET /clients => gets all clients return 200

GET /clients/10 => get client with id of 10 return 404 if no such client exist

PUT /clients/12 => updates client with id of 12 return 404 if no such client exist

DELETE /clients/15 => deletes client with the id of15 return 404 if no such client exist return 205 if success

POST /clients/5/accounts =>creates a new account for client with the id of 5 return a 201 status code

GET /clients/7/accounts => get all accounts for client 7 return 404 if no client exists

GET /clients/7/accounts?amountLessThan=2000&amountGreaterThan400 => get all accounts for client 7 between 400 and 2000 return 404 if no client exists

GET /clients/9/accounts/4 => get account 4 for client 9 return 404 if no account or client exists

PUT /clients/10/accounts/3 => update account with the id 3 for client 10 return 404 if no account or client exists

DELETE /clients/15/accounts/6 => delete account 6 for client 15 return 404 if no account or client exists

PATCH /clients/17/accounts/12 => Withdraw/deposit given amount (Body: {"deposit":500} or {"withdraw":250} return 404 if no account or client exists return 422 if insufficient funds

PATCH /clients/12/accounts/7/transfer/8 => transfer funds from account 7 to account 8 (Body: {"amount":500}) return 404 if no client or either account exists return 422 if insufficient funds
 */



import io.javalin.Javalin;
import static io.javalin.apibuilder.ApiBuilder.*;

import dev.morera.controllers.AccountController;
import dev.morera.controllers.ClientController;

public class JavalinRunner {

	public static void main(String[] args) {

		Javalin app = Javalin.create();

		app.start(8081);

		app.routes(() -> {
			path("/clients", () ->{
				get(ClientController::getAllClients);
				post(ClientController::createNewClient);
				path("/{id}" , () -> {
					get(ClientController::getClientByID);
					delete(ClientController::deleteClient);
					put(ClientController::updateClient);
					//patch(ClientController::updateClient);
					
					path("/accounts", ()->{
						get(AccountController::getAccountByID);
					});
				});
			});
			path("accounts", () -> {
				get(AccountController::getAllAccounts);
			});
		});
		//one method in accountService that checks if withdrawal/deposit
		/* TODO: logics
		 * 
		 */
	}

}
