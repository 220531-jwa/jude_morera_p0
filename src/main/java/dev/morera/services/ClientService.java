package dev.morera.services;

import java.util.List;

import dev.morera.models.Client;
import dev.morera.repositories.ClientDAO;


public class ClientService {

	private static ClientDAO clientDAO = new ClientDAO();

	public List<Client> getAllClients() {
		return clientDAO.getAllClients();
	}

	public Client getClientById(int id) throws Exception {
		Client c = clientDAO.getClientById(id);

		
		
		return c;
	}

	public Client createClient(Client clientFromReqBody) {
	
		Client createdClient = clientDAO.createClient(clientFromReqBody);
		return createdClient;
	}

	public boolean deleteClient(int id) {
		return clientDAO.deleteClient(id);
		
	}

	public Client updateClient(int id,Client cChanged) {
		return(clientDAO.updateClient(id, cChanged));
		
	}
	
}
