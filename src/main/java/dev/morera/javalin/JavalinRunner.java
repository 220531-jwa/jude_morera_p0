package dev.morera.javalin;

import io.javalin.Javalin;
import static io.javalin.apibuilder.ApiBuilder.*;

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
				});
			});
		});
		//one method in accountService that checks if withdrawal/deposit
		/* TODO: logics
		 * 
		 */
	}

}
