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
		if (c != null) {
			ctx.status(200);
			ctx.json(c);
		}
		else {
			ctx.status(404);
		}
	}

	public static void createNewClient(Context ctx) {
		
		Client clientFromReqBody = ctx.bodyAsClass(Client.class);
		Client c = cs.createClient(clientFromReqBody);
		if (c != null) {
			ctx.json(c);
			ctx.status(201);	
		}
		else {
			ctx.status(500); //TODO: get proppa status
		}
		
	}

	public static void  deleteClient(Context ctx) {
		int id = Integer.parseInt(ctx.pathParam("id"));
		if (cs.deleteClient(id)) {
			ctx.status(205);
		}
		else {
			ctx.status(404);
		}
	}
	
	public static void updateClient(Context ctx) {
		int id = Integer.parseInt(ctx.pathParam("id"));
		Client cChanged = ctx.bodyAsClass(Client.class); //unmarshalling
		
		
		
		//System.out.println("updateClient -= " + cChanged);
		if(cs.updateClient(id, cChanged)) {
			ctx.status(201);
		}
		else {
			ctx.status(404);
		}
		//ctx.json(cChanged);

	}
}//file
