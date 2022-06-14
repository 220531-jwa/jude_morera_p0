package dev.morera.controllers;

import java.util.List;

import dev.morera.models.Client;
import dev.morera.services.ClientService;
import io.javalin.http.Context;

public class ClientController {

	private static ClientService cs = new ClientService();
	
	public static void getAllClients(Context ctx) {
		ctx.status(200);
		List<Client> clients = cs.getAllClients();
		ctx.json(clients);
	}
	
	public static void getClientByID(Context ctx) {
		int id = Integer.parseInt(ctx.pathParam("id"));
		Client c = null;
		try {
			c = cs.getClientById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		ctx.status(200);
		ctx.json(c);
	}
	
	public static void createNewClient(Context ctx) {
		ctx.status(201);
		Client clientFromReqBody = ctx.bodyAsClass(Client.class);
		Client c = cs.createClient(clientFromReqBody);
		ctx.json(c);
	}
	
	
	
}
